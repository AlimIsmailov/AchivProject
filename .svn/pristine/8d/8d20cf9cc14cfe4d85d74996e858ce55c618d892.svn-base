<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<form:form method="put" commandName="password">
			
			<div class="form-group">
				<form:label path="password" cssClass="col-md-3 control-label">
					<spring:message code="label.password" />:</form:label>
				<div class="col-md-8">
					<form:errors path="password" cssClass="error" />
					<form:password path="password" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="confirmPassword" cssClass="col-md-3 control-label">
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
						value="<spring:message code="label.savechanges"/>"> <span></span>
					<input type="reset" class="btn btn-default"
						value="<spring:message code="label.cancel"/>">
				</div>
			</div>
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>