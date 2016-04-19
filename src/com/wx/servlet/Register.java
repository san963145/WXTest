package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.dao.StudentInfoDao;
import com.wx.daoImpl.StudentInfoDaoImpl;



/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String className=request.getParameter("className");
		String openId=request.getParameter("openID");
		String sex=request.getParameter("sex");
		StudentInfoDao dao=new StudentInfoDaoImpl();
		if(dao.checkUserId(userId))
		{
			request.setAttribute("result","2");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		else
		{
			boolean flag=dao.insert(userId, userName, password, className, sex, openId);
			if(flag)
			{
			  request.setAttribute("result","1");
			}
			else
			{
				request.setAttribute("result","3");
			}
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

}
