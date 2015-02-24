<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<div>
				<h1 align="center">
					<spring:message code="label.testing" />
				</h1>
			</div>
			<%-- <c:if test="${empty quizResult}"><h2>You dont have Quiz Result</h2></c:if> --%>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Grade</th>
						<th>Pass/Passed</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${quizResult}" var="result">
						<tr>
							<td><c:if test="${result.passed == true }">
									<a href="<spring:url value="/testing/my-tests/${result.id}" />">${result.name}</a>
								</c:if> <c:if test="${result.passed == false }">
								${result.name}
							</c:if></td>
							<td>${result.totalGrade}</td>

							<td><c:if test="${result.passed == true }">
									<button type="button" class="btn btn-primary"
										disabled="disabled">Passed</button>
								</c:if> <c:if test="${result.passed == false }">
									<a href='<spring:url value="/testing/pass-the-test"/>'
										class="btn btn-primary ">Pass the Test</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>