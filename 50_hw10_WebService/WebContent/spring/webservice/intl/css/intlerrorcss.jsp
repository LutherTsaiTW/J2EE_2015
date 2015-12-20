<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/snow.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
<script src="../../javascript/jquery.snow.min.1.0.js"></script>
<script>
$(document).ready( function(){
        $.fn.snow();
});
</script>
<title>#springMessage("error.title")</title>
</head>
<body class="snow">
	<div class="body">
		<p class="title">
			#springMessage("error.title")
		</p>
		<p class="content">
		#foreach( $ErrorMessage in $ErrorModel )
   			$ErrorMessage.getDefaultMessage() <br>
		#end
		</p>
	</div>
	<center><a href="#springMessage("homeURL")">#springMessage("home")</a></center>
</body>
</html>