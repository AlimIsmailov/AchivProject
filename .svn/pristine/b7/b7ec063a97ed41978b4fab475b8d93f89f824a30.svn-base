<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-md-4 col-md-offset-4">
					<h1 class="text-center login-title">
						<spring:message code="label.pleasesignin" />
					</h1>
					<c:if test="${error}">
						<p style="color: red">
							<spring:message code="label.loginpasincor" />
						</p>
					</c:if>
					<div class="account-wall">
						<img class="profile-img"
							src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
							alt="">
						<form class="form-signin" role="form"
							action='${pageContext.request.contextPath}/j_spring_security_check'
							method='POST'>
							<input type="text" class="form-control"
								placeholder="<spring:message code="label.login"/>" required
								autofocus name="j_username"> <input type="password"
								class="form-control" name='j_password'
								placeholder="<spring:message code="label.password"/>" required>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								<spring:message code="label.signin" />
							</button>
							<label class="checkbox pull-left"> <input type="checkbox"
								value="remember-me"> <spring:message
									code="label.remember" />
							</label> <a href="<c:url value="/forgotPassword"/>"
								class="pull-right need-help"><spring:message
									code="label.forgotPassword" /> </a><span class="clearfix"></span>
						</form>
					</div>
					<a href='<c:url value = "/register"/>'
						class="text-center new-account"><spring:message
							code="label.signup" /> </a>
				</div>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>