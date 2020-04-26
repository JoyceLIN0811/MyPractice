<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
	layout:decorator="layout/online">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<head>
<title>Topic</title>
</head>
<body>
	<main layout:fragment="content">
		<article class="container" style="padding: 3% 0 2%;">
			<header class="row">
				<div class="col s12">
					<blockquote class="flow-text left">
						<c:out value="${topic_content.title}">文章標題</c:out>
					</blockquote>
					<blockquote class="flow-text right">
						<a href="<c:url value='/_04_topic/topiclist' />"
							style="color: black">回文章列表</a>
					</blockquote>
				</div>
			</header>
			<section class="row">
				<div class="col s12">
					<p>
						<c:out value="${topic_content.username}"></c:out>
					</p>
					<br>
					<p class="left">
						發布時間:
						<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm"
							value="${topic_content.posttime}" />
					</p>
				</div>
			</section>
			<section class="row">
				<p class="col s12"
					style="text-align: justify; display: block; white-space: pre-wrap">
					<c:out value="${topic_content.content}">文章內容</c:out>
				</p>
			</section>


			<header class="row">
				<div class="col s12">
					<blockquote class="flow-text left">
						回覆數量:
						<c:out value="${ReplyTotalNum}"></c:out>
					</blockquote>
				</div>
			</header>


			<c:forEach varStatus="stars" var="aReplyBean"
				items="${reply_content}">
				<section class="row">
					<div class="col s12"
						style="margin: 3% 0 3%; padding: 2%; border-top-right-radius: 10px; border-bottom-right-radius: 10px; background: linear-gradient(to right, #e7e4e4, #f3f2f2); background: -webkit-linear-gradient(left, #e7e4e4, #f3f2f2); background: -o-linear-gradient(right, #e7e4e4, #f3f2f2); background: -moz-linear-gradient(right, #e7e4e4, #f3f2f2);">
						<span class="chip left" style="background-color: #f3f2f2"><c:out
								value="${aReplyBean.username}"></c:out></span> <span class="right">回覆時間:
							<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm"
								value="${aReplyBean.replytime}" />
						</span>
						<div class="row">
							<p class="col s12"
								style="text-align: justify; display: block; white-space: pre-wrap">
								回覆內容:<br>
								<c:out value="${aReplyBean.replycontent}"></c:out>
							</p>
						</div>
					</div>
				</section>
			</c:forEach>
			<header class="row">
				<div class="col s12">
					<blockquote class="flow-text left">新增回覆</blockquote>
				</div>
			</header>
			<section>
				<form action="<c:url value='addreply.do'/>" method="post">
					<div class="row">
						<div class="col s12">
							<label>Content: <textarea name="replycontent"
									class="materialize-textarea" maxlength="1024"
									data-length="1024" required="required"></textarea>
							</label>
						</div>
					</div>
					<input type="hidden" name="replytime" value="${reply.getReplytime()}" />
					<input type="hidden" name="topicid" value="${param.topicid }" />
					<input type="hidden" name="username" value="${reply.getUsername()}" />
					<div class="row">
						<div class="col s12 center">
							<button class="btn waves-effect waves-light btn-large"
								type="submit" name="answer">回覆</button>
						</div>
					</div>
				</form>
			</section>
		</article>
	</main>
</body>
</html>