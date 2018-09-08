<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Business Casual - Start Bootstrap Theme</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/css/business-casual.min.css"
	rel="stylesheet">

</head>

<body>

	<h1 class="site-heading text-center text-white d-none d-lg-block">
		<span class="site-heading-upper text-primary mb-3">A Free
			Bootstrap 4 Business Theme</span> <span class="site-heading-lower"></span>
	</h1>
	<%-- <c:choose>
		<c:when test="${sessionScope.userid!=null}"> --%>
	<c:choose>
		<c:when test="${sessionScope.userid==null&&sessionScope.admin_userid==null}">
			<nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
				<div class="container">
					<a
						class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none"
						href="#">Start Bootstrap</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarResponsive" aria-controls="navbarResponsive"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav mx-auto">
							<li class="nav-item active px-lg-4"><a
								class="nav-link text-uppercase text-expanded" href="${path}">Home
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item px-lg-4"><a
								class="nav-link text-uppercase text-expanded"
								href="${path}/member/register.do">회원가입하기</a></li>
							<li class="nav-item px-lg-4"><a
								class="nav-link text-uppercase text-expanded"
								href="${path}/member/Login.do">로그인</a></li>
							
							<li class="nav-item px-lg-4"><a
								class="nav-link text-uppercase text-expanded"
								href="${path}/board/list.do">게시판</a></li>
							<li class="nav-item px-lg-4">
						</ul>
					</div>
				</div>
			</nav>
		</c:when>
		
		<%-- <c:when
			test="${sessionScope.admin_userid==null||sessionScope.userid==null}"> --%>
			<c:otherwise>
		<!-- Navigation -->
			<nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
				<div class="container">
					<a
						class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none"
						href="#">Start Bootstrap</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarResponsive" aria-controls="navbarResponsive"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav mx-auto">
							<li class="nav-item active px-lg-4"><a
								class="nav-link text-uppercase text-expanded" href="${path}">Home
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item px-lg-4"><a
								class="nav-link text-uppercase text-expanded"
								href="${path}/member/logout.do">로그아웃</a></li>
							<li class="nav-item px-lg-4"><a
								class="nav-link text-uppercase text-expanded"
								href="${path}/board/list.do">게시판</a></li>
							<%-- 	<c:if test="${sessionScope.userid!=null&&sessionScope.admin_name==null}"> --%>
							<c:if test="${sessionScope.userid!=null||sessionScope.admin_username==null&&sessionScope.admin_userid==null}">
							<li class="nav-item px-lg-4">
								<a
								class="nav-link text-uppercase text-expanded"
								href="${path}/member/view.do?userid=${userid}">회원정보보기</a></li>
							</c:if>
								
							<c:if test="${sessionScope.admin_userid!=null&&sessionScope.admin_name!=null}">
							<li class="nav-item px-lg-4">
								<a
								class="nav-link text-uppercase text-expanded"
								href="${path}/member/list.do">회원목록</a></li>
							</c:if>
								
						</ul>
					</div>
				</div>
			</nav>
		</c:otherwise>
	</c:choose>
</body>

</html>
