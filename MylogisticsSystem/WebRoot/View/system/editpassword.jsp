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
<title>系统管理</title>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
	<script type="text/javascript">
	function checkinput(){
		var oldpassword=document.getElementById("oldpassword");
		var newpassword=document.getElementById("password");
		var newrepassword=document.getElementById("newrepassword");
		if(oldpassword.value==""||oldpasssword.value==null){
			alert("旧密码不能为空");
			return false;
		}
		if(newpassword.value==""||newpasssword.value==null){
			alert("新密码不能为空");
			return false;
		}
		if(newrepassword.value==""||newrepassword.value==null){
			alert("重复密码不能为空");
			return false;
		}
		if(newpassword.value!=newrepassword.value){
			alert("两次密码不一致");
			return false;
		}
		
	}
	</script>
</head>
<body>
<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
		<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
						<div class="contentBox">
							<div class="contentBoxTop">
								<h3>
                    更新系统当前密码
								</h3>
							</div>
							<div class="innerContent">
									<form action="<%=basePath%>system/updatepassword" method="post">
								<table class="sysTable">
									<tr>
								
										<td class="sTitle">当前用户名:</td>
									
										<td colspan="3" style="text-align: left">${user.manager_name}</td>
										
									</tr>
									<tr>
										<td class="sTitle">当前密码:</td>
										<td><input type="text" id="oldpassword" name="oldpassword"value="${user.manager_password}"></td>
										
									</tr>
									<tr>
									<td class="sTitle">新的密码:</td>
										<td><input type="text" id="password" name="password"></td>
									</tr>
									<tr>
										<td class="sTitle">重复密码:</td>
										<td colspan="3"><input type="text" id="newrepassword" name="newrepassword" ></td>
									</tr>
									
									<tr class="sSubmit">
										<td Colspan="4"><input type="submit" class="from_sub" value="保存" /></td>

									</tr>
								</table>
								</form>
								<!-- End of  #contentlist -->
							</div>
						</div>
						<!-- End of  #contentlist -->

			</div>
			<jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>

			
			
</body>
</html>
