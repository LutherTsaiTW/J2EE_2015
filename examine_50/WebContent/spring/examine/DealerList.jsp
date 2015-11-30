<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>車商列表</title>
</head>
<body>
	<div class="body">
		<p class="title">車商列表</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>車商總數</th>
					<th>$DealerListModel.dealer</th>
				</tr>
				<tr>
					<th>車商總金額</th>
					<th>$DealerListModel.cash</th>
				</tr>
			</table>
			<br><br>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>序號</th>
					<th>姓名</th>
					<th>信箱</th>
					<th>現金</th>
				</tr>
				#foreach( $accountModel in $DealerListModel.accountList )
				<tr>
					<th>$accountModel.id</th>
					<th>$accountModel.name</th>
					<th>$accountModel.email</th>
					<th>$accountModel.cash</th>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>