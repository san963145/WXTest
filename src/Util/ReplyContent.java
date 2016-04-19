package Util;

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

}
