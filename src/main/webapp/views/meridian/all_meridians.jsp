<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Meridians</title>
</head>
<body>
	<%@ include file = "/views/header.jsp" %>
	<div align="center">
		<h1 align="center">All Meridians</h1>
	</div>
	<div align="center">
		<c:forEach var="meridian" items="${meridians}">
			<li>${meridian.name }</li>
		</c:forEach>
	</div>
</body>
</html>