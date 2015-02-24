<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<style>
</style>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<form:form class="form-container" method="post" action="addQuestion"
				commandName="question">

				<div class="form-title">
					<h3>
						<spring:message code="label.question" />
					</h3>
				</div>

				<div class="form-title">
					<form:label path="name">
						<spring:message code="label.name" />
					</form:label>
					<form:errors path="name" style="color: red;"></form:errors>
					<form:input class="form-field" path="name" />

					<form:label path="answers[0].name">Answer1</form:label>
					<form:errors path="answers[0].name" style="color: red;"></form:errors>
					<form:input class="form-field" path="answers[0].name" />
					
					<label class="checkbox">  
            	 	   <input type="checkbox" name="answers[0].isCorrect" value="true">  
							Correct
          		    </label>

					<form:label path="answers[1].name">Answer2</form:label>
					<form:errors path="answers[1].name" style="color: red;"></form:errors>
					<form:input class="form-field" path="answers[1].name" />
					
					<label class="checkbox">  
            	 	   <input type="checkbox" name="answers[1].isCorrect" value="true">  
							Correct
          		    </label>

					<form:label path="answers[2].name">Answer3</form:label>
					<form:errors path="answers[2].name" style="color: red;"></form:errors>
					<form:input class="form-field" path="answers[2].name" />
					
					<label class="checkbox">  
            	 	   <input type="checkbox" name="answers[2].isCorrect" value="true">  
							Correct
          		    </label>

					<form:label path="answers[3].name">Answer4</form:label>
					<form:errors path="answers[3].name" style="color: red;"></form:errors>
					<form:input class="form-field" path="answers[3].name" />
					
					<label class="checkbox">  
            	 	   <input type="checkbox" name="answers[3].isCorrect" value="true">  
							Correct
          		    </label>
					
					<form:label path="subtopic">
						<spring:message code="label.subtopic" />
					</form:label>

					<form:select path="subtopic" items="${subtopicList}"
						itemLabel="name" itemValue="name">
					</form:select>
				</div>
				<div class="submit-container">
					<input class="submit-button" type="submit" id="newgroup"
						value="<spring:message code="label.add"/>" />
				</div>
			</form:form>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>