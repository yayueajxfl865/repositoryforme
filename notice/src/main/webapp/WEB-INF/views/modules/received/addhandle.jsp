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
		<form action="" method="post" id="userForm">
		<div class="padding border-bottom">
			<ul class="search" style="padding-left: 10px;">
				<li><input type="text" placeholder="请输入易班ID" name="yb_userid"
					class="input"
					style="width: 250px; line-height: 17px; display: inline-block" />
				</li>
				<li><input type="text" placeholder="请输入姓名" name="yb_realname"
					class="input"
					style="width: 250px; line-height: 17px; display: inline-block" />
					<a href="javascript:void(0)" class="button border-main icon-search"
					onclick="query()">搜索</a> <a href="javascript:history.go(-1)"
					class="button bg-main icon-backward" type="button">返回</a>
				</li>
			</ul>
		</div>
		</form>
		<div class="padding border-bottom" style="height: 90%;">
			<div>
				<c:if test="${userList !=null&&userList!=''}">
					<table class="table table-hover text-center">
						<tr>
							<th>姓名</th>
							<th>班别</th>
							<th>系别</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${userList }" var="u" varStatus="status">
							<tr id="tr_${u.id }">
								<td>${u.yb_realname }</td>
								<td>${u.claban.name }</td>
								<td>${u.claban.tie.name }</td>
								<td><div class="button-group">
										<a class="button border-main" href="javascript:void(0)" onclick="authorize('${u.id}')"><span class="icon-refresh"></span>授权</a>
									</div></td>
							</tr>
						</c:forEach>
						<tr>
							<%-- 
					<td colspan="8"><div class="pagelist">
							<a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
								href="">3</a><a href="">下一页</a><a href="">尾页</a>
						</div>
					</td>
					 --%>
						</tr>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			layer.closeAll();
		});
		function query() {//查询指定User
			i = layer.msg("正在查询...", {icon: 16,rate: 'top',time: 0});
		    var action="${ctx }/queue/queue/searchYbUser";
		    $("#userForm").attr("action",action).submit();
		}
		function authorize(id){//授权
			layer.confirm('确定授权该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
				i = layer.msg("正在授权...", {icon: 16,rate: 'top',time: 0});
				$("#layerindex").val(i);
				window.location.href="${ctx}/queue/queue/authorize/?id="+id+"&role=handle";
			});
		}
	</script>
</body>
</html>