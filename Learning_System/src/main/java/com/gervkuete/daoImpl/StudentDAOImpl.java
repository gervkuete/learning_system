package com.gervkuete.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gervkuete.dao.StudentDAO;
import com.gervkuete.dbConnectionFactory.DatabaseFactory;
import com.gervkuete.model.Student;
import com.gervkuete.passwordEncryption.EncryptPassword;
import com.gervkuete.uuidGenerator.UUIDGenerator;

public class StudentDAOImpl implements StudentDAO {

	private static StudentDAOImpl instance = new StudentDAOImpl();

	Logger logger = Logger.getLogger(StudentDAOImpl.class);

	public static StudentDAOImpl getInstance() {
		return instance;
	}

	// Store student into database and return his uuid
	@Override
	public String addStudent(Student std) {

		// Generate salt for the given user password.
		String salt = EncryptPassword.generateSalt(16);

		// Generate uuid code for each user
		String uniqueCode = UUIDGenerator.generateUUIDCode();

		// Hash the password and encrypt it
		String encryptedPassword = EncryptPassword.hashPassword(std.getPassword(), salt);

		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date registerDate = new Date(currentDate.getTime());

		int rowCount = 0;
		int rowAffected = 0;

		PreparedStatement pstm = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		
		try {
			connection = DatabaseFactory.getConnection();
			connection.setAutoCommit(false);

			String insertUser = "INSERT INTO users (email, password, salt, role, uuid_code, register_date) VALUES (?,?,?,?,?,?)";

			ps = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, std.getEmail());
			ps.setString(2, encryptedPassword);
			ps.setString(3, salt);
			ps.setInt(4, 11);
			ps.setString(5, uniqueCode);
			ps.setDate(6, registerDate);

			rowCount = ps.executeUpdate();
			// get id of the inserted row (user)
			rs = ps.getGeneratedKeys();
			int idUser = 0;
			if (rs.next())
				idUser = rs.getInt(1);

			if (rowCount > 0) {
				String insertStudent = "INSERT INTO students (last_name, first_name, date_of_birth, sex, country, idUser) VALUES (?,?,?,?,?,?)";
				pstm = connection.prepareStatement(insertStudent);
				pstm.setString(1, std.getLastName());
				pstm.setString(2, std.getFirstName());
				pstm.setDate(3, std.getDateOfBirth());
				pstm.setString(4, std.getSex());
				pstm.setString(5, std.getCountry());
				pstm.setInt(6, idUser);

				rowAffected = pstm.executeUpdate();
			}

			if (rowAffected > 0) {
				connection.commit();
				return uniqueCode;
			}

		} catch (SQLException se) {
			if (connection != null)
				try {
					connection.rollback();
					logger.info("Transaction failed.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			logger.error(se.getMessage());

		}
		return null;
	}

	@Override
	public Student findStudentById(int id) {

		Student student = null;
		
		String getStudent = "SELECT * FROM students WHERE id=?";
		try (Connection connection = DatabaseFactory.getConnection();
				PreparedStatement ps = connection.prepareStatement(getStudent)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				student = new Student();
				student.setLastName(rs.getString("last_name"));
				student.setFirstName(rs.getString("first_name"));
				student.setDateOfBirth(rs.getDate("date_of_birth"));
				student.setSex(rs.getString("sex"));
				student.setCountry(rs.getString("country"));
			}

			return student;

		} catch (SQLException se) {
			logger.error(se.getMessage());
		}

		return null;
	}

	@Override
	public List<Student> getAllStudents() {

		List<Student> students = new LinkedList<>();
		String queryUsers = "SELECT * FROM students";

		try (Connection connection = DatabaseFactory.getConnection();
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(queryUsers)) {
	
			while (rs.next()) {
				Student student = new Student();
				student.setLastName(rs.getString("last_name"));
				student.setFirstName(rs.getString("first_name"));
				student.setDateOfBirth(rs.getDate("date_of_birth"));
				student.setSex(rs.getString("sex"));
				student.setCountry(rs.getString("country"));

				students.add(student);
			}

			return students;
		} catch (SQLException se) {
			logger.error(se.getMessage());
		}

		return null;
	}

	@Override
	public Student findStudentByUserId(int userId) {

		Student student = null;

		String getStudent = "SELECT id FROM students WHERE idUser=?";
		try (Connection connection = DatabaseFactory.getConnection();
				PreparedStatement pstm = connection.prepareStatement(getStudent);
				ResultSet rs = pstm.executeQuery()) {

			pstm.setInt(1, userId);
			if (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setLastName(rs.getString("last_name"));
				student.setFirstName(rs.getString("first_name"));
				student.setDateOfBirth(rs.getDate("date_of_birth"));
				student.setSex(rs.getString("sex"));
				student.setCountry(rs.getString("country"));
			}

			return student;

		} catch (SQLException se) {
			logger.error(se.getMessage());
		}

		return null;
	}

}
