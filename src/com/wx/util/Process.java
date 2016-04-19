package com.wx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Process {
	
	private static final long TIMELIMIT=60;
	
	public static void sign(HttpServletRequest request, HttpServletResponse response,Map<String,String> map,String userID) throws IOException
	{
		String toUserName=map.get("toUserName");
        String fromUserName=map.get("fromUserName");
        String content=map.get("content");
        ServletContext application=(ServletContext) request.getServletContext();
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
      	   String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "系统当前为签到模式,已签到成功,当前用户:"+userID);
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
              	  if(list==null) //第一位签到
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
              	  String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "签到成功:"+userID);
                    response.getWriter().write(replyContent);
              	}
              	else  //令牌正确,超时
              	{
              		String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "系统当前为签到模式,已超过签到时限,签到失败!");
                      response.getWriter().write(replyContent);
              	}
              }
              else    // 令牌错误
              {
              	Object obj=application.getAttribute("initTime");
              	if(obj!=null)
              	{
              	   long t=(long)obj;
              	   long curTime=System.currentTimeMillis();
              	   String c="";
              	   if(curTime-t<TIMELIMIT*1000)
              	   {
              		   c="系统当前为签到模式,请发送正确的签到令牌!剩余签到时间"+(TIMELIMIT-(curTime-t)/1000)+"秒.";  
              	   }
              	   else
              	   {
              		   c="系统当前为签到模式,已超过签到时限,未成功签到!";
              	   }               	   
              	   String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c);
                     response.getWriter().write(replyContent);
              	}
              }
            } 
            else
            {
          	  String c="系统当前为签到模式,等待教师设定随机数!";
          	  String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c);
              response.getWriter().write(replyContent);
            }
        }
	}

}
