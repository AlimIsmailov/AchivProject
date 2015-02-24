<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<br>
			<form:form class="form-horizontal" method="post" action="addTest"
				modelAttribute="testFormBean" cssClass="form-fix">
				<c:if test="${success eq true}">
					<div class="alert alert-success">Generated successful!</div>
				</c:if>
				<c:if test="${error eq  'empty-group'}">
					<div class="alert alert-danger">Group is empty!</div>
				</c:if>
				<c:if test="${error eq  'not-enought-elements'}">
					<div class="alert alert-danger">Not enough of questions in
						this subtopic</div>
				</c:if>
				<div class="form">
					<h2>Generate new test</h2>
				</div>
				<div class="form">
					<form:label path="quizName">
						Test name
					</form:label>
					<form:input class="form-control" path="quizName" />
					<form:errors path="quizName" style="color: red" />
					<br>
					<form:label path="groupName">
						Group
					</form:label>
					<form:select path="groupName" class="form-control">
						<c:forEach items="${groupList}" var="group">
							<form:option value="${group.groupName}"></form:option>
						</c:forEach>
					</form:select>
					<br>
					<form:label path="subtopicName">
						SubTopic
					</form:label>
					<form:select path="subtopicName" class="form-control">
						<c:forEach items="${subtopicList}" var="subtopic">
							<form:option value="${subtopic.name}"></form:option>
						</c:forEach>
					</form:select>
					<br>
					<form:label path="countOfQuestions">
						Count of questions
					</form:label>
					<form:select path="countOfQuestions" class="form-control">
						<c:forEach items="${counts}" var="count">
							<form:option value="${count}"></form:option>
						</c:forEach>
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