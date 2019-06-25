<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="bootstrap/css/signin.css" rel="stylesheet">
</head>
<body>
<div>
	<form  >
		Audio Name : <input type="text" name="audioname" class="form-control" placeholder="Audio Name" /> 
		Audio Description : <input type="text" name="audiodesciption" class="form-control" placeholder="Audio Description" /> 
		Upload Audio File : <input type="file" name="uploadfile">
		<button class="btn btn-lg btn-primary btn-block" type="submit"> Send </button>
			
	</form>
	</div>
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="bootstrap/js/vendor/bootstrap.min.js"></script>
		<script src="bootstrap/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</body>
</html>