<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
var isEnd = false;
var page = 0;
var render = function(vo) {
	var htmls =	 "<li><table width=510 border=1><tr>" +
			"<td></td>" +
			"<td>" + vo.no + "</td>" +
			"<td>" + vo.name + "</td>" +
			"<td>" + vo.reg_date + "</td>" +
			"<td><form action='${pageContext.request.contextPath }/guestbook' method='post'>	<input type='hidden' name='a' value='deleteform'>" +
			"<input type='hidden' name='no' value='${vo.no }'><input type='submit' value='삭제'></form></td></tr><tr><td colspan=4>" + vo.content +"</td>" +
		"</tr></table><br></li>";
		
		$("#list-guestbook").append(htmls);
}
var fetchList = function() {
	if (isEnd == true) {
			return;
	}
	++page;
	$.ajax({
		url:"${pageContext.request.contextPath }/api/guestbook?a=ajax-list&p="+page,
		type:"get",
		dataType:"json",
		success:function(response) {
			if (response.result != "success") {
				console.error(response.message);
				isEnd = true;
				return;
			}
			// rendering
			$(response.data).each(function(index, vo) {
				render(vo);					
			});
			if (response.data.length < 5) {
				isEnd = true;
				$("#btn-fetch").prop("disabled", true);
			}
		},
		error: function(jqXHR, status, e) {
			console.log(staus + ":" + e);
		}
	});
};

$(function () {
	// 1번째 리스트 가져오기
	fetchList();
	
	$("#add-form").submit(function(event) {
		event.preventDefault();
		
		// ajax insert
	});
	
	$(window).scroll(function() {
		var $window = $(this);
		var scrollTop =$window.scrollTop(); 
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
		// 스크롤바가 바닥까지 왔을때 (20px 덜 왔을때) 
		if (scrollTop + windowHeight + 40 > documentHeight) {
			fetchList();
		}
	});
	$("#btn-fetch").click(function() {
		fetchList();
	});
});
</script>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
			<h1 align=center><img src="/mysite3/assets/images/guest2.png"></h1>
				<form id="add-form" action="" method="post">
					<input type="hidden" name="a" value="add">
					<table id='table1'>
						<tr>
							<td class="info2"><label for='id2'><p id="id" title='이름'>&nbsp;</p></label></td>
							<td rowspan=4><textarea name="content" id="content2" placeholder='내용을 입력하세요.'></textarea></td>
						</tr>
						<tr>
							<td><input id='id2' class="info" type="text" name="name" placeholder='이름' title="이름"></td>
						</tr>
						<tr>
							<td class="info2"><label for='pw2'><p id='password' title='비밀번호'>&nbsp;</p></label></td>
						</tr>
						<tr>
							<td><input id='pw2' class="info" type="password" name="pass" placeholder='비밀번호' title="비밀번호"></td>
						</tr>
						<tr>
							<td colspan=2 align=right><input id='submit' type="submit" VALUE="" title="완료"></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">
				</ul>
				<button style="magrin-top:20px;" id="btn-fetch">가져오기</button>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>