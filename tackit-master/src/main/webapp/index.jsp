<html lang="en">
<head>
<meta charset="utf-8">
<title>Url</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link href="bootstrap/bootstrap.min.css" rel="stylesheet" media="screen"
	type="text/css">
<link href="bootstrap/bootstrap.css" rel="stylesheet" media="screen"
	type="text/css">
<link href="bootstrap/bootstrap-responsive.css" rel="stylesheet"
	media="screen" type="text/css">
<link href="bootstrap/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen" type="text/css">
<script type="text/javascript" src="jsbootstrap/bootstrap.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap.min.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-dropdown.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-alert.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-button.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-carousel.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-scrollspy.js" /></script>
<script type="text/javascript" src="jsbootstrap/bootstrap-modal.js" /></script>


<script type="text/javascript">
	function submiturl() {

		alert('inside submiturl');

		var url = $('#url').val();

		alert('url: ' + url);

		$.ajax({
			url : "tackit/user/url",
			type : "POST",
			data : "url=" + url,

			success : function(data, textStatus, jqXHR) {
				alert('success');
				window.location.href="signup.jsp";
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('Could not process request.. ' + errorThrown);
			}
		});
	}
</script>




</head>

<body>





			<label>Add your pins here...!!</label> <input type="text" id="url"
				placeholder="http://">
			<button type="submit" id="submiturl" class="btn btn-primary"
				onclick="submiturl()">Submit</button>
	



</body>
</html>
