<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<th><p class="text-primary"><spring:message code="label.login" /></p></th>
						<th><p class="text-primary"><spring:message code="label.firstname" /></p></th>
						<th><p class="text-primary"><spring:message code="label.lastname" /></p></th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td>${user.login}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td><a href="useractivate/${user.id}">
									<c:if test="${user.active eq 0}"><button
										type="button" class="btn btn-success btn-block" id="activate">
										<spring:message code="label.activate" />
									</button></c:if></a>
							<a href="userdeactivate/${user.id}">
									<c:if test="${user.active eq 1}"><button
										type="button" class="btn btn-danger btn-block" id="deactivate">
										<spring:message code="label.deactivate" />
									</button></c:if></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>



	</tiles:putAttribute>
</tiles:insertDefinition>