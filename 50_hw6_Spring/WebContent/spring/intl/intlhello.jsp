<!DOCTYPE HTML>
<html>
<head>
<title>Hello</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/web.css" rel="stylesheet" type="text/css">

<body>
	<div class="body">
		<p class="title">#springMessage("hello.title")</p>

		<form action="doHello" method="POST">
			<p class="content">
				#springMessage("hello.question")
				<input type="text" name="name" size="10"/> 
				<input type="submit" value="?" class="QuestionMark" />
			</p>
		</form>

		<a href="#springMessage("homeURL")">#springMessage("home")</a>
	</div>

</body>
</html>