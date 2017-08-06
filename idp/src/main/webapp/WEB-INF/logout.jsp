<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String base = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统一认证系统，注销页面</title>
<script type="text/javascript" src="<%=base %>/assets/jquery.js"></script>

</head>
<body>

<ul id="spList">
</ul>
<script>
<% if( request.getAttribute( "loginUrls" ) != null ){
	List<String> ls = (List<String>)request.getAttribute( "loginUrls" );
	for( String url : ls ){ %>
	$.ajax({
             type: "GET",
             url: "<%=url %>",
             dataType: "jsonp",
             jsonpCallback: "callback",
             success: function(data){
                 $("#spList").append( "<li>"+data.msg+"</li>" );
             },
             error: function(){
            	 $("#spList").append( "<li>error</li>" );
             }
         });
<%}} %>


</script>

</body>
</html>