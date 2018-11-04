package selTest.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NamedMethodGenerator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import impl.StudentImpl;
import pojo.Student;


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet/*")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private @Resource(name = "Hello") String Hello;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    
   
    
    /**
     * 
     * @return
     */
   
    
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // 设置响应内容类型
    	request.setCharacterEncoding("utf-8");
    	 String name = request.getParameter("sname");
    	 String pass = request.getParameter("spass");
    	 
    	 StudentImpl stu = new StudentImpl();
    	 stu.saveStudent(new Student(name, pass));
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.log("执行 doPost方法 ....");
        doGet(request, response);
    }
    
    
}