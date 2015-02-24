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

		<h2>${direction.name}</h2>


		<c:if test="${!empty direction.topics}">
			<spring:message code="label.topics" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
				</tr>
				<c:forEach items="${direction.topics}" var="topic">
					<tr>
						<td><a href="${pageContext.request.contextPath}/topics/${topic.id}">${topic.topicName}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${!empty direction.groups}">
			<spring:message code="label.groups" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
				</tr>
				<c:forEach items="${direction.groups}" var="group">
					<tr>
						<td><a href="${pageContext.request.contextPath}/groups/${group.id}">${group.groupName}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>