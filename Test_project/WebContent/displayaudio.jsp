<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
	

	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_audiouser where user_id='${user.getId()}' and audio_id='${audio.getId()}'" var="result" />
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
				<div class="outerbox" style="border-style:solid;border-width: 5px">
					<div class="innerbox">
						<a href="ShowAudioServlet?audioid=${audio.getId()}"><img src="img/play.jpg"></a>
						
					</div>
				</div>
			</div>

			<div class="col-md-7">
				<h2 class="break-word" style="color: #Fec503" align="left">${audio.getName()}</h2>
				<sql:query dataSource="${webappDataSource}" sql="select * from tbl_chapter where chapter_id='${audio.getChapter_id()}'" var="getchapter"/>
	<c:forEach var="row" items="${getchapter.rows}">
				<footer style="text-align: left; color: #Fec503">
					จาก Chapter <a href="ShowChapAudioServlet?chapterid=${row.chapter_id}"><i>${row.chapter_name}</i></a>
				</footer>
				</c:forEach>

	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_user where id='${audio.getUser_id()}'" var="getuser"/>
	<c:forEach var="row" items="${getuser.rows}">
				<strong class="username">${row.username}</strong></c:forEach><strong> - </strong> <small class="date-book">${audio.getAudiodate()}</small><br>

				<div class="tag-issues">
					<span class="glyphicon glyphicon-tag" style="font-size: 14px; color: #F49C14"></span>
<sql:query dataSource="${webappDataSource}" sql="select * from tbl_categories where categories_id='${audio.getCategories_id()}'" var="getcat"/>
					<c:forEach var="row" items="${getcat.rows}">
						<span class="tag-issue">${row.categories_name}</span>
						</c:forEach>
					<form action="CountFavAudioServlet" method="Post">
						<c:forEach var="row" items="${result.rows}">
							<input type="hidden" name="favaudio" value="${row.favorite_audio}">
							<c:set var="favaudio" value="${row.favorite_audio}" />
						</c:forEach>
						<input type="hidden" name="audioid" value="${audio.getId()}">
						<input type="hidden" name="userid" value="${user.getId()}"> 
						 
						<input type="submit" 
							<c:choose><c:when test="${favaudio==0}">class="w8-button iconize" id="w8-d-blue"
   			</c:when><c:when test="${favaudio==1}">
       class="w8-button iconize2" id="w8-d-blue"
    </c:when></c:choose> />${row.favorite_audio}
					</form>
					<form class="form-inline" action="PinAudioServlet" method="post">
				<input type="hidden" name="audioid" value="${audio.getId()}">
				<input type="hidden" name="userid" value="${user.getId()}">
				<c:forEach var="row" items="${result.rows}">
					<span ><input type="text" name="pinaudio" class="form-control" placeholder="Input audio time here" value="${row.pin_audio}"></span>
					</c:forEach>
  					<button type="submit" class="btn btn-default">Pin it</button>
				</form>
				</div>

				<div class="col-md-12" style="margin-top: 40px">
					<small>${audio.getDetail()}</small>
					<dl class="dl-horizontal" style="margin-top: 10px; text-align: left;">
					
						
					</dl>
				</div>
<!-- begin htmlcommentbox.com -->
 <div id="HCB_comment_box"><a href="http://www.htmlcommentbox.com">Comment Box</a> is loading comments...</div>
 <link rel="stylesheet" type="text/css" href="//www.htmlcommentbox.com/static/skins/bootstrap/twitter-bootstrap.css?v=0" />
 <script type="text/javascript" id="hcb"> /*<!--*/ if(!window.hcb_user){hcb_user={};} (function(){var s=document.createElement("script"), l=(""+window.location).replace(/'/g,"%27") || hcb_user.PAGE, h="//www.htmlcommentbox.com";s.setAttribute("type","text/javascript");s.setAttribute("src", h+"/jread?page="+encodeURIComponent(l).replace("+","%2B")+"&opts=16862&num=10");if (typeof s!="undefined") document.getElementsByTagName("head")[0].appendChild(s);})(); /*-->*/ </script>
<!-- end htmlcommentbox.com -->
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
	<script src="js/home.js"></script>
	<script src="js/recorderjs/agency.js"></script>
</body>
</html>