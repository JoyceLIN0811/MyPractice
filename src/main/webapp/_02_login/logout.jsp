<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en-US" xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="utf-8" />
<meta name="description"
	content="Programming forum about Java powered by Spring Framework" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>登出</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
</head>
<body>
	<!-- 先將使用者名稱取出 -->
	<c:set var="memberName" value="${ LoginOK.username }" />
	<!-- 移除放在session物件內的屬性物件 -->
	<c:remove var="LoginOK" scope="session" />
	<c:remove var="ShoppingCart" scope="session" />
	<!-- 下列敘述設定變數funcName的值為OUT，top.jsp 會用到此變數 -->
	<c:set var="funcName" value="OUT" scope="session" />
<body>
	<nav>
		<div class="nav-wrapper">
			<div class="container">
				<ul id="nav-left" class="left hide-on-med-and-down">
					<li style="font-size: 25px; color: white">專屬毛孩子的討論區</li>
				</ul>

				<ul id="nav-left" class="right hide-on-med-and-down">
				<li><a href="<c:url value='/_04_Topic/topics.jsp' />">討論區</a></li>
					<li><a href="<c:url value='/_02_login/login.jsp' />">登入</a></li>
					<li><a href="<c:url value='/_01_register/register.jsp' />">註冊</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<main layout:fragment="content">
		<p align="center">

			<!-- 下列六行敘述設定登出後要顯示的感謝訊息 -->
			<c:set var="logoutMessage" scope="request" />
			<font color='blue' ><BR> ${LoginOK.username}感謝您使用本系統。<BR>
				您已經登出<BR> </font>
			<%-- 	<jsp:useBean id='logoutBean' class='logoutBean' scope='page' /> --%>

<%-- 			<c:set target='${logoutBean}' property='session' --%>
<%-- 				value='${pageContext.session}' /> --%>

			${ logoutBean.logout }

<%-- 			<c:redirect url="/index.jsp" /> --%>

		</p>
	</main>
	<footer class="page-footer">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">你想說的 想知道的，都在這裡。</h5>
					<p class="white-text">想要了解毛孩的想法或身體狀況? 想增進和毛孩之間的關係?
						想知道最新、最正卻的營養、保健知識，還有如何正確地與毛小孩互動?</p>
					<p class="white-text">在這裡你不只可以找到網友與家中毛孩相處的心情、情感、生活分享以及正確、健康的飼育觀念，協助你找出問題的關鍵原因，提出適當方法協助您有效解決一切有關毛孩的問題。</p>
				</div>
				<div class="col l4 offset-l2 s12">
					<h5 class="white-text">Links</h5>
					<ul>
						<li><a class="white-text" th:href="aboutUs.html">關於我們</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</body>
</html>