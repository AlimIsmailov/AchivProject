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
<!-- Add location -->
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal"><spring:message code="label.add"/></button>

			<form:form action="addLocation" commandName="location"
				class="form-horizontal" method="post">
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"><spring:message code="label.newlocation"/></h4>
							</div>
							<div class="modal-body">
								<div class="formGroup">
									<label for="name" class="col-sm-2 control-label">
										<spring:message code="label.name" /></label>
									<div class="col-sm-10">
										<form:input path="name" cssClass="form-control" />
										<form:errors path="name" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal"><spring:message code="label.close"/></button>
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.add"/>" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</security:authorize>
<!-- Edit location -->
<security:authorize access="hasRole('ROLE_ADMIN')">
			<%-- <!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal"><spring:message code="label.add"/></button> --%>

			<form:form action="addLocation" commandName="location"
				class="form-horizontal" method="post">
				<!-- Modal -->
				<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"><spring:message code="label.newlocation"/></h4>
							</div>
							<div class="modal-body">
								<div class="formGroup">
									<label for="name" class="col-sm-2 control-label">
										<spring:message code="label.name" /></label>
									<div class="col-sm-10">
										<form:input path="name" cssClass="form-control" />
										<form:errors path="name" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal"><spring:message code="label.close"/></button>
								<input type="submit" class="btn btn-primary" value="<spring:message code="label.add"/>" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</security:authorize>
		<br>

<!-- List of locations -->
		<div class="body">
			<c:if test="${!empty locationList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.location" /></th>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</security:authorize>
					</tr>
					<c:forEach items="${locationList}" var="location">
						<tr>
							<td>${location.name}</td>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<td><a href="location/edit/${location.id}"
									class="btn btn-default" data-toggle="modal"
									data-target="#myModal2" onclick="location/edit/${location.id}"><spring:message
											code="label.edit" /></a></td>
								<td><a href="location/delete/${location.id}"
									class="btn btn-danger"><spring:message code="label.delete"/></a></td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>