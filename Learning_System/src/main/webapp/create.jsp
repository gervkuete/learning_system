<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Create Exam</title>
		<link rel="stylesheet" href="styles.css">
		
		<style>
			table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
            margin: auto;
            }
		</style>
	</head>
<body>

	<div class="page">
		<div class="bar">
			<diV>
				<h3>Admin</h3>
			</diV>
			<div>
				<a href="${pageContext.request.contextPath}/login.jsp">Logout</a>
			</div>
		</div>

		<div class="wrapper">
			<div class="left-side">
				<div class="left-element">
				Dashboard
				</div>
				<div class="dropdown">
					<button class="dropbtn">Exam</button>
					<div class="dropdown-content">
						<a href="${pageContext.request.contextPath}/create.jsp">Create exam</a> 
						<a href="exam.do">Edit	exam</a> 
						<a href="exam.do">Delete exam</a> 
						<a href="exam.do">view	exams</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">Users</button>
					<div class="dropdown-content">
						<a href=#>Students</a> 
						<a href=#>Admins</a>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="create-form">
					<p style="color:green;"><c:out value="${confirmation}"/></p>
					<form action="exam.do" method="post">
						<fieldset>
							<legend>Adding exam</legend>
							<input type="hidden" name="page" value="/create.jsp" />
							<span>Title: <input type="text" name="title" required /></span><br>
							<span>Date: <input type="date" name="date" required /></span><br>
							<input type="submit" value="Add" />
						</fieldset>
					</form>
				</div>
				
				<c:if test="${fn:length(list) > 0}">
					<div class="show-exam" style="overflow-x:auto;">
						<table style="width:80%">
						<caption><strong>List of exams</strong></caption>
						<tr>
							<th><strong>Id</strong></th>
							<th><strong>Title</strong></th>
							<th><strong>Date</strong></th>
						</tr>
						<c:forEach var="exam" items="${list}">
							<tr>
								<td><c:out value="${exam.id}"/></td>
								<td><c:out value="${exam.title}"/></td>
								<td><c:out value="${exam.date}"/></td>
							</tr>
						</c:forEach>					
					</table>
					</div>
				</c:if>
				
			</div>
		</div>
	</div>


</body>
</html>