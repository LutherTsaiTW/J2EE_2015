<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>#springMessage("query.title")</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>

<body>
	<div class="body">
	
			<p class="title">#springMessage("query.likeTitle")</p>
			
			<p class="content">	
			#springMessage("query.name") = $MessageModel.result
			<br><br>
			#springMessage("query.amount") = $MessageModel.count
			<br><br> 
			</p>
			
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
			
	</div>
</body>
</html>