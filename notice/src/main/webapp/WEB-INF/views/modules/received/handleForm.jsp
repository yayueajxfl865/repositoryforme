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
	function back(){//返回上一层
		window.history.back(-1); 
	}
	function addHandle(){//添加角色
		i = layer.msg("正在加载，请稍候...", {icon: 16,rate: 'top',time: 0});
		window.location.href="${ctx }/queue/queue/addHandle";
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
						<a class="button border-main icon-plus-square-o" href="javascript:void(0)" onclick="addHandle()">添加角色</a>
						<button class="button bg-main icon-backward" type="button" onclick="back()">返回</button>

					</li>
				</ul>
			</div>
			<div class="padding border-bottom" style="height: 90%;">
				<c:if test="${userList!=null&&userList!='' }">
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
										<a class="button border-red" href="javascript:void(0)"
											onclick="deleteStu('${u.id}')"><span class="icon-trash-o"></span>
											删除</a>
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
	</form>
</body>
</html>