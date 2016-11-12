<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>后台管理登录页面</title>
<link rel="stylesheet" type="text/css" href="content/css/login/bootstrap-social.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>content/login/bootstrap-theme.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>content/login/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>content/login/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>content/login/templatemo_style.css"/>

</head>
<body class="templatemo-bg-image-1">
	<div class="container">
		<div class="col-md-12">			
			<form class="form-horizontal templatemo-login-form-2" role="form" action="validateLogin" method="post">
				<div class="row">
					<div class="col-md-12">
						<h1>后台管理登录</h1>
					</div>
				</div>
				<div class="row">
					<div class="templatemo-one-signin col-md-6">
				        <div class="form-group">
				          <div class="col-md-12">		          	
				            <label for="username" class="control-label">用户名</label>
				            <div class="templatemo-input-icon-container">
				            	<i class="fa fa-user"></i>
				            	<input type="text" class="form-control" id="username" name="username" placeholder="">
				            </div>		            		            		            
				          </div>              
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <label for="password" class="control-label">密码</label>
				            <div class="templatemo-input-icon-container">
				            	<i class="fa fa-lock"></i>
				            	<input type="password" class="form-control" id="password" name="password" placeholder="">
				            </div>
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <div class="checkbox">
				                <label>
				                  <input type="checkbox"> 记住我
				                </label>
				            </div>
				          </div>
				        </div>
				        <div class="form-group">
				          <div class="col-md-12">
				            <input type="submit" value="登录" class="btn btn-warning">
				          </div>
				        </div>
				        <div class="form-group">
				          	<div class="col-md-12">
				        		<a href="forgot-password.html" class="text-center">忘记密码？</a>
				       	 	</div>
				    	</div>
					</div>
					<div class="templatemo-other-signin col-md-6">
						<label class="margin-bottom-15">
							message
						</label>
						
					</div>   
				</div>				 	
		      </form>		      		      
		</div>
	</div>
</body>
</html>
