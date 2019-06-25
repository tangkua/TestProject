<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">

<!-- Custom CSS -->
<link href="css/home.css" rel="stylesheet">
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

</head>
<body style="background-color: #617C96">
 <c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>

	
<c:forEach var="item" items="${audios}">
    <b><c:out value="${item}" /></b>
</c:forEach>

	
	<sql:setDataSource var="selectchapter" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
	<sql:query dataSource="${selectchapter}"
		sql="select * from tbl_audio where audio_id='${audio.getId()}'"
		var="result3" />
	<c:forEach var="row" items="${result3.rows}">
		${row.audio_name}

	</c:forEach>
	
	<sql:query dataSource="${selectchapter}"
		sql="select * from tbl_book where book_id='${book.getBook_id()}'"
		var="result" />
		<c:forEach var="row" items="${result.rows}">
		${row.chapter_name}

	</c:forEach>
		

<div class="container">
		<div class="row" style="margin-top: 110px;">
			<div class="col-md-1"></div>
			<!--********start tab*********-->

			<div class="col-md-10">
				<!--<span class="data-tab">-->
				<div class="container" style="width: 100%">
					<ul class="nav nav-tabs" role="tablist" id="myTab">
						
     <li role="presentation" class="active"><a href="#book" aria-controls="Book" role="tab" data-toggle="tab"><b>Book</b></a></li>  
    			
						<li role="presentation"><a href="#audio" aria-controls="Audio" role="tab" data-toggle="tab"><b>Audio</b></a></li>
					</ul>
					
					
					<div class="tab-content">
						<!--book page-->
						<div role="tabpanel" class="tab-pane fade in active" id="book" aria-labelledby="book-tab">
							<!--new arrival-->
							<div style="margin-top: 40px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbsp;Search Results
								</label>&nbsp; <label style="font-size: 16px">ผลการค้นหา</label>

								<!--pic book content-->
								<div class="row">

									<c:forEach var="row" items="${result.rows}">
										<div class="col-md-3" style="border-style: solid; border-width: 5px;">
											
											<div class="row">
												<div class="col-md-12 col-sm-12 container show-pic-home" style="width: 100%;">
													<!--image-->
													<a href="ShowBookDetailServlet?bookid=${row.book_id}"><img src="ShowBookSevlet?bookid=${row.book_id}" alt="${row.title}" class="img-thumbnail center-block"
														style="height: 240px; width: 170px; max-width: 170px; max-hight: 240px;"> </a>
													
				<div style="position:absolute;top:213px;width:170px;right:29px;border-width:1px;background-color:#fff;opacity:0.6;">
					<div style="text-align:center">					
					<span class="glyphicon glyphicon-heart-empty" title="favorite"></span><label>${row.count_book}</label>					
				</div>
					</div>
												<!--container image-->
											</div>

											

											<div class="row">
												<div class="col-md-12 col-sm-12 title break-word" align="center" style="height: 60px;">
													<a href="ShowBookDetailServlet?bookid=${row.book_id}"><strong>${row.title}</strong></a>
												</div>
											</div>

											<div class="row">
												<table class="col-md-12" align="center">
													<tbody>
														<tr>
															<td class="col-md-12 col-sm-12 by-user">${row.owner}</td>
														</tr>
														<tr>
															<td class="col-md-12 " style="text-align: right;"><p class="glyphicon glyphicon-time time-home">${row.book_date}</p></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<!--end row-->
							<br>  <br>
							<hr>
							
							
							
							


						</div>
						
						
						
						<!-- Audio tabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb -->
						<div role="tabpanel" class="tab-pane fade in active" id="audio" aria-labelledby="book-tab">
							<!--new arrival-->
							<div style="margin-top: 40px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbsp;Search Results
								</label>&nbsp <label style="font-size: 16px">ผลการค้นหา</label>

								<!--pic book content-->
								<div class="row">

									<c:forEach var="row" items="${result3.rows}">
										<div class="col-md-3" style="border-style: solid; border-width: 5px;">
											<div class="row">
												<div class="col-md-12 col-sm-12 container show-pic-home" style="width: 100%;">
													<!--image-->
													<a href="SelectAudioServlet?audioid=${row.audio_id}&userid=${user.getId()}"><img src="img/audio.jpg" alt="${row.audio_name}" class="img-thumbnail center-block"
														style="height: 240px; width: 170px; max-width: 170px; max-hight: 240px;"></a>
				<div style="position:absolute;top:213px;width:170px;height:26px;right:29px;border-width:1px;background-color:#fff;opacity:0.6;">
					<div style="text-align:center">					
					<span class="glyphicon glyphicon-heart-empty" title="favorite"></span><label>${row.audio_countview}</label>					
					</div>
				</div>
												</div>
												</div>
												<!--container image-->
											

											
							

											<div class="row">
												<div class="col-md-12 col-sm-12 title break-word" align="center" style="height: 60px;">
													<a href="SelectAudioServlet?audioid=${row.audio_id}&userid=${user.getId()}"><strong>${row.audio_name}</strong></a>
												</div>
											</div>

											<div class="row">
												<table class="col-md-12" align="center">
													<tbody>
														<tr>
															<td class="col-md-12 " style="text-align: right;"><p class="glyphicon glyphicon-time time-home">${row.audio_datetime}</p></td>
														</tr>
														<sql:query dataSource="${selectchapter}" sql="select * from tbl_user where id='${row.user_id}'" var="showuser" />
														<c:forEach var="row" items="${showuser.rows}">
														<tr>
															<td class="col-md-12 col-sm-12 by-user">${row.username}</td>
														</tr>	
														</c:forEach>
														
													</tbody>
												</table>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<!--end row-->
							<br> <br>
							<hr>




							
							



						</div>
						
							</div>
							
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