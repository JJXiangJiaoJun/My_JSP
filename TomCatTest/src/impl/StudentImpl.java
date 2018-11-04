package impl;

import pojo.Student;
import DBconn.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentImpl {

	public void saveStudent(Student student ) {
		MyDataBase m_conn = new MyDataBase("StudentInfo", "zqhzju", "zqh6623123");
//		PreparedStatement pre = null;
//		Connection stu_conn = null;
//		//连接
//		stu_conn=m_conn.ConnectToDataBase();
//		try {
//			pre = stu_conn.prepareStatement("insert into student_info (name,address) VAlUES (?,?)");
//			pre.setString(1, student.getSname());
//			pre.setString(2, student.getSpass());
//			pre.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				stu_conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		m_conn.test();
	}
	
	
}
