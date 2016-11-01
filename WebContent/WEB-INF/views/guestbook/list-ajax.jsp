<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var isEnd = false;
	var page = 0;
	var render = function(vo) {
		//
		// 현업에서는 이 부분을 template library ex)ejs
		//
		var htmls = "<li id='gb-" + vo.no + " '><p><span>" + vo.name
				+ "</span>" + "<span>" + vo.reg_date + "</span></p>"
				+ "<a href='' title='삭제' no=' " + vo.no + " '>삭제</a><div>" + vo.content
				+ "</div></li>";

		$("#list-guestbook").append(htmls);
	}
	var fetchList = function() {
		if (isEnd == true) {
			return;
		}
		++page;
		$.ajax({
					url : "${pageContext.request.contextPath }/api/guestbook?a=ajax-list&p="
							+ page,
					type : "get",
					dataType : "json",
					success : function(response) { // response.result = "success" or "fail"
						// response.data = [{}, {}, {}....]
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
					error : function(jqXHR, status, e) {
						console.log(staus + ":" + e);
					}
				});
	};

	$(function() {
		// 1번째 리스트 가져오기
		fetchList();

		// 삭제 버튼 click event (live event)
		$(document).on("click", "#list-guestbook li a", function() {
			event.preventDefault();
			console.log("여기서 비밀번호를 입력 받는 modal dialog를 띄웁니다.");
			$(this).attr('id');
			
			dialog = $("#dialog-form").dialog({
				height : 200,
				width : 350,
				modal : true,
				buttons : {
					"삭제" : gbDelete,
					"취소" : function() {
						dialog.dialog("close");
					}
				},
				close : function() {
					gbForm.reset();
				}
			})
			dialog.dialog("open");
		});

		var gbDelete = function() {
			console.log(no);
					$.ajax({
						url : "${pageContext.request.contextPath }/api/guestbook?a=ajax-deletet&no="
								+ vo.no + "&password=" + pw,
						type : "get",
						dataType : "json",
						success : function(response) { // response.result = "success" or "fail"
																   // response.data = [{}, {}, {}....]
							if (response.result != "success") {
								console.error(response.message);
								return;
							}
							;
						},
						error : function(jqXHR, status, e) {
							console.log(staus + ":" + e);
						}
					});
		}

		$("#add-form").submit(function(event) {
			event.preventDefault();

			// ajax insert
		});

		$(window).scroll(function() {
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();

			// 스크롤바가 바닥까지 왔을때 (20px 덜 왔을때) 
			if (scrollTop + windowHeight + 40 > documentHeight) {
				fetchList();
			}
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1 align=center>
					<img src="/mysite3/assets/images/guest2.png">
				</h1>
				<form id="add-form" action="" method="post">
					<input type="hidden" name="a" value="add">
					<table id='table1'>
						<tr>
							<td class="info2"><label for='id2'><p id="id"
										title='이름'>&nbsp;</p></label></td>
							<td rowspan=4><textarea name="content" id="content2"
									placeholder='내용을 입력하세요.'></textarea></td>
						</tr>
						<tr>
							<td><input id='id2' class="info" type="text" name="name"
								placeholder='이름' title="이름"></td>
						</tr>
						<tr>
							<td class="info2"><label for='pw2'><p id='password'
										title='비밀번호'>&nbsp;</p></label></td>
						</tr>
						<tr>
							<td><input id='pw2' class="info" type="password" name="pass"
								placeholder='비밀번호' title="비밀번호"></td>
						</tr>
						<tr>
							<td colspan=2 align=right><input id='submit' type="submit"
								VALUE="" title="완료"></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">
				</ul>

				<div id="dialog-form" title="삭제" style="display:none;">
					<p class="">비밀번호를 입력하세요</p>
					<form id="gbForm">
						<label for="pw">비밀번호</label> <input type="pw" name="pw" id="pw"
							value="" class=""> <input type="submit" tabindex="-1"
							style="position: absolute; top: -1000px">
					</form>
				</div>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>