<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>#springMessage("list.accountTitle")</title>
</head>
<body>
	<div class="body">
		<p class="title">#springMessage("list.accountTitle")</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>#springMessage("list.accountId")™Ÿ</th>
					<th>#springMessage("list.accountName")Š</th>
					<th>#springMessage("list.accountLikeAmount")</th>
				</tr>
				#foreach( $accountModel in $AccountListModel )
				<tr>
					<th>$accountModel.id</th>
					<th>$accountModel.name</th>
					<th>$accountModel.count</th>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>