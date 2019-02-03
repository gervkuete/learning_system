package com.gervkuete.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gervkuete.daoImpl.ExamDAOImpl;
import com.gervkuete.model.Exam;

public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(ExamController.class);

	List<Exam> list;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		list = new LinkedList<>();
		boolean check = false;
		String confirmation = null;
		String operation = request.getParameter("page");
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		
		java.sql.Date examDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
		
		try {
			java.util.Date utilDate = format.parse(date);
			examDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		// Create exam with request parameters
		Exam exam = new Exam();
		exam.setTitle(title);
		exam.setDate(examDate);
		
		switch (operation) {
		case "/create.jsp":
			check = ExamDAOImpl.getInstance().addExam(exam);
			if (check == true) {
				List<Exam> list = ExamDAOImpl.getInstance().getAllExams();
				confirmation = "exam added successfully";
				request.setAttribute("list", list);
				request.setAttribute("confirmation", confirmation);
				request.getRequestDispatcher("create.jsp").forward(request, response);
				break;
			}

		case "/edit.jsp":
			check = ExamDAOImpl.getInstance().updateExam(exam);
			if (check == true) {
				confirmation = "exam updated successfully";
				request.setAttribute("confirmation", confirmation);
				request.getRequestDispatcher("edit.jsp").forward(request, response);
				break;
			}

		case "/delete.jsp":
			check = ExamDAOImpl.getInstance().deleteExam(exam);
			if (check == true) {
				confirmation = "exam deleted successfully";
				request.setAttribute("confirmation", confirmation);
				request.getRequestDispatcher("delete.jsp").forward(request, response);
				break;
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			list = ExamDAOImpl.getInstance().getAllExams();
			request.setAttribute("examList", list);
			request.getRequestDispatcher("view.jsp").forward(request,
					response);
		}
	

}
