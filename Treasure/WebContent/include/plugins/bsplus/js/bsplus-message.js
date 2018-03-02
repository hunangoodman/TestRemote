var BsplusMessage = function($, $common) {
	"use strict";// 严格模式
	var $module, $constants = $common.constants, $lc = $common.loadingConfig, $ac = $common.alertConfig, $tc = $common.toastConfig, $ic = $common.insertConfig, $position = [ "left-top", "left-center", "left-bottom", "center-top", "center-center", "center-bottom", "right-top", "right-center", "right-bottom" ];
	return {
		name : "BsplusMessage",
		initialize : function() {
			$module = this;
		},
		loading : function(opts) {
			try {
				var $opts = $.extend({}, $lc, opts);
				this.each(function() {
					var $this = $(this), $loading, $back;
					$loading = $this.children("div.bsplus-loading");
					$back = $this.children("div.loading-back");
					if ($loading.length == 0) {
						$loading = $('<div class="bsplus-loading"><div class="loading-message"><i class="fa fa-spin"></i><span class="message"></span></div></div>');
						if ($this.is("body")) {
							$loading.css("position", "fixed");
						} else {
							$loading.css("position", "absolute");
						}
						$this.append($loading);
						$loading.listenPlusEvent({
							onHidden : opts.onHidden
						});// 事件监听初始化
					}
					if ($opts.showBorder) {
						$loading.addClass("loading-boxed");
					} else {
						$loading.removeClass("loading-boxed");
					}
					if ($opts.iconCls) {
						$loading.find('i').css("color", $opts.color).addClass($opts.iconCls);
					}
					$loading.find('span.message').css("color", $opts.color).html("&nbsp;" + $opts.msg);
					$loading.children("div.loading-message").css({
						"background-color" : $opts.backColor,
						"border-color" : $opts.borderColor,
						"border-radius" : $opts.borderRadius
					});
					if ($opts.showMask) {
						if ($back.length == 0) {
							$back = $('<div class="loading-back"></div>');
							if ($this.is("body")) {
								$back.css("position", "fixed");
							} else {
								$back.css("position", "absolute");
							}
							$this.append($back);// 添加加载提示框
						}
						$back.css({
							"background-color" : $opts.maskColor,
							"opacity" : $opts.opacity
						});
					}
					$loading.show();
					$back.show();
					if ($opts.timeout && !isNaN($opts.timeout) && $opts.timeout > 0) {
						setTimeout(function() {// 自动隐藏
							$module.unloading.call($this);// 隐藏加载提示框
						}, $opts.timeout);
					}
				});
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		unloading : function() {
			try {
				var $loading = this.children("div.bsplus-loading"), $back = this.children("div.loading-back");
				$loading && $loading.is(":visible") && $loading.fadeOut().triggerPlusEvent("onHidden");// 隐藏时触发事件
				$back && $back.is(":visible") && $back.fadeOut();
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		alert : function() {
			var $opts = {};
			try {
				if (arguments) {
					if (arguments.length == 1) {
						if ("object" == typeof arguments[0]) {
							$opts = arguments[0];
						} else {
							$opts.msg = arguments[0];
						}
					} else if (arguments.length > 1) {
						if ("object" == typeof arguments[0]) {
							$opts = arguments[0];
						} else {
							$opts.msg = arguments[0];
						}
						$opts.callback = arguments[1];
					}
				}
				$opts = $.extend({}, $ac, $opts);
				var $alert = $('<div class="modal-backdrop alert-backdrop" style="display: none;"></div><div class="modal bs-example-modal-sm alert-modal"><div class="modal-dialog modal-sm" style="top:30%;left:38%;position: absolute;"><div class="modal-content" style="' + ($opts.round ? '' : 'border-radius: 0px;') + '"><div class="modal-header"><h4 class="modal-title"><span class="' + $opts.iconCls + '" aria-hidden="true"></span>&nbsp;' + $opts.title + '</h4></div><div class="modal-body"><p class="alert-msg">' + $opts.msg + '</p></div><div class="modal-footer"><button type="button" class="btn btn-primary btn-sm" role="confirm">' + $opts.btnText + '</button></div></div></div></div>');
				$("body").append($alert);
				$alert.listenPlusEvent({
					confirm : $opts.callback
				});// 事件监听初始化
				$alert.find("button[role='confirm']").unbind("click").click(function(e) {
					$alert.triggerPlusEvent("confirm");// 触发回调事件
					$alert.fadeOut(function() {
						$alert.remove();
					});
				});
				$alert.fadeIn();
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		showToast : function(opts) {
			var $opts = $.extend({}, $tc, opts);
			var $toast = $('<div class="bsplus-toast"><div class="toast-body">' + ($opts.showCloseBtn ? '<button type="button" class="close-btn">×</button>' : '') + ($opts.showProgress ? '<div class="toast-progress" style="width: 100%;"></div>' : '') + '<div class="toast-icon"><i class="' + $opts.iconCls + '"></i></div><div class="toast-content"><div class="toast-head"><div class="toast-title">' + $opts.title + '</div></div><div class="toast-message">' + $opts.content + '</div></div></div></div>');
			var $toastBody = $toast.find("div.toast-body"), $closeBtn = $toast.find("button.close-btn");
			var $p = $opts.position, $ps;
			$toast.listenPlusEvent({// 事件监听
				onHidden : $opts.onHidden
			});
			if ($.inArray($p, $position) < 0) {
				$p = "center-center";
			}
			$ps = $p.split("-");
			if ("top" == $ps[1]) {
				$toast.css({
					"top" : "0px"
				});
			} else if ("bottom" == $ps[1]) {
				$toast.css({
					"bottom" : "0px"
				});
			} else {
				$toast.css({
					"top" : "43%"
				});
			}
			$toastBody.css({
				"width" : $opts.width,
				"borderRadius" : $opts.round ? "3px" : "0",
				"backgroundColor" : $opts.backColor,
				"color" : $opts.color,
				"float" : $ps[0]
			});
			$("body").append($toast.fadeIn());
			$closeBtn.unbind("click").click(function() {
				BsplusMessage.hideLastToast($toast);
			});
			if ($opts.timeOut > 0) {
				BsplusMessage.updateProgress($toast, $opts);
			}
			return this;
		},
		updateProgress : function(toast, opts) {
			var percent = 100, $progress = toast.find("div.toast-progress"), count = opts.timeOut / 50, speed = 100 / count;
			var progressing = function() {
				opts.intervalId = setInterval(function() {
					percent -= speed;
					if ($progress && $progress.length > 0) {
						$progress.width(percent + "%");
					}
					if (percent <= 0) {
						clearInterval(opts.intervalId);
						BsplusMessage.hideLastToast(toast);
					}
				}, 50);
			}
			progressing();
			toast.unbind("hover").hover(function() {
				clearInterval(opts.intervalId);
			}, function() {
				progressing();
			});
		},
		hideLastToast : function(toast) {
			if (!toast) {
				toast = $("div.bsplus-toast");
				toast = $(toast[toast.length - 1]);
			}
			toast && toast.fadeOut(function() {
				toast.triggerPlusEvent("onHidden");
				toast.remove();
			});
		},
		confirm : function(msg, onok, oncancel) {
			try {
				var $confirm = $('<div class="modal-backdrop confirm-backdrop"></div><div class="modal bs-example-modal-sm confirm-modal" style="display: block;padding-right: 17px;top:45%;"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><span aria-hidden="true" class="fa fa-question-circle"></span>&nbsp;' + $constants.confirmTitle + '</h4></div><div class="modal-body"><p class="confirm-msg">' + msg + '</p></div><div class="modal-footer"><button class="btn btn-default btn-sm" type="button" role="cancel">' + $constants.cancel + '</button><button class="btn btn-primary btn-sm" type="button" role="confirm">' + $constants.ok + '</button></div></div></div></div>');
				$("body").append($confirm);
				$confirm.find("button[role='cancel']").unbind("click").click(function(e) {
					"function" == typeof oncancel && oncancel();
					$confirm.fadeOut(function() {
						$confirm.remove();
					});
				});
				$confirm.find("button[role='confirm']").unbind("click").click(function(e) {
					"function" == typeof onok && onok();
					$confirm.fadeOut(function() {
						$confirm.remove();
					});
				});
				$confirm.fadeIn();
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		insertContent : function(options) {
			try {
				var size = {
					sm : "modal-sm",
					md : "",
					lg : "modal-lg"
				};
				var _insert = $('<div class="modal fade" style="display: block; padding-right: 17px;z-index: 10050;"><div class="modal-dialog ' + ((options && options.size && size[options.size]) || "modal-sm") + '" style="margin-top:10%;"><div class="modal-content"><div class="modal-header"><h4 class="modal-title">内容插入</h4></div><div class="form-group"><textarea placeholder="请输入内容" rows="' + (options && options.rows || 4) + '" style="background-color: rgb(255, 255, 255); border: 1px solid rgb(229, 229, 229); border-radius: 4px; width: 90%; font-size: 18px; margin-top: 8%; margin-left: 5%;resize: none;"></textarea></div><div class="modal-footer"><button class="btn btn-default btn-sm" type="button" role="cancel">取消</button><button class="btn btn-primary btn-sm" type="button" role="confirm">确定</button></div></div></div></div>');
				var _title = _insert.find("h4.modal-title");
				var _textarea = _insert.find("textarea");
				_insert.data("title", _title).data("textarea", _textarea);
				_insert.find("button[role='cancel']").unbind("click").click(function(e) {
					_insert.triggerPlusEvent("oncancel");// 触发回调事件
					_insert.modal("hide");
				});
				_insert.find("button[role='confirm']").unbind("click").click(function(e) {
					e.preventDefault();
					var _content = _textarea.val();
					if ($common.isBlank(_content)) {
						$module.showToast({
							content : (options && options.emptyText) ? options.emptyText : $constants.pleaseEnterContent,
							backColor : "#C9302C"
						});
					} else {
						if (!options || (options && options.closeOnOkBtnclick) || options.closeOnOkBtnclick == null) {
							_insert.modal("hide");
						}
						_insert.triggerPlusEvent("onconfirm", {
							content : _content
						});// 触发回调事件
					}
				});
				if (options) {
					_title.html(options.title || $constants.insertContent);
					_textarea.attr("placeholder", options.emptyText || $constants.insertContent);
					options.defaultText && _textarea.val(options.defaultText);
					_insert.listenPlusEvent({
						onconfirm : options.onconfirm,
						oncancel : options.oncancel
					});// 事件监听初始化
				}
				_insert.modal("show");
			} catch (e) {
				$common.log(e);
			}
			return this;
		}
	}
}(jQuery, Common);