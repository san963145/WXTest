package com.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.LessonAbsenceSidListDao;
import com.wx.dao.StudentCheckinDao;
import com.wx.dao.StudentInfoDao;
import com.wx.daoImpl.LessonAbsenceSidListDaoImpl;
import com.wx.daoImpl.LessonSignRecordDaoImpl;
import com.wx.daoImpl.StudentCheckinDaoImpl;
import com.wx.daoImpl.StudentInfoDaoImpl;

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
		String sign=request.getParameter("sign");
		if(sign.equals("1"))
		{
			Map<String,String>map=(Map<String,String>) application.getAttribute("signMap");
			String result="";
			if(map!=null)
			{	
				Iterator<Entry<String,String>>it=map.entrySet().iterator();
				while(it.hasNext())
				{
					String value=it.next().getValue();
					result=result+value+"#";
				}
				result=result.substring(0,result.length()-1);				
			}
			PrintWriter out=response.getWriter();
			out.print(result);
			out.close();
		}
		else if(sign.equals("2"))
		{
			StudentCheckinDao studentCheckinDao=new StudentCheckinDaoImpl();
			long lessonID=(long) application.getAttribute("lessonID");
			String tClassID=(String) application.getAttribute("classID");
			Map<String,String>map=(Map<String,String>) application.getAttribute("signMap");
			ArrayList<String>list=(ArrayList<String>) studentCheckinDao.getAbsenceListByLesson(lessonID);
			ArrayList<String>list2=new ArrayList<String>();
			ArrayList<String>list3=new ArrayList<String>();
			String signResult="";
			String unsignResult="";
			if(list!=null)
			{
				if(map==null)
					list2=list;
				else
				{
			        for(int i=0;i<list.size();i++)
			        {
				       String sid=list.get(i);
				       if(map.containsKey(sid))
				       {
					     list3.add(map.get(sid));
				       }
				       else
				       {
					     list2.add(sid);
				       }
			        }
				}
			  StudentInfoDao studentInfoDao=new StudentInfoDaoImpl();
			  for(int i=0;i<list3.size();i++)
			  {
				String sName=list3.get(i);
				signResult+=(sName+"#");
			  }
			  if(signResult.length()>0)
				  signResult=signResult.substring(0,signResult.length()-1);
			  LessonAbsenceSidListDao dao=new LessonAbsenceSidListDaoImpl();
			  for(int i=0;i<list2.size();i++)
			  {
				String sid=list2.get(i);	
				dao.add(lessonID, tClassID, sid);
				String sName=studentInfoDao.getNameById(sid);
				unsignResult+=(sName+"#");
			  }
			  if(unsignResult.length()>0)
				  unsignResult=unsignResult.substring(0,unsignResult.length()-1);
			  int signCount=list3.size();
			  int unsignCount=list2.size();			  
			  new LessonSignRecordDaoImpl().add(lessonID, tClassID, signCount, unsignCount);
			}			
			PrintWriter out=response.getWriter();
			out.print(signResult+"*"+unsignResult);
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
