package InfoWriter;
import GradeModel.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnDB.dataBaseConnector;

public class GradeInfoWriter {
	private static final String database = "StudentInfo";
	private static final String table = "grade_info";
	
	public void saveGrade(Grade grade)
	{
		dataBaseConnector m_conn = new dataBaseConnector(database, "zqhzju", "zqh6623123");
		Connection connection = m_conn.generateConn();
		String command = "INSERT INTO "+table+" (gname) VALUES (?)";
		
		try {
			PreparedStatement pre = connection.prepareStatement(command);
			pre.setString(1, grade.getGname());
			pre.executeUpdate();
			System.out.println("插入班级成功!....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入班级失败!....");
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Grade> queryGrade(String gname)
	{
		ArrayList<Grade> grades=new ArrayList<Grade>();
		dataBaseConnector m_conn = new dataBaseConnector(database, "zqhzju", "zqh6623123");
		Connection connection = m_conn.generateConn();
		String command = "SELECT * FROM "+table+" WHERE gname LIKE ?";
		
		try {
			PreparedStatement pre = connection.prepareStatement(command);
			pre.setString(1, "%"+gname+"%");
			ResultSet result = pre.executeQuery();
			while(result.next())
			{
				Grade grade = new Grade();
				grade.setGid(result.getInt("gid"));
				grade.setGname(result.getString("gname"));
				grades.add(grade);
			}
	
			System.out.println("查询班级成功!....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查询班级失败!....");
			e.printStackTrace();
		}	
		return grades;
	}
	
	public void updateGrade(Grade grade)
	{
		dataBaseConnector m_conn = new dataBaseConnector(database, "zqhzju", "zqh6623123");
		Connection connection = m_conn.generateConn();
		String command="UPDATE "+ table+" SET gname=? WHERE gid=?";
		try {
			PreparedStatement pre = connection.prepareStatement(command);
			pre.setString(1, grade.getGname());
			pre.setInt(2, grade.getGid());
			pre.executeUpdate();
			System.out.println("修改班级成功!....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("修改班级失败!....");
			e.printStackTrace();
		}	
	}
	
	public void deleteGrade(int gid)
	{
		dataBaseConnector m_conn = new dataBaseConnector(database, "zqhzju", "zqh6623123");
		Connection connection = m_conn.generateConn();
		String command = "DELETE FROM " + table + " WHERE gid=?";
		try {
			PreparedStatement pre = connection.prepareStatement(command);
			pre.setInt(1, gid);
			pre.executeUpdate();
			System.out.println("删除班级成功!....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除班级失败!....");
			e.printStackTrace();
		}	
	}
}
