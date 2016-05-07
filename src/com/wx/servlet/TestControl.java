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

import com.wx.dao.LessonSidListDao;
import com.wx.dao.QuestionDao;
import com.wx.dao.StudentAnswerDao;
import com.wx.daoImpl.LessonSidListDaoImpl;
import com.wx.daoImpl.QuestionDaoImpl;
import com.wx.daoImpl.StudentAnswerDaoImpl;

/**
 * Servlet implementation class TestControl
 */
@WebServlet("/TestControl")
public class TestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestControl() {
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
		String parameter=(String)request.getParameter("parameter");
		if(parameter.equals("start"))
		{
		  String mode=(String) application.getAttribute("mode");
		  if(mode!=null)
		  {
			  if(mode.equals("option2"))
			  {
				  PrintWriter out=response.getWriter();
				  out.print("error");
				  out.close();
				  return; 
			  }			  
		  }
		  QuestionDao questionDao=new QuestionDaoImpl();
		  String question=request.getParameter("question");
		  int number=Integer.parseInt(question);
		  long qid=questionDao.getQidByNum(lessonID, number);
		  String answer=questionDao.getAnswerByQid(qid);
		  String title=questionDao.getTitleByQid(qid);
		  application.setAttribute("qid", qid);
		  application.setAttribute("answer", answer);
		  application.setAttribute("title", title);
		  application.setAttribute("number", number);
		  application.setAttribute("mode", "option2");
		  PrintWriter out=response.getWriter();
		  out.print("success");
		  out.close();
		}
		else if(parameter.equals("stop"))
		{
			  application.removeAttribute("mode");
			  application.removeAttribute("title");
			  application.removeAttribute("answer");
			  PrintWriter out=response.getWriter();
			  out.print("success");
			  out.close();
		}
		else if(parameter.equals("getAnswer"))
		{
			  LessonSidListDao lessonSidListDao=new LessonSidListDaoImpl();
			  StudentAnswerDao studentAnswerDao=new StudentAnswerDaoImpl();
			  long qid=(long) application.getAttribute("qid");
			  Integer totalCount=lessonSidListDao.getCountByLesson(lessonID);
			  Integer answerCount=studentAnswerDao.getCountByQid(qid);
			  ArrayList<String> answers=studentAnswerDao.getListByQid(qid);
			  StringBuilder s=new StringBuilder();
			  s.append(totalCount.toString()+"#"+answerCount.toString());
			  s.append("-");
			  for(int i=0;i<answers.size();i++)
			  {
				  s.append(answers.get(i)+"#");
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
