package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.dao.LessonSignRecordDao;
import com.wx.daoImpl.LessonAbsenceSidListDaoImpl;
import com.wx.daoImpl.LessonSignRecordDaoImpl;
import com.wx.daoImpl.StudentCheckinDaoImpl;
import com.wx.util.Consts;
import com.wx.util.Test;

/**
 * Servlet implementation class SetRandNum
 */
@WebServlet("/SetRandNum")
public class SetRandNum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRandNum() {
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
		ServletContext application=(ServletContext) request.getServletContext();
        long lessonID=(long) application.getAttribute("lessonID");
		String randNum0=(String) application.getAttribute("randNum");
		String randNum=(String)request.getParameter("randNum");
		String reSign=(String)request.getParameter("reSign");
		PrintWriter out=response.getWriter();

			if(randNum0!=null)
		   {
			out.print("error");
			out.close();
		   }
		    else
		   {
		    	
		    LessonSignRecordDao dao=new LessonSignRecordDaoImpl();
		    if(dao.checkByLesson(lessonID))
		    {
		    	Test.log("rep");
		    	if(reSign==null)
		    	{
		    	   out.print("signed");
				   out.close();
				   return;
		    	}
		    }
		    new StudentCheckinDaoImpl().delete(lessonID);
		    dao.delete(lessonID);
		    new LessonAbsenceSidListDaoImpl().delete(lessonID);
			application.setAttribute("randNum", randNum);
			long t=System.currentTimeMillis();
			application.setAttribute("initSignTime", t);	
			application.setAttribute("mode", "option1");		
			out.print(Consts.SIGNTIMELIMIT);
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
