<%@ include file="../../template/default/taglib.jsp"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<h1>Rating table</h1>
		<div class="row">
			<div class="col-md-9 col-md-push-3">
				<div id="users" class="bs-example">
				<table class = "table table-hover">
				<thead>
				<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Total grade</th>
				</tr></thead>
				</table></div>
			</div>

			<div class="col-md-3 col-md-pull-9">
				<ul class="list-group">
				  <li class="list-group-item list-group-item-info"><select id="directions" class="form-control">
					</select></li>
				  <li class="list-group-item list-group-item-info"><select id="groups" class="form-control">
					
					</select></li>
				</ul>
				<ul id = "tableInfo" class="list-group">
				
				<li class="list-group-item list-group-item-danger"><strong>Group info</strong></li>
				<li class="list-group-item list-group-item-success">Teacher: -</li>
				<li class="list-group-item list-group-item-success">Start date: -</li>
				<li class="list-group-item list-group-item-success">End date: -</li>
				</ul>
			</div>
		</div>
		<script>$(document).ready(function() {
	 $.ajax({
	        url: "<spring:url value='/rating/directions'/>",
	        type: "POST",
	         
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(directionsList) {
	            $('#directions').append($('<option>').text("Direction").attr('value', null));
	        	$.each(directionsList, function(i, value) {
		       		 $('#directions').append($('<option>').text(value).attr('value', value));
		    	 }); 
	        }
	    });
	
	  $('#directions').change(function(event) {
		  var direction = $("#directions").val();
		  $('#groups').children().remove(); 		  
		  $.ajax({
	        url: "<spring:url value='/rating/groups'/>",
	        type: "POST",
	        data: direction,
	    	dataType: 'json',
	         
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(groupsList) {
	            $('#groups').append($('<option>').text("Groups").attr('value', null));
	        	$.each(groupsList, function(i, value) {
		       		 $('#groups').append($('<option>').text(value).attr('value', value));
		    	 }); 
	        }
	    });
	  });
	  
	  $('#groups').change(function(event) {
		  var group = $("#groups").val();
		  $('#users').children().remove();
		  var data = getDataFromServer('/rating/users', group);
		  console.log("is data null: "+data);
		  tablefilling(data);
	  });
	  

	});
		  function getDataFromServer(path, requestBody){
		      var usersList;
		      console.log("path:"+path);
		      console.log("requestBody: "+requestBody);
		      $.ajax({
			        url: "<spring:url value='/rating/users'/>",
			        type: "POST",
			        data: requestBody,
			    	dataType: 'json',
			         
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			        },
			        success: function(ratingTableData) {
			           var groupInfo = ratingTableData[0];
			           var usersList = ratingTableData[1];
			            usersList[0].firstName = "Name";
			            usersList[0].lastName = "Surname";
			            usersList[0].rating = "Total grade";
			            tablefilling(usersList);
			            console.log(ratingTableData[0]);
		      		    console.log(groupInfo.startDate);
		      		    console.log(groupInfo.endDate);
		      		$('#tableInfo').children().remove();
			            $("#tableInfo")
			            .append($('<li class="list-group-item list-group-item-danger"></li>').html("<strong>Group info</strong>"))
			            .append($('<li class="list-group-item list-group-item-success"></li>').html("Teacher: "+groupInfo.groupManager))
			            .append($('<li class="list-group-item list-group-item-success"></li>')
			        	    .html("Start date: " + groupInfo[0].startDate.dayOfMonth+"/"+groupInfo[0].startDate.monthOfYear+"/"+groupInfo[0].startDate.year))
			            .append($('<li class="list-group-item list-group-item-success"></li>')
			        	    .html("End date: " + groupInfo[0].endDate.dayOfMonth+"/"+groupInfo[0].endDate.monthOfYear+"/"+groupInfo[0].endDate.year));
			            
			        },
			        error: function(){
			            console.log("error:");
			        }
			    });
		      console.log("return: "+usersList);
		      return usersList;
		  }
		  
		  function tablefilling(usersList){
		      console.log("tablefilling usersList: "+usersList);
		      	var id = "users";
			  var className = "table table-hover";
			  $.jsontotable(usersList, {
				id : "#" + id,
				className : className,
				header: true
			});
		  }
	</script>
	</tiles:putAttribute>
</tiles:insertDefinition>