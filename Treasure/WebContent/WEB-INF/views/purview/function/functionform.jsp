<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<title>功能按钮管理</title>
</head>
<body>
	<form class="form-horizontal" method="post"
		action="${path}/mgr/function/addFunction">
		<input name="functionId" type="hidden" />
		<div class="modal-body">
			<div class="form-group">
				<label for="functionName" class="col-sm-3 control-label">按钮名称</label>
				<div class="col-sm-9">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="text"
							class="form-control" name="functionName" maxlength="100"
							placeholder="请输入按钮名称" data-bv-notempty
							data-bv-notempty-message="请输入按钮名称">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="code" class="col-sm-3 control-label">Key值 </label>
				<div class="col-sm-9">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="text"
							class="form-control" name="code" maxlength="50"
							placeholder="请输入按钮名称" data-bv-notempty
							data-bv-notempty-message="请输入按钮名称">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<textarea class="form-control" rows="2" name="description"></textarea>
				</div>
			</div>
		</div>
	</form>
</body>
</html>