package com.wx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wx.dao.MyConnManager;
import com.wx.dao.StudentAnswerDao;
import com.wx.dao.StudentInfoDao;

public class StudentAnswerDaoImpl implements StudentAnswerDao{

	@Override
	public boolean add(String sid, long qid,String sName, String answer, int corrected,String time) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			PreparedStatement ps=conn.prepareStatement("insert into StudentAnswer values(?,?,?,?,?,?)");
			ps.setString(1, sid);
			ps.setLong(2, qid);
			ps.setString(3, sName);
			ps.setString(4, answer);
			ps.setInt(5, corrected);	
			ps.setString(6, time);
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

	

	@Override
	public boolean checkSidByQid(long qid, String sid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from StudentAnswer where QID=? and SID=?");
			ps.setLong(1, qid);
			ps.setString(2, sid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			if(rs.next())
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



	@Override
	public void updateAnswer(long qid, String sid, String answer,int corrected) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			PreparedStatement ps=conn.prepareStatement("update StudentAnswer set ANSWER=?, CORRECTED=? where QID=? and SID=?");
			ps.setString(1, answer);
			ps.setInt(2, corrected);
			ps.setLong(3, qid);
			ps.setString(4, sid);	
			ps.execute();
			conn.commit();
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
	}



	@Override
	public int getCountByQid(long qid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select count(*) Count from StudentAnswer where QID=?");
			ps.setLong(1, qid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			Integer result=0;
			if(rs.next())
			{
				result=rs.getInt("Count");
			}	
			return  result;
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
		  return 0;
	}



	@Override
	public ArrayList<String> getListByQid(long qid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from StudentAnswer where QID=? order by TIME asc");
			ps.setLong(1, qid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<String> result=new ArrayList<String>();
			while(rs.next())
			{
				String sName=rs.getString("SNAME");
				String answer=rs.getString("ANSWER");
				result.add(sName+":"+answer);
			}	
			return  result;
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
		  return new ArrayList<String>();
	}



	@Override
	public int getCorrectNumByQid(long qid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select count(*) Count from StudentAnswer where QID=? and CORRECTED=1");
			ps.setLong(1, qid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			Integer result=0;
			if(rs.next())
			{
				result=rs.getInt("Count");
			}	
			return  result;
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
		  return 0;
	}



	@Override
	public ArrayList<ArrayList<String>> getAnswerListByQid(long qid) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select ANSWER, count(*) Count from StudentAnswer where QID=? group by ANSWER order by Count desc");
			ps.setLong(1, qid);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
			while(rs.next())
			{
				ArrayList<String> list=new ArrayList<String>();
				String answer=rs.getString("ANSWER");
				Integer c=rs.getInt("Count");
				list.add(answer);
				list.add(c.toString());
				result.add(list);
			}	
			return  result;
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
		  return new ArrayList<ArrayList<String>>();
	}



	@Override
	public ArrayList<ArrayList<String>> getNoAnswerListByQid(long qid,long lessonID) {
		// TODO Auto-generated method stub
		Connection conn=MyConnManager.getConnection(); 	
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from LessonSidList where LESSONID=?");
			ps.setLong(1, lessonID);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
			StudentInfoDao dao=new StudentInfoDaoImpl();
			while(rs.next())
			{
				ArrayList<String> list=new ArrayList<String>();
				String sid=rs.getString("SID");
				if(this.checkSidByQid(qid, sid))
				{
					continue;
				}
				else
				{
					String sName=dao.getNameById(sid);
					list.add(sid);
					list.add(sName);					
					result.add(list);
				}				
			}	
			return  result;
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
		  return new ArrayList<ArrayList<String>>();
	}

}
