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

		<div class="body">
			<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">

				<h2>
					<spring:message code="label.schedule" />
				</h2>

				<!-- Form for add/edit Event -->
				<form:form class="form-container" method="post"
					action="editevent" commandName="editableevent">
					<div class="form-title">
						<h3>
							<spring:message code="label.event" />
						</h3>
					</div>

					<div class="form-title">
						<form:label path="group">
							<spring:message code="label.selectgroup" />
						</form:label>
						<form:select path="group" items="${groupList}"
							itemValue="groupName" />
						<c:out value="${group}" />
						<form:label path="location">
							<spring:message code="label.selectlocation" />
						</form:label>
						<form:select path="location" items="${locationList}"
							itemValue="name" />
						<form:label path="startTime">
							<spring:message code="label.selecttime" />
						</form:label>
						<form:select path="startTime">
							<c:forEach var="i" begin="8" end="17">
								<form:option value="${i}">${i}:00</form:option>
							</c:forEach>
						</form:select>
						<form:select path="endTime">
							<c:forEach var="i" begin="9" end="18">
								<form:option value="${i}">${i}:00</form:option>
							</c:forEach>
						</form:select>
						<p>
							<form:label path="day">
								<spring:message code="label.selectday" />
							</form:label>
							<form:radiobutton path="day" value="1" />
							Monday
							<form:radiobutton path="day" value="2" />
							Thuesday
							<form:radiobutton path="day" value="3" />
							Wednesday
							<form:radiobutton path="day" value="4" />
							Thursday
							<form:radiobutton path="day" value="5" />
							Friday
					</div>
					<div class="submit-container">
						<input class="submit-button" type="submit"
							value="<spring:message code="label.add"/>" />
					</div>
				</form:form>
			</security:authorize>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>