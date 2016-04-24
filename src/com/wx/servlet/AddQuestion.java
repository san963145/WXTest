package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.QuestionDao;
import com.wx.daoImpl.QuestionDaoImpl;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletContext application=(ServletContext) request.getServletContext();
		HttpSession session=request.getSession();
		String tid=(String) session.getAttribute("curUser");
		long lessonID=(long) application.getAttribute("lessonID");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String answer=request.getParameter("answer");
		QuestionDao questionDao=new QuestionDaoImpl();
		int number=questionDao.getMaxNumByLesson(lessonID)+1;
		long qid=System.currentTimeMillis();
		boolean flag=questionDao.add(qid, tid, lessonID, number, title, answer, content);
		if(flag)
		{
			session.setAttribute("result", "1");
			response.sendRedirect("TestPage");
		}
		else
		{
			session.setAttribute("result", "error");
			response.sendRedirect("TestPage");
		}
		
		
	}

}
