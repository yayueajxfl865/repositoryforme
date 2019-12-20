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

function back(){//返回上一步
	window.history.back(-1); 
}

function nextClaban(Tid){//选择进行发送
	window.location.href="${ctx }/queue/mobile/nextClaban?Tid="+Tid;
}
</script>
</head>
<body>
    <div>
	<header class="ui-header ui-header-positive ui-border-b">
	    <span style="float: left;" onclick="back()">上一步</span>
	</header>
	</div><br><br>
	<div class="demo-item">
        <div class="demo-block">
            <div class="ui-form ui-border-t">
                <ul class="ui-list ui-list-text ui-list-checkbox ui-border-b">
                    <c:forEach items="${list }" var="t" varStatus="status">
                    <li class="ui-border-t" onclick="nextClaban('${t.id}')">
                        <label class="ui-checkbox">
                            
                        </label>
                        <p>${t.name }</p>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>