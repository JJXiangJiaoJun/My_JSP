package Init;

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
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String[] slikes = {"吃饭","睡觉","打豆豆","看动漫","听音乐","上网"};
	private GradeInfoWriter gradeInfoWriter = new GradeInfoWriter();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    //初始化时执行的函数
    public void init() throws ServletException{
    	ArrayList<Grade> grades = gradeInfoWriter.queryGrade("");
    	
    	//获得application对象
    	getServletContext().setAttribute("grades", grades);
    	getServletContext().setAttribute("slikes", slikes);
    }
}
