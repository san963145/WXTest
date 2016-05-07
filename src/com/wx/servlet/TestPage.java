package com.wx.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class TestPage
 */
@WebServlet("/TestPage")
public class TestPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPage() {
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
		QuestionDao questionDao=new QuestionDaoImpl();
		ArrayList<String> questionList=questionDao.getTitlesByLesson(lessonID);
		request.setAttribute("questionList", questionList);
		String result=(String) session.getAttribute("result");
		if(result!=null)
		{
			if(result.equals("error"))
			{
				request.setAttribute("result", "error");
			}
			else
			{
				request.setAttribute("result", "1");
			}
			session.removeAttribute("result");
		}
		request.getRequestDispatcher("pages/teacher/test.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
