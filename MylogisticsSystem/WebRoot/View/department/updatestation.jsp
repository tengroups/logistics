<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>更新二级分拣中心</title>
<script src="<%=basePath%>content/js/jquery.min.js"
	type="text/javascript"></script>
<jsp:include page="../shared/manager_headtitle.jsp"></jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
	//$(function() {--修改这里
		$("#citylist").change(
				function() { //当城市选项改变时激活
					var selec = $(this).val(); //获取下拉城市选项改变的值
					var url ="<%=basePath%>test/getAreaJsonDataByCityId"; 
					//参数依次类型(action,Controller,area）            
                    //上面我们只用第一个参数action，也就是方法的请求地址
					$("#arealist").find("option").remove(); //清空
					
					/* getJSON语法
					   $.getJSON(
                        url,             //请求URL
                        [data],          //传参，可选参数
                        [callback]       //回调函数，可选参数
  　                                                     );        
					 */
					 if(selec==-1)
						{
						    //如果选择的是请选择-对应的值是-1，则清空第二个下拉框，并添加请选择文字
							$("#arealist").find("option").remove(); //清空
							$("<option></option>").val("-1")
							.text("请选择").appendTo(
									$("#arealist"));
						}
					 else
					    {
							$("<option></option>").val("-1")
							.text("请选择").appendTo(
									$("#arealist"));

					$.getJSON(url, {
						'city_id' : selec
					}, function(data) { //city_id是参数名，和Controllers中的action参数名相同
						//语法：$.each( collection, callback(indexInArray, valueOfElement) ) 
						$.each(data,
								function(i, item) {
									$("<option></option>").val(item["city_id"])
											.text(item["city_name"]).appendTo(
													$("#arealist"));
								}); //如果url访问成功  则执行function(data)这个函数（看仔细了`，这里该函数也是.getJSON的第三个参数）
					}); //function(data)获取了通过url返回来的值，并且循环读取出来            
					    }
				});
					
					
				 $("#arealist").change(
							function() { //当城市选项改变时激活
								var selec = $(this).val(); //获取下拉城市选项改变的值
								var url ="<%=basePath%>test/getAreaJsonDataByCityId";
								//参数依次类型(action,Controller,area）            
			                    //上面我们只用第一个参数action，也就是方法的请求地址
								$("#streetlist").find("option").remove();  //清空
								
								/* getJSON语法
								   $.getJSON(
			                        url,             //请求URL
			                        [data],          //传参，可选参数
			                        [callback]       //回调函数，可选参数
			  　                                                     );        
								 */
								 if(selec==-1)
									{
									    //如果选择的是请选择-对应的值是-1，则清空第二个下拉框，并添加请选择文字
										$("#arealist").find("option").remove(); //清空
										$("<option></option>").val("-1")
										.text("请选择").appendTo(
												$("#arealist"));
										$("<option></option>").val("-1")
										.text("请选择").appendTo(
												$("#streetlist"));
									}
									else
								    {
										$("<option></option>").val("-1")
										.text("请选择").appendTo(
												$("#streetlist"));
								$.getJSON(url, {
									'city_id' : selec
								}, function(data) { //city_id是参数名，和Controllers中的action参数名相同
									//语法：$.each( collection, callback(indexInArray, valueOfElement) ) 
									$.each(data,
											function(i, item) {
												$("<option></option>").val(item["city_id"])
														.text(item["city_name"]).appendTo(
																$("#streetlist"));
											}); //如果url访问成功  则执行function(data)这个函数（看仔细了`，这里该函数也是.getJSON的第三个参数）
								}); 
								    }//function(data)获取了通过url返回来的值，并且循环读取出来            
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
						<h3>更新三级分拣站</h3>
					</div>
					<div class="innerContent">
					<form action="<%=basePath%>department/updatestationData" method="post">
						<table class="sysTable">
						<input type="hidden" name="rangerId" value="${stationinfo.rangerId }">
						<input type="hidden" name="station_id" value="${stationinfo.station_id}">
					
								<tr>
								<td class="sTitle"><span class="important">*</span>设置二级管理员:</td>
								<td colspan="3">
								     <select id="userlist1" name="department_manager" value="${stationinfo.department_manager}">
								     <option value="0">请选择</option>
										<c:forEach var="um" items="${userlist1}">
											<option value='<c:out value="${um.manager_name}" />'>
												${um.manager_name }</option>
										</c:forEach>
								     </select></td>
                                </td>
							</tr>
							
								<tr>
									<td class="sTitle"><span class="important">*</span>三级分拣站主题:</td>
									<td colspan="3"><input type="text" name="station_name"
										style="width: 400px;" value="${stationinfo.station_name}"/></td>
								</tr>
								<tr>
									<td class="sTitle"><span class="important">*</span>所在地区:</td>
									<td colspan="3"><select id="citylist" name="city_name" value="${stationinfo.city_name }">
										<option value="-1">请选择</option>
											<c:forEach var="ci" items="${citylist}">
												<option value='<c:out value="${ci.city_id }" />'>
													${ci.city_name }</option>
											</c:forEach>
									</select> <select id="arealist" name="district_name" value="${stationinfo.district_name }">
											<option value="-1">请选择</option>
											
									</select> <select id="streetlist" name="street_name" value="${stationinfo.street_name }">
											<option value="-1">请选择</option>
									</select></td>
								</tr>
							<tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>设置三级管理员:</td>
								<td colspan="3">
								     <select id="userlist" name="manager_id" value="${stationinfo.manager_id}">
								     <option value="0">请选择</option>
										<c:forEach var="ul" items="${userlist}">
											<option value='<c:out value="${ul.manager_id }" />'>
												${ul.manager_name }</option>
										</c:forEach>
								     </select></td>
                                </td>
							</tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>负责人:</td>
								<td><input type="text" name="station_manager" value="${stationinfo.station_manager}"/></td>
							</tr>
							<tr>
								<td class="sTitle">联系方式1-座机:</td>
								<td colspan="3"><input type="text" name="station_tel"value="${stationinfo.station_tel}" /></td>
							</tr>
							<tr>
								<td class="sTitle"><span class="important">*</span>地址:</td>
								<td colspan="3"><input type="text" style="width: 490px;" name="station_address" value="${stationinfo.station_address}"/></td>
							</tr>
							<tr class="sSubmit">
								<td Colspan="4"><input type="submit" class="from_sub"
									value="保存" /></td>

							</tr>
						</table>
                        </form>
						<!-- End of  #contentlist -->
					</div>
				</div>
			</div>
			<jsp:include page="../shared/manager_siderbar.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="../shared/manager_footer.jsp"></jsp:include>
</body>
</html>
