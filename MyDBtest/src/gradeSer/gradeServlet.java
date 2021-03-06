package gradeSer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import GradeModel.Grade;
import InfoWriter.GradeInfoWriter;
/**
 * Servlet implementation class gradeServlet
 */
@WebServlet("/gradeServlet")
public class gradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		
		if(command.equals("save"))
		{
			GradeInfoWriter saver = new GradeInfoWriter();
			Grade grade =new Grade();
		    grade.setGname(request.getParameter("gname"));
		    saver.saveGrade(grade);
		    
		    //更新application中的信息
		    getServletContext().setAttribute("grades", saver.queryGrade(""));
		    //重定向到save
		    response.sendRedirect("/MyDBtest/saveGrade.jsp");
		}
		
		else if(command.equals("query"))
		{
			GradeInfoWriter queryer = new GradeInfoWriter();
			

			ArrayList<Grade> grades =  queryer.queryGrade(request.getParameter("gname"));
			
			request.setAttribute("grades", grades);
			//页面内部跳转
			request.getRequestDispatcher("/queryGrade.jsp").forward(request, response);			
		}
		
		else if(command.equals("update")) 
		{
			//执行更新学生信息
			GradeInfoWriter updater = new GradeInfoWriter();
			Grade grade = new Grade();
			grade.setGid(Integer.parseInt(request.getParameter("gid")));
			grade.setGname(request.getParameter("gname"));	
			updater.updateGrade(grade);		
			//及时更新applic中的班级信息
			getServletContext().setAttribute("grades", updater.queryGrade(""));
			//返回查询页面，外部跳转
			response.sendRedirect("/MyDBtest/queryGrade.jsp");
		}
		
		else if(command.equals("delete"))
		{
			GradeInfoWriter deleter = new GradeInfoWriter();
			deleter.deleteGrade(Integer.parseInt(request.getParameter("gid")));
			response.sendRedirect("/MyDBtest/queryGrade.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
