<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	ul, li{margin:0;padding:0;list-style-type:none}
	#lst>li{float:left; width:10%; height:40px; line-height:40px; 
	   border-bottom:1px solid gray}
	#lst>li:nth-child(5n+2){width:60%}
	.wordCut{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
</style>
</head>
<body>
<div>
	<h1>리스트</h1>
	<a href="/test/boardWrite">글쓰기</a>
	<ul id="lst">
		<li>no</li>
		<li>subject</li>
		<li>userid</li>
		<li>hit</li>
		<li>writedate</li>
		<c:forEach var="vo" items="${list }">
			<li>${vo.no }</li>
			<li class="wordCut"><a href="/test/boardView?no=${vo.no}">${vo.subject}</a></li>
			<li>${vo.userid}</li>
			<li>${vo.hit}</li>
			<li>${vo.writedate}</li>
		</c:forEach>
	</ul>
</div>
</body>
</html>