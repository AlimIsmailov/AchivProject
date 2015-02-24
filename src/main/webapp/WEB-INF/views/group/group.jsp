<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#tableId')
								.append(
										$("<thead></thead>")
												.html(
														"<tr><th>Name</th><th>Direction</th><th>Start</th><th>Finish</th><th></th><th></th></tr>"));
						$('#button')
								.click(
										function() {
											var future = $('#future').prop(
													'checked');
											var current = $('#current').prop(
													'checked');
											var finished = $('#finished').prop(
													'checked');
											console.log("Future: " + future);
											console.log("Current: " + current);
											console
													.log("Finished: "
															+ finished);
											$('#tableId tbody').remove();
											$
													.ajax({
														url : "chooseGroup",
														type : "Get",
														data : "future="
																+ future
																+ "&current="
																+ current
																+ "&finished="
																+ finished,
														dataType : 'json',

														beforeSend : function(
																xhr) {
															xhr
																	.setRequestHeader(
																			"Accept",
																			"application/json");
															xhr
																	.setRequestHeader(
																			"Content-Type",
																			"application/json");
														},
														success : function(
																response) {
															console
																	.log("Group id: "
																			+ response[0].id)
															$
																	.each(
																			response,
																			function(
																					i,
																					item) {
																				var myStartObj = $
																						.parseJSON(response[i].start), myStartDate = new Date(
																						response[i].start);
																				var myStartDateString = myStartDate
																						.toLocaleFormat('%d/%m/%Y');
																				var myEndObj = $
																						.parseJSON(response[i].end), myEndDate = new Date(
																						response[i].end);
																				var myEndDateString = myEndDate
																						.toLocaleFormat('%d/%m/%Y');
																				var id = response[i].id;
																				var groupName = response[i].groupName;
																				var directionName = response[i].directionName;
																				  $(
																						'#tableId')
																						.append(
																								$(
																										"<tbody></tbody>")
																										.html(
																												'<tr><td>'
																														+ groupName
																														+ '</td><td>'
																														+ directionName
																														+ '</td><td>'
																														+ myStartDateString
																														+ '</td><td>'
																														+ myEndDateString
																														+ '</td>'+
																														'<td><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalF'
																														+id+'"}">Edit</button><form action="editGroup" class="form-horizontal" method="post"><div class="modal fade" id="myModalF'+id+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><h4 class="modal-title" id="myModalLabel"><spring:message code="label.edit" /></h4></div><div class="modal-body"><div class="formGroup"><input type="hidden" id = "myId" value='+id+' /><spring:message code="label.name" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" cssClass="form-control" id="groupNameId" value="'+groupName+'" /><br><spring:message code="label.start" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" cssClass="form-control" id="groupStartId" value="'+myStartDateString+'"/><br><spring:message code="label.finish" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" cssClass="form-control" id="groupEndId" value="'+myEndDateString+'"/><br><spring:message code="label.direction" />&nbsp;&nbsp;<select id="selectList"><c:forEach items="${directionList}" var="direction"><option id="groupDirection'+id+'" value="${direction.name}">${direction.name}</option></c:forEach></select><br><spring:message code="label.manager" /><select multiple="true" id="selectManager"><c:forEach items="${managerList}" var="user"><option id="${user.login}">${user.login}</option></c:forEach></select><br><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="label.close" /></button><input type="button" id="editButton" class="btn btn-primary" value="<spring:message code="label.edit"/>" /></div></div></div></div></div></form></td><td><button class="btn btn-danger" data-toggle="modal" data-target="#myModalDelete${group.id}"><spring:message code="label.delete" /></button></td></tr>'));
														}); 
															$('#editButton').click(function(){
																var name = $('#groupNameId').val();
																var start = $('#groupStartId').val();
																var end = $('#groupEndId').val();
																var direction = $('#selectList :selected').text();
																var manager = $('#selectManager :selected').text();
																console.log("Name: "+name);
																console.log("Start: "+start);
																console.log("End: "+end);
																console.log("Direction: "+direction);
																console.log("Manager: "+manager);
																$.ajax({
																	url : "editGroup",
																	type : "Get",
																	data : "name="
																			+ name
																			+ "&start="
																			+ start
																			+ "&end="
																			+ end
																			+ "&direction="
																			+direction
																			+ "&manager="
																			+manager,
																			success : function(){
																				
																			}		
																})
															})
													}
													});
										})

						$('#filter')
								.click(
										function() {
											var direction = $('#direction')
													.val();
											var startFrom = $('#startFrom')
													.val();
											if ((startFrom == null)
													|| (startFrom == "")) {
												startFrom = "1900-01-01";
											}
											var startTo = $('#startTo').val();
											if ((startTo == null)
													|| (startTo == "")) {
												startTo = "2100-01-01";
											}
											var finishFrom = $('#finishFrom')
													.val();
											if ((finishFrom == null)
													|| (finishFrom == "")) {
												finishFrom = "1900-01-01";
											}
											var finishTo = $('#finishTo').val();
											if ((finishTo == null)
													|| (finishTo == "")) {
												finishTo = "2100-01-01";
											}
											console.log("Direction: "
													+ direction);
											console.log("Start from: "
													+ startFrom);
											console.log("Start to: " + startTo);
											console.log("Finish from: "
													+ finishFrom);
											console.log("Finish to: "
													+ finishTo);
											$('#tableId tbody').remove();
											$
													.ajax({
														url : "filterGroup",
														type : "Get",
														data : "direction="
																+ direction
																+ "&startFrom="
																+ startFrom
																+ "&startTo="
																+ startTo
																+ "&finishFrom="
																+ finishFrom
																+ "&finishTo="
																+ finishTo,
														dataType : 'json',

														beforeSend : function(
																xhr) {
															xhr
																	.setRequestHeader(
																			"Accept",
																			"application/json");
															xhr
																	.setRequestHeader(
																			"Content-Type",
																			"application/json");
														},

														success : function(
																response) {
															$
																	.each(
																			response,
																			function(
																					i,
																					item) {
																				var myStartObj = $
																						.parseJSON(response[i].start), myStartDate = new Date(
																						response[i].start);
																				var myStartDateString = myStartDate
																						.toLocaleFormat('%d/%m/%Y');
																				var myEndObj = $
																						.parseJSON(response[i].end), myEndDate = new Date(
																						response[i].end);
																				var myEndDateString = myEndDate
																						.toLocaleFormat('%d/%m/%Y');
																				var id = response[i].id;
																				var groupName = response[i].groupName;
																				var directionName = response[i].directionName;
																				$(
																						'#tableId')
																						.append(
																								$(
																										"<tbody></tbody>")
																										.html(
																												'<tr><td>'
																														+ response[i].groupName
																														+ '</td>'
																														+ '<td>'
																														+ response[i].directionName
																														+ '</td>'
																														+ '<td>'
																														+ myStartDateString
																														+ '</td><td>'
																														+ myEndDateString
																														+ '</td><td><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModalF'+id+'"}">Edit</button><form action="editGroup" class="form-horizontal" method="post"><div class="modal fade" id="myModalF'+id+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><h4 class="modal-title" id="myModalLabel"><spring:message code="label.edit" /></h4></div><div class="modal-body"><div class="formGroup"><input type="hidden" id = "myId" value='+id+' /><spring:message code="label.name" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" cssClass="form-control" id="groupNameId" value="'+groupName+'" /><br><spring:message code="label.start" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" cssClass="form-control" id="groupStartId" value="'+myStartDateString+'"/><br><spring:message code="label.finish" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" cssClass="form-control" id="groupEndId" value="'+myEndDateString+'"/><br><spring:message code="label.direction" />&nbsp;&nbsp;<select><c:forEach items="${directionList}" var="direction"><option id="groupDirection'+id+'" value="${direction.name}">${direction.name}</option></c:forEach></select><br><spring:message code="label.manager" /><select multiple="true"><c:forEach items="${managerList}" var="user"><option id="${user.login}">${user.login}</option></c:forEach></select><br><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="label.close" /></button><input type="button" id="editButton" class="btn btn-primary" value="<spring:message code="label.edit"/>" /></div></div></div></div></div></form></td><td><button class="btn btn-danger" data-toggle="modal" data-target="#myModalDelete${group.id}"><spring:message code="label.delete"/></button></td> </tr>'));
																			})
														}

													})
										})

						$('.datepicker').datepicker({
							format : "yyyy-mm-dd"
						})
					})
</script>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<c:if test="${success eq true}">
				<div class="alert alert-success">
					<spring:message code="label.groupAddedS" />
				</div>
			</c:if>
			<c:if test="${groupexists eq true}">
				<div class="alert alert-danger">
					<spring:message code="label.groupExists" />
				</div>
			</c:if>
			<c:if test="${invaliddate eq true}">
				<div class="alert alert-danger">
					<spring:message code="label.invalidDate" />
				</div>
			</c:if>
			<input type="checkbox" id="future" /><spring:message code="label.futureGroups" /> <input type="checkbox"
				id="current" /><spring:message code="label.currentGroups" /> <input type="checkbox" id="finished" /><spring:message code="label.finishedGroups" />
			<button class="btn btn-primary" id="button"><spring:message code="label.choose" /></button>
			<br> <br>
			<!-- Add group -->
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<!-- Button trigger modal -->
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#myModal">
					<spring:message code="label.add" />
				</button>
				<br>
				<br>
				<form:form action="addGroup" commandName="groupFormBean"
					class="form-horizontal form-validate" method="post">
					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only"><spring:message
												code="label.close" /></span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<spring:message code="label.newgroup" />
									</h4>
								</div>
								<div class="modal-body">
									<div class="formGroup">
										<label for="name" class="col-sm-2 control-label"> <spring:message
												code="label.name" />
										</label>
										<div class="col-sm-10">
											<form:input path="name" cssClass="form-control" name="name" />
											<form:errors path="name" />
										</div>

										<label for="start" class="col-sm-2 control-label"> <spring:message
												code="label.start" /></label>
										<div class="col-sm-10">
											<form:input path="start" cssClass="form-control"
												value="2014-08-01" />
											<form:errors path="start" />
										</div>

										<label for="end" class="col-sm-2 control-label"> <spring:message
												code="label.finish" /></label>
										<div class="col-sm-10">
											<form:input path="end" cssClass="form-control"
											 value="2014-12-30" />
											<form:errors path="end" />
										</div>

										<label for="group.direction" class="col-sm-2 control-label">
											<spring:message code="label.direction" />
										</label>
										<div class="col-sm-10">
											<form:select path="group.direction" items="${directionList}"
												itemLabel="name" itemValue="name" cssClass="form-control">
											</form:select>
											<form:errors path="group.direction" />
										</div>

										<label for="users" class="col-sm-2 control-label"> <spring:message
												code="label.manager" />
										</label>
										<div class="col-sm-10">
											<form:select path="users" items="${managerList}"
												itemLabel="login" itemValue="login" multiple="true"></form:select>
											<form:errors path="group.direction" cssClass="form-control" />
										</div>

									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">
										<spring:message code="label.close" />
									</button>
									<input type="submit" class="btn btn-primary"
										value="<spring:message code="label.add"/>" />
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</security:authorize>
			Direction: <select id="direction">
				<c:forEach items="${directionList}" var="direction">
					<option>${direction}</option>
				</c:forEach>
			</select> Start: from <input type="text" value="2014-08-27" id="startFrom"
				class="datepicker" /> to <input type="text" value="2014-12-31"
				id="startTo" class="datepicker" /> Finish: from <input type="text"
				value="2014-08-27" id="finishFrom" class="datepicker" /> to <input
				type="text" value="2014-12-31" id="finishTo" class="datepicker" />
			<input class = "btn btn-primary" type="button" value="Select" id="filter" /> <br> <br>
			<table class="table table-bordered table-hover table-striped"
				id="tableId">
			</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>