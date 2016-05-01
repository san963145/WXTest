package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wx.dao.LessonSidListDao;
import com.wx.dao.StudentInfoDao;
import com.wx.dao.TClassStudentDao;
import com.wx.dao.TeacherInfoDao;
import com.wx.daoImpl.LessonSidListDaoImpl;
import com.wx.daoImpl.StudentInfoDaoImpl;
import com.wx.daoImpl.TClassStudentDaoImpl;
import com.wx.daoImpl.TeachClassDaoImpl;
import com.wx.daoImpl.TeacherInfoDaoImpl;
import com.wx.util.ClearApplicationData;
import com.wx.util.Consts;
import com.wx.util.Data;
import com.wx.util.Process;
import com.wx.util.ReceiveParse;
import com.wx.util.ReplyContent;
import com.wx.util.SignUtil;


/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CheckServlet.class); 
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.error("get 1238");
		
		String signature = request.getParameter("signature");  

		String timestamp = request.getParameter("timestamp");  

        String nonce = request.getParameter("nonce");  
 
        String echostr = request.getParameter("echostr");  
  
        PrintWriter out = response.getWriter();  

        if (SignUtil.checkSignature(signature, timestamp, nonce)) 
        {  
           out.print(echostr);  
        }  
        out.close();  
        out = null;				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        ServletContext application=(ServletContext) request.getServletContext();
        Map<String,String> xmlMap=ReceiveParse.parse(request, response);
        String toUserName=xmlMap.get("toUserName");
        String fromUserName=xmlMap.get("fromUserName");
        String content=xmlMap.get("content");
        TClassStudentDao tClassStudentDao=new TClassStudentDaoImpl();
        TeacherInfoDao teacherInfoDao=new TeacherInfoDaoImpl();
        StudentInfoDao studentInfoDao=new StudentInfoDaoImpl();
        LessonSidListDao lessonSidListDao=new LessonSidListDaoImpl();
        
        String classID=(String) application.getAttribute("classID");
        if(classID!=null)   //�γ��ѿ���
        {
           long lessonID=(long) application.getAttribute("lessonID");
           long curTime=System.currentTimeMillis();
           if(curTime-lessonID>Consts.DataTimeOut*60*1000)   //ѧ������ʦ������Ϣ,lesson����,�������
           {
        	   ClearApplicationData.clear(application);
        	   String replyContent="ϵͳ��ǰδ�����κο���,���ṩ��ְ��ID";
               String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
               response.getWriter().write(xml);
               return ;
           }
           String tOpenID=(String) application.getAttribute("tOpenID");
           if(fromUserName.equals(tOpenID))     //��ǰ����δ�˳�,��ʦopenID�Ѱ�,��ʦ����΢�ŷ�����Ϣ
           {
        	  String r=new TeachClassDaoImpl().getNameAndTotalbyClass(classID);
        	  String className=r.split("#")[0];
        	  String title="���ص�ǰ����";
         	  String url="http://1.myjavatest.applinzi.com/pages/teacher/main.jsp";
         	  String replyContent="�γ���:\r\n"+className;
         	  String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
         	  response.getWriter().write(xml);
         	  return ;
           }
           
           String sid=lessonSidListDao.checkOpenID(lessonID,fromUserName);
           if(!sid.equals("null"))    //openID��sid�Ѱ�
           {
        	   if(content.startsWith("Q:")||content.startsWith("q:")||content.startsWith("Q��")||content.startsWith("q��"))
        	   {
        		   Process.feedBack(request, response, xmlMap, sid);
        		   return;
        	   }
        	   String mode=(String) application.getAttribute("mode");
               if(mode!=null)
               {
            	   if(mode.equals("option1"))  //ǩ��ģʽ
            	   {
            		   Process.sign(request, response, xmlMap, sid); 
            	   }
            	   else if(mode.equals("option2"))
            	   {
            		   Process.test(request, response, xmlMap, sid); 
            	   }
            	   else if(mode.equals("option3"))
            	   {
            		   Process.feedBack(request, response, xmlMap, sid);
            	   }            	   
               } 
               else
        	   {
        		   String c="��ǰ�����ѿ���,��ʦδ��������!";
        		   String xml=ReplyContent.generateXML(fromUserName, toUserName, c);
        		   response.getWriter().write(xml);
        	   }
           }
           else       //δ��
           {
        	   if(tClassStudentDao.checkUserId(classID, content)) // ѧ��ID��ȷ
               {
        		   if(lessonSidListDao.checkSid(lessonID, content))
        		   {
        			   String replyContent="��ID���������ն˵�¼,���ṩ����ID!";
                       String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                       response.getWriter().write(xml); 
                       return;
        		   }
        		   if(Data.stagedOpenIDList.containsKey(fromUserName))
        		   {
        			   Data.stagedOpenIDList.remove(fromUserName);
        			   lessonSidListDao.add(lessonID, content, fromUserName);
                	   String sName=studentInfoDao.getNameById(content);
                	   String t="��IDΪѧ��,�ɹ��������,�û���:"+sName;
                	   String replyContent="�����п���ʱ�������,���������ʽ  Q:����";
                 	   String xml=ReplyContent.generateXML(fromUserName, toUserName, t, replyContent);
                 	   response.getWriter().write(xml);
        		   }  
        		   else
        		   {
        			   
        			   Data.stagedOpenIDList.put(fromUserName, "");
        			   String t="ȷ����ID:"+content+"���뵱ǰ����?";
                	   String replyContent="ȷ����������͸�ID,�����������ݿ�ȡ��";
                 	   String xml=ReplyContent.generateXML(fromUserName, toUserName, t, replyContent);
                 	   response.getWriter().write(xml);
        		   }
               }
               else
               {
            	   if(Data.stagedOpenIDList.containsKey(fromUserName))
            	   {
            		   Data.stagedOpenIDList.remove(fromUserName);
            		   String replyContent="ȡ���ɹ�,���ṩ��ȷ��ѧ��ID";
                       String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                       response.getWriter().write(xml);
            	   }
            	   String replyContent="���ṩ��ȷ��ѧ��ID";
                   String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                   response.getWriter().write(xml);
               }  
           }                                     
        }
        else      //�γ�δ����
        {
          if(teacherInfoDao.checkTid(content))
          {
        	 // application.setAttribute("tOpenID", fromUserName);
        	  String title="��ְ����¼";
        	  String url="http://1.myjavatest.applinzi.com?tOpenID="+fromUserName;
        	  String replyContent="��ǰ�ն�OpenID:\r\n"+fromUserName;
        	  String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
        	  response.getWriter().write(xml);
          }
          else
          {
        	  String replyContent="ϵͳ��ǰδ�����κο���,���ṩ��ְ��ID";
              String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
              response.getWriter().write(xml); 
          }          
        }
	}

}
