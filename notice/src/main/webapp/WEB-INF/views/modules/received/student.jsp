<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/layui.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function() {
		layer.closeAll();
	})
	$(document).on("click","[id='shanchurenyuan']",function() {//选择学生进行发送消息
				var indexs = []; // 存放选中的人员信息记录的下标
				var flag = false;
				var companyId = $(":input[name='companyId']").val();
				$("[id^='checkbox_']").each(function() {
					var index = $(this).attr("id").split("_")[1];
					if ($(this).is(":checked")) {
						indexs.push(index);
						flag = true;
					}
				});
				if (!flag) {
					$.jBox.alert("请选择要批量移除的人员", "提示");
					return;
				}
				window.top.indexTip('正在执行...', 'loading');
				window.location.href = "${ctx}/legal/legal/delete?indexs="+ indexs + "&companyId=" + companyId;
			});
	function checkChan(){
		alert("ahh");
	}
</script>
<body>
	<div>
		<table class="layui-table">
			<thead>
			<tr>
					<th>
					
					</th>
					<th></th>
					<th></th>
					<th><button type="button" class="layui-btn layui-btn-normal"
							onclick="query()">下一步</button></th>
				</tr>
				<tr>
					<th>
					<input class="easyui-checkbox" name="fruit" value="Apple" label="全选:" onchange="checkChan()">
					</th>
					<th>姓名</th>
					<th>班级</th>
					<th>系别</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${userList ne null||userList ne '' }"></c:if>
				<c:forEach items="${userList }" var="u" varStatus="status">
					<tr>
					    <td><input class="easyui-checkbox" name="fruit" value="Apple"></td>
						<td>${u.yb_realname }</td>
						<td>${u.claban.name }</td>
						<td>${u.claban.tie.name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>