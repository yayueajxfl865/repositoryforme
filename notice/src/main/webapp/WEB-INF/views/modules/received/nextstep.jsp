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
		<div class="padding border-bottom">
			<ul class="search" style="padding-left: 10px;">
				<li><select name="cid" class="input"
					style="width: 200px; line-height: 17px;" id="tieId">
						<option value="">请选择系别</option>
						<c:forEach items="${fns:getTieList()}" var="t" varStatus="status">
							<option value="${t.id }">${t.name }</option>
						</c:forEach>
				</select></li>
				<li>
					<%--
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
           --%> <a href="javascript:void(0)"
					class="button border-main icon-search" onclick="query()">查询</a> <a
					href="javascript:history.go(-1)" class="button bg-main icon-backward"
					type="button">返回</a>
				</li>
			</ul>
		</div>
		<div class="padding border-bottom" style="height: 90%;">
			<div>
				<table class="layui-table" style="text-align: center;">
					<tbody>
						<c:forEach items="${claList}" var="obj" varStatus="status">
							<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
								<tr>
							</c:if>
							<td><span style="cursor: pointer;"
									onclick="queryStu('${obj.id}')">${obj.name }</span>
							</td>
							<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
								</tr>
							</c:if>
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
		function query() {//查询
			var tieId = $("#tieId").val();
			if (tieId == null || tieId == "") {
				return false;
			} else {
				i = layer.msg("正在加载,请稍候...", {icon: 16,rate: 'top',time: 0});
				window.location.href = "${ctx }/queue/queue/loadClaban?tieId="
						+ tieId;
			}
		}
		function queryStu(claId) {//点击系别查询学生
			i = layer.msg("正在加载,请稍候...", {icon: 16,rate: 'top',time: 0});
			window.location.href = "${ctx }/queue/queue/loadStudent?claId="
					+ claId;
		}
	</script>
</body>
</html>