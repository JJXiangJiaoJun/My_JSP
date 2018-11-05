package InfoWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnDB.dataBaseConnector;

public class LoginChecker {
	
	//获得用户的密码
	public String getPasswd(String username)
	{
		dataBaseConnector m_conn = new dataBaseConnector("StudentInfo", "zqhzju", "zqh6623123");
		Connection m_connection = m_conn.generateConn();
		String passwd = "";
		//下面进行查询
	    String command = "SELECT passwd FROM login WHERE username=?";
		try {
			PreparedStatement pre = m_connection.prepareStatement(command);
			pre.setString(1, username);
			ResultSet result = pre.executeQuery();
			if(result.next())
				passwd = result.getString("passwd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return passwd;
	}
	
}
