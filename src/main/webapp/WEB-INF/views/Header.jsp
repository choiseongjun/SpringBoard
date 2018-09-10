<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<%@ page session="true"%>
<html lang="en">
   <head>
   
  
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">    <title>UCS   </title>
      <!-- Favicon -->
      
      <!-- Bootstrap CSS -->
      <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
      <!-- Animate CSS -->
      <link href="${pageContext.request.contextPath}/assets/vendors/animate/animate.css" rel="stylesheet">
      <!-- Icon CSS-->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendors/font-awesome/css/font-awesome.min.css">
      <!-- Owlcarousel CSS-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendors/owl_carousel/owl.carousel.css" media="all">
      <!--Template Styles CSS-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css" media="all" />
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
   </head>
   <body>
      <div class="bg-grediunt">
         <div class="bg-banner-img clip-ellipse">
            <div class="ovrllay">
           
               <!-- Header_Area -->
               <nav class="navbar navbar-default header_aera affix-top">
                  <div class="container m-s">
                     <!-- Brand and toggle get grouped for better mobile display -->
                     <div class="col-md-4 p0">
                        <div class="navbar-header">
                           <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#min_navbar">
                           <span class="sr-only">Toggle navigation</span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                           <span class="icon-bar"></span>
                           </button>
                           <a class="navbar-brand logo-biss" href="${path}"> <img src="${pageContext.request.contextPath}/assets/images/logo_img.png"></a>
                        </div>
                     </div>
                     <c:choose>
		<c:when test="${sessionScope.userid==null}">
                     <!-- Collect the nav links, forms, and other content for toggling -->
                     <div class="col-md-8 p0">
                        <div class="collapse navbar-collapse" id="min_navbar">
                          <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown submenu">
                           <a href="${path}" class="">Home</a>
                        </li>
                         <li class="dropdown submenu">
                           <a href="${path}/member/register.do" class="">회원가입</a>
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/member/Login.do" class="">로그인하기</a>
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/board/list.do" class="">게시판</a> 
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/message.do" class="">생각중</a>
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path }" class="">생각중</a>
                        </li>
                      
                     </ul>
                        </div>
                       </div>
                   </c:when>
                  <c:otherwise>
                    <div class="col-md-8 p0">
                        <div class="collapse navbar-collapse" id="min_navbar">
                          <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown submenu">
                           <a href="${path}" class="">Home</a>
                        </li>
                         <li class="dropdown submenu">
                           <a href="${path}/member/view.do?userid=${userid}" class="">나의 정보 </a>
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/member/logout.do" class="">로그아웃하기</a>
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/board/list.do" class="">게시판</a> 
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/ChatList.do" class="">임시</a>
                        </li>
                        <li class="dropdown submenu">
                           <a href="${path}/member/list.do" class="">회원목록</a>
                        </li>
                      
                     </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                     </div>
                     </c:otherwise>
                    </c:choose>
                  </div>
                  <!-- /.container -->
               </nav>
               <!-- End Header_Area -->
               <!-- #banner start -->
              
            </div>
            </div>
            </div>
            
      
         <!-- /#banner end -->
        
        
      <!--#End Our footer Area -->
      <!-- The following is only needed when the video is in the html
         otherwise the who .hero__overlay html can be removed -->
      <div class="hero__overlay">
         <div class="hero__modal">
            <a class="hero__close" href="#">Close</a>
            <iframe allowscriptaccess="always" id="hero-video" class="hero__player" src="https://www.youtube.com/embed/1NSA8ycGfKg?enablejsapi=1&html5=1" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
         </div>
         <!-- /.hero__modal -->    
      </div>
      <!-- /.hero__overlay -->
      <!-- jQuery JS -->
 <%--      <script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.0.min.js"></script> --%>
      <!-- Bootstrap JS -->
      <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
       <script src="${pageContext.request.contextPath}/assets/vendors/popup/lightbox.min.js"></script>
      <!-- Animate JS -->
      <script src="${pageContext.request.contextPath}/assets/vendors/animate/wow.min.js"></script>
      <!-- Owlcarousel JS -->
      <script src="${pageContext.request.contextPath}/assets/vendors/owl_carousel/owl.carousel.min.js"></script>
      <!-- Stellar JS -->
    
      <!-- Theme JS -->
      <script src="${pageContext.request.contextPath}/assets/js/theme.min.js"></script>
   </body>
</html>