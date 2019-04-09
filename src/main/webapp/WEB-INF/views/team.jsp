<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Team</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
			<h2 id="article_header" class="text-warning" align="center">All teams</h2>
	    	<div>&nbsp;</div>

	    	<!-- Div to add a new user to the mongo database -->
	    	<div id="add_new_team">
	    			<c:url var="addUrl" value="/api/team/add" /><a id="add" href="${addUrl}" class="btn btn-success">Add team</a>
	    	</div>
	    	<div>&nbsp;</div>

	    	<!-- Table to display the user list from the mongo database -->
	    	<table id="teams_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Id</th>
	            		<th>Name</th>
	            		<th colspan="2"></th>

	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${teams}" var="team">
	                	<tr align="center">
	                    	<td><c:out value="${team.id}" /></td>
	                    	<td><c:out value="${team.name}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="/api/team/edit?id=${team.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Update</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/api/team/delete?id=${team.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
<td>
	                        	<c:url var="detailUrl" value="/api/team/detail?id=${team.id}" /><a id="detail" href="${detailUrl}" class="btn btn-info">Team members</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>
	</body>
</html>