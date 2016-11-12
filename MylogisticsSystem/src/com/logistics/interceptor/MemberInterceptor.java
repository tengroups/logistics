package com.logistics.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.model.UserInfo;

public class MemberInterceptor implements HandlerInterceptor
{
	//定义静态的session常量
    public final static String SESSION_MEMBER="UserSession";
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	//拦截mvc.xml配置的/Admin/**路径的请求
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception
	{
		// TODO Auto-generated method stub
		//请求路径
		String contextPath=request.getContextPath();
		String url=request.getServletPath().toString();
		HttpSession session=request.getSession();
		UserInfo user=(UserInfo) session.getAttribute(SESSION_MEMBER);
		//判断session是否为空来定向不同页面（也可以将用户信息存入session，
		//然后取用户的权限信息，根据权限判断跳转指定页面）
		if(user==null)
		{
			//被拦截，重定向到login页面
			response.sendRedirect(contextPath+"/login?redirectURL="+URLEncoder.encode(url));
			return false;
		}
		
		return true;
	}

}
