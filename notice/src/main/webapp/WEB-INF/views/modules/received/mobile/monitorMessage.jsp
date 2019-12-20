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
</head>
<body>

	<div class="demo-item">
        <p class="demo-desc">表单多选项,普通列表中多选项</p>
        <div class="demo-block">
            <div class="ui-form ui-border-t">
                <form action="#">
                    <div class="ui-form-item ui-form-item-checkbox ui-border-b">
                        <label class="ui-checkbox">
                            <input type="checkbox" />
                        </label>
                        <p>表单中用于多选操作</p>
                    </div>
                    <div class="ui-form-item ui-form-item-checkbox ui-border-b">
                        <label class="ui-checkbox">
                            <input type="checkbox" checked=""/>
                        </label>
                        <p>表单中用于多选操作</p>
                    </div>
                </form>
                <ul class="ui-list ui-list-text ui-list-checkbox ui-border-b">
                    <li class="ui-border-t">
                        <label class="ui-checkbox">
                            <input type="checkbox" />
                        </label>
                        <p>普通列表ui-list中用于多选操作</p>
                    </li>
                    <li class="ui-border-t">
                        <label class="ui-checkbox">
                            <input type="checkbox" checked="" />
                        </label>
                        <p>普通列表中用于多选操作</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>