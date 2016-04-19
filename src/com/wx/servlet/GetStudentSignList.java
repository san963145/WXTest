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

/**
 * Servlet implementation class GetStudentSignList
 */
@WebServlet("/GetStudentSignList")
public class GetStudentSignList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentSignList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext application=(ServletContext) request.getServletContext();
		ArrayList<String>list=(ArrayList<String>) application.getAttribute("signList");
		String result="";
		if(list!=null)
		{	
			int n=list.size();
			if(n>0)
			{
			  for(int i=0;i<n;i++)
			  {
				result=result+list.get(i)+"#";
			  }
			  result=result.substring(0,result.length()-1);
			}
		}
		PrintWriter out=response.getWriter();
		out.print(result);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
