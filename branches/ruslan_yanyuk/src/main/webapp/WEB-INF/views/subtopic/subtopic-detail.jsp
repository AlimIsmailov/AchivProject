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

		<script type="text/javascript">
			$(document).ready(
					function() {
						$("#triggerRemove").click(function(c) {
							console.log("log");
							e.preventDefault();
							console.log("log");
							$("#modalRemove #removeBtn").attr("href", $(this).attr("href"));
							console.log("log");
							$("#modalRemove").modal("show");
							console.log("log");
						});
					});
		</script>

		<h2>${subtopic.name}</h2>

		<br />
		
		<c:if  test="${param.fail eq true}">
			<div class="alert alert-success">Creating file failed!!</div>
		</c:if>

		<security:authorize access="hasRole('ROLE_MANAGER')">
			<!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal">Add Article</button>

			<form:form commandName="article" action="${subtopic.id}/uploadFile"
				enctype="multipart/form-data" class="form-horizontal">
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Add Article</h4>
							</div>
							<div class="modal-body">
								<div class="formGroup">
									<label for="title" class="col-sm-2 control-label">Title:</label>
									<div class="col-sm-10">
										<form:input path="title" cssClass="form-control" />
										<form:errors path="title" />
									</div>
									<label for="description" class="col-sm-2 control-label">
										Description:</label>
									<div class="col-sm-10">
										<form:input path="description" cssClass="form-control" />
										<form:errors path="description" />
									</div>
									<div class="form-group">
										<label for="file" class="col-sm-2 control-label">File
											input: </label>
										<form:input type="file" path="file" />
										<p class="help-block">You can only download files of .pdf
											extension</p>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<input type="submit" class="btn btn-primary" value="Save" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</security:authorize>

		<br />

		<h3>Useful resources:</h3>
		<br />
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${subtopic.articles}" var="article">
					<tr>
						<td><a
							href="<spring:url value="${subtopic.id}/download/${article.id}" />">
								<c:out value="${article.title}" />
						</a></td>
						<td>${article.description}</td>
						<security:authorize access="hasRole('ROLE_MANAGER')">
							<td><a
								href="<spring:url value="${subtopic.id}/delete/${article.id}" />"
								class="btn btn-danger triggerRemove" id="triggerRemove"><spring:message
										code="label.delete" /> </a></td>
							<td><a
								href="<spring:url value="${subtopic.id}/edit/${article.id}" />"
								class="btn btn-default"><spring:message code="label.edit" />
							</a></td>
						</security:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!--Removing Confirmation Modal -->
		<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<spring:message code="label.delete" />
						</h4>
					</div>
					<div class="modal-body">Do you really want to delete this
						item</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<a href="" class="btn btn-danger removeBtn" id="removeBtn"><spring:message
								code="label.delete" /></a>
					</div>
				</div>
			</div>
		</div>


		<c:if test="${!empty subtopic.questions}">
			<spring:message code="label.questions" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
				</tr>
				<c:forEach items="${subtopic.questions}" var="question">
					<tr>
						<td><a href="/achivproject/question/answers/${question.id}">${question.name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>