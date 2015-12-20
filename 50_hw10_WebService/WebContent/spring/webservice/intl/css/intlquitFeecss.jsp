<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/snow.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
<script src="../../javascript/jquery.snow.min.1.0.js"></script>ript>
<title>#springMessage("quit.title")</title>
<script>
$(document).ready( function(){
        $.fn.snow();
});
</script>
</head>
<body class="snow">
	<div class="body">

		<p class="title">#springMessage("quit.title")</p>

		<p class="content">#springMessage("account") $FeeModel.name #springMessage("quit.content")</p>

		<a href="#springMessage("homeURL")">#springMessage("home")</a>
		
	</div>
</body>
</html>