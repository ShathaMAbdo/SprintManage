<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>Team form</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
	    <div class="container">
	        <h3 id="form_header" class="text-warning" align="center">Team Form</h3>
	        <div>&nbsp;</div>

			<!-- Team input form to add a new team or update the existing team-->
	        <c:url var="saveUrl" value="/api/team/save" />
	        <form:form id="team_form" modelAttribute="teamAttr" method="POST" action="${saveUrl}">
	        	<form:hidden path="id" />
	            <label for="team_name">Enter Name: </label>
	            <form:input id="team_name" cssClass="form-control" path="name" />
	            <div>&nbsp;</div>
	            <button id="saveBtn" type="submit" class="btn btn-primary">Save</button>
	        </form:form>
	    </div>
	</body>
</html>