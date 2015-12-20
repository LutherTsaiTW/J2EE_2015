<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/snow.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
<script src="../../javascript/jquery.snow.min.1.0.js"></script>
<script>
$(document).ready( function(){
        $.fn.snow();
});
</script>
<title>#springMessage("list.title")</title>
</head>
<body class="snow">
	<div class="body">
		<p class="title">#springMessage("list.title")</p>
		<p class="content">
		<center>
			<table class="snowTable" border="1px" cellspacing="0px" cellpadding="6px"
				valign="middle">
				<tr>
					<th>#springMessage("list.id")</th>
					<th>#springMessage("list.name")</th>
					<th>#springMessage("list.fee")</th>
				</tr>
				#foreach( $feeModel in $FeeListModel )
				<tr>
					<td>$feeModel.id</td>
					<td>$feeModel.name</td>
					<td>$feeModel.count</td>
				</tr> 
				#end
			</table>
		</center><br>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>
</body>
</html>