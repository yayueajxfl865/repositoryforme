<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css">
<script type="text/javascript" src="${ctxStatic }/jquery/jquery-1.9.1.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
function stuMange(){//学生管理
	window.location.href="${ctx }/queue/mobile/tieList";
}

function teaMange(){//教师管理
	
}

function stMange(){//社团管理
	
}


</script>
<body>

<div class="demo-item">
        <div class="demo-block">
            <div class="ui-tooltips ui-tooltips-guide" onclick="stuMange()">
                <div class="ui-tooltips-cnt ui-tooltips-cnt-link ui-border-b">
                    <i class="ui-icon-talk"></i>学生管理
                </div>
            </div>
            <div class="ui-tooltips ui-tooltips-guide" onclick="teaMange()">
                <div class="ui-tooltips-cnt ui-tooltips-cnt-link ui-border-b">
                    <i class="ui-icon-talk"></i>教师管理
                </div>
            </div>
            <div class="ui-tooltips ui-tooltips-guide" onclick="stMange()">
                <div class="ui-tooltips-cnt ui-tooltips-cnt-link ui-border-b">
                    <i class="ui-icon-talk"></i>社团管理
                </div>
            </div>
        </div>
    </div>
</body>
</html>