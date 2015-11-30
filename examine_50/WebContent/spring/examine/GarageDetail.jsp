<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>託售列表</title>
</head>
<body>
	<div class="body">
		<p class="title">託售列表</p>
		<p class="content">
		車商序號 = $GarageModel.ownerId <br>
		汽車數量 = $GarageModel.count <br>
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>序號</th>
					<th>汽車</th>
					<th>價格</th>
				</tr>
				#foreach( $carModel in $GarageModel.carList )
				<tr>
					<th>$carModel.id</th>
					<th>$carModel.name</th>
					<th>$carModel.price</th>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>