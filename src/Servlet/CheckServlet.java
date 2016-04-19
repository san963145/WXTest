package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;





import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import Util.ReplyContent;
import Util.SignUtil;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CheckServlet.class); 
	private static final long TIMELIMIT=60;
	
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

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
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
        StringBuffer sb = new StringBuffer();  
        InputStream is = request.getInputStream();  
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }  
        String xml = sb.toString(); 
 
            
        int toUserNameStartIndex=xml.indexOf("<ToUserName>")+21;
        int toUserNameEndIndex=xml.indexOf("]]></ToUserName>");
        int fromUserNameStartIndex=xml.indexOf("<FromUserName>")+23;
        int fromUserNameEndIndex=xml.indexOf("]]></FromUserName>");
        int contentStartIndex=xml.indexOf("<Content>")+18;
        int contentEndIndex=xml.indexOf("]]></Content>");
        String toUserName=xml.substring(toUserNameStartIndex, toUserNameEndIndex);
        String fromUserName=xml.substring(fromUserNameStartIndex, fromUserNameEndIndex);
        String content=xml.substring(contentStartIndex, contentEndIndex);
        
        HashMap<String,String> map=(HashMap<String,String>) application.getAttribute("map");
        boolean flag=false;
        String userID=null;
        if(map!=null)
        {
        	if(map.containsKey(fromUserName))
        	{
        		flag=true;
        		userID=map.get(fromUserName);
        	}
        }
        if(flag)
        {
          ArrayList<String>list=(ArrayList<String>) application.getAttribute("signList");
          boolean x=false;
          if(list!=null)
          {
        	  if(list.contains(userID))
        	  {
        		  x=true;
        	  }
          }
          if(x)
          {
        	  String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "ϵͳ��ǰΪǩ��ģʽ,��ǩ���ɹ�,��ǰ�û�:"+userID);
              response.getWriter().write(replyContent); 
          }
          else
          {
        	  String randNum=(String) application.getAttribute("randNum");
              if(randNum!=null)
              {
            	if(!content.matches("\\d+"))
            	{
            		content="0";
            	}
            	int inputNum=Integer.parseInt(content);
                int checkNum=Integer.parseInt(randNum)+Integer.parseInt(userID);
                if(inputNum==checkNum)
                {
                	long initTime=(long) application.getAttribute("initTime");
                	long curTime=System.currentTimeMillis();
                	if(curTime-initTime<TIMELIMIT*1000)
                	{
                	  if(list==null) //��һλǩ��
                	  {
                		list=new ArrayList<String>();
                		list.add(userID);
                		application.setAttribute("signList", list);
                	  }
                	  else  
                	  {
                		list.add(userID);
                		application.removeAttribute("signList");
                		application.setAttribute("signList", list);
                	  }
                	  String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "ǩ���ɹ�:"+userID);
                      response.getWriter().write(replyContent);
                	}
                	else  //������ȷ,��ʱ
                	{
                		String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "ϵͳ��ǰΪǩ��ģʽ,�ѳ���ǩ��ʱ��,ǩ��ʧ��!");
                        response.getWriter().write(replyContent);
                	}
                }
                else    // ���ƴ���
                {
                	Object obj=application.getAttribute("initTime");
                	if(obj!=null)
                	{
                	   long t=(long)obj;
                	   long curTime=System.currentTimeMillis();
                	   String c="";
                	   if(curTime-t<TIMELIMIT*1000)
                	   {
                		   c="ϵͳ��ǰΪǩ��ģʽ,�뷢����ȷ��ǩ������!ʣ��ǩ��ʱ��"+(TIMELIMIT-(curTime-t)/1000)+"��.";  
                	   }
                	   else
                	   {
                		   c="ϵͳ��ǰΪǩ��ģʽ,�ѳ���ǩ��ʱ��,δ�ɹ�ǩ��!";
                	   }               	   
                	   String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c);
                       response.getWriter().write(replyContent);
                	}
                }
              } 
              else
              {
            	  String c="ϵͳ��ǰΪǩ��ģʽ,�ȴ���ʦ�趨�����!";
            	  String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c);
                  response.getWriter().write(replyContent);
              }
          }
          
        }
        else
        {
          String replyContent="Received:"+content+"\r\n"+"Your openID:"+fromUserName;
          StringBuffer str = new StringBuffer();                
          str.append(
                     "<xml>"+
                     "<ToUserName><![CDATA["+fromUserName+"]]></ToUserName>"+
                     "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>"+
                     "<CreateTime>1460885578</CreateTime>"+
                     "<MsgType><![CDATA[news]]></MsgType>"+
                     "<ArticleCount>1</ArticleCount>"+
                     "<Articles>"+
                     "<item>"+
                     "<Title><![CDATA[�����¼ҳ��]]></Title>"+
                     "<Description><![CDATA["+replyContent+"]]></Description>"+
                     "<Url><![CDATA[http://1.myjavatest.applinzi.com]]></Url>"+
                     "</item>"+
                     "</Articles>"+
                     "</xml>"
                   );
           response.getWriter().write(str.toString()); 
        }
	}

}
