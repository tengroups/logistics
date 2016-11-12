<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>系统页面</title>

<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script src="<%=basePath%>content/kindeditor/kindeditor-min.js"
	type="text/javascript"></script>
<script type="text/javascript">
			var editor;

			KindEditor.ready(function(K) {
				editor = K.create('#sys_content', {
					//uploadJson: '/Announce/UploadImage', //(Announce为Controller,UploadImage为Action，下同)
					//allowUpload: true, //允许上传图片 
					resizeType: 0,
					width:'90%',
					height: '200px',
					allowImageUpload: false,
					items: [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link'
					]

				});
			});
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
                    系统信息维护
								</h3>
							</div>
							<div class="innerContent">
								<form action="<%=basePath%>system/updateData" method="post">
								<table class="sysTable">
									<tr>
								
										<td class="sTitle">公司名称:</td>
									
										<td colspan="3" style="text-align: left"><input type="text" name="company_name" value="${systeminfo.company_name}" /></td>
										
									</tr>
									<tr>
										<td class="sTitle">识别码:</td>
										<td><input type="text" name="company_code" value="${systeminfo.company_code}"/></td>
										<td class="sTitle">营业执照号:</td>
										<td><input type="text" name="company_license"value="${systeminfo.company_license}"/></td>
									</tr>
									<tr>
										<td class="sTitle">公司联系方式:</td>
										<td colspan="3"><input type="text" name="company_tel"  value="${systeminfo.company_tel}"/></td>
									</tr>
									<tr>
										<td class="sTitle">公司地址:</td>
										<td colspan="3"><input type="text" style="width: 490px;" name="company_address" value="${systeminfo.company_address}"/></td>
									</tr>
                                    <tr>
                                    	<td class="sTitle">简介:</td>
                                    	<td colspan="3" style=""><textarea id="sys_content" name="company_content" value="">${systeminfo.company_content}</textarea></td>
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