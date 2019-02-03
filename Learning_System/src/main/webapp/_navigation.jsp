<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
html, body {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
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
</style>



<nav class="nav main-nav">
	<ul>
		<li><a href="${pageContext.request.contextPath}/home.jsp">HOME</a></li>
		<li><a href="about.html">ABOUT</a></li>
		<li><a href="${pageContext.request.contextPath}/login.jsp">TAKE EXAM</a></li>
		<li><a href="#contact.html">CONTACT</a></li>
	</ul>
</nav>