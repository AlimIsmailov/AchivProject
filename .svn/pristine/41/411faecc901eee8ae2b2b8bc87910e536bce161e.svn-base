<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<form:form class="form-container" method="post" action="addGroup"
				commandName="groupFormBean">
				<div class="form-title">
					<h3>
						<spring:message code="label.newgroup" />
					</h3>
				</div>
				<div class="form-title">
					<form:label path="group.groupName">
						<spring:message code="label.name" />
					</form:label>
					<form:errors path="group.groupName" style="color: red;"></form:errors>
					<form:errors path="group.direction.groups" style="color: red;"></form:errors>
					<form:input class="form-field" path="group.groupName" />
					<form:label path="start">
						<spring:message code="label.start" />
					</form:label>
					<form:errors path="start" style="color: red;">Wrong date!</form:errors>
					<form:input class="form-field" path="start" class="datepicker" />

					<form:label path="end">
						<spring:message code="label.finish" />
					</form:label>
					<form:errors path="end" style="color: red;">Wrong date!</form:errors>
					<form:input class="form-field" path="end" class="datepicker"/>

					<form:label path="group.direction">
						<spring:message code="label.direction" />
					</form:label>

					<form:select path="group.direction" items="${directionList}"
						itemLabel="name" itemValue="name">
					</form:select>
					<br>

					<form:label path="users">
						<spring:message code="label.manager" />
					</form:label>
					<form:checkboxes items="${managerList}" path="users"
						itemLabel="login" itemValue="login" />
				</div>
				<div class="submit-container">
					<input class="submit-button" type="submit"
						value="<spring:message code="label.add"/>" />
				</div>
			</form:form>

		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
