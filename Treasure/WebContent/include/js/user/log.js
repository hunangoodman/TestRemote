;
var LoginLog = function() {
	"use strict";
	var $grid = $("#user-log-list-grid");
	return {
		onDetailOpen : function(e) {
			if (!e.detailRow.data("inited")) {
				var html = BsplusCommon.loadUrl({
					url : bsplus.path + "user/logdetail",
					contentType : "application/x-www-form-urlencoded",
					paramData : {
						userId : e.record.userId
					}
				});
				e.detailRow.html(html).BsplusGrid("init").data("inited", true);
			}
		}
	}
}();