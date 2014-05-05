<%@ page language="java" contentType="text/html; " pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@include file='header.html'%>

<script type="text/javascript">
function userLogin(){
	var username = $('#username').val();
	var password = $('#password').val();
	   
	$.ajax({
		url : "movierecommender/user/login",
	    type: "POST",
	    data : "username=" + username + "&password=" + password,
	   
	    success:function(data, textStatus, jqXHR){
	    	console.log(data);
	    	//window.location.href="homepage.jsp?user="+username;
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	alert('Could not process request.. ' + errorThrown);
	    }
	});
}

function newUserLogin(){
	$.ajax({
		url : "movierecommender/newlogin/newpath",
	    type: "POST",
	    data : $("#loginform").serialize(),
	    success:function(data, textStatus, jqXHR){
	    	console.log(data);
	    	//window.location.href="homepage.jsp?user="+username;
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
		<% if(session.getAttribute("error") != null  && session.getAttribute("error").equals("Invalid username/password")) { %>
		<div id="errorOutput">
			<%=session.getAttribute("error") %>
		</div>
		<% } %>
		<div class="span5">
			<div style="height: 180px; background-color: lightblue; -webkit-box-shadow: 3px 0px 5px #2F4F4F; -moz-box-shadow: 3px 0px 5px #888888; box-shadow: 3px 0px 5px #888888; padding: 30px;">
				<h3>Sign In</h3>
				<form name="formname" id="loginform" method="POST" action="LoginServlet">
				<table>
					<tr>
						<td><label for="username" class="col-sm-2 control-label">Username</label></td>
						<td>
							<div class="col-sm-10">
								<input type="text" name="username" class="form-control" id="username" placeholder="Username">
							</div>
						</td>
					</tr>
					<tr>
						<td><label for="inputPassword3" class="col-sm-2 control-label">Password</label></td>
						<td>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control" id="password" placeholder="Password">
							</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div class="col-sm-offset-2 col-sm-10">
								<!--button type="submit" class="btn btn-primary" style= "margin-left:45px" id="login" onclick="newUserLogin()">Login</button-->
								<button type="submit" class="btn btn-primary" style= "margin-left:45px" id="login">Login</button>
							</div>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>

		<div class="span5">
			<div class="innerDivStyling">
				<h3>Don't have an account?</h3>
				<button class="btn btn-large btn-success margin-top-80" type="button" onclick="window.location.href='signup.jsp'">Sign up</button>
			</div>
		</div>
	</div>
</div>

<div id="cnt" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
    <h3 id="myModalLabel">Modal header</h3>
  </div>
  <div class="modal-body">
    <p>One fine bodyâ¦</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary">Save changes</button>
  </div>
</div>
<%@include file='footer.html'%>

</body>
</html>



