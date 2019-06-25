<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>


</head>
<body style="background-color: #617C96">

	
	
	
	<div class="container" style="margin-top: 120px; margin-bottom: 120px; max-width: 500px;background-color: white">
	<br>
		<form name="formreg" class="form-reg" action="InsertUserServlet" method="POST" enctype="multipart/form-data" >
			Username &nbsp;<input type="text" id="username" name="U" class="form-control" placeholder="Username" required autofocus><br> Password <input type="password" id="password" name="P"
				class="form-control" placeholder="Password" required autofocus><br> Confirm password <input type="password" id="repassword" name="RP" class="form-control"
				placeholder="Confirm Password" required autofocus><br> Name <input type="text" id="name" name="N" class="form-control" placeholder="Name" required autofocus> <br>
			Lastname<input type="text" id="lastname" name="L" class="form-control" placeholder="Lastname" required autofocus><br> Gender &nbsp; <label class="radio-inline"> <input
				type="radio" name="optionsRadiosGender" id="optionsRadiosMale" value="Male" checked> Male
			</label> 
			<label class="radio-inline"> 
			<input type="radio" name="optionsRadiosGender" id="optionsRadiosFemale" value="Female"> Female</label><br> <br> 
			Email <input type="email" id="email" name="E" class="form-control" placeholder="Email" required autofocus><br> 
			Photo <input type="file" id="photo" name="photo" size="50" /><br> <label class="radio-inline"> <input type="radio" name="optionsUsertype" id="Normaluser" value="Normal User" checked> Normal User
			</label> <label class="radio-inline"> <input type="radio" name="optionsUsertype" id="Blinduser" value="Blind User"> Blind User
			</label><br> <br>
			<button class="btn btn-lg btn-primary btn-block"  type="submit">Accept</button>

		</form>
	</div>
	<script type="text/javascript" src="js/onsubmit_regist.js"></script>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/vendor/bootstrap.min.js"></script>
	<script src="bootstrap/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/recorderjs/classie.js"></script>
	<script src="js/recorderjs/cbpAnimatedHeader.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/recorderjs/agency.js"></script>
	
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
</body>
</html>