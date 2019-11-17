<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/layui.jsp"%>
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
<script src="${ctxStatic }/lanseUI/js/jquery.js"></script>
<script src="${ctxStatic }/lanseUI/js/pintuer.js"></script>
</head>
<script type="text/javascript">
   $(function(){
	   layer.closeAll();
   })
   function formComit(){//消息表单提交
	   i = layer.msg("正在添加至至消息队列，等待系统发送...", {icon: 16,rate: 'top',time: 0});
	   var action="${ctx}/queue/queue/sendMessage";
	   $("#pro_form").attr("action",action).submit();
   }
</script>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span>发布通知</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" id="pro_form" action=""> <div class="form-group">
				    <input type="hidden" value="${indexStr }" name="indexStr" id="indexStr">
					<div class="label">
						<label>标题：</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="theme" value="" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>内容：</label>
					</div>
					<div class="field">
						<textarea name="content" style="height: 200px;"></textarea>
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="button" onclick="formComit()">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>