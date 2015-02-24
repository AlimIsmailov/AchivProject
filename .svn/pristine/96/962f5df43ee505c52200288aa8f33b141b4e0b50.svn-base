<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
<script type="text/javascript">
$(function() {
	  var json, tabsState;
	  $('a[data-toggle="pill"], a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
	    var href, json, parentId, tabsState;

	    tabsState = localStorage.getItem("tabs-state");
	    json = JSON.parse(tabsState || "{}");
	    parentId = $(e.target).parents("ul.nav.nav-pills, ul.nav.nav-tabs").attr("id");
	    href = $(e.target).attr('href');
	    json[parentId] = href;

	    return localStorage.setItem("tabs-state", JSON.stringify(json));
	  });

	  tabsState = localStorage.getItem("tabs-state");
	  json = JSON.parse(tabsState || "{}");

	  $.each(json, function(containerId, href) {
	    return $("#" + containerId + " a[href=" + href + "]").tab('show');
	  });

	  $("ul.nav.nav-pills, ul.nav.nav-tabs").each(function() {
	    var $this = $(this);
	    if (!json[$this.attr("id")]) {
	      return $this.find("a[data-toggle=tab]:first, a[data-toggle=pill]:first").tab("show");
	    }
	  });
	});
</script>

		<div class="container">
			<h1>
				<spring:message code="label.edit.profile" />
			</h1>
			<hr>
			<div class="row">
			
				<!-- Image Form -->
				<form:form method="put" enctype="multipart/form-data">
				<input type="submit" hidden="true" name="imageForm">
				<div class="col-md-3">
					<div class="text-center">
						<img alt="avatar"
							src="<c:url value="/profile/showImage/${authUserLogin}"/>"
							onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/avatar.gif';"
							class="avatar img-circle" width="100" height="100">

						<h6>
							<spring:message code="label.upload.photo" />
						</h6>
						<input type="file" class="form-control" name="image" onchange="imageForm.click();">
						
					</div>
				</div>
				</form:form>
				
				<!-- edit form column -->
				<div class="col-md-9 personal-info">
					<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">×</a> <i
							class="fa fa-coffee"></i> This is an <strong>.alert</strong>. Use
						this to show important messages to the user.
					</div>
					<h3>
						<spring:message code="label.personal.info" />
					</h3>

					<!-- Tabs -->
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a href="#info" data-toggle="tab">Info</a></li>
						<li><a href="#email" data-toggle="tab">Email</a></li>
						<li><a href="#password" data-toggle="tab">Password</a></li>
					</ul>
					
					<!-- Tab Content -->
					<div class="tab-content">
						<div class="tab-pane active" id="info">
							<!-- Info Form -->
							<form:form method="put" commandName="info">
								<input type="hidden" name="infoForm">
								<div class="form-group">
									<form:label path="firstName" cssClass="col-lg-3 control-label"
										title="${update.firstName}">
										<spring:message code="label.firstname" />:</form:label>
									<div class="col-lg-8">
										<form:errors path="firstName" cssClass="error" />
										<form:input path="firstName" cssClass="form-control" />
									</div>
								</div>

								<div class="form-group">
									<form:label path="lastName" title="${update.lastName}"
										cssClass="col-lg-3 control-label">
										<spring:message code="label.lastname" />:</form:label>

									<div class="col-lg-8">
										<form:errors path="lastName" cssClass="error" />
										<form:input path="lastName" cssClass="form-control" />
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
						</div>

						<div class="tab-pane" id="email">
							<!-- Email Form -->
							<form:form method="put" commandName="email">
								<input type="hidden" name="emailForm">
								<div class="form-group">
									<form:label path="email" title="${email.email}"
										cssClass="col-lg-3 control-label">
										<spring:message code="label.email" />:</form:label>
									<div class="col-lg-8">
										<form:errors path="email" cssClass="error"/>
										<form:input path="email" cssClass="form-control" />
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
						</div>
						<div class="tab-pane" id="password">
							<!-- Password Form -->
							<form:form method="put" commandName="password">
								<input type="hidden" name="passwordForm">
								<div class="form-group">
									<form:label path="password"
										cssClass="col-md-3 control-label">
										<spring:message code="label.password" />:</form:label>
									<div class="col-md-8">
										<form:errors path="password" cssClass="error" />
										<form:password path="password" cssClass="form-control" />
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
											value="<spring:message code="label.savechanges"/>"> <span></span>
										<input type="reset" class="btn btn-default"
											value="<spring:message code="label.cancel"/>">
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>