package Servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Dao.StudentInfoDao;
import Dao.TeacherInfoDao;


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
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletContext application=(ServletContext) request.getServletContext();
		String userID=request.getParameter("userID");
		String password=request.getParameter("password");
		String role=request.getParameter("role");
		String s="null";
		String userName="null";
		String openID="null";
		StudentInfoDao dao=new StudentInfoDao();
		TeacherInfoDao dao2=new TeacherInfoDao();
		try {
			if(role.equals("学生"))
			{
			 s=dao.checkStudentLogin(userID,password);
			}
			else
			{
				s=dao2.checkTeacherLogin(userID,password);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userName=s.split("#")[0];
		openID=s.split("#")[1];
		if(userName.equals("null"))
		{
			request.setAttribute("result","2");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("result","1");
			
			
			request.setAttribute("userName",userName);
			if(role.equals("学生"))
			{
				HashMap<String,String>map=(HashMap<String,String>) application.getAttribute("map");
				if(map!=null)
				{
					map.put(openID, userID);
					application.removeAttribute("map");
					application.setAttribute("map", map);
				}
				else
				{
					map=new HashMap<String,String>();
					map.put(openID, userID);
					application.setAttribute("map", map);
				}
			  request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
			}
			else
			{
			   request.getRequestDispatcher("pages/teacher/main.jsp").forward(request, response);
			}
		}		
	}
}
