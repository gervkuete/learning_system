package com.gervkuete.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class EmailConfirmation extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SENDER = "lepetitgervais1@gmail.com";
	private static final String PASSWORD = "lareussite2017";
	
	private Logger logger = Logger.getLogger(EmailConfirmation.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Inside Email Confirmation Servlet.");
		// Get request attributes
		String recipient = (String) request.getAttribute("email");
		String uuidCode = (String) request.getAttribute("uuid");
		
		// Customize message for email
		String subject = "Registration Confirmation";
		String msg = "Thank you for registering \n. Please click on the link below to activate your account.";
		String contextPath = request.getContextPath();	
		String confirmationLink = "http://localhost:8080" + contextPath + "/activation.do?uuid=" +uuidCode +"&email=" +recipient;
		
		try {
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(SENDER, PASSWORD);
						}
					});
			
			/*initContext = new InitialContext();
			Session session = (Session) initContext.lookup(JNDI_SESSION_RESOURCE);
			*/
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText(msg + confirmationLink);
			Transport.send(message);
			
			
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
		
	}

}
