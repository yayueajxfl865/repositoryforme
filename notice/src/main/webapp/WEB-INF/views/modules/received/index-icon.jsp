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
	function withdraw(id){//撤回消息
		layer.confirm('是否撤回该条通知?', {icon: 3, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
			i = layer.msg("正在执行撤回...", {icon: 16,rate: 'top',time: 0});
			window.location.href="${ctx}/queue/queue/withdraw?id="+id;
		});
	}
	function deleteMessage(id){//删除消息
		layer.confirm('是否删除该条通知?', {icon: 0, title:'系统提示',shade: [0.1, '#117FBA']}, function(index){
			i = layer.msg("正在执行删除...", {icon: 16,rate: 'top',time: 0});
			$("#layerindex").val(i);
			$.ajax({
				url:"${ctx}/queue/queue/deleteMessage/?id="+id,
				type:"post",
				async:false,
				success:function(result){
					var code = $.parseJSON(result);
					if(code.status=="100"){
						layer.msg('删除失败!', {icon: 2}); 
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
	
</script>
<body>
	
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">最新通知</strong>
		</div>
 <c:if test="${messageList!=null&&messageList!='' }">
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
    <c:forEach items="${messageList }" var="m" varStatus="status">
    <tr id="tr_${m.id }">
      <td>${status.index+1 }</td>
      <td><span class="box"><a style="color: #0088D5;cursor: pointer;" href="javascript:void(0)">${m.ybUser.yb_realname }</a></span></td>
      <td>${m.theme }</td>
      <td>
      <c:choose>
        <c:when test="${m.fszt eq '1'}">
        <span style="color: #0088D5;cursor: pointer;">无</span>
        </c:when>
        <c:otherwise>
            <span class="box"><a style="color: #0088D5;cursor: pointer;" href="javascript:void(0)" onclick="csmDetails('${m.id}')">${m.rever }</a></span>
        </c:otherwise>
     </c:choose>
      </td>
      <th><span style="color: #333333;font-family: cursive;"><fmt:formatDate value="${m.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span></th>
      <td>用户消息</td>
      <td>
     <c:choose>
        <c:when test="${m.fszt eq '1'}">
        <span style="color: #EE3333;cursor: pointer;">撤回</span>
        </c:when>
        <c:otherwise>
        <span>发送成功</span>
        </c:otherwise>
     </c:choose>
      </td>
      <td><div class="button-group"> 
      <a class="button border-main" href="javascript:void(0)" onclick="query('${m.id}')"><span class="icon-send-o"></span>查看</a>
      <c:choose>
        <c:when test="${m.fszt eq '1'}">
        </c:when>
        <c:otherwise>
       <a class="button border-green" href="javascript:void(0)" onclick="withdraw('${m.id}')"><span class="icon-share"></span>撤回</a>
        </c:otherwise>
     </c:choose>
      <a class="button border-red" href="javascript:void(0)" onclick="deleteMessage('${m.id}')"><span class="icon-trash-o"></span> 删除</a> </div></td>
    </tr>
    </c:forEach>
   
    <c:if test="${messageList==null }">
      <span>暂无任何数据...</span>
    </c:if>
  </table>
   </c:if>
</div>
	
</body>
</html>