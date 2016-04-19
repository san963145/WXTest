package Util;

import Dao.StudentInfoDao;



public class Test {
	
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
        StudentInfoDao dao=new StudentInfoDao();
        String openID=dao.checkStudentLogin("1","1");
        System.out.println("3 "+openID);
		
		
	}

}
