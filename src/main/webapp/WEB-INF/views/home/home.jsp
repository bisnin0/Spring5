<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>	
</head>
<body>
<h1>
	<c:if test="${logStatus!='Y'}"><a href="/test/login">로그인</a></c:if>
	<c:if test="${logStatus!=null && logStatus=='Y'}"><a href="/test/logout">로그아웃</a></c:if> 
	<a href="/test/boardList">게시판</a>
	<a href="/test/answerList">계층형 게시판</a>
</h1>

<P><a href="/test/tran">트랙잭션테스트</a></P>
</body>
</html>
