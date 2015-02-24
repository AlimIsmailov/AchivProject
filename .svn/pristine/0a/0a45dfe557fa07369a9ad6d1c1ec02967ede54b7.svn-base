<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<div>
				<h1 align="left">Modules. Examinations</h1>
			</div>
			<table class="table table-bordered table-hover" >
				<thead>
					<tr>
						<th width="50%" class="info">Name</th>
						<th width="10%" class="info" style="text-align:center">Grade / 10.00</th>
						<th width="10%" class="info" style="text-align:center">State</th>
						<th width="10%" class="info" style="text-align:center">Review</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${quizResultList}" var="result">
						<tr>
							<td>
								<span class="text-info">${result.name}</span>
							</td>
							<td align="center">
								<c:if test="${result.totalGrade == null}">
									<span class="text-muted">Not passed</span>
								</c:if>
								<c:if test="${result.totalGrade != null}">
									<span class="text-success">${result.totalGrade}</span>
								</c:if>
							</td>
							<td align="center">
								<c:if test="${result.passed == true }">
									<button type="button" class="btn btn-info" disabled="disabled">Finished</button>
								</c:if> 
								<c:if test="${result.passed == false }">
									<button class="btn btn-primary" data-toggle="modal"	data-target="#modalStart${result.id}">Pass the test</button>
									<!-- Modal -->
									<form:form action="pass-the-test" commandName="quizResult" method="post">
										<form:input type="hidden" path="id" value="${result.id}" />
										<div class="modal fade" id="modalStart${result.id}" tabindex="-1">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span>&times;</span><span class="sr-only">Close</span>
														</button>
														<h3 class="modal-title" id="myModalLabel" align="left">Pass the test</h3>
													</div>
													<div class="modal-body">
														<h4 align="left">Are you sure you want begin passing the test?</h4>
														<br />
														<p align="left">
															<em>P.S. Select only right answers. Good luck!</em>
														</p>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
														<input class="btn btn-warning" type="submit" value="Start" />
													</div>
												</div>
											</div>
										</div>
									</form:form>
								</c:if>
							</td>
							<td align="center">
								<c:if test="${result.passed == true}">
									<c:if test="${result.review == true}">
										<form:form action="my-test-detail" commandName="quizResult" method="post">
         									<form:input type="hidden" path="id" value="${result.id}" />
         									<input class="btn btn-info" type="submit" value="Review" />
         								</form:form>
									</c:if>
									<c:if test="${result.review == false}">
										<span class="text-muted">Not permitted</span>
									</c:if>
								</c:if>
								<c:if test="${result.passed == false }">
									<span class="text-muted">Not passed</span>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${empty quizResultList}">
				<h4>
					<em>You don't have active testing. Contact with your teacher.</em>
				</h4>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>