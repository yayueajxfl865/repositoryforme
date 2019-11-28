<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="${ctxStatic }/lanseUI/css/pintuer.css">
<link rel="stylesheet" href="${ctxStatic }/lanseUI/css/admin.css">
<script type="text/javascript" src="${ctxStatic }/jquery/jquery-1.9.1.js"></script>
<script src="${ctxStatic }/ui/layer/layer.js"></script>
</head>
<style>
   .box a{ text-decoration:none;}
   .box a:hover{ text-decoration:underline; }
</style>
<script type="text/javascript">
	$(function(){
		layer.closeAll();
    });
	function query(id){//查看当前消息
		i = layer.msg("正在加载...", {icon: 16,rate: 'top',time: 0});
		window.location.href="${ctx}/queue/queue/lookMessage?id="+id;
	}
	function csmDetails(id){//查询消费者
		i = layer.msg("正在加载...", {icon: 16,rate: 'top',time: 0});
		window.location.href="${ctx}/queue/queue/csmDetails?id="+id;
	}
	function withdraw(){//撤回消息
		layer.confirm('该操作可能会导致系统队列异常,是否确定删除该人员?', {icon: 3, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
		
		});
		
	}
</script>
<body>
    <div>
    <input type="hidden" id="pageNo" name="pageNo" value="${pageNo }">
    </div>
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">历史通知</strong>
		</div>
	<table class="table table-hover text-center">
    <tr>
      <th width="4%">序号</th>
      <th width="5%">发起人</th>
      <th width="10%">消息</th>
      <th width="10%">接收者</th>
      <th width="5%">发送时间</th>
      <th width="5%">消息类型</th>
      <th width="5%">状态</th>
     <th width="15%">操作</th>
    </tr>
    <c:if test="${messageList!=null }">
    <c:forEach items="${messageList }" var="m" varStatus="status">
    <tr>
      <td>${status.index+1 }</td>
      <td><span class="box"><a style="color: #0088D5;cursor: pointer;" href="javascript:void(0)">${m.producer.ybUser.yb_realname }</a></span></td>
      <td>${m.theme }</td>
      <td><span class="box"><a style="color: #0088D5;cursor: pointer;" href="javascript:void(0)" onclick="csmDetails('${m.id}')">${m.rever }</a></span></td>
      <th><span style="color: #333333;font-family: cursive;"><fmt:formatDate value="${m.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span></th>
      <td>用户消息</td>
      <td>发送成功</td>
      <td><div class="button-group"> 
      <a class="button border-main" href="javascript:void(0)" onclick="query('${m.id}')"><span class="icon-send-o"></span>查看</a>
      <a class="button border-green" href="javascript:void(0)" onclick="withdraw()"><span class="icon-share"></span>撤回</a>
      <a class="button border-red" href="javascript:void(0)" onclick="return del(1,2)"><span class="icon-trash-o"></span> 删除</a> </div></td>
    </tr>
    </c:forEach>
    </c:if>
    <c:if test="${messageList==null }">
      <span>暂无任何数据...</span>
      
    </c:if>
  </table>
		</div>
	</form>
</body>
</html>