<%@ page language="java" session="true" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<style>
.top-buffer {
 margin-top:4px; 
 }

#container {
width:680px;  
margin:0 auto; 

}

.readOnlyRow {
background: none; 
border: none;
width:140px;
}
</style>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<form:form action="searchUsers" commandName="searchRequestForm" method="post">
				<div class="panel panel-default" style="width: 650px; margin: 0 auto;">
					<div class="panel-body">
						<div class="row">
							<div id="NameFieldContainer" class="col-md-5">
								<form:input id="NameField" path="firstNameOrLoginOrEmail" value="${searchRequestForm.firstNameOrLoginOrEmail}" type="text" class="form-control" placeholder="Name" />
							</div>
							<div id="SurNameFieldContainer" class="col-md-5">
								<form:input id="SurNameField" type="text" path="surName" class="form-control" placeholder="Surname" value="${searchRequestForm.surName}"/>
							</div>
							<div class="col-md-2">
								<div>
									<button type="submit" class="btn btn-primary col-md-12">Search</button>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4" >
								<div class="radio">
									<label> <form:radiobutton id="byNameRadioBtn" path="searchTarget" value="searchByName" onClick="toggleRadioButton()" checked="true"/>Name
									</label> 
									<label> <form:radiobutton id="byLoginBtn" path="searchTarget" value="searchByLogin" onClick="toggleRadioButton()"/>Login
									</label> 
									<label> <form:radiobutton id="byEmailBtn" path="searchTarget" value="searchByEmail" onClick="toggleRadioButton()"/>email
									</label>
								</div>
							</div>
							<div class="col-md-8"  >
									<div class="checkbox inline"><form:checkbox id="advancedOptionsBtn" path="advancedMode" value="1" onClick="toggleButoonSearchOptions()"/>Advanced</div>
							</div>
						</div>
						
						<div id="SearchingOptionsContainer" class="row top-buffer hide">
						<div class="col-md-4">
								<div class="panel panel-primary">
									<div class="panel-heading">
									<div class="row">
										<div class="col-md-7"  >
											<h3 class="panel-title">Roles</h3>
										</div>
										<div class="col-md-3">
											<form:select path="operationOnRoles" style="background-color:#72C2FF;">
												<option value="AND" <c:if test="${searchRequestForm.operationOnRoles == 'AND'}">selected</c:if>>AND</option>
												<option value="OR" <c:if test="${searchRequestForm.operationOnRoles == 'OR'}">selected</c:if>>OR</option>
											</form:select>
										</div>
										<div class="col-md-2"  >
										</div>
									</div>
									</div>
									<div class="panel-body">
										<div class="controls">
											<form:checkbox path="selectedRoles" value="ROLE_ADMIN"/>Administrator<br>
											<form:checkbox path="selectedRoles" value="ROLE_MANAGER"/>Manager<br>
											<form:checkbox path="selectedRoles" value="ROLE_STUDENT"/>Student<br>
											<form:checkbox path="selectedRoles" value="ROLE_USER"/>User
										</div>
									</div>
								</div>
							</div>
						<div class="col-md-4">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3 class="panel-title">Status</h3>
									</div>
									<div class="panel-body">
										<div class="radio">
											<label> <form:radiobutton  path="statusOption" value="1"/>Active
											</label> <br>
											<label> <form:radiobutton  path="statusOption" value="0"/>Not active
											</label> <br>
											<label> <form:radiobutton  path="statusOption" value="any"/>Any status
											</label>
										</div>
								</div>
							</div></div>
							<div class="col-md-4">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3 class="panel-title">Result options</h3>
									</div>
									<div class="panel-body">
										<form:checkbox path="firstNameColumn" value="1"/>Name<br>
										<form:checkbox path="lastNameColumn" value="1"/>Surname<br>
										<form:checkbox path="loginColumn" value="1"/>Login<br>
										<form:checkbox path="emailColumn" value="1"/>Email
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form:form><br>
			
			<div class="row">
				<div class="col-md-4 text-left">
					Found results: ${userListForm.listSize};
					<c:if test="${userListForm.listSize != 0}">
					Page: ${userListForm.page} of ${userListForm.totalPagesCount}</c:if>
				 </div>
			</div>
			
			
			<c:if test="${userListForm.userList != null}">
			<c:if test="${userListForm.listSize != 0}">
				<form:form method="post" action="applyChanges" commandName="userListForm">

				<table class="table table-bordered table-hover table-striped">
					<tr>
						<c:if test="${searchRequestForm.firstNameColumn == true}"><th rowspan="2"><spring:message code="label.firstname"/></th></c:if>
						<c:if test="${searchRequestForm.lastNameColumn == true}"><th rowspan="2"><spring:message code="label.lastname"/></th></c:if>
						<c:if test="${searchRequestForm.emailColumn == true}"><th rowspan="2"><spring:message code="label.email"/></th></c:if>
						<c:if test="${searchRequestForm.loginColumn == true}"><th rowspan="2"><spring:message code="label.login"/></th></c:if>
						<th style="text-align:center" colspan="3">Roles</th>
						<th style="text-align:center" rowspan="2">Active</th>
					</tr>
					<tr>
						<td class="hide">User</td>
						<td class="tg-031e text-center">Admin</td>
						<td class="tg-031e text-center">Manager</td>
						<td class="tg-031e text-center">Student</td>
					</tr>
					<c:forEach items="${userListForm.pageList}" var="user" varStatus="status">
						<tr>
						    <td class="hide"><form:input id="tableRow" path="pageList[${status.index}].id"/></td>
						    <c:if test="${searchRequestForm.firstNameColumn == true}">
						    <td>
						    <input type="text" class="readOnlyRow" name="pageList[${status.index}].firstName" value="${user.firstName}" readonly/>
						    </td></c:if>
							<c:if test="${searchRequestForm.lastNameColumn == true}">
							<td><input type="text" class="readOnlyRow" name="pageList[${status.index}].lastName" value="${user.lastName}" readonly/>
							</td></c:if>
							<c:if test="${searchRequestForm.emailColumn == true}">
							<td><input type="text" class="readOnlyRow" name="pageList[${status.index}].email" value="${user.email}" readonly/>
							</td></c:if>
							<c:if test="${searchRequestForm.loginColumn == true}">
							<td><input type="text" class="readOnlyRow" name="pageList[${status.index}].login" value="${user.login}" readonly/>
							</td></c:if>

						    <td class="hide"><form:checkbox path="pageList[${status.index}].roles" value="ROLE_USER"/></td> 
							<td class="text-center"><form:checkbox path="pageList[${status.index}].roles" value="ROLE_ADMIN"/></td>
							<td class="text-center"><form:checkbox path="pageList[${status.index}].roles" value="ROLE_MANAGER"/></td>
							<td class="text-center"><form:checkbox path="pageList[${status.index}].roles" value="ROLE_STUDENT"/></td>
							<td class="text-center"><form:checkbox path="pageList[${status.index}].active" value="1"/></td>
						</tr>
					</c:forEach>
				</table>
				<div class="row">
					<div class="col-md-8">
					<c:if test="${userListForm.listSize > userListForm.elementsPerPage}">
					<ul class="pagination"  style="margin: 0;">
						<li ><a href="previousPage">&laquo;</a></li>
						<li><a href="nextPage">&raquo;</a></li>
					</ul>
					</c:if>
				</div>
				<div  class="col-md-4" style="text-align: right;">
				<input type="submit" value="Apply" class="btn  btn-primary"/>
				</div>
				</div>
			</form:form>
			</c:if>
			</c:if>

