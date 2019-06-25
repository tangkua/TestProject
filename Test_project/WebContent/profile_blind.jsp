<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	 <c:set var="typeuser" value="${user.getUsertype()}" />
<c:choose>
<c:when test="${typeuser == 'Normal User'}">
       <%@ include file="navandheader.jsp"%>
    </c:when>
    <c:when test="${typeuser == 'Blind User'}">
        <%@ include file="navblind.jsp"%>
    </c:when>
</c:choose>
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
  

	<sql:setDataSource var="webappDataSource"
		driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/rvcdb"
		user="root" password="" />
	<sql:query dataSource="${webappDataSource}"
		sql="select * from tbl_book where owner='${user.getUsername()}'"
		var="result" />

	<sql:query dataSource="${webappDataSource}"
		sql="select * from tbl_book b,tbl_chapteruser cu where b.book_id=cu.book_id and user_id='${user.getId()}' and cu.favorite_chapter='1' group by cu.book_id"
		var="favvbok" />
		
		<sql:query dataSource="${webappDataSource}"
		sql="select * from tbl_audio a,tbl_audiouser au where a.audio_id=au.audio_id and au.user_id='${user.getId()}' and au.favorite_audio='1' group by au.audio_id"
		var="favvau" />


	<div class="container">

		<div class="row" style="margin-top: 100px;">
			<!---------- profile -------->
			<div class="col-md-3 col-sm-12 col-xs-12"
				style="background-color: #394264; border-radius: 6px 6px 6px 6px; margin-bottom: 30px;">
				<table class="col-md-12 center-block">
					<tr>
						<td><img src="PhotoServlet?U=${user.getUsername()}"
							alt="user name" class="img-circle center-block"
							style="border-color: #3498db; border-style: solid; height: 150px; width: 150px; margin-top: 40px"
							title="รูปประจำตัว">

							<button class="btn btn-info btn-sm"
								style="position: absolute; top: 160px; opacity: 0.8; margin-left: 115px"
								data-toggle="modal" data-target="#uploadpic">
								<span class="glyphicon glyphicon-picture"
									title="Change profile picture"></span>
							</button></td>
					</tr>
					<tr>
						<td>
							<div class="break-word" align="center"
								style="width: 265px; margin-top: 30px; color: #bbebde;">
								<strong>${user.getUsername()}</strong>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="break-word" align="center"
								style="margin-top: 10px; height: 30px; width: 265px; color: #bdc3c7; font-size: 12px">${user.getFirstName()}&nbsp;${user.getLastName()}</div>
						</td>
					</tr>
					<tr>
						<td>
							<button type="button" class="btn btn-default center-block"
								style="margin-top: 20px; margin-bottom: 20px"
								data-toggle="modal" data-target="#uploadprofile">Edit</button>
						</td>
					</tr>
				</table>

			</div>
			<!--********start tab*********-->

			<div class="col-md-9">
				<!--<span class="data-tab">-->
				<div class="container" style="width: 100%">
					

					<div class="tab-content">
						<!--book page!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
						
						<!--end book tab-->

						<sql:query dataSource="${webappDataSource}"
							sql="select * from tbl_audio where user_id='${user.getId()}'"
							var="resulta" />

						

							
							
							<div style="margin-top: 20px">
								<label style="font-size: 24px"> <span
									class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
									&nbsp;Favorite Audio
								</label>&nbsp; <label style="font-size: 16px">ออดิโอที่ชื่นชอบ</label>

								<!--Fav audio-->
								<div class="row">

									<c:forEach var="row" items="${favvau.rows}">
										<div class="col-md-3"
											style="border-style: solid; border-width: 5px;">
											<div class="row">
												<div class="col-md-12 container show-pic-home"
													style="width: 100%;">
													<!--image-->
													<a href="SelectAudioServlet?audioid=${row.audio_id}&userid=${user.getId()}">
													<img
														src="img/audio.jpg"
														alt="${row.audio_name}" class="img-thumbnail center-block"
														style="height: 240px; width: 170px; max-width: 170px; max-hight: 240px;"></a>
														<div
														style="position: absolute; top: 213px; width: 170px; border-width: 1px; background-color: #fff; opacity: 0.6;">
														<div style="text-align: center">
															<span class="glyphicon glyphicon-heart-empty"
																title="favorite"></span><label>${row.audio_countview}</label>
														</div>
														
													</div>
												</div>
												<!--container image-->
											</div>

											

											<div class="row">
												<div class="col-md-12 title break-word" align="center"
													style="height: 60px;">
													<a href="SelectAudioServlet?audioid=${row.audio_id}&userid=${user.getId()}"><b>${row.audio_name}</b></a>
												</div>
											</div>

											<div class="row">
												<table class="col-md-12" align="center">
													<tbody>
														
														<tr>
															<td class="col-md-12 " style="text-align: right;"><p
																	class="glyphicon glyphicon-time time-home"
																	>${row.audio_datetime}</p></td>
														</tr>
														<tr>
														<c:forEach var="row" items="${getuser.rows}">
															<td class="col-md-12 by-user"><strong>${row.username}</strong></td>
															</c:forEach>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</c:forEach>
								</div>
								<hr>
							</div>
							
						</div>
						<!--tab contain-->
					</div>
					<!--big-container ครอบ tab-->
				</div>
				<!--end md-10-->
			</div>
			<!--end tab content-->
		</div>
		<!--end container-->

		<!-- Upload User Pic -->
		<div class="modal fade" id="uploadpic" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #617C96">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" style="color: #FEC503">Change Profile
							Picture</h4>
					</div>
					<div class="modal-body">
						<form name="formreg" action="UpdateUserServlet" method="POST"
							enctype="multipart/form-data">
							Username : ${user.getUsername()} &nbsp;<input type="hidden"
								id="username" name="U" value="${user.getUsername()}"
								class="form-control" disabled required autofocus><br>
							Photo <input type="file" id="photo" name="photo" size="50" /><br>
							<input type="hidden" name="userID" value="${user.getId()} ">

							<button class="btn btn-lg btn-primary " type="submit">Accept</button>


						</form>
					</div>

				</div>
			</div>
		</div>

		<!-- Upload User Profile -->
		<div class="modal fade" id="uploadprofile" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #617C96">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" style="color: #FEC503">Edit Profile
							</h4>
					</div>
					<div class="modal-body">
						<form name="formreg" action="UpdateUserDetailServlet"
							method="POST">
							Username &nbsp;<input type="text" id="username" name="U"
								value="${user.getUsername()}" class="form-control"
								placeholder="Username" disabled required autofocus><br>
							Password <input type="password" value="${user.getPassword()}"
								id="password" name="P" class="form-control"
								placeholder="Password" required autofocus><br>
							Confirm password <input type="password"
								value="${user.getPassword()}" id="repassword" name="RP"
								class="form-control" placeholder="Confirm Password" required
								autofocus><br> Name <input type="text" id="name"
								name="N" value="${user.getFirstName()}" class="form-control"
								placeholder="Name" required autofocus> <br>
							Lastname<input type="text" value="${user.getLastName()}"
								id="lastname" name="L" class="form-control"
								placeholder="Lastname" required autofocus><br>
							Gender &nbsp; <label class="radio-inline"> <input
								type="radio" name="optionsRadiosGender" id="optionsRadiosMale"
								value="Male" ${user.getGender()=='Male'?'checked':''}>
								Male
							</label> <label class="radio-inline"> <input type="radio"
								name="optionsRadiosGender" id="optionsRadiosFemale"
								value="Female" ${user.getGender()=='Female'?'checked':''}>
								Female
							</label><br> <br> Email <input type="email"
								value="${user.getEmail()}" id="email" name="E"
								class="form-control" placeholder="Email" required autofocus><br>
							<label class="radio-inline"> <input type="radio"
								name="optionsUsertype" id="Normaluser" value="Normal User"
								${user.getUsertype()=='Normal User'?'checked':''}>
								Normal User
							</label> <label class="radio-inline"> <input type="radio"
								name="optionsUsertype" id="Blinduser" value="Blind User"
								${user.getUsertype()=='Blind User'?'checked':''}> Blind
								User
							</label><br> <br> <input type="hidden" name="userID"
								value="${user.getId()} ">

							<button class="btn btn-lg btn-primary " type="submit">SAVE</button>


						</form>
					</div>

				</div>
			</div>
		</div>


		<script
			src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
			
		</script>
		<script type="text/javascript">
			function showData(value) {
				$.ajax({
					url : "ShowBookDetailServlet?bookid=" + value,
					type : "POST",
					async : false,
					success : function(data) {
						//Do something with the data here
					}
				});
			}
		</script>



		<script type="text/javascript">
			$(document).ready(function() {
				$('#myTab a[href="#book"]').tab('show');
			});
		</script>

		<script type="text/javascript">
			$(function() {
				$(".fav").button({
					icons : {
						primary : "ui-icon-locked"
					}
				});

				$("body").on(
						'click',
						".fav",
						function() {
							$(this).find('.ui-button-icon-primary')
									.toggleClass(
											"ui-icon-locked ui-icon-unlocked");
							return false;
						});
			});
		</script>



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

		<script type="text/javascript">
			$('#editbook').on('show.bs.modal', function(event) {
				var button = $(event.relatedTarget) // Button that triggered the modal
				var recipient = button.data('whatever') // Extract info from data-* attributes
				// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
				// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
				var modal = $(this)

				modal.find('.bookk input').val(recipient)
			})
		</script>
</body>
</html>