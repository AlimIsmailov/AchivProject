<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<c:if test="${param.success eq true}">
				<div class="alert alert-success">Group added (edited)
					successfully!</div>
			</c:if>
			<c:if test="${param.groupexists eq true}">
				<div class="alert alert-danger">This groups already exists!</div>
			</c:if>
			<c:if test="${param.invaliddate eq true}">
				<div class="alert alert-danger">Invalid date!</div>
			</c:if>
			<security:authorize
				access="hasAnyRole('ROLE_STUDENT','ROLE_MANAGER','ROLE_ADMIN')">
				<form:form method="POST" action="chooseGroup"
					commandName="groupFormBean">
					<form:checkbox path="isFuture" />Future
			&nbsp;
			&nbsp;
			&nbsp;
			&nbsp;
			<form:checkbox path="isCurrent" checked="checked"/>Current
			&nbsp;
			&nbsp;
			&nbsp;
			&nbsp;
			<form:checkbox path="isFinished" />Finished
			&nbsp;
			&nbsp;
			&nbsp;
			&nbsp;
				<input type="submit" class="btn btn-primary" value="Choose" />
				</form:form>
				<br>
				<br>
			</security:authorize>

			<!-- Add group -->
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<!-- Button trigger modal -->
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#myModal">
					<spring:message code="label.add" />
				</button>
				<br>
				<br>
				<form:form action="addGroup" commandName="groupFormBean"
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
									<h4 class="modal-title" id="myModalLabel">
										<spring:message code="label.newgroup" />
									</h4>
								</div>
								<div class="modal-body">
									<div class="formGroup">
										<label for="group.groupName" class="col-sm-2 control-label">
											<spring:message code="label.name" />
										</label>
										<div class="col-sm-10">
											<form:input path="group.groupName" cssClass="form-control" />
											<form:errors path="group.groupName" />
										</div>

										<label for="start" class="col-sm-2 control-label"> <spring:message
												code="label.start" /></label>
										<div class="col-sm-10">
											<div class='date'><form:input path="start" cssClass="form-control"
												value="2014-08-01" /></div>
											<form:errors path="start" />
										</div>

										<label for="end" class="col-sm-2 control-label"> <spring:message
												code="label.finish" /></label>
										<div class="col-sm-10">
											<form:input path="end" cssClass="form-control"
												class="datepicker" value="2014-12-30" />
											<form:errors path="end" />
										</div>

										<label for="group.direction" class="col-sm-2 control-label">
											<spring:message code="label.direction" />
										</label>
										<div class="col-sm-10">
											<form:select path="group.direction" items="${directionList}"
												itemLabel="name" itemValue="name" cssClass="form-control">
											</form:select>
											<form:errors path="group.direction" />
										</div>

										<label for="users" class="col-sm-2 control-label"> <spring:message
												code="label.manager" />
										</label>
										<div class="col-sm-10">
											<%-- <form:checkboxes items="${managerList}" path="users"
												itemLabel="login" itemValue="login" /> --%>
											<form:select path="users" items="${managerList}" itemLabel="login" itemValue="login" multiple="true"></form:select>
											<form:errors path="group.direction" cssClass="form-control" />
										</div>

									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">
										<spring:message code="label.close" />
									</button>
									<input type="submit" class="btn btn-primary"
										value="<spring:message code="label.add"/>" />
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</security:authorize>
			<c:if test="${!empty groupList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.groups" /></th>
						<th><spring:message code="label.direction" /></th>
						<th><spring:message code="label.start" /></th>
						<th><spring:message code="label.finish" /></th>
						<security:authorize
							access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</security:authorize>
						<security:authorize access="hasRole('ROLE_USER')">
							<th>&nbsp;</th>
						</security:authorize>
					</tr>

					<c:forEach items="${groupList}" var="group">
						<tr>
							<td><a href="group/${group.id}">${group.groupName}</a></td>
							<td>${group.direction.name}</td>
							<td><fmt:formatDate value="${group.start}" dateStyle="LONG" /></td>
							<td><fmt:formatDate value="${group.end}" dateStyle="LONG" /></td>
							<security:authorize
								access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')">
								<td>
									<button class="btn btn-primary btn-sm" data-toggle="modal"
										data-target="#myModalF${group.id}">
										<spring:message code="label.edit" />
									</button> <form:form action="editGroup" commandName="groupFormBean"
										class="form-horizontal" method="post">
										<!-- Modal -->
										<div class="modal fade" id="myModalF${group.id}" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span><span
																class="sr-only">Close</span>
														</button>
														<h4 class="modal-title" id="myModalLabel">
															<spring:message code="label.edit" />
														</h4>
													</div>
													<div class="modal-body">
														<div class="formGroup">
															<form:input type="hidden" path="group.id"
																value="${group.id}" />
															<label for="group.groupName"
																class="col-sm-2 control-label"> <spring:message
																	code="label.name" />
															</label>
															<div class="col-sm-10">
																<form:input path="group.groupName"
																	cssClass="form-control" value="${group.groupName}" />
																<form:errors path="group.groupName" />
															</div>

															<label for="start" class="col-sm-2 control-label">
																<spring:message code="label.start" />
															</label>
															<div class="col-sm-10">
																<c:if test='${group.start.month+1 <10}'>
																	<c:set var="zeroS" value="0"></c:set>
																</c:if>
																<form:input path="start" cssClass="form-control"
																	class="datepicker"
																	value="${group.start.year+1900}-${zeroS}${group.start.month+1}-${group.start.date}" />
																<form:errors path="start" />
															</div>

															<label for="end" class="col-sm-2 control-label">
																<spring:message code="label.finish" />
															</label>
															<div class="col-sm-10">
																<c:if test='${group.end.month+1 <10}'>
																	<c:set var="zeroE" value="0"></c:set>
																</c:if>
																<form:input path="end" cssClass="form-control"
																	class="datepicker"
																	value="${group.end.year+1900}-${zeroE}${group.end.month+1}-${group.end.date}" />
																<form:errors path="end" />
															</div>

															<label for="group.direction"
																class="col-sm-2 control-label"> <spring:message
																	code="label.direction" />
															</label>
															<div class="col-sm-10">
																<form:select path="group.direction"
																	items="${directionList}" itemLabel="name"
																	itemValue="name" cssClass="form-control"
																	value="${group.direction}">
																</form:select>
																<form:errors path="group.direction" />
															</div>

															<label for="users" class="col-sm-2 control-label">
																<spring:message code="label.manager" />
															</label>
															<div class="col-sm-10">
																<form:checkboxes items="${managerList}" path="users"
																	itemLabel="login" itemValue="login" />
																<form:errors path="group.direction"
																	cssClass="form-control" />
															</div>

														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">
															<spring:message code="label.close" />
														</button>
														<input type="submit" class="btn btn-primary"
															value="<spring:message code="label.edit"/>" />
													</div>
												</div>
											</div>
										</div>
									</form:form>
								</td>
								<td><button class="btn btn-danger" data-toggle="modal"
										data-target="#myModalDelete${group.id}">
										<spring:message code="label.delete" />
									</button> <form:form action="group/delete/${group.id}"
										commandName="groupFormBean" class="form-horizontal"
										method="get">
										<!-- Modal -->
										<div class="modal fade" id="myModalDelete${group.id}"
											tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="false">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span><span
																class="sr-only">Close</span>
														</button>
														<h4 class="modal-title" id="myModalLabel">
															<spring:message code="label.delete" />
														</h4>
													</div>
													<div class="modal-body">
														<div class="formGroup">Are you sure?</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">
															<spring:message code="label.close" />
														</button>
														<input type="submit" class="btn btn-primary"
															value="<spring:message code="label.delete"/>" />
													</div>
												</div>
											</div>
										</div>
									</form:form></td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>