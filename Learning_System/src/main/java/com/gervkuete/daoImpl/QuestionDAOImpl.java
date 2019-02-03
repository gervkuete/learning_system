package com.gervkuete.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gervkuete.dao.QuestionDAO;
import com.gervkuete.dbConnectionFactory.DatabaseFactory;
import com.gervkuete.model.Question;

public class QuestionDAOImpl implements QuestionDAO {
	
	Logger logger = Logger.getLogger(QuestionDAOImpl.class);

	// find question
	@Override
	public Question findQuestionById(int id) {

		String findQuestion = "SELECT * FROM question WHERE id=?";

		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(findQuestion)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Question question = new Question();
				question.setId(id);
				question.setText(rs.getString("text"));
				question.setAnswer(rs.getInt("answerID"));
				question.setExamID(rs.getInt("idExam"));

				return question;
			}

		} catch (SQLException se) {
			logger.info("Error occured while fetching the question with id=" + id);
			logger.error(se.getMessage());
		}
		return null;
	}

	// Retrieve all questions
	@Override
	public List<Question> getAllQuestions() {

		List<Question> questions = new LinkedList<>();
		String getQuestions = "SELECT * FROM question";

		try (Connection con = DatabaseFactory.getConnection();
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(getQuestions)) {

			while (rs.next()) {
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setText(rs.getString("text"));
				question.setExamID(rs.getInt("idExam"));

				questions.add(question);
			}

			return questions;

		} catch (SQLException se) {

			System.out.println("Error occured while retrieving questions:" + se.getMessage());
		}

		return null;
	}

	// Add question
	@Override
	public boolean addQuestion(Question question) {

		int rowCount = 0;
		boolean operationSucceded = false;
		String sqlAdd = "INSERT INTO question (text, answerID, idExam) VALUES (?,?,?)";

		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlAdd)) {

			ps.setString(1, question.getText());
			ps.setInt(2, question.getAnswer());
			ps.setInt(3, question.getExamID());
			rowCount = ps.executeUpdate();

			if (rowCount > 0)
				operationSucceded = true;

		} catch (SQLException se) {

			logger.info("Cannot insert exam.");
			logger.error(se.getMessage());
		}
		return operationSucceded;
	}

	// Delete question
	@Override
	public boolean deleteQuestion(Question question) {
		
		int rowCount = 0;
		boolean operationSucceded = false;
		String sqlDelete = "DELETE FROM question WHERE id=?";
		
		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlDelete)) {
			
			ps.setInt(1, question.getId());
			rowCount = ps.executeUpdate();
			
			if (rowCount > 0)
				operationSucceded = true;
			
		} catch (SQLException se) {
			logger.info("Cannot delete question with id=" +question.getId());
			logger.error(se.getMessage());
		}
		
		return operationSucceded;
	}

	// Update question
	@Override
	public boolean updateQuestion(Question question) {
		
		int rowCount = 0;
		boolean operationSucceded = false;
		String sqlUpdate = "UPDATE question WHERE id=?";
		
		try (Connection con = DatabaseFactory.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
			
			ps.setInt(1, question.getId());
			rowCount = ps.executeUpdate();
			
			if (rowCount > 0)
				operationSucceded = true;
			
		} catch (SQLException se) {
			
			System.out.println("Cannot update question with id=" +question.getId() +" " +se.getMessage());
			logger.error(se.getMessage());
		}
		
		return operationSucceded;
	}

}
