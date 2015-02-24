<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<br>
			<form:form class="form-horizontal" method="post" action="addTest"
				modelAttribute="testFormBean" cssClass="form-fix testForm">
				<c:if test="${success eq true}">
					<div class="alert alert-success">Generated successful!</div>
				</c:if>
				<c:if test="${error eq  'empty-group'}">
					<div class="alert alert-danger">Group is empty!</div>
				</c:if>
				<c:if test="${error eq  'not-enought-elements'}">
					<div class="alert alert-danger">Not enough of questions in
						this subtopic</div>
				</c:if>

				<h2 align="center">Generate new test</h2>
				<br/>
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Test name:</label>
						<div class="col-sm-7">
							<form:input type="text" class="form-control" path="quizName"/>
							<form:errors path="quizName" style="color: red" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Group:</label>
						<div class="col-sm-7">
							<form:select path="groupName" class="form-control" id="groupSelect">
								<form:option value="">Select a group</form:option>
								<c:forEach items="${groupList}" var="group">
									<form:option value="${group.groupName}"></form:option>
								</c:forEach>
							</form:select>
							<form:errors path="groupName" style="color: red" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Topic:</label>
						<div class="col-sm-7">
							<form:select path="topicName" class="form-control" id="topicSelect"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Subtopic:</label>
						<div class="col-sm-7">
							<form:select path="subtopicName" class="form-control" id="subtopicSelect"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Count of questions:</label>
						<div class="col-sm-7">
							<form:select path="countOfQuestions" class="form-control" id="count">
								<option value="">Select a count</option>
								<c:forEach items="${counts}" var="count">
									<form:option value="${count}"></form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-sm-4 control-label">Review:</label>
						<div class="col-sm-7">
							<form:radiobutton path="review" value="true" label="True"/>	
							<form:radiobutton path="review" value="false" label="False"/>		
						</div>
					</div>
				</div>
				
				<div class="submit-container">
					<input class="btn btn-lg btn-primary" type="submit"	value="Generate" />
				</div>

			</form:form>
		</div>

		<script type="text/javascript">
			$(document).ready(function() {
				$("#groupSelect").change(function() {
					$('#topicSelect').children().remove();
					$('#subtopicSelect').children().remove();
					var groupName = $('#groupSelect').val();
						$.ajax({
							type : "GET",
							url : "<spring:url value='/testing/topics'/>",
							data : "groupName=" + groupName,
							dataType: 'json',
							success : function(json) {
								 $.each(json, function(i, value) {
						       		 $('#topicSelect').append($('<option>').text(value).attr('value', value));
						    	 }); 
								 $('#subtopicSelect').children().remove(); 
								 var topicName = $('#topicSelect').val();
									$.ajax({
										type : "GET",
										url : "<spring:url value='/testing/subtopics'/>",
										data : "topicName=" + topicName,
										dataType: 'json',
										success : function(json) {
											$.each(json, function(i, value) {
						       					 $('#subtopicSelect').append($('<option>').text(value).attr('value', value));
						    				 }); 
										}
									});
							}
						});  
			});
				$("#topicSelect").change(function() {
					var topicName = $('#topicSelect').val();
					$('#subtopicSelect').children().remove(); 
						$.ajax({
							type : "GET",
							url : "<spring:url value='/testing/subtopics'/>",
							data : "topicName=" + topicName,
							dataType: 'json',
							success : function(json) {
								 $.each(json, function(i, value) {
		       						 $('#subtopicSelect').append($('<option>').text(value).attr('value', value));
		    					 }); 
						}
					});
				});
	});
		</script>
		<script type="text/javascript">
			$(document).ready(
					function() {
						$('.testForm').validate(
								{
									rules : {
										quizName : {
											required : true,
											minlength : 3
										},
										groupName: {
											required : true
										},
										topicName : {
											required : true
										},
										subtopicName : {
											required : true
										},
										countOfQuestions : {
											required : true
										},
										review : {
											required : true
										},
									},
									highlight : function(element) {
										$(element).closest('.form-group')
												.removeClass('has-success')
												.addClass('has-error');
									},
									unhighlight : function(element) {
										$(element).closest('.form-group')
												.removeClass('has-error')
												.addClass('has-success');
									},
									messages: {
										topicName : "No topics in this group!",
										subtopicName : "No subtopics in this topic!",
										countOfQuestions: "Select a count",
										review : "Select any"
									}
								});
					});
		</script>

	</tiles:putAttribute>
</tiles:insertDefinition>