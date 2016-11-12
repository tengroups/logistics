<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
                <base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>Title</title>
	
	</head>
	<body>

   <h2>${message}</h2>
   <img alt="" src="${file}">
   <%--  <a href="${file}">下载</a> 代码正确浏览器兼容问题导致图片不显示--%>
    
	</body>
</html>
