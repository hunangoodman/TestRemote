/**
 * 角色管理模块js
 */
;
var PlusRole = function() {
	"use strict";
	var $rolelistGrid = $("#rolelist-dataGrid1"), $target = $rolelistGrid.find("div.grid-body");
	return {
		openRoleresource : function() {
			if ($rolelistGrid.length == 0) {
				$rolelistGrid = $("#rolelist-dataGrid1");
			}
			if ($target.length == 0) {
				$target = $rolelistGrid.find("div.grid-body");
			}
			var selecteds = $rolelistGrid.getSelected();
			if (!selecteds || selecteds.length == 0) {
				bsplus.showToast({
					content : "请先选择数据！"
				});
				return;
			} else if (selecteds.length > 1) {
				bsplus.showToast({
					content : "不允许批量操作！"
				});
				return;
			}
			$("#bsplus-tabs").addTab({
				code : "role-resource",
				title : "分配权限",
				url : bsplus.path + "/mgr/module/moduleselect?roleId=" + selecteds[0].roleId,
				showRefresh : false
			});
		},
		closeTab : function() {
			$("#bsplus-tabs").closeTab("role-resource");
		},
		saveData : function() {
			var datas = this.getDatas();
			if (!datas || datas.length == 0) {
				bsplus.showToast({
					content : "请先选择要分配的模块！",
					backColor : "#d9534f"
				});
				return;
			}
			bsplus.submitData({
				url : bsplus.path + "/mgr/role/addRoleResource",// 数据提交地址
				toastOptions : {
					timeOut : 1000,
					onHidden : PlusRole.closeTab
				},
				data : {
					datas : JSON.stringify(datas)
				}
			});
		},
		renderFunction : function(e) {
			var functions = e.record.functions, html = [];
			if (functions && functions.length > 0) {
				for (var i = 0, a = functions.length; i < a; i++) {
					html[i] = '&nbsp;&nbsp;<input class="fn-checker" ' + (functions[i].checked ? 'checked="checked"' : '') + ' value="' + functions[i].functionId + '" type="checkbox" data-after-text="' + functions[i].functionName + '">';
				}
				return '<div><button type="button" class="btn btn-info btn-ssm" onclick="PlusRole.checkAll(this)" style="margin-top: -2px;">全选</button>' + html.join("")+"</div>";
			}
			return "";
		},
		checkAll : function(e) {
			var $this = $(e), $cell = $this.closest("div.cell-content");
			if ($this.data("checked")) {
				$this.html("全选").data("checked", false);
				$cell.find("input:checkbox").check(false);
			} else {
				$this.html("取消").data("checked", true);
				$cell.find("input:checkbox").check(true);
			}
		},
		getDatas : function() {
			var $moduleGrid = $("#module-select-grid"), selecteds = $moduleGrid.getSelectedRows(), datas = [], roleId = $("#roleId").val(), $fns;
			if (selecteds && selecteds.length > 0) {
				for (var i = 0, a = selecteds.length; i < a; i++) {
					$fns = selecteds[i].find("input.fn-checker:checkbox:checked");
					if ($fns && $fns.length > 0) {
						$fns.each(function() {
							datas.push({
								roleId : roleId,
								moduleId : selecteds[i].data("record").moduleId,
								functionId : this.value
							});
						});
					} else {
						datas.push({
							roleId : roleId,
							moduleId : selecteds[i].data("record").moduleId
						});
					}
				}
			}
			return datas;
		},
		ondataload : function() {
			$("#module-select-grid").select({
				field : "checked",
				fieldValues : [ true ]
			});
		}
	}
}();
$(function() {
	$("#bootbox-roleresource").unbind("click").click(function() {
		PlusRole.openRoleresource();
	});
});