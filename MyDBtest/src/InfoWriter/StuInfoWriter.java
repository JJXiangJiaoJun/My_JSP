package InfoWriter;

import StudentModel.Student;

import ConnDB.dataBaseConnector;
import GradeModel.Grade;

import java.sql.*;
import java.util.ArrayList;

public class StuInfoWriter {
	
	private int size=4;  //每页显示的条数
	
	
	public void saveStudetInfo(Student student)
	{
		dataBaseConnector mysql_connector = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		//创造连接对象
		Connection m_connect = mysql_connector.generateConn();
		try {
			PreparedStatement command = m_connect.prepareStatement("INSERT INTO student_info (name,major,address,likes,class) VALUES (?,?,?,?,?)");
			command.setString(1, student.getSname());
			command.setString(2, student.getSmajor());
			command.setString(3, student.getSaddress());
			command.setString(4, student.getSlikes());
			command.setInt(5, student.getGrade().getGid());
			command.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库写入数据错误!");
			e.printStackTrace();
		}
		
		finally {
			try {
				m_connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库关闭错误");
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteStudent(int id)
	{
		dataBaseConnector mysql_connector = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		//创造连接对象
		Connection m_connect = mysql_connector.generateConn();
		try {
			PreparedStatement command = m_connect.prepareStatement("DELETE FROM student_info WHERE id=?");
			command.setInt(1, id);
			command.executeUpdate();
			System.out.println("删除了id="+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库删除数据错误!");
			e.printStackTrace();
		}
		
		finally {
			try {
				m_connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库关闭错误");
				e.printStackTrace();
			}
		}
	}
	
	//************修改为分页显示，queryStudent取出所有数据，并且显示第一页
	public ArrayList<Student> queryStudent(String name)
	{
		dataBaseConnector mysql_connector = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		//创造连接对象
		Connection m_connect = mysql_connector.generateConn();
		String command = "SELECT *,s.name AS sname,s.id AS sid FROM student_info s LEFT JOIN grade_info g ON s.class = g.gid WHERE s.name LIKE ?";
		
		
		//下面进行数据查询
		try {
			PreparedStatement pre = m_connect.prepareStatement(command);
			pre.setString(1, "%"+name+"%");
			ResultSet result = pre.executeQuery();
			
			//取出查询的数据
			ArrayList<Student> resultStudent =new ArrayList<Student>();
			while(result.next())
			{
				//创建新的学生对象，并将其信息导入，最后将实例插入 Arraylist
				Student stu = new Student();
			    stu.setId(result.getInt("sid"));
			    stu.setSaddress(result.getString("address"));
			    stu.setSlikes(result.getString("likes"));
			    stu.setSname(result.getString("sname"));
			    stu.setSmajor(result.getString("major"));
			    Grade grade = new Grade();
			    grade.setGid(result.getInt("gid"));
			    grade.setGname(result.getString("gname"));
			    stu.setGrade(grade);
				resultStudent.add(stu);
			}
			return resultStudent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查询学生信息错误");
			e.printStackTrace();
			return null;
		}
		
	}
	
	//************修改为分页显示，queryStudent取出所有数据，并且显示第一页
	public ArrayList<Student> queryStudent(String name,int currentPage)
	{
		dataBaseConnector mysql_connector = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		//创造连接对象
		Connection m_connect = mysql_connector.generateConn();
	   String command = "SELECT *,s.name AS sname,s.id AS sid FROM student_info s LEFT JOIN grade_info g ON s.class=g.gid WHERE s.name LIKE ? LIMIT ?,?";

	  
		//下面进行数据查询
		try {
			PreparedStatement pre = m_connect.prepareStatement(command);
			pre.setString(1, "%"+name+"%");
			pre.setInt(2,(currentPage-1)*size);
		    pre.setInt(3, size);
			ResultSet result = pre.executeQuery();
			
			//取出查询的数据
			ArrayList<Student> resultStudent =new ArrayList<Student>();
			while(result.next())
			{
				//创建新的学生对象，并将其信息导入，最后将实例插入 Arraylist
				Student stu = new Student();
			    stu.setId(result.getInt("sid"));
			    stu.setSaddress(result.getString("address"));
			    stu.setSlikes(result.getString("likes"));
			    stu.setSname(result.getString("sname"));
			    stu.setSmajor(result.getString("major"));
			    Grade grade = new Grade();
			    grade.setGid(result.getInt("gid"));
			    grade.setGname(result.getString("gname"));
			    stu.setGrade(grade);
				resultStudent.add(stu);
			}
			return resultStudent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查询分页学生信息错误");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	public int countStudent(String name)
	{
		dataBaseConnector mysql_connector = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		int total=-1;
		//创造连接对象
		Connection m_connect = mysql_connector.generateConn();
		String command = "SELECT COUNT(*) total FROM student_info WHERE name LIKE ?";
		
		
		//下面进行数据查询
		try {
			PreparedStatement pre = m_connect.prepareStatement(command);
			pre.setString(1, "%"+name+"%");
			
			ResultSet result = pre.executeQuery();
			if(result.next())
			 total = result.getInt("total");
			total = total%size==0?total/size:total/size+1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查询学生信息错误");
			e.printStackTrace();
		}
		return total;
	}
	
	public boolean modifyStudent(Student student)
	{
		dataBaseConnector mysql_connector = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		//创造连接对象
		Connection m_connect = mysql_connector.generateConn();
		String command = "UPDATE student_info  SET name=?,address=?,major=?,likes=?,class=? WHERE id=?";
		
		try {
			PreparedStatement pre =m_connect.prepareStatement(command);
			pre.setString(1, student.getSname());
			pre.setString(2, student.getSaddress());
			pre.setString(3, student.getSmajor());
			pre.setString(4, student.getSlikes());
			pre.setInt(5, student.getGrade().getGid());
			pre.setInt(6, student.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("学生信息修改失败");
			e.printStackTrace();
		}
		
		return false;
	}
}
