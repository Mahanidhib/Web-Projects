package com.bridgelabz.registration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AunthenticationFilter implements Filter {

	private ServletContext context;

	public AunthenticationFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = fConfig.getServletContext();
		((ServletContext) this.context).log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		if (session == null || session.getAttribute("Name") == null) { // checking whether the session exists
			this.context.log("Unauthorized access request");
			res.sendRedirect(req.getContextPath() + "/Login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}
}