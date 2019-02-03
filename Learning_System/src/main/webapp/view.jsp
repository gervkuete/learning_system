<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>View exams</title>
		<link rel="stylesheet" href="styles.css">
		
		<style>
			table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
            margin: auto;
            }
		</style>
		
		<script type="text/javascript">
			var table = document.getElementById(""), index;
			for (var i = 0, i<table.rows.length; i++) {
				table.rows[i].onclick = function() {
					index = this.rows;
					
					document.getElementById("title").value = this.cells[0].innerHTML;
					document.getElementById("date").value = this.cells[1].innerHTML;
				}
			}
		</script>
		
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
						<a href="exam.do">Edit exam</a> 
						<a href="exam.do">Delete exam</a> 
						<a href="exam.do">view exams</a>
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
			
				<c:if test="${fn:length(examList) > 0}">
					<div class="table-cotent">
					
						<table style="width:60%">
							<caption><strong>List of exams</strong></caption>
							<tr>
								<th><strong>Id</strong></th>
								<th><strong>Title</strong></th>
								<th><strong>Date</strong></th>
							</tr>
							<c:forEach var="exam" items="${examList}">
								<tr>
									<td><c:out value="${exam.id}"/></td>
									<td><c:out value="${exam.title}"/></td>
									<td><c:out value="${exam.date}"/></td>
								</tr>
							</c:forEach>					
						</table>
					
					</div>		
							
					<div class="edit-delete">
						<span><button onclick="edit();">Edit</button></span>
						<span><button onclick="delete();">Delete</button></span>
					</div>
				</c:if>
			</div>
		</div>
	</div>

</body>
</html>