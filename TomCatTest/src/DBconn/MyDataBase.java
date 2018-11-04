package DBconn;
import java.sql.*;

public class MyDataBase {
	public String getCONN_DB() {
		return CONN_DB;
	}


	public void setCONN_DB(String cONN_DB) {
		CONN_DB = cONN_DB;
	}


	public String getUSER() {
		return USER;
	}


	public void setUSER(String uSER) {
		USER = uSER;
	}


	public String getPASSWD() {
		return PASSWD;
	}


	public void setPASSWD(String pASSWD) {
		PASSWD = pASSWD;
	}


	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final String ipAddress = "jdbc:mysql://localhost:3306/";
	private String CONN_DB;
	private String USER;
	private String PASSWD;
	
	public MyDataBase(String CONN_DB,String USER,String PASSWD)
	{
		this.CONN_DB = CONN_DB;
		this.USER = USER;
		this.PASSWD = PASSWD;	
	}
	
	
	public Connection  ConnectToDataBase()
	{
		try {
			//加载mysql jdbc驱动
			 Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序 
			System.out.println("mysql驱动加载成功");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("mysql驱动加载失败");
		}
		
		//连接数据库
		
		try {
			//获得数据库连接 
			Connection connect = DriverManager.getConnection(ipAddress+CONN_DB,USER,PASSWD);
			System.out.println("mysql连接成功");
			return connect;
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据库连接错误!");
			e.printStackTrace();
			return null;
		}
	}
	
	public void test()
	{
		 try {
	          Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	          System.out.println("Success loading Mysql Driver!");
	        }
	        catch (Exception e) {
	          System.out.print("Error loading Mysql Driver!");
	          e.printStackTrace();
	        }
	        try {
	          Connection connect = DriverManager.getConnection(
	              "jdbc:mysql://localhost:3306/StudentInfo","zqhzju","zqh6623123");
	          System.out.println("Success connect Mysql server!");

	          //select code
	          Statement stmt = connect.createStatement();
	          ResultSet rs = stmt.executeQuery("select * from student_info");
	           while (rs.next()) {
	            System.out.println(rs.getString("id"));

	          }
	        }
	        catch (Exception e) {
	          System.out.print("get data error!");
	          e.printStackTrace();
	        }
	}
	
	 public static void main(String args[]) {
	        try {
	          Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
	          System.out.println("Success loading Mysql Driver!");
	        }
	        catch (Exception e) {
	          System.out.print("Error loading Mysql Driver!");
	          e.printStackTrace();
	        }
	        try {
	          Connection connect = DriverManager.getConnection(
	              "jdbc:mysql://localhost:3306/StudentInfo","zqhzju","zqh6623123");
	          System.out.println("Success connect Mysql server!");

	          //select code
	          Statement stmt = connect.createStatement();
	          ResultSet rs = stmt.executeQuery("select * from student_info");
	           while (rs.next()) {
	            System.out.println(rs.getString("id"));

	          }
	        }
	        catch (Exception e) {
	          System.out.print("get data error!");
	          e.printStackTrace();
	        }
	      }
		

}
