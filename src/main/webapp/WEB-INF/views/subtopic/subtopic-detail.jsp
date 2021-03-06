<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>

<script type="text/javascript" src="bootstrap-filestyle.js">
	
</script>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<h2>${subtopic.name}</h2>

		<br />

		<c:if test="${exists eq true}">
			<div class="alert alert-danger">
				<spring:message code="label.file.exists" />
			</div>
		</c:if>

		<c:if test="${mistakes eq true}">
			<div class="alert alert-danger">
				<spring:message code="label.article.mistakes" />
			</div>
		</c:if>

		<c:if test="${upload eq false}">
			<div class="alert alert-danger">
				<spring:message code="label.file.format" />
			</div>
		</c:if>
		<c:if test="${server eq false}">
			<div class="alert alert-danger">
				<spring:message code="label.file.server" />
			</div>
		</c:if>
		<c:if test="${rename eq false}">
			<div class="alert alert-danger">
				<spring:message code="label.file.rename" />
			</div>
		</c:if>

		<br />

		<security:authorize access="hasRole('ROLE_MANAGER')">
			<!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal">
				<spring:message code="label.article.add" />
			</button>

			<form:form commandName="articleFormBean" method="POST"
				action="${subtopic.id}/uploadFile" enctype="multipart/form-data"
				cssClass="form-horizontal articleForm">
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span> <span class="sr-only"><spring:message
											code="label.close" /></span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="label.add" />
								</h4>
							</div>
							<div class="modal-body">
								<div class="formGroup">
									<label for="title" class="col-sm-2 control-label"><spring:message
											code="label.title" />: </label>
									<div class="col-sm-10">
										<form:input path="title" cssClass="form-control" />
										<form:errors path="title" style="color: red" />
									</div>
								</div>
								<div class="formGroup">
									<label for="description" class="col-sm-2 control-label">
										<spring:message code="label.description" />:
									</label>
									<div class="col-sm-10">
										<form:input path="description" cssClass="form-control" />
										<form:errors path="description" style="color: red" />
									</div>
								</div>
								<div class="formGroup">
									<label for="file" class="col-sm-2 control-label"><spring:message
											code="label.file" />: </label>
									<div class="col-sm-10">
										<form:input id="filestyle-2" class="filestyle" type="file"
											path="file" data-buttontext="Find file"
											style="position: absolute; clip: rect(0px, 0px, 0px, 0px);"
											tabindex="-1" />
										<div class="bootstrap-filestyle input-group">
											<span class="group-span-filestyle input-group-btn"
												tabindex="0"> <label class="btn btn-primary "
												for="filestyle-2"><spring:message
														code="label.upload" /></label>
											</span>
										</div>
										<div class="col-sm-10">
											<p class="help-block" style="text-align: center">
												<spring:message code="label.article.info" />
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<spring:message code="label.cancel" />
								</button>
								<input type="submit" class="btn btn-primary"
									value='<spring:message code="label.save" />' />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</security:authorize>

		<br />

		<h3>
			<spring:message code="label.usefulresources" />
			:
		</h3>
		<br />
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th><spring:message code="label.title" /></th>
					<th><spring:message code="label.description" /></th>
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
							<td>
								<button class="btn default" data-toggle="modal"
									data-target="#myModalEdit${article.id}">
									<spring:message code="label.edit" />
								</button> <form:form action="${subtopic.id}/edit/${article.id}"
									class="form-horizontal articleFormEdit" method="POST"
									commandName="articleFormBean">
									<!--Delete Pop up Modal -->
									<div class="modal fade" id="myModalEdit${article.id}"
										tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="false">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only"><spring:message code="label.close" /></span>
													</button>
													<h4 class="modal-title" id="myModalLabel">
														<spring:message code="label.edit" />
													</h4>
												</div>
												<div class="modal-body">
													<div class="formGroup">
														<label for="title" class="col-sm-2 control-label"><spring:message
																code="label.title" />:</label>
														<div class="col-sm-10">
															<form:input id="title" path="title"
																value="${article.title}" cssClass="form-control" />
															<form:errors path="title" />
														</div>
														<label for="description" class="col-sm-2 control-label">
															<spring:message code="label.description" />:
														</label>
														<div class="col-sm-10">
															<form:input path="description" cssClass="form-control"
																value="${article.description}" />
															<form:errors path="description" />
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">
														<spring:message code="label.cancel" />
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
									data-target="#myModalDelete${article.id}">
									<spring:message code="label.delete" />
								</button> <form:form action="${subtopic.id}/delete/${article.id}"
									class="form-horizontal" method="get">
									<!--Delete Pop up Modal -->
									<div class="modal fade" id="myModalDelete${article.id}"
										tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="false">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">
														<span aria-hidden="true">&times;</span><span
															class="sr-only"><spring:message code="label.close" /></span>
													</button>
													<h4 class="modal-title" id="myModalLabel">
														<spring:message code="label.delete" />
													</h4>
												</div>
												<div class="modal-body">
													<div class="formGroup">
														<spring:message code="label.article.warn" />${article.title}?</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">
														<spring:message code="label.cancel" />
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
			</tbody>
		</table>

		<c:if test="${!empty subtopic.questions}">
			<spring:message code="label.questions" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
				</tr>
				<c:forEach items="${subtopic.questions}" var="question">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/question/answers/${question.id}">${question.name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<script type="text/javascript">
			$(document)
					.ready(
							function() {
								$(".articleForm")
										.validate(
												{
													rules : {
														title : {
															required : true,
															minlength : 1,
															maxlength : 10
														},
														description : {
															required : true,
															minlength : 10,
															maxlength : 255
														},
														file : {
															required : true,
															accept : "application/pdf, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation"
														},
													},
													messages : {
														title : "This field is required and must have 1-10 characters",
														description : "This field is required and must have at least 10 characters",
														file : "This field is required"
													}
												});
							});
			
			$(document)
			.ready(
					function() {
						$(".articleFormEdit")
								.validate(
										{
											rules : {
												title : {
													required : true,
													minlength : 1,
													maxlength : 10
												},
												description : {
													required : true,
													minlength : 10,
													maxlength : 255
												},
											},
											unhighlight : function(
													element) {
												$(element)
														.closest(
																'.formGroup')
														.removeClass(
																'has-error')
														.addClass(
																'has-success');
											},
											messages : {
												title : "This field is required and must have 1-10 characters",
												description : "This field is required and must have at least 10 characters",
											}
										});
					});
		</script>

	</tiles:putAttribute>
</tiles:insertDefinition>