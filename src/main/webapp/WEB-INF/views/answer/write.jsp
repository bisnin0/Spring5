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
<h1>계층형 게시판 글쓰기</h1>
<form method="post" action="/test/answerWriteOk">
	제목 : <input type="text" name="subject" size="60"/><br/>
	<textarea name="content" rows="5" cols="60"></textarea><br/>
	<input type="submit" value="등록"/>
</form>
</body>
</html>