var BsplusSwitch = function($) {// 开关
	"use strict";
	var $module = null;
	return {
		name : "BsplusSwitch",
		initialize : function() {
			$module = this;
		},
		init : function(o) {
			var $switch = this;
			if (!$switch.hasClass("bsplus-switch")) {
				$switch = this.find("input:checkbox.bsplus-switch");
			}
			if (o && "object" === typeof o) {// 初始化data
				for ( var n in o) {
					$switch.data(n, o[n]);
				}
			}
			$switch.each(function() {
				var $this = $(this);
				if ($this.is("input:checkbox") && !$this.data("inited")) {
					$this.hide();
					$this.data("inited", true);
					var $switcher = $this.closest("div.switcher"), $width = $this.data("width");
					if ($switcher.length > 0) {
						$this = $($this[0].cloneNode(true));
						$switcher.after($this);
						$switcher.remove();
					}
					$this.closest("div.switch-controller").removeAttr("class").closest("div.switcher").removeAttr("class");
					$this.wrap('<div class="switcher"><div class="switch-controller"></div></div>');
					$this.after('<span class="switch-left">' + ($this.data("left-text") || "ON") + '</span><label class="switch-center">&nbsp;</label><span class="switch-right">' + ($this.data("right-text") || "OFF") + '</span>');
					$switcher = $this.closest("div.switcher");
					$width && $width > 0 && $switcher.css("width", $width);
					false == $this.data("animate") && $switcher.removeClass("animate") || $switcher.addClass("animate");
					this.checked && $switcher.addClass("on") || $switcher.addClass("off");
					$switcher.unbind("click").click(function() {
						if ($this.prop("checked")) {
							$this.prop("checked", false).trigger("change");
							$switcher.removeClass("on").addClass("off");
						} else {
							$this.prop("checked", true).trigger("change");
							$switcher.removeClass("off").addClass("on");
						}
					});
				}
			});
		}
	}
}($);
