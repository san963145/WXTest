package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentInfoDao {


	public String checkStudentLogin(String sid,String spwd)
	{	
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from StudentInfo where SID=? and SPWDMD5=?");
			ps.setString(1, sid);
			ps.setString(2, spwd);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String userName="null";
			String openID="null";
			if(rs.next())
			{
				userName=rs.getString("SName");
				openID=rs.getString("OpenID");
			}	
			return  userName+"#"+openID;
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return "null";
	}
    public boolean checkUserId(String userId)
    {
    	Connection conn=MyConnManager.getConnection();					
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from StudentInfo where SID=?");
			ps.setString(1, userId);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			String openID="null";
			if(rs.next())
			{
				openID=rs.getString("OpenID");
			}	
			if(!openID.equals("null"))
			{
				return true;
			}
		}  catch(Exception e)
		  {
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return false;
    }
	public boolean insert(String userId,String userName,String spwd,String className,String sex,String openID)
	{
		
		Connection conn=MyConnManager.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			PreparedStatement ps=conn.prepareStatement("insert into StudentInfo values(?,?,?,?,?,?)");
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setString(3, spwd);
			ps.setString(4, openID);
			ps.setString(5, className);
			ps.setString(6, sex);
			ps.execute();
			conn.commit();
			return true;	
		
		}  catch(Exception e)
		  {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		  }		
		   finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  return false;
	}
}
