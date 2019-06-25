<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voice and Chapter Application : INDEX</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="bootstrap/css/signin.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

</head>
<body>
	


<% String username = request.getParameter("U");
	session.setAttribute("uname", username);
	String password = request.getParameter("P");
	session.setAttribute("pword", password);%>
	
	<div class="container">
	<br>
	<br>
	
		<h1 class="form-signin-heading" ><center><font color="#fed136">Login</font></center></h1>
		<form class="form-signin" action="LoginServlet" method="POST">
			<input type="text" name="U" class="form-control" placeholder="Username" /> <input type="password" class="form-control" placeholder="Password"  name="P"/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
			<br>   <a href="registrationform.jsp" style="pull-right">Sign up</a>
		</form>

	</div>
	<br>
	<br>
	<br>
	<br>

	

	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/vendor/bootstrap.min.js"></script>
	<script src="bootstrap/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

</body>
</html>

