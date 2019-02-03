<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>welcome</title>
<style>
html, body {
	margin: 0;
	padding: 0;
}

.main-header {
	padding-bottom: 220px;
}

.nav li {
	display: inline;
}

.nav ul {
	margin: 0;
}

.nav a {
	display: inline-block;
	text-decoration: none;
	color: black;
	padding: .5em;
}

.nav a:hover {
	background-color: white;
}

.main-nav {
	background-color: lightblue;
	text-align: center;
	font-size: 1.1em;
	font-weight: lighter;
	border-bottom: 1px solid rgba(255, 255, 255, .3);
}

.login-logout {
	border-radius: 5px;
	width: 10%;
	height: 30px;
	margin-left: 1225px;
	text-align: center;
	padding-top: 10px;
	background-color: LightGray;
}

.login-logout a:hover {
	text-decoration: underline;
}

.login-logout a:link {
	text-decoration: none;
}

.page-header {
	margin: 0;
	font-size: 4em;
	text-align: center;
	font-weight: normal;
	color: black;
}

.page-footer {
	text-align: center;
	height: 50px;
	color: black;
	padding: 1.5em 0;
	background-color: lightblue;
}
</style>

</head>
<body>
	<div class="wrapper">
		<jsp:include page="_navigation.jsp" />
		<div class="login-logout">
			<a href="${pageContext.request.contextPath}/login.jsp">Login/</a> <a
				href="${pageContext.request.contextPath}/register.jsp">Register</a>
		</div>

		<h1 class="page-header">Examination portal</h1>

		<jsp:include page="_footer.jsp" />
	</div>

</body>
</html>