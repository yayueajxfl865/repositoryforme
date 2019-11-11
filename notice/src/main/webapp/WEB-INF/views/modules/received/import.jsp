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
<body>
	<div>
		<button type="button" class="layui-btn" id="student">
			<i class="layui-icon">&#xe67c;</i>学生导入
		</button>
		<button type="button" class="layui-btn" id="teacher">
			<i class="layui-icon">&#xe67c;</i>教职工导入
		</button>
	</div>
<script type="text/javascript">
	layui.use('upload', function() {
		var upload = layui.upload;
		upload.render({
			elem : '#student',
			url : '${ctx}/queue/queue/studentImport',
			accept : 'file' ,
			exts : 'xls|xlsx',
			field : 'file',
			before: function(obj){ 
			    layer.load(); 
			},
			done : function(res) {
				layer.closeAll('loading');
			}
		});
		upload.render({
			elem : '#teacher',
			url : '/upload/',
			accept : 'file' ,
			done : function(res) {
				console.log(res)
			}
		});
	});
</script>
</body>
</html>