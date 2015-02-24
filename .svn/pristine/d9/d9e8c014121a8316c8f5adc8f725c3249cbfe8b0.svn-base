<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<h2>${quiz.name}</h2>
		<div class="body">
			<form:form class="form-horizontal" method="post" action="finish"
				modelAttribute="quiz" cssClass="form-fix">
				<c:forEach items="${quiz.userAnswers}" var="test">
					<h3>${test.question.name}</h3>
					<br>
					<form:checkboxes items="${test.question.answers}" path="${answers}"
						element="span class='checkbox'" itemLabel="name" itemValue="name" />
					<br>
				</c:forEach>
				<div class="submit-container">
					<input class="btn btn-lg btn-primary" type="submit"
						value="Finish test" />
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
