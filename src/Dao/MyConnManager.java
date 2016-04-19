package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnManager {
	
	private static final String driverClass="com.mysql.jdbc.Driver";
	private static final String jdbcURL="jdbc:mysql://vxhsotuwhwmd.rds.sae.sina.com.cn:10786/student_wx_test1?useUnicode=true&characterEncoding=UTF-8";
	private static final String name="javatest";
	private static final String pwd="javatest2016";
	
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(jdbcURL, name, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
