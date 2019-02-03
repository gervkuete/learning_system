package com.gervkuete.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.gervkuete.daoImpl.UserDAOImpl;
import com.gervkuete.model.User;
import com.gervkuete.passwordEncryption.EncryptPassword;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatch;
	private Logger logger = Logger.getLogger(Login.class);

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		dispatch = context.getRequestDispatcher("/login.jsp");
		logger.info("Inside Login Servlet");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errorMessage = null;
		User user = null;

		// get request parameters and check if there is an user with these credentials
		// in the database
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || email == "" || password == null || password == "") {
			errorMessage = "email and password required";
			request.setAttribute("errorMessage", errorMessage);
			dispatch.forward(request, response);
			return;
		}

		user = UserDAOImpl.getInstance().findUserByEmail(email);

		if (user != null) {
			String storedPassword = user.getPassword();
			String salt = EncryptPassword.getPasswordSalt(email);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			if (EncryptPassword.verifyPasswordMatching(password, storedPassword, salt)) {
				if (user.getRole() == 11) {

					response.sendRedirect("examPage.jsp");
				} else {

					response.sendRedirect("adminPanel.jsp");
				}
			}
			
		} else {

			errorMessage = "email or password incorrect";
			request.setAttribute("errorMessage", errorMessage);
			dispatch.forward(request, response);
			return;
		}

	}

}
