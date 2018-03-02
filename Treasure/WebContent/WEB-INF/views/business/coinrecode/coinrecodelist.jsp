<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="priv" uri="/mytag/privilege"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<div class="bsplus-grid" data-url="${path}/member/mgr/showRecord" 
	data-sort-field="id" data-sort-order="desc" data-size-list="[10,50,100,200]" data-id-field="id"
	data-scroll-height="400px" data-empty-text="暂时没有相关数据" data-show-config="true"
	data-multi-select="false" data-scroll-position="top-bottom">
	<div class="grid-head">
		<priv:priv-btn btnname="search">
			<div class="grid-query">
				<form>
					<div class="form-group form-group-sm">
						<div class="input-group">
							<span class="input-group-addon">电话号码</span><input type="text" name="mobile"
								class="form-control" placeholder="请输入电话号码，支持模糊查询" style="width: 200px;" />
								<span class="input-group-addon">用户姓名</span><input type="text" name="fullName"
								class="form-control" placeholder="请输入姓名，支持模糊查询" style="width: 200px;" />
						</div>
						<button type="button" class="btn blue-haze btn-sm" data-action="search">
							<span class="fa fa-search" aria-hidden="true"></span>&nbsp; 查询
						</button>
						<button type="button" class="btn btn-default btn-sm" data-action="reset">
							<span class="fa fa-trash" aria-hidden="true"></span>&nbsp; 清空
						</button>
					</div>
				</form>
			</div>
		</priv:priv-btn>
	</div>
	<div class="grid-body">
		<div class="bsplus-table table-bordered">
			<div class="table-fields">
				<div data-type="index" data-common-cls="text-center" data-width="3%" data-visible="false">序号</div>
				<div data-type="select" data-common-cls="text-center" data-width="3%">选择</div>
				<div data-field="mobile" data-common-cls="text-center" data-allow-sort="true" data-width="8%">电话号码</div>
				<div data-field="userName" data-common-cls="text-center" data-allow-sort="true" data-width="8%">用户姓名</div>
				<div data-field="coin" data-common-cls="text-center" data-allow-sort="true" data-width="8%">拨币数量</div>
				<div data-field="recordDate" data-common-cls="text-center" data-allow-sort="true"
					data-width="8%">创建时间</div>
			</div>
		</div>
	</div>
</div>
<!-- <script src="${path}/js/coin/coinRecord.js" type="text/javascript" charset="UTF-8">
</script> -->
