<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorator="layout/offline">
<head>
<title>Register</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<meta charset="UTF-8">
</head>
<body>
	<main layout:fragment="content">
		<article class="container" style="padding-top: 3%;">
		<header class="row">
				<blockquote class="flow-text left" th:text="${header}"><a href="<c:url value='/index.jsp' />" style="color:black">回首頁</a></blockquote>
				
			</header>
			<header class="row">
				<div class="col s12">
					<h3 class="center">註冊</h3>
					<h5 class="center">Sign up</h5>
				</div>
			</header>
			<section>
				<form action="<c:url value='register.do'/>" method="post">
					<div class="row">
						<div class="col s12">
							<label> *電子信箱(即為您的登入帳號): <input type="text"
								name="email" minlength="3" maxlength="30" data-length="30"
								required="required" />
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col s12">
							<label> 密碼： <input type="password" name="password"
								minlength="3" maxlength="15" data-length="15"
								required="required" />
							</label>
						</div>
					</div>
<!-- 					<div class="row"> -->
<!-- 						<div class="col s12"> -->
<!-- 							<label> 密碼確認： <input type="password" name="passwordcheck" -->
<!-- 								minlength="6" maxlength="15" data-length="10" -->
<!-- 								required="required" /> -->
<!-- 							</label> -->
<!-- 						</div> -->
					</div>
					<div class="row">
						<div class="col s12">
							<label> 使用者名稱： <input type="text" name="username"
								maxlength="16" data-length="16" required="required"/>
							</label>
						</div>
					</div>
					<div class="row">
<!-- 					<div class="col s12"> -->
<!-- 							<label> 性別: <input type="text" name="gender" -->
<!-- 								maxlength="2" data-length="10" /> -->
<!-- 							</label> -->
<!-- 						</div> -->
					
						<div class="col s12">
							<label for="" class="t1">性別:</label> 
							<input type="radio" name="gender" value="male" id="m1" required="required">
							<label for="m1">男</label>
							<input type="radio" name="gender" value="female" id="f1"><label for="f1">女
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col s12">
							<label> 年齡： <input type="text" name="age"
								maxlength="2" data-length="10" required="required"/>
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col s12 center">
							<p class="center">In case of the success, you will be
								redirected to login page</p>
							<p class="center">Otherwise change your username or email</p>
						</div>
					</div>
					<div class="row">
						<div class="col s12 center">
							<button class="btn waves-effect waves-light btn-large"
								type="submit" name="register">註冊</button>
						</div>
					</div>
				</form>
			</section>
		</article>
	</main>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</body>
</html>