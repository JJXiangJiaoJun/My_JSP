package ConnDB;

import java.sql.*;


public class dataBaseConnector {
	
	private String database;
	private String user;
	private String passwd;
	
	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final String conn_url = "jdbc:mysql://localhost:3306/";
	private static final String coding = "?useUnicode=true&characterEncoding=utf8";
	public dataBaseConnector(String database,String user,String passwd)
	{
		this.database=database;
		this.user =user;
		this.passwd = passwd;
	}
	
	public Connection generateConn() {
		//加载驱动
		try {
			Class.forName(driverClass);
			System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("加载mysql驱动失败");
			e.printStackTrace();
		}
		
		//连接数据库
		try {
			Connection  m_Connection = DriverManager.getConnection(conn_url+database+coding, user, passwd);
			
			System.out.println("数据库连接成功");
			return m_Connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接是失败");
			e.printStackTrace();
			return null;
		}
	}
	
	
}
