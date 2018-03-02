var BsplusChecker = function($, $common) {// 选择框（checkbox，radio）样式
	var $default = $common.checkerConfig;
	if ("object" == typeof BsplusConfig && "object" == typeof BsplusConfig.checkerConfig) {
		$default = $.extend($default, BsplusConfig.checkerConfig);
	}
	return {
		name : "BsplusChecker",
		init : function(o) {
			var $checks = this;
			if (!$checks.is("input:checkbox,input:radio")) {
				$checks = this.find("input:checkbox,input:radio");
			}
			if (o && "object" === typeof o) {// 初始化data
				for ( var n in o) {
					$checks.data(n, o[n]);
				}
			}
			$checks.each(function() {
				var $this = $(this), $class = $this.data("cls") || $default["cls"], $ishidden = $this.data("hidden"), $color = $this.data("color") || $default["color"], $borderColor = $this.data("border-color") || $default["border-color"];
				if ($this.is("input:checkbox,input:radio") && !$this.data("inited") && $class && "none" != $class) {
					$this.hide();
					$this.data("inited", true);
					$this.closest("label.checkbox,label.radio").removeAttr("class").closest("span.bsplus-checker").removeAttr("class");
					if ("checkbox" == this.type) {
						$this.wrap("<label class='checkbox'></label>");
					} else if ("radio" == this.type) {
						$this.wrap("<label class='radio'></label>");
					}
					var $p = $this.parent("label.checkbox,label.radio");
					$color && $p.css("color", $color);
					$borderColor && $p.css("border-color", $borderColor);
					$p.addClass($class).wrap("<span class='bsplus-checker" + (this.checked ? ' checked' : '') + ($this.prop("disabled") ? ' disabled' : '') + ($ishidden ? ' bsplus-hidden' : '') + "'></span>");
					$this.data("before-text") && $p.closest("span.bsplus-checker").prepend("<label>" + $this.data("before-text") + "</label>");
					$this.data("after-text") && $p.closest("span.bsplus-checker").append("<label>" + $this.data("after-text") + "</label>");
				}
			});
			$checks.change(function(e) {
				var $o = $(this);
				if (this.checked) {
					if ("radio" == this.type && $o.attr("name")) {
						$("input:radio[name='" + this.name + "']").closest("span.bsplus-checker").removeClass("checked");
					}
					$o.closest("span.bsplus-checker").addClass("checked");
				} else {
					$o.closest("span.bsplus-checker").removeClass("checked");
				}
			});
			return this;
		},
		clickChecker : function() {
			if (!this.is("input:checkbox,input:radio")) {
				return this;
			}
			if(this.prop("disabled")){
				return this;
			}
			if (this[0].checked) {
				this.prop("checked", false).trigger("change");
			} else {
				this.prop("checked", true).trigger("change");
			}
			return this;
		},
		check : function(checked, trigger) {
			trigger = false == trigger ? false : true;
			if (!this.is("input:checkbox,input:radio")) {
				return this;
			}
			if(this.prop("disabled")){
				return this;
			}
			this.prop("checked", checked);
			if (trigger) {
				this.trigger("change");
			}
			if (checked) {
				this.closest("span.bsplus-checker").addClass("checked");
			} else
				this.closest("span.bsplus-checker").removeClass("checked");
			return this;
		},
		disabled : function() {
			if (!this.is("input:checkbox,input:radio")) {
				return this;
			}
			this.prop("disabled", true).closest("span.bsplus-checker").addClass("disabled");
			return this;
		},
		enabled : function() {
			if (!this.is("input:checkbox,input:radio")) {
				return this;
			}
			this.prop("disabled", false).closest("span.bsplus-checker").removeClass("disabled");
			return this;
		},
		refresh : function() {
			try {
				if (!this.is("input:checkbox,input:radio")) {
					return this;
				}
				this.each(function() {
					if (this.checked) {
						if ("radio" == this.type && $common.exist(this.name)) {
							$("input:radio[name='" + this.name + "']").closest("span.bsplus-checker").removeClass("checked");
						}
						$(this).closest("span.bsplus-checker").addClass("checked");
					} else {
						$(this).closest("span.bsplus-checker").removeClass("checked");
					}
				});
			} catch (e) {
				$common.log(e);
			}
			return this;
		}
	}
}(jQuery, Common);