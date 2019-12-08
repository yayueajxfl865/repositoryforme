<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<script type="text/javascript" src="${ctxStatic }/jquery/jquery-1.9.1.js"></script>
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
			mui.ajax({  
			url:"${ctx}/queue/queue/ajaxUserdata",
			type:"post",
			async:false,
			success:function(result){
				//alert(result);
				$("#ajaxlist").html(result);
				mui.toast('更新了通知');
				mui('#refreshContainer').pullRefresh().endPulldownToRefresh(); //完成刷新
			},
			error:function(e){
				layer.msg('获取数据失败!', {icon: 2}); 
			}
		});
		}, 1500);
	}
	function loooMessage(id){
		alert(id);
	}
</script>
<body>
   <!--下拉刷新容器-->
	<div id="refreshContainer" class="mui-content mui-scroll-wrapper">
		<div class="mui-scroll">
			<!--数据列表-->
			<ul class="mui-table-view mui-table-view-chevron">
			    <div id="ajaxlist">
			    <c:forEach items="${list }" var="l" varStatus="status">
				<li class="mui-table-view-cell" onclick="loooMessage('${l.id }')">${l.imessage.theme }</li>
				</c:forEach>
				</div>
			</ul>
		</div>
	</div>
</body>
</html>