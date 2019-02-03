package com.gervkuete.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gervkuete.daoImpl.StudentDAOImpl;
import com.gervkuete.dbConnectionFactory.DatabaseFactory;
import com.gervkuete.model.Exam;
import com.gervkuete.model.Question;
import com.gervkuete.model.Student;
import com.gervkuete.model.User;

public class VerifyAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerifyAnswer() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get user's answers
		List<String> answers = Arrays.asList(request.getParameterValues("questionAnswer"));
		List<Integer> userAnswers = answers.stream().map(Integer::parseInt).collect(Collectors.toList());
		

		HttpSession session = request.getSession();
		Exam exam = (Exam) session.getAttribute("exam");
		List<Question> questionsList = exam.getQuestions();
		int rightAnswers = 0;

		// Compare user's answers with the the correct answer of a question.
		for (Question question : questionsList) {
			for (int elt : userAnswers) {
				if (question.isCorrectAnswer(elt)) {
					rightAnswers++;
				}
			}
		}
		
		// Store the number of questions and right answers of the exam for the current user into the database.
		int questionCount = questionsList.size();
		int idExam = exam.getId();
		User currentUser = (User) session.getAttribute("user");
		int userId = currentUser.getId();
		StudentDAOImpl instance = new StudentDAOImpl();
		Student student = instance.findStudentByUserId(userId);
		
		
		String insertResult = "INSERT INTO student_exam (idStudent, idExam, questionCount, rightAnswers) VALUES (?,?,?,?)";
		try (Connection conn = DatabaseFactory.getConnection();
				PreparedStatement pstm = conn.prepareStatement(insertResult)) {
			
			pstm.setInt(1, student.getId());
			pstm.setInt(2, idExam);
			pstm.setInt(3, questionCount);
			pstm.setInt(4, rightAnswers);
			
			pstm.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		session.setAttribute("rightAnswers", rightAnswers);
		RequestDispatcher dispatch = request.getRequestDispatcher("resultPage.jsp");
		dispatch.forward(request, response);
		
		
	
	
	}

}
