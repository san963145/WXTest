package com.wx.util;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.dao.FeedBackDao;
import com.wx.dao.StudentAnswerDao;
import com.wx.dao.StudentCheckinDao;
import com.wx.daoImpl.FeedBackDaoImpl;
import com.wx.daoImpl.StudentAnswerDaoImpl;
import com.wx.daoImpl.StudentCheckinDaoImpl;
import com.wx.daoImpl.StudentInfoDaoImpl;

public class Process {
	
	
	
	public static void sign(HttpServletRequest request, HttpServletResponse response,Map<String,String> map,String userID) throws IOException
	{
		String toUserName=map.get("toUserName");
        String fromUserName=map.get("fromUserName");
        String content=map.get("content");
        ServletContext application=(ServletContext) request.getServletContext();
        String sName=new StudentInfoDaoImpl().getNameById(userID);
        Map<String,String>sMap=(Map<String,String>) application.getAttribute("signMap");
        boolean x=false;
        if(sMap!=null)
        {
      	  if(sMap.containsKey(userID))
      	  {
      		  x=true;
      	  }
        }
        if(x)
        {          
      	   String replyContent=ReplyContent.generateXML(fromUserName, toUserName, "课堂当前为签到模式,已签到成功,当前用户:"+sName);
           response.getWriter().write(replyContent); 
        }
        else
        {
      	    String randNum=(String) application.getAttribute("randNum");
            if(randNum!=null)
            {
          	if(!content.matches("\\d+"))
          	{
          		content="1";
          	}
          	  int inputNum=Integer.parseInt(content);
              int checkNum=Integer.parseInt(randNum)+Integer.parseInt(userID);
  
              if(inputNum==checkNum)  //令牌正确
              {
              	long initTime=(long) application.getAttribute("initSignTime");
              	long curTime=System.currentTimeMillis();
              	if(curTime-initTime<Consts.SIGNTIMELIMIT*60*1000)
              	{
              	  StudentCheckinDao studentCheckinDao=new StudentCheckinDaoImpl();
              	  long st=System.currentTimeMillis();
              	  Timestamp checkTime=new Timestamp(st);
              	  Time cTime=new Time(st);
              	  long lessonID=(long) application.getAttribute("lessonID");
              	  if(sMap==null) //第一位签到
              	  {
              		sMap=new HashMap<String,String>();
              		sMap.put(userID, sName+" "+cTime.toString());
              		application.setAttribute("signMap", sMap);              		              		
              		studentCheckinDao.add(userID, lessonID, checkTime.toString(), 1);
              	  }
              	  else  
              	  {
              		sMap.put(userID, sName+" "+cTime.toString());
              		application.removeAttribute("signMap");
              		application.setAttribute("signMap", sMap);
              		int seq=studentCheckinDao.getMaxSeqBylessonID(lessonID);
              		studentCheckinDao.add(userID, lessonID, checkTime.toString(), seq+1);
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
              	Object obj=application.getAttribute("initSignTime");
              	if(obj!=null)
              	{
              	   long t=(long)obj;
              	   long curTime=System.currentTimeMillis();
              	   String c="";
              	   if(curTime-t<Consts.SIGNTIMELIMIT*60*1000)
              	   {
              		   c="系统当前为签到模式,请发送正确的签到令牌!剩余签到时间"+(Consts.SIGNTIMELIMIT*60-(curTime-t)/1000)+"秒.";  
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

	public static void test(HttpServletRequest request, HttpServletResponse response,Map<String,String> map,String userID) throws IOException
	{
		String toUserName=map.get("toUserName");
        String fromUserName=map.get("fromUserName");
        String content=map.get("content");
        ServletContext application=(ServletContext) request.getServletContext();
        long qid=(long) application.getAttribute("qid");
        String answer=(String) application.getAttribute("answer");
        String c=(String) application.getAttribute("title");
        String [] st=c.split("#");
        String title="";
        String titleContent="无内容";
        if(st.length<2)
        {
           title=st[0];        
        }
        else
        {
          title=st[0];
          titleContent=st[1];
        }
        if(content.startsWith("A:")||content.startsWith("a:")||content.startsWith("A：")||content.startsWith("a："))
        {
        	String reAnswer="";
        	if(content.startsWith("A:")||content.startsWith("a:"))
        	   reAnswer=content.split(":")[1];
        	else 
        		reAnswer=content.split("：")[1];
        	StudentAnswerDao dao=new StudentAnswerDaoImpl();
        	int corrected=0;
        	if(answer.equalsIgnoreCase(reAnswer))
        	{
        		corrected=1;
        	}
        	boolean flag=dao.checkSidByQid(qid, userID);
        	String sName=new StudentInfoDaoImpl().getNameById(userID);
        	if(flag)
        	{
        		dao.updateAnswer(qid, userID, reAnswer,corrected);
        		String c2="发送成功,已将答案更改为:"+reAnswer;
            	String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c2);
                response.getWriter().write(replyContent);
        	}
        	else
        	{
        		Time time=new Time(System.currentTimeMillis());       		
        		dao.add(userID, qid, sName,reAnswer, corrected,time.toString());
        		String c2="已成功发送答案:"+reAnswer;
            	String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c2);
                response.getWriter().write(replyContent);
        	}
        	
        }
        else
        {
        	Test.log("suc");
        	String t="当前为答题模式,教师已发布题目.答题请以格式  A:答案 发送";
      	    String replyContent="题目标题:"+title+"\r\n内容:"+titleContent;
      	    String xml=ReplyContent.generateXML(fromUserName, toUserName, t, replyContent);
      	    response.getWriter().write(xml);
        }
        
	}

	public static void feedBack(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> map, String userID) throws IOException 
	{
		// TODO Auto-generated method stub
		String toUserName=map.get("toUserName");
        String fromUserName=map.get("fromUserName");
        String content=map.get("content");
        ServletContext application=(ServletContext) request.getServletContext();
        long lessonID=(long) application.getAttribute("lessonID");
        if(content.startsWith("Q:")||content.startsWith("q:")||content.startsWith("Q：")||content.startsWith("q："))
        {
        	String question="";
        	if(content.startsWith("Q:")||content.startsWith("q:"))
        	   question=content.split(":")[1];
        	else 
        		question=content.split("：")[1];
        	FeedBackDao dao=new FeedBackDaoImpl();
        	Time time=new Time(System.currentTimeMillis());
        	boolean flag=dao.add(lessonID, userID, question, time.toString()); 
        	if(flag)
        	{
        		String c="反馈问题发送成功!";
            	String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c);
                response.getWriter().write(replyContent);
        	}
        	else
        	{
        		String c="发送失败!";
            	String replyContent=ReplyContent.generateXML(fromUserName, toUserName, c);
                response.getWriter().write(replyContent);
        	}
        }
        else
        {
        	String t="当前为问题反馈模式,请以格式  Q:内容  发送问题";
      	    String replyContent="";
      	    String xml=ReplyContent.generateXML(fromUserName, toUserName, t, replyContent);
      	    response.getWriter().write(xml);
        }
	}
	
}
