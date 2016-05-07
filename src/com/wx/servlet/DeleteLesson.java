package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.LessonDao;
import com.wx.daoImpl.LessonAbsenceSidListDaoImpl;
import com.wx.daoImpl.LessonDaoImpl;
import com.wx.daoImpl.StudentCheckinDaoImpl;

/**
 * Servlet implementation class DeleteLesson
 */
@WebServlet("/DeleteLesson")
public class DeleteLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLesson() {
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
		LessonDao lessonDao=new LessonDaoImpl();
		lessonDao.delete(lessonID);
		new StudentCheckinDaoImpl().delete(lessonID);
		new LessonAbsenceSidListDaoImpl().delete(lessonID);
		request.getRequestDispatcher("Logout").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
