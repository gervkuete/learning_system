<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>

<head>
<title>sign in</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

<div class="wrapper"></div>
	<jsp:include page="_navigation.jsp" />


	<h1 style="text-align: center;">Login</h1>
	<p style="color: red;">${errorMessage}</p>
	
	<form action="login.do" method="POST">

		<div class="form-fields">
			<label for="email"><strong>Email</strong></label> <input type="email"
				name="email" required/>
		</div>

		<div class="form-fields">
			<label for="password"><strong>Password</strong></label> <input
				type="password" name="password" required/>
		</div>

		<div class="form-fields">
			<button type="submit" class="submit-button">login</button>
		</div>
		<p>
			Not yet registered? <a href="${pageContext.request.contextPath}/register.jsp">Sign up</a>
		</p>

	</form>

	<jsp:include page="_footer.jsp" />

</body>

</html>