<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- Custom CSS -->
<link href="css/home.css" rel="stylesheet">
<link href="css/agency.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

</head>
<body style="background-color:#617C96">
<%@ include file="navandheader.jsp"%>
<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_audio where chapter_id='${audio.getChapter_id()}'" var="result" />
<div class="container">
		<div class="row" style="margin-top: 110px;">
		<div class="col-md-1"></div>
			<!--********start tab*********-->

			<div class="col-md-10">
		<div class="container" style="width: 100%">
							<!--------------Popular Book------------>
							<div style="margin-top: 20px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbsp;All audio from this chapter
								</label>&nbsp <label style="font-size: 16px">ออดิโอ</label>

								<!--pic book content-->
								<div class="row">

									<c:forEach var="row" items="${result.rows}">
										<div class="col-md-3" style="border-style: solid; border-width: 5px;">
											<div class="row">
												<div class="col-md-12 col-sm-12 container show-pic-home" style="width: 100%;">
													<!--image-->
													<a href="SelectAudioServlet?audioid=${row.audio_id}&userid=${user.getId()}"><img src="img/audio.jpg" alt="ชื่อหนังสือ"
														class="img-thumbnail center-block" style="height: 240px; width: 170px; max-width: 170px; max-hight: 240px;"></a>
												<div style="position:absolute;top:213px;width:170px;height:26px;right:29px;border-width:1px;background-color:#fff;opacity:0.6;">
					<div style="text-align:center">					
					<span class="glyphicon glyphicon-heart-empty" title="favorite"></span><label>${row.audio_countview}</label>					
					</div>
				</div>
												</div>
												<!--container image-->
											</div>

											<div class="row">
												<div classs="col-md-12 col-sm-12 ">

													
												</div>
											</div>

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
														<tr>
														<sql:query dataSource="${webappDataSource}" sql="select * from tbl_user where id='${row.user_id}'" var="showuser" />
														<c:forEach var="row" items="${showuser.rows}">
															<td class="col-md-12 col-sm-12 by-user">${row.username}</td></c:forEach>
														</tr>
														
													</tbody>
												</table>
											</div>
										</div>
									</c:forEach>
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
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/recorderjs/classie.js"></script>
	<script src="js/recorderjs/cbpAnimatedHeader.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/recorderjs/agency.js"></script>
</body>
</html>