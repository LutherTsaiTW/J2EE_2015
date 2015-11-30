<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>帳戶資訊</title>
</head>
<body>
	<div class="body">
		<p class="title">帳戶資訊</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>車商序號</th>
					<th>$AccountModel.id</th>
				</tr>
				<tr>
					<th>車商姓名</th>
					<th>$AccountModel.name</th>
				</tr>
				<tr>
					<th>帳戶現金</th>
					<th>$AccountModel.cash</th>
				</tr>
				<tr>
					<th>汽車資產</th>
					<th>$AccountModel.asset</th>
				</tr>
				<tr>
					<th>汽車數量</th>
					<th>$AccountModel.count</th>
				</tr>
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>