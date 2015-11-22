<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>#springMessage("appreciate.title")</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">#springMessage("appreciate.title")</p>
		
		<form id="form" action="doAppreciate" method="POST">
		
			<p class="content">
					#springMessage("thank")
					<input type="text" name="name" size="10"/>
					<br>
					我對你充滿
					<input type="text" name="likeAmount" size="10"/>
					點感謝
					<br>
					<br>
					<input type="submit" value="!"/>
		   </p>
		   
		</form>
		
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>