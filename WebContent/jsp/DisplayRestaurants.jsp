<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose For Me</title>

</head>
<body>
	<h1>Here are the list of Restaurants nearby:</h1>

	<c:forEach var="restaurants" items="${restaurants}">

		<tr>
			<td><c:out value="${restaurants.name}" /><br></td>
		</tr>

	</c:forEach>

	<div>
		<button onClick="window.location.href=window.location.href">Choose</button>
		<p>CHOSEN: ${randRestaurant.name}</p>
	</div>
</body>
</html>