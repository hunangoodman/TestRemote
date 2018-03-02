var BsplusTab = function($, $common) {// tab动态添加
	"use strict";
	var $module, defaults = {
		dataUrl : null,// 数据加载地址
		targetId : null,// 数据对象设置的元素
		dataField : "obj",// 数据对象字段
		paramData : null,
		initForm : false,
		initPlugins : true,
		showClose : true,
		showRefresh : true,
		inIframe : false,
		ontitleload : null,
		oncontentload : null,
		onclose : null,
		onrefresh : null
	};
	return {
		name : "BsplusTab",
		initialize : function() {
			$module = this;
		},
		init : function(target) {
			try {
				var _ptab = null;
				if (target.hasClass("bsplus-tab")) {
					_ptab = target;
				} else {
					_ptab = target.find("div.bsplus-tab");
				}
				if (_ptab.length > 0) {
					_ptab.each(function() {
						var _this = $(this);
						if (!_this.data("inited")) {
							var _navtabs = _this.children("ul.nav-tabs");
							if (_navtabs.length == 0) {
								_navtabs = $('<ul class="nav nav-tabs" style="margin-bottom: 5px;"></ul>');
								_this.append(_navtabs);
							}
							var _tabcontent = _this.children("div.tab-content");
							if (_tabcontent.length == 0) {
								_tabcontent = $('<div class="tab-content no-space"></div>');
								_this.append(_tabcontent);
							}
							_this.data("inited", true);
							$module.addTabClickListener(_this);
							_this.listenPlusEvent({
								onitemclick : _this.data("onitemclick")
							});
						}
					});
				}
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		addTab : function(opts) {
			var target = this;
			try {
				if (!target) {
					return this;
				}
				if (!target.data("inited")) {
					$module.init(target);
				}
				opts = $.extend({}, defaults, opts);
				opts.code = opts.code || Date.parse(new Date());
				var _navtabs = target.children("ul.nav-tabs"), _tabcontent = target.children("div.tab-content"), _maxSize = target.data("max-size"), _maxMessage = target.data("max-message") || $common.constants.tooMuchTabOpen;
				var _title = target.find("#nav-" + opts.code);
				if (_maxSize > 0 && _navtabs.find("li").length >= _maxSize && _title.length == 0) {
					bsplus.showToast({
						content : _maxMessage
					});
					return this;
				} else {
					_navtabs.children("li.active,li.tabdrop>.dropdown-menu>.active").removeClass("active");
					_tabcontent.children("div.active").removeClass("active");
				}
				if (_title.length == 0) {
					_title = '<li id="nav-' + opts.code + '" class="active"><a href="#tabcontent-' + opts.code + '" role="tab" data-toggle="tab">' + opts.title;
					if (opts.showRefresh) {
						_title += ' <i class="fa fa-refresh refreshbtn" data-code="' + opts.code + '" data-toggle="tooltip" data-placement="left" title="' + $common.constants.refresh + '"></i>';
					}
					if (opts.showClose) {
						_title += ' <i class="fa fa-remove closebtn" data-code="' + opts.code + '" data-toggle="tooltip" data-placement="left" title="' + $common.constants.close + '"></i>';
					}
					_title += '</a></li>';
					_title = $(_title);
					_title.data("opts", opts);
					_navtabs.append(_title);
					_title.listenPlusEvent({
						onclose : opts.onclose,
						onrefresh : opts.onrefresh
					});
					_title.find('i[data-toggle="tooltip"]').tooltip();
					if ("function" === typeof opts.ontitleload) {
						opts.ontitleload({
							target : target,
							title : _title
						});
					}
					var _content = $('<div role="tabpanel" class="tab-pane active" id="tabcontent-' + opts.code + '"></div>');
					_tabcontent.append(_content.data("code", opts.code));
					if (opts.inIframe) {
						_content.loading({
							boxed : true,
							msg : $common.constants.loading
						});
						var bodyiframe = document.createElement("iframe"), height = 700;
						bodyiframe.setAttribute("class", "main-content-inner");
						bodyiframe.setAttribute("src", opts.url);
						bodyiframe.setAttribute("frameborder", "0");
						bodyiframe.setAttribute("width", "100%");
						bodyiframe.style.zoom = "0.80";
						bodyiframe.onload = function(e) {
							_content.unloading();
							height = bodyiframe.contentWindow.document.body.clientHeight;
							bodyiframe.setAttribute("height", height > 700 ? height : 700);
						}
						_content.append(bodyiframe);
					} else {
						if (!$common.isBlank(opts.url, true)) {
							_content.loading({
								boxed : true,
								msg : $common.constants.loadingPage
							});
							setTimeout(function() {
								_content.html($common.getBodyHtml($common.loadUrl({
									url : opts.url,
									paramData : opts.paramData
								})));
								opts.initPlugins && _content.initPlugins();
								opts.initForm && _content.find("form").initForm();
								"function" === typeof opts.oncontentload && opts.oncontentload({
									target : target,
									title : _title,
									content : _content
								});
								if (!$common.isBlank(opts.dataUrl, true)) {
									_content.loading({
										boxed : true,
										msg : $common.constants.loading
									});
									var _data = $common.loadDatas({
										url : opts.dataUrl,
										dataField : opts.dataField,
										paramData : opts.paramData
									});
									try {
										if (!$common.isBlank(opts.targetId, true)) {
											$common.setData($("#" + opts.targetId), _data);
										} else {
											$common.setData(_content, _data);
										}
									} catch (e) {
										$common.log(e);
									}
								}
								_content.unloading();
							}, 10);
						}
					}
				} else {
					_title.addClass('active').data("opts", opts);
					target.find("#tabcontent-" + opts.code).addClass("active");
					$module.refreshTab(target, opts.code);
				}
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		closeTab : function(target, code) {
			try {
				if (!code) {
					return this;
				}
				var _title = target.find("#nav-" + code), _tabcontent = target.find("#tabcontent-" + code);
				if (_title.hasClass("active")) {
					_title.prev().addClass('active');
					_tabcontent.prev().addClass('active');
				}
				_title.triggerPlusEvent("onclose", {
					target : target,
					title : _title,
					content : _tabcontent
				});
				_title.remove();
				_tabcontent.remove();
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		refreshTabByTarget : function(target) {
			var tabpanel = target.closest("div[role='tabpanel']"), _target = tabpanel.closest("div.bsplus-tab"), code = tabpanel.attr("id");
			try {
				if (code.indexOf("tabcontent-") >= 0) {
					this.refreshTab(_target, code);
				} else {
					refreshTab(null, code);
				}
			} catch (e) {
				$common.log(e);
			}
		},
		refreshTab : function(target, code) {
			try {
				if (!code) {
					return this;
				}
				var _title = target.find("#nav-" + code), _tabcontent = target.find("#tabcontent-" + code);
				_title.triggerPlusEvent("onrefresh", {
					target : target,
					title : _title,
					content : _tabcontent
				});
				var opts = _title.data("opts");
				if ($common.isBlank(opts.url, true)) {
					return this;
				}
				_tabcontent.loading({
					boxed : true,
					msg : $common.constants.loadingPage
				});
				if (opts.inIframe) {
					var iframe = _tabcontent.find("iframe");
					iframe.attr('src', opts.url);
				} else {
					setTimeout(function() {
						_tabcontent.html($common.getBodyHtml($common.loadUrl({
							url : opts.url,
							paramData : opts.paramData
						})));
						opts.initPlugins && _tabcontent.initPlugins();
						opts.initForm && _tabcontent.find("form").initForm();
						"function" === typeof opts.oncontentload && opts.oncontentload({
							target : target,
							title : _title,
							content : _tabcontent
						});
						var _dataUrl = opts.dataUrl;
						if (!$common.isBlank(_dataUrl, true)) {
							_tabcontent.loading({
								boxed : true,
								msg : $common.constants.loading,
								timeout : 3000
							});
							var _data = $common.loadDatas({
								url : _dataUrl,
								dataField : opts.dataField,
								paramData : opts.paramData
							});
							try {
								if (!$common.isBlank(opts.targetId, true)) {
									$common.setData($("#" + opts.targetId), _data);
								} else {
									$common.setData(_tabcontent, _data);
								}
							} catch (e) {
								$common.log(e);
							}
						}
						_tabcontent.unloading();
					}, 10);
				}
			} catch (e) {
				$common.log(e);
			}
			return this;
		},
		getActiveTab : function(target) {
			return target.find("div.active[role='tabpanel']");
		},
		addTabClickListener : function(target) {
			var _element = null;
			if (target) {
				target.unbind("click").click(function(e) {
					_element = $(e.target);
					if (_element.length > 0) {
						switch (_element[0].tagName.toLowerCase()) {
						case "i":
							if (_element.hasClass("refreshbtn")) {
								$module.refreshTab(target, _element.data("code"));
							} else if (_element.hasClass("closebtn")) {
								$module.closeTab(target, _element.data("code"));
							}
						case "a":
							var _a = _element.closest("a");
							_a.blur();
						case "li":
							var _li = _element.closest("li");
							target.triggerPlusEvent("onitemclick", {
								tab : _li
							});
						default:
							break;
						}
					}
				});
			}
			return this;
		}
	}
}($, Common);