<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.username"
		var="authUserLogin" scope="session" />
</security:authorize>

<div class="container">
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="<spring:url value="/home" htmlEscape="true"/>">ITAchiever</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class=""><a
						href="<spring:url value="/home" htmlEscape="true"/>"><spring:message
								code="label.home" /></a></li>
					<li class=""><a
						href="<spring:url value="/about" htmlEscape="true"/>"><spring:message
								code="label.about" /></a></li>

					<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a
							href="<spring:url value="/userManagement" htmlEscape="true"/>"><spring:message
									code="label.userManagement" /></a></li>
					
						<li><a
							href="<spring:url value="/useractivation" htmlEscape="true"/>"><spring:message
									code="label.useractivation" /></a></li>

						<li class=""><a
							href="<spring:url value="/users" htmlEscape="true"/>"><spring:message
									code="label.users" /></a></li>
					</security:authorize>

					<li class=""><a
						href="<spring:url value="/directions" htmlEscape="true"/>"><spring:message
								code="label.directions" /></a></li>

					<security:authorize access="isAuthenticated()">
						<li class=""><a
							href="<spring:url value="/groups" htmlEscape="true"/>"><spring:message
									code="label.groups" /></a></li>
					</security:authorize>


					<security:authorize access="hasRole('ROLE_MANAGER')">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><spring:message code="label.testing" /><span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a
									href="<spring:url value="/testing/generate" htmlEscape="true"/>"><spring:message
											code="label.generate-tests" /></a></li>
								<li class="divider"></li>
								<li><a
									href="<spring:url value="/topics" htmlEscape="true"/>"><spring:message
											code="label.topics" /></a></li>
								<li><a
									href="<spring:url value="/subtopics/all" htmlEscape="true"/>"><spring:message
											code="label.subtopics" /></a></li>
								<li><a
									href="<spring:url value="/questions" htmlEscape="true"/>"><spring:message
											code="label.questions" /></a></li>
							</ul></li>

						<li class=""><a
							href="<spring:url value="/applications" htmlEscape="true"/>"><spring:message
									code="label.applications" /></a></li>

					</security:authorize>

					<security:authorize access="hasRole('ROLE_STUDENT')">
						<li><a
							href="<spring:url value="/testing/my-tests" htmlEscape="true"/>"><spring:message
									code="label.testing" /></a></li>
					</security:authorize>

					<security:authorize access="! isAuthenticated()">
						<li class=""><a
							href="<spring:url value="/login" htmlEscape="true"/>"><spring:message
									code="label.signin" /></a></li>
						<li class=""><a
							href="<spring:url value="/register" htmlEscape="true"/>"><spring:message
									code="label.signup" /></a></li>
					</security:authorize>

					<security:authorize access="isAuthenticated()">
						<!-- Add Schedule in menu -->
						<li class=""><a
							href="<spring:url value="/schedule" htmlEscape="true"/>">Schedule</a></li>
					</security:authorize>
				</ul>
				<security:authorize access="isAuthenticated()">
					<div class="dropdown-account">
						<div class="dropdown ">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img
								alt="avatar"
								src="<c:url value="/users/profile/showImage/${authUserLogin}"/>"
								onerror="this.onerror=null;this.src='https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100';"
								class="img-circle" width="60" height="50">
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<spring:url value="#" htmlEscape="true"/>"><security:authentication
											property="principal.username" /></a></li>
								<li><a
									href="<spring:url value="/logout" htmlEscape="true"/>"><spring:message
											code="label.signout" /></a></li>
								<li><a
									href="<spring:url value="/users/profile/show/${authUserLogin}" htmlEscape="true"/>"><spring:message
											code="label.profile" /></a></li>
								<!-- <li class="divider"></li> -->
							</ul>
						</div>
					</div>
				</security:authorize>

			</div>
		</div>
	</div>
</div>