<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>#springMessage("quit.title")</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/snow.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
<script src="../../javascript/jquery.snow.min.1.0.js"></script>
<script>
$(document).ready( function(){
        $.fn.snow();
});
</script>
</head>
<body class="snow">
	<div class="body">
		<p class="title">#springMessage("quit.title")</p>
		
		<form id="form" action="doQuit" method="POST">
		
			<p class="content">
				#springMessage("quit.question")
				<input type="text" name="name" size="10"/>
				<input type="submit" value="!"/>
			</p>
			
		</form>
		
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>