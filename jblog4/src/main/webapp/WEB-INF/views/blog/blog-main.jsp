<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<h4>${postVo.title }</h4>
				<p>
					${postVo.contents }
				<p>
				</div>
				<ul class="blog-list">
					<c:set var="count" value="${fn:length(postList) }" />
					<c:forEach items="${postList }" var="pl" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId }/${pl.categoryNo }/${pl.no }">${pl.title }</a> <span>${pl.updateDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>
		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:set var="count" value="${fn:length(categoryList) }" />
				<c:forEach items="${categoryList }" var="cl" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId }/${cl.no }">${cl.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>