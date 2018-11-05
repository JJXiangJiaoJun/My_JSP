package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.org.apache.regexp.internal.REDebugCompiler;

import InfoWriter.LoginChecker;
import sun.awt.RepaintArea;

/**
 * Servlet Filter implementation class LoginFilter
 */

public class LoginFilter implements Filter {

   private LoginChecker loginChecker = new LoginChecker();
	/**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		//访问 mypage 页面前需要经过此过滤器
		//System.out.println("进入到后台登录过滤器");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		

		//这里说明用户未登录，那么获取cookies并验证是否能登录
		if(req.getSession().getAttribute("username")==null )
		{
			
			System.out.println("进入到后台登录过滤器");
			Cookie[] cookies = req.getCookies();
			String username=null;
			String passwd=null;
			if(cookies!=null)
			{
				for(Cookie cookie:cookies)
				{
					if(cookie.getName().equals("username"))
					{
						username = cookie.getValue();
					}
					else if(cookie.getName().equals("passwd"))
					{
						passwd =cookie.getValue();
					}
				}
			}
			if(username!=null&&passwd!=null)
			{
				//匹配数据中的用户名以及密码
				//匹配成功则设置登录，否则退出登录
				if(loginChecker.getPasswd(username).equals(passwd))
                      req.getSession().setAttribute("username", username);
			}
			
		}
		
		
		
		// pass the request along the filter chain
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
