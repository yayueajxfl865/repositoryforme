<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/mui.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css">
<title>Insert title here</title>
<script type="text/javascript">
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
	var indexs = new Array(); // 存放选中的人员信息记录的下标
	var flag = false;
	$("input[name='chek[]']").each(function() {
		if (this.checked) {
			var index = $(this).val();
			indexs.push(index);
			flag = true;
		}
	});
	if (flag) {
		i = layer.msg("正在提交，请稍候...", {icon: 16,rate: 'top',time: 0});
		window.location.href="${ctx }/queue/queue/arrComit?indexs="+indexs;
	}
	else{
		layer.msg('请选择人员!', {icon: 0}); 
		return false;
	}
}

function nextTie(Tid){//选择系别进行发送
	window.location.href="${ctx }/queue/mobile/nextTie?Tid="+Tid;
}
function sendMessage(){//发送消息
	window.location.href="${ctx }/queue/mobile/sendMessage";
}
</script>
</head>
<body>
    <div>
	<header class="ui-header ui-header-positive ui-border-b">
	    <span style="float: left;" onclick="checkall()">全选</span>
		<span style="float: right;" onclick="sendMessage()">发送</span>
	</header>
	</div><br><br>
	<div class="demo-item">
        <div class="demo-block">
            <div class="ui-form ui-border-t">
                <ul class="ui-list ui-list-text ui-list-checkbox ui-border-b">
                    <c:forEach items="${list }" var="t" varStatus="status">
                    <li class="ui-border-t" onclick="nextTie('${t.id}')">
                        <label class="ui-checkbox">
                            <input type="checkbox" name="chek[]"/>
                        </label>
                        <p>${t.yb_realname }</p>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>