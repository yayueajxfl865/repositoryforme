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
<script type="text/javascript"
	src="${ctxStatic }/jquery/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="${ctxStatic }/ui/mui/css/mui.min.css">
<script src="${ctxStatic }/ui/mui/js/mui.min.js"></script>
</head>
<style>
.mui-table-view-cell.mui-active {
	background-color: #0062CC;
}
</style>
<script type="text/javascript">
	function loooMessage(imessageId, consumerId) {
		window.location.href = "${ctx }/queue/queue/imessageDetails?imessageId="
				+ imessageId + "&consumerId=" + consumerId;
	}
</script>
<body>
	<!-- 列表-->
	<ul class="mui-table-view">
		<c:forEach items="${list }" var="l" varStatus="status">
			<li class="mui-table-view-cell"
				onclick="loooMessage('${l.imessage.id }','${l.id }')">${l.imessage.theme }
				<c:choose>
					<c:when test="${l.status eq '1'}">
						<span class="mui-badge mui-badge-primary"></span>
					</c:when>
					<c:otherwise>
						<span class="mui-badge mui-badge-danger">1</span>
					</c:otherwise>
				</c:choose>
			</li>
		</c:forEach>
	</ul>

</body>
</html>