<!DOCTYPE HTML>
<html>
<head>
	<title>Hello</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../css/web.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">#stext('name=hello.title')</p>
		
		<form action="doHello" method="post">
			<p class="content">#stext('name=hello.question')
				<input type="text" name="helloForm.name" size="10"/>
				<input type="submit" value="?" class="QuestionMark"/>
			</p>
		</form>
		
		<a href="../index.html">#stext('name=home')</a>
	</div>
</body>
</html>