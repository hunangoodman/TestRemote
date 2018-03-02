window.Member = function() {
	"use strict";
	var $grid = $("#member-list");
	return {
		lock : function(e) {
			var selecteds = $grid.getSelected();
			var status = 1;
			if (selecteds == null || selecteds.length == 0) {
				bsplus.showToast({
					content : "请先选择要锁定或要解锁的数据！"
				});
				return false;
			} else if (selecteds.length > 1) {
				bsplus.showToast({
					content : "不允许批量操作！"
				});
				return false;
			}
			if (selecteds[0].status == 1) {
				status = 0;
			} else {
				status = 1;
			}
			bsplus.confirm(status == 0 ? "您确定解锁该用户？<font color='red'>【注：解锁后用户可正常登陆系统！】</font>" : "您确定锁定该用户？<font color='red'>【注：被锁定的用户将无法登陆系统！】</font>", function() {
				bsplus.submitData({
					blockTarget : $grid,// 锁定页面元素（加载提示）
					refrshCurrent : true,// 刷新当前页
					refrshGrid : $grid,// 要刷新的数据表格对象
					url : bsplus.path + "/member/mgr/updateMemberLock",// 数据提交地址
					data : {// 数据对象
						key : selecteds[0].id,
						status : status
					}
				});
			});
			return false;
		},
		recharge : function(e) {
			var selecteds = $grid.getSelected();
			if (selecteds == null || selecteds.length == 0) {
				bsplus.showToast({
					content : "请先选择要充值的用户！"
				});
				return false;
			} else if (selecteds.length > 1) {
				bsplus.showToast({
					content : "不允许批量操作！"
				});
				return false;
			} else if (selecteds[0].verifiedStatus != 1) {
				bsplus.showToast({
					content : "该用户为通过实名认证，不可充值！"
				});
				return false;
			}
			return true;
		}
	}
}();