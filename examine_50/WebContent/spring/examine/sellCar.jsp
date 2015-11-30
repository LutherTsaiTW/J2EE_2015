<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我要賣車</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">我要賣車</p>
		
		<form id="form" action="doSellCar" method="POST">
		
			<p class="content">
					車商序號:
					<input type="text" name="id" size="10"/>
					<br><br>
					汽車名稱:
					<input type="text" name="name" size="10"/>
					<br><br>
					汽車價格:
					<input type="text" name="price" size="10"/>
					<br><br>
					<input type="submit" value="託售"/>
		   </p>
		
		</form>

		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>