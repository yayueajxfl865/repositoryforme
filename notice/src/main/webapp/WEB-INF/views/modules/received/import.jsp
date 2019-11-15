<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/layui.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	layui.use('upload', function() {//学生导入
		var upload = layui.upload;
		upload.render({
			elem : '#student',
			url : '${ctx}/queue/queue/studentImport',
			accept : 'file' ,
			exts : 'xls|xlsx',
			field : 'file',
			before: function(obj){ 
				i = layer.msg("正在导出，请稍候...", {icon: 16,rate: 'top',time: 0});
				$("#layerindex").val(i);
			},
			done : function(res) {
				layer.close($("#layerindex").val());
			}
		});
	});
</script>
</head>
<body>
    <input type="hidden" id="layerindex" />
	<div>
		<button type="button" class="layui-btn" id="student">
			<i class="layui-icon">&#xe67c;</i>学生导入
		</button>
		<button type="button" class="layui-btn" id="teacher">
			<i class="layui-icon">&#xe67c;</i>教职工导入
		</button>
	</div>
</body>
</html>