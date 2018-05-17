package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter{
//	private ServletContext ctx;
	public static final String AUTHENTICATION_HEADER = "Authorization";
	
	public void init(FilterConfig cfg) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		String authorise = (String)session.getAttribute("user");
		if ("login".equals(authorise) || "authorisierModifierBD".equals(authorise))
		{
			chain.doFilter(request, response);
		}
		else
		{
//			httpResponse.sendRedirect(ctx.getContextPath() + "/login.jsp");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}
