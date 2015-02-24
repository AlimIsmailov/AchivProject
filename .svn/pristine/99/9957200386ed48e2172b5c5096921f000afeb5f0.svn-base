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
		<p>
			<spring:message code="label.group" />
			: <strong> ${group.groupName}</strong>
		</p>

		<p>
			<spring:message code="label.direction" /> :
			<a href="${pageContext.request.contextPath}/directions/${direction.id}">${group.direction.name}</a>
		</p>

		<c:if test="${!empty managerList}">
			<spring:message code="label.managers" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.login" /></th>
					<th><spring:message code="label.firstname" /></th>
					<th><spring:message code="label.lastname" /></th>
				</tr>
				<c:forEach items="${managerList}" var="manager">
					<tr>
						<td>${manager.login}</td>
						<td>${manager.firstName}</td>
						<td>${manager.lastName}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${!empty userList}">
			<spring:message code="label.users" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.login" /></th>
					<th><spring:message code="label.firstname" /></th>
					<th><spring:message code="label.lastname" /></th>
				</tr>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.login}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>