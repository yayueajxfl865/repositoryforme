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
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder">发送通知</strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left: 10px;">
				<li><select name="cid" class="input"
					style="width: 200px; line-height: 17px;" id="clubsId">
						<option value="">请选择社团机构</option>
						<c:forEach items="${fns:getClubsList()}" var="t"
							varStatus="status">
							<option value="${t.id }">${t.clubsName }</option>
						</c:forEach>
				</select></li>
				<li><a href="javascript:void(0)"
					class="button border-main icon-search" onclick="query()">查询</a>
					<%-- 
					<c:if test="${userList ne null && userList ne ''}">
					<button type="button" class="button border-green"
						onclick="checkall()">
						<span class="icon-check"></span> 全选
					</button>
					<button class="button bg-main icon-check-square-o" type="button"
						onclick="nextTep()">下一步</button> 
					</c:if>
					 --%>
					<a href="javascript:history.go(-1)"
					class="button bg-main icon-backward" type="button">返回</a></li>
			</ul>
		</div>
		<c:if test="${userList ne null && userList ne ''}">
		<table class="table table-hover text-center">
				<tr>
					
					<th>姓名</th>
					<th>班别</th>
					<th>系别</th>
					<th>社团</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${userList }" var="u" varStatus="status">
					<tr id="tr_${u.id }">
						
						<td>${u.yb_realname }</td>
						<td>${u.claban.name }</td>
						<td>${u.claban.tie.name }</td>
						<td>${u.clubs.clubsName }</td>
						<td><div class="button-group">
							<c:choose>
						      <c:when test="${u.s2 eq '1'}">
						        <a class="button border-red" href="javascript:void(0)" onclick="cancelAuthorize('${u.id}')"><span class="icon-refresh"></span>取消授权</a>
						      </c:when>
						      <c:otherwise>
						      <a class="button border-main" href="javascript:void(0)" onclick="authorize('${u.id}')"><span class="icon-refresh"></span>授权</a>
						      </c:otherwise>
						    </c:choose>
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
	<script type="text/javascript">
		$(function() {
			layer.closeAll();
		});
		
		function query() {//查询
			var clubsId = $("#clubsId").val();
			if (clubsId == null || clubsId == "") {
				return false;
			} else {
				i = layer.msg("正在加载,请稍候...", {icon: 16,rate: 'top',time: 0});
				window.location.href = "${ctx }/queue/queue/loadClubsAuthor?clubsId="
						+ clubsId;
			}
		}
		function checkall() {//全选或者反全选
			$("input[name='chek[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		}
		function nextTep() {//下一步
			var indexs = new Array(); // 存放选中的人员信息记录的下标
			var flag = false;
			$("input[name='chek[]']").each(function() {
				if (this.checked) {
					var index = $(this).val();
					indexs.push(index);
					flag = true;
				}
			});
			if (flag) {
				i = layer.msg("正在提交，请稍候...", {icon: 16,rate: 'top',time: 0});
				window.location.href="${ctx }/queue/queue/arrComit?indexs="+indexs;
			}
			else{
				layer.msg('请选择人员!', {icon: 0}); 
				return false;
			}
			
		}
		function queryStu(claId) {//点击系别查询学生
			layer.msg('正在加载...', {
				icon : 16,
				shade : 0.01
			});
			window.location.href = "${ctx }/queue/queue/loadStudent?claId="
					+ claId;
		}
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
		function authorize(id){//授权
			layer.confirm('确定授权该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
				i = layer.msg("正在授权...", {icon: 16,rate: 'top',time: 0});
				$("#layerindex").val(i);
				window.location.href="${ctx}/queue/queue/authorize/?id="+id+"&role=chairman";
			});
		}
		
		function cancelAuthorize(id){//取消授权
			layer.confirm('确定授权该人员?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
				i = layer.msg("正在授权...", {icon: 16,rate: 'top',time: 0});
				$("#layerindex").val(i);
				window.location.href="${ctx}/queue/queue/cancelAuthorize/?id="+id+"&role=chairman";
			});
		}
	</script>
</body>
</html>