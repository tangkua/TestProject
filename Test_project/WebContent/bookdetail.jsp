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
	<c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>

	<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
	<sql:query dataSource="${webappDataSource}" sql="select * from tbl_chapter where book_id='${book.getBook_id()}' order by chapter_id asc" var="result" />


	<div class="container" >
			<div class="row"  style ="margin-top:100px;hight:500px">
				<div class="col-md-5" >
				
					<div class="col-md-12 container book-image" style="width:100%;" ><!--image-->
						<img src="ShowBookSevlet?bookid=${book.getBook_id()}" alt="ชื่อหนังสือ" class="img-thumbnail center-block" style="height:380px;width:300px; max-width:300px; max-hight:380px;">
					</div>


				</div>
				<div class="col-md-7" >
					<h2 class="break-word" style="color:#Fec503">${book.getTitle()}</h2>
					<strong class= "username" >${book.getOwner()} </strong>
					<strong> - </strong>
					<small class= "date-book">${book.getBookdate()} </small><br>
					
					<div class="tag-issues">
						<span class="glyphicon glyphicon-tag" style="font-size:14px;color:#F49C14"></span>
						<sql:setDataSource var="webappData" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
<sql:query dataSource="${webappData}" sql="select * from tbl_categories where categories_id='${book.getCategories_id()}'" var="result11" />
						<c:forEach var="row" items="${result11.rows}">
							<span class="tag-issue">${row.categories_name} </span>
						</c:forEach>
							
							
							<!-- <span class="tag-issue">tag2</span>  -->
						
					
					</div>
					<div class="col-md-12" style="margin-top:40px">
					<small>${book.getDetail() }</small>
					<dl class="dl-horizontal" style="margin-top:10px;text-align:left;">
  						<dt>ผู้แต่ง</dt>
  						<dd>${book.getAuthor()}</dd>
  						<dt>สำนักพิมพ์</dt>
  						<dd>${book.getPublisher()}</dd>
					</dl>
				</div>
			</div>



		</div>
		<div class="row" style="margin-top:20px">
			<h3></h3>
			<table class="col-md-12 table tb-show">
				<tbody>
					<c:forEach var="row" items="${result.rows}" varStatus="loop">
					<tr>
						<td class ="col-md-1">
						<h4 class="ch-number">
						<!--เลขchapter-->
  							 <c:out value="${loop.count}"/><p>
							</h4> 
							
						</td>
						<td class="col-md-3 break-word" >
							<a href="ShowChapterServlet?chapterid=${row.chapter_id}&userid=${user.getId()}&bookid=${book.getBook_id()}"><h4>${row.chapter_name}</h4></a>
							<div style="margin-top:10px" >
								<a href ="AudioFromChapServlet?chapterid=${row.chapter_id}">
									<button  class="btn btn-info btn-xs"><span class="glyphicon glyphicon-play-circle"  title="ออดิโอ">ออดิโอ</span></button>	
								</a>	
							</div>
						</td>
						<td class="col-md-7 break-word "  >
							<div class="describe small">${row.chapter_detail}</div>

							<div class="small pull-right" style ="margin-top:30px;" >
								
								&nbsp;<label class="label label-info glyphicon glyphicon-time "  title="เพิ่มเื่อเวลา....">${row.chapter_datetime}</label>
								&nbsp;<label class="label label-info glyphicon glyphicon-heart-empty "  title="favorite">${row.chapter_countview}</label>
							</div>
						</td>
						
					</tr>
					
					</c:forEach>

				</tbody>
			</table>
			

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