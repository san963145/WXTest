package com.wx.util;

import org.apache.log4j.Logger;

import com.wx.dao.StudentInfoDao;
import com.wx.daoImpl.StudentInfoDaoImpl;




public class Test {
	
	private static Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) throws Exception
	{
		/*String s="<xml><ToUserName><![CDATA[gh_a9a9cfe58f0f]]></ToUserName><FromUserName><![CDATA[obsmNwQJVwVWQXJs7YZSY4VOwGHg]]></FromUserName><CreateTime>1460883674</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[57]]></Content><MsgId>6274447603517109013</MsgId></xml> ";
		int toUserNameStartIndex=s.indexOf("<ToUserName>")+21;
        int toUserNameEndIndex=s.indexOf("]]></ToUserName>");
        int FromUserNameStartIndex=s.indexOf("<FromUserName>")+23;
        int FromUserNameEndIndex=s.indexOf("]]></FromUserName>");
        System.out.println(s.substring(toUserNameStartIndex,toUserNameEndIndex));
        System.out.println(s.substring(FromUserNameStartIndex,FromUserNameEndIndex));
        */
        StudentInfoDao dao=new StudentInfoDaoImpl();
        String openID=dao.checkStudentLogin("1","1");
        System.out.println("3 "+openID);
		
		
	}
    
	public static void log(String s)
	{
		logger.error(s);
	}
	public static void log(long s)
	{
		logger.error(s);
	}
	public static void log(int s)
	{
		logger.error(s);
	}
}
