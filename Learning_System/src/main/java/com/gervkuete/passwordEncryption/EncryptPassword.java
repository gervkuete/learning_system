package com.gervkuete.passwordEncryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;

import com.gervkuete.dbConnectionFactory.DatabaseFactory;

public class EncryptPassword {

	public static final int ITERATIONS_COUNT = 65336;
	public static final int KEY_LENGTH = 128;

	private static Logger logger = Logger.getLogger(EncryptPassword.class);

	// generate a random sequence of symbols(salt) for a given length
	public static String generateSalt(int length) {

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[length];
		random.nextBytes(salt);
		String mySalt = Base64.getEncoder().encodeToString(salt);

		return mySalt;
	}

	// Hash password and encrypt it
	public static String hashPassword(String password, String salt) {

		char[] psw = password.toCharArray();
		byte[] saltBytes = salt.getBytes();
		KeySpec spec = new PBEKeySpec(psw, saltBytes, ITERATIONS_COUNT, KEY_LENGTH);

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hashedPassword = factory.generateSecret(spec).getEncoded();
			String encryptedPassword = Base64.getEncoder().encodeToString(hashedPassword);

			return encryptedPassword;

		} catch (NoSuchAlgorithmException e) {

			logger.info("Algorithm not found for hashing");
			logger.error(e.getMessage());
		} catch (InvalidKeySpecException e) {

			logger.info("Error occured while converting the key");
			logger.error(e.getMessage());
		}

		return null;
	}

	// check password for using login
	public static boolean verifyPasswordMatching(String enteredPassword, String storedPassword, String salt) {

		String newPassword = hashPassword(enteredPassword, salt);

		return newPassword.equals(storedPassword);
	}

	// Get password salt
	public static String getPasswordSalt(String email) {

		PreparedStatement pstm = null;
		ResultSet rs = null;

		try (Connection connection = DatabaseFactory.getConnection()) {

			String sqlQuery = "SELECT salt FROM users WHERE email=?";
			pstm = connection.prepareStatement(sqlQuery);
			pstm.setString(1, email);
			rs = pstm.executeQuery();

			if (rs.next()) {
				return rs.getString("salt");
			}

		} catch (SQLException se) {
			logger.error(se.getMessage());
		}

		return null;
	}

}
