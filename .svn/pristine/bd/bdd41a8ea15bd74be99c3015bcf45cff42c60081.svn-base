<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<c:if test="${!empty userList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><p class="text-primary"><spring:message code="label.firstname" />
										<a href="usersheap"><button
										type="button" class="btn btn-default btn-xs">
										<spring:message code="label.sortbylogindown" />
									</button></a></p></th>
						<th><p class="text-primary"><spring:message code="label.firstname" /></p></th>
						<th><p class="text-primary"><spring:message code="label.lastname" /></p></th>
						<th><p class="text-primary"><spring:message code="label.email" /></p></th>
						<th><p class="text-primary"><spring:message code="label.created" /></p></th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td>${user.login}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.created}</td>
							<td><a href="<c:url value="users/profile/show/${user.login}" /> "><button
										type="button" class="btn btn-primary btn-sm">
										<spring:message code="label.edit" />
									</button></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>