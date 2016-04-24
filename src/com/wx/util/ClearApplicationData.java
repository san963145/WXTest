package com.wx.util;

import javax.servlet.ServletContext;

public class ClearApplicationData {
	
	public static void clear(ServletContext application)
	{
		application.removeAttribute("classID");
		application.removeAttribute("lessonID");
		application.removeAttribute("mode");
		application.removeAttribute("randNum");
		application.removeAttribute("initSignTime");
		application.removeAttribute("signMap");
		application.removeAttribute("tOpenID");
		application.removeAttribute("qid");
		application.removeAttribute("answer");
		application.removeAttribute("title");
		application.removeAttribute("number");
		application.removeAttribute("curUser");
	}
	public static void clearWithoutTOpenID(ServletContext application)
	{
		application.removeAttribute("classID");
		application.removeAttribute("lessonID");
		application.removeAttribute("mode");
		application.removeAttribute("randNum");
		application.removeAttribute("initSignTime");
		application.removeAttribute("signMap");
		application.removeAttribute("qid");
		application.removeAttribute("answer");
		application.removeAttribute("title");
		application.removeAttribute("number");
		application.removeAttribute("curUser");
	}

}
