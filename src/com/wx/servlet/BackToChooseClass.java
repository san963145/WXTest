package com.wx.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.TeachClassDao;
import com.wx.dao.TeacherInfoDao;
import com.wx.daoImpl.TeachClassDaoImpl;
import com.wx.daoImpl.TeacherInfoDaoImpl;


/**
 * Servlet implementation class BackToChooseClass
 */
@WebServlet("/BackToChooseClass")
public class BackToChooseClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackToChooseClass() {
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
		String userID=request.getParameter("userID");
		Map<String, String> classMap=null;
		String result="";
		TeacherInfoDao dao=new TeacherInfoDaoImpl();
		TeachClassDao teachClassDao=new TeachClassDaoImpl();
		try {

			result=dao.getRole(userID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result.equals("1"))
		{
			classMap=teachClassDao.getClassListByTid(userID);
		}
		else if(result.equals("2"))
		{
			classMap=teachClassDao.getClassListByTutorID(userID);
		}
		request.setAttribute("classMap",classMap);
		request.getRequestDispatcher("pages/teacher/enterClass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
