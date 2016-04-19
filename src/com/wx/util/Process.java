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

}
