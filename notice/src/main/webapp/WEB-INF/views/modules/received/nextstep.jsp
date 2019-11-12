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
		<table class="layui-table">
			<tbody>
				<tr>
					<td>
						<form class="layui-form" action="">
							<div class="layui-form-item">
								<div class="layui-input-inline">
									<select name="city" lay-verify="required">
										<c:forEach items="${tList }" var="t" varStatus="status">
										<option value="${t.id }">${t.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</form>
					</td>
					<td>
						<div>
							<label>系别：</label><input id="dd" value=""
								style="width: 155px; height: 28px;"> <input
								type="hidden" value="" id="pantryId" name="pantryId">
						</div>
					</td>
					<td><button type="button" class="layui-btn layui-btn-normal"
							onclick="query()">查询</button></td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		$(function() {

		});
		layui.use('form', function() {
			var form = layui.form;
		});
		function query() {//查询
			layer.alert('只想简单的提示');
		}
	</script>
</body>
</html>