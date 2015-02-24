<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body content"> 
			<h2 align="center" class="text-muted">${quizResult.name}</h2>
			<c:forEach items="${quizResult.userAnswers}" var="userAnswer" varStatus="status">
				<c:if test="${userAnswer.answerGrade == 0}">
					<div class="panel panel-danger">
						  <div class="panel-heading">
   						 		 <h3 class="panel-title">${userAnswer.question.name}</h3>
  						  </div>
  						  <div class="panel-body" align="left">
  						  		<c:forEach items="${userAnswer.question.answers}" var="answer">
  						  			<div class="checkbox">
  										<label>
  											<c:forEach items="${userAnswer.answers}" var="uanswer">
  												<c:if test="${answer == uanswer}">
  													<input type="checkbox" value="" disabled checked>
  												</c:if>
  											</c:forEach>
    										<input type="checkbox" value="" disabled>
    											<c:if test="${answer.isCorrect == false}">
    												<span class="label label-danger">${answer.name}</span>
    											</c:if>
    											<c:if test="${answer.isCorrect == true}">
    												<span class="label label-success">${answer.name}</span>
    											</c:if>
										 </label>
									</div>
  						  		</c:forEach>
  						  </div>
					</div>
				</c:if>
				<c:if test="${userAnswer.answerGrade > 0}">
					<div class="panel panel-success">
						  <div class="panel-heading">
   						 		 <h3 class="panel-title">${userAnswer.question.name}</h3>
  						  </div>
  						  <div class="panel-body" align="left">
  						  		<c:forEach items="${userAnswer.question.answers}" var="answer">
  						  			<div class="checkbox">
  										<label>
  											<c:forEach items="${userAnswer.answers}" var="uanswer">
  												<c:if test="${answer == uanswer}">
  													<input type="checkbox" value="" disabled checked>
  												</c:if>
  											</c:forEach>
											<input type="checkbox" value="" disabled>
    											<c:if test="${answer.isCorrect == false}">
    												<span class="label label-danger">${answer.name}</span>
    											</c:if>
    											<c:if test="${answer.isCorrect == true}">
    												<span class="label label-success">${answer.name}</span>
    											</c:if>
										 </label>
									</div>
  						  		</c:forEach>
  						  </div>
					</div>
				</c:if>
			</c:forEach>
			<br/>
			<h3 align="right" class="text-muted">Your final grade for this quiz is ${quizResult.totalGrade} / 10.00</h3>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
