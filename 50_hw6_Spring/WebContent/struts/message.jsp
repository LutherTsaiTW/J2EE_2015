<!DOCTYPE HTML>
<html>
<head>
	<title>Hello JSP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../css/web.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">#stext('name=message.title')</p>
		
		<p class="message">
			$helloForm.result
		</p>
		<p class="stamp">
			$helloForm.stamp
		</p>
		
		<a href="../index.html">#stext('name=home')</a>
	</div>
</body>
</html>