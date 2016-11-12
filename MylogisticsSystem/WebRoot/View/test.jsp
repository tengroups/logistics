<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="page" uri="/WEB-INF/pager.tld"%>  
<% String path = request.getContextPath(); %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>  
<html>  
<head>  
<title>测试分页标签</title>  
    <link href="<%=path%>/bootstrap/css/bootstrap.css" rel="stylesheet">  
    <link href="<%=path%>/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">  
    <script type="text/javascript">  
        function toDel(id){  
            var url = "<%=path%>/test/del?id=" + id +"&pageNum=${param.pageNum}&pageSize=${param.pageSize}";  
            window.location.href = url;  
        }  
    </script>  
</head>  
<body>  
      
    <div class="container">  
          <c:forEach items="${list}" var="item">  
              <div class="border-bottom1">  
                  <h3><a href="<%=path%>/test/view?id=${item.id}">${item.name}</a></h3>  
                  <p>  
                    ${item.content}  
                  </p>  
                  <p class="text-right muted">  
                    2013-06-22 22:37     
                    <a href="javascript:toDel('${item.id}');">删除</a>    
                    <a href="<%=path%>/test/toEdit?id=${item.id}&pageNum=${param.pageNum}&pageSize=${param.pageSize}">编辑</a>    
                  </p>  
              </div>  
          </c:forEach>  
    <form method="post" id="testForm" action="<%=path%>/test">  
            <input type="hidden" name="type" value="${type}">  
          </form>  
          <page:createPager pageSize="${pageSize}" totalPage="${totalPage}" totalCount="${totalCount}" curPage="${pageNum}" formId="testForm"/>  
    </div>  
      
</body>  
</html>  