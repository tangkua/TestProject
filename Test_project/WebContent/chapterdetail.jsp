<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- Custom CSS -->
<link href="css/home.css" rel="stylesheet">
<link href="css/agency.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//www.htmlcommentbox.com/static/skins/bootstrap/twitter-bootstrap.css?v=0" />
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

	<style type="text/css">
#HCB_comment_box #HCB_comment_form_box {
	padding-bottom: 1em
}

#HCB_comment_box .hcb-link {
	cursor: pointer
}

#HCB_comment_box .hcb-icon {
	border: 0px transparent none
}

#HCB_comment_box textarea {
	display: block;
	width: 100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	width: 100%
}

#HCB_comment_box blockquote {
	margin: 10px;
	overflow: hidden
}

#HCB_comment_box .hcb-err {
	color: red
}

#HCB_comment_box .hcb-comment-tb {
	margin: 0
}

#HCB_comment_box .comment {
	position: relative
}

#HCB_comment_box .comment .likes {
	position: absolute;
	top: 0;
	right: 0;
	opacity: 0.8
}

#HCB_comment_box .comment .hcb-comment-tb a {
	visibility: hidden
}

#HCB_comment_box .comment:hover .hcb-comment-tb a {
	visibility: visible
}

#HCB_comment_box .gravatar {
	padding-right: 2px
}

#HCB_comment_box input {
	margin-left: 0
}
</style>
</head>
<body style="background-color: #617C96">

	<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_chapteruser where user_id='${user.getId()}' and chapter_id='${chapter.getChapter_id()}'" var="result" />

	<c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>
	<div class="container" style="margin-top: 100px; border: 1px solid #fff;">
		<div class="row" style="border: 1px solid #fff;">
			<div class="col-md-8" style="border: 1px solid #fff;">
				<iframe class="center-block" src="ShowPdfServlet?chapter_id=${chapter.getChapter_id()}" frameborder="0" style="width: 100%; height: 1024px; margin-bottom: 30px"></iframe>
			</div>
			<div class="col-md-4" style="border: 1px solid #fff;">
				<h2 class="break-word" style="color: #Fec503" align="left">${chapter.getChapter_name()}</h2>
				<footer style="text-align:right;color:#Fec503">จากหนังสือ <i>${book.getTitle()}</i></footer>
				<strong class="username">${book.getOwner()}</strong> <strong> - </strong> <small class="date-book">${chapter.getChapter_datetime()}</small><br>

				<div class="tag-issues">
					<span class="glyphicon glyphicon-tag" style="font-size: 14px; color: #F49C14"></span> 
					<sql:setDataSource var="webappData" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
<sql:query dataSource="${webappData}" sql="select * from tbl_categories where categories_id='${book.getCategories_id()}'" var="result11" />
						<c:forEach var="row" items="${result11.rows}">
							<span class="tag-issue">${row.categories_name} </span>
						</c:forEach>

					<form action="CountFavServlet" method="Post">
						<c:forEach var="row" items="${result.rows}">
							<input type="hidden" name="favchap" value="${row.favorite_chapter}">
							<c:set var="favchapter" value="${row.favorite_chapter}" />
						</c:forEach>
						<input type="hidden" name="bookid" value="${book.getBook_id()}">
						<input type="hidden" name="userid" value="${user.getId()}"> <input type="hidden" name="chapterid" value="${chapter.getChapter_id()}"> <input type="submit"
							<c:choose><c:when test="${favchapter==0}">class="w8-button iconize" id="w8-d-blue"
   			</c:when><c:when test="${favchapter==1}">
       class="w8-button iconize2" id="w8-d-blue"
    </c:when></c:choose> />${row.favorite_chapter}
					</form>
				<form class="form-inline" action="PinChapterServlet" method="post">
				<input type="hidden" name="chapterid" value="${chapter.getChapter_id()}">
				<input type="hidden" name="userid" value="${user.getId()}">
				<c:forEach var="row" items="${result.rows}">
					<span ><input type="text" name="pinchapter"class="form-control" placeholder="Input chapter page here" value="${row.pin_chapter}"></span>
					</c:forEach>
  					<button type="submit" class="btn btn-default">Pin it</button>
				</form>

				</div>

				<div class="col-md-12" style="margin-top: 40px">
					<small>${chapter.getChapter_description()}</small>
					<dl class="dl-horizontal" style="margin-top: 10px; text-align: left;">
						<dt>ผู้แต่ง</dt>
						<dd>${book.getAuthor()}</dd>
						<dt>สำนักพิมพ์</dt>
						<dd>${book.getPublisher()}</dd>
					</dl>
				</div>
				<div style="margin-top: 50px">
					<h4>
						<a href="ShowFullScreenServlet?chapter_id=${chapter.getChapter_id()}" title="ดูแบบเต็มจอ">Full Screen</a>
					</h4>
					
					
					

				</div>
				<br>
				<div class="tag-issues">
<!-- begin htmlcommentbox.com -->
 <div   id="HCB_comment_box"><a href="http://www.htmlcommentbox.com">Comment Form</a> is loading comments...</div>
 <link rel="stylesheet" type="text/css" href="//www.htmlcommentbox.com/static/skins/bootstrap/twitter-bootstrap.css?v=0" />
 <script type="text/javascript" id="hcb"> /*<!--*/ if(!window.hcb_user){hcb_user={};} (function(){var s=document.createElement("script"), l=(""+window.location).replace(/'/g,"%27") || hcb_user.PAGE, h="//www.htmlcommentbox.com";s.setAttribute("type","text/javascript");s.setAttribute("src", h+"/jread?page="+encodeURIComponent(l).replace("+","%2B")+"&opts=16862&num=10");if (typeof s!="undefined") document.getElementsByTagName("head")[0].appendChild(s);})(); /*-->*/ </script>
<!-- end htmlcommentbox.com -->
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
	<script src="js/home.js"></script>
	<script src="js/recorderjs/agency.js"></script>

</body>
</html>