package com.gervkuete.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gervkuete.daoImpl.StudentDAOImpl;
import com.gervkuete.daoImpl.UserDAOImpl;
import com.gervkuete.model.Student;
import com.gervkuete.model.User;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Register.class);

	private RequestDispatcher dispatch;

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		dispatch = context.getRequestDispatcher("/register.jsp");
		logger.info("Inside Register Servlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Inside Register Servlet. Within doPost().");
		String errorMessage = null;
		String confMessage = null;

		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String birth = request.getParameter("birthday");
		String sex = request.getParameter("sex").substring(0, 1);
		String country = request.getParameter("country");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		if (lastName == null || firstName == null || birth == null || sex == null || country == null || email == null
				|| password == null || confirmPassword == null) {

			errorMessage = "All fields are required";
			request.setAttribute("errorMessage", errorMessage);
			dispatch.forward(request, response);
			return;
		}

		if (!password.equals(confirmPassword)) {
			errorMessage = "Password does not match";
			request.setAttribute("errorMessage", errorMessage);
			dispatch.forward(request, response);
			return;
		}

		logger.info("Inside Register Servlet before DAO call");
		// Check if there is a user already registered with this email.
		User user = UserDAOImpl.getInstance().findUserByEmail(email);
		if (user != null) {
			errorMessage = "There is user registered with " + email + " already!";
			request.setAttribute("errorMessage", errorMessage);
			dispatch.forward(request, response);
			return;
		}

		java.sql.Date birthday = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
		
		try {
			java.util.Date date = format.parse(birth);
			birthday = new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		Student student = new Student();
		student.setLastName(lastName);
		student.setFirstName(firstName);
		student.setDateOfBirth(birthday);
		student.setSex(sex);
		student.setCountry(country);
		student.setEmail(email);
		student.setPassword(password);

		logger.info("Inside Register Servlet after DAO call");
		// persist the created user
		String token = StudentDAOImpl.getInstance().addStudent(student);
		if (token != null) {
			confMessage = "Your are successfuly registerd.\n Please check your email to confirm your registration!";
			request.setAttribute("confMessage", confMessage);
			request.getRequestDispatcher("confirmationPage.jsp").include(request, response);
			request.setAttribute("uuid", token);
			request.setAttribute("email", email);
			request.getRequestDispatcher("mailing.do").forward(request, response);
		}

	}

}
