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
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
			<h1 align=center><img src="/mysite3/assets/images/guest2.png"></h1>
				<form action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="a" value="add">
					<table id='table1'>
						<tr>
							<td class="info2"><label for='id2'><p id="id" title='이름'>&nbsp;</p></label></td>
							<td id="box" rowspan=4><textarea name="content" id="content2" placeholder='내용을 입력하세요.'></textarea></td>
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
				<ul>
				<c:set var="count" value="${fn:length(list) }"/> 
				<c:forEach items="${list }" var="vo" varStatus="status">
					<li>
						<table id='table2'>
							<tr>
								<td>[${count-status.index }]</td>
								<td>[${vo.no }]</td>
								<td>[${vo.name }]</td>
								<td>[${vo.reg_date }]</td>
								<td>
								<form action="${pageContext.request.contextPath }/guestbook" method="post">
									<input type='hidden' name="a" value="deleteform">
									<input type="hidden" name="no" value="${vo.no }">
									<input type="submit" value="삭제">			
								</form>
								</td>
							</tr>
							<tr>
								<td colspan=4>${fn:replace(vo.content, newLine, "<br>") }</td>
							</tr>
						</table>
						<br>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>