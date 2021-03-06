package StudentInfo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GradeModel.Grade;

import java.lang.Integer;
import StudentModel.Student;
import InfoWriter.StuInfoWriter;
import java.util.*;


/**
 * Servlet implementation class StuServlet
 */
public class StuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StuInfoWriter stuInfoWriter = new StuInfoWriter();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		///******************************获取参数*********************************///
		String scommand = request.getParameter("command");
	    if(scommand.equals("save")) {
	    String sname = request.getParameter("sname");
	    String saddress = request.getParameter("saddress");
	    String smajor = request.getParameter("smajor");
	    String[] arr_slikes = request.getParameterValues("slikes");
	    String string_gid =request.getParameter("gid");
	    int gid = Integer.parseInt(string_gid);
	    //将slike合并成一个字符串，并用,连接
	    String slikes =String.join(",", arr_slikes);
	  ///******************************获取参数*********************************///
	    System.out.println("姓名:"+sname+"  地址:"+saddress+ "主修:"+smajor);	 
	    System.out.println("学生爱好为:"+slikes);
	    System.out.println("学生班级为: "+gid);
	    System.out.println("正在尝试写入数据库...............\n");
	    
	    ///******************************构建学生对象*********************************///
	    Student student = new Student();
	    student.setSlikes(slikes);
	    student.setSname(sname);
	    student.setSaddress(saddress);
	    student.setSmajor(smajor);
	    Grade grade = new Grade();
	    grade.setGid(gid);
	    student.setGrade(grade);
	    ///******************************构建学生对象*********************************///
	    stuInfoWriter.saveStudetInfo(student);  //写入数据库
	    
	    System.out.println("写入数据库成功!...................\n");
	    response.sendRedirect("/MyDBtest/test.jsp");
	    }
	    else if(scommand.equals("delete")){
	    	String sid = request.getParameter("studentId");
	    	System.out.println("正在尝试删除数据库数据....... id="+sid);
	    	int i= Integer.parseInt(sid);
	    	StuInfoWriter deleter= new StuInfoWriter();
	    	deleter.deleteStudent(i);
	    	System.out.println("删除数据成功!!!!");
	    	
	    	//重新显示界面
	    	//下面根据学生信息进行查询
	    	StuInfoWriter queryer = new StuInfoWriter();
	    	ArrayList<Student> queryResult = queryer.queryStudent("");
	    	
	    	//查询完毕下面进行信息转发
	    	request.setAttribute("students", queryResult);
	    	//页面之间进行跳转 传回jsp页面
	    	RequestDispatcher dispach = request.getRequestDispatcher("/queryStudent.jsp");
	    	dispach.forward(request, response);
	    	//查询数据重新显示界面
	    }
	    else if(scommand.equals("query"))
	    {
	    	System.out.println("查找学生信息.......");
	    	String sname = request.getParameter("studentname");  //获取查询的学生信息名字
	    	//下面根据学生信息进行查询
	    	//获得学生总数
	    	int totalPage = stuInfoWriter.countStudent(sname);
	    	//查询总学生，设置到session中
	    	request.getSession().setAttribute("totalPage", totalPage);
	    	request.getSession().setAttribute("sname", sname);
	    	request.getSession().setAttribute("currentPage", 1);
	    	ArrayList<Student> students = stuInfoWriter.queryStudent(sname,1);
	    	
	    	//查询完毕下面进行信息转发
	    	request.setAttribute("students", students);
	    	//页面之间进行跳转 传回jsp页面
	    	RequestDispatcher dispach = request.getRequestDispatcher("/queryStudent.jsp");
	    	dispach.forward(request, response);	
	    	System.out.println("查询学生信息成功");
	    }
	    else if(scommand.equals("split"))
	    {
	    	//查询当前显示页面的学生并显示
	    	ArrayList<Student> students = stuInfoWriter.queryStudent((String)request.getSession().getAttribute("sname"),Integer.parseInt(request.getParameter("currentPage")));
	    	request.setAttribute("students", students);
	    	request.getSession().setAttribute("currentPage", request.getParameter("currentPage"));
	        request.getRequestDispatcher("/queryStudent.jsp").forward(request, response);
	    }
	    else if(scommand.equals("modify"))
	    {
	    	System.out.println("修改学生信息.........");
	    	String sid = request.getParameter("sid");
	    	System.out.println("正在修改学生 Id=" + sid);
	    	int id = Integer.parseInt(sid);
	    	
	    	//创建需要修改的学生对象
	    	Student student =new Student();
	    	student.setId(id);
	    	student.setSaddress(request.getParameter("saddress"));
	    	student.setSname(request.getParameter("sname"));
	    	student.setSmajor(request.getParameter("smajor"));
	    	student.setSlikes(String.join(",", request.getParameterValues("slikes")));
	    	Grade grade=new Grade();
	    	grade.setGid(Integer.parseInt(request.getParameter("gid")));
	    	student.setGrade(grade);
	    	stuInfoWriter.modifyStudent(student);
	    	System.out.println("学生信息:"+ request.getParameter("sname")+request.getParameter("saddress")+request.getParameter("smajor"));
	    
	    	System.out.println("修改学生信息成功!.......");
	    	 response.sendRedirect("/MyDBtest/queryStudent.jsp");
	    }
	    else System.out.println("没有此选项！");
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
