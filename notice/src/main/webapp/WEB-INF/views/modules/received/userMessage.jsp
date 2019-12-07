<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<link rel="stylesheet" href="${ctxStatic }/ui/mui/css/mui.min.css">
<script src="${ctxStatic }/ui/mui/js/mui.min.js"></script>
</head>
<script type="text/javascript">
mui.init({
	  pullRefresh : {
	    container:"#refreshContainer",//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
	    down : {
	      style:'circle',//必选，下拉刷新样式，目前支持原生5+ ‘circle’ 样式
	      color:'#2BD009',
	      callback :pullfresh
	    }
	  }
	});
   

	
	function pullfresh(){//刷新函数
		setTimeout(function() {
			//使用ajax请求数据
			$.ajax({
			url:"${ctx}/queue/queue/ajaxUserdata",
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
			
			mui.toast('更新了5条通知');
			mui('#refreshContainer').pullRefresh().endPulldownToRefresh(); //完成刷新
		}, 5000);

	}
	

</script>
<body>
   <!--下拉刷新容器-->
	<div id="refreshContainer" class="mui-content mui-scroll-wrapper">
		<div class="mui-scroll">
			<!--数据列表-->
			<ul class="mui-table-view mui-table-view-chevron">
				<li class="mui-table-view-cell">data</li>
				<li class="mui-table-view-cell">data</li>
			</ul>
		</div>
	</div>
</body>
</html>