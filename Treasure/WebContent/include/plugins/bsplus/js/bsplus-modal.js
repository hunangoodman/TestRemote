var BsplusModal = function($, $common) {// 模态框
	var $module = null, $ct = $common.constants, $destroy = true, $allIds = [], $activeIds = [], num = 0, $size = [ 'lg', 'sm', 'max' ], $position = [ 'top-left', 'top-center', 'top-right', 'middle-left', 'middle-center', 'middle-right', 'bottom-left', 'bottom-center', 'bottom-right' ], $defaults = {
		url : "",// 页面资源加载地址
		dataUrl : "",// 页面数据加载地址（加载的数据将自动根据表单name设值）
		paramData : null,// 参数对象
		contentType : "application/x-www-form-urlencoded",// ajax请求参数
		overflow : null,// modal-body样式
		dragable : false,// 是否可拖拽
		destroyOnHidden : true,// 隐藏时销毁模态框
		animation : "fade",// 动画(暂且为字符串，输入动画名称，若以后bootstrap提供其它动画亦可使用)
		round : true,// 是否显示圆角
		initValidate : true,// 是否初始化表单验证
		initPlugins : true,// 是否初始化其它插件（非iframe有效）
		inIframe : false,// 是否使用iframe打开地址（前提：url不为空）
		refrshGrid : null,// 刷新的表格对象（为空不刷新，否则刷新）
		refrshCurrent : true,// 刷新表格当前页
		refrshWhen : 1,// 1：保存成功时刷新,2:关闭时刷新
		modalId : null,// 模态框id
		size : "",// lg:大模态框、sm：小模态框、max：最大化（全屏）
		width : null,// 模态框宽度
		height : null,// 模态框高度
		modalPadding : "10px",// modal-body的padding值
		position : "middle-center",// 位置，[top-left:顶部靠左，top-center：顶部居中，top-right:顶部靠右，
		// middle-left:中部靠左，middle-center:居中显示，middle-right:中部靠右，bottom-left:底部靠左，
		// bottom-center：底部居中， bottom-right:底部靠右]
		marginLeft : "auto",// 模态框距离左边的位置（auto：居中显示）
		marginTop : "auto",// 模态框距离顶部的位置（auto：居中显示）
		iframeCls : "embed-responsive-item",// iframe样式
		showCloseBtn : true,// 是否显示右上角关闭小按钮
		title : $ct.dialog,// 模态框标题
		iconCls : "",// 模态框图标样式
		content : null,// 模态框body内显示的内容
		showFooter : true,// 是否显示底部
		btnList : null,// 模态框底部自定义按钮组
		showCancelBtn : true,// 是否显示取消按钮
		cancelBtnCls : "btn btn-default btn-sm",// 取消按钮样式
		cancelBtnIcon : "fa fa-close",
		cancelBtnText : $ct.cancel,// 取消按钮文字
		showSaveBtn : true,// 是否显示确定按钮
		saveBtnCls : "btn btn-primary btn-sm",// 确定按钮文字
		saveBtnIcon : "fa fa-save",
		saveBtnText : $ct.save,// 确定按钮文字
		hideWhen : 2,// 1:保存按钮点击时隐藏/2:数据提交完成时隐藏
		backdrop : true,// backdrop : false:不显示遮罩层，'static':空白处不关闭.
		keyboard : true,// keyboard:false时,esc键盘不关闭.
		closeOnSubmitSuccess : true,// 保存成功时关闭
		onSaveBtnclick : null,// 保存按钮点击事件
		onCancelBtnclick : null,// 取消按钮点击事件
		onSubmitSuccess : null,// 保存按钮保存数据成功时调用
		onready : null,// 模态框创建完时触发
		oninited : null,// 模态框中的控件初始化(包括表单验证)完成时触发
		onshow : null,// show 方法调用之后立即触发该事件
		onshown : null,// 此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发
		onhide : null,// hide 方法调用之后立即触发该事件。
		onhidden : null,// 此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
		onload : null,// ifram元素加载完时触发
		ondataload : null,// 从远端的数据源加载完数据之后触发该事件
		ondestroy : null
	// 模态框销毁时触发
	}, $amcl = function(opts) {// 模态框点击事件
		var $event, $element, $target, $modal = opts.modal;
		if ($modal && !$modal.data("inited")) {
			$modal.click(function(e) {
				$event = e || window.event, $element = $($event.target || $event.srcElement);
				if ($element.length > 0) {
					switch ($element[0].tagName.toLowerCase()) {
					case "i":
					case "span":
					case "button":
						$target = $element.is("button") ? $element : $element.closest("button");
						$target.blur();// 失去焦点
						opts.target = $target;
						if ($target.hasClass("self-btn")) {
							var $onclick = $target.data("onclick");
							if ("function" === typeof $onclick) {
								$onclick(opts);
							} else {
								$common.showToast({
									msg : $ct.customizeEvent,
									shortCutFunction : "warning"
								});
							}
						} else if ("save" === $target.data("action")) {// 保存按钮
							if ("function" === typeof opts.onSaveBtnclick) {
								$modal.triggerPlusEvent("onSaveBtnclick", opts);
								if (1 == opts.hideWhen) {
									$modal.modal("hide");
								}
							} else {
								if (!opts.form) {
									$common.showToast({
										msg : $ct.noForm,
										shortCutFunction : "warning"
									});
									return;
								}
								opts.form.each(function() {
									$(this).find("button:submit")[0].click();
								});
							}
						} else if ("cancel" === $target.data("action")) {
							if ("function" === typeof opts.onCancelBtnclick) {
								$modal.triggerPlusEvent("onCancelBtnclick", opts);
							} else {
								$modal.modal("hide");
							}
						}
						break;
					default:
						break;
					}
				}
			});
		}
	}, $getPs = function(opts) {
		var $sw = $common.getScrollBarWidth(), $cw = document.body.clientWidth, $ch = document.body.clientHeight, $w, $h, $ps, $p1, $p2, $p = {
			mLeft : "auto",
			mTop : "auto",
			width : 600,
			maxHeight : $ch - (opts.showFooter ? 90 : 45),
			height : "auto",
			align : "middle"
		};
		try {
			if (opts.size) {
				if ($size.indexOf(opts.size) >= 0) {
					if ('max' == opts.size) {
						$p.width = $cw;
						$p.height = $p.maxHeight;
						$p.mLeft = 0;
						$p.mTop = 0;
						$p.align = "top";
					} else if ('lg' == opts.size) {
						$p.width = 900;
					} else if ('sm' == opts.size) {
						$p.width = 300;
					}
				} else
					$common.log({
						type : 1,
						name : "ParameterError",
						message : "Parameter of size should be in (lg,sm,max)"
					});
			}
			if (opts.width) {
				if (opts.width.indexOf("%") > 0) {
					$w = parseInt(opts.width) / 100;
					$w = $cw * ($w > 1 ? 1 : $w);
				} else {
					$w = parseInt(opts.width);
					$w = $w < $cw ? $w : $cw;
				}
				$p.width = $w;
			}
			if (opts.height) {
				if (opts.height.indexOf("%") > 0) {
					$h = parseInt(opts.height) / 100;
					$h = $ch * ($h > 1 ? 1 : $h);
				} else {
					$h = parseInt(opts.height);
					$h = $h < $ch ? $h : $ch;
				}
				$p.height = $h - (opts.showFooter ? 90 : 45);
			}
			$p.mLeft = ($cw - $p.width) / 2;
			if (opts.position) {
				if ($position.indexOf(opts.position) >= 0) {
					$ps = opts.position.split("-"), $p1 = $ps[0], $p2 = $ps[1];
					$p.align = $p1;
					$p.mLeft = "left" == $p2 ? 0 : "right" == $p2 ? $cw - $p.width : $p.mLeft
				} else
					$common.log({
						type : 1,
						name : "ParameterError",
						message : "Parameter of position should be in ('top-left', 'top-center', 'top-right', 'middle-left', 'middle-center', 'middle-right', 'bottom-left', 'bottom-center', 'bottom-right')"
					});
			}
			$p.width = $p.width + $sw;
			if (opts.marginLeft && parseInt(opts.marginLeft) >= 0) {
				$p.mLeft = parseInt(opts.marginLeft);
			}
			if (opts.marginTop && parseInt(opts.marginTop) >= 0) {
				$p.mTop = parseInt(opts.marginTop);
			}
		} catch (e) {
			$common.log(e);
		}
		return $p;
	};
	(function() {
		$(window).click(function(e) {
			var $event = e || window.event, $element = $($event.target || $event.srcElement), $modal, $opts;
			if ($element.length > 0 && $element.is("div")) {
				if ($element.hasClass("modal-backdrop")) {
					$modal = $element.prev("div.modal");
				} else if ($element.hasClass("modal-dialog")) {
					$modal = $element.closest("div.modal");
				}
				if ($modal && $modal.hasClass("bsplus-modal")) {
					$opts = $modal.data("opts");
					if (true == $opts.backdrop) {
						$modal.modal("hide");
					}
				}
			}
		});
	}());
	(function() {// Modal扩展
		var $Modal = $.fn.modal.Constructor;
		$Modal.prototype.setScrollbar = function() {// 不设置padding-right值
		}
		$Modal.prototype.resetScrollbar = function() {
		}
	}());
	return {
		name : "BsplusModal",
		initialize : function() {
			$module = this;
		},
		open : function(options) {
			var opts = $.extend({}, $defaults, options), $modal, $dialog, $content, $header, $close, $title, $body, $p = $getPs(opts);
			if (opts.modalId) {
				if ($allIds.indexOf(opts.modalId) >= 0) {
					$module.refrsh($("#" + opts.modalId).modal("show").data("opts", {
						destroyOnHidden : opts.destroyOnHidden,
						backdrop : opts.backdrop,
						keyboard : opts.keyboard,
						url : opts.url,
						dataUrl : opts.dataUrl,
						paramData : opts.paramData,
						inIframe : opts.inIframe,
						content : opts.content,
						contentType : opts.contentType
					}));
					return $modal;
				}
			} else {
				opts.modalId = "modal-" + (num++);
			}
			$allIds.push(opts.modalId);
			$modal = $("<div class='modal bsplus-modal' id='" + opts.modalId + "'></div>");
			opts.modal = $modal;
			$modal.css({
				"display" : "table",
				"overflow" : "visible",
				"width" : $p.width + "px",
				"margin-left" : $p.mLeft >= 0 ? $p.mLeft + "px" : "auto",
				"margin-top" : $p.mTop >= 0 ? $p.mTop + "px" : "auto"
			}).addClass(opts.animation).listenPlusEvent({// 事件监听
				onSaveBtnclick : opts.onSaveBtnclick,
				onCancelBtnclick : opts.onCancelBtnclick,
				onSubmitSuccess : opts.onSubmitSuccess,
				onready : opts.onready,
				oninited : opts.oninited,
				onshow : opts.onshow,
				onshown : opts.onshown,
				onhide : opts.onhide,
				onhidden : opts.onhidden,
				onload : opts.onload,
				ondataload : opts.ondataload,
				ondestroy : opts.ondestroy
			});
			$dialog = $("<div class='modal-dialog' role='document'></div>");
			$dialog.css("vertical-align", $p.mTop >= 0 ? "top" : $p.align);
			$modal.append($dialog);
			opts.dialog = $dialog;
			$content = $("<div class='modal-content'></div>");
			!opts.round && $content.css("border-radius", "0");
			$dialog.append($content);
			$header = $("<div class='modal-header'></div>");
			$content.append($header);
			if (opts.showCloseBtn) {
				$close = $("<button class='close' aria-label='Close' data-dismiss='modal' type='button'><span class='fa fa-remove'></span></button >");
				$header.append($close);
			}
			$title = $("<h4 class='modal-title'></h4>");
			$title.html("<span class='" + (opts.iconCls || "") + "'></span>&nbsp" + opts.title);
			$header.append($title);
			$body = $("<div class='modal-body'></div>");
			$body.css({
				"height" : $p.height,
				"max-height" : $p.maxHeight
			});
			opts.overflow && $body.css("overflow", opts.overflow);
			$content.append($body.css("padding", opts.modalPadding));
			opts.content && $body.append("<div class='modal-body-content'>" + opts.content + "</div>");
			if (opts.inIframe) {
				(function() {
					var $subdiv = $("<div class='embed-responsive' style='height: auto;padding-bottom: 56%'></div>"), $iframe = $("<iframe class='embed-responsive-item'></iframe>");
					if (opts.paramData != null && !$common.isBlank(opts.url, true)) {
						if (opts.url.indexOf("?") < 0) {
							opts.url += "?";
						}
						opts.url += encodeURI($.param(opts.paramData));
					}
					$iframe.attr("src", opts.url);
					opts.iframe = $iframe;
					$iframe.load(function(e) {
						$modal.triggerPlusEvent("onload", opts);
						$module.initCommon(opts);
					});
					$subdiv.append($iframe);
					$body.append($subdiv);
				}());
			} else {
				if (!$common.isBlank(opts.url, true)) {
					(function() {
						var $subdiv = $("<div class='container-fluid' style='height: auto;'></div>"), $content;
						$body.append($subdiv);
						$content = $common.getBodyHtml($common.loadUrl({
							url : opts.url,
							paramData : opts.paramData,
							contentType : opts.contentType
						}));
						if (!$common.isBlank($content, true)) {
							$subdiv.html($content);
						}
					}());
				}
			}
			if (opts.showFooter) {
				(function() {
					var $footer = $("<div class='modal-footer'></div>"), $cancel, $save;
					$content.append($footer);
					if (opts.btnList && opts.btnList.length > 0) {
						$(opts.btnList).each(function(index) {
							var $this = this, $btn = $("<button class='" + ($this.btnCls || "btn btn-default btn-sm") + " self-btn' type='button'><i class='" + ($this.iconCls || "") + "'></i>&nbsp;" + ($this.text || "Button" + index) + "</button >");
							$footer.append($btn);
							$btn.data("onclick", $this.onclick);
						});
					}
					if (opts.showCancelBtn) {
						$cancel = $("<button class='" + opts.cancelBtnCls + "' type='button'><span class='" + (opts.cancelBtnIcon || "") + "' aria-hidden='true'></span>&nbsp;" + opts.cancelBtnText + "</button>");
						$footer.append($cancel.data("action", "cancel"));
					}
					if (opts.showSaveBtn) {
						$save = $("<button class='" + opts.saveBtnCls + "' type='button'><span class='" + (opts.saveBtnIcon || "") + "' aria-hidden='true'></span>&nbsp;" + opts.saveBtnText + "</button>");
						opts.saveBtn = $save;
						$footer.append($save.data("action", "save"));
					}
				}());
			}
			$modal.data("opts", {
				destroyOnHidden : opts.destroyOnHidden,
				backdrop : opts.backdrop,
				keyboard : opts.keyboard,
				url : opts.url,
				dataUrl : opts.dataUrl,
				paramData : opts.paramData,
				inIframe : opts.inIframe,
				content : opts.content,
				contentType : opts.contentType
			});
			$(document.body).append($modal);
			$modal.on('show.bs.modal', function(e) {
				$modal.triggerPlusEvent("onshow", opts);
			});
			$modal.on('shown.bs.modal', function(e) {
				try {
					$activeIds.push($modal[0].id);
				} catch (e) {
					$common.log(e);
				}
				$modal.triggerPlusEvent("onshown", opts);
				if (opts.dragable) {
					$dialog.BsplusDrag("init", {
						"drag-handle" : "div.modal-header",
						"out-parent" : true
					});
				}
			});
			$modal.on('hide.bs.modal', function(e) {
				$modal.triggerPlusEvent("onhide", opts);
			});
			$modal.on('hidden.bs.modal', function(e) {
				try {
					var $id = $modal[0].id, $index = $activeIds.indexOf($id);
					if ($index >= 0) {
						$activeIds.splice($index, 1);
					}
				} catch (e) {
					$common.log(e);
				}
				$modal.triggerPlusEvent("onhidden", opts);
				if ($modal.data("opts").destroyOnHidden) {
					$module.destroy(opts);
				}
			});
			$modal.triggerPlusEvent("onready", opts);
			$modal.modal({
				backdrop : opts.backdrop,
				keyboard : opts.keyboard
			});
			$amcl(opts);
			opts.form = $modal.find("form");
			!opts.inIframe && $module.initCommon(opts);
			return $modal;
		},
		initCommon : function(opts) {
			try {
				if (opts.initValidate) {
					$module.initSave(opts);// 表单初始化
				}
				if (!opts.inIframe) {
					if (opts.initPlugins) {
						opts.modal.initPlugins();
					}
				}
				opts.modal.data("inited", true).triggerPlusEvent("oninited", opts);
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		initSave : function(opts) {
			try {
				var obj = window.BsplusForm;
				opts.inIframe && (obj = opts.iframe[0].contentWindow.BsplusForm);
				obj && obj.init(opts);
			} catch (e) {
				$common.log(e);
			}
		},
		refrsh : function(modal) {
			var $opts, $iframe, $content;
			if (modal) {
				$opts = modal.data("opts");
				if ($opts) {
					$opts.content && modal.find("div.modal-body-content").html($opts.content);
					if ($opts.inIframe) {
						$iframe = modal.find("iframe");
						$iframe.attr("src", $iframe.attr("src"));
					} else {
						if (!$common.isBlank($opts.url, true)) {
							$content = $common.getBodyHtml($common.loadUrl({
								url : $opts.url,
								paramData : $opts.paramData,
								contentType : $opts.contentType
							}));
							if (!$common.isBlank($content, true)) {
								modal.find("div.container-fluid").html($content);
							}
						}
					}
				}
			}
			return this;
		},
		close : function(opts) {// 关闭模态框(默认关闭最后打开的一个)
			try {
				var $modal = opts ? opts.modal : null, $length = $activeIds.length;
				if (!$modal && $length > 0) {
					$modal = $("#" + $activeIds[$length - 1]);
				}
				$modal && $modal.modal("hide");
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		destroy : function(opts) {// 销毁（移除）模态框
			try {
				var $modal = opts ? opts.modal : null, $length = $activeIds.length, $id, $index;
				if (!$modal && $length > 0) {
					$modal = $("#" + $activeIds[$length - 1]);
				}
				if ($modal) {
					if ($modal.is(":visible")) {
						$modal.modal("hide").data("opts").destroyOnHidden = true;
					} else {
						try {
							$id = $modal[0].id, $index = $allIds.indexOf($id);
							if ($index >= 0) {
								$allIds.splice($index, 1);
							}
						} catch (e) {
							$common.log(e);
						}
						$modal.triggerPlusEvent("ondestroy", opts);
						$modal.next("div.modal-backdrop").remove();
						$modal.remove();
					}
				}
			} catch (e) {
				$common.log(e);
			}
			return this;
		}
	}
}($, Common);