<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="page-container">
	<div class="bsplus-grid" data-url="${path}/mgr/function/showList"
		id="function-select-list" data-id-field="functionId"
		data-show-pager="false" data-page-size="100"
		data-ondataload="onFunctionListload" data-empty-text="暂时没有相关数据">
		<div class="grid-body">
			<div class="bsplus-table table-bordered">
				<div class="table-fields">
					<div data-type="select" data-common-cls="text-center"
						data-width="10%">选择</div>
					<div data-field="functionName" data-common-cls="text-center"
						data-width="20%">按钮名称</div>
					<div data-field="code" data-common-cls="text-center">Key值</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function onFunctionListload(e) {
		if ("${param.functionIds}" != "") {
			$("#function-select-list").select({
				field : "functionId",
				fieldValues : "${param.functionIds}".split(",")
			});
		}
	}
</script>