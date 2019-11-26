<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/layui.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctxStatic }/lanseUI/css/pintuer.css">
<link rel="stylesheet" href="${ctxStatic }/lanseUI/css/admin.css">
</head>
<style>

</style>
<script type="text/javascript">
	
	$(function() {
		layer.closeAll();
		
	})
	function stuManage(){//学生管理
		i = layer.msg("正在加载，请稍候...", {icon: 16,rate: 'top',time: 0});
		window.location.href="${ctx }/queue/queue/stuManage";
	}

</script>
<body>
    <input type="hidden" id="layerindex" />
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">发送通知</strong>
			</div>
			<div class="padding border-bottom" style="height: 90%;">
				<table>
				   <tr>
				      <td><div onclick="stuManage()">学生管理</div></td>
				      <td>教师管理</td>
				      <td>社团管理</td>
				   
				   </tr>
				
				
				</table>
				
				
			</div>
		</div>
	</form>
</body>
</html>