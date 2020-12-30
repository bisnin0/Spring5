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
<h1>로그인</h1>
<form method="post" action="/test/loginOk">
	아이디 <input type="text" name="userid"/><br/>
	비밀번호 <input type="password" name="userpwd"/><br/>
	<input type="submit" value="Login"/>
</form>
</body>
</html>