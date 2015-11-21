<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>汽車銷售</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">汽車銷售</p>
		
		<form id="form" action="doSellCar" method="POST">
		
			<p class="content">
					顧客
					<input type="text" name="customerName" size="10"/>
					<br>
					購買新車
					<input type="text" name="carName" size="10"/>
					<br>
					<br>
					<input type="submit" value="確定"/>
		   </p>
		   
		</form>
		
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>