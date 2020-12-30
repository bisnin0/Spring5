<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.15.1/full/ckeditor.js"></script>
<script>
	$(function(){
		console.log('jquery');
        CKEDITOR.replace( 'content', {//해당 이름으로 된 textarea에 에디터를 적용
            width:'100%',
            height:'400px',
            filebrowserImageUploadUrl: 'community/imageUpload' //여기 경로로 파일을 전달하여 업로드 시킨다.
        });
        
        
        CKEDITOR.on('dialogDefinition', function( ev ){
        	console.log('dialogDefinition');
            var dialogName = ev.data.name;
            var dialogDefinition = ev.data.definition;
         
            switch (dialogName) {
                case 'image': //Image Properties dialog
                    //dialogDefinition.removeContents('info');
                    dialogDefinition.removeContents('Link');
                    dialogDefinition.removeContents('advanced');
                    break;
            }
        });  

	});
</script>
</head>
<body>
<h1>글등록 폼</h1>
<form method="post" action="/test/boardWriteOk">
	제목 : <input type="text" name="subject" style="width:70%"/><br/>
	글내용 : <textarea name="content" style="width:70%;height:100px"></textarea><br/>
	<input type="submit" value="등록하기"/>
</form>
</body>
</html>