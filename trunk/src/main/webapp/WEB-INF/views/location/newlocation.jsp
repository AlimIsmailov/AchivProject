<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form:form class="form-container" method="post" action="addLocation"
				commandName="location">
				<div class="form-title">
					<h3><spring:message code="label.newlocation" /></h3>
				</div>
					
				<div class="form-title">
					<form:label path="name">
						<spring:message code="label.name" />
					</form:label>
				<form:errors path="name" style="color: red;"></form:errors>
				</div>
				<form:input class="form-field" path="name" />
				<br />
				<div class="submit-container">
					<input class="submit-button" type="submit"
						value="<spring:message code="label.add"/>" />
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>