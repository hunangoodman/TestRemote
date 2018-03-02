window.Withdraw = function() {
	"use strict";
	var $grid = $("#withdraw-grid-list");
	return {
		beforeCheck : function(e) {
			var selected = $grid.getSelected();
			if (selected && selected.length > 0) {
				if (1 == selected[0].checkStatus||2 == selected[0].checkStatus) {
					bsplus.showToast({
						content : "该申请已完成审核，不可重复审核。"
					});
					return false;
				}
				return true;
			} else {
				bsplus.showToast({
					content : "请选择要审核的订单！"
				});
			}
		}
	}
}();