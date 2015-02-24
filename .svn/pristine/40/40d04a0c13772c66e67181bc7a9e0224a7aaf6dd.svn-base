<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<h2>${topic.topicName}</h2>


		<c:if test="${!empty topic.directions}">
			<spring:message code="label.directions" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
				</tr>
				<c:forEach items="${topic.directions}" var="direction">
					<tr>
						<td><a href="/achivproject/directions/${direction.id}">${direction.name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${!empty topic.subtopics}">
			<spring:message code="label.subtopics" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
				</tr>
				<c:forEach items="${topic.subtopics}" var="subtopic">
					<tr>
						<td><a href="/achivproject/subtopics/${subtopic.id}">${subtopic.name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>