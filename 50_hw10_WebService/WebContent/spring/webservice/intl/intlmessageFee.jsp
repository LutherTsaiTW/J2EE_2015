<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>#springMessage("message.title")</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>

<body>
	<div class="body">
	
			<p class="title">#springMessage("message.title")</p>
			
			<p class="content">	
			$MessageModel.result
			<br><br>
			$MessageModel.stamp
			<br><br> 
			#springMessage("message.fee") = NT$ $MessageModel.count
			</p>
			
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
			
	</div>
</body>
</html>