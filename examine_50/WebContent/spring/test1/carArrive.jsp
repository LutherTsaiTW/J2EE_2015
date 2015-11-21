<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>汽車入庫</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">汽車入庫</p>
		
		<form id="form" action="doCarArrive" method="POST">
		
			<p class="content">
					新車
					<input type="text" name="name" size="10"/>
					，
					<input type="text" name="amount" size="10"/>
					輛
					<br>
					<br>
					<input type="submit" value="確定"/>
		   </p>
		   
		</form>
		
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>