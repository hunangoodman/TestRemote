<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/consult/addConsult">
	<input name="conId" type="hidden" />
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-2 control-label">标题</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="1" name="conHeadline"
						placeholder="请输入标题"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group">
				<label class="col-sm-2 control-label">内容</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" name="conContent"
						placeholder="请输入内容"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>