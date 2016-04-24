package com.wx.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
import com.wx.util.Test;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
		HttpSession session=request.getSession();
		Map<String, String> classMap=(Map<String, String>) session.getAttribute("classMap");
		String userID=request.getParameter("userID");
		String password=request.getParameter("password");
		String className=request.getParameter("class");
		TeachClassDao teachClassDao=new TeachClassDaoImpl();
		TeacherInfoDao teacherInfoDao=new TeacherInfoDaoImpl();		
		String classID="";
		Iterator<Entry<String,String>>it=classMap.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,String>entry=it.next();
			if(className.equals(entry.getValue()))
			{
				classID=entry.getKey();
			}
		}
		Test.log("1classID: "+classID);
		teachClassDao.setTutorID(classID, userID);
		boolean flag=teacherInfoDao.add(userID, password, 2);
		Test.log("flag: "+flag);
		if(!flag)
		{
			request.setAttribute("result", "error");
		}
		else
		{
			request.setAttribute("result", "1");
		}
		request.getRequestDispatcher("pages/teacher/config.jsp").forward(request, response);
	}

}
