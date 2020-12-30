<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	ul, li{margin:0;padding:0;list-style-type:none;}
	li{float:left; width:15%; height:35px; line-height:35px; 
	   border-bottom:1px solid lightblue;}
	li:nth-child(4n+2){width:55%;}
	.wordCut{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
</style>
</head>
<body>
<h1>답변형 게시판 리스트</h1>
<a href="/test/answerWrite">글쓰기</a>
<ul>
	<li>번호</li>
	<li>제목</li>
	<li>작성자</li>
	<li>날짜</li>
	
	<c:forEach var="vo" items="${list }">
		<li>${vo.no }</li>
		<li class="wordCut">
			<!-- 공백을  step -->
			<c:forEach var="s" begin="1" end="${vo.step }">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${vo.step>0 }">
				☞
			</c:if>
			<a href="/test/answerView?no=${vo.no}">${vo.subject }</a></li>
		<li>${vo.userid }</li>
		<li>${vo.writedate }</li>
	</c:forEach>
</ul>
</body>
</html>