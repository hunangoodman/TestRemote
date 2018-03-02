var BsplusDate = function($, $common) {// 日期控件
	"use strict";
	var $module = null;
	return {
		name : "BsplusDate",
		initialize : function() {
			$module = this;
		},
		init : function(o) {
			var $date = this;
			if (!$date.is("input")) {
				$date = this.find("input.bsplus-date");
			}
			if ($date.length == 0) {
				return this;
			}
			if (o && "object" === typeof o) {// 初始化data
				for ( var n in o) {
					$date.data(n, o[n]);
				}
			}
			if (!$date.hasClass("bsplus-date")) {
				$date.addClass("bsplus-date");
			}
			$date.each(function() {
				var _this = $(this);
				_this.datetimepicker({// 日期控件格式化
					format : _this.data("format") || "yyyy-mm-dd",
					language : _this.data("language") || "zh-CN",
					initialDate : _this.data("initial-date") || new Date(),
					weekStart : _this.data("week-start") || 1,
					todayBtn : false == _this.data("show-today-btn") ? false : true,
					autoclose : false == _this.data("autoclose") ? false : true,
					todayHighlight : false == _this.data("today-highlight") ? false : true,
					startView : _this.data("start-view") || 2,
					minView : _this.data("min-view") || 0,
					maxView : _this.data("max-view") || 4,
					forceParse : false == _this.data("force-parse") ? false : true,
					startDate : _this.data("start-date"),
					endDate : _this.data("end-date"),
					daysOfWeekDisabled : _this.data("disabled-days"),
					keyboardNavigation : false == _this.data("keyboard") ? false : false,
					minuteStep : _this.data("minute-step") || 5,
					pickerPosition : _this.data("position") || "bottom-right"
				});
			});
			$date.change(function() {// 日期验证
				var bvField = $(this).data("bv-field");
				if (!$common.isBlank(bvField, true)) {
					var _validator = $(this).closest("form").data('bootstrapValidator');
					if ($common.exist(_validator)) {
						_validator.updateStatus(bvField, 'NOT_VALIDATED', null).validateField(bvField);
					}
				}
			});
			return this;
		}
	}
}($, Common);