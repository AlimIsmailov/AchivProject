<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
		<c:if test="${confirmed eq true}">
				<div class="alert alert-success"><spring:message code="label.applicationConfirmed" /></div>
			</c:if>
			<c:if test="${deleted eq true}">
				<div class="alert alert-success"><spring:message code="label.applicationDeleted" /></div>
			</c:if>
			<c:if test="${!empty applicationList}">
				<table class="table table-bordered table-hover table-striped sortable">
					<tr>
						<th><spring:message code="label.group" /></th>
						<th><spring:message code="label.firstname" /></th>
						<th><spring:message code="label.lastname" /></th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${applicationList}" var="application">
						<tr>
							<td>${application.group.groupName}</td>
							<td>${application.user.firstName}</td>
							<td>${application.user.lastName}</td>
							<td><fmt:formatDate value="${application.created}" dateStyle="DEFAULT" /></td>
							<td><a href="application/confirm/${application.id}"class="btn btn-default"><spring:message
										code="label.confirm" /></a></td>
							<td><a href="application/delete/${application.id}"class="btn btn-default"> <spring:message
										code="label.delete" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
