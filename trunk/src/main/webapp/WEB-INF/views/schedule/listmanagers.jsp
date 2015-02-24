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
		<div class="body">
			<c:if test="${!empty userList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.managers" /></th>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<th>&nbsp;</th>
						</security:authorize>
					</tr>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td> ${user.firstName} ${user.lastName}</td>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<td><a href="schedule/${user.login}"
									class="btn btn-default" data-toggle="modal"
									 onclick="schedule/add/${user.login}"><spring:message
											code="label.viewschedule" /></a></td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>