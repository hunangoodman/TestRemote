<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/mgr/module/addModule">
	<input name="moduleId" type="hidden" /> <input name="level"
		type="hidden" id="modelform-sourceLevel" value="0" />
	<div class="modal-body">
		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<label for="moduleName" class="col-sm-4 control-label">模块名称</label>
					<div class="col-sm-8">
						<div class="input-icon">
							<i class="fa fa-asterisk"></i> <input type="text"
								class="form-control" name="moduleName" maxlength="100"
								placeholder="请输入模块名称" data-bv-notempty
								data-bv-notempty-message="请输入模块名称" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6">
				<div class="form-group">
					<label class="col-sm-4 control-label">模块排序</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="openType"
							maxlength="11" placeholder="请输入模块名称" pattern="^[0-9]+$"
							data-bv-regexp-message="只能输入正整数" />
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="parentId" class="col-sm-2 control-label">父级模块</label>
			<div class="col-sm-10">
				<select class="bsplus-select" data-placeholder="请选择数据"
					data-show-checker="true" name="parentId"
					data-onitemclick="selectParent" data-direction="down"
					data-show-filter="true" data-url="${path}/mgr/module/showList"
					data-text-field="moduleName" data-value-field="moduleId"
					data-max-height="250px" data-empty-option="请选择父级模块">
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="path" class="col-sm-2 control-label">资源路径</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="path"
					placeholder="请输入资源路径" />
			</div>
		</div>
		<div class="form-group">
			<label for="isUsed" class="col-sm-2 control-label">是否启用</label>
			<div class="col-sm-10">
				<div class="row">
					<div class="col-xs-2" style="margin-top: 6px;">
						<input type="radio" class="form-control-sm" name="isUsed"
							value="1" checked="checked" data-after-text="启用"/> 
					</div>
					<div class="col-xs-2" style="margin-top: 6px;">
						<input type="radio" class="form-control-sm" name="isUsed"
							value="0" data-after-text="禁用"/> 
					</div>
					<div class="col-xs-8">
						<div class="form-group">
							<label for="icon" class="col-sm-4 control-label">图标样式</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="icon"
									placeholder="请输入图标样式" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="functionIds" class="col-sm-2 control-label">功能名称</label>
			<div class="col-sm-10">
				<input type="hidden" class="form-control" name="functionIds"
					id="modelform-functionIds" />
				<div class="row">
					<div class="col-xs-9">
						<textarea class="form-control" rows="1" name="functionNames"
							id="modelform-functionNames" placeholder="请选择功能"
							readonly="readonly"></textarea>
					</div>
					<div class="col-xs-3">
						<button type="button" class="btn btn-info"
							id="modelform-select_funciton_btn">选择功能</button>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10">
				<textarea class="form-control" rows="1" name="description"></textarea>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	function selectParent(e) {
		var record = e.record;
		if (record) {
			var plevel = record.level;
			if (plevel >= 0) {
				$("#modelform-sourceLevel").val(plevel + 1);
			} else {
				$("#modelform-sourceLevel").val(1);
			}
		} else {
			$("#modelform-sourceLevel").val(0);
		}
	}
	$("#modelform-select_funciton_btn").click(function() {
		bsplus.openModal({// 弹出选择功能界面
			url : "${path}/mgr/function/functionselect?functionIds=" + $("#modelform-functionIds").val(),
			title : "选择功能按钮",
			dialogCls : "modal-dialog modal-lg",
			saveBtnText : "确定",
			width : "50%",
			onSaveBtnclick : function(opts) {
				var selecteds = $("#function-select-list").getSelected();
				if (selecteds && selecteds.length > 0) {
					var ids = [], names = [];
					for (var i = 0, a = selecteds.length; i < a; i++) {
						ids.push(selecteds[i].functionId);
						names.push(selecteds[i].functionName);
					}
					$("#modelform-functionIds").val(ids.join(","));
					$("#modelform-functionNames").val(names.join(","));
				} else {
					$("#modelform-functionIds").val("");
					$("#modelform-functionNames").val("");
				}
				bsplus.destroyModal(opts);// 销毁模态框
			}
		});
	});
</script>