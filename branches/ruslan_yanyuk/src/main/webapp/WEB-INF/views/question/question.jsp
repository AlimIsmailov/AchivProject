<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<a href="newquestion"><spring:message code="label.add" /></a>
			<c:if test="${!empty questionList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.name" /></th>
						<th><spring:message code="label.subtopic" /></th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						
					</tr>
					<c:forEach items="${questionList}" var="question">
						<tr>
							<td><a href="question/answers/${question.id}">${question.name}</a></td>
							<%-- <td>${question.name}</td> --%>
							<td>${question.subtopic.name}</td>
							<td><a href="question/edit/${question.id}"><spring:message
										code="label.edit" /></a></td>
							<td><a href="question/delete/${question.id}"> <spring:message
										code="label.delete" /></a></td>

						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>
