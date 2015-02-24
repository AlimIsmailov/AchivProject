<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<a href="newsubtopic"><spring:message code="label.add" /></a>
			<c:if test="${!empty subtopicList}">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th><spring:message code="label.subtopic" /></th>
						<th><spring:message code="label.topic" /></th>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${subtopicList}" var="subtopic">
						<tr>
							<td><a
								href="<spring:url value="/subtopics/${subtopic.id}" />"><c:out
										value="${subtopic.name}" /></a></td>
							<td>${subtopic.topic.topicName}</td>
							<td><a href="subtopic/edit/${subtopic.id}"><spring:message
										code="label.edit" /></a></td>
							<td><a href="subtopic/delete/${subtopic.id}"> <spring:message
										code="label.delete" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>



	</tiles:putAttribute>
</tiles:insertDefinition>