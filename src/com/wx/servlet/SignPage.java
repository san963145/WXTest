package com.wx.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wx.dao.LessonAbsenceSidListDao;
import com.wx.dao.LessonSignRecordDao;
import com.wx.dao.TeachClassDao;
import com.wx.daoImpl.LessonAbsenceSidListDaoImpl;
import com.wx.daoImpl.LessonSignRecordDaoImpl;
import com.wx.daoImpl.TeachClassDaoImpl;
import com.wx.util.TableGenerator;
import com.wx.util.Test;


/**
 * Servlet implementation class SignPage
 */
@WebServlet("/SignPage")
public class SignPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignPage() {
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
		String tClassID=(String) application.getAttribute("classID");
		LessonSignRecordDao lessonSignRecordDao=new LessonSignRecordDaoImpl();
		LessonAbsenceSidListDao lessonAbsenceSidListDao=new LessonAbsenceSidListDaoImpl();
		TeachClassDao teachClassDao=new TeachClassDaoImpl();
		
		
		String result=teachClassDao.getNameAndTotalbyClass(tClassID);
		Double signNum=lessonSignRecordDao.getAvgSignNumClass(tClassID);
		ArrayList<ArrayList<String>> signStatistics1_firstTable_content=new ArrayList<ArrayList<String>>();
		int total = 1;
		String className="";
		if(signNum!=null)
		{
			String []s=result.split("#");
			ArrayList<String> list=new ArrayList<String>();
			className=s[0];
			list.add(s[0]);
			list.add(s[1]);
			total=Integer.parseInt(s[1]);
			String s1=String.format("%.1f", 100.0*signNum/(double)total)+"%";
			list.add(s1);
			Test.log(list.get(2));
			signStatistics1_firstTable_content.add(list);
		}
		String signStatistics1_firstTable=TableGenerator.generateTable("�γ�����", 3, new String[]{"�γ���","������","ƽ��������"}, signStatistics1_firstTable_content);
		
		ArrayList<ArrayList<String>> signStatistics1_secondTable_content=lessonSignRecordDao.getLessonRecordByClass(tClassID,total);
		String signStatistics1_secondTable=TableGenerator.generateTable("��ʷ���ڼ�¼", 3, new String[]{"Lesson���","��������","������"}, signStatistics1_secondTable_content);
		
		String lessonCount=lessonSignRecordDao.getLessonCount(tClassID);
		ArrayList<ArrayList<String>> signStatistics2_firstTableContent=new ArrayList<ArrayList<String>>();		
		ArrayList<String> list=new ArrayList<String>();
		list.add(className);
		list.add(lessonCount);
		signStatistics2_firstTableContent.add(list);
		String signStatistics2_firstTable=TableGenerator.generateTable("Lessonͳ��", 2, new String[]{"�γ���","Lesson��¼����"}, signStatistics2_firstTableContent);
		
		
		ArrayList<ArrayList<String>> signStatistics2_secondTableContent=lessonAbsenceSidListDao.getByClass(tClassID);
		String signStatistics2_secondTable=TableGenerator.generateTable("ѧ��ȱ��ͳ��", 3, new String[]{"ѧ��","����","ȱ�ڴ���"}, signStatistics2_secondTableContent);
		
		request.setAttribute("signStatistics1_firstTable", signStatistics1_firstTable);
		request.setAttribute("signStatistics1_secondTable", signStatistics1_secondTable);
		request.setAttribute("signStatistics2_firstTable", signStatistics2_firstTable);
		request.setAttribute("signStatistics2_secondTable", signStatistics2_secondTable);
		request.setAttribute("signStatistics1_secondTable_content", signStatistics1_secondTable_content);
		request.getRequestDispatcher("pages/teacher/sign.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
