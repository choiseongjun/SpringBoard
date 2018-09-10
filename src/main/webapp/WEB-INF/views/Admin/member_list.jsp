<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp"%>
</head>
<style>
body {
	margin-top: 20px;
	background: #eee;
}

a {
	color: #f96332;
}

.m-t-5 {
	margin-top: 5px;
}

.card {
	background: #fff;
	margin-bottom: 30px;
	transition: .5s;
	border: 0;
	border-radius: .1875rem;
	display: inline-block;
	position: relative;
	width: 100%;
	box-shadow: none;
}

.card .body {
	font-size: 14px;
	color: #424242;
	padding: 20px;
	font-weight: 400;
}

.profile-page .profile-header {
	position: relative
}

.profile-page .profile-header .profile-image img {
	border-radius: 50%;
	width: 140px;
	border: 3px solid #fff;
	box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23)
}

.profile-page .profile-header .social-icon a {
	margin: 0 5px
}

.profile-page .profile-sub-header {
	min-height: 60px;
	width: 100%
}

.profile-page .profile-sub-header ul.box-list {
	display: inline-table;
	table-layout: fixed;
	width: 100%;
	background: #eee
}

.profile-page .profile-sub-header ul.box-list li {
	border-right: 1px solid #e0e0e0;
	display: table-cell;
	list-style: none
}

.profile-page .profile-sub-header ul.box-list li:last-child {
	border-right: none
}

.profile-page .profile-sub-header ul.box-list li a {
	display: block;
	padding: 15px 0;
	color: #424242
}
</style>
<body>
	<link
		href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
		rel="stylesheet">
	<div class="container profile-page">
		<link
			href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
			rel="stylesheet">
		<div class="container profile-page">
			<div class="row">
				<div class="col-xl-6 col-lg-7 col-md-12">
					<div class="card profile-header">
						<div class="body">
							<div class="row">
								<c:forEach var="row" items="${list}">
									<div class="col-lg-4 col-md-4 col-12">
										<div class="profile-image float-md-right">
											<img src="${path}/resources/assets/images/ProFilePicture/${row.profileimage}"
												width="400px" height="400px">
											<!-- <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt=""> -->
										</div>
									</div>
									<div class="col-lg-8 col-md-8 col-12">
										<a href="${path}/member/userinfo.do/${row.userid}"><h4 class="m-t-0 m-b-0">
												<strong>이름:${row.name}</strong>
											</h4></a> <span class="job_post" data-userid="${row.userid}">아이디:${row.userid}</span>
										<p>이메일:${row.email}</p>
										<div>
											<button class="btn btn-primary btn-round">Follow</button>
											<button class="btn btn-primary btn-round btn-simple"
												onclick="sendMessage('${row.userid}')">Message</button>
										</div>
										<p class="social-icon m-t-5 m-b-0">
											<a title="Twitter" href="javascript:void(0);"><i
												class="fa fa-twitter"></i></a> <a title="Facebook"
												href="javascript:void(0);"><i class="fa fa-facebook"></i></a>
											<a title="Google-plus" href="javascript:void(0);"><i
												class="fa fa-twitter"></i></a> <a title="Behance"
												href="javascript:void(0);"><i class="fa fa-behance"></i></a>
											<a title="Instagram" href="javascript:void(0);"><i
												class="fa fa-instagram "></i></a>
										</p>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function sendMessage(otherUserid) {//사용자 아이디를 클릭하면..
			//var otherUserid = $(this).attr("data-userid");
			$.ajax({
				url : '${path}/setOtherUserid.do',
				type : 'POST',
				data : {
					'otherUserid' : otherUserid
				},
				success : function(data) {
					if (data == 1) {
						location.href = "${path}/message.do";
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		};
	</script>
</body>
</html>