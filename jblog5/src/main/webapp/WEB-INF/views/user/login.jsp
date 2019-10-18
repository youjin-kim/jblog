<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/user-menu.jsp" />
		<form class="login-form" method="post"
			action="${pageContext.servletContext.contextPath }/user/login">
			<label class="block-label" for="id">아이디</label> 
			<input type="text" id="id" name="id"> 
			
			<label class="block-label">패스워드</label> 
			<input type="password" name="password" style="margin-bottom:10px; width:230px; padding:5px">

			<c:if test="${result.equals('fail') }">
				<p>로그인이 실패 했습니다.</p>
			</c:if>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
