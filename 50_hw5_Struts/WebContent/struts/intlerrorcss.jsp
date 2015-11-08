<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Error Message</title>
	<link href="../css/snow.css" rel="stylesheet" type="text/css">
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
	<script src="../javascript/jquery.snow.min.1.0.js"></script>
	
	<script>
		$(document).ready( function(){
			$.fn.snow();
		});
	</script>
</head>

<body class="snow">
	<div class="body">
		<p class="title">
			#stext('name=error.title')
		</p>
		<p class="content">#sfielderror</p>
		<a href="../index.html"> #stext('name=home') </a>
	</div>
</body>

</html>