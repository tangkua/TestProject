<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- Custom CSS -->
<link href="css/agency.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
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
	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-md-5" style="text-align: center;">
				<div class="outerbox">
					<div class="innerbox">
						<video style="margin-top: 20px" controls="" autoplay="">
							<source src="${audio.getFileaudio()}">
						</video>
					</div>
				</div>
			</div>

			<div class="col-md-7">
				<h2 class="break-word" style="color: #Fec503" align="left">1. การสร้างสรรค์สร้างสรรค์</h2>
				<footer style="text-align: left; color: #Fec503">
					จากหนังสือ <i>สร้างเว็บด้วยยยอะไรซักอย่างที่อะไรก็ไม่รู้ คิดไม่ออก </i>
				</footer>
				<strong class="username">allovish</strong> <strong> - </strong> <small class="date-book">DD/MM/YY</small><br>

				<div class="tag-issues">
					<span class="glyphicon glyphicon-tag" style="font-size: 14px; color: #F49C14"></span>

					
						<span class="tag-issue">tag1</span>
						<span class="tag-issue">tag2</span>
					
				</div>

				<div class="col-md-12" style="margin-top: 40px">
					<small>เกลี่ยวกับอะไรที่ไม่ยาก ไม่โดน ไม่ตลก ไม่ชอบ ท้องอืด ปวดขี้ลิตตเต้อลลลลลลลลลลลลลลล</small>
					<dl class="dl-horizontal" style="margin-top: 10px; text-align: left;">
						<dt>ผู้แต่ง</dt>
						<dd>คมสันต์ มาลีสี</dd>
						<dt>สำนักพิมพ์</dt>
						<dd>สำนักพิมพ์ ต้นไม้ต้นใหญ่ร่มเย็น</dd>
					</dl>
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