<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Audio and Book Sharing</title>

<script src="js/audiodisplay.js"></script>
<script src="js/recorderjs/recorder.js"></script>
<script src="js/main.js"></script>

<style>
html {
	overflow: hidden;
}

body {
	font: 14pt Arial, sans-serif;
	background: lightgrey;
	display: flex;
	flex-direction: column;
	height: 100vh;
	width: 100%;
	margin: 0 0;
}

canvas {
	display: inline-block;
	background: #202020;
	width: 50%;
	height: 25%; <!--
	box-shadow: 0px 0px 10px blue;
	-->
}

#controls {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-around;
	height: 20%;
	width: 100%;
}

#record {
	height: 15vh;
}

#record.recording {
	background: red;
	background: -webkit-radial-gradient(center, ellipse cover, #ff0000 0%, lightgrey 75%
		, lightgrey 100%, #7db9e8 100%);
	background: -moz-radial-gradient(center, ellipse cover, #ff0000 0%, lightgrey 75%,
		lightgrey 100%, #7db9e8 100%);
	background: radial-gradient(center, ellipse cover, #ff0000 0%, lightgrey 75%,
		lightgrey 100%, #7db9e8 100%);
}

#save, #save img {
	height: 15vh;
}

#save {
	opacity: 0.25;
}

#save[download] {
	opacity: 1;
}

#viz {
	height: 80%;
	width: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
}

@media ( orientation : landscape) {
	body {
		flex-direction: row;
	}
	#controls {
		flex-direction: column;
		height: 100%;
		width: 10%;
	}
	#viz {
		height: 100%;
		width: 90%;
	}
}
</style>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</head>
<body style="background-color: #617C96">
	<!-- #FFD756 -->

	<sql:setDataSource var="webappDataSource"
		driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb"
		user="root" password="" />
	<sql:query dataSource="${webappDataSource}"
		sql="select categories_id from tbl_categories" var="result1" />

	<c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>



	<div id="viz">
		<canvas id="analyser" width="1024" height="500"
			style="margin-top: 100px"></canvas>
		<canvas id="wavedisplay" width="1024" height="500"></canvas>
	</div>
	<div id="controls">

		<img id="record" style="margin-top: 100px; margin-left: -500px"
			src="img/rec.png" onclick="toggleRecording(this);">
		<!-- popup for upload  -->
		<button class="btn btn-lg btn-primary btn-block" data-toggle="modal"
			data-target="#popupupload1" style="margin-left: -500px" type="submit">Upload
			File</button>
		<div class="modal fade" id="popupupload1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #617C96">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" style="color: #FEC503">Upload your
							audio file </h4>
					</div>
					<div class="modal-body">
						<sql:setDataSource var="selectchapter"
							driver="com.mysql.jdbc.Driver"
							url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
						<sql:query dataSource="${selectchapter}"
							sql="select * from tbl_chapter where user_id='${user.getId()}'"
							var="result3" />

						<form action="InsertAudioServlet" method="POST"
							enctype="multipart/form-data">

							Chapter : &nbsp; <label class="radio-inline"> <input
								type="radio" name="selchap" id="nonchap" value=0> Not
								have chapter
							</label> <label class="radio-inline"> <input type="radio"
								name="selchap" id="havechap" value=1> Have Chapter
							</label> <br> <select class="form-control" name="chapter_id"
								id="selectchap" placeholder="Select">
								<c:forEach var="row" items="${result3.rows}">
									<option value="${row.chapter_id}">${row.chapter_name}</option>
								</c:forEach>
							</select><br> Audio name : <input type="text" class="form-control"
								name="audioname" placeholder="Audio name" /><br> Audio
							Description : <input type="text" class="form-control"
								name="audiodescription" placeholder="Audio Description" /><br>
							Audio File : <input type="file" name="fileaudio" size="50" /><br>
							Categories : <select class="form-control" name="categories"
								placeholder="Select">

								<c:forEach var="row" items="${result1.rows}">
									<option value="${row.categories_name}">${row.categories_name}</option>
								</c:forEach>
							</select><br> <input type="hidden" name="user_id"
								value="${user.getId()}">
							<button type="submit" class="btn btn-lg btn-primary">UPLOAD</button>
						</form>
					</div>
					<!-- <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						
					</div>  -->
				</div>
			</div>
		</div>

		<a id="save" style="margin-left: -500px" href="#"><img
			src="img/save1.png"></a>
	</div>

	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/vendor/bootstrap.min.js"></script>
	<script src="bootstrap/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>


	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/recorderjs/classie.js"></script>
	<script src="js/recorderjs/cbpAnimatedHeader.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/recorderjs/agency.js"></script>

	<script src="js/audiodisplay.js"></script>
	<script src="js/recorderjs/recorder.js"></script>
	<script src="js/record.js"></script>


</body>
</html>