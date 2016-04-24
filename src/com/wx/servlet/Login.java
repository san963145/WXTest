package com.wx.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.LessonDao;
import com.wx.dao.TeachClassDao;
import com.wx.dao.TeacherInfoDao;
import com.wx.daoImpl.LessonDaoImpl;
import com.wx.daoImpl.TeachClassDaoImpl;
import com.wx.daoImpl.TeacherInfoDaoImpl;
import com.wx.util.ClearApplicationData;
import com.wx.util.Consts;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String classID=request.getParameter("classID");		
		application.setAttribute("classID", classID);
		LessonDao lessonDao=new LessonDaoImpl();
		long maxLessonID=lessonDao.getMaxLessonID(classID);
		long curTime=System.currentTimeMillis();
		if(curTime-maxLessonID>Consts.LESSONINTERVALTIME*60*1000)
		{
			lessonDao.add(classID);
			long curLessonID=lessonDao.getMaxLessonID(classID);
			application.setAttribute("lessonID", curLessonID);
		}
		else
		{			
			application.setAttribute("lessonID", maxLessonID);
		}

		request.getRequestDispatcher("pages/teacher/main.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String userID=request.getParameter("userID");
		String password=request.getParameter("password");
		String result="null";
		TeacherInfoDao dao=new TeacherInfoDaoImpl();
		try {

			result=dao.checkTeacherLogin(userID,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.equals("null"))
		{
			request.setAttribute("result","2");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			ServletContext application=(ServletContext) request.getServletContext();
			boolean isBusy=false;
			Long lessonID=(Long) application.getAttribute("lessonID");
			if(lessonID!=null)
			{
				long curTime=System.currentTimeMillis();
				if(curTime-lessonID<Consts.LESSONINTERVALTIME*60*1000)
				{
					String curUserID=(String) application.getAttribute("curUser");
					if(curUserID!=null)
					{
						if(!curUserID.equals(userID))
						{
							isBusy=true;
						}
					}
				}
			}
			if(isBusy)
			{
				request.setAttribute("result","busy");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return ;
			}
			ClearApplicationData.clearWithoutTOpenID(application);
			TeachClassDao teachClassDao=new TeachClassDaoImpl();
			Map<String, String> classMap=null;
			if(result.equals("1"))
			{
				classMap=teachClassDao.getClassListByTid(userID);
			}
			else if(result.equals("2"))
			{
				classMap=teachClassDao.getClassListByTutorID(userID);
			}
			request.setAttribute("classMap",classMap);
			session.setAttribute("role", result);
			session.setAttribute("curUser", userID);
			application.setAttribute("curUser", userID);
			session.setMaxInactiveInterval(Consts.SESSIONTIME);
			request.getRequestDispatcher("pages/teacher/enterClass.jsp").forward(request, response);
		}		
	}
}


