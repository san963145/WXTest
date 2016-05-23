package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.util.ClearApplicationData;
import com.wx.util.Consts;
import com.wx.util.Test;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext application=(ServletContext) request.getServletContext();
		HttpSession session=request.getSession();
		String sessionCurUser=(String) session.getAttribute("curUser");
		Long lessonID=(Long) application.getAttribute("lessonID");
		if(sessionCurUser==null)
		{
			if(lessonID!=null)
			{
				long t=System.currentTimeMillis();
				if(t-lessonID<Consts.SESSIONTIME*60*1000)
				{
					Test.log("No clear");
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				}
			}
		}
		String tOpenID=(String) application.getAttribute("tOpenID");
		ClearApplicationData.clear(application);		
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
		   for(Cookie c : cookies)
		   {
				c.setMaxAge(0);
				response.addCookie(c);
		   }
		}
		session.invalidate();
		if(tOpenID!=null)
		{
		   request.getRequestDispatcher("index.jsp?tOpenID="+tOpenID).forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
