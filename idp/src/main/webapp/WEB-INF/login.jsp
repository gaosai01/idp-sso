<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String base = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统一认证系统，登录页面</title>
<script type="text/javascript" src="<%=base %>/assets/jquery.js"></script>
</head>
<body>
	<form action="doLogin" method="post">
		<input type="hidden" id="appkey" name="appkey" value="<%=request.getAttribute( "appkey" )==null ? "" : request.getAttribute( "appkey" ) %>"/>
		<input type="text" id="username" name="username" value="13070537189" />
		<br/>
		<input type="text" id="pwd" name="pwd" value="123456" />
	</form>
	<input type="submit" onclick="login()"/>
	<script>
	var logining = false;
	function login(){
		if( logining ){
			return;
		}
		logining = true;
		$.ajax({
            type: "POST",
            url: "doLogin",
            data:{
            	appkey:$("#appkey").val(),
            	username:$("#username").val(),
            	pwd:$("#pwd").val()
            },
            dataType: "json",
            success: function(data){
           	 	if( data.code == 3 ){
           	 		alert( "登录成功" );
           	 		location.href = data.data.redirect;
           	 	}else{
           	 		alert( data.msg );
           	 	}
           	 	logining = false;
            },
            error: function(){
           		alert( "服务器异常" );
              	logining = false;
            }
        });
	}
	</script>

</body>
</html>