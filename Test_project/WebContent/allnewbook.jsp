<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>




<!DOCTYPE>
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


	<sql:setDataSource var="webappDataSource"
		driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb"
		user="root" password="" />

	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_book order by book_id desc " var="result" />

       <%@ include file="navandheader.jsp"%>

<div class="container">
		<div class="row" style="margin-top: 110px;">
		<div class="col-md-1"></div>
			<!--********start tab*********-->

			<div class="col-md-10">
		<div class="container" style="width: 100%">
		<!--new arrival-->
							<div style="margin-top: 40px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbsp;All New Arrival
								</label>&nbsp; <label style="font-size: 16px">มาใหม่ทั้งหมด</label>

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
				</div>			</div>
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