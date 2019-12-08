<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<link rel="stylesheet" href="${ctxStatic }/ui/mui/css/mui.min.css">
<script src="${ctxStatic }/ui/mui/js/mui.min.js"></script>
</head>
<body>
    
	<!--下拉刷新容器-->
	<!-- ajax请求数据 -->
	<c:forEach items="${list }" var="l" varStatus="status">
		<li class="mui-table-view-cell">${l.imessage.theme }</li>
	</c:forEach>
</body>
</html>