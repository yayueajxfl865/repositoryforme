<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/layui.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
   $(function(){
	    i = layer.msg("授权成功，正在验证身份信息，请稍后", {icon: 16,rate: 'top',time: 0});
		window.location.href="${ctx}/queue/queue/authorization";
   });
</script>
<body>

</body>
</html>