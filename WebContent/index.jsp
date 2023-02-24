<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose For Me</title>
<script>
	function getCurrentLocation(){
		if(navigator.geolocation){
			navigator.geolocation.getCurrentPosition(showPosition);
		}else{
			document.getElementById("location").placeholder = "Geolocation is not supported";
		}
	}
	
	 function showPosition(position) {
	        var latitude = position.coords.latitude;
	        var longitude = position.coords.longitude;
	        var locationInput = document.getElementById("location");
	        locationInput.value = latitude + ", " + longitude;
	  }
</script>

</head>
<body>
	<h1>Choose For Me</h1>
	
	<form id="location-form" name="search" action="${pageContext.request.contextPath}/RestaurantsServlet"
			method="GET" onSubmit="">
		
			
			<input type="text" name="location" id="location">
			<select id="food-category" name="food-category">
				<option value="korean">Korean</option>
				<option value="japanese">Japanese</option>
				<option value="vietnamese">Vietnamese</option>
				<option value="american">American</option>
				<option value="french">French</option>
				<option value="italian">Italian</option>
			</select>
			<input type="submit" value="search">
			
			
	</form>
	<button onclick="getCurrentLocation()">Use Current Location</button>
	<br>
</body>
</html>