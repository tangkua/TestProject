<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
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
</head>
<body style="background-color: #617C96">
<sql:setDataSource var="selectchapter" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
<sql:query dataSource="${selectchapter}" sql="select * from tbl_chapter where user_id='${user.getId()}'" var="result3" />
<sql:query dataSource="${selectchapter}" sql="select categories_name from tbl_categories" var="result1" />
<c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>

<div class="container" style="margin-top: 120px; margin-bottom: 120px; max-width: 500px;background-color:white" >
	
	
	<br>
	<button class="btn btn-warning "  data-toggle="modal" data-target="#editcover">Change audio file</button><br>
	<form action="UpdateAudioDetailServlet" method="POST">
		<input type="hidden" name="audioid" value="${audio.getId()}">
		<br>
		
		Chapter : &nbsp; <label class="radio-inline"> <input type="radio" name="selchap" id="nonchap" value=0 checked> Not have chapter
							</label> <label class="radio-inline"> <input type="radio" name="selchap" id="havechap" value=1> Have Chapter
							</label> <br> <select class="form-control" name="chapter_id" id="selectchap" placeholder="Select">
								<c:forEach var="row" items="${result3.rows}">
									<option value="${row.chapter_id}">${row.chapter_name}</option>
								</c:forEach>
							</select><br>
					Audio name : <input type="text" class="form-control" name="audioname" value="${audio.getName()}" /><br> Audio Description : <input type="text" value="${audio.getDetail()}" class="form-control" name="audiodescription"
						placeholder="Audio Description" /><br>  Categories : <select class="form-control"
								name="categories" placeholder="Select">
								
								<c:forEach var="row" items="${result1.rows}">
									<option value="${row.categories_name}">${row.categories_name}</option>
								</c:forEach>
							</select><br> 
	<input type ="hidden" name="userid" value="${user.getId()}">
		<button class="btn  btn-primary " type="submit">UPDATE</button>
	</form>
	
	
	</div>
	
	<!-- Chapter Upload Modal -->
<div class="modal fade" id="editcover" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #617C96">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" style="color: #FEC503">Update your audio</h4>
			</div>
			<div class="modal-body">
				<form action="UpdateAudioFileServlet" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="audioid" value="${audio.getId()}">
					Audio file : <input type="file" id="audiofile" name="audiofile" size="50" /><br>
					<button class="btn btn-lg btn-primary " type="submit">UPDATE</button>
				</form>
			</div>

		</div>
	</div>
</div>
	
	<%--${user}  expression language --%>
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
</body>
</html>