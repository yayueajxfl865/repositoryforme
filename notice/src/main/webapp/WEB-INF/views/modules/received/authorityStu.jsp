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
input[type=checkbox]:after  {
        content: "";
        display:block;
        width: 20px;
        height: 20px;
        text-align: center;
        line-height: 14px;
        font-size: 16px;
        color: #fff;
        border: 2px solid #ddd;
        background-color: #fff;
        box-sizing:border-box;
    }
    input[type=checkbox]:checked:after  {
        content: '\2714';
        background-color: #5774FF;
    }
</style>
<script type="text/javascript">
	$(function() {
		layer.closeAll();
	})
	function deleteStu(id){//删除学生
		layer.confirm('该操作可能会导致系统队列异常,是否确定删除该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
			i = layer.msg("正在执行删除...", {icon: 16,rate: 'top',time: 0});
			$("#layerindex").val(i);
			$.ajax({
				url:"${ctx}/queue/queue/deleteStu/?id="+id,
				type:"post",
				async:false,
				success:function(result){
					var code = $.parseJSON(result);
					if(code.status=="100"){
						layer.msg('该人员正处于系统队列中,无法删除!', {icon: 2}); 
					}
					else if(code.status=="200"){
						layer.msg('删除成功!', {icon: 1}); //删除成功
			    		$("#tr_"+id).remove();
					}
				},
				error:function(e){
					layer.msg('删除失败!', {icon: 2}); 
				}
			});
		});
		
	}
	function back(){//返回上一层
		window.history.back(-1); 
	}
	
	function authorize(id){//授权
		layer.confirm('确定授权该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
			i = layer.msg("正在授权...", {icon: 16,rate: 'top',time: 0});
			$("#layerindex").val(i);
			window.location.href="${ctx}/queue/queue/authorize/?id="+id+"&role=monitor";
		});
	}
	
	function cancelAuthorize(id){//取消授权
		layer.confirm('确定授权该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
			i = layer.msg("正在授权...", {icon: 16,rate: 'top',time: 0});
			$("#layerindex").val(i);
			window.location.href="${ctx}/queue/queue/cancelAuthorize/?id="+id+"&role=monitor";
		});
	}
</script>
<body>
    <input type="hidden" id="layerindex" />
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">学生管理</strong>
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
						    <c:choose>
						      <c:when test="${u.s1 eq '1'}">
						        <a class="button border-red" href="javascript:void(0)" onclick="cancelAuthorize('${u.id}')"><span class="icon-refresh"></span>取消授权</a>
						      </c:when>
						      <c:otherwise>
						      <a class="button border-main" href="javascript:void(0)" onclick="authorize('${u.id}')"><span class="icon-refresh"></span>授权</a>
						      </c:otherwise>
						    </c:choose>
							</div>	
						</td>			
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
		</div>
	</form>
</body>
</html>