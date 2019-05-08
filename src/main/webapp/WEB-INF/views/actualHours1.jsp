<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>Sub Task </title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
	  <div class="container">
                    <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <ul class="nav navbar-nav">
                                    <li><a class="navbar-brand" href="/" th:href="@{/}">Home</a></li>
                                    <li><a href="/api/user/users"style="color:red;" th:href="@{/api/user/users}">USERS</a></li>
                                    <li><a href="/api/team/teams" style="color:red;"th:href="@{/api/team/teams}">Teams</a></li>
                                    <li><a href="/api/sprint/sprints"style="color:red;" th:href="@{api/sprint/sprints}">SPRINTS</a></li>
                                    <li><a href="/api/sprint/add"style="color:red;" th:href="@{/api/sprint/add}">Create Sprint</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
       </div>
	  <div class="container">
	        <h3 id="form_header" class="text-warning" align="center">table</h3>
	        <div>&nbsp;</div>
            <c:url var="saveUrl" value="/api/sprint/save" />
              <form:form id="task_form" modelAttribute="taskAttr" method="POST" action="${saveUrl}">
              <form:hidden path="id" />
	            <form:input id="task_name" cssClass="form-control" path="name" />
                               <table id="subtask_table" class="table">
                                <tbody>
                                   <c:forEach items="${taskAttr.subTasks}" varStatus="st" var="subTask">
                                     <tr align="left">
                                       <td> <form:input cssClass="form-control" path="subTasks[${st.index}].id" value="${subTask.name}"/></td>
                                       <form:hidden path="subTasks[${st.index}].id" />
                                       <table id="actualHours_table" class="table">
                                          <tbody>
                                            <c:forEach items="${subTask.actualHours}" varStatus="ah" var="actualHour">
                                              <td style="width: 50px;"> Day${ah.index+1}</td>
                                              <td> ${actualHour}  </td>
                                           <td><form:input style="width: 30px;" type="number" path="actualHours[${ah.index}]"  value="${actualHour}" /></td>
                                            </c:forEach>
                                          </tbody>
                                        </table>

                                     </tr>
                                   </c:forEach>
                                </tbody>
                               </table>
                  <div>&nbsp;</div>
	            <button id="saveBtn" type="submit" class="btn btn-primary">Save</button>
	                        <c:url var="CancelUrl" value="/api/task/edit?taskid=${taskid}&sprintid=${sprintid}" /><a id="cancel" href="${CancelUrl}" class="btn btn-danger">Cancel</a>

               </form:form>

	   </div>
     </body>
</html>