<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Meridian</title>
<style type="text/css">
	.error{
		color: red;
		position: fixed;
		text-align: left;
		margin-left: 10px;
		vertical-align: middle;
	}
</style>
</head>
<body>
	<%@ include file = "/views/header.jsp" %>
	<div align="center">
		<h1 align="center">Add New Meridian</h1>
	</div>
	<div align="center">
		<h4 align="center" style="color: green">${successMessage }</h4>
		<h4 align="center" style="color: red">${errorMessage }</h4>
	</div>
	<div align="center">
		<form:form method="post" action="/meridian/add" modelAttribute="meridian">
			<table>
				<tr>
					<td>
						<label>Meridian Name</label>
					</td>
					<td>
						<form:input path="name"/>
					</td>
					<td>
						<form:errors path="name" cssClass="error"/>
					</td>	
				</tr>
				<tr>
					<td>
						<label>Alternate Names</label>
					</td>
					<td>
						<form:textarea rows="6" cols="20" path="alternateNames"/>
					</td>
					<td>
						<form:errors path="alternateNames" cssClass="error" />
					</td>	
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="Save">
					</td>
				</tr>
			</table>
			
		</form:form>
	</div>
	
</body>
</html>