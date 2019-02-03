<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>Registration</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
	<jsp:include page="_navigation.jsp"></jsp:include>
    <h1 style="text-align: center;">Register</h1>
    
    <p style="color:red;"><c:out value="${errorMessage }"/></p>
	<form action="register.do" method="POST">

		<div class="form-fields">
			<label for="lastName"><strong>LastName</strong></label> <input
				type="text" name="lastName" required autofocus>
		</div>

		<div class="form-fields">
			<label for="firstName"><strong>FirstName</strong></label> <input
				type="text" name="firstName" required>
		</div>

		<div class="form-fields">
			<label for="birthday"><strong>Date of Birth</strong></label> <input
				type="date" name="birthday" required>
		</div>

		<div>
			<label for="sex"><strong>Sex</strong></label>
			<div class="radio-button">
				<input type="radio" name="sex" value="M"><strong>Male</strong> <input
					   type="radio" name="sex" value="F"><strong>Female</strong>
			</div>

		</div>

		<div class="form-fields">
			<label for="country"><strong>Country</strong></label> <input
				type="text" name="country" required>
		</div>

		<div class="form-fields">
			<label for="email"><strong>Email</strong></label> <input type="email"
				name="email" required>
		</div>

		<div class="form-fields">
			<label for="password"><strong>Password</strong></label> <input
				type="password" name="password" required>
		</div>

		<div class="form-fields">
			<label for="confirmpassword"><strong>Confirm
					Password</strong></label> <input type="password" name="confirmPassword" required>
		</div>

		<div class="form-fields">
			<button type="submit" class="submit-button">Register</button>
		</div>
		<p>
			Already registered? <a href="${pageContext.request.contextPath}/login.jsp">Sign in</a>
		</p>
		
	</form>
	<p><c:out value="${confMessage}"/></p>

  <jsp:include page="_footer.jsp"/>
</body>

</html>