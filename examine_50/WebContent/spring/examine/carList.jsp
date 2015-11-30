<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>車市列表</title>
</head>
<body>
	<div class="body">
		<p class="title">車市列表</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>汽車總數</th>
					<th>$GarageModel.ownerId</th>
				</tr>
				<tr>
					<th>汽車總金額</th>
					<th>$GarageModel.count</th>
				</tr>
			</table>
			<br><br>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>序號</th>
					<th>汽車</th>
					<th>價格</th>
					<th>車主序號</th>
				</tr>
				#foreach( $carModel in $GarageModel.carList )
				<tr>
					<th>$carModel.id</th>
					<th>$carModel.name</th>
					<th>$carModel.price</th>
					<th>$carModel.ownerId</th>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>