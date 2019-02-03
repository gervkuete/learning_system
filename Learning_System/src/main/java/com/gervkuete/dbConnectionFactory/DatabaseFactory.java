package com.gervkuete.dbConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DatabaseFactory {

	private static Logger logger = Logger.getLogger(DatabaseFactory.class);
	private static DataSource ds = null;

	static {
		logger.info("Within Database factory");
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/DBConnection");
		} catch (NamingException e) {
			
			logger.error(e.getMessage());
		}
	}

	public static Connection getConnection() {

		Connection connection = null;
		try {
			
			 connection = ds.getConnection();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return connection;
	}

}
