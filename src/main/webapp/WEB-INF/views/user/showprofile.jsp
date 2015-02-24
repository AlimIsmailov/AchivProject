<%@ include file="../../template/default/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<c:set var="accessFlag"
			value="${login eq authUserLogin ? true : false}" scope="page" />

		<div class="container">
			<div class="row">
				<security:authorize access="${accessFlag}">
					<div class="col-md-5  toppad  pull-right col-md-offset-3 ">
						<a href="<c:url value="/profile/edit/${login}"/>"><spring:message
								code="label.edit.profile" /></a> <br>

					</div>
				</security:authorize>
				<div
					class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">${user.firstName}&nbsp;${user.lastName}</h3>

						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3 col-lg-3 " align="center">
									<img alt="avatar"
										src="<c:url value="/profile/showImage/${user.login}"/>"
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
												<td><spring:message code="label.created" />:</td>
												<td><fmt:formatDate value="${user.created}"
														dateStyle="LONG" /></td>
											</tr>
											
											<tr>
												<td><spring:message code="label.email" /></td>
												<td>${user.email}</td>
											</tr>

											<tr>
												<td><spring:message code="label.role" /></td>
												<td>${user.role}</td>
											</tr>

											<tr>
												<td><spring:message code="label.group" />:</td>
												<td><c:choose>
														<c:when test="${fn:length(user.activeGroups) eq 0}">
															<spring:message code="label.noCurrentGroups" />
														</c:when>
														<c:otherwise>
															<c:forEach items="${user.activeGroups}" varStatus="i">
																<a
																	href="<c:url value="/group/${user.activeGroups[i.index].id}" />">${currentGroups[i.index].groupName}</a>
															</c:forEach>
														</c:otherwise>
													</c:choose></td>
											</tr>

										</tbody>
									</table>
									<security:authorize access="${accessFlag}">

										<a href="<c:url value="/groups"/>" class="btn btn-primary">
											<spring:message code="label.myGroups" />
										</a>
										<a href="<c:url value="/testing/my-tests"/>"
											class="btn btn-primary"> <spring:message
												code="label.testing" />
										</a>

									</security:authorize>

								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>