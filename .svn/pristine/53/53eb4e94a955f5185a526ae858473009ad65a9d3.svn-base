<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<p><spring:message code="label.subtopic" />
		: <strong> ${subtopic.name}</strong> </p>
		<c:if test="${!empty questionList}">
			<spring:message code="label.questions" />
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th><spring:message code="label.name" /></th>
					<th><spring:message code="label.isCorrect" /></th>
				</tr>
				<c:forEach items="${questionList}" var="question">
					<tr>
						<td><a href="question/answers/${question.id}">${question.name}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>