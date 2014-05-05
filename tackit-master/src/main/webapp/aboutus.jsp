<!DOCTYPE html>
<html>
<head>
<title>About us</title>

<style>
#aboutcontainer div
{
margin:0px;
padding:0px;
background-size: 1350px 800px;
background-repeat: no-repeat;
font-family: Palatino Linotype;
font-size: 130%;
font-style: italic;
overflow:auto 
}
#aboutcontainer div.transbox
{
width:500px;
height:400px;
margin:20px 20px;
opacity:0.9;
}
#aboutcontainer div.transbox p
  {
  margin:20px 20px;
  font-weight:bold;
  color:strong 	black;
  }
#aboutcontainer h1
{
text-align: left;
font-family: Palatino Linotype;
font-size: 350%;
font-style: italic;
}
#aboutcontainer ul
{
list-style-type: none;
margin:10;
padding:50;
}
#aboutcontainer li
{
float:left;
display:inline;
width: 200px;
background color: #FF3300;
font-size: 150%;
}
#aboutcontainer a:link, a:visited
{
display:block;
width:200px;
font-weight:bold;
color: #330099;
background-color: #FFFFFF;
text-align:center;
text-decoration:none;
opacity: 0.5;
text-transformation:uppercase;
}
#aboutcontainer a:hover,a:active;
{
background-color: #666699;
}
</style>
<%@include file='header.html'%>
</head>
<body id="newimage" style="background: url(images/walkingdead.jpg);">
<div id="aboutcontainer">
	<div class="transbox"> 
	<p>
	Hello everyone! Welcome to Movies for You. 
	 This project has been developed by Knowledge Discoverers.
	 Here we provide you a movie recommender engine which gives you recommendations based on your preference.</br><br>
	 Developed by: Abhishek Yadav, Dhruv Gogna, Nidhi Srivastava, Nikita Varma, Sweny Zaveri </p>
	
	</div>
</div>
</div>
<div>
<%@include file='footer.html'%>
</body>
</html>