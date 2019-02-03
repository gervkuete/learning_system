<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>exam</title>
</head>
<body>
  
  <jsp:include page="_navigation.jsp"/>
  
  <div class="logout">
  <a href="${pageContext.request.contextPath}/login.jsp"></a>
  </div>
  <form action="result.do" method="post">

	<c:forEach var="map" items="${mapOfQuestions}" varStatus="loopCount">
	  
	     ${loopCount.count}. <c:out value="${map.key}"/>
	  
	   <c:forEach var="listItem" items="${map.value }" varStatus="counter">
	    
	       <input type="radio" name="questionAnswer" value="${counter.count}">"${listItem}"<br>
	    	  
	   </c:forEach>	
	   <hr>
	</c:forEach>
		
 <input type="submit" value="Submit"/>

</form>
  <jsp:include page="_footer.jsp"/>
</body>
</html>