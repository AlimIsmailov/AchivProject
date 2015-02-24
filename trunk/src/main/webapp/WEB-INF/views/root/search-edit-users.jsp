<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style>

#container {
width:522px;  
margin:0 auto; 
border:1px solid; 
border-color:#DDDDDD
}

#header{
background-color: #F9F9F9;
}

#roleesSeclect {
background-color: #F9F9F9; 
height: 200px; 
width: 25%; 
float: left;
padding:5px;
}

#searchFields{
background-color: #F9F9F9;
height: 200px; 
width: 33%; 
float: left; 
padding:5px;
}

#tableColumnsSelect{
background-color: #F9F9F9;
height: 200px; 
width: 42%; 
float: left; 
padding:5px;
}

#footer{
background-color: #F9F9F9; 
clear: both; 
text-align: right;
}

#headerLabel {
margin-bottom: 0; 
margin-top: 0;
}

#tableRow{
background: none; 
border: none;
width:140px;
}

#tableRowId{
background: none; 
border: none;
display:none;
width:auto;
}
</style>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">

			<form:form id="container" action="searchUsers" commandName="searchRequestForm" method="post">
				<div id="header">
					<h1 id="headerLabel">Search options</h1>
				</div>
				
				<div id="roleesSeclect">
					<b><spring:message code="label.role"/></b><br> 
					<form:select id="fs-main" path="selectedRoles"
						multiple="true" size="6">
						<option value="ROLE_ADMIN">Administrator</option>
						<option value="ROLE_MANAGER">Manager</option>
						<option value="ROLE_USER">User</option>
						<option value="ROLE_STUDENT">Student</option>
						<option value="ALL">ALL</option>
					</form:select>
				</div>
				<div id="searchFields">
					<b><spring:message code="label.firstname"/></b><br>
					<form:input type="text" path="firstNameField"/>
					<b><spring:message code="label.lastname"/></b><br>
					<form:input type="text" path="lastNameField"/>
					<b><spring:message code="label.login"/></b><br> 
					<form:input type="text" path="loginField"/>
				</div>
				<div id="tableColumnsSelect">
					<b>Columns to display</b><br> 
					<form:checkbox path="firstNameColumn"/><spring:message code="label.firstname"/><br> 
					<form:checkbox path="lastNameColumn"/><spring:message code="label.lastname"/><br>
					<form:checkbox path="emailColumn"/><spring:message code="label.email"/><br> 
					<form:checkbox path="dateColumn"/><spring:message code="label.creationDate"/>
				</div>
				<div id="footer">
					<input type="submit" value="<spring:message code="label.search"/>"/>
				</div>

			</form:form><br>
			
			<form:form method="post" action="applyChanges"
				commandName="userListForm">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th rowspan="2"><spring:message code="label.firstname"/></th>
						<th rowspan="2"><spring:message code="label.lastname"/></th>
						<th rowspan="2"><spring:message code="label.email"/></th>
						<th rowspan="2"><spring:message code="label.login"/></th>
						<th style="text-align:center" colspan="4">Roles</th>
						<th style="text-align:center" rowspan="2">Active</th>
						<th style="text-align:center" rowspan="2">Delete</th>
					</tr>
					<tr>
						<td class="tg-031e">User</td>
						<td class="tg-031e">Admin</td>
						<td class="tg-031e">Manager</td>
						<td class="tg-031e">Student</td>
					</tr>
					<c:forEach items="${userListForm.userList}" var="user" varStatus="status">
				
						<tr>
						    <td id="tableRowId"><form:input id="tableRow" path="userList[${status.index}].id"/></td>
						    <td><form:input id="tableRow" path="userList[${status.index}].firstName" value="${user.firstName}"/></td>
							<td><form:input id="tableRow" path="userList[${status.index}].lastName" value="${user.lastName}"/></td>
							<td><form:input id="tableRow" path="userList[${status.index}].email" value="${user.email}"/></td>
							<td><form:input id="tableRow" path="userList[${status.index}].login" value="${user.login}"/></td>

							<td><form:checkbox path="userList[${status.index}].roles" value="ROLE_USER"/></td>

							<td><form:checkbox path="userList[${status.index}].roles" value="ROLE_ADMIN"/></td>

							<td><form:checkbox path="userList[${status.index}].roles" value="ROLE_MANAGER"/></td>

							<td><form:checkbox path="userList[${status.index}].roles" value="ROLE_STUDENT"/></td>
							
							<!-- userList[${status.index}].active -->
							<td><form:checkbox path="" value="1"/></td>
							
							<td><form:checkbox path="deleteUserIdList" value="userList[${status.index}].id"/></td>
						</tr>
						
					</c:forEach>
				</table>
				<div style="text-align: right;">
				<input type="submit" value="Apply" />
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>