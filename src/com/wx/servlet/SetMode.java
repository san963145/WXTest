package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetMode
 */
@WebServlet("/SetMode")
public class SetMode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetMode() {
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
		String mode=(String)request.getParameter("mode");
		PrintWriter out=response.getWriter();
		if(mode.equals("empty"))
		{
			String curMode=(String) application.getAttribute("mode");
			if(curMode==null)
			{
				curMode="4";
			}
			out.print(curMode);
			out.close();
		}
		else if(mode.equals("clear"))
		{
			application.removeAttribute("mode");			
			out.print("4");
			out.close();			
		}
		else
		{
			application.removeAttribute("mode");
			application.setAttribute("mode", mode);
			out.print(mode);
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
