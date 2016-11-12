<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Save for Web Slices (设计.psd) -->
<div id="__01" class="mainbody">
	<div class="header">
		<div class="header_logo"></div>
		<div class="header_logInfo">
			欢迎您 <strong>${welcome}</strong>! [ <a href="/MylogisticsSystem/View/login.jsp">退出</a> ]
			| <a href="/">首页</a>
		</div>
	</div>
	<div class="manager_nav_bg">
		<div class="manager_nav">
			<ul id="menu">
				<c:forEach var="menuinfo" items="${menulist}">
					<li><a href="${menuinfo.menuPath}"><c:out
								value="${menuinfo.menuContent}" /></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>