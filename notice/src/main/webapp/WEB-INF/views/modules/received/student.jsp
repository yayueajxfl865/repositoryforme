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
<script src="${ctxStatic }/lanseUI/js/pintuer.js"></script>
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
		window.location.href = "${ctx }/queue/queue/loginQueue?indexs="+ indexs;
		var indexs = []; // 存放选中的人员信息记录的下标
		var flag = false;
		$("input[name='chek[]']").each(function() {
			if (this.checked) {
				var index = $(this).val();
				indexs.push(index);
				flag = true;
			}
		});
		if (flag) {
			alert("haha");
			//layer.msg('正在加载...', { icon: 16 ,shade: 0.01});
			window.location.href = "${ctx }/queue/queue/loginQueue?indexs="+ indexs;
		}
		else{
			layer.alert('请选择人员');
			return false;
		}
	}
</script>
<body>
	<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">学生管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <button type="button"  class="button border-green" onclick="checkall()"><span class="icon-check"></span> 全选</button>
          
          <button class="button bg-main icon-check-square-o" type="submit" onclick="nextTep()">下一步</button>
       
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="50"></th>
        <th>姓名</th>       
        <th>班别</th>
        <th>系别</th>
        <th>操作</th>       
      </tr>   
      <c:forEach items="${userList }" var="u" varStatus="status">
        <tr>
          <td>
          <input type="checkbox" name="chek[]" value="${u.yb_userid }" /></td>
          <td>${u.yb_realname }</td>
          <td>${u.claban.name }</td>
          <td>${u.claban.tie.name }</td>  
          <td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
       </c:forEach>   
      <tr>
        <td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
      </tr>
    </table>
  </div>
</form>
</body>
</html>