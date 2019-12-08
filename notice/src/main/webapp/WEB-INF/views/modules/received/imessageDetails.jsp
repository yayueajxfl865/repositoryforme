<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<script type="text/javascript"
	src="${ctxStatic }/jquery/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="${ctxStatic }/ui/mui/css/mui.min.css">
<script src="${ctxStatic }/ui/mui/js/mui.min.js"></script>
</head>

<body style="height: 100%;">
	<!-- 列表-->
	<div class="mui-card-header mui-card-media">
		<span style="color: #1A73E8">发送人：</span>
		<div class="mui-media-body">
			${imessage.ybUser.yb_realname }
			<p>发送于 ${imessage.createDate }</p>
		</div>
	</div>
	<div style="height:88%; ;background: #FFF; ">${imessage.content }</div>
	

</body>
</html>