<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
$(function(){
	getList();
	$("#btn-add-category").click(function(){
		var name = $("#category-name").val();
		var explain = $("#category-explain").val();
		
		var category = JSON.stringify({
			"name": name,
			"explain": explain
		});

		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/addcategory",
			type: "post",
			dataType: "json",
			data: category,
			contentType:"application/json;charset=UTF-8",
			success: function(data, response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				
				$("#category-name").val("");
				$("#category-explain").val("");
				$("#category-name").focus();
				getList();
				
			},
			error: function(xhr, error) {
				console.error("error: " + error);
			}
		});
	});
	
	function getList() {
		$("tr[target='_blank']").remove();
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/getlist",
			type: "get",
			dataType: "json",
			data: "",
			success: function(data, response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				var list = "";
				$.each(data, function(key, category) {
					list += "<tr target='_blank'>";
					list += "<td>"+eval(key+1)+"</td>";
					list += "<td>"+category.name+"</td>";
					list += "<td>"+category.countPost+"</td>";
					list += "<td>"+category.explain+"</td>";
					list += "<td><button data-value='"+category.no+"'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></button></td>";
					list += "</tr>";
				});
				$("#tbl-category").append(list);
				
			},
			error: function(xhr, error) {
				console.error("error:"+error);
			}
		});
	}
	
	$("#tbl-category").on("click", "button", function(event) {
		event.preventDefault();
		var value = $(this).data("value");
		var pageNum = $(this).data("page");
		var data = JSON.stringify({
			"categoryNo": value
		});
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/delete",
			type: "post",
			dataType: "json",
			data: data,
			contentType:"application/json;charset=UTF-8",
			success: function(data, response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				
				getList(pageNum);
			},
			error: function(xhr, error) {
				console.error("error:"+error);
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath }/${id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.servletContext.contextPath }/${id }/admin/write">글작성</a></li>
				</ul>
		      	<table id="tbl-category" class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="category-name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="category-explain" type="text" name="explain"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btn-add-category" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>