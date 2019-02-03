package com.gervkuete.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gervkuete.dbConnectionFactory.DatabaseFactory;
import com.gervkuete.passwordEncryption.EncryptPassword;
import com.gervkuete.uuidGenerator.UUIDGenerator;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Admin.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String errorMessage = null;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		if (name == "" || email == "" || password == ""|| confirmPassword == "") {
			errorMessage = "All fields should be filled!";
			request.setAttribute("errorMessage", errorMessage);
			return;
		}

		if (!password.equals(confirmPassword)) {
			errorMessage = "Password does not match!";
		}

		// Generate salt for the given user password.
		String salt = EncryptPassword.generateSalt(16);

		// Generate uuid code for each user
		String uniqueCode = UUIDGenerator.generateUUIDCode();

		// Hash the password and encrypt it
		String encryptedPassword = EncryptPassword.hashPassword(password, salt);

		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date registerDate = new Date(currentDate.getTime());

		int rowCount = 0;
		int rowAffected = 0;

		Connection connection = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		ResultSet rs = null;

		try {

			connection = DatabaseFactory.getConnection();
			connection.setAutoCommit(false);
			String insertUser = "INSERT INTO users (email, password, salt, role, uui_code, register_date) VALUES (?,?,?,?,?,?)";
			pstm1 = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			pstm1.setString(1, email);
			pstm1.setString(2, encryptedPassword);
			pstm1.setString(3, salt);
			pstm1.setInt(4, 22);
			pstm1.setString(5, uniqueCode);
			pstm1.setDate(6, registerDate);

			rowCount = pstm1.executeUpdate();
			// get id of the inserted row (user)
			rs = pstm1.getGeneratedKeys();
			int idUser = 0;
			if (rs.next())
				idUser = rs.getInt(1);

			if (rowCount > 0) {
				String insertAdmin = "INSERT INTO admin (idUser, admin_name) VALUES (?,?)";
				pstm2 = connection.prepareStatement(insertAdmin);
				pstm2.setInt(1, idUser);
				pstm2.setString(2, name);

				rowAffected = pstm2.executeUpdate();
				if (rowAffected > 0) {
					connection.commit();
					String message = "Admin added successfuly!!!";
					request.setAttribute("message", message);
					request.getRequestDispatcher("forAdmin").forward(request, response);
				}
			}

		} catch (SQLException se) {
			try {
				if (connection != null)
					connection.rollback();
				logger.info("Transaction failed.");
			} catch (SQLException e) {
				logger.error(se.getMessage());
			}
			logger.error(se.getMessage());
		}
	}

}
