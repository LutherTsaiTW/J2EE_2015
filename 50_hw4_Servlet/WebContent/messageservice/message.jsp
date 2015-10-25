<!DOCTYPE HTML>
<html>
<head>
	<title>Hello JSP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../css/web.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">Message</p>
		
		<p class="content">What is your name?
			${requestScope.result}
		</p>
		
		<a href="../index.html">HOME</a>
	</div>
</body>
</html>