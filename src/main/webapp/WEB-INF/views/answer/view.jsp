<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>글내용보기</h1>
<ul>
	<li>번호 : ${vo.no }</li>
	<li>작성자 : ${vo.userid }</li>
	<li>등록일 : ${vo.writedate }</li>
	<li>제목 : ${vo.subject }</li>
	<li>글내용 : ${vo.content }</li>
</ul>
<a href="/test/replyWriteForm?no=${vo.no}">답글쓰기</a>
수정
삭제
</body>
</html>