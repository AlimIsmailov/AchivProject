<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

</head>
<body>
	<center>



		<b>Being Java Guys | Registration Form </b>



		<script>
   function doAjaxPost() {
    
	   console.log("Start");
    
    var name = $('#name').val();
    console.log("name");
    var gender = $('#gender').val();
    console.log("gender");
    var email = $('#email').val();
    console.log("email");
    var phone = $('#phone').val();
    console.log("phone");
    var city = $('#city').val();

    $.ajax({
     type : "Get", 
     url : "hello", 
     data : "name=" + name + "&gender=" + gender + "&email="
       + email + "&phone=" + phone + "&city=" + city,
     success : function(response) {
      alert(response); 
     },
     error : function(e) {
      alert('Error: ' + e); 
     }
    });
   }
  </script>
		<div id="form">
			<form method="get">
				<table>
					<tr>
						<td>Name :</td>
						<td><input type="text" id="name" /></td>
					</tr>
					<tr>
						<td>Gender :</td>
						<td><input type="radio" id="gender" value="male" /> Male <input
							type="radio" id="gender" value="female" /> Female</td>
					</tr>
					<tr>
						<td>Email :</td>
						<td><input type="text" id="email" /></td>
					</tr>
					<tr>
						<td>Phone :</td>
						<td><input type="text" id="phone" /></td>
					</tr>
					<tr>
						<td>City :</td>
						
					</tr>
					<tr>
						<td> </td>
						<td><input type="button" value="Save" onclick="doAjaxPost()" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
</html>
