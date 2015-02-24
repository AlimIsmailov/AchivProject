<%@ include file="../../template/default/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<c:set var="accessFlag"
			value="${login eq authUserLogin ? true : false}" scope="page" />
		<c:set var="login" value="${userForm.user.login}" scope="session" />
		<c:set var="rolesSize" value="${fn:length(userForm.user.roles)}"
			scope="page"/>
		<c:set var="firstName" value="${userForm.user.firstName}"
			scope="session" target="userForm.user" />
		<c:set var="lastName" value="${userForm.user.lastName}"
			scope="session" target="userForm.user" />
		<c:set var="created" value="${userForm.user.created}" scope="session" />
		<c:set var="email" value="${userForm.user.email}" scope="session"
			target="userForm.user" />

		<div class="container">
			<div class="row">
				<security:authorize access="${accessFlag} or hasRole('ROLE_ADMIN')">
					<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
						<a href="<c:url value="/users/profile/edit/${login}"/>">Edit
							Profile</a> <br>

					</div>
				</security:authorize>
				<div
					class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">${firstName}&nbsp;${lastName}</h3>
							
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3 col-lg-3 " align="center">
									<img alt="avatar"
										src="<c:url value="/users/profile/showImage/${login}"/>"
										onerror="this.onerror=null;this.src='https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=100';"
										class="img-circle" width="100" height="100">
								</div>

								<div class=" col-md-9 col-lg-9 ">
									<table class="table table-user-information">
										<tbody>
											<tr>
												<td><spring:message code="label.login" />:</td>
												<td>${login}</td>
											</tr>
											<tr>
												<td><spring:message code="label.role" />:</td>
												<td><c:choose>
														<c:when test="${rolesSize eq 1}">
															<spring:message code="label.role.user"/>
														</c:when>
														<c:when test="${rolesSize eq 2}">
															<spring:message code="label.role.student"/>
														</c:when>
														<c:when test="${rolesSize eq 3}">
															<spring:message code="label.role.manager"/>
														</c:when>
														<c:otherwise>
															<spring:message code="label.role.admin"/>
														</c:otherwise>
													</c:choose></td>
											</tr>
											<tr>
												<td><spring:message code="label.created" />:</td>
												<td><fmt:formatDate value="${created}" dateStyle="LONG" /></td>
											</tr>
											
											<c:if test="${rolesSize gt 1 and rolesSize lt 4}">
											<tr>
												<td><spring:message code="label.directions" /></td>
												<td>
													<c:choose>
														<c:when test="${fn:length(currentGroups) eq 0}">
															<spring:message code="label.noCurrentDirections"/>
														</c:when>
														<c:otherwise>
															<c:forEach items="${currentGroups}" varStatus="i">
																<c:out value="${currentGroups[i.index].direction.name}" />
															</c:forEach>
														</c:otherwise>	
													</c:choose>
												</td>
											</tr>
											
											<tr>
												<td><spring:message code="label.group" />:</td>
												<td>
													<c:choose>
														<c:when test="${fn:length(currentGroups) eq 0}"> 
															<spring:message code="label.noCurrentGroups"/> 
														</c:when>
														<c:otherwise>
															<c:forEach items="${currentGroups}" varStatus="i">
																<a
																href="<c:url value="/group/${currentGroups[i.index].id}" />">${currentGroups[i.index].groupName}</a>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
											</c:if>
											
											<tr>
												<td><spring:message code="label.email" /></td>
												<td>${email}</td>
											</tr>


										</tbody>
									</table>
									<c:if test="${rolesSize gt 1 and rolesSize lt 4}">
									<security:authorize access="${accessFlag}">

										<a href="<c:url value="/group"/>" class="btn btn-primary">
											<spring:message code="label.myGroups" />
										</a>
										<a href="<c:url value="/testing/my-tests"/>"
											class="btn btn-primary"> <spring:message
												code="label.testing" />
										</a>

									</security:authorize>
									</c:if>
								</div>
							</div>
						</div>
						<security:authorize access="${accessFlag}">
							<div class="panel-footer">
								<a data-original-title="Broadcast Message" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-primary"><i
									class="glyphicon glyphicon-envelope"></i></a> <span
									class="pull-right"> <a
									href="<c:url value="/user/profile/edit"/>"
									data-original-title="Edit this user" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-warning"><i
										class="glyphicon glyphicon-edit"></i> </a> <a
									data-original-title="Remove this user" data-toggle="tooltip"
									type="button" class="btn btn-sm btn-danger"><i
										class="glyphicon glyphicon-remove"></i></a>
								</span>
							</div>
						</security:authorize>
					</div>
				</div>
			</div>
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>