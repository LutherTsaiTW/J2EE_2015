<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>#springMessage("list.carTitle")</title>
</head>
<body>
	<div class="body">
		<p class="title">#springMessage("list.carTitle")</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>#springMessage("list.carId")</th>
					<th>#springMessage("list.carName")</th>
					<th>#springMessage("list.carAmount")</th>
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