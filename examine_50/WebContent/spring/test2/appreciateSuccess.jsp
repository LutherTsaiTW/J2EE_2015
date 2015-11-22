<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>#springMessage("appreciate.successTitle")</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>

<body>
	<div class="body">
	
			<p class="title">#springMessage("appreciate.successTitle")</p>
			
			<p class="content">	
			#springMessage("appreciate.content")，$MessageModel.result
			<br><br>
			#springMessage("appreciate.value") = $MessageModel.stamp
			<br><br> 
			</p>
			
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
			
	</div>
</body>
</html>