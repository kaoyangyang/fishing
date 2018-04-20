package com.sdtz.adlet.filter;

/**
 * Created  on 18-4-2 上午11:16.
 * 作者: LiuLiHao
 * 说明:
 */

import com.sdtz.adlet.entity.Manager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		//String acount=req.getParameter("acount");
		//获取请求路径
		String path = req.getContextPath();
		//获取session中作为判断的字段
		Manager user = (Manager)session.getAttribute("user");

		//如不包含，那么就要判断 session中否有标志，如果没有标志，那么不让他看，让他去登录，反之执行操作！
		if (user == null ) {
			System.out.println("user==null");
			resp.setHeader("SESSIONSTATUS", "TIMEOUT");
			resp.setHeader("CONTEXTPATH", "/login.do");
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			//判断是否为ajax请求，默认不是
			boolean isAjaxRequest = false;
			if((req.getHeader("x-requested-with"))!=null&&
				!"".equals(req.getHeader("x-requested-with"))
				&& req.getHeader("x-requested-with").equals("XMLHttpRequest")){
				isAjaxRequest = true;
			}
			//若不是ajax直接返回登录页面，否则根据SC_FORBIDDEN：403提示登录
			if(!isAjaxRequest){
				resp.sendRedirect(path+"/login.do");
			}
			return;
		} else {
			System.out.println("user!=null");
			filterChain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("初始化filter");
	}

}
