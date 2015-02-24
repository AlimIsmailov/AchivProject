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
<!-- Form for add/edit Event to next week sxhedule -->
					<form:form class="form-container" method="post"
						action="schedule/addnext" commandName="event">
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
							<c:out value="${location}" />
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
							<form:label path="day">
								<spring:message code="label.selectday" />
							</form:label>
							<form:radiobutton path="day" value="1" checked="true" />
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
			
			<form:select path="event.group.user" items="${userList}"
								itemValue="name" />
							<c:out value="${user.firstName} ${user.lastName}" />

<!-- Week navigation buttons -->
			<table>
				<tr>
					<td><div class="submit-container">
							<input class="submit-button" type="submit"
								value="<spring:message code="label.previousweek"/>" />
						</div></td>
				</tr>
			</table>

<!-- Schedule Table -->
			<TABLE border="1">
				<TBODY>
					<TR>
						<TH align="center" valign="middle" width="80"></TH>
						<TH align="center" valign="middle">Monday</TH>
						<TH align="center" valign="middle">Tuesday</TH>
						<TH align="center" valign="middle">Wednesday</TH>
						<TH align="center" valign="middle">Thursday</TH>
						<TH align="center" valign="middle">Friday</TH>
					</TR>
					<c:forEach begin="8" end="18" step="1" var="time">
						<TR>
							<TD align="center" valign="middle"><c:out value="${time}" />:00</TD>
							<c:forEach begin="1" end="5" step="1" var="day">
								<TD align="center" valign="middle" width="800" height="80"><c:forEach
										items="${nextEventList}" var="event">
										<c:if
											test="${event.startTime <= time
													&& event.endTime > time
													&& event.day == day}">
											<c:out value="${event.location.name}" />
											<p>
												<security:authorize
													access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
													<c:out value="${event.group.groupName}" />
													<c:out value="${event.group.direction.name}" />
													<p>
														<a href="schedule/edit/${event.id}"
															class="btn btn-default" data-toggle="modal"
															data-target="#myModal2"
															onclick="schedule/edit/${event.id}"><spring:message
																code="label.edit" /></a><a
															href="schedule/delete/${event.id}" class="btn btn-danger"><spring:message
																code="label.delete" /> </a>
												</security:authorize>
										</c:if>
									</c:forEach></TD>
							</c:forEach>
						</TR>
					</c:forEach>
				</TBODY>
			</TABLE>

			<a href="parsecsv"><spring:message code="label.parsefile" /></a>
			<p>
			<p>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>