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
	　　 swipeBack: false, //关闭左滑关闭功能
	    container:"#refreshContainer",//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
	    down : {
	      style:'circle',//必选，下拉刷新样式，目前支持原生5+ ‘circle’ 样式
	      color:'#2BD009', //可选，默认“#2BD009” 下拉刷新控件颜色
	      height:'50px',//可选,默认50px.下拉刷新控件的高度,
	      range:'100px', //可选 默认100px,控件可下拉拖拽的范围
	      offset:'0px', //可选 默认0px,下拉刷新控件的起始位置
	      auto: true,//可选,默认false.首次加载自动上拉刷新一次
	      callback :pulldownRefresh //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
	   },
	　　up:{
	　　　　contentrefresh: '正在加载...',
	　　　　contentnomore:'没有更多数据了',
	　　　　callback:pulluploading //上拉加载下一页
	　　}
	 }
	});
	
	function pulldownRefresh(){
		
	}
	function pulluploading(){
		
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