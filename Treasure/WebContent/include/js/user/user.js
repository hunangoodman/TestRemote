;
var User = function() {
	"use strict";
	var $grid = $("#user-list-grid");
	return {
		lock : function(e) {
			var selecteds = $grid.getSelected();
			var status = 1;
			if (selecteds == null || selecteds.length == 0) {
				bsplus.showToast({
					content : "请先选择要锁定或要解锁的数据！"
				});
				return;
			} else if (selecteds.length > 1) {
				bsplus.showToast({
					content : "不允许批量操作！"
				});
				return;
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
					url : bsplus.path + "/mgr/user/updateUserLock",// 数据提交地址
					data : {// 数据对象
						key : selecteds[0].userId,
						status : status
					}
				});
			});
			return false;
		},
		resetPassword : function(e) {
			var ids = $grid.getSelectedKeys();
			if (ids == null || ids.length == 0) {
				bsplus.showToast({
					content : "请先选择要重置密码的用户！"
				});
				return;
			} else if (ids.length > 1) {
				bsplus.showToast({
					content : "不允许批量操作！"
				});
				return;
			}
			bsplus.confirm("您确定重置密码为:123456?", function() {
				bsplus.submitData({
					blockTarget : $grid,// 锁定页面元素（加载提示）
					refrshCurrent : true,// 刷新当前页
					refrshGrid : $grid,// 要刷新的数据表格对象
					url : bsplus.path + "/mgr/user/resetPassword",// 数据提交地址
					data : {
						userId : ids[0]
					}
				// 数据对象
				});
			});
			return false;
		},
		renderStatus : function(e) {
			if (e.value == "1") {
				return "锁定";
			}
			return "活动";
		},
		renderRole : function(e) {
			var roles = e.record.rlist;
			if (roles && roles.length > 0) {
				var roleNames = [];
				for (var i = 0; i < roles.length; i++) {
					roleNames.push(roles[i].roleName);
				}
				return roleNames.join(",");
			}
			return "";
		},
		renderShop : function(e) {
			var shops = e.record.slist;
			if (shops && shops.length > 0) {
				var shopNames = [];
				for (var i = 0; i < shops.length; i++) {
					shopNames.push(shops[i].shopName);
				}
				return shopNames.join(",");
			}
			return "";
		}
	}
}();