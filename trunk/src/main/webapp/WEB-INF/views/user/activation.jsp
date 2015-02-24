<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

<script>$(document).ready(function() {
	  $('#online').submit(function(event) {
	    $.ajax({
	    	
	    	data: JSON.stringify(),
	        url: $("#online").attr( "action"),
	        type: "POST",
	         
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(usersNamesList) {
	        	 console.log("Input "+usersNamesList);
	        	var a = usersNamesList;
	            var respContent = "";
	            a.forEach(function(entry) {
		            respContent += '<li class="list-group-item">';
		            respContent += entry + "</li>";
	            });

	             
	            $("#usersOnline").html(respContent);
	        }
	    });
	      
	    event.preventDefault();
	  });
	    
	});</script>

		<div class="alert alert-success form-fix" role="alert">
			<h4>
				<b><spring:message code="label.registration.success.welldon" /></b>
			</h4>
			<p>
				<spring:message code="label.registration.success.info" />
			</p>
			
			<form:form action="online" id="online">
				<input type="submit" value="Check">
			</form:form>
			<ul class="list-group form-fix" id="usersOnline">
				
			</ul>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>