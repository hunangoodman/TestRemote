window.Order = function() {
	"use strict";
	var $grid = $("#order-grid-list");
	return {
		beforeCheck : function(e) {
			var selected = $grid.getSelected();
			if (selected && selected.length > 0) {
				if (1 == selected[0].payStatus) {
					bsplus.showToast({
						content : "该订单已完成支付，不可重复审核。"
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