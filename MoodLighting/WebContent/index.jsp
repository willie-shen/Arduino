<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mood Pet</title>
<script>
	function lock(){
		
		var input = parseInt(document.getElementById("moodValue").value);
	//	https://stackoverflow.com/questions/43015921/checking-if-input-is-integer-using-javascript
		if(!Number.isInteger(+document.getElementById("moodValue").value)){
		//	alert("ERROR");
			document.getElementById("error").innerHTML = "Error: You did not input an integer".fontcolor("red");
			document.getElementById("moodValue").value = "";
			document.getElementById("moodValue").reset();

			return false;
		}
		
		else if(input < 0 || intpu > 20){
			document.getElementById("error").innerHTML = ("Error: " + input + " is out of Bounds").fontcolor("red");
			document.getElementById("moodValue").value = "";
			document.getElementById("moodValue").reset();

			return false;
			
		}
		
		document.getElementById("button").disabled = true;
		//document.getElementById("button").innerHTML = "Please Wait...";

		var countDownDate = new Date(new Date().getTime() + 5000).getTime();

		// Update the count down every 1 second
		var x = setInterval(function() {

		  // Get todays date and time
		  var now = new Date().getTime();

		  // Find the distance between now an the count down date
		  var distance = countDownDate - now;

		  // Time calculations for days, hours, minutes and seconds
		 // var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		  //var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		  //var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

		  // Display the result in the element with id="demo"
		  document.getElementById("button").innerHTML = "Please Wait... " + seconds + "s ";

		  // If the count down is finished, write some text 
		  if (distance < 0) {
		    clearInterval(x);
			unlock();
			document.getElementById("button").innerHTML = "Submit"	
		  }
		}, 1000);
		
		//
		//setTimeout(unlock, 2000);
	}
	
	function unlock(){
		document.getElementById("button").disabled = false;
	}
</script>
</head>
<body>

	<div id = "error" font color = "red"></div>
	<form name = "myForm" method = "GET" action = "MoodInput">
		<p>Enter a value between 0-20</p>
	<input type = "text" id = "moodValue" name = "moodValue"> <button type = "submit" id = "button" value = "Submit" onclick = "lock()">Submit</button>
	<p>0 = purr</p> <!-- 60 seconds -->
	<p>1 = happy</p> <!-- 60 seconds -->
	<p>2 = sad</p> <!-- 60 seconds -->
	<p>Else = Color Blend</p> <!-- 50 seconds -->
	</form>
	
</body>
</html>