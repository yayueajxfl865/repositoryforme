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

.form_title_div{
	width:120px; font-size: 18px; border-right: 23px solid transparent; border-bottom: 23px solid #3DAEE9; border-bottom-color: #3DAEE9;
       border-top-left-radius: 5px;-webkit-border-left-radius: 5px;//第二个图片要加上这个做圆角
}
.form_title_div span{
	display: block; margin-bottom: -130px; margin-left: 15px; color: #ffffff;
}
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
			<div class="padding border-bottom" style="height: 90%;margin-top: 20px;margin-left: 30px;">
				<table style="width: 50%">
					<tr>
						<td>
							<div class="form_title_div" style="float: left;cursor: pointer;"
								onclick="stuManage()">
								<span>学生管理</span>
							</div>
						</td>
						<td>
							<div class="form_title_div" style="float: left;cursor: pointer;">
								<span>教师管理</span>
							</div>
						</td>
						<td>
							<div class="form_title_div" style="float: left;cursor: pointer;">
								<span>社团管理</span>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>