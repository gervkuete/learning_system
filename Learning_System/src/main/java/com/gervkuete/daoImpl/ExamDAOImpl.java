package com.gervkuete.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gervkuete.dao.ExamDAO;
import com.gervkuete.dbConnectionFactory.DatabaseFactory;
import com.gervkuete.model.Exam;

public class ExamDAOImpl implements ExamDAO {
	
	private static ExamDAOImpl instance = new ExamDAOImpl();
	
	Logger logger = Logger.getLogger(ExamDAOImpl.class);
	
	public static ExamDAOImpl getInstance() {
		return instance;
	}

	@Override
	public Exam findExamById(int id) {

		String getExam = "SELECT * FROM exams WHERE idExam=?";

		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(getExam)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			Exam exam;
			if (rs.next()) {
				exam = new Exam();
				exam.setId(id);
				exam.setTitle(rs.getString("title"));
				exam.setDate(rs.getDate("exam_date"));
			}

		} catch (SQLException se) {
			logger.info("Error occured while getting exam with idExam=" + id + " from the database");
			logger.error(se.getMessage());
		}

		return null;
	}

	@Override
	public List<Exam> getAllExams() {

		// retrieve all exams in the database
		List<Exam> exams = new LinkedList<>();
		String retrieveExams = "SELECT * FROM exams";

		try (Connection con = DatabaseFactory.getConnection();
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(retrieveExams);) {

			Exam exam;
			while (rs.next()) {
				exam = new Exam();
				exam.setId(rs.getInt("idExam"));
				exam.setTitle(rs.getString("title"));
				exam.setDate(rs.getDate("exam_date"));

				exams.add(exam);
			}

			return exams;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean addExam(Exam exam) {

		boolean operationValidation = false;
		int rowCount = 0;
		String sqlAdd = "INSERT INTO exams (title, exam_date) VALUES (?,?)";

		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlAdd)) {

			ps.setString(1, exam.getTitle());
			ps.setDate(2, exam.getDate());

			rowCount = ps.executeUpdate();
			if (rowCount > 0)
				operationValidation = true;

		} catch (SQLException se) {
			logger.info("Error occured while inserting data into the database.");
			logger.error(se.getMessage());
		}

		return operationValidation;
	}

	@Override
	public boolean updateExam(Exam exam) {

		boolean operationValidation = false;
		int rowCount = 0;
		String sqlUpdate = "UPDATE exams SET title=?, exam_date=? WHERE idExam=?";

		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlUpdate)) {

			ps.setString(1, exam.getTitle());
			ps.setDate(2, exam.getDate());
			ps.setInt(3, exam.getId());

			rowCount = ps.executeUpdate();
			if (rowCount > 0)
				operationValidation = true;

		} catch (SQLException se) {
			logger.info("Cannot update exam with idExam=" + exam.getId() );
			logger.error(se.getMessage());
		}
		return operationValidation;

	}

	@Override
	public boolean deleteExam(Exam exam) {

		boolean operationValidation = false;
		int rowCount = 0;
		String sqlDelete = "DELETE FROM exams WHERE idExam=?";

		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlDelete)) {

			ps.setInt(1, exam.getId());

			rowCount = ps.executeUpdate();
			if (rowCount > 0)
				operationValidation = true;

		} catch (SQLException se) {
			System.out.println("Cannot delete exam with idExam=" + exam.getId() + ": " + se.getMessage());
		}
		return operationValidation;

	}

	@Override
	public Exam findExamByTitle(String title) {

		Exam exam;
		ResultSet rs = null;
		String queryExam = "SELECT * FROM exams WHERE title=?";
		try (Connection conn = DatabaseFactory.getConnection();
				PreparedStatement pstm = conn.prepareStatement(queryExam)) {

			pstm.setString(1, title);
			rs = pstm.executeQuery();

			if (rs.next()) {
				exam = new Exam();
				exam.setId(rs.getInt("idExam"));
				exam.setTitle(rs.getString("title"));
				exam.setDate(rs.getDate("exam_date"));
			}

		} catch (SQLException se) {
			logger.error(se.getMessage());
		}

		return null;
	}

	@Override
	public int getExamId(String title) {

		PreparedStatement pstm = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = DatabaseFactory.getConnection();

			String getId = "SELECT idExam FROM exams WHERE title=?";
			pstm = conn.prepareStatement(getId);
			pstm.setString(1, title);
			rs = pstm.executeQuery();

			if (rs.next()) {
				return rs.getInt("idExam");
			}

		} catch (SQLException se) {
			logger.error(se.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return 0;
	}

}
