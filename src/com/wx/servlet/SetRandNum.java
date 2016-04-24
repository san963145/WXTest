package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.util.Consts;

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

		String randNum0=(String) application.getAttribute("randNum");
		String randNum=(String)request.getParameter("randNum");
		PrintWriter out=response.getWriter();

			if(randNum0!=null)
		   {
			out.print("error");
			out.close();
		   }
		    else
		   {
			application.setAttribute("randNum", randNum);
			long t=System.currentTimeMillis();
			application.setAttribute("initSignTime", t);	
			application.setAttribute("mode", "option1");
			application.removeAttribute("stopSign");
			application.removeAttribute("endSign");
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
