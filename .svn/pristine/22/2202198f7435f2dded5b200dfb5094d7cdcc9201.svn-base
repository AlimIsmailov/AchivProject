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

				<div class="body">

<!-- Week navigation buttons -->
					<table>
						<tr>
							<td align="right"><div class="submit-container">
									<input class="submit-button" type="submit"
										value="<spring:message code="label.nextweek"/>" />
								</div></td>
						</tr>
					</table>
				</div>
			</security:authorize>

<!-- Previous week schedule Table -->
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
										items="${previousEventList}" var="event">
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
														<a href="schedule/delete/${event.id}"
															class="btn btn-danger"><spring:message
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