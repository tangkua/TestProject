<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.ite.rvc.user.User"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	User currentUser1 = new User();
%>
<%
	currentUser1 = (User) session.getAttribute("user");
%>
<%
	if (currentUser1 == null) {
		response.sendRedirect("index.jsp");
	}
%>






<sql:setDataSource var="webappDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
<sql:query dataSource="${webappDataSource}" sql="select * from tbl_categories" var="result1" />

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top" style="background-color: black">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand page-scroll" href="hometest.jsp">Voice and Book Sharing</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a href="#page-top"></a></li>
				<li><a class="page-scroll" href="about.jsp"><span class="glyphicon glyphicon-tree-conifer" aria-hidden="true"></span>About</a></li>
				<!-- <li><a class="page-scroll dropdown-toggle" data-toggle="dropdown" aria-expanded="true" href="#services">Upload</a></li>  -->
				<li><a class="page-scroll dropdown-toggle" data-toggle="dropdown" aria-expanded="true" href="#services"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>Categories</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					<c:forEach var="row" items="${result1.rows}">
							<li role="presentation"><a role="menuitem" tabindex="-1"  href="SelectCatServlet?catid=${row.categories_id}&usertype=${user.getUsertype()}">${row.categories_name}</a></li>
						</c:forEach>
						
					</ul></li>
				
				
				<li ><a class="page-scroll dropdown-toggle" data-toggle="dropdown" aria-expanded="true" href="#"><img style="height: auto; width: auto; max-width: 30px; max-height: 25px;"
								src="PhotoServlet?U=${user.getUsername()}" width="30" height="40" /> <c:if
							test="${loginValid == true}">
	 ${user.getUsername()}
							<br></a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
						<li role="presentation"><a role="menuitem" tabindex="-1" href="DisplayBookServlet?username=${user.getUsername()}&usertype=${user.getUsertype()}"> Profile</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1" href="useredit.jsp" >Edit</a></li>

					</ul></li>
				<li><a class="page-scroll" href="logout.jsp"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>Logout</c:if> <c:if test="${loginValid == false}">
	FAIL <%
 	response.sendRedirect("index.jsp");
 %>
						</c:if></a></li>
						<li><a class="page-scroll"  data-toggle="modal" data-target="#search"><span class="glyphicon glyphicon-search"></span></a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
<sql:setDataSource var="selectchapter" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
						<sql:query dataSource="${selectchapter}" sql="select * from tbl_chapter where user_id='${user.getId()}'" var="result3" />
<!-- Audio Upload Modal -->
<div class="modal fade" id="popupupload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #617C96">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" style="color: #FEC503">Upload your audio file</h4>
			</div>
			<div class="modal-body">
				<form action="InsertAudioServlet" method="POST" enctype="multipart/form-data">
				Chapter : &nbsp; <label class="radio-inline"> <input type="radio" name="selchap" id="nonchap" value=0 checked> Not have chapter
							</label> <label class="radio-inline"> <input type="radio" name="selchap" id="havechap" value=1> Have Chapter
							</label> <br> <select class="form-control" name="chapter_id" id="selectchap" placeholder="Select">
								<c:forEach var="row" items="${result3.rows}">
									<option value="${row.chapter_id}">${row.chapter_name}</option>
								</c:forEach>
							</select><br>
					Audio name : <input type="text" class="form-control" name="audioname" placeholder="Audio name" /><br> Audio Description : <input type="text" class="form-control" name="audiodescription"
						placeholder="Audio Description" /><br> Audio File : <input type="file" name="fileaudio" size="50" /><br>  Categories : <select class="form-control"
								name="categories" placeholder="Select">
								<option value="0">None</option>
								<c:forEach var="row" items="${result1.rows}">
									<option value="${row.categories_name}">${row.categories_name}</option>
								</c:forEach>
							</select><br> <input type="hidden" name="user_id" value="${user.getId()}">
					<button type="submit" class="btn btn-lg btn-primary">UPLOAD</button>
				</form>
			</div>

		</div>
	</div>
</div>

<!-- Book Upload Modal -->
<div class="modal fade" id="popupuploadbook" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #617C96">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" style="color: #FEC503">Upload your book</h4>
			</div>
			<div class="modal-body">
				<form action="InsertBookServlet" method="POST" enctype="multipart/form-data">
					Title : <input type="text" name="title" class="form-control" placeholder="Title"><br> Author : <input type="text" name="author" class="form-control" placeholder="Author"><br>
					Publisher : <input type="text" name="publisher" class="form-control" placeholder="publisher"><br> Description : <input type="text" name="description" class="form-control"
						placeholder="Description"><br> Categories : <select class="form-control" name="categories" placeholder="Select">
						<c:forEach var="row" items="${result1.rows}">
							<option value="${row.categories_name}">${row.categories_name}</option>
						</c:forEach>
					</select><br> Cover : <input type="file" id="cover" name="cover" size="50" /><br> <input type="hidden" name="user_name" value="${user.getUsername()}">
					
					<button class="btn btn-lg btn-primary " type="submit">UPLOAD</button>
				</form>
			</div>

		</div>
	</div>
</div>


<sql:setDataSource var="selectbook" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb" user="root" password="" />
<sql:query dataSource="${selectbook}" sql="select * from tbl_book  where owner='${user.getUsername()}' " var="result2" />

<!-- Chapter Upload Modal -->
<div class="modal fade" id="popupuploadchapter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #617C96">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" style="color: #FEC503">Upload your chapter</h4>
			</div>
			<div class="modal-body">
				<form action="InsertChapterServlet" method="POST" enctype="multipart/form-data">
					Book : <select class="form-control" name="book_id" placeholder="Select">
						<c:forEach var="row" items="${result2.rows}">
							<option value="${row.book_id}">${row.title}</option>
						</c:forEach>
					</select> <br> Chapter Name : <input type="text" name="chaptername" class="form-control" placeholder="Chapter Name"><br> Description : <input type="text" name="chapterdescription"
						class="form-control" placeholder="Chapter Detail"><br> Chapter File : <input type="file" id="filechapter" name="filechapter" size="50" /><br> 
						<input type="hidden" name="user_id" value="${user.getId()}">
					<button class="btn btn-lg btn-primary " type="submit">UPLOAD</button>
				</form>
			</div>

		</div>
	</div>
</div>


<!-- Search -->
<div class="modal fade" id="search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="background-color: #617C96">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" style="color: #FEC503">Search</h4>
			</div>
			<div class="modal-body">
				<form action="SearchServlet" method="POST" >
				Search by &nbsp;
	<label class="radio-inline"> <input type="radio"
		name="searchtype" id="Audio" value="Audio" checked > Audio
	</label>
	
	<!-- <label class="radio-inline"> <input type="radio"
		name="searchtype" id="Chapter" value="Chapter"> Chapter
	</label>
	<label class="radio-inline"> <input type="radio"
		name="searchtype" id="User" value="User"> User
	</label> --><br>
					<input type="text" name="keyword" class="form-control" placeholder="Search for..."><br>
					<button type="submit" class="btn  btn-primary">Search</button>
					
				</form>
			</div>

		</div>
	</div>
</div>

