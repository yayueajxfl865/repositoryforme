<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/layui.jsp"%>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div>
   <table class="layui-table">
 
 
  <tbody>
    <tr>
      <td>
      <div><label>系别：</label><input id="cc" value="" style="width: 155px;height:28px; ">
		 <input type="hidden" value="" id="pantryId" name="pantryId">
      </div>
      
      </td>
      <td>
      <div><label>系别：</label><input id="dd" value="" style="width: 155px;height:28px; ">
		 <input type="hidden" value="" id="pantryId" name="pantryId">
      </div>
      </td>
      <td><button type="button" class="layui-btn layui-btn-normal">查询</button></td>
    </tr>
    
  </tbody>
</table>
   
   </div>
<script type="text/javascript">
$(function() {
		$('#cc').combotree({
			url : '${ctx}/queue/queue/tieTree' ,
			required : true,
			onBeforeSelect : function(node) {
				if (!$("#cc").combotree("tree").tree('isLeaf', node.target)) {
					window.top.indexTip('请选择项目', 'error');
					return false;
				}
			},
			onClick : function(node) {

				var id = node.id;
				$("#pantryId").val("");
				$("#pantryId").val(id);
			}
		});
		$('#dd').combotree({
			url : '' ,
			required : true,
			onBeforeSelect : function(node) {
				if (!$("#cc").combotree("tree").tree('isLeaf', node.target)) {
					window.top.indexTip('请选择项目', 'error');
					return false;
				}
			},
			onClick : function(node) {

				var id = node.id;
				$("#pantryId").val("");
				$("#pantryId").val(id);
			}
		});

	});
</script>
</body>
</html>