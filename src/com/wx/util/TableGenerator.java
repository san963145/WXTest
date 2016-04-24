package com.wx.util;

import java.util.ArrayList;

public class TableGenerator {

	public static String generateTable(String title,int columnNum,String[] head,ArrayList<ArrayList<String>> content)
	{		
		StringBuilder s=new StringBuilder();
		s.append("<table class=\"table table-bordered\">");
		s.append("<caption class=\"text-center\">"+title+"</caption>");
		s.append("<thead><tr>");
        for(int i=0;i<columnNum;i++)
        {
        	s.append("<th>"+head[i]+"</th>");
        }
        s.append("</thead>");
        s.append("<tbody>");
        int rowNum=content.size();
        for(int i=0;i<rowNum;i++)
        {
        	s.append("<tr>");
        	ArrayList<String>row=content.get(i);
        	
        	int cNum=row.size();
        	for(int j=0;j<cNum;j++)
        	{
        	  s.append("<td>"+row.get(j)+"</td>");
        	}
        	s.append("</tr>");
        }
        s.append("</tbody>");
        s.append("</table>");
		return s.toString();
	}

}
