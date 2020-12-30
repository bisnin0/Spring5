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
	<h1>답글쓰기</h1>
	<form method="post" action="/test/replyWriteFormOk">
		<!-- 원글 글번호 -->
		<input type="hidden" name="no" value="${no}"/>
		제목 : <input type="text" name="subject" size="50"/><br/>
		글내용 : <textarea name="content"></textarea><br/>
		<input type="submit" value="답글등록"/>
	</form>
</body>
</html>