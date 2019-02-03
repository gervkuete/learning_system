package com.gervkuete.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gervkuete.dao.UserDAO;
import com.gervkuete.dbConnectionFactory.DatabaseFactory;
import com.gervkuete.model.User;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance = new UserDAOImpl();

	Logger logger = Logger.getLogger(UserDAOImpl.class);

	public static UserDAOImpl getInstance() {
		return instance;
	}

	@Override
	public User findUserByEmail(String email) {

		User user = null;
		String getUSer = "SELECT * FROM users WHERE email=?";
		try (Connection connection = DatabaseFactory.getConnection()) {

			PreparedStatement pstm = connection.prepareStatement(getUSer);

			pstm.setString(1, email);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("idUser"));
				user.setEmail(email);
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
		} catch (SQLException se) {
			logger.error(se.getMessage());
		}

		return user;
	}

}
