package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.FeedBackDao;
import com.wx.daoImpl.FeedBackDaoImpl;

/**
 * Servlet implementation class FeedBack
 */
@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedBack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String tid=(String) session.getAttribute("curUser");
		if(tid==null)
		{
			request.getRequestDispatcher("Logout").forward(request, response);
			return ;
		}
		ServletContext application=(ServletContext) request.getServletContext();
		long lessonID=(long) application.getAttribute("lessonID");
		FeedBackDao dao=new FeedBackDaoImpl();
		ArrayList<String> list=dao.getListByLesson(lessonID);
		StringBuilder s=new StringBuilder();
		for(int i=0;i<list.size();i++)
		{
			s.append(list.get(i));
			s.append("#");
		}
		if(s.length()>0)
		{
		 if(s.toString().endsWith("#"))
			 s.deleteCharAt(s.length()-1);
		}
		 PrintWriter out=response.getWriter();
		 out.print(s.toString());
		 out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
