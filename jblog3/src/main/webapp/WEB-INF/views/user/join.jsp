<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<title>JBlog</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
$(function(){
	$("#blog-id").change(function(){
		$("#btn-checkemail").show();
		$("#img-checkemail").hide();
	});	
	
	$("#btn-checkemail").click(function(){
		var id = $("#blog-id").val();
		if(id == ""){
			return;
		}
	
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/user/checkid?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result == "fail"){
					console.error(response.message);
					return;
				}
				
				if(response.data == true){
					alert("이미 존재하는 id입니다.");
					$("#blog-id").val("");
					$("#blog-id").focus();
					return;
				}
				
				$("#btn-checkemail").hide();
				$("#img-checkemail").show();
			},
			error: function(xhr, error) {
				console.error("error: " + error);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/user-menu.jsp" />
		<form class="join-form" id="join-form" method="post"
			action="${pageContext.servletContext.contextPath }/user/join">

			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text"> 
			
			<label class="block-label" for="blog-id">아이디</label>
         	<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkemail" type="button" value="중복체크">
			<img id="img-checkemail" style='display:none;'
				src="${pageContext.servletContext.contextPath }/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
