<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add admin</title>

<style type="text/css">
.html, body {
	padding: 150px; 
	text-align: center;
	margin: 0 auto;
}

.wrapper {
border: solid 1px;
}

.paddingBtm {
	padding-bottom: 12px;
}
</style>
</head>
<body>

<div class="wrapper">
<p style="color: red;">${errorMessage}</p>

	<form id="loginFormId" name="loginForm" method="post" action="Admin">

		<div id="passwordDiv" class="paddingBtm">

			<span id="pass">Name: </span><input id="passInput" type="text"
				name="name"  required/>

		</div>


		<div id="usernameDiv" class="paddingBtm">

			<span id="user">Email: </span><input id="userInput" type="email"
				name="email" required/>

		</div>

		<div id="passwordDiv" class="paddingBtm">

			<span id="pass">Password: </span><input id="passInput"
				type="password" name="password" required/>

		</div>

		<div id="passwordDiv" class="paddingBtm">

			<span id="pass">Confirm Password: </span><input id="passInput"
				type="password" name="psw" required />

		</div>


		<div id="loginBtn">

			<input id="btn" type="submit" value="Submit" />

		</div>

	</form>

	<p style="color: green;">${message}</p>

</div>
	
</body>
</html>