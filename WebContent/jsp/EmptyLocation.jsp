<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Choose For Me</h1>
	
	<p>Field Cannot be empty. Please enter a location or zip code.</p>
	<form id="location-form" name="search" action="${pageContext.request.contextPath}/RestaurantsServlet"
			method="GET" onSubmit="">
		
			
			<input type="text" name="location" id="location">
			<input type="submit" value="search">
	</form>
</body>
</html>