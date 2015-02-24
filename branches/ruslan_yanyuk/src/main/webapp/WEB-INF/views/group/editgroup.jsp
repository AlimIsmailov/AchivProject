<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<style>
</style>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<form:form class="form-container" method="post" action="editGroup" commandName="group">
				<div class="form-title">
					<h3><spring:message code="label.editgroup" /></h3>
				</div>
					
				<div class="form-title">
					<form:label path="groupName">
						<spring:message code="label.name" />
					</form:label>
				<form:errors path="groupName" style="color: red;"></form:errors>
				</div>
				<form:input class="form-field" path="groupName"/>
				<form:label path="direction">
						<spring:message code="label.direction" />
					</form:label>
			
				<form:select path="direction" items="${directionList}" itemLabel="name" itemValue="name">
					</form:select>
				<br />
				<div class="submit-container">
					<input class="submit-button" type="submit"
						value="<spring:message code="label.edit"/>" />
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>