<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>#springMessage("query.title")</title>
</head>
<body>
	<div class="body">
		<p class="title">#springMessage("query.title")</p>
		<p class="content">
			#springMessage("account") $FeeModel.name #springMessage("query.content") NT$ $FeeModel.count
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>