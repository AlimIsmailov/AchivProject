<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<form:form method="POST" action="create"
			modelAttribute="userFormBean" class="form-horizontal"
			cssClass="form-fix">
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<h2 align="center"><spring:message code="label.signup" /></h2>
					<label><spring:message code="label.login" /></label>
					<form:errors path="user.login" style="color: red" />
					<form:input type="text" title="You login" path="user.login"
						class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
				<form:errors path="user.firstName" style="color: red" />
					<label><spring:message code="label.firstname" /></label>
					<form:input type="text" title="You first name" path="user.firstName"
						class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
				<form:errors path="user.lastName" style="color: red" />
					<label><spring:message code="label.lastname" /></label>
					<form:input type="text" title="You last name" path="user.lastName"
						class="form-control"  />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<label><spring:message code="label.password" /></label>
					<form:errors path="user.password" style="color: red" />
					<form:input type="password" title="You password" path="user.password"
						class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<label><spring:message code="label.confirmPassword" /></label>
					<form:input type="password" title="You password"
						path="confirmPassword" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<label><spring:message code="label.email" /></label>
					<form:errors path="user.email" style="color: red" />
					<form:input type="text" title="E-mail" path="user.email"
						class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<button type="submit" class="btn btn-lg btn-primary btn-block">
						<spring:message code="label.signup" />
					</button>
					<input class="btn btn-lg btn-primary btn-block" type="reset" value="Reset" />
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>