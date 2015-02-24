<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<form:form class="form-horizontal" method="post" action="addTest"
				modelAttribute="quizResultFormBean" cssClass="form-fix">
				<c:if test="${param.success eq true}">
					<div class="alert alert-success">Generated successfull!</div>
				</c:if>
				<c:if test="${param.error eq  'empty-group'}">
					<div class="alert alert-danger">Group is empty!</div>
				</c:if>
				<c:if test="${param.error eq  'not-enought-elements'}">
					<div class="alert alert-danger">Not enought of questions in this subtopic</div>
				</c:if>
				<div class="form-title">
					<h2>Generate new test</h2>
				</div>
				<div class="form-title">
					<form:label path="quizResult">
						Test name
					</form:label>
					<form:input class="form-control" path="quizResult.name" />
					<br>
					<form:label path="group">
						Group
					</form:label>
					<form:select path="group" class="form-control">
						<c:forEach items="${groupList}" var="groups">
							<form:option value="${groups.groupName}"></form:option>
						</c:forEach>
					</form:select>
					<br>
					<form:label path="subtopic">
						SubTopic
					</form:label>
					<form:select path="subtopic" class="form-control">
						<c:forEach items="${subtopicList}" var="subtopics">
							<form:option value="${subtopics.name}"></form:option>
						</c:forEach>
					</form:select>
					<br>
					<form:label path="countOfQuestions">
						Count of questions
					</form:label>
					<form:select path="countOfQuestions" class="form-control">
						<form:option value="10"></form:option>
						<form:option value="15"></form:option>
						<form:option value="20"></form:option>
					</form:select>
				</div>
				<div class="submit-container">
					<input class="btn btn-lg btn-primary" type="submit"
						value="Generate" />
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>