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
<title>易班通知发布系统</title>
<link rel="stylesheet" href="${ctxStatic }/lanseUI/css/pintuer.css">
<link rel="stylesheet" href="${ctxStatic }/lanseUI/css/admin.css">
<script src="${ctxStatic }/lanseUI/js/jquery.js"></script>
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="${ctxStatic }/lanseUI/images/y.jpg"
					class="radius-circle rotate-hover" height="50" alt="" />易班通知发布系统
			</h1>
		</div>
   <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span>系统首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> </div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<h2>
			<span class="icon-user"></span>权限管理
		</h2>
		<ul style="display: block">
			<li><a href="info.html" target="right"><span
					class="icon-caret-right"></span>我的权限</a></li>
			<li><a href="info.html" target="right"><span
					class="icon-caret-right"></span>权限分配</a></li>
			<li><a href="${ctx }/queue/queue/pass" target="right"><span
					class="icon-caret-right"></span>密码管理</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>通知管理
		</h2>
		<ul>
			<li><a href="${ctx }/queue/queue/release" target="right"><span
					class="icon-caret-right"></span>发布通知</a></li>
			<li><a href="add.html" target="right"><span
					class="icon-caret-right"></span>发布管理</a></li>
			<li><a href="${ctx }/queue/queue/excelTepImport" target="right"><span
					class="icon-caret-right"></span>基础数据</a></li>
		</ul>
	</div>
<script type="text/javascript">
$(function(){
	//i = layer.msg("正在加载，请稍候...", {icon: 16,rate: 'top',time: 0});
	var src="${ctx }/queue/queue/indexIcon";
	$("#index-icon").attr("src",src);
	$(".leftnav h2").click(function(){
		  $(this).next().slideToggle(200);	
		  $(this).toggleClass("on"); 
	})
	$(".leftnav ul li a").click(function(){
		  $("#a_leader_txt").text($(this).text());
	  	  $(".leftnav ul li a").removeClass("on");
		  $(this).addClass("on");
     })
});
</script>
	<ul class="bread">
		<li><a href="{:U('Index/info')}" target="right" class="icon-home">
				首页</a></li>
		<li><a href="##" id="a_leader_txt">网站信息</a></li>
		<li><b>当前语言：</b><span style="color: red;">中文</php></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a
			href="##">英文</a></li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" rameborder="0" src="" name="right" id="index-icon"
			width="100%" height="100%"></iframe>
	</div>
	<div style="text-align: center;"></div>
</body>
</html>