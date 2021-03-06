package Login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import InfoWriter.LoginChecker;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginChecker loginChecker = new LoginChecker();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String command= request.getParameter("command");
		//这里是用户登录的界面，需要处理逻辑关系，考虑
		if(command.equals("login"))
		{
			//1、没有输入用户名 2、用户名错误 3、密码错误
			String username = request.getParameter("username");
			String passwd = request.getParameter("passwd");
			//如果未输入用户名
			if(username.equals("")||passwd.equals(""))
			{	
				System.out.println("未输入用户名");
				String error_msg = "请输入用户名和密码";
				String error =URLEncoder.encode(error_msg, "utf-8");
				response.setCharacterEncoding("utf-8");
				response.sendRedirect("/MyDBtest/login.jsp?error="+error);	
			}
			//对用户名进行查询
			else
			{
			   String real_passwd = loginChecker.getPasswd(username);
			   //没有此用户
			   if(real_passwd.equals(""))
			   {
					System.out.println("没有此用户");
					String error_msg = "没有此用户，请重新输入用户名";
					String error =URLEncoder.encode(error_msg, "utf-8");
					response.setCharacterEncoding("utf-8");
					response.sendRedirect("/MyDBtest/login.jsp?error="+error);	
			   }
			   
			   //查询成功  检查用户是否设置自动登录，如果是则发送cookies
			   else if(real_passwd.equals(passwd))
			   {
				   //设置session username属性表示用户已经登录
				   request.getSession().setAttribute("username", username);
				   
				   //检查用户是否选择自动登录
				   //如果选择了自动登录，则需要保存cookies
				   
				   if("on".equals(request.getParameter("autologin")))
				   {
					   int saveTime = Integer.parseInt(request.getParameter("maxage"));
					   saveTime =24*60*60*saveTime;
					   Cookie user_cookie = new Cookie("username", username);
					    Cookie passwd_cookie = new Cookie("passwd", passwd);
					    user_cookie.setMaxAge(saveTime);
					    passwd_cookie.setMaxAge(saveTime);
					    response.addCookie(user_cookie);
					    response.addCookie(passwd_cookie);
					    
				   }
				   //如果没有选择自动登录
				   else 
				   {
					  Cookie[] cookies = request.getCookies();
					  for(Cookie cookie:cookies)
					  {
						  System.out.println("删除 cookie");
						  if(cookie.getName().equals("username"))
						  {
							  cookie.setMaxAge(0);
							  response.addCookie(cookie);  //添加生成的新的cookie
						  }
						  else if(cookie.getName().equals("passwd"))
						  {
							  cookie.setMaxAge(0);
							  response.addCookie(cookie);  //添加生成的新的cookie
						  }
					  }
					  
				   }				   
				   //重定向到后台界面
				   response.sendRedirect("/MyDBtest/mypage.jsp");
			   }
			   //密码错误
			   else {
					System.out.println("密码错误");
					String error_msg = "密码错误，请重新输入密码";
					String error =URLEncoder.encode(error_msg, "utf-8");
					response.setCharacterEncoding("utf-8");
					response.sendRedirect("/MyDBtest/login.jsp?error="+error);	
			 }
		 }		
     }
		else if(command.equals("deleteCookies"))
		{
			//用户主动注销，那么不用再保存Cookies
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie:cookies)
            {
            	if(cookie.getName().equals("username"))
            	{
            		cookie.setMaxAge(0);
            		response.addCookie(cookie);  //设置失效
            	}
            	else if(cookie.getName().equals("passwd"))
            	{
            		cookie.setMaxAge(0);
            		response.addCookie(cookie); //设置失效
            	}
            }
            
            //重定向到登录界面
            request.getSession().removeAttribute("username");
            response.sendRedirect("/MyDBtest/login.jsp");
		}
  }

}
