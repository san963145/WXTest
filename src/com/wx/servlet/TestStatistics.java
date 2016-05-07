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

import com.wx.dao.LessonSidListDao;
import com.wx.dao.QuestionDao;
import com.wx.dao.StudentAnswerDao;
import com.wx.daoImpl.LessonSidListDaoImpl;
import com.wx.daoImpl.QuestionDaoImpl;
import com.wx.daoImpl.StudentAnswerDaoImpl;
import com.wx.util.TableGenerator;


/**
 * Servlet implementation class TestStatistics
 */
@WebServlet("/TestStatistics")
public class TestStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestStatistics() {
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
		Long qid=(Long) application.getAttribute("qid");
		QuestionDao questionDao=new QuestionDaoImpl();
		ArrayList<String> questionList=questionDao.getTitlesByLesson(lessonID);
		request.setAttribute("questionList", questionList);
		if(qid==null)
		{			
			request.getRequestDispatcher("pages/teacher/testStatistics.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("qid", qid);
			LessonSidListDao lessonSidListDao=new LessonSidListDaoImpl();
			StudentAnswerDao studentAnswerDao=new StudentAnswerDaoImpl();
			Integer questionSeq=(Integer) application.getAttribute("number");
			String answer=new QuestionDaoImpl().getAnswerByQid(qid);
			Integer totalNum=lessonSidListDao.getCountByLesson(lessonID);
			Integer answerNum=studentAnswerDao.getCountByQid(qid);
			Integer correctNum=studentAnswerDao.getCorrectNumByQid(qid);
			ArrayList<String> list=new ArrayList<String>();			
			list.add(questionSeq.toString());
			list.add(answer);
			list.add(totalNum.toString());
			list.add(answerNum.toString());
			list.add(correctNum.toString());
			ArrayList<ArrayList<String>> table1_content=new ArrayList<ArrayList<String>>();
			table1_content.add(list);
			String table1=TableGenerator.generateTable("基本信息",5, new String[]{"题目序号","正确答案","学生在线人数","答题人数","答案正确人数"}, table1_content);
			ArrayList<ArrayList<String>> table2_content=studentAnswerDao.getAnswerListByQid(qid);
			String table2=TableGenerator.generateTable("学生答题结果", 2, new String[]{"答题结果","人数"}, table2_content);
			
			ArrayList<ArrayList<String>> table3_content=studentAnswerDao.getNoAnswerListByQid(qid,lessonID);
			String table3=TableGenerator.generateTable("未答题学生列表", 2, new String[]{"学号","姓名"}, table3_content);

			request.setAttribute("table1", table1);
			request.setAttribute("table2", table2);
			request.setAttribute("table3", table3);
			
			request.getRequestDispatcher("pages/teacher/testStatistics.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
