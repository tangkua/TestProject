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


	<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />

	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_book order by book_id desc limit 0,4" var="result" />

 <c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>
	



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
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbsp;New Arrival
								</label>&nbsp; <label style="font-size: 16px">มาใหม่</label>

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
							<br> <span class="pull-right"><a href="allnewbook.jsp">แสดงเพิ่มเติม</a></span> <br>
							<hr>
							<sql:query dataSource="${webappDataSource}" sql="select CHAPTER_ID,count(*) count_fav from tbl_chapteruser where FAVORITE_CHAPTER = 1 group by CHAPTER_ID order by 2 desc " var="result4" />
							<!--------------Popular Book------------>
							<c:forEach var="row" items="${result4.rows}">
								<sql:update dataSource="${webappDataSource}" var="count" sql="
   UPDATE tbl_chapter set CHAPTER_COUNTVIEW='${row.count_fav}' where chapter_id='${row.chapter_id}'" />
							</c:forEach>
							
							<sql:query dataSource="${webappDataSource}" sql="select book_id,count(*) count_favv from tbl_chapteruser where FAVORITE_CHAPTER = 1 group by book_id order by 2 desc" var="result5" />
							<!--------------Popular Book------------>
							<c:forEach var="row" items="${result5.rows}">
								<sql:update dataSource="${webappDataSource}" var="countt" sql="
   UPDATE tbl_book set COUNT_BOOK='${row.count_favv}' where book_id='${row.book_id}'" />
  
  							</c:forEach>
							
							
							
							
							<sql:query dataSource="${webappDataSource}" sql="select * from tbl_book order by count_book desc limit 0,4" var="result6" />
							
							<div style="margin-top: 20px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbsp;Popular Book
								</label>&nbsp; <label style="font-size: 16px">หนังสือยอดนิยม</label>

								<!--pic book content-->
								<div class="row">

									<c:forEach var="row" items="${result6.rows}">


										<div class="col-md-3" style="border-style: solid; border-width: 5px;">
											<div class="row">
												<div class="col-md-12 col-sm-12 container show-pic-home" style="width: 100%;">

													<!--image-->
													<a href="ShowBookDetailServlet?bookid=${row.book_id}"><img src="ShowBookSevlet?bookid=${row.book_id}" alt="${row.title}"
														class="img-thumbnail center-block" style="height: 240px; width: 170px; max-width: 170px; max-hight: 240px;"></a>
							<div style="position:absolute;top:213px;width:170px;right:29px;border-width:1px;background-color:#fff;opacity:0.6;">
					<div style="text-align:center">					
					<span class="glyphicon glyphicon-heart-empty" title="favorite"></span><label>${row.count_book}</label>					
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
													<a href="profile.html"><strong>${row.title}</strong></a>
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
									</c:forEach>
								</div>
							</div>
							<!--end row-->
							<br> <span class="pull-right"><a href="allfavbook.jsp">แสดงเพิ่มเติม</a></span> <br>
							<hr>



						</div>
						
						
						
						
						<!--end book tab-->
						<sql:query dataSource="${webappDataSource}" sql="select * from tbl_audio order by audio_id desc limit 0,4" var="result2" />

						<!-- Audio tabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb -->
						<div role="tabpanel" class="tab-pane fade in active" id="audio" aria-labelledby="book-tab">
							<!--new arrival-->
							<div style="margin-top: 40px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbspNew Arrival
								</label>&nbsp <label style="font-size: 16px">มาใหม่</label>

								<!--pic book content-->
								<div class="row">

									<c:forEach var="row" items="${result2.rows}">
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
														<sql:query dataSource="${webappDataSource}" sql="select * from tbl_user where id='${row.user_id}'" var="showuser" />
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
							<br> <span class="pull-right"><a href="allnewaudio.jsp">แสดงเพิ่มเติม</a></span> <br>
							<hr>



<sql:query dataSource="${webappDataSource}" sql="select AUDIO_ID,count(*) count_fav from tbl_audiouser where FAVORITE_AUDIO = 1 group by AUDIO_ID order by 2 desc " var="audior" />
							<!--------------Popular Book------------>
							<c:forEach var="row" items="${audior.rows}">
								<sql:update dataSource="${webappDataSource}" var="counttt" sql="
   UPDATE tbl_audio set AUDIO_COUNTVIEW='${row.count_fav}' where audio_id='${row.audio_id}'" />
							</c:forEach>
							

<sql:query dataSource="${webappDataSource}" sql="select * from tbl_audio order by audio_countview desc limit 0,4" var="result66" />
							<!--------------Popular Book------------>
							<div style="margin-top: 20px">
								<label style="font-size: 24px"> <span class="glyphicon glyphicon-tasks" aria-hidden="true"></span> &nbspPopular Audio
								</label>&nbsp <label style="font-size: 16px">ออดิโอยอดนิยม</label>

								<!--pic book content-->
								<div class="row">

									<c:forEach var="row" items="${result66.rows}">
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
													<a href="profile.html"><strong>${row.audio_name}</strong></a>
												</div>
											</div>

											<div class="row">
												<table class="col-md-12" align="center">
													<tbody>
													<tr>
															<td class="col-md-12 " style="text-align: right;"><p class="glyphicon glyphicon-time time-home">${row.audio_datetime}</p></td>
														</tr>
														<tr>
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
							<!--end row-->
							<br> <span class="pull-right"><a href="allfavaudio.jsp">แสดงเพิ่มเติม</a></span> <br>
							<hr>



						</div>
					</div>
					<!--audio page-->
				</div>
				<!--tab contain-->
			</div>
			<div class="col-md-1"></div>
			<!--big-container ครอบ tab-->
		</div>
		<!--end md-10-->



	</div>
	<!--end tab content-->

	<!--end container-->

	<script type="text/javascript">
		$(document).ready(function() {
			$('#myTab a[href="#book"]').tab('show');
		});
	</script>


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