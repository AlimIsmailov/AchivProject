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
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Grade</th>
						<th>Pass/Passed</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${quizResultList}" var="result">
						<tr>
							<td><c:if test="${result.passed == true }">
									<form:form action="my-tests" commandName="quizResult"
										method="post">
										<form:input type="hidden" path="id" value="${result.id}" />
										<input class="btn btn-link" type="submit"
											value="${result.name}" />
									</form:form>
								</c:if> <c:if test="${result.passed == false }">
								${result.name}
							</c:if></td>
							<td><c:if test="${result.totalGrade == null}">Not passed</c:if>
								<c:if test="${result.totalGrade != null}">${result.totalGrade}</c:if>
							<td><c:if test="${result.passed == true }">
									<button type="button" class="btn btn-primary"
										disabled="disabled">Passed</button>
								</c:if> <c:if test="${result.passed == false }">
									<form:form action="pass-the-test" commandName="quizResult"
										method="post">
										<form:input type="hidden" path="id" value="${result.id}" />
										<input class="btn btn-primary" type="submit"
											value="Pass the Test" />
									</form:form>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>