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
		<h1>机构名称：${departInfo.department_name}</h1>
		管理员： ${departInfo.department_manager}
		<p>
		   联系方式： ${departInfo.department_mobile}
		</p>
		<p>
		   地址： ${departInfo.department_address}
		</p>
	</body>
</html>
