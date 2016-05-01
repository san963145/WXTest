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
        if(classID!=null)   //课程已开启
        {
           long lessonID=(long) application.getAttribute("lessonID");
           long curTime=System.currentTimeMillis();
           if(curTime-lessonID>Consts.DataTimeOut*60*1000)   //学生、教师发送信息,lesson过期,清空数据
           {
        	   ClearApplicationData.clear(application);
        	   String replyContent="系统当前未开启任何课堂,请提供教职工ID";
               String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
               response.getWriter().write(xml);
               return ;
           }
           String tOpenID=(String) application.getAttribute("tOpenID");
           if(fromUserName.equals(tOpenID))     //当前课堂未退出,教师openID已绑定,教师返回微信发送消息
           {
        	  String r=new TeachClassDaoImpl().getNameAndTotalbyClass(classID);
        	  String className=r.split("#")[0];
        	  String title="返回当前课堂";
         	  String url="http://1.myjavatest.applinzi.com/pages/teacher/main.jsp";
         	  String replyContent="课程名:\r\n"+className;
         	  String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
         	  response.getWriter().write(xml);
         	  return ;
           }
           
           String sid=lessonSidListDao.checkOpenID(lessonID,fromUserName);
           if(!sid.equals("null"))    //openID与sid已绑定
           {
        	   if(content.startsWith("Q:")||content.startsWith("q:")||content.startsWith("Q：")||content.startsWith("q："))
        	   {
        		   Process.feedBack(request, response, xmlMap, sid);
        		   return;
        	   }
        	   String mode=(String) application.getAttribute("mode");
               if(mode!=null)
               {
            	   if(mode.equals("option1"))  //签到模式
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
        		   String c="当前课堂已开启,教师未发布任务!";
        		   String xml=ReplyContent.generateXML(fromUserName, toUserName, c);
        		   response.getWriter().write(xml);
        	   }
           }
           else       //未绑定
           {
        	   if(tClassStudentDao.checkUserId(classID, content)) // 学生ID正确
               {
        		   if(lessonSidListDao.checkSid(lessonID, content))
        		   {
        			   String replyContent="该ID已在其他终端登录,请提供其他ID!";
                       String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                       response.getWriter().write(xml); 
                       return;
        		   }
        		   if(Data.stagedOpenIDList.containsKey(fromUserName))
        		   {
        			   Data.stagedOpenIDList.remove(fromUserName);
        			   lessonSidListDao.add(lessonID, content, fromUserName);
                	   String sName=studentInfoDao.getNameById(content);
                	   String t="该ID为学生,成功进入课堂,用户名:"+sName;
                	   String replyContent="课堂中可随时提出问题,发送问题格式  Q:内容";
                 	   String xml=ReplyContent.generateXML(fromUserName, toUserName, t, replyContent);
                 	   response.getWriter().write(xml);
        		   }  
        		   else
        		   {
        			   
        			   Data.stagedOpenIDList.put(fromUserName, "");
        			   String t="确认以ID:"+content+"进入当前课堂?";
                	   String replyContent="确认请继续发送该ID,发送其余内容可取消";
                 	   String xml=ReplyContent.generateXML(fromUserName, toUserName, t, replyContent);
                 	   response.getWriter().write(xml);
        		   }
               }
               else
               {
            	   if(Data.stagedOpenIDList.containsKey(fromUserName))
            	   {
            		   Data.stagedOpenIDList.remove(fromUserName);
            		   String replyContent="取消成功,请提供正确的学生ID";
                       String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                       response.getWriter().write(xml);
            	   }
            	   String replyContent="请提供正确的学生ID";
                   String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                   response.getWriter().write(xml);
               }  
           }                                     
        }
        else      //课程未开启
        {
          if(teacherInfoDao.checkTid(content))
          {
        	 // application.setAttribute("tOpenID", fromUserName);
        	  String title="教职工登录";
        	  String url="http://1.myjavatest.applinzi.com?tOpenID="+fromUserName;
        	  String replyContent="当前终端OpenID:\r\n"+fromUserName;
        	  String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
        	  response.getWriter().write(xml);
          }
          else
          {
        	  String replyContent="系统当前未开启任何课堂,请提供教职工ID";
              String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
              response.getWriter().write(xml); 
          }          
        }
	}

}
