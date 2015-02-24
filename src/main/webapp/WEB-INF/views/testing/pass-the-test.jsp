<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<form:form class="form-horizontal" method="post" action="finish" modelAttribute="testFormBean" cssClass="content">
				<h2 align="center" class="text-muted">${quiz.name}</h2>
				<form:input type="hidden" path="id" value="${quiz.id}" />
				<c:forEach items="${quiz.userAnswers}" var="userAnswer"	varStatus="status">
					<div class="panel panel-default">
						  <div class="panel-heading">
   						 		 <h3 class="panel-title">${userAnswer.question.name}</h3>
  						  </div>
  						   <div class="panel-body" align="left">
  						   		<form:checkboxes items="${userAnswer.question.answers}"	path="testsData[${userAnswer.question.name}]"
								element="span class='checkbox'" itemLabel="name" itemValue="name" multiple="true" />
  						   </div>
					</div>
				</c:forEach>
				<div class="submit-container">
					<a href="" class="btn btn-lg btn-success" data-toggle="modal" data-target="#modalFinish">Pass the test</a>
				</div>
				<div class="modal fade" id="modalFinish" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span>&times;</span><span class="sr-only">Close</span>
								</button>
								<h3 class="modal-title" id="myModalLabel">Completion of the	test</h3>
							</div>
							<div class="modal-body">
								<h4>Are you sure you gave all the answers and want to complete your test?</h4>
								<br />
								<p>
									<em>P.S. If you did not selected no any answers on question, you will get 0 points!!!</em>
								</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								<input class="btn btn-success" type="submit" value="Finish" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
