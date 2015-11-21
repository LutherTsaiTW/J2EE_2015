<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>汽車庫存</title>
</head>
<body>
	<div class="body">
		<p class="title">汽車庫存</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>編號</th>
					<th>汽車</th>
					<th>數量</th>
				</tr>
				#foreach( $carModel in $CarListModel )
				<tr>
					<th>$carModel.id</th>
					<th>$carModel.name</th>
					<th>$carModel.count</th>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>