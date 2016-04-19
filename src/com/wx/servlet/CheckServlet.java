package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wx.util.ReceiveParse;
import com.wx.util.ReplyContent;
import com.wx.util.SignUtil;
import com.wx.util.Process;

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
        if(flag)   //已成功登录
        {
           Process.sign(request, response, xmlMap, userID);        
        }
        else      //未登录
        {
          String replyContent="Received:"+content+"\r\n"+"Your openID:"+fromUserName;
          String title="进入登录页面";
          String url="http://1.myjavatest.applinzi.com";
          String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
          response.getWriter().write(xml); 
        }
	}

}
