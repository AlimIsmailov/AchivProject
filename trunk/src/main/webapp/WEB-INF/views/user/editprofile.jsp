<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="container">
			<h1>
				<spring:message code="label.edit.profile" />
			</h1>
			<hr>
			<form:form method="put" modelAttribute="userForm"
				enctype="multipart/form-data" id="form">
				<div class="row">
					<!-- left column -->
					<div class="col-md-3">
						<div class="text-center">
							<img alt="avatar"
								src="<c:url value="/users/profile/showImage/${login}"/>"
								onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/avatar.gif';"
								class="avatar img-circle" width="100" height="100" >

							<h6>
								<spring:message code="label.upload.photo" />
							</h6>
							<input type="file" class="form-control" name="image" >
						</div>
					</div>
					<!-- edit form column -->
					<div class="col-md-9 personal-info">
						<div class="alert alert-info alert-dismissable">
							<a class="panel-close close" data-dismiss="alert">�</a> <i
								class="fa fa-coffee"></i> This is an <strong>.alert</strong>.
							Use this to show important messages to the user.
						</div>
						<h3>
							<spring:message code="label.personal.info" />
						</h3>
						
						<security:authorize access="hasRole('ROLE_ADMIN')">
						<div class="form-group">
							<form:label path="user.login"
								cssClass="col-lg-3 control-label" title="${login}">
								<spring:message code="label.login" />:</form:label>
							<div class="col-lg-8">
								<form:errors path="user.login" cssClass="error" />
								<form:input path="user.login" cssClass="form-control" />
							</div>
						</div>
						</security:authorize>
						
						<div class="form-group">
							<form:label path="user.firstName"
								cssClass="col-lg-3 control-label" title="${firstName}">
								<spring:message code="label.firstname" />:</form:label>
							<div class="col-lg-8">
								<form:errors path="user.firstName" cssClass="error" />
								<form:input path="user.firstName" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<form:label path="user.lastName" title="${lastName}"
								cssClass="col-lg-3 control-label">
								<spring:message code="label.lastname" />:</form:label>

							<div class="col-lg-8">
								<form:errors path="user.lastName" cssClass="error" />
								<form:input path="user.lastName" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<form:label path="user.email" title="${email}"
								cssClass="col-lg-3 control-label">
								<spring:message code="label.email" />:</form:label>
							<div class="col-lg-8">
								<form:errors path="user.email" cssClass="error" />
								<form:input path="user.email" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="user.password"
								cssClass="col-md-3 control-label">
								<spring:message code="label.password" />:</form:label>
							<div class="col-md-8">
								<form:errors path="user.password" cssClass="error" />
								<form:password path="user.password" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="confirmPassword"
								cssClass="col-md-3 control-label">
								<spring:message code="label.confirmPassword" />:</form:label>
							<div class="col-md-8">
								<form:errors path="confirmPassword" cssClass="error" />
								<form:password path="confirmPassword" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
								<input type="submit" class="btn btn-primary"
									value="Save Changes"> <span></span> <input type="reset"
									class="btn btn-default" value="Cancel">
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<hr>
	</tiles:putAttribute>
</tiles:insertDefinition>

