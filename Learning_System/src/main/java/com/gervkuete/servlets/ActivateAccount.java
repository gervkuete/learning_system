package com.gervkuete.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gervkuete.dbConnectionFactory.DatabaseFactory;

public class ActivateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(ActivateAccount.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = (String) request.getAttribute("email");
		String token = (String) request.getAttribute("uuid");
		String storedUUID = null;

		PreparedStatement ps = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			
			String getUUID = "SELECT uuid_code FROM users WHERE email=?";

			conn = DatabaseFactory.getConnection();
			ps = conn.prepareStatement(getUUID);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				storedUUID = rs.getString("uuid_code");
			}

			if (storedUUID != null && storedUUID.equals(token)) {

				String activeAccount = "UPDATE users SET isVerified =? WHERE email=? AND uuid_code=?";

				pstm = conn.prepareStatement(activeAccount);
				pstm.setBoolean(1, true);
				pstm.setString(2, email);
				pstm.setString(3, token);

				int rowCount = pstm.executeUpdate();
				if (rowCount == 1) {
					request.getRequestDispatcher("WEB_INF/jsp/confirmationPage.jsp").forward(request, response);
				}

			}

		} catch (SQLException se) {

			logger.error(se.getMessage());
		} finally {

			try {

				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				logger.info("Error occured while closing resources.");
				logger.error(e.getMessage());
			}

		}

	}

}
