<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Control Board</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
            <div class="bar">
                <div class="wrap-bar">
                        <diV><h3>Admin</h3></diV>
                        <div><a href="${pageContext.request.contextPath}/login.jsp">Logout</a></div> 
                </div>               
             </div>
        <div class="page">
            
              <div class="wrapper">
                <div class="left-side">
                    <div class="left-element">
                        Dashboard
                    </div>
                    <div class="dropdown">
                        <button class="dropbtn">Exam</button>
                        <div class="dropdown-content">
                            <a href="${pageContext.request.contextPath}/create.jsp">Create exam</a>
                            <a href="edit.jsp">Edit exam</a>
                            <a href="delete.jsp">Delete exam</a>
                            <a href="view.jsp">view exams</a>
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
        
                </div>
            </div>
        </div>


    </body>
</html>