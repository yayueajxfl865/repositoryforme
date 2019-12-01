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
	layui.use('upload', function() {
		var upload = layui.upload;
		upload.render({//学生导入
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
				var status=res.status;
				var message=res.message;
				if(status=='200'){
					layer.msg("导入成功,共导入"+message+"条记录", {icon: 1}); 
				}
				else{
					layer.msg('导入失败', {icon: 2}); 
				}
			}
		});
		upload.render({//教师导入
			elem : '#teacher',
			url : '${ctx}/queue/queue/teacherImport',
			accept : 'file' ,
			exts : 'xls|xlsx',
			field : 'file',
			before: function(obj){ 
				i = layer.msg("正在导出，请稍候...", {icon: 16,rate: 'top',time: 0});
				$("#layerindex").val(i);
			},
			done : function(res) {
				var status=res.status;
				var message=res.message;
				if(status=='200'){
					layer.msg("导入成功,共导入"+message+"条记录", {icon: 1}); 
				}
				else{
					layer.msg('导入失败', {icon: 2}); 
				}
			}
		});
		upload.render({//教师导入
			elem : '#clubs',
			url : '${ctx}/queue/queue/clubsImport',
			accept : 'file' ,
			exts : 'xls|xlsx',
			field : 'file',
			before: function(obj){ 
				i = layer.msg("正在导出，请稍候...", {icon: 16,rate: 'top',time: 0});
				$("#layerindex").val(i);
			},
			done : function(res) {
				var status=res.status;
				var message=res.message;
				if(status=='200'){
					layer.msg("导入成功,共导入"+message+"条记录", {icon: 1}); 
				}
				else{
					layer.msg('导入失败', {icon: 2}); 
				}
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
		<button type="button" class="layui-btn" id="clubs">
			<i class="layui-icon">&#xe67c;</i>社团导入
		</button>
		<button type="button" class="layui-btn" id="clubs">
			<i class="layui-icon">&#xe601;</i>模版下载
		</button>
	</div>
	<%-- 
	<div>
		<table class="layui-table" style="text-align: center;">
			<thead style="text-align: center;">
				<tr style="text-align: center;">
					<th style="text-align: center;">昵称</th>
					<th style="text-align: center;">加入时间</th>
					<th style="text-align: center;">签名</th>
					<th style="text-align: center;">签名</th>
					<th style="text-align: center;">签名</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach begin="0" end="50">
					<tr>
						<td>哈哈</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	 --%>
</body>
</html>