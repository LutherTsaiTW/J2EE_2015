<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>#springMessage("pay.title")</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">#springMessage("pay.title")</p>

		<form id="form" action="doPay" method="POST">
		
			<p class="content">#springMessage("pay.question")
					<input type="text" name="name" size="10"/>
					<input type="submit" value="!" class="QuestionMark"/>
		   </p>
			
		</form>
		
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>