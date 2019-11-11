<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="cn.yiban.open.common.User"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>通知管理系统</title>
  <script type="text/javascript" src="${ctxStatic }/jquery/jquery-1.9.1.js"></script>
  <script src="${ctxStatic }/ui/layer/layer.js"></script>
  <link rel="stylesheet" href="${ctxStatic }/ui/layui/css/layui.css">
</head>

<%

	//User user = (User) session.getAttribute("currentUser");//user.me()返回一个JSON，获取该JSON种的info信息
	//JSONObject userInfo = null;
	//if (user != null) {
	//	userInfo = JSONObject.fromObject(user.me()).getJSONObject("info");
	//}
%>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">通知管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <!-- 
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
     -->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
        <%--
          <img src="<%=userInfo.getString("yb_userhead")%>" class="layui-nav-img">
          <%=userInfo.getString("yb_username")%>
           --%>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退出登录</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">通知管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">消息发布</a></dd>
            <dd><a href="javascript:;">发布管理</a></dd>
            <dd><a href="javascript:;">基础数据</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">权限管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">权限分配</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">超链接</a></dd>
          </dl>
        </li>
        <!-- 
        <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>
         -->
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
			
		<form action="${ctx}/queue/queue/messagetext" method="post">
		<input type="text" name="userName" style="width: 300px;height: 45px;">
		<input type="submit" value="提交">
		</form>


  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
   
   
   
   
   
   
  </div>
</div>
<script src="${ctxStatic }/ui/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>