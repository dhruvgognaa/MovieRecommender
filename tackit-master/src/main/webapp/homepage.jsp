<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>HomePage</title>
<%@include file='header.html'%>
</head>
<body>
<% if(session.getAttribute("username") == null) { %>
	<script>window.location.href = 'login.jsp';</script>
<% }  %>
<div id="container" class="outerdiv">
	<div class="container-fluid innerdiv">
		<h1>Your movie recommender</h1>
		<div id="fade"></div>
		<div id="modal">
		    <img id="loader" src="images/ajax-loader.gif" />
		</div>
	</div>
	<!--div class="control-group outerdiv-nopadding clearboth">
		<div class="controls innerdiv">
			<input type="text" class="displayinline" id="userid" placeholder="Enter User ID">
			<label class="control-label displayinline" for="Age">&nbsp;&nbsp;&nbsp;&nbsp;Age</label>
	    	<select id="userAge" class="displayinline">
	    		<option value="0" selected="selected">&nbsp;</option>
				<option value="6"><12</option>
				<option value="15">13-19</option>
				<option value="25">20-29</option>
				<option value="37">30-45</option>
				<option value="51">46-55</option>
				<option value="60">56-75</option>
			</select>
			<label class="control-label displayinline" for="Gender">&nbsp;&nbsp;&nbsp;&nbsp;Gender</label>
	    	<select id="userAge" class="displayinline">
				<option value="M" selected="selected">M</option>
				<option value="F">F</option>
			</select>
		</div>
	</div-->
	<div class="control-group outerdiv-nopadding clearboth">
		<!--label class="control-label" for="inputEmail">Search</label-->
		<div class="controls innerdiv">
 			<!--input type="text" id="moviename" placeholder="Movie Name"-->
 			<input type="text" id="userid" placeholder="Enter User ID">
 			<select id="clusteredNonClustered">
				<option value="clustered" selected="selected">Clustered</option>
				<option value="nonclustered">Non-Clustered</option>
			</select>
			<select id="itemOrUser">
				<option value="itemtoitem" selected="selected">Item-to-Item</option>
				<option value="usertouser">User-to-User</option>
			</select>
 		</div>
	</div>
	<button type="submit" class="btn btn-primary" id="submitButton"  onclick="getResults()">Search</button>
	<div id="resultsDiv" style="padding-top: 20px"></div>
</div>

<%@include file='footer.html'%>
</body>

<script>
$(document).ready(function() {
    $("#userid").keydown(function(event) {
    	if ( event.keyCode == 46 || event.keyCode == 8 ) {
    	}
    	else {
    		if (event.keyCode < 48 || event.keyCode > 57 ) {
    			event.preventDefault();	
    		}	
    	}
    });
});

$('#userid').tooltip({
	'trigger':'hover', 
	'placement': 'bottom', 
	'title': 'Existing user ids between 1 and 943. Any other input treated as new user.'
});

$('#clusteredNonClustered').tooltip({
	'trigger':'hover', 
	'placement': 'bottom',
	'title': 'Clustered or NonClustered Data Set.'
});

$('#itemOrUser').tooltip({
	'trigger':'hover', 
	'placement': 'bottom', 
	'title': 'Item-to-Item or User-to-User filtering.'
});

function getResults(){
	var result;
	var userid = $("#userid").val();
	var moviename;
	if(userid > 943 || userid == "" || userid == 0)
		moviename = window.prompt('Please enter a movie name e.g. toy story');
	if(userid == "")
		userid = 0;
	var clustered = $("#clusteredNonClustered").val();
	var item = $("#itemOrUser").val();
	var age = 11;
	var gender = "M";
	
	$.ajax({
		url : "movierecommender/recommendation/getmovies",
	    type: "POST",
	    data : "moviename=" + moviename + "&clustered=" + clustered + "&item=" + item + "&userid=" + userid + "&age=" + age + "&gender=" + gender,
	    beforeSend : function (){
	    	openModal();
        },
	    success:function(data, textStatus, jqXHR){
	    	if(data != "" && data != (moviename+" not found in database")){
	    		result = makeTable(data);
		    	$("#resultsDiv").html(result);
	    	}
	    	else if (data == (moviename+" not found in database")){
	    		$("#resultsDiv").html(moviename+" not found in database");
	    	}
	    	else{
	    		$("#resultsDiv").html("No recommendations found");
	    	}
	    	
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	alert('Could not process request.. ' + errorThrown);
	    },
	    complete : function (){
			closeModal();
        }
	});
}

function makeTable(data){
	var movies = data.split(';');//.join('<br>');
	var newData = "";
	var index = 0;
	
	newData += "<table id='movieListTable' class = 'tableborder' style='margin: 0 auto; box-shadow: 5px 5px 5px #999; min-width: 500px; opacity: 0.9;'>";
		newData += "<tr>";
			newData += "<th class='even'>Movie Name</th>";
			newData += "<th class='even'>Release Date</th>";
		newData += "</tr>";
	for(var i=0;i<movies.length;i++){
		var odd = 'odd';
		if(index%2 != 0)
			odd = 'even';
		var details = movies[i].split(':::');
		newData += "<tr>";
			newData += "<td class='" + odd + "'>" + "<a href='" + details[3] + "' target='_blank'>" + details[1] +"</a></td>";
			newData += "<td class='" + odd + "'>" + details[2] + "</td>";
		newData += "</tr>";
		index++;
	}
	newData += "</table>";
	return newData;
}

function openModal() {
    $('#modal').css('display', 'block');
    $('#fade').css('display', 'block');
}

function closeModal() {
	$('#modal').css('display', 'none');
    $('#fade').css('display', 'none');
}

<% if(session.getAttribute("username") != null) { %>
	var loggedInUser = <%= session.getAttribute("userid") %>
	$("#userid").val(loggedInUser);
	$("#userid").attr('disabled', true);
<% }  %>
</script>
</html>



