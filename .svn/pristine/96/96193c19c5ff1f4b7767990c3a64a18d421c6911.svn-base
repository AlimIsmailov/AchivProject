<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<style>
</style>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<form:form class="form-container" method="post" action="addSubtopic"
				commandName="subtopic">

				<div class="form-title">
					<h3>
						<spring:message code="label.subtopic" />
					</h3>
				</div>

				<div class="form-title">
					<form:label path="name">
						<spring:message code="label.name" />
					</form:label>
					<form:errors path="name" style="color: red;"></form:errors>
					<form:input class="form-field" path="name" />

					<form:label path="topic">
						<spring:message code="label.topic" />
					</form:label>

					<form:select path="topic" items="${topicList}"
						itemLabel="topicName" itemValue="topicName">
					</form:select>
				</div>
				<div class="submit-container">
					<input class="submit-button" type="submit" id="/addSubtopic"
						value="<spring:message code="label.add"/>" />
				</div>
			</form:form>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
