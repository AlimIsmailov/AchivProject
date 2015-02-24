<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!Doctype html>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<!-- Topic exists -->
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Topic added (edited)
				successfully!</div>
		</c:if>
		<c:if test="${param.fail eq true}">
			<div class="alert alert-danger">This topic already exists!</div>
		</c:if>
		<!-- Add topic -->
		<security:authorize access="hasRole('ROLE_MANAGER')">
			<!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal">
				<spring:message code="label.add" />
			</button>

			<form:form action="topics/addTopic" commandName="topic"
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
									<spring:message code="label.newtopic" />
								</h4>
							</div>
							<div class="modal-body">
								<div class="formGroup">
									<label for="topicName" class="col-sm-2 control-label">
										<spring:message code="label.name" />
									</label>
									<div class="col-sm-10">
										<form:input path="topicName" cssClass="form-control" />
										<form:errors path="topicName" />

										<form:checkboxes items="${directionList}" path="directions"
											element="span class='checkbox'" itemLabel="name"
											itemValue="name" />
										<form:errors path="directions" />

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

		<br>
		<div class="body">

			<!-- List of topics -->
			<c:if test="${!empty topicList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.topics" /></th>
						<security:authorize access="hasRole('ROLE_MANAGER')">
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</security:authorize>
					</tr>
					<c:forEach items="${topicList}" var="topic">
						<tr>
							<td><a href="<spring:url value="/topics/${topic.id}" />"><c:out
										value="${topic.topicName}" /></a></td>
							<security:authorize access="hasRole('ROLE_MANAGER')">
								<td>
									<button class="btn btn-primary btn-sm" data-toggle="modal"
										data-target="#myModal${topic.id}">
										<spring:message code="label.edit" />
									</button> <form:form action="topics/editTopic" commandName="topic"
										class="form-horizontal" method="post">
										<!-- Modal -->
										<div class="modal fade" id="myModal${topic.id}" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="false">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span><span
																class="sr-only">Close</span>
														</button>
														<h4 class="modal-title" id="myModalLabel">
															<spring:message code="label.edittopic" />
														</h4>
													</div>
													<div class="modal-body">
														<div class="formGroup">
															<form:input type="hidden" path="id" value="${topic.id}" />
															<label for="topicName" class="col-sm-2 control-label">
																<spring:message code="label.name" />
															</label>
															<div class="col-sm-10">
																<form:input path="topicName" cssClass="form-control"
																	value="${topic.topicName}" />
																<form:errors path="topicName" />

																<form:checkboxes items="${directionList}"
																	path="directions" element="span class='checkbox'"
																	itemLabel="name" itemValue="name" />
																<form:errors path="directions" />


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
								<td>
									<button class="btn btn-danger" data-toggle="modal"
										data-target="#myModalDelete${topic.id}">
										<spring:message code="label.delete" />
									</button> <form:form action="topic/delete/${topic.id}"
										commandName="topic" class="form-horizontal" method="get">
										<!-- Modal -->
										<div class="modal fade" id="myModalDelete${topic.id}"
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
														<div class="formGroup">Are you sure? There may be
															subtopics!</div>
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
									</form:form>
								</td>

							</security:authorize>
						</tr>
					</c:forEach>
				</table>
			</c:if>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<a href="newtopic"><spring:message code="label.add" /></a>
			<c:if test="${!empty topicList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.topic" /></th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${topicList}" var="topic">
						<tr>
							<td><a
								href="<spring:url value="/topics/${topic.id}" />"><c:out
										value="${topic.topicName}" /></a></td>
							<td><a href="topic/edit/${topic.id}"><spring:message
										code="label.edit" /></a></td>
							<td><a href="topic/delete/${topic.id}"> <spring:message
										code="label.delete" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition> --%>