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
<script type="text/javascript">
	$(function() {
		layer.closeAll();
	})
	function back(){
	   i = layer.msg("正在加载...", {icon: 16,rate: 'top',time: 0});
	   window.location.href="${ctx }/queue/queue/indexIcon";
   }
	function deleteStu(id){//删除学生
		layer.confirm('该操作可能会导致系统队列异常,是否确定删除该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
			i = layer.msg("正在执行删除...", {icon: 16,rate: 'top',time: 0});
			$("#layerindex").val(i);
		});
	}
	function withdraw(){//撤回消息
		layer.confirm('该操作可能会导致系统队列异常,是否确定删除该人员?', {icon: 3, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
		
		});
		
	}
</script>
<body>
    <input type="hidden" id="layerindex" />
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">接收者</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li>
						<button class="button bg-main icon-backward" type="button" onclick="back()">返回</button>
					</li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="50"></th>
					<th>姓名</th>
					<th>班别</th>
					<th>系别</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${mList }" var="u" varStatus="status">
					<tr id="tr_${u.id }">
						<td>${status.index+1 }</td>
						<td>${u.ybUser.yb_realname }</td>
						<td>${u.ybUser.claban.name }</td>
						<td>${u.ybUser.claban.tie.name }</td>
						<td><span style="color: #EE3333;cursor: pointer;">未阅读</span></td>
						<td>
						<div class="button-group">
							<a class="button border-green" href="javascript:void(0)" onclick="withdraw()"><span class="icon-share"></span>撤回</a>
						</div>
					    </td>
					</tr>
				</c:forEach>
				<%--
				<tr>
					<td colspan="8"><div class="pagelist">
							<a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
								href="">3</a><a href="">下一页</a><a href="">尾页</a>
						</div></td>
				</tr>
				 --%>
			</table>
		</div>
	</form>
</body>
</html>