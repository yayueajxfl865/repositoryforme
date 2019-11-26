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
<body>
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">发送通知</strong>
			</div>
		<div class="padding border-bottom" style="height: 90%;">
			<div>
				<table class="layui-table">
					<colgroup>
						<col width="150">
						<col width="200">
					</colgroup>
					<tbody>
						<tr style="height: 30px;">
							<td>
								<form class="layui-form" action="">
									<div class="layui-form-item">
										<div class="layui-input-inline">
											<select name="tieName" lay-verify="required" id="tieId">
												<c:forEach items="${fns:getTieList()}" var="t"
													varStatus="status">
													<option value="${t.id }">${t.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</form>
							</td>

							<td><button type="button" class="layui-btn layui-btn-normal"
									onclick="query()">查询</button></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="layui-table">
					<tbody>
						<c:if test="${claList ne null||claList ne '' }"></c:if>
						<c:forEach items="${claList }" var="c" varStatus="status">
							<tr>
								<td><span onclick="queryStu('${c.id}')">${c.name }</span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>


		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			layer.closeAll();
		});
		layui.use('form', function() {
			var form = layui.form;
		});
		function query() {//查询
			layer.msg('正在在载...', { icon: 16 ,shade: 0.01});
			var tieId=$("#tieId").val();
			window.location.href="${ctx }/queue/queue/loadClaban?tieId="+tieId;
		}
		function queryStu(claId){//点击系别查询学生
			layer.msg('正在加载...', { icon: 16 ,shade: 0.01});
			window.location.href="${ctx }/queue/queue/loadStudent?claId="+claId;
		}
	</script>
</body>
</html>