<script type="text/javascript">
$('document').ready(function() {
    toggleRadioButton();
    toggleButoonSearchOptions();
});

function toggleButoonSearchOptions() {
	var searchingOptionsContainer = document.getElementById("SearchingOptionsContainer");
	var advancedOptionsBtn = document.getElementById("advancedOptionsBtn");
	
if (advancedOptionsBtn.checked)
	searchingOptionsContainer.className = "row top-buffer";
else
	searchingOptionsContainer.className = "row top-buffer hide";
}

	function toggleRadioButton() {
		var byNameRadioBtn = document.getElementById("byNameRadioBtn");
		var byEmailBtn = document.getElementById("byEmailBtn");
		var surNameField = document.getElementById("SurNameField");
		var nameField = document.getElementById("NameField");
		var byLoginBtn = document.getElementById("byLoginBtn");
		var nameFieldContainer = document.getElementById("NameFieldContainer");
		var surNameFieldContainer = document.getElementById("SurNameFieldContainer");

		if (byNameRadioBtn.checked) {
			nameFieldContainer.className = "col-md-5";
			surNameFieldContainer.className = "col-md-5";
			nameField.placeholder = "Name";
			surNameField.setAttribute("type", "text");
			surNameField.placeholder = "Surname";
		}

		if (byLoginBtn.checked) {
			nameFieldContainer.className = "col-md-10";
			surNameFieldContainer.className = "";
			surNameField.setAttribute("type", "hidden");
			nameField.placeholder = "Login";
		}

		if (byEmailBtn.checked) {
			nameFieldContainer.className = "col-md-10";
			surNameFieldContainer.className = "";
			surNameField.setAttribute("type", "hidden");
			nameField.placeholder = "Email";
		}
	}
</script>
</security:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>