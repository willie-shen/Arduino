<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mood Pet</title>
</head>
<body>

	<div id = "error"></div>
	<form name = "myForm" method = "GET" action = "MoodInput">
		<p>Enter a value between 0-20</p>
	<input type = "text" id = "moodValue" name = "moodValue"> <button type = "submit" value = "Submit">Submit</button>
	<p>0 = purr</p>
	<p>1 = happy</p>
	<p>2 = sad</p>
	<p>Else = Color Blend</p>
	</form>
	
</body>
</html>