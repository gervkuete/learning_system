package com.gervkuete.appFilter;

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

import com.gervkuete.model.User;

public class SecurityFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// get the path of the request's url that call the servlet
		String reqURL = req.getServletPath();

		// Access to login functionality
		if (reqURL.equals("/login.do")) {

			// pass the request along the filter chain
			chain.doFilter(req, resp);
			return;
		}
		// Access to register functionality
		if (reqURL.equals("/register.do")) {

			chain.doFilter(req, resp);
			return;
		}
		if (reqURL.equals("/exam.do")) {
			chain.doFilter(req, resp);
			return;
		}

        // All other functionality requires authentication
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			chain.doFilter(req, resp);
		} else {
			resp.sendRedirect("login.jsp");
		}

	}

}
