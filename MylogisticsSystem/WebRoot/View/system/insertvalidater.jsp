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
<title>发布公告</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/kindeditor/kindeditor-min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var editor;

	KindEditor.ready(function(K) {
		editor = K.create('#notice_Content', {
			resizeType : 1,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]

		});
	});
</script>
</head>
<body>
	<!-- header开始 -->
	<jsp:include page="../shared/manager_head.jsp"></jsp:include>
	<div class="manager_main">
		<div class="content">
			<div class="leftbox">
				<!-- Begin of  #contentlist -->
				<div class="contentBox">
					<div class="contentBoxTop">
						<h3>新增权限用户</h3>
					</div>
					<div class="innerContent">
						<form action="<%=basePath%>system/insertData" method="post">
							<table class="aTable">
								<tr>
									<td class="aTitle">管理员账号： <input type="text" id="manager_name"name="manager_name"/></td>
                                    <td class="aTitle">管理员密码： <input type="text" id="manager_password"name="manager_password"/></td>
								</tr>
								<tr>
									<td class="aTitle">用户权限id：  
									<select id="userlist" name="role_id">
								     <option value="0">请选择</option>
								      <option value="1">总管理员</option>
								     <option value="2">二级管理员</option>
								      <option value="3">录入人员</option>
								      <option value="4">客服人员</option>
								      <option value="5">扫码员</option>
								      <option value="6">派送员</option>
								     </select></td>
									<td class="aTitle">分拣中心id： <input type="text" name="department_id"/></td>
								</tr>
								<tr class="aSubmit">
									<td><input type="submit" class="from_sub" value="保存" /></td>

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
	<!-- footer开始 -->
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
</body>
</html>
