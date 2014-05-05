<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Signup</title>
<%@include file='header.html'%>

<script type="text/javascript">
function checkPasswordMatch(){
	//var checkPasswordMatch = function() {
	    var password = $("#password").val();
	    var rePassword = $("#reEnterPasswordInput").val();
		
	    if (password != rePassword)
	        $("#divCheckPasswordMatch").html("Passwords do not match!");
	    else{
	        $("#divCheckPasswordMatch").html("");  
	    }
	}

function createUser(){
	$("#reEnterPasswordInput").keyup(checkPasswordMatch());
	var username = $('#username').val();
	var password = $('#password').val();
	var gender = $('#gender').val();
	var age = $('#age').val();
	var occupation = $('#occupation').val();
	var zipcode = $('#zipcode').val();
	
	$.ajax({
		url : "movierecommender/user/signup",
	    type: "POST",
	    data : "username=" + username + "&password=" + password + "&gender=" + gender + "&age=" + age + "&occupation=" + occupation + "&zipcode=" + zipcode,
	   
	    success:function(data, textStatus, jqXHR){
	    	window.location.href = "login.jsp";
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	alert('Could not process request.. ' + errorThrown);
	    }
	});
}
</script>
</head>
<body>
<div id="container" class="outerdiv">
	<div class="container-fluid innerdiv">
	<h2 class="padding-right-15">Please fill out the form</h2>
		<div class="tab-pane active" id="NewUserSignUp">
			<table cellpadding="5px">
				<tr>
					
				</tr>
				<tr>
					<td class="align-left"><h5>User Name</h5></td>
					<td><div class="input-group">
							<input type="text" id="username" class="required" placeholder="User Name">
						</div></td>
				</tr>
				<tr>
					<td class="align-left"><h5>Password</h5></td>
					<td><div class="input-group">
							<input type="password" id="password" name="passwordInput" class="required" placeholder="Password">
						</div></td>
				</tr>
				<tr>
					<td class="align-left"><h5>Re-enter Password</h5></td>
					<td><div class="input-group">
							<input type="password" id="reEnterPasswordInput" name="reEnterPasswordInput" class="required" placeholder="Re-enter Password" onChange="checkPasswordMatch();">
						</div></td>
					<td><div class="registrationFormAlert" id="divCheckPasswordMatch"></div></td>
				</tr>
				<tr>
					<td class="align-left"><h5>Age</h5></td>
					<td><div class="input-group">
							<input type="text" id="age" placeholder="Age">
						</div></td>
				</tr>
				<tr>
					<td class="align-left"><h5>Gender</h5></td>
					<td><div class="input-group">
							<input type="text" id="gender" placeholder="Gender">
						</div></td>
				</tr>
				<tr>
					<td class="align-left"><h5>Occupation</h5></td>
					<td><div class="input-group">
							<input type="text" id="occupation" placeholder="Occupation">
						</div></td>
				</tr>
				<tr>
					<td class="align-left"><h5>Zip Code</h5></td>
					<td><div class="input-group">
							<input type="text" id="zipcode" placeholder="ZipCode">
						</div></td>
				</tr>
			</table>
			<button id="signup" class="btn btn-primary" type="button" onclick="createUser()">Register</button>
		</div>
	</div>
</div>

<%@include file='footer.html'%>

<script>
	function PassVerify(){
	    var pass1 = $('#password').val();
	    var pass2 = $('#confirmpassword').val();
	    var message = document.getElementById('confirmMessage');
	    var badColor = "red";
	    if(password == confirmpassword){
	        message.innerHTML = "";
	    }else{
	        message.style.color = badColor;
	        message.innerHTML = "Passwords Do Not Match..";
	    }
	} 
	function EmailVerify() {
	    var email = document.getElementById('email').value;
	    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    var message = document.getElementById('emailMessage');
	    var badColor = "red";
	    message.style.color = badColor;
	
	    if (!filter.test(email)) {   
	       $('#emailMessage').html("Please Enter valid Email");
	       return false;
	    }else{
	       $('#emailMessage').html("");
	       $.get('/check_email?email=' + email, function(data){
	            if(data == "true")
	                $('#emailMessage').html("Email already exists");
	       });
	    }
	    return true;
	}
	function validateAndSubmit(){
	    
	}
</script>
</body>
</html>



