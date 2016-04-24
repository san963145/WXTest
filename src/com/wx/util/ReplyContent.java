package com.wx.util;

public class ReplyContent {
	
	public static String generateXML(String fromUserName,String toUserName,String content)
	{
		String replyContent=content;
    	StringBuffer str = new StringBuffer();                
        str.append(
                     "<xml>"+
                     "<ToUserName><![CDATA["+fromUserName+"]]></ToUserName>"+
                     "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>"+
                     "<CreateTime>1460885578</CreateTime>"+
                     "<MsgType><![CDATA[text]]></MsgType>"+
                     "<Content><![CDATA["+replyContent+"]]></Content>"+
                     "</xml>"
                   );
        return str.toString();
	}
	public static String generateXML(String fromUserName,String toUserName,String title,String content,String url)
	{
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
                        "<Title><![CDATA["+title+"]]></Title>"+
                        "<Description><![CDATA["+content+"]]></Description>"+
                        "<Url><![CDATA["+url+"]]></Url>"+
                        "</item>"+
                        "</Articles>"+
                        "</xml>"
                   );
        return str.toString();
	}
	public static String generateXML(String fromUserName,String toUserName,String title,String content)
	{
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
                        "<Title><![CDATA["+title+"]]></Title>"+
                        "<Description><![CDATA["+content+"]]></Description>"+
                        "</item>"+
                        "</Articles>"+
                        "</xml>"
                   );
        return str.toString();
	}
	

}
