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

			<table align="center">
				<tr>
					<td><form:form action="listmanagers" method="get">
							<input type="submit" class="btn btn-primary"
								value="<spring:message code="label.managerschedule" />" />
						</form:form></td>
					<td><form:form action="listgroups" method="get">
							<input type="submit" class="btn btn-primary"
								value="<spring:message code="label.groupschedule" />" />
						</form:form></td>
					<td><form:form action="listlocations" method="get">
							<input type="submit" class="btn btn-primary"
								value="<spring:message code="label.locationschedule" />" />
						</form:form></td>
				</tr>
			</table>
			<p>
			<p>
			<table align="center">
				<tr>
					<td><form:form action="download" method="post">
							<input type="submit" class="btn btn-primary"
								value="<spring:message code="label.downloadallschedules" />" />
						</form:form></td>
					<td><button class="btn btn-primary" data-toggle="modal"
							data-target="#myModal">
							<spring:message code="label.uploadallschedules" />
						</button></td>
				</tr>
			</table>

			<form:form action="uploadFile" enctype="multipart/form-data"
				class="form-horizontal" method="post">
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									<spring:message code="label.upload" />
								</h4>
							</div>
							<div class="modal-body">
								<spring:message code="label.uploadCsvFile" />
								<input type="file" name="file">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<spring:message code="label.close" />
								</button>
								<input type="hidden" name="week" value="${week}" /> <input
									type="hidden" name="scheduleParameter"
									value="${scheduleParameter}" /> <input type="submit"
									class="btn btn-primary"
									value="<spring:message code="label.uploadaction"/>" />
							</div>
						</div>
					</div>
				</div>
			</form:form>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>