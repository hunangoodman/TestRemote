;
(function($) {
	"use strict";
	$(document).keydown(function(e) {
		switch (e.keyCode || e.which || e.charCode) {
		case 32:
			$("span.bsplus-checker:focus").click();
		case 37:
			if (BsplusCommon.clickTarget) {
				var $grid = BsplusCommon.clickTarget;
				if (!$grid.hasClass("bsplus-grid")) {
					$grid = $grid.closest("div.bsplus-grid");
				}
				if ($grid.length > 0) {
					$grid.scrolls("left");
				}
			}
			break;
		case 39:
			if (BsplusCommon.clickTarget) {
				var $grid = BsplusCommon.clickTarget;
				if (!$grid.hasClass("bsplus-grid")) {
					$grid = $grid.closest("div.bsplus-grid");
				}
				if ($grid.length > 0) {
					$grid.scrolls("right");
				}
			}
			break;
		default:
			break;
		}
	});
	window.BsplusCommon = function($) {// 框架通用模块
		var $common;
		return {
			clickTarget : null,// 记录当前点击对象
			name : "BsplusCommon",
			path : "",
			browser : {
				appname : 'unknown',
				version : 0
			},
			dataField : {// 默认数字字段
				codeField : "code",// 执行返回结果代码字段
				msgField : "msg",// 执行返回结果消息字段
				listField : "list",// 执行返回结果数据集合字段
				objField : "object",// 执行返回结果数据对象字段
				urlField : "url",// 跳转页面地址字段
				pageField : "pageVo"// 执行返回结果分页信息对象字段（针对数据表格）
			},
			constants : {
				operateSuccess : "操作成功！",
				operateFailure : "操作失败！",
				operateError : "操作异常！",
				noOperate : "无任何操作！",
				confirmTitle : "温馨提示",
				ok : "确认",
				cancel : "取消",
				save : "保存",
				dialog : "对话框",
				refresh : "刷新",
				close : "关闭",
				insertContent : "内容插入",
				pleaseEnterContent : "请输入内容",
				canNotSet : "无法设置",
				canNotBeEmpty : "不能为空",
				submitting : "正在提交数据，请稍后...",
				loading : "正在加载数据，请稍后...",
				loadingPage : "正在加载页面，请稍后...",
				deleting : "正在删除数据，请稍候...",
				requestError : "请求出错",
				requestTimedOut : "请求超时",
				filter : "过滤",
				select : "选择",
				selected : "选中",
				allSelected : "全选中",
				selectAll : "全选",
				pleaseSelect : "请选择数据",
				detail : "详情",
				index : "序号",
				quiryFailure : "查询数据失败",
				loadFailure : "数据加载失败",
				unknownError : "未知错误",
				showing : "每页显示",
				record : "条",
				altogether : "共",
				currentlySelected : "当前选中",
				jumpTo : "跳到第",
				page : "页",
				selectOnlyOne : "只能选择一条数据",
				parameterError : "参数错误",
				confirmToDelete : "您确定删除选中数据吗？",
				confirmToSave : "是否保存该行编辑内容？",
				rowNotInEdit : "该行未处于编辑状态",
				cellWithoutEdit : "该表格无正在编辑的单元格",
				canNotToCall : "不能调用此方法",
				confirmToClose : "表单数据已被修改，您确定不保存关闭吗？",
				customizeEvent : "请编写点击事件！",
				noForm : "该页面没有可提交的表单！",
				tooMuchTabOpen : "请先关闭部分tab再打开！",
				notGrid : "不是表格对象，不能调用此方法！",
				notGridRow : "不是表格行对象，不能调用此方法！",
				notGridCell : "不是表格单元格对象，不能调用此方法！",
				notTab : "不是tab插件，不能调用此方法"
			},
			gridConfig : {
				"row-cls" : "row-cls",// 行样式
				"row-selected-cls" : "row-selected-cls",// 行选中样式
				"edit-when" : 2,// 表格编辑触发条件，1或rowclick：行单击时编辑行，2或rowdblclick:行双击时编辑行，3或cellclick：单元格单击时编辑单元格，4或celldblclick：单元格双击时编辑单元格
				"sort-all" : false,// 是否允许数据库排序, 默认排序当前页
				"search-param-page" : "pageVo",
				"search-param-condition" : "condition",
				"search-content-type" : "application/x-www-form-urlencoded",// 查询条件提交的相关信息
				"details-cls" : "row-details",// 详情样式
				"open-details-onload" : false,// 是否自动展开详情
				"details-close-icon" : "fa fa-plus-square",// 详情关闭按钮图标
				"details-loading-icon" : "fa fa-spinner fa-spin",// 详情打开关闭过程按钮图标
				"details-open-icon" : "fa fa-minus-square",// 详情打开按钮图标
				"empty-text" : "没有相关数据",
				"child-icon" : "fa fa-credit-card",// 子节点（末节点）图标
				"parent-close-icon" : "fa fa-folder",// 父节点关闭图标
				"parent-open-icon" : "fa fa-folder-open",// 父节点展开图标
				"expand-tree-onload" : true,
				"size-list" : [ 10, 50, 100 ],
				"page-size" : 10,
				"checker-cls" : "check2",
				"multi-select" : true,
				"empty-cls" : "grid-empty-cls",// 空行样式
				"hide-otherdetails-when-show" : true,
				"default-img" : ""// 默认图片路径
			},
			checkerConfig : {// 选择按钮配置
				"cls" : "check2",
				"color" : "",
				"border-color" : ""
			},
			loadingConfig : {// 加载提示框配置
				msg : "Loading...",
				showBorder : true,
				backColor : "#D7D7D7",// 消息框背景颜色
				borderColor : "#C1BFBF",// 边框颜色
				borderRadius : "7px",// 边框圆角
				iconCls : 'fa-spinner',
				showMask : true,// 是否显示遮罩
				color : '#353333',// 字体颜色
				opacity : "0.3",// 遮罩层透明度
				maskColor : "black",// 遮罩层颜色
				timeout : -1,
				onHidden : null
			},
			alertConfig : {// 消息弹出框配置
				title : "温馨提示",
				btnText : "确定",
				msg : "",
				iconCls : "fa fa-info-circle",
				callback : null,
				round : true,
				backdrop : true
			},
			toastConfig : {// toast消息弹出框配置
				title : "温馨提示",
				content : "消息内容",
				iconCls : "fa fa-info-circle",
				width : "300px",// 可以是尺寸，也可以是百分比
				round : true,// 是否显示圆角
				showCloseBtn : true,// 是否显示关闭按钮
				showProgress : true,// 是否显示进度条
				backColor : "#2F96B4",
				color : "#fff",
				timeOut : 3000,// 自动关闭时间，小于等于0时一直显示
				position : "center-center",// "left-top","left-center","left-bottom","center-top","center-center","center-bottom","right-top","right-center","right-bottom"
				onHidden : null
			// 隐藏后触发事件
			},
			submitParam : {
				blockTarget : null,// 锁定页面元素（加载提示）
				refrshGrid : null,// 数据提交成功之后要刷新的表格
				refrshCurrent : false,// 刷新当前页
				refrshTimeOut : 0,
				url : "",// 数据提交地址
				type : "post",
				async : true,
				cache : true,
				data : null,// 数据对象
				dataType : "json",
				contentType : "application/x-www-form-urlencoded",
				onError : null,
				onSuccess : null,
				onHandleSuccess : null,
				toastOptions : null
			// 提示信息参数
			},
			codeConfig : {
				"true" : {
					success : true,// 标识是否成功
					showToast : true,
					toastOptions : {
						backColor : "#51A351",
						defaultMsg : "操作成功！"
					},
					callback : null
				},
				"false" : {
					success : false,
					showToast : true,
					toastOptions : {
						backColor : "#d9534f",
						defaultMsg : "操作失败！"
					},
					callback : null
				},
				"error" : {
					success : false,
					showToast : true,
					toastOptions : {
						backColor : "#C9302C",
						defaultMsg : "操作异常！"
					},
					callback : null
				},
				"info" : {
					success : false,
					showToast : true,
					toastOptions : {
						backColor : "#2F96B4",
						defaultMsg : "无任何操作！"
					},
					callback : null
				},
				"defaults" : {
					success : false,
					showToast : true,
					toastOptions : {
						backColor : "#2F96B4",
						defaultMsg : "操作失败！"
					},
					callback : null
				}
			},
			initActionBtn : function(button) {// 初始化功能按钮
				if (!button || button.data("inited")) {
					return this;
				}
				var $action = button.data("action"), $options = button.data("options"), $param = null;
				button.listenPlusEvent({
					beforeaction : button.data("beforeaction") || null
				});
				try {
					if (!$common.isBlank($options, true)) {
						$options = eval('(' + $options + ')');
					}
					button.data("options", $options);
				} catch (e) {
					$common.log(e);
				}
				switch ($action) {
				case "add":
					button.data("options", $.extend({
						refrshGrid : button.closest("div.bsplus-grid"),
						refrshCurrent : false
					}, $options));
					break;
				case "edit":
					button.data("options", $.extend({
						paramName : "key",
						refrshGrid : button.closest("div.bsplus-grid"),
						refrshCurrent : true
					}, $options));
					break;
				case "delete":
					button.data("options", $.extend({
						allowbatch : true,
						paramName : "keys"
					}, $options));
					break;
				default:
					break;
				}
				button.data("inited", true);
				return this;
			},
			dataHandle : function(options) {// 数据处理函数
				try {
					var opts = $.extend({}, $common.dataField, options);
					var handler = $common.codeConfig["defaults"];
					var toastOptions = handler.toastOptions;
					if (opts.data) {
						opts.code = $common.getValueByField(opts.data, opts.codeField);
						opts.msg = $common.getValueByField(opts.data, opts.msgField);
						handler = $common.codeConfig[opts.code] || $common.codeConfig["defaults"];
						toastOptions = handler.toastOptions;
						toastOptions.content = opts.msg || toastOptions.defaultMsg;
					}
					handler.showToast && bsplus.showToast($.extend({}, toastOptions, opts.toastOptions));
					"function" === typeof handler.callback && handler.callback(opts);
					"function" === typeof opts.onHandleSuccess && opts.onHandleSuccess(opts);
				} catch (e) {
					$common.log(e);
				}
				return this;
			},
			clickListener : function() {
				$(window).click(function(e) {
					var $event = e || window.event, $element = $($event.target || $event.srcElement), $li = null, $array = [ 'reset', 'clear', 'openModal' ], $events, $form, $bv;
					$common.clickTarget = $element;
					switch ($element[0].tagName.toLowerCase()) {
					case "a":
						if ($element.is("a")) {
							if ($element.hasClass("portlet-collapse")) {
								$element.removeClass("portlet-collapse").addClass("portlet-expand").closest("div.portlet").find("div.portlet-body").slideUp(400);
							} else if ($element.hasClass("portlet-expand")) {
								$element.removeClass("portlet-expand").addClass("portlet-collapse").closest("div.portlet").find("div.portlet-body").slideDown(400);
							} else {
								var $li = $element.closest("li");
								if ($li && $li.data("select-menu-item")) {// 下拉菜单选项
									$commonSelectMenu.clickItem($li);
								}
							}
						}
					case "i":
					case "button":
						var $button = $element;
						if (!$button.is("button")) {
							$button = $element.closest("button");
						}
						if ($button.length == 0) {
							return;
						}
						var $action = $button.data("action");
						if (!$common.isBlank($action, true)) {
							if (!$button.data("inited")) {
								$common.initActionBtn($button);
							}
							if ($array.indexOf($action) < 0) {
								return;
							}
							$events = $button.data("events");
							var $options = $button.data("options");
							if ($events && "function" === typeof $events.beforeaction) {// 执行相关操作之前执行的方法
								try {
									if (!$button.triggerPlusEvent("beforeaction")) {
										return;
									}
								} catch (e) {
									$common.log(e);
								}
							}
							switch ($action) {
							case "reset":
								e.preventDefault();
								var $dataformId = $button.data("formId");
								if (!$common.isBlank($dataformId, true)) {
									$form = $("#" + $dataformId);
								} else {
									$form = $button.closest("form");
								}
								if ($form && $form.length > 0) {
									try {
										$form[0].reset();// 重置表单（通过调用）
										BsplusChecker.refresh.call($form.find("input:checkbox,input:radio"));
										BsplusSelect.refresh.call($form.find("select.bsplus-select"));
										$bv = $form.data('bootstrapValidator');
										if ($bv) {
											$bv.resetForm(true);// 重置验证
										}
										// DOM中的reset方法来重置表单。）
									} catch (e) {
										$common.log(e);
									}
								}
								break;
							case "clear":
								e.preventDefault();
								var $dataformId = $button.data("formId");
								if (!$common.isBlank($dataformId, true)) {
									$form = $("#" + $dataformId);
								} else {
									$form = $button.closest("form");
								}
								if ($form && $form.length > 0) {
									try {
										$form.find("input,textarea,select").val("");
										$form.data('bootstrapValidator').resetForm(true);// 重置验证
										// DOM中的reset方法来重置表单。）
									} catch (e) {
										$common.log(e);
									}
								}
								break;
							case "openModal":
								e.preventDefault();
								$commonModal.open($options);
								break;
							default:
								break;
							}
							$element.blur();
							break;
						}
					case "label":
					case "span":
						(function() {
							var checkr = $element.hasClass("bsplus-checker") ? $element : $element.closest("span.bsplus-checker");
							if (checkr && checkr.length > 0) {
								if (!$element.hasClass("checkbox") && !$element.hasClass("radio")) {
									BsplusChecker.clickChecker.call(checkr.find("input:checkbox,input:radio"));
								}
							} else {
								if ($element.is("span") && $element.hasClass("photos-close")) {
									bsplus.hidePhotos();
								}
							}
						}());
					case "div":
						if ($element.is("div") && ($element.hasClass("bsplus-photos") || $element.hasClass("photos-back"))) {
							bsplus.hidePhotos();
						}
					default:
						break;
					}
					if (!$element.hasClass("config-container") && $element.closest("div.config-container").length == 0) {
						var $cc = $("div.config-container"), _cc;
						$cc.each(function() {
							_cc = $(this);
							if (_cc.is(":visible")) {
								_cc.hide(300, function() {
									BsplusGrid.showOrHideConfig(_cc);
								});
							}
						});
					}
				});
			},
			init : function() {
				$common = this;
				if ("object" == typeof BsplusConfig) {
					"object" == typeof BsplusConfig.codeConfig && ($common.codeConfig = $.extend($common.codeConfig, BsplusConfig.codeConfig));
					"object" == typeof BsplusConfig.gridConfig && ($common.gridConfig = $.extend($common.gridConfig, BsplusConfig.gridConfig));
					"object" == typeof BsplusConfig.dataField && ($common.dataField = $.extend($common.dataField, BsplusConfig.dataField));
					"object" == typeof BsplusConfig.constants && ($common.constants = $.extend($common.constants, BsplusConfig.constants));
					"object" == typeof BsplusConfig.checkerConfig && ($common.checkerConfig = $.extend($common.checkerConfig, BsplusConfig.checkerConfig));
					"object" == typeof BsplusConfig.loadingConfig && ($common.loadingConfig = $.extend($common.loadingConfig, BsplusConfig.loadingConfig));
					"object" == typeof BsplusConfig.alertConfig && ($common.alertConfig = $.extend($common.alertConfig, BsplusConfig.alertConfig));
					"object" == typeof BsplusConfig.toastConfig && ($common.toastConfig = $.extend($common.toastConfig, BsplusConfig.toastConfig));
					"object" == typeof BsplusConfig.submitParam && ($common.submitParam = $.extend($common.submitParam, BsplusConfig.submitParam));
				}
				try {
					var userAgent = window.navigator.userAgent.toLowerCase();
					if (/(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test(userAgent)) {
						$common.browser = {
							appname : RegExp.$1,
							version : RegExp.$2
						};
					} else if (/version\D+(\d[\d.]*).*safari/.test(userAgent)) { // safari
						$common.browser = {
							appname : 'safari',
							version : RegExp.$2
						};
					} else {
						$common.browser = {
							appname : 'unknown',
							version : 0
						};
					}
				} catch (e) {
					$common.log(e);
				}
				$common.clickListener();// 监听点击事件
			},
			getPath : function() {
				$common = this;
				if (!$common.path) {
					try {// 路径初始化
						var curWwwPath = window.document.location.href;
						var pathName = window.document.location.pathname;
						$common.path = curWwwPath.substring(0, curWwwPath.indexOf(pathName)) + pathName.substring(0, pathName.substr(1).indexOf('/') + 1) + "/";
					} catch (e) {
					}
				}
				return $common.path;
			},
			log : function(e) {// 错误信息打印
				try {
					if (1 == e.type) {
						console.log(e.name + ":" + e.message);
					} else
						console.log(e);
				} catch (e) {
				}
			},
			isBlank : function(obj, trim) {// 判断对象或字符串是否为空
				if ("undefined" === typeof obj || null === obj || "" === obj)
					return true;
				if ("string" === typeof obj && trim && "" === obj.trim())
					return true;
				return false;
			},
			isArray : function(obj) {// 判断对象是否属于数组
				if (!obj) {
					return false;
				}
				return Object.prototype.toString.apply(obj) === '[object Array]';
			},
			exist : function(obj) {// 判断对象是否存在(不包括"")
				if (null === obj || "undefined" === typeof obj)
					return false;
				return true;
			},
			getValue : function(value) {
				if (value === null || "null" === value) {
					value = "";
				}
				if (value === 0) {
					value = "0";
				}
				if (value === false) {
					value = "false";
				}
				return value;
			},
			// 获取元素距离各边距离
			getDistance : function(obj) {
				if (!obj instanceof jQuery) {
					obj = $(obj);
				}
				var distance = {};
				distance.top = (obj.offset().top - $(document).scrollTop());
				distance.bottom = ($(window).height() - distance.top - obj.outerHeight());
				distance.left = (obj.offset().left - $(document).scrollLeft());
				distance.right = ($(window).width() - distance.left - obj.outerWidth());
				return distance;
			},
			loadUrl : function(options) {
				try {
					var html = null;
					$.ajax({
						cache : true,
						async : false,// 必须
						type : 'POST',
						url : options.url,
						data : options.paramData,
						contentType : options.contentType || "application/json;charset=utf-8",
						success : function(data) {
							html = data;
						}
					});
					return html;
				} catch (e) {
					$common.log(e);
				}
				return null;
			},
			loadDatas : function(options) {
				try {
					var $dataList = null;
					if (!options.url) {
						return $dataList;
					}
					var $getRequest = options.url.endWith(".json") || options.url.endWith(".text");
					$.ajax({
						cache : true,
						async : false,
						url : options.url,
						dataType : "json",
						type : $getRequest ? "GET" : "POST",
						data : options.paramData,
						error : function(e) {
							$common.log(e);
						},
						success : function(data) {
							if (data) {
								if ("object" === typeof data) {// 查询数据成功，且数据不为空
									var listField = options.listField || $common.dataField.listField;
									if (listField) {
										$dataList = $common.getValueByField(data, listField);
									} else {
										$dataList = data;
									}
									if (!$dataList) {
										$dataList = data;
									}
								} else if ("string" === typeof data) {
									if (data.indexOf("action-flag=login") >= 0) {
										window.top.location.href = bsplus.path + "/mgr/user/login";
									} else {
										$dataList = eval(data);// 将json数组转化为数组对象
									}
								} else {
									$dataList = data;
								}
							}
						}
					});
					return $dataList;
				} catch (e) {
					$common.log(e);
				}
				return null;
			},
			getBodyHtml : function(html) {
				try {
					if (!$common.isBlank(html, true)) {
						if (html.indexOf("<body") >= 0) {
							html = html.split("<body", 2)[1];
							html = html.substring((html.indexOf(">") + 1));
							if (html.indexOf("</body>") >= 0) {
								html = html.split("</body>", 2)[0];
							}
						}
					}
					return html;
				} catch (e) {
					$common.log(e);
				}
				return null;
			},
			getValueByField : function(data, field) {// 根据字段获取值
				var $fileds, $value;
				if (!data) {
					return data;
				}
				try {
					if (!$common.isBlank(field, true)) {
						if (field.indexOf(".") > 0) {
							$fileds = field.split(".");
							if ($fileds.length > 0) {
								$value = data[$fileds[0]];
								if ($value && $fileds.length > 1) {
									for (var a = 1, b = $fileds.length; a < b; a++) {
										$value = $value[$fileds[a]];
									}
								}
							}
						} else {
							$value = data[field];
						}
					}
				} catch (e) {
					$common.log(e);
				}
				return $value;
			},
			getScrollBarWidth : function() {// 获取滚动条宽度
				if ($common.browser.appname.indexOf("ie") >= 0) {
					return 17;
				}
				var inner = $('<div><div/>'), outer = $('<div><div/>'), w1, w2;
				outer.append(inner);
				$('body').append(outer);
				w1 = inner[0].offsetWidth;
				outer.css('overflow', 'scroll');
				w2 = outer[0].offsetWidth;
				if (w1 == w2) {
					w2 = outer[0].clientWidth;
				}
				outer.remove();
				return w1 - w2;
			},
			objClone : function(myObj) {// 克隆对象
				if (myObj == null || 'object' != typeof myObj)
					return myObj;
				var myNewObj = new Object();
				for ( var i in myObj)
					myNewObj[i] = this.objClone(myObj[i]);
				return myNewObj;
			},
			getFormatData : function(f, fl, justVisible) {// 获取表单数据，fl代替空值,justVisible:是否只获取显示的控件数据
				if (!f)
					return null;
				return $common.getFormatDataByInputs(true == justVisible ? f.find("input[name]:visible,textarea[name]:visible,select[name]:visible") : f.find("input[name],textarea[name],select[name]"), fl);
			},
			getFormatDataByInputs : function(inputs, fl) {
				if (!inputs)
					return null;
				try {
					if (inputs.length > 0) {
						var data = {}, $name = null, $value = null, $fileds = null, $this = null;
						inputs.each(function() {
							$this = $(this);
							$fileds = null;
							$name = this.name;
							$value = $this.val();
							if ($this.is("select") && typeof ($this.attr("multiple")) != "undefined") {
								if (!$value || $value.length == 0) {
									$name = $name + "[]";
								}
							}
							if ($this.is("input:checkbox,input:radio")) {
								if (!this.checked) {
									return true;// false时相当于for的break,
									// 如果return
									// true,就相当于for的continure
								}
							}
							if (!$common.isBlank($name, true)) {
								if ($name.indexOf(".") > 0) {// 深度封装
									$fileds = $name.split(".");
									if ($fileds.length > 0) {
										var $o = $common.creatObjByName(data, $fileds[0]);
										if ($fileds.length > 1) {
											for (var i = 1, a = $fileds.length - 1; i < a; i++) {
												$o = $common.creatObjByName($o, $fileds[i]);
											}
										}
										$common.creatObjByName($o, $fileds[$fileds.length - 1], $value || fl || "");
									}
								} else {
									$common.creatObjByName(data, $name, $value || fl || "");
								}
							}
						});
						return data;
					}
				} catch (e) {
					$common.log(e);
				}
				return null;
			},
			creatObjByName : function(obj, name, value) {
				if (name.indexOf("[]") > 0) {
					var $names = name.split("[");
					obj[$names[0]] = obj[$names[0]] || [];
					if ("undefined" != typeof value) {
						obj[$names[0]].push(value);
					}
					return obj[$names[0]];
				} else if ("undefined" != typeof value) {
					if ($common.isArray(obj)) {
						obj.push(value);
					} else {
						obj[name] = value;
					}
				} else {
					obj[name] = obj[name] || {};
				}
				return obj[name];
			},
			initToolTips : function(obj) {
				var $tooltips = null;
				if (obj) {
					$tooltips = obj.find('[data-toggle="tooltip"]');
				} else {
					$tooltips = $('[data-toggle="tooltip"]');
				}
				if ($tooltips.length > 0) {
					$tooltips.tooltip();
				}
			},
			submitData : function(options) {
				try {
					var opts = $.extend({}, $common.submitParam, options);
					if ($common.isBlank(opts.url, true)) {
						throw new Error("URL" + $common.constants.canNotBeEmpty);
					}
					opts.blockTarget && opts.blockTarget.loading({
						boxed : true,
						msg : opts.msg || $common.constants.submitting
					});
					$.ajax($.extend({
						error : function(request) {
							$common.log(request);
							if ("function" === typeof opts.onError) {
								opts.onError(request);
							} else {
								opts.blockTarget && opts.blockTarget.unloading();
								bsplus.showToast({
									content : $common.constants.requestError,
									backColor : "#C9302C"
								});
							}
						},
						success : function(data) {
							if ("string" === typeof data && data.indexOf("action-flag=login") >= 0) {
								window.top.location.href = bsplus.path + "/mgr/user/login";
							}
							opts.blockTarget && opts.blockTarget.unloading();
							if (opts.refrshGrid) {// 是否刷新表格
								if (!opts.refrshCurrent && opts.refrshGrid.data("pageVo")) {
									opts.refrshGrid.data("pageVo").currentPage = 1;// 刷新首页
								}
								setTimeout(function() {
									opts.refrshGrid.reload();// 刷新表格
								}, opts.refrshTimeOut);
							}
							opts.data = data;
							if ("function" === typeof opts.onSuccess) {
								opts.onSuccess(opts);
							} else {
								$common.dataHandle(opts);
							}
						}
					}, opts));
				} catch (e) {
					$common.log(e);
				}
			}
		}
	}($);
	window.Cookies = function() {
		"use strict";
		return {
			setCookie : function(name, value) {
				var Days = 30;
				var exp = new Date();
				exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
				document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
			},
			getCookie : function(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			},
			delCookie : function(name) {
				var exp = new Date();
				exp.setTime(exp.getTime() - 1);
				var cval = Cookies.getCookie(name);
				if (cval != null)
					document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
			}
		}
	}();
	window.BsplusEvent = function($, $common) {// 事件管理
		return {
			name : "BsplusEvent",
			listen : function(events) {
				var $events = this.data("events") || {}, $args = this.data("args") || {}, $arg = null;
				if (events) {
					var $param = null;
					for ( var e in events) {
						$param = events[e];
						if ("function" === typeof $param) {
							$events[e] = $param;
						} else if ("string" === typeof $param) {
							try {
								if ($param.indexOf("()") >= 0) {
									$param = $param.split("(")[0];
								} else if ($param.indexOf("(") >= 0) {
									if ($param.indexOf(")") >= 0) {
										var $params = $param.split("(");
										$param = $params[0];
										if ($params.length > 1) {
											$arg = $params[1].split(")")[0].split(",");
											for (var i = 0, a = $arg.length; i < a; i++) {
												if ("this" === $arg[i]) {
													$arg[i] = this[0];
												} else if ("string" === typeof $arg[i] && $arg[i].indexOf("this.") >= 0) {
													eval("$arg[" + i + "]=this[0]." + $arg[i].split(".")[1]);
												} else if ("event" === $arg[i]) {
													$arg[i] = {
														target : this,
														type : e,
														tpof : "event"
													};
												} else if ("string" === typeof $arg[i] && $arg[i].indexOf("event.") >= 0) {
													$arg[i] = {
														target : this,
														type : e,
														tpof : "event"
													}[$arg[i].split(".")[1]];
												} else {
													try {
														eval("$arg[" + i + "]=" + $arg[i]);
													} catch (e) {
														throw {
															type : 1,
															name : "ReferenceError",
															message : $arg[i] + " is not defined"
														}
													}
												}
											}
											$args[e] = $arg;
										}
									} else {
										throw {
											type : 1,
											name : "SyntaxError",
											message : "missing ) after argument list"
										}
									}
								}
								$events[e] = eval($param);
							} catch (error) {
								$common.log(error);
							}
						} else {
							delete $events[e];
						}
					}
				}
				return this.data("events", $events).data("args", $args);
			},
			trigger : function(type, data) {
				try {
					var $events = this.data("events"), $args = this.data("args"), $arg = null;
					if (!$events || !type) {
						return null;
					}
					if ("function" === typeof $events[type]) {
						$arg = $args[type];// 参数数组
						var arry = [];
						if ($arg && $arg.length > 0) {
							for (var i = 0, a = $arg.length; i < a; i++) {
								if ("object" == typeof $arg[i] && "event" == $arg[i].tpof) {
									arry[i] = $.extend({}, $arg[i], data);
									delete arry[i].tpof;
								} else {
									arry[i] = $arg[i];
								}
							}
							return $events[type].apply(this, arry);// 将this指向target对象
						} else {
							return $events[type].apply(this, [ $.extend({
								target : this,
								type : type
							}, data) ]);// 将this指向target对象
						}
					}
				} catch (e) {
					$common.log(e);
				}
			}
		}
	}($, BsplusCommon);
	window.BsplusMessage = function($, $common) {
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
					if ($loading) {
						if ($loading.is(":visible")) {
							$loading.fadeOut().triggerPlusEvent("onHidden");// 隐藏时触发事件
						} else {
							$loading.hide().triggerPlusEvent("onHidden");
						}
					}
					if ($back) {
						if ($back.is(":visible")) {
							$back.fadeOut();
						} else {
							$back.hide();
						}
					}
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
					var $confirm = $('<div class="modal-backdrop confirm-backdrop" style="display: none;"></div><div class="modal bs-example-modal-sm confirm-modal" style="display: none;padding-right: 17px;top:45%;"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><h4 class="modal-title"><span aria-hidden="true" class="fa fa-question-circle"></span>&nbsp;' + $constants.confirmTitle + '</h4></div><div class="modal-body"><p class="confirm-msg">' + msg + '</p></div><div class="modal-footer"><button class="btn btn-default btn-sm" type="button" role="cancel">' + $constants.cancel + '</button><button class="btn btn-primary btn-sm" type="button" role="confirm">' + $constants.ok + '</button></div></div></div></div>');
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
						md : "md",
						lg : "modal-lg"
					};
					var _insert = $('<div class="modal fade" style="display: block; padding-right: 17px;z-index: 10050;"><div class="modal-dialog ' + ((options && options.size && size[options.size]) || "modal-sm") + '" style="margin-top:10%;"><div class="modal-content"><div class="modal-header"><h4 class="modal-title">内容插入</h4></div><div class="form-group" style="padding:10px;margin:0;"><textarea placeholder="请输入内容" rows="' + (options && options.rows || 4) + '" style="background-color: rgb(255, 255, 255); border: 1px solid rgb(229, 229, 229); border-radius: 4px; width: 100%; font-size: 18px;resize: none;"></textarea></div><div class="modal-footer"><button class="btn btn-default btn-sm" type="button" role="cancel">取消</button><button class="btn btn-primary btn-sm" type="button" role="confirm">确定</button></div></div></div></div>');
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
						if (options.required && $common.isBlank(_content)) {
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
	}($, BsplusCommon);
	window.BsplusTab = function($, $common) {// tab动态添加
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
									opts.initForm && (BsplusForm.init({
										form : _content.find("form")
									}))
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
	}($, BsplusCommon);
	window.BsplusChecker = function($, $common) {// 选择框（checkbox，radio）样式
		var $default = $common.checkerConfig;
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
					var $this = $(this), $class = $this.data("cls") || $default["cls"], $ishidden = $this.data("hidden"), $color = $this.data("color") || $default["color"], $borderColor = $this.data("border-color") || $default["border-color"], $tabindex = $this.attr("tabindex") || 0, $focus = $tabindex >= 0;
					if ($this.is("input:checkbox,input:radio") && !$this.hasClass("bsplus-switch") && !$this.data("inited") && $class && "none" != $class) {
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
						$p.addClass($class).wrap("<span class='bsplus-checker" + (this.checked ? ' checked' : '') + ($this.prop("disabled") ? ' disabled' : '') + ($ishidden ? ' bsplus-hidden' : '') + "'" + ($focus ? "tabindex='" + $tabindex + "'" : "") + "></span>");
						$this.data("before-text") && $p.closest("span.bsplus-checker").prepend("<label class='checker-text'>" + $this.data("before-text") + "</label>&nbsp;");
						$this.data("after-text") && $p.closest("span.bsplus-checker").append("&nbsp;<label class='checker-text'>" + $this.data("after-text") + "</label>");
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
				if (this.prop("disabled")) {
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
				if (this.prop("disabled")) {
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
	}($, BsplusCommon);
	window.BsplusSwitch = function($) {// 开关
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
						var $switcher = $this.closest("div.switcher"), $width = $this.data("width"), $height = $this.data("height");
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
						$height && $height > 0 && $switcher.css("height", $height);
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
	window.BsplusDrag = function($, $common) {// 拖拽
		var $defaults = {
			handle : null, // 点击哪个元素开始拖动,可为空。不为空时，需要为被拖动元素的子元素。
			onDrag : null, // 拖动时触发的回调。
			direction : 'all', // 拖动方向：['all','vertical','horizontal']
			outParent : false,// 是否允许拖拽出父级标签
			dragArea : null
		// 限制在哪个区域拖动,以数组形式提供[minX,maxX,minY,maxY]
		}, mousePosotion = function(ev) {// 获取鼠标当前坐标
			if (ev.pageX || ev.pageY) {
				return {
					x : ev.pageX,
					y : ev.pageY
				};
			}
			return {
				x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
				y : ev.clientY + document.body.scrollTop - document.body.clientTop
			};
		}, $draging = false, $opts, $currentDrag, $handle, $mp, $params = {// initDiffX|Y
			// :
			// 初始时，鼠标与被移动元素原点的距离
			// moveX|Y : 移动时，被移动元素定位位置 (新鼠标位置与initDiffX|Y的差值)
			// 如果定义了移动中的回调函数，该对象将以参数传入回调函数。
			initDiffX : '',
			initDiffY : '',
			moveX : '',
			moveY : ''
		};
		$(window).mousemove(function(e) {
			if ($draging) {
				// 被移动元素的新位置，实际上鼠标当前位置与原位置之差
				// 实际上，被移动元素的新位置，也可以直接是鼠标位置，这也能体现拖拽，但是元素的位置就不会精确。
				$mp = mousePosotion(e);
				$params.moveX = $mp.x - $params.initDiffX;
				$params.moveY = $mp.y - $params.initDiffY;
				// 是否限定在某个区域中移动.
				// dragArea格式: [x轴最小值,x轴最大值,y轴最小值,y轴最大值]
				if ($opts.dragArea && $common.isArray($opts.dragArea) && $opts.dragArea.length == 4) {
					if ($params.moveX < $opts.dragArea[0]) {
						$params.moveX = $opts.dragArea[0]
					}
					if ($params.moveX > $opts.dragArea[1]) {
						$params.moveX = $opts.dragArea[1]
					}
					if ($params.moveY < $opts.dragArea[2]) {
						$params.moveY = $opts.dragArea[2]
					}
					if ($params.moveY > $opts.dragArea[3]) {
						$params.moveY = $opts.dragArea[3]
					}
				}
				// 移动方向：可以是不限定、垂直、水平。
				if ('all' == $opts.direction) {
					$currentDrag.css({
						'left' : $params.moveX,
						'top' : $params.moveY
					});
				} else if ('vertical' == $opts.direction) {
					$currentDrag.css('top', $params.moveY);
				} else if ('horizontal' == $opts.direction) {
					$currentDrag.css('left', $params.moveX);
				}
				// 如果有回调
				if ($opts.onDrag) {
					// 将$params作为参数传递
					$opts.onDrag.call($opts.onDrag, $params);
				}
			}
		});
		// 鼠标弹起时，标记为取消移动
		$(window).mouseup(function(e) {
			if ($draging) {
				$draging = false;
				$handle[0].releaseCapture && $handle[0].releaseCapture();
			}
		});
		return {
			name : "BsplusDrag",
			init : function(o) {
				if (!this.hasClass("bsplus-drag")) {
					this.addClass("bsplus-drag");
				}
				if (o && "object" === typeof o) {// 初始化data
					for ( var n in o) {
						this.data(n, o[n]);
					}
				}
				this.each(function() {
					var $this = $(this), $do, opts;// 是否正在拖动
					if ($this.data("inited")) {
						return false;
					}
					opts = {
						handle : $this.data("drag-handle") || "",
						onDrag : $this.data("ondrag") || null,
						direction : $this.data("direction") || 'all',
						outParent : $this.data("out-parent") || false,
						dragArea : $this.data("drag-area") || null
					}
					opts = $.extend({}, $defaults, opts);
					if (!opts.dragArea && !opts.outParent) {
						var $parent = $this.parent();
						if ($parent && $parent.length > 0) {
							opts.dragArea = [ 0, $parent.width() - $this.width(), 0, $parent.height() - $this.height() ];
						}
					}
					// 点击哪个元素，以触发移动。
					// 该元素需要是被移动元素的子元素（比如标题等）
					var handle = opts.handle ? $(opts.handle, $this) : $this;
					if (!handle || handle.length <= 0) {
						$common.log('handle is not found! the element must be a child of ' + this.id);
						return false;
					}
					// 被移动元素，需要设置定位样式，否则拖拽效果将无效。
					"static" == $this.css('position') && $this.css('position', 'absolute');
					// 点击时，记录鼠标位置
					handle.addClass("drag-handle").bind('mousedown', function(e) {
						// 标记开始移动
						$opts = opts, $currentDrag = $this, $draging = true, $handle = handle;
						// 改变鼠标形状
						$this.css('cursor', 'move');
						// 捕获事件。（该用法，还有个好处，就是防止移动太快导致鼠标跑出被移动元素之外）
						handle[0].setCapture && handle[0].setCapture();
						// （实际上是鼠标当前位置相对于被移动元素原点的距离）
						$mp = mousePosotion(e);
						$params.initDiffX = $mp.x - $this.position().left;
						$params.initDiffY = $mp.y - $this.position().top;
					});
					// 移动过程
					$this.data("inited", true);
				});
				return this;
			}
		}
	}($, BsplusCommon);
	window.BsplusDate = function($, $common) {// 日期控件
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
					if (!_this.data("inited")) {
						var _width = _this.data("width");
						_this.data("inited", true).wrap('<div class="bsplus-date-group"></div>').datetimepicker({// 日期控件格式化
							format : _this.data("format") || "yyyy-mm-dd",
							language : _this.data("language") || "zh-CN",
							initialDate : _this.data("initial-date") || new Date(),
							weekStart : _this.data("week-start") > -1 ? _this.data("week-start") : 1,
							todayBtn : false == _this.data("show-today-btn") ? false : true,
							autoclose : false == _this.data("autoclose") ? false : true,
							todayHighlight : false == _this.data("today-highlight") ? false : true,
							startView : _this.data("start-view") > -1 ? _this.data("start-view") : 2,
							minView : _this.data("min-view") || 0,
							maxView : _this.data("max-view") > -1 ? _this.data("max-view") : 4,
							forceParse : false == _this.data("force-parse") ? false : true,
							startDate : _this.data("start-date"),
							endDate : _this.data("end-date"),
							daysOfWeekDisabled : _this.data("disabled-days"),
							keyboardNavigation : _this.data("keyboard") || false,
							minuteStep : _this.data("minute-step") > -1 ? _this.data("minute-step") : 1,
							pickerPosition : _this.data("position") || "bottom-right",
							container : _this.data("in-group") ? _this.closest("div.bsplus-date-group") : null
						});
						_width > 0 && (_this.closest("div.bsplus-date-group").css("width", _width));
						if (false != _this.data("show-icon-btn")) {
							_this.after('<span class="fa fa-calendar date-select-btn" data-toggle="tooltip" title="' + (_this.data("tooltip-msg") || "选择日期") + '"></span>');
						}
						if (_this.data("show-clear-btn")) {
							_this.after('<span class="fa fa-close date-clear-btn" data-toggle="tooltip" title="清除"></span>');
						}
						var $group = _this.closest("div.bsplus-date-group");
						$group.find("span.date-select-btn").unbind("click").click(function() {
							_this.datetimepicker("show");
						}).tooltip();
						$group.find("span.date-clear-btn").unbind("click").click(function() {
							_this.val("").trigger("change");
						}).tooltip();
					}
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
	}($, BsplusCommon);
	window.BsplusSelect = function($, $common) {// 多选/单选按钮组
		var $module, $checker = "object" == typeof BsplusChecker ? BsplusChecker : null, $dataField = $common.dataField, $activeSelect, $constants = $common.constants;
		function addClickListener(select) {
			var $controller = select.closest("div.selecter"), $selectText = $controller.find("input.select-text"), $ul = $controller.find("ul.select-list"), $event, $element, $target, $multiple = $common.exist(select.attr("multiple")), $selectedCls = select.data("selected-cls") || "selected", $separator = select.data("separator") || ",", $empty = select.data("empty-option"), $lis = $ul.children("li.select-item"), $allLis = $ul.children("li.select-item,li.select-checkall,li.empty-item"), $length = $lis.length, $filter = $ul.children("li.select-filter").find("input.select-filter-text");
			var $filtering = function() {
				var $v = $filter.val();
				if ($v) {
					$allLis.hide().children("a:contains(" + $v + ")").parent("li").show();
				} else {
					$allLis.show();
				}
			}
			$controller.unbind("click").click(function(e) {
				if ($activeSelect && $activeSelect != select) {
					$module.hidedropdown.call($activeSelect);
				} else {
					$activeSelect = select;
				}
				$event = e || window.event, $element = $($event.target || $event.srcElement);
				if ($element.length > 0) {
					switch ($element[0].tagName.toLowerCase()) {
					case "i":
					case "span":
						$target = $element.is("span") ? $element : $element.closest("span.select-btn,span.clear-btn");
						if ($target.hasClass("select-btn")) {
							if ($ul.is(":visible")) {
								$module.hidedropdown.call(select);
							} else {
								$module.showdropdown.call(select);
							}
							break;
						} else if ($target.hasClass("clear-btn")) {
							$filter.val("");
							$filtering();// 过滤
							break;
						}
					case "input":
					case "div":
						$target = $element.is("div") ? $element : $element.closest("div.select-group");
						if ($target.hasClass("select-group")) {
							if ($ul.is(":visible")) {
								$module.hidedropdown.call(select);
							} else {
								$module.showdropdown.call(select);
							}
						}
					case "a":
					case "li":
						$target = $element.is("li") ? $element : $element.closest("li.select-item,li.select-checkall,li.empty-item");
						if ($target.hasClass("select-item") || $target.hasClass("empty-item")) {
							$element.blur();
							clickItem(select, $target);
						} else if ($target.hasClass("select-checkall")) {
							$element.blur();
							(function() {
								var $values = [], $texts = [];
								if ($target.hasClass($selectedCls)) {
									$target.removeClass($selectedCls).find("input:checkbox").prop("checked", false).trigger("change");
									$lis.removeClass($selectedCls).find("input:checkbox").prop("checked", false).trigger("change");
								} else {
									$target.addClass($selectedCls).find("input:checkbox").prop("checked", true).trigger("change");
									$lis.addClass($selectedCls).find("input:checkbox").prop("checked", true).trigger("change");
									$lis.each(function() {
										var $li = $(this);
										$values.push($li.data("value"));
										$texts.push($li.data("text"));
									});
									select.triggerPlusEvent("onallselected", {
										value : $multiple ? $values : $values.join($separator),
										text : $multiple ? $texts : $texts.join($separator),
										data : select.data("dataList")
									});
								}
								if ($values.length > 0) {
									$selectText.val($texts.join($separator));
									$ul.find("li.empty-item").removeClass($selectedCls).find("input:checkbox,input:radio").prop("checked", false).trigger("change");
								} else {
									$selectText.val($empty || "");
									$ul.find("li.empty-item").addClass($selectedCls).find("input:checkbox,input:radio").prop("checked", true).trigger("change");
								}
								select.val($multiple ? $values : $values.join($separator)).trigger("change").triggerPlusEvent("onitemclick", {
									value : $multiple ? $values : $values.join($separator),
									text : $multiple ? $texts : $texts.join($separator),
									data : select.data("dataList"),
									record : null,
									checked : $target.hasClass($selectedCls),
									item : $target
								});
							}());
							$selectText.attr("title", $selectText.val());
						}
						var bvField = select.data("bv-field");
						if (bvField) {
							var _bootstrapValidator = select.closest("form").data('bootstrapValidator');
							if ($common.exist(_bootstrapValidator)) {
								_bootstrapValidator.updateStatus(bvField, 'NOT_VALIDATED').validateField(bvField);
							}
						}
						break;
					default:
						break;
					}
				}
			});
			$filter.unbind("keyup").keyup(function() {
				$filtering();// 过滤
			});
		}
		var clickItem = function(select, $target, trigger) {
			trigger = false == trigger ? false : true;
			var $controller = select.closest("div.selecter"), $selectText = $controller.find("input.select-text"), $ul = $controller.find("ul.select-list"), $event, $element, $target, $multiple = $common.exist(select.attr("multiple")), $selectedCls = select.data("selected-cls") || "selected", $separator = select.data("separator") || ",", $empty = select.data("empty-option"), $lis = $ul.children("li.select-item"), $allLis = $ul.children("li.select-item,li.select-checkall,li.empty-item"), $length = $lis.length, $filter = $ul.children("li.select-filter").find("input.select-filter-text");
			var $values = [], $texts = [];
			if ($multiple) {
				if ($target.hasClass($selectedCls)) {
					$target.removeClass($selectedCls).find("input:checkbox,input:radio").prop("checked", false).trigger("change");
				} else {
					$target.addClass($selectedCls).find("input:checkbox,input:radio").prop("checked", true).trigger("change");
				}
				$ul.find("li.select-item." + $selectedCls).each(function() {
					var $this = $(this);
					$values.push($this.data("value"));
					$texts.push($this.data("text"));
				});
				if ($values.length > 0) {
					$selectText.val($texts.join($separator));
					$ul.find("li.empty-item").removeClass($selectedCls).find("input:checkbox,input:radio").prop("checked", false).trigger("change");
				} else {
					$selectText.val($empty || "");
					$ul.find("li.empty-item").addClass($selectedCls).find("input:checkbox,input:radio").prop("checked", true).trigger("change");
				}
			} else {
				$values.push($target.data("value"));
				$module.hidedropdown.call(select);
				$ul.children().removeClass($selectedCls).find("input:checkbox,input:radio").prop("checked", false).trigger("change");
				$target.addClass($selectedCls).find("input:checkbox,input:radio").prop("checked", true).trigger("change");
				$selectText.val($target.data("text"));
			}
			select.val($values).triggerPlusEvent("onitemclick", {
				value : $multiple ? $values : $values.join($separator),
				text : $multiple ? $texts : $texts.join($separator),
				record : $target.data("record"),
				checked : $target.hasClass($selectedCls),
				item : $target
			});
			trigger && select.trigger("change");
			if ($multiple && $length == $values.length) {
				select.triggerPlusEvent("onallselected", {
					value : $multiple ? $values : $values.join($separator),
					text : $multiple ? $texts : $texts.join($separator),
					data : select.data("dataList")
				});
				$ul.find("li.select-checkall").addClass($selectedCls).find("input:checkbox").prop("checked", true).trigger("change");
			} else {
				$ul.find("li.select-checkall").removeClass($selectedCls).find("input:checkbox").prop("checked", false).trigger("change");
			}
			$selectText.attr("title", $selectText.val());
		};
		(function() {
			$(window).click(function(e) {
				var $event = e || window.event, $element = $($event.target || $event.srcElement), $target;
				if ($element.length > 0) {
					switch ($element[0].tagName.toLowerCase()) {
					case "input":
					case "i":
					case "span":
					case "button":
					case "a":
					case "label":
					case "li":
					case "div":
						$target = $element.is("div") ? $element : $element.closest("div.selecter");
						if ($activeSelect && $target.hasClass("selecter")) {
							break;
						}
					default:
						if ($activeSelect) {
							$module.hidedropdown.call($activeSelect);
						}
						break;
					}
				}
			});
		}());
		return {
			name : "BsplusSelect",
			initialize : function() {
				$module = this;
			},
			init : function(o) {
				var $select = this;
				if (!$select.is("select")) {
					$select = this.find("select");
				}
				if (o && "object" === typeof o) {// 初始化data
					for ( var n in o) {
						$select.data(n, o[n]);
					}
				}
				if (!$select.hasClass("bsplus-select")) {
					$select.addClass("bsplus-select");
				}
				$select.each(function() {
					var $this = $(this), $direction = $this.data("direction") || "auto", $showBtn = false == $this.data("show-btn") ? false : true, $selectGroup = $('<div class="select-group"><input type="' + ($this.data("input-type") || "text") + '" class="form-control select-text" readonly="readonly" placeholder="' + ($this.data("placeholder") || "") + '"/>' + ($showBtn ? '<span class="select-btn"><i class="fa direction"></i></span>' : '<i class="fa direction"></i>') + '</div>'), $ul = $("<ul class='select-list'></ul>");
					var $optionWidth = $this.data("option-width") || "100%", $width = $this.data("width") || "100%", $maxHeight = $this.data("max-height") || 300;
					var $showChecker = $this.data("show-checker"), $showCheckall = $this.data("show-checkall"), $multiple = $common.exist($this.attr("multiple")), $showFilter = $this.data("show-filter");
					if ($this.is("select") && !$this.data("inited")) {
						$this.hide().wrap("<div class='selecter " + ("up" == $direction ? 'up' : 'down') + "'></div>").after($selectGroup).listenPlusEvent({
							oninited : $this.data("oninited") || null,
							onready : $this.data("onready") || null,
							ondataload : $this.data("ondataload") || null,
							renderer : $this.data("renderer") || null,
							onitemclick : $this.data("onitemclick") || null,
							onallselected : $this.data("onallselected") || null,
							ondropdownshown : $this.data("ondropdownshown") || null,
							ondropdownhidden : $this.data("ondropdownhidden") || null
						});// 事件监听初始化
						var $controller = $this.closest("div.selecter"), $selectText = $selectGroup.find("input.select-text");
						$controller.css("width", $width);
						"up" == $direction ? $selectGroup.before($ul) : $selectGroup.after($ul);
						$showFilter && $ul.append('<li class="select-filter"><div class="input-group"><span class="input-group-addon"><i class="select-filter-search"></i></span><input type="text" class="form-control select-filter-text" placeholder="' + ($this.data("filter-placeholder") || $constants.filter) + '"><span class="input-group-addon clear-btn"><i class="select-filter-clear"></i></span></div></li>');
						$showCheckall && $multiple && $ul.append("<li class='select-checkall'><a href='javascript:void(0)'>" + ($showChecker ? '<input type="checkbox"/>&nbsp;' : '') + ($this.data("select-all-text") || $constants.selectAll) + "</a></li>");
						$ul.css({
							"max-height" : $maxHeight,
							"width" : $optionWidth,
							"min-width" : $optionWidth
						});
						$this.data("inited", true).data("liH", $ul.children().length * 34).triggerPlusEvent("oninited");// 初始化完成
						$module.loadData.apply($this);
						$module.build.apply($this);
						addClickListener($this);
					}
				});
				return this;
			},
			loadData : function() {
				if (!this.is("select")) {
					return this;
				}
				this.each(function() {
					var $this = $(this), $option, $groupField = $this.data("group-field"), $emptyText = $this.data("empty-option"), $hasRender = "function" === typeof ($this.data("events") || {}).renderer, $dataList, $data = $this.data("list");
					var $url = $this.data("url"), $record = null, $content, $thisvalue, $textField = $this.data("text-field"), $valueField = $this.data("value-field"), $value = $this.val() || $this.data("default") || "";
					if ($data && $common.isArray($data)) {
						$dataList = $data;
					} else if ("string" == typeof $data) {
						$dataList = eval($data);
					} else {
						var id = $this.data("list-linked-id");
						if (id) {
							$dataList = $("#" + id).data("dataList");
						}
					}
					if ((!$dataList || $dataList.length == 0) && !$common.isBlank($url, true)) {
						try {
							$dataList = $common.loadDatas({
								url : $url,
								listField : $this.data("list-field")
							});
						} catch (e) {
							$common.log(e);
						}
					}
					$this.data("dataList", $dataList);
					if ($dataList && $common.isArray($dataList)) {
						$this.empty();
						if ($emptyText) {
							$option = $('<option value="">' + $emptyText + '</option>');
							$option.data("record", null);
							$this.append($option);
						}
					}
					if ($dataList && $dataList.length > 0) {
						if ($groupField) {// 分组
							(function() {
								var $optgroup = null, index = 0, $groupValue = null;
								for (var i = 0, a = $dataList.length; i < a; i++) {
									$record = $dataList[i];
									$groupValue = $common.getValueByField($record, $groupField);
									$optgroup = $this.find("optgroup[label='" + $groupValue + "']");
									if ($optgroup.length == 0) {
										$optgroup = $("<optgroup label='" + $groupValue + "'></optgroup>");
										$this.append($optgroup);
									}
									if ("object" == typeof $record) {
										$content = $common.getValueByField($record, $textField);
										$thisvalue = $common.getValueByField($record, $valueField);
									} else {
										$content = $thisvalue = $record;
									}
									$option = $('<option value="' + $thisvalue + '"></option>');
									if ($hasRender) {
										try {
											$content = $this.triggerPlusEvent("renderer", {
												value : $thisvalue,
												text : $content,
												record : $record,
												data : $dataList,
												item : $option
											});
										} catch (e) {
											$common.log(e);
										}
									}
									if ($content == null || "null" == $content) {
										$content = "";
									}
									$option.html($content).data("record", $record);
									$optgroup.append($option);
								}
							}());
						} else {
							for (var i = 0, a = $dataList.length; i < a; i++) {
								$record = $dataList[i];
								if ("object" == typeof $record) {
									$content = $common.getValueByField($record, $textField);
									$thisvalue = $common.getValueByField($record, $valueField);
								} else {
									$content = $thisvalue = $record;
								}
								$option = $('<option value="' + $thisvalue + '"></option>');
								if ($hasRender) {
									try {
										$content = $this.triggerPlusEvent("renderer", {
											value : $thisvalue,
											text : $content,
											record : $record,
											data : $dataList,
											item : $option
										});
									} catch (e) {
										$common.log(e);
									}
								}
								if ($content == null || "null" == $content) {
									$content = "";
								}
								$option.html($content).data("record", $record);
								$this.append($option);
							}
						}
					} else {
						$url && $this.empty();
					}
					$this.triggerPlusEvent("ondataload", {// 数据加载完成事件
						data : $dataList
					});
					$value && $this.val($value);
				});
				return this;
			},
			build : function(o) {
				if (!this.is("select")) {
					return this;
				}
				if (o && "object" === typeof o) {// 初始化data
					for ( var n in o) {
						this.data(n, o[n]);
					}
				}
				if (!this.hasClass("bsplus-select")) {
					this.addClass("bsplus-select");
				}
				this.each(function() {
					var $this = $(this), texts = [], $separator = $this.data("separator") || ",", $selectText, $ul, $li, $optgroup, $controller, $selectedCls = $this.data("selected-cls") || "selected", $selectedCls, $multiple = $common.exist($this.attr("multiple")), $showChecker = $this.data("show-checker");
					if ($this.data("inited")) {
						$controller = $this.closest("div.selecter"), $ul = $controller.find("ul.select-list"), $selectText = $controller.find("input.select-text");
						$this.children().each(function() {
							var $thisoptions = $(this);
							if ($thisoptions.is("optgroup")) {
								$li = $("<li class='group'><label>" + $thisoptions.attr("label") + "</label></li>");
								$ul.append($li).addClass("list-group");
								$thisoptions.children().each(function() {
									var $thisoption = $(this), $text = $thisoption.html(), $li = $("<li class='select-item'><a href='javascript:void(0)'>" + ($showChecker ? ('<input type="' + ($multiple ? 'checkbox' : 'radio') + '"/>') : '') + "&nbsp;" + $text + "</a></li>");
									$li.data("value", $thisoption.val()).data("text", $text);
									if ($thisoption.prop("selected")) {
										$li.addClass($selectedCls);
										texts.push($li.data("text"));
										if ($showChecker) {
											$li.find("input:checkbox,input:radio").prop("checked", true);
										}
									}
									$ul.append($li);
								});
							} else {
								var $thisoption = $(this), $text = $thisoption.html(), $value = $thisoption.val(), $li = $("<li class='select-item'><a href='javascript:void(0)'>" + ($showChecker ? ('<input type="' + ($multiple ? 'checkbox' : 'radio') + '"/>') : '') + "&nbsp;" + $text + "</a></li>");
								$li.data("value", $value).data("text", $text).data("record", $thisoption.data("record"));
								if ("" == $value) {
									$li.removeClass("select-item").addClass("empty-item");
								} else {
									!$thisoption.data("record") && $thisoption.data("record", {
										value : $value,
										text : $text
									});
								}
								if ($thisoption.prop("selected")) {
									$li.addClass($selectedCls);
									texts.push($li.data("text"));
									if ($showChecker) {
										$li.find("input:checkbox,input:radio").prop("checked", true);
									}
								}
								$ul.append($li);
							}
						});
						$selectText.val(texts.join($separator));
						$selectText.attr("title", $selectText.val());
						try {
							$checker && $checker.init.call($ul.find("input:checkbox,input:radio"), {
								"cls" : $this.data("checker-cls")
							});
						} catch (e) {
							$common.log(e);
						}
						addClickListener($this);
						$this.data("liH", $ul.children().length * 34).triggerPlusEvent("onready", {// 初始化完成
							data : $this.data("dataList")
						});
					} else {
						$module.init.apply($this);
					}
				});
				return this;
			},
			rebuild : function(o) {
				if (!this.is("select")) {
					return this;
				}
				if (o && "object" === typeof o) {// 初始化data
					for ( var n in o) {
						this.data(n, o[n]);
					}
				}
				if (!this.hasClass("bsplus-select")) {
					this.addClass("bsplus-select");
				}
				this.each(function() {
					var $this = $(this), $controller = $this.closest("div.selecter");
					$controller.find("li.select-item,li.group").remove();
					$module.build.apply($this);
				});
				return this;
			},
			reload : function() {
				if (!this.is("select")) {
					return this;
				}
				$module.loadData.apply(this);
				$module.rebuild.apply(this);
				return this;
			},
			showdropdown : function() {
				var $this = this, selecter = $this.closest("div.selecter"), $maxHeight = parseInt($this.data("max-height") || "300"), direction = $this.data("direction") || "auto", $liH = $this.data("liH");
				if ("auto" == direction) {// 根据元素绝对位置自动判断方向
					var distance = $common.getDistance(selecter);
					$maxHeight = $liH > $maxHeight ? $maxHeight : $liH;
					direction = distance.bottom - $maxHeight <= 0 ? "up" : "down";
					if ("up" == direction) {
						selecter.removeClass("down").addClass("up");
					} else
						selecter.removeClass("up").addClass("down");
				}
				selecter.find("ul.select-list").slideDown(300, function() {
					$this.triggerPlusEvent("ondropdownshown");
					$activeSelect = $this;
				});
				return this;
			},
			hidedropdown : function() {
				var $this = this, selecter = $this.closest("div.selecter");
				selecter.find("ul.select-list").slideUp(200, function() {
					$this.triggerPlusEvent("ondropdownhidden");
				});
				return this;
			},
			getSelected : function() {
				if (!this.is("select")) {
					$common.log("The method can not be called");
					return null;
				}
				var selecteds = this.find("option:selected"), datas = [];
				if (selecteds) {
					selecteds.each(function() {
						$(this).data("record") && datas.push($(this).data("record"));
					});
				}
				return datas;
			},
			select : function(values, trigger) {
				if (!this.is("select") || !values) {
					return this;
				}
				var $select = this, $selecter = $select.closest("div.selecter"), $multiple = $common.exist(this.attr("multiple")), $selectedCls = this.data("selected-cls") || "selected", $separator = this.data("separator") || ",";
				var datas = [];
				if ("string" === typeof values) {
					if (values.indexOf($separator) >= 0) {
						datas = values.split($separator);
					} else {
						datas.push(values);
					}
				} else if ("number" === typeof values) {
					datas.push(values + "");
				} else if ($common.isArray(values)) {
					datas = values;
				}
				if (datas.length > 0) {
					$selecter.find("li.select-item").each(function() {
						var $this = $(this);
						if (datas.indexOf($this.data("value")) >= 0) {
							clickItem($select, $this, trigger);
							if (!$multiple) {
								return false;
							}
						}
					});
				} else {
					clickItem($select, $selecter.find("li.empty-item"), trigger);
				}
				return this;
			},
			setUrl : function(url, toload) {
				if (!this.is("select")) {
					return this;
				}
				toload = (false == toload ? false : true);
				this.data("url", url);
				toload && $module.reload.apply(this);
				return this;
			},
			refresh : function() {
				if (!this.is("select")) {
					return this;
				}
				this.each(function() {
					var $this = $(this), $value = $this.val() || $this.data("default") || "", $selectedCls = $this.data("selected-cls") || "selected", $selecter = $this.closest("div.selecter"), $li = $selecter.find("ul.select-list>li");
					if ($li.length > 0) {
						$li.removeClass($selectedCls);
						if ($value) {
							$module.select.call($this, $value);
						} else {
							clickItem($this, $selecter.find("ul.select-list>li.empty-item"));
						}
					}
				});
				return this;
			}
		};
	}($, BsplusCommon);
	window.BsplusPhotos = function($, $common) {// 相册
		var $module = null;
		return {
			name : "BsplusPhotos",
			initialize : function() {
				$module = this;
			},
			show : function(source) {
				if (!source)
					return;
				if ("string" == typeof source) {
					var $photos = $('<div class="bsplus-photos"><div class="photos-back"></div><span class="photos-close"></span><div class="photos-content"><img src="' + source + '"/></div></div>');
					$("body").append($photos);
					$photos.find("div.photos-content").BsplusDrag("init");
					setTimeout(function() {
						$photos.addClass("active");
					}, 10);
				} else if ($common.isArray(source)) {// 数组

				}
			},
			hide : function() {
				$("body").find("div.bsplus-photos").removeClass("active").fadeOut(500, function() {
					$(this).remove();
				});
			}
		}
	}(jQuery, BsplusCommon);
	window.BsplusForm = function($, $common) {// 表单处理
		var $module = null;
		return {
			name : "BsplusForm",
			initialize : function() {
				$module = this;
			},
			init : function(options) {// 保存数据
				options = options || {};
				var _forms = options.form || (!$common.isBlank(options.formId, true) ? $("#" + options.formId) : (options.modal ? options.modal.find("form") : $("body").find("form")));
				if (!_forms || _forms.length == 0) {
					return $module;
				}
				_forms.each(function(index) {
					var _form = $(this);
					var opts = $.extend(true, {}, options);// 接收改变的表单数据的参数名
					opts.form = _form;
					var _validator = _form.data("validator");
					if ($common.isBlank(_validator, true)) {
						_validator = _form.bootstrapValidator({
							excluded : _form.data("bv-excluded") || ':disabled',
							message : 'This value is not valid',
							feedbackIcons : {
								valid : 'fa fa-check vicon',
								invalid : 'fa fa-close vicon',
								validating : 'fa fa-refresh vicon'
							}
						});
					} else {
						if (opts.inIframe) {
							_validator = "opts.iframe.contentWindow." + _validator;
						}
						_form.listenPlusEvent({
							validator : _validator
						});
					}
					_form.listenPlusEvent({
						beforsubmit : _form.data("beforsubmit"),
						onsubmiterror : _form.data("onsubmiterror"),
						aftersubmit : _form.data("aftersubmit"),
						ondataload : _form.data("ondataload")
					});
					var _events = _form.data("events");
					try {
						if ("function" == typeof _events.validator) {
							_validator = _events.validator(_form);
						}
					} catch (e) {
						$common.log(e);
						_validator = _form.bootstrapValidator({
							excluded : ':disabled',
							message : 'This value is not valid',
							feedbackIcons : {
								valid : 'fa fa-check vicon',
								invalid : 'fa fa-close vicon',
								validating : 'fa fa-refresh vicon'
							}
						});
					}
					var _submitBtns = _form.find("button:submit");
					_validator.on('error.field.bv', function(e) {
						if (opts && opts.saveBtn) {
							if (_submitBtns.prop("disabled")) {
								opts.saveBtn.prop("disabled", true);
							} else {
								opts.saveBtn.prop("disabled", false);
							}
						}
					}).on('success.field.bv', function(e) {
						if (opts && opts.saveBtn) {
							if (_submitBtns.prop("disabled")) {
								opts.saveBtn.prop("disabled", true);
							} else {
								opts.saveBtn.prop("disabled", false);
							}
						}
					}).on('success.form.bv', function(e, data) {// 表单验证成功
						e.preventDefault();
						try {
							if ("function" === typeof _events.beforsubmit) {
								if (!_events.beforsubmit($.extend({}, opts, _form.data("args")["beforsubmit"]))) {
									_form.find("button:submit").prop("disabled", false);
									if (opts && opts.saveBtn) {
										opts.saveBtn.prop("disabled", false);
									}
									return this;
								}
							}
						} catch (e) {
							$common.log(e);
						}
						if ($common.isBlank(_form.attr('action'), true)) {
							$common.log("action" + $common.constants.canNotBeEmpty);
							return this;
						}
						try {
							if (opts && opts.saveBtn) {
								opts.saveBtn.prop("disabled", true);
							}
							var _target = _form.closest("div.modal-content");
							_target.loading({
								msg : $common.constants.submitting
							});
						} catch (e) {
							$common.log(e);
						}
						$.ajax({
							cache : true,
							type : "POST",
							url : _form.attr('action'),
							data : _form.serialize(),
							error : function(request) {
								$common.log(request);
								_target.unloading();// 隐藏加载提示框
								_form.triggerPlusEvent("onsubmiterror", request);
								bsplus.showToast({
									content : $common.constants.requestError,
									backColor : "#C9302C"
								});
							},
							success : function(data) {
								_target.unloading();// 隐藏加载提示框
								opts.data = data;
								if ($common.codeConfig[data.code].success) {
									if (opts.refrshGrid && 1 === opts.refrshWhen) {// 是否刷新表格
										if (!opts.refrshCurrent && opts.refrshGrid.data("pageVo")) {
											opts.refrshGrid.data("pageVo").currentPage = 1;// 刷新首页
										}
										opts.refrshGrid.reload();// 刷新表格
									}
									opts.closeOnSubmitSuccess && BsplusModal.destroy(opts);
								}
								if ("function" === typeof opts.onSubmitSuccess) {
									try {
										opts.onSubmitSuccess(opts);
									} catch (e) {
										$common.log(e);
									}
								} else {
									$common.dataHandle(opts);
								}
								_form.triggerPlusEvent("aftersubmit", opts);
							}
						});
					});
				});
				return $module;
			},
			showEdit : function(options) {
				try {
					var defaults = {
						paramName : "key"
					};
					var opts = $.extend(defaults, options), paramData = {}, _formId = options.formId, _form = opts.form;
					paramData[opts.paramName] = opts.primarykey;
					paramData = $.extend(paramData, opts.paramData);
					if (!$common.isBlank(_formId, true)) {
						_form = $("#" + _formId);
					}
					opts.form = _form;
					var _target = opts.form.closest("div.modal-content");
					_target.loading({
						msg : $common.constants.loading
					});
					$.ajax({
						cache : true,
						type : "POST",
						url : opts.dataUrl,
						data : paramData,
						error : function(request) {
							_target.unloading();// 隐藏加载提示框
							bsplus.showToast({
								content : $common.constants.requestError,
								backColor : "#C9302C"
							});
						},
						success : function(data) {
							_target.unloading();// 隐藏加载提示框
							var msg = "";
							if (data) {
								opts.data = $common.getValueByField(data, opts.objField || $common.dataField.objField);
								var code = $common.getValueByField(data, $common.dataField.codeField);
								setTimeout(function() {
									$module.setFormData(opts);// 设置数据到表单
								}, 10);
								if (!$common.codeConfig[code].success) {
									msg = $common.getValueByField(data, $common.dataField.msgField) || $common.constants.quiryFailure;
								}
							} else {
								msg = $common.constants.loadFailure;
							}
							if (!$common.isBlank(msg, true)) {
								bsplus.showToast({
									content : msg
								});
							}
						}
					});
				} catch (e) {
					$common.log(e);
				}
				return this;
			},
			setData : function(data) {
				$module.setFormData({
					form : this,
					data : data
				});
			},
			setFormData : function(opts) {
				try {
					var _form = opts.form, data = opts.data;
					if (_form && data) {
						var inputs = _form.find("input,textarea,select"), _this = null, fieldName = null, _thisvalue = null;
						inputs.each(function(index) {
							_this = $(this);
							fieldName = this.name;
							if (!$common.isBlank(fieldName, true)) {
								_thisvalue = $common.getValueByField(data, fieldName);
								if (!_thisvalue && _thisvalue != "0" && _thisvalue != 0) {
									_thisvalue = "";
								}
								_this.data("oldValue", _thisvalue);
								if ("radio" == this.type || "checkbox" == this.type) {
									if (this.value == _thisvalue) {
										_this.check(true);
									} else {
										_this.check(false);
									}
								} else if (_this.hasClass("bsplus-select")) {
									_this.select(_thisvalue);
								} else {
									this.value = _thisvalue;
								}
							}
						});
						_form.triggerPlusEvent("ondataload", opts);
					}
					if ("function" === opts.ondataload) {
						opts.ondataload(opts);// 数据加载完成事件
					}
				} catch (e) {
					$common.log(e);
				}
				return this;
			},
			validForm : function(form) {// 验证表单并返回验证结果，Boolean：true验证通过，false
				// 验证未通过
				if (!form) {
					return this;
				}
				var _validator = form.data('bootstrapValidator');
				if (_validator) {
					_validator.validate();
					return _validator.isValid();
				}
				return false;
			}
		}
	}($, BsplusCommon);
	window.BsplusModal = function($, $common) {// 模态框
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
			backdrop : "static",// backdrop : false:不显示遮罩层，'static':空白处不关闭.
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
			var $cw, $ch;
			if (window.innerWidth)
				$cw = window.innerWidth;
			else if ((document.body) && (document.body.clientWidth))
				$cw = document.body.clientWidth;
			// 获取窗口高度
			if (window.innerHeight)
				$ch = window.innerHeight;
			else if ((document.body) && (document.body.clientHeight))
				$ch = document.body.clientHeight;
			var $sw = $common.getScrollBarWidth(), $w, $h, $ps, $p1, $p2, $p = {
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
					if ("number" === typeof opts.height) {
						$h = opts.height;
						$h = $h < $ch ? $h : $ch;
					} else if (opts.height.indexOf("%") > 0) {
						$h = parseInt(opts.height) / 100;
						$h = $ch * ($h > 1 ? 1 : $h);
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
	}($, BsplusCommon);
	window.BsplusGrid = function($, $common) {
		var $module = null, $checker = "object" == typeof BsplusChecker ? BsplusChecker : null, $ct = $common.constants, $path = $common.getPath(), $default = $common.gridConfig, $gridOpts = {
			agcl : function(grid, attributes) {// 事件托管
				var $event, $element, $time, $selectedCls = attributes.selectedCls, $events = grid.data("events"), array = [ 'search', 'add', 'edit', 'delete', 'config' ], showNum = grid.data("children-show-item");
				grid.unbind("click").click(function(e) {
					if (!grid.data("inited")) {// 如果尚未初始化，则先初始化
						$module.init.apply(grid);
						return $module;
					}
					$event = e || window.event, $element = $($event.target || $event.srcElement);
					if (grid.parents("div.bsplus-grid").length > 0) {
						$event.stopPropagation();
					}
					if ($element.length > 0) {
						switch ($element[0].tagName.toLowerCase()) {
						case "input":
							if ("checkbox" === $element[0].type || "radio" === $element[0].type) {
								if ($element.hasClass("checkAll")) {
									var $rows = attributes.bodyContent.find("div.table-row");
									if ($element.prop("checked")) {
										$checker.check.call($rows.addClass($selectedCls).data("selected", true).find("div.table-cell[data-type='select'] input:checkbox"), true);
									} else {
										$checker.check.call($rows.removeClass($selectedCls).data("selected", false).find("div.table-cell[data-type='select'] input:checkbox"), false);
									}
									grid.triggerPlusEvent("onselectallclick", {
										checked : $element.prop("checked"),
										data : grid.data("dataList"),
										clickTarget : $element
									});
									$module.getSelectedNum.call(grid);
									break;
								} else {
									var $cell = $element.closest("div.table-cell");
									if ("select" === $cell.data("type")) {
										$module.selectRows.call(grid, [ $cell.closest("div.table-row").data("index") ], $element.prop("checked"));
									}
								}
							}
						case "i":
							var $pth = $element.closest("div.table-sort");
							if ($pth.length > 0) {
								$gridOpts.onSort(grid, $pth);
								break;
							}
							var $button = $element.closest("button[data-action]");
							if ($button.length > 0) {
								var $action = $button.data("action");
								if (!$common.isBlank($action, true)) {
									if ($button.hasClass("pager-btn")) {
										e.preventDefault();
										$gridOpts.gotoPage(grid, attributes, $action);// 分页按钮点击
									} else {
										e.preventDefault();
										if (array.indexOf($action) < 0) {
											return;
										}
										$gridOpts.doAction(grid, $button, $action);// 功能按钮点击
									}
								}
								break;
							}
						case "a":
							var $a = null;
							if ($element.hasClass("detail-btn")) {
								$a = $element;
							} else {
								$a = $element.closest("a.detail-btn");
							}
							$a && $a.length > 0 && $gridOpts.clickDbtn(grid, $a);
						case "img":
							var $img = $element;
							if ($img.is("img")) {
								var $cell = $img.closest("div.table-cell");
								if ($cell.length > 0 && "img" == $cell.data("type")) {
									if (2 == $cell.data("view-mode")) {
										window.open($cell.data("link"));
									} else {
										bsplus.showPhotos($cell.data("view-url") || $img.attr("src"));
									}
								}
							}
							break;
						case "label":
							var $cell = $element.closest("span.bsplus-checker");
							if ($cell && $cell.length > 0) {
								break;
							}
						case "span":
							if (attributes.isTree) {
								var $pa = $element.closest("a.tree-btn");
								if ($pa.length > 0) {// 展开关闭树形条目
									var $row = $pa.closest("div.table-row");
									$row = attributes.bodyContent.find("div.scroll-area div.table-row[data-index='" + $row.data("index") + "']");
									$gridOpts.hideOrShowChild(grid, $row, attributes);
									grid.triggerPlusEvent("ontreecontrolclick", {
										selected : $row.data("selected"),
										record : $row.data("record"),
										row : $row,
										clickTarget : $element,
										status : $row.data("closed") ? "close" : "open"
									});
									break;
								}
							}
							var $button = $element.closest("button[data-action]");
							if ($button.length > 0) {
								var $action = $button.data("action");
								if (!$common.isBlank($action, true)) {
									if ($button.hasClass("pager-btn")) {
										e.preventDefault();
										$gridOpts.gotoPage(grid, attributes, $action);// 分页按钮点击
									} else {
										e.preventDefault();
										if (array.indexOf($action) < 0) {
											return;
										}
										$gridOpts.doAction(grid, $button, $action);// 功能按钮点击
									}
								}
								break;
							}
						case "button":
							var $button = $element;
							if (!$button.is("button")) {
								$button = $element.closest("button");
							}
							$button.blur();// 失去焦点
							var $action = $button.data("action");
							if (!$common.isBlank($action, true)) {
								if ($button.hasClass("pager-btn")) {
									e.preventDefault();
									$gridOpts.gotoPage(grid, attributes, $action);// 分页按钮点击
								} else {
									e.preventDefault();
									$gridOpts.doAction(grid, $button, $action);// 功能按钮点击
								}
								break;
							} else if ($button.hasClass("switch-btn")) {// showNum
								var $row = $button.closest("div.table-row");
								var $hideCell = $row.find("div.table-cell-children>div.table-cell-child.bsplus-hidden");
								if ($hideCell.length > 0) {
									$row.find("div.table-cell-children").each(function() {
										var $children = $(this);
										var $hide = $children.find("div.table-cell-child.bsplus-hidden");
										$hide.each(function(index) {
											if (index >= showNum) {
												return false;
											}
											$(this).removeClass("bsplus-hidden");
										});
										if ($hide.length <= showNum) {
											$children.find("button.switch-btn").html("<span class='fa fa-chevron-up'>&nbsp;</span>收起");
										}
									});
								} else {
									$row.find("div.table-cell-children").each(function() {
										var $children = $(this);
										$children.find("div.table-cell-child").each(function(index) {
											if (index < showNum) {
												return true;
											}
											if (!$(this).hasClass("switch-btn-box")) {
												$(this).addClass("bsplus-hidden");
											}
										});
										$children.find("button.switch-btn").html("<span class='fa fa-chevron-down'>&nbsp;</span>展开");
									});
								}
								break;
							}
						case "li":
						case "ul":
						case "div":
							var $cell = null, $headCell = null, $row = null, $ri;
							if ($element.hasClass("table-cell")) {
								$cell = $element;
							} else {
								$cell = $element.closest("div.table-cell");
							}
							if ($cell && $cell.length > 0) {
								$row = $cell.closest("div.table-row");
								$ri = $row.data("index");
								if ($row.closest("div.scroll-area").length == 0) {
									$row = attributes.bodyContent.find("div.scroll-area div.table-row[data-index='" + $ri + "']");
								}
								clearTimeout($time);
								$time = setTimeout(function() {// 单击事件延时200ms触发
									if (attributes.selectOnRowClick) {// 行点击选中
										$module.selectRows.call(grid, [ $ri ], !$row.data("selected"));
									}
									grid.triggerPlusEvent("oncellclick", {
										selected : $row.data("selected") || false,
										row : $row,
										cell : $cell,
										record : $row.data("record"),
										clickTarget : $element
									});
									grid.triggerPlusEvent("onrowclick", {
										selected : $row.data("selected") || false,
										record : $row.data("record"),
										row : $row,
										clickTarget : $element
									});
								}, attributes.clicktime);
							} else if ($element.hasClass("table-head-cell")) {
								$headCell = $element;
							} else {
								$headCell = $element.closest("div.table-head-cell");
							}
							$headCell && $headCell.hasClass("table-sort") && $gridOpts.onSort(grid, $headCell);
						default:
							break;
						}
					}
				});
				grid.unbind("dblclick").dblclick(function(e) {
					if (!grid.data("inited")) {// 如果尚未初始化，则先初始化
						$module.init.apply(grid);
						return $module;
					}
					clearTimeout($time);
					$event = e || window.event, $element = $($event.target || $event.srcElement);
					if ($element.length > 0) {
						switch ($element[0].tagName.toLowerCase()) {
						case "input":
						case "div":
							var $cell = null, $row = null;
							if ($element.hasClass("table-cell")) {
								$cell = $element;
							} else {
								$cell = $element.closest("div.table-cell");
							}
							if ($cell && $cell.length > 0) {
								$row = $cell.closest("div.table-row");
								grid.triggerPlusEvent("oncelldblclick", {
									row : $row,
									cell : $cell,
									record : $row.data("record"),
									clickTarget : $element
								});
								grid.triggerPlusEvent("onrowdblclick", {
									row : $row,
									record : $row.data("record"),
									clickTarget : $element
								});
							}
						default:
							break;
						}
					}
				});
				grid.keydown(function(e) {// 回车查询
					var ev = document.all ? window.event : e, $target = $(ev.target);
					if (ev.keyCode == 13) {
						if ($target.is("input")) {
							var $query = $target.closest("div.grid-query");
							if ($query.length > 0) {
								e.preventDefault();
								var page = grid.data("pageVo") || {};
								page.currentPage = 1;
								grid.data("pageVo", page);
								grid.reload();
							}
						}
					}
				});
				if (attributes.showPager) {
					var $pages = attributes.pages, $currentPage = 1;
					if ($pages && $pages.length > 0) {
						$pages.find("input[current-page]").each(function() {
							var $input = $(this);
							$input.unbind("keyup").keyup(function() {
								var $pageVo = grid.data("pageVo"), $totalPage = $pageVo.totalPage;
								$currentPage = $input.val();
								if (isNaN($currentPage) || parseInt($currentPage) < 1) {
									$input.val(1);
								} else if (parseInt($currentPage) > $totalPage) {
									$input.val($totalPage);
								} else {
									var re = /^[1-9]+[0-9]*]*$/;
									if (!re.test($currentPage)) {
										$input.val(1);
									}
								}
							}).keydown(function(e) {// 回车跳转页面
								if (e.keyCode == 13) {
									$currentPage = $input.val();
									if (!$common.isBlank($currentPage, true)) {
										$currentPage = parseInt($currentPage);
									}
									grid.data("pageVo").currentPage = $currentPage;
									$module.search.call(grid);
								}
							}).change(function(e) {// 只改变时跳转页面
								$currentPage = $input.val();
								if (!$common.isBlank($currentPage, true)) {
									$currentPage = parseInt($currentPage);
								}
								grid.data("pageVo").currentPage = $currentPage;
								$module.search.call(grid);
							});
						});
					}
				}
				return $module;
			},
			setDetailsHeight : function(grid, attributes) {
				var $drs = attributes.bodyContent.find("div.scroll-area div.detail-row:visible");
				if ($drs.length > 0) {
					$drs.each(function() {
						var $this = $(this), $i = $this.data("index");
						attributes.bodyContent.find("div.detail-row[data-index='" + $i + "']").height($this.height() + 6);
					});
				}
			},
			clickDbtn : function(grid, as) {
				if (!grid || !as) {
					return $module;
				}
				var attributes = grid.data("attributes") || {}, $openCls = grid.data("details-open-icon") || $default["details-open-icon"], $closeCls = grid.data("details-close-icon") || $default["details-close-icon"];
				as.each(function() {
					var $this = $(this), $row = $this.closest("div.table-row"), $ci = $this.closest("div.table-row").data("index"), $detailRows = $row.next("div.detail-row[data-index='" + $ci + "']");
					if ($row.data("detail-show")) {
						$detailRows.hide();
						$row.data("detail-show", false);
						$this.children("i").attr("class", $closeCls);
						try {
							grid.triggerPlusEvent("ondetailclose", {
								detailRow : $detailRows,
								record : $row.data("record"),
								id : $row.data("record")[grid.data("id-field")] || "",
								row : $row
							});
						} catch (e) {
							$common.log(e);
						}
					} else {
						if (grid.data("hide-otherdetails-when-show") || $default["hide-otherdetails-when-show"]) {
							var $otherDetailRows = attributes.bodyContent.find("div.detail-row:visible[detail-index!='" + $ci + "']");
							attributes.bodyContent.find("div.table-cell[data-type='detail'] i").attr("class", $closeCls);
							if ($otherDetailRows.length > 0) {
								$otherDetailRows.each(function() {
									var $t = $(this), $index = $t.data("index"), $odrs = attributes.bodyContent.find("div.detail-row[data-index='" + $index + "']"), $or = attributes.bodyContent.find("div.scroll-area div.table-row[data-index='" + $index + "']");
									$odrs.hide();
									$or.data("detail-show", false);
									attributes.bodyContent.find("div.table-cell[data-type='detail'][data-index='" + $index + "'] i").attr("class", $closeCls);
									try {
										grid.triggerPlusEvent("ondetailclose", {
											detailRow : $odrs,
											record : $or.data("record"),
											id : $or.data("record")[grid.data("id-field")] || "",
											row : $or
										});
									} catch (e) {
										$common.log(e);
									}
								});
							}
						}
						$detailRows.show();
						$row.data("detail-show", true);
						$this.children("i").attr("class", $openCls);
						try {
							grid.triggerPlusEvent("ondetailopen", {
								detailRow : $detailRows,
								record : $row.data("record"),
								id : $row.data("record")[grid.data("id-field")] || "",
								row : $row
							});
						} catch (e) {
							$common.log(e);
						}
						$gridOpts.setDetailsHeight(grid, attributes);// 设置高度
					}
				});
			},
			hoverGrid : function(grid) {
				if (!grid) {
					return $module;
				}
				var attributes = grid.data("attributes") || {}, rcls = grid.data("row-hover-cls") || "hover-cls", ccls = grid.data("cell-hover-cls");
				grid.unbind("mouseover").mouseover(function(e) {
					var $event = e || window.event, $element = $($event.target || $event.srcElement), $cell = null;
					if ($element.length > 0) {
						if ($element.hasClass("table-cell")) {
							$cell = $element;
						} else {
							$cell = $element.closest("div.table-cell");
						}
						ccls && attributes.bodyContent.find("div.table-cell." + ccls).removeClass(ccls) && attributes.bodyContent.find("div.table-cell[data-index='" + $cell.data("index") + "']").addClass(ccls);
						rcls && attributes.bodyContent.find("div.table-row." + rcls).removeClass(rcls) && attributes.bodyContent.find("div.table-row[data-index='" + $cell.parent("div.table-row").data("index") + "']").addClass(rcls);
					}
				});
				return $module;
			},
			toTreeData : function(list, childField, parentField, chindrenName) {// 将数组数据转化为树形关系的数组数据
				try {
					var r = [], hash = {}, len = list.length, aVal = null, hashVP = null;
					for (var i = 0; i < len; i++) {
						delete list[i][chindrenName];
						hash[list[i][childField]] = list[i];
					}
					for (var i = 0; i < len; i++) {
						aVal = list[i], hashVP = hash[aVal[parentField]];
						if (hashVP) {
							!hashVP[chindrenName] && (hashVP[chindrenName] = []);
							hashVP[chindrenName].push(aVal);
						} else {
							aVal.firstNode = true;
							r.push(aVal);
						}
					}
					return r;
				} catch (e) {
					$common.log(e);
				}
				return null;
			},
			loadData : function(grid, attributes, options) {// 加载数据
				var $url = grid.data("url") || $default["url"], $events = grid.data("events"), $dataList = null, $pageVo = null, contentType = grid.data("search-content-type") || $default["search-content-type"];// 数据加载地址
				if (!$common.isBlank(grid.data("list"), true)) {
					try {
						$dataList = eval(grid.data("list"));
						$gridOpts.setData(grid, $dataList);
						return $module;
					} catch (e) {
						$common.log(e);
					}
				} else if ($common.isBlank($url, true)) {
					$gridOpts.setData(grid, null);
					attributes.bodyDiv.unloading();// 隐藏加载提示框
					return $module;
				}
				var $getRequest = $url.endWith(".json") || $url.endWith(".text");
				$.ajax({
					cache : true,
					async : true,
					url : $url,
					timeout : grid.data("timeout") || $default["timeout"] || 60000,
					type : $getRequest ? "GET" : "POST",
					contentType : contentType,
					data : contentType.indexOf("application/json") >= 0 ? JSON.stringify(options.condition) : options.condition,
					error : function(r) {
						attributes.bodyDiv.unloading();// 隐藏加载提示框
						$common.log(r);
						// 数据加载异常事件
						if ($events && "function" === typeof $events.onloaderror) {
							grid.triggerPlusEvent("onloaderror", {
								status : r.status,
								statusText : r.statusText,
								message : $ct.requestError,
								error : r
							});
						} else {
							if ("timeout" == r.statusText) {
								bsplus.showToast({
									content : $ct.requestTimedOut,
									backColor : "#C9302C"
								});
							} else {
								bsplus.showToast({
									content : $ct.requestError,
									backColor : "#C9302C"
								});
							}
						}
						return $module;
					},
					success : function(data) {
						grid.data("responseData", data);
						grid.triggerPlusEvent("onloadsuccess", {
							data : data
						});
						if (!data) {
							bsplus.showToast({
								content : $ct.quiryFailure,
								backColor : "#C9302C"
							});
						} else if ("string" === typeof data) {
							if (data.indexOf("action-flag=login") >= 0) {
								window.top.location.href = bsplus.path + "/mgr/user/login";
							} else {
								$dataList = eval(data);// 将json数组转化为数组对象
							}
						} else if ("object" === typeof data) {
							if ("error" == data[grid.data("code-field") || $common.dataField.codeField]) {
								bsplus.showToast({
									content : data[grid.data("msg-field") || $common.dataField.msgField],
									backColor : "#C9302C"
								});
							}
							if ($getRequest) {
								$dataList = data;
							} else {
								$dataList = $common.getValueByField(data, grid.data("list-field") || $default["list-field"] || $common.dataField.listField);
								$pageVo = $common.getValueByField(data, grid.data("page-field") || $default["page-field"] || $common.dataField.pageField);
							}
						} else {
							bsplus.showToast({
								content : $common.getValueByField(data, $common.dataField.msgField) || $ct.unknownError
							});
						}
						if ($getRequest) {
							grid.data("localDatas", $dataList);
							$gridOpts.showLocalData(grid, attributes);// 显示当前js对象中的数据
						} else {
							grid.data("dataList", $dataList).data("pageVo", $pageVo);
							if (attributes.isTree) {// 表格类型，默认为datagrid(普通表格),treegrid（树形表格）
								$gridOpts.showTreeData(grid, attributes);// 显示树形数据
							} else {
								$gridOpts.showData(grid, attributes);// 显示数据
							}
						}
					}
				});
				return $module;
			},
			setData : function(grid, datas) {// 设置表格数据
				try {
					if (!grid.data("inited")) {// 如果尚未初始化，则先初始化
						$module.init.apply(grid);
					}
					grid.data("localDatas", datas);
					$gridOpts.showLocalData(grid, grid.data("attributes") || {});
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			showLocalData : function(grid, attributes) {
				var $pageVo = grid.data("pageVo"), $currentData = null, $dataList = attributes.isTree ? grid.data("localDatas").clone() : grid.data("localDatas");
				if ($dataList != null && $dataList.length > 0) {
					if (attributes.showPager) {
						if ("boolean" === typeof grid.data("sort-all") ? grid.data("sort-all") : $default["sort-all"]) {// 数据源排序
							if (!$common.isBlank($pageVo.sortField, true) && !$common.isBlank($pageVo.sortOrder, true)) {
								if ("desc" == $pageVo.sortOrder.toLowerCase()) {
									$dataList.sort(function(a, b) { // 根据列数排序
										return b[$pageVo.sortField] > a[$pageVo.sortField] ? 1 : -1;
									});
								} else {
									$dataList.sort(function(a, b) { // 根据列数排序
										return a[$pageVo.sortField] > b[$pageVo.sortField] ? 1 : -1;
									});
								}
							}
							$currentData = $dataList.slice(($pageVo.currentPage - 1) * $pageVo.pageSize, $pageVo.pageSize * $pageVo.currentPage);
						} else {
							$currentData = $dataList.slice(($pageVo.currentPage - 1) * $pageVo.pageSize, $pageVo.pageSize * $pageVo.currentPage);
							if (!$common.isBlank($pageVo.sortField, true) && !$common.isBlank($pageVo.sortOrder, true)) {
								if ("desc" == $pageVo.sortOrder.toLowerCase()) {
									$currentData.sort(function(a, b) { // 根据列数排序
										return b[$pageVo.sortField] > a[$pageVo.sortField] ? 1 : -1;
									});
								} else {
									$currentData.sort(function(a, b) { // 根据列数排序
										return a[$pageVo.sortField] > b[$pageVo.sortField] ? 1 : -1;
									});
								}
							}
						}
						grid.data("dataList", $currentData);
						$pageVo.totalRecord = $dataList.length;
						$pageVo.totalPage = Math.ceil($dataList.length / $pageVo.pageSize);
					} else {
						if (!$common.isBlank($pageVo.sortField, true) && !$common.isBlank($pageVo.sortOrder, true)) {
							if ("desc" == $pageVo.sortOrder.toLowerCase()) {
								$dataList.sort(function(a, b) { // 根据列数排序
									return b[$pageVo.sortField] > a[$pageVo.sortField] ? 1 : -1;
								});
							} else {
								$dataList.sort(function(a, b) { // 根据列数排序
									return a[$pageVo.sortField] > b[$pageVo.sortField] ? 1 : -1;
								});
							}
						}
						grid.data("dataList", $dataList);
					}
				} else {
					grid.data("dataList", null);
					$pageVo.totalRecord = 0;
					$pageVo.totalPage = 0;
					$pageVo.currentPage = 1;
				}
				try {
					if (attributes.isTree) {// 表格类型，默认为datagrid(普通表格),treegrid（树形表格）
						$gridOpts.showTreeData(grid, attributes);// 显示树形数据
					} else {
						$gridOpts.showData(grid, attributes);// 显示数据
					}
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			uopdateSortIcon : function(grid) {
				var pageVo = grid.data("pageVo"), attributes = grid.data("attributes") || {}, $hs = null, $fs = null;
				if (!pageVo || !attributes) {
					return;
				}
				if (pageVo.sortField) {
					attributes.tableHead && attributes.tableHead.length > 0 && (attributes.tableHead.find("div.table-head-cell.table-sort").removeClass("sorting").find("i").attr("class", "fa fa-sort")) && ($hs = attributes.tableHead.find("div.table-head-cell[data-sort-field='" + (pageVo.oldSortField || grid.data("sort-field")) + "']"));
					attributes.tableFoot && attributes.tableFoot.length > 0 && (attributes.tableFoot.find("div.table-head-cell.table-sort").removeClass("sorting").find("i").attr("class", "fa fa-sort")) && ($fs = attributes.tableFoot.find("div.table-head-cell[data-sort-field='" + (pageVo.oldSortField || grid.data("sort-field")) + "']"))
					if ("desc" == pageVo.sortOrder.toLowerCase()) {
						$hs && $hs.length > 0 && $hs.addClass("sorting") && $hs.find("i").attr("class", "fa fa-sort-down");
						$fs && $fs.length > 0 && $fs.addClass("sorting") && $fs.find("i").attr("class", "fa fa-sort-down");
					} else if ("asc" == pageVo.sortOrder.toLowerCase()) {
						$hs && $hs.length > 0 && $hs.addClass("sorting") && $hs.find("i").attr("class", "fa fa-sort-up");
						$fs && $fs.length > 0 && $fs.addClass("sorting") && $fs.find("i").attr("class", "fa fa-sort-up");
					}
				}
			},
			renderCellContent : function(ch, o) {
				var hasRender = false, value = "", renderMap = ch.data("render-map");
				if (!o) {
					return "";
				}
				value = o.value;
				if (o.cellEvent && "function" === typeof o.cellEvent.renderer) {
					try {
						value = ch.triggerPlusEvent("renderer", {
							responseData : o.responseData,
							value : value,
							record : o.record,
							row : o.row,
							cell : o.cell,
							cellContent : o.cellContent
						});
						hasRender = true;
					} catch (e) {
						$common.log(e);
					}
				}
				try {
					if (!$common.isBlank(o.dateFormat, true) && !$common.isBlank(value, true)) {
						if ("string" === typeof value) {
							value = new Date(Date.parse(value.replace(/-/g, "/"))).format(o.dateFormat);
						} else if ("number" === typeof value) {
							value = new Date(value).format(o.dateFormat);
						} else if ("object" === typeof value) {
							value = value.format(o.dateFormat);
						}
					}
				} catch (e) {
					$common.log(e);
				}
				value = $common.getValue(value);
				if (renderMap) {
					if ("string" === typeof renderMap) {
						eval("renderMap=" + renderMap)
					}
					value = renderMap[value] || value;
				}
				if (o.columnType) {
					o.cell.attr("data-type", o.columnType);
					if (o.cell.attr("class").indexOf("min-") < 0)
						o.cell.addClass("img" == o.columnType ? "min-40" : "min-40");
					if (o.cell.attr("class").indexOf("max-") < 0)
						o.cell.addClass("img" == o.columnType ? "max-80" : "max-50");
					if ("index" == o.columnType) {
						o.cellContent.html(o.index + 1);
					} else if ("select" == o.columnType) {
						if (o.multiSelect) {// 默认多选
							o.cellContent.html("<input type='checkbox' class='subcheck' value='" + (o.record[o.idField] || "") + "'/>");
						} else {// 单选
							o.cellContent.html("<input type='radio' class='singlecheck' value='" + (o.record[o.idField] || "") + "'/>");
						}
					} else if ("img" == o.columnType) {
						var $defaultUrl = ch.data("default-img") || $default["default-img"], $imgUrl = value || $defaultUrl;
						if ("string" == typeof $imgUrl) {
							o.cellContent.html('<img class="grid-cell-img" src="' + $imgUrl + '" style="width:' + (ch.data("img-width") || 120) + ';height:' + (ch.data("img-height") || 100) + ';" alt="' + (ch.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/>');
						} else if ($common.isArray($imgUrl)) {// 数组
							o.cellContent.data("imgList", value);
							$imgUrl = value[0];
							if (value.length > 0) {
								o.cellContent.html('<img class="grid-cell-img" src="' + $imgUrl + '" style="width:' + (ch.data("img-width") || 120) + ';height:' + (ch.data("img-height") || 100) + ';" alt="' + (ch.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/>');
							}
						}
						o.cell.data("view-mode", ch.data("view-mode") || 1).data("link", ch.data("link-url") || $imgUrl).data("view-url", ch.data("view-url"));
					} else if ("detail" == o.columnType) {
						o.cell.css("fontSize", "16px");
						var $openBtn = $("<a href='javascript:void(0)' class='detail-btn'><i class='" + (o.showDetail ? o.doi : o.dci) + "'></i></a>");
						o.cellContent.append($openBtn);
					} else if ("link" == o.columnType) {
						var linkUrl, linkField = ch.data("link-field"), linkTarget = ch.data("link-target") || "_blank";
						linkField && (linkUrl = $common.getValueByField(o.record, linkField));
						!linkUrl && value.isUrl() && (linkUrl = value);
						!linkUrl && (linkUrl = ch.data("link-default-url") || "");
						if (linkUrl) {
							o.cellContent.append("<a href='" + linkUrl + "' target='" + linkTarget + "'>" + value + "</a>");
						} else {
							o.cellContent.append("<a href='javascript:void(0)'>" + value + "</a>");
						}
					}
				} else {
					value = ($common.getValue(ch.data("before")) || "") + (value || $common.getValue(ch.data("default")) || "") + ($common.getValue(ch.data("after")) || "");
					o.cellContent.append(value);
				}
				if (o.cellEvent && "function" === typeof o.cellEvent.ondrew) {
					ch.triggerPlusEvent("ondrew", {
						responseData : o.responseData,
						value : value,
						record : o.record,
						row : o.row,
						cell : o.cell,
						cellContent : o.cellContent
					});
				}
				if (o.showTips) {
					if (ch.data("tips-msg")) {
						o.cellContent.attr("title", ch.data("tips-msg")).attr("data-toggle", "tooltip").attr("data-placement", ch.data("placement") || "top");
					} else if (!hasRender) {
						o.cellContent.attr("title", value).attr("data-toggle", "tooltip").attr("data-placement", ch.data("placement") || "top");
					}
				}
				return o;
			},
			showData : function(grid, attributes) {// 显示数据
				var $ths = attributes.headCells, $dataList = grid.data("dataList"), childNum = grid.data("children-show-item"), $rows = null, $scrollRows = attributes.tableBody.find("div.scroll-area>div.table-rows"), $fixedLeft = attributes.tableBody.find("div.fixed-left>div.table-rows"), $fixedRight = attributes.tableBody.find("div.fixed-right>div.table-rows"), $l = $fixedLeft.length > 0, $r = $fixedRight.length > 0;
				if ($dataList && $dataList.length > 0) {
					if (!$ths || $ths.length == 0) {
						return $module;
					}
					$rows = grid.data("rows");
					if ($rows) {
						$rows.removeData();// 清除数据缓存
					}
					var $ro, rcl = 1, $rc, $fixed = null, $dhtml = "", $details = "", $children = null, $cfield = grid.data("children-field"), $idField = grid.data("id-field") || $default["id-field"], $rowCls = grid.data("row-cls") || $default["row-cls"], $record = null, $events = grid.data("events"), $pageVo = grid.data("pageVo"), $detailsRow = null, $showDetail = grid.data("show-details-onload") || $default["show-details-onload"], $detailsCls = grid.data("details-cls") || $default["details-cls"], $detailsCell = null, $doi = grid.data("details-open-icon") || $default["details-open-icon"], $dci = grid.data("details-close-icon") || $default["details-close-icon"], $loadingCls = grid.data("details-loading-icon") || $default["details-loading-icon"], $row = null, $rowl = null, $rowr = null, $cell = null, $cellContent = null, $responseData = grid.data("responseData");
					$scrollRows.length > 0 && $scrollRows.empty();
					$fixedLeft.length > 0 && $fixedLeft.empty();
					$fixedRight.length > 0 && $fixedRight.empty();
					if ("string" === typeof $rowCls) {
						$rowCls = $rowCls.split(",");
					}
					rcl = $rowCls.length;
					for (var i = 0, length = $dataList.length; i < length; i++) {
						$record = $dataList[i];
						if (!$record) {
							continue;
						}
						if ($cfield) {
							$children = $common.getValueByField($record, $cfield);
						}
						$rc = $rowCls[i % rcl];
						$row = $("<div class='table-row " + $rc + "' data-index=" + i + "></div>");
						if ($l) {
							$rowl = $("<div class='table-row " + $rc + "' data-index=" + i + "></div>");
							$fixedLeft.append($rowl);
						}
						if ($r) {
							$rowr = $("<div class='table-row " + $rc + "' data-index=" + i + "></div>");
							$fixedRight.append($rowr);
						}
						$ro = {
							index : i,
							responseData : $responseData,
							record : $record,
							row : $row,
							multiSelect : attributes.multiSelect,
							idField : $idField,
							showDetail : $showDetail,
							doi : $doi,
							dci : $dci
						};
						$ths.each(function(index, e) {
							var $this = $(this), $showTips = $this.data("show-tips"), $width = $this.data("width"), $hasRender = false, $content = "", $field = $this.data("field"), $dateFormat = $this.data("date-format"), $columnType = $this.data("type"), $cellEvent = $this.data("events"), $value = "";
							$cell = $("<div class='table-cell " + ($this.data("content-cls") || "") + "' data-index=" + index + "></div>");
							false == $this.data("visible") && $cell.addClass("bsplus-hidden");
							$width && $cell.css("width", $width);
							if ($children && $children.length > 0 && $this.data("show-children")) {
								var $tcc = $("<div class='table-cell-children'></div>");
								for (var j = 0, b = $children.length; j < b; j++) {
									var $cc = $("<div class='table-cell-child' data-index=" + j + "></div>");
									if (childNum && j >= childNum) {
										$cc.addClass("bsplus-hidden");
									}
									$cellContent = $("<div class='cell-content'></div>");
									$value = $common.getValueByField($children[j], $field);
									var ro = $gridOpts.renderCellContent($this, $.extend({}, {
										value : $value,
										cell : $cell,
										cellContent : $cellContent,
										cellEvent : $cellEvent,
										dateFormat : $dateFormat,
										columnType : $columnType,
										field : $field,
										showTips : $showTips
									}, $ro));
									$cellContent = ro.cellContent;
									$cell = ro.cell;
									$cc.append($cellContent);
									$tcc.append($cc);
								}
								if (childNum && $children.length > childNum) {
									$tcc.append("<div class='table-cell-child switch-btn-box'><button class='btn btn-sm switch-btn'><span class='fa fa-chevron-down'>&nbsp;</span>展开</button></div>");
								}
								$cell.addClass($this.data("common-cls") || "").append($tcc);
							} else {
								$cellContent = $("<div class='cell-content'></div>");
								$value = $common.getValueByField($record, $field);
								var ro = $gridOpts.renderCellContent($this, $.extend({}, {
									value : $value,
									cell : $cell,
									cellContent : $cellContent,
									cellEvent : $cellEvent,
									dateFormat : $dateFormat,
									columnType : $columnType,
									field : $field,
									showTips : $showTips
								}, $ro));
								$cellContent = ro.cellContent;
								$cell = ro.cell;
								$cell.addClass($this.data("common-cls") || "").append($cellContent);
							}
							$fixed = $this.data("fixed");
							if ($fixed) {
								if ("left" == $fixed) {
									$rowl.append($cell);
								} else if ("right" == $fixed) {
									$rowr.append($cell);
								} else {
									$row.append($cell);
								}
							} else {
								$row.append($cell);
							}
						});
						$scrollRows.append($row.data("record", $record));
						if (attributes.showDetails) {
							$details = "<i class='" + $loadingCls + "'></i>" + $ct.loading;
							if ($showDetail) {
								$dhtml = "<div data-index='" + i + "' class='detail-row " + ($detailsCls || "") + "'></div>";
								$row.data("detail-show", true);
							} else {
								$dhtml = "<div data-index='" + i + "' class='detail-row " + ($detailsCls || "") + "' style='display: none;'></div>";
								$row.data("detail-show", false);
							}
							$detailsRow = $($dhtml);
							if ($events && "function" === typeof $events.ondrawdetails) {
								try {
									$details = grid.triggerPlusEvent("ondrawdetails", {
										record : $record,
										row : $row,
										detailRow : $detailsRow
									});
									if ($common.isBlank($details, true)) {
										$details = "";
									}
								} catch (e) {
									$details = "<i class='" + $loadingCls + "'></i>" + $ct.loading;
									$common.log(e);
								}
							}
							$detailsRow.html($details);
							$scrollRows.length > 0 && $scrollRows.append($detailsRow);
							$fixedLeft.length > 0 && $fixedLeft.append($dhtml);
							$fixedRight.length > 0 && $fixedRight.append($dhtml);
						}
						if ($rowl || $rowr) {
							var maxh = $gridOpts.getMaxHeight($row, $rowl, $rowr);
							$row.css("height", maxh + 2);
							$rowl && $rowl.css("height", maxh + 2);
							$rowr && $rowr.css("height", maxh + 2);
						}
					}
					if (attributes.showDetails && $showDetail) {
						$gridOpts.setDetailsHeight(grid, attributes);
					}
					if (attributes.showSummary && $events && "function" == typeof $events.ondrawsummary) {
						var $summary = grid.triggerPlusEvent("ondrawsummary", {
							pageVo : $pageVo,
							data : $dataList
						}) || "";
						grid.find("div.grid-summary").html($summary);
					}
				} else {
					if (attributes.showEmptyText) {
						$row = $("<div class='table-row'><div class='table-cell " + (grid.data("empty-cls") || $default["empty-cls"]) + "'>" + (grid.data("empty-text") || $default["empty-text"]) + "</div></div>");
						$scrollRows.html($row);
						if ($l) {
							$rowl = $("<div class='table-row'></div>");
							$fixedLeft.html($rowl);
							$row.find("div.table-cell").css("text-align", "left");
						}
						if ($r) {
							$rowr = $("<div class='table-row'></div>");
							$fixedRight.html($rowr);
						}
						if ($rowl || $rowr) {
							var maxh = $gridOpts.getMaxHeight($row, $rowl, $rowr);
							$row.css("height", maxh + 2);
							$rowl && $rowl.css("height", maxh + 2);
							$rowr && $rowr.css("height", maxh + 2);
						}
					}
				}
				$gridOpts.loadSuccess(grid, attributes);
				return $module;
			},
			getMaxHeight : function(row, rowl, rowr) {
				var h1 = row.height(), max = h1;
				if (rowl) {
					max = rowl.height() - h1 > 0 ? rowl.height() : h1;
				}
				if (rowr) {
					max = rowr.height() - max > 0 ? rowr.height() : max;
				}
				return max;
			},
			showTreeData : function(grid, attributes) {// 显示树形表格数据
				var $ths = attributes.headCells, $datas = [], $dataList = grid.data("dataList"), $rows = null, $scrollRows = attributes.tableBody.find("div.scroll-area>div.table-rows"), $fixedLeft = attributes.tableBody.find("div.fixed-left>div.table-rows"), $fixedRight = attributes.tableBody.find("div.fixed-right>div.table-rows"), $l = $fixedLeft.length > 0, $r = $fixedRight.length > 0;
				if ($dataList && $dataList.length > 0) {
					if (!$ths || $ths.length == 0) {
						return $module;
					}
					(function() {
						var $parentField = grid.data("parent-field"), $childField = grid.data("child-field"), $treeColumn = grid.data("tree-column");
						if ($common.isBlank($parentField, true) || $common.isBlank($childField, true) || $common.isBlank($treeColumn, true)) {
							throw {
								type : 1,
								name : "ParameterError",
								message : "Attributes of 'parent-field'、'child-field'、'tree-column' is required for treeGrid"
							};
						}
						$rows = grid.data("rows");
						if ($rows) {
							$rows.removeData();// 清除数据缓存
						}
						var rcl, $rc, $fixed = null, $count = 0, $details = "", $dhtml = "", $headcontent = "", $expand = grid.data("expand-tree-onload"), $pci = grid.data("parent-close-icon") || $default["parent-close-icon"], $poi = grid.data("parent-open-icon") || $default["parent-open-icon"], $ci = grid.data("child-icon") || $default["child-icon"], $responseData = grid.data("responseData");
						var $idField = grid.data("id-field") || $default["id-field"], $rowCls = grid.data("row-cls") || $default["row-cls"], $record = null, $events = grid.data("events"), $pageVo = grid.data("pageVo"), $openBtn = null, $detailsRow = null, $showDetail = grid.data("show-details-onload"), $detailsCls = grid.data("details-cls") || $default["details-cls"], $detailsCell = null, $doi = grid.data("details-open-icon") || $default["details-open-icon"], $dci = grid.data("details-close-icon") || $default["details-close-icon"], $loadingCls = grid.data("details-loading-icon") || $default["details-loading-icon"], $row = null, $rowl = null, $rowr = null, $cell = null, $cellContent = null, $responseData = grid.data("responseData");
						if (!$expand && false != $expand) {
							$expand = $default["expand-tree-onload"];
						}
						if (!$showDetail && false != $showDetail) {
							$showDetail = $default["show-details-onload"];
						}
						$scrollRows.length > 0 && $scrollRows.empty();
						$fixedLeft.length > 0 && $fixedLeft.empty();
						$fixedRight.length > 0 && $fixedRight.empty();
						$dataList = $gridOpts.toTreeData($dataList, $parentField, $childField, 'children');
						if ("string" === typeof $rowCls) {
							$rowCls = $rowCls.split(",");
						}
						rcl = $rowCls.length;
						var drawRows = function(list, pindex, num) {
							for (var i = 0, length = list.length; i < length; i++) {
								$record = list[i];
								$datas.push($record);
								$headcontent = "";
								if (!$record) {
									continue;
								}
								$rc = $rowCls[$count % rcl];
								$row = $("<div class='table-row " + $rc + "' data-index=" + $count + "></div>");
								if ($l) {
									$rowl = $("<div class='table-row " + $rc + "' data-index=" + $count + "></div>");
									$fixedLeft.append($rowl);
								}
								if ($r) {
									$rowr = $("<div class='table-row " + $rc + "' data-index=" + $count + "></div>");
									$fixedRight.append($rowr);
								}
								if (pindex >= 0) {
									$row.attr("data-pindex", pindex);
									$rowl && $rowl.attr("data-pindex", pindex);
									$rowr && $rowr.attr("data-pindex", pindex);
								}
								$ths.each(function(index, e) {
									var $this = $(this), $showTips = $this.data("show-tips"), $width = $this.data("width"), $hasRender = false, $content = "", $field = $this.data("field"), $dateFormat = $this.data("date-format"), $columnType = $this.data("type"), $cellEvent = $this.data("events"), $value = "", renderMap = $this.data("render-map");
									$cell = $("<div class='table-cell " + ($this.data("content-cls") || "") + "' data-index=" + index + "></div>");
									$cellContent = $("<div class='cell-content'></div>");
									$cell.addClass($this.data("common-cls") || "").append($cellContent);
									false == $this.data("visible") && $cell.addClass("bsplus-hidden");
									$width && $cell.css("width", $width);
									$value = $common.getValueByField($record, $field);
									if ($cellEvent && "function" === typeof $cellEvent.renderer) {
										try {
											$value = $this.triggerPlusEvent("renderer", {
												responseData : $responseData,
												value : $value,
												record : $record,
												row : $row,
												cell : $cell,
												cellContent : $cellContent
											});
											$hasRender = true;
										} catch (e) {
											$common.log(e);
										}
									}
									try {
										if (!$common.isBlank($dateFormat, true) && !$common.isBlank($value, true)) {
											if ("string" === typeof $value) {
												$value = new Date(Date.parse($value.replace(/-/g, "/"))).format($dateFormat);
											} else if ("number" === typeof $value) {
												$value = new Date($value).format($dateFormat);
											} else if ("object" === typeof $value) {
												$value = $value.format($dateFormat);
											}
											$record[$field] = $value;
										}
									} catch (e) {
										$common.log(e);
									}
									$value = $common.getValue($value);
									if (renderMap) {
										if ("string" === typeof renderMap) {
											eval("renderMap=" + renderMap)
										}
										$value = renderMap[$value];
									}
									if (!$common.isBlank($field, true) && $field == $treeColumn) {
										$headcontent = "<span class='tree-content'>";
										if (!$record.firstNode) {
											if (!$expand) {
												$row.hide();
												$rowl && $rowl.hide();
												$rowr && $rowr.hide();
											}
											for (var j = 0; j < num; j++) {
												$headcontent += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
											}
										}
										if ($record.children && $record.children.length > 0) {
											$headcontent += "<a href='javascript:void(0)' class='tree-btn'><span class='" + ($expand ? $poi : $pci) + "'>&nbsp;</span></a>";
										} else {
											$headcontent += "<span class='" + $ci + "'>&nbsp;</span>";
										}
										$headcontent += "</span>";
										$cellContent.append($headcontent);
									}
									if ($columnType) {
										$cell.attr("data-type", $columnType);
										if ($cell.attr("class").indexOf("min-") < 0)
											$cell.addClass("img" == $columnType ? "min-40" : "min-40");
										if ($cell.attr("class").indexOf("max-") < 0)
											$cell.addClass("img" == $columnType ? "max-80" : "max-50");
										if ("index" == $columnType) {
											$cellContent.html($count + 1);
										} else if ("select" == $columnType) {
											if (attributes.multiSelect) {// 默认多选
												$cellContent.html("<input type='checkbox' class='subcheck' value='" + ($record[$idField] || "") + "'/>");
											} else {// 单选
												$cellContent.html("<input type='radio' class='singlecheck' value='" + ($record[$idField] || "") + "'/>");
											}
										} else if ("img" == $columnType) {
											var $defaultUrl = $this.data("default-img") || $default["default-img"], $imgUrl = $value || $defaultUrl;
											if ("string" == typeof $value) {
												$cellContent.html('<img class="grid-cell-img" src="' + $imgUrl + '" style="width:' + ($this.data("img-width") || 120) + ';height:' + ($this.data("img-height") || 100) + ';" alt="' + ($this.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/>');
											} else if ($common.isArray($value)) {// 数组
												$cellContent.data("imgList", $value);
												$imgUrl = $value[0];
												if ($value.length > 0) {
													$cellContent.html('<img class="grid-cell-img" src="' + $imgUrl + '" style="width:' + ($this.data("img-width") || 120) + ';height:' + ($this.data("img-height") || 100) + ';" alt="' + ($this.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/>');
												}
											}
											$cell.data("view-mode", $this.data("view-mode") || 1).data("link", $this.data("link-url") || $imgUrl);
										} else if ("detail" == $columnType) {
											$cell.css("fontSize", "16px");
											$openBtn = $("<a href='javascript:void(0)' class='detail-btn'><i class='" + ($showDetail ? $doi : $dci) + "'></i></a>");
											$cellContent.append($openBtn);
										} else if ("link" == $columnType) {
											var linkUrl, linkField = $this.data("link-field"), linkTarget = $this.data("link-target") || "_blank";
											linkField && (linkUrl = $common.getValueByField($record, linkField));
											!linkUrl && $value.isUrl() && (linkUrl = $value);
											!linkUrl && (linkUrl = $this.data("link-default-url") || "");
											if (linkUrl) {
												$cellContent.append("<a href='" + linkUrl + "' target='" + linkTarget + "'>" + $value + "</a>");
											} else {
												$cellContent.append("<a href='javascript:void(0)'>" + $value + "</a>");
											}
										}
									} else {
										$value = ($common.getValue($this.data("before")) || "") + ($value || $common.getValue($this.data("default")) || "") + ($common.getValue($this.data("after")) || "");
										if (!$common.isBlank($field, true) && $field == $treeColumn) {
											$cellContent.find("span.tree-content").append($value);
										} else {
											$cellContent.append($value);
										}
									}
									if ($cellEvent && "function" === typeof $cellEvent.ondrew) {
										$this.triggerPlusEvent("ondrew", {
											responseData : $responseData,
											value : $value,
											record : $record,
											row : $row,
											cell : $cell,
											cellContent : $cellContent
										});
									}
									if ($showTips) {
										if ($this.data("tips-msg")) {
											$cellContent.attr("title", $this.data("tips-msg")).attr("data-toggle", "tooltip").attr("data-placement", $this.data("placement") || "top");
										} else if (!$hasRender) {
											$cellContent.attr("title", $value).attr("data-toggle", "tooltip").attr("data-placement", $this.data("placement") || "top");
										}
									}
									$fixed = $this.data("fixed");
									if ($fixed) {
										if ("left" == $fixed) {
											$rowl.append($cell);
										} else if ("right" == $fixed) {
											$rowr.append($cell);
										} else {
											$row.append($cell);
										}
									} else {
										$row.append($cell);
									}
								});
								$scrollRows.append($row.data("record", $record));
								if (attributes.showDetails) {
									$details = "<i class='" + $loadingCls + "'></i>" + $ct.loading;
									if ($showDetail) {
										$dhtml = "<div data-index='" + $count + "' class='detail-row " + ($detailsCls || "") + "'></div>";
										$row.data("detail-show", true);
									} else {
										$dhtml = "<div data-index='" + $count + "' class='detail-row " + ($detailsCls || "") + "' style='display: none;'></div>"
										$row.data("detail-show", false);
									}
									$detailsRow = $($dhtml);
									if ($events && "function" === typeof $events.ondrawdetails) {
										try {
											$details = grid.triggerPlusEvent("ondrawdetails", {
												record : $record,
												row : $row,
												detailRow : $detailsRow
											});
											if ($common.isBlank($details, true)) {
												$details = "";
											}
										} catch (e) {
											$details = "<i class='" + $loadingCls + "'></i>" + $ct.loading;
											$common.log(e);
										}
									}
									$detailsRow.html($details);
									$scrollRows.length > 0 && $scrollRows.append($detailsRow);
									$fixedLeft.length > 0 && $fixedLeft.append($dhtml);
									$fixedRight.length > 0 && $fixedRight.append($dhtml);
								}
								if ($rowl || $rowr) {
									var maxh = $gridOpts.getMaxHeight($row, $rowl, $rowr);
									$row.css("height", maxh + 2);
									$rowl && $rowl.css("height", maxh + 2);
									$rowr && $rowr.css("height", maxh + 2);
								}
								$count++;
								if ($record.children && $record.children.length > 0) {
									$row.data("closed", !$expand);
									drawRows($record.children, $count - 1, num + 1);// 递归
								}
							}
						}
						drawRows($dataList, -1, 0);
						grid.data("dataList", $datas.concat());// 缓存数据
						if (attributes.showDetails && $showDetail) {
							$gridOpts.setDetailsHeight(grid, attributes);
						}
						if (attributes.showSummary && $events && "function" == typeof $events.ondrawsummary) {
							var $summary = grid.triggerPlusEvent("ondrawsummary", {
								pageVo : $pageVo,
								data : $dataList
							}) || "";
							grid.find("div.grid-summary").html($summary);
						}
					}());
				} else {
					if (attributes.showEmptyText) {
						var $row = $("<div class='table-row'><div class='table-cell " + (grid.data("empty-cls") || $default["empty-cls"]) + "'>" + (grid.data("empty-text") || $default["empty-text"]) + "</div></div>");
						$scrollRows.html($row);
						if ($l) {
							var $rowl = $("<div class='table-row'></div>");
							$fixedLeft.html($rowl);
							$row.find("div.table-cell").css("text-align", "left");
						}
						if ($r) {
							var $rowr = $("<div class='table-row'></div>");
							$fixedRight.html($rowr);
						}
						if ($rowl || $rowr) {
							var maxh = $gridOpts.getMaxHeight($row, $rowl, $rowr);
							$row.css("height", maxh + 2);
							$rowl && $rowl.css("height", maxh + 2);
							$rowr && $rowr.css("height", maxh + 2);
						}
					}
				}
				$gridOpts.loadSuccess(grid, attributes);
				return $module;
			},
			hideOrShowChild : function(grid, row, attributes) {// 树形表格显示或隐藏子节点
				try {
					var $index = row.data("index"), $status = row.data("closed");
					var $crow = attributes.bodyContent.find("div.table-row[data-pindex='" + $index + "']");
					if ($status) {
						row.data("closed", false);// 展开
						attributes.bodyContent.find("div.table-row[data-index='" + $index + "'] a.tree-btn>span").attr("class", grid.data("parent-open-icon") || $default["parent-open-icon"]);
						$crow.show();
						grid.triggerPlusEvent("ontreeopen", {
							selected : row.data("selected"),
							record : row.data("record"),
							row : row,
							status : "open"
						});
					} else {
						row.data("closed", true);// 关闭
						attributes.bodyContent.find("div.table-row[data-index='" + $index + "'] a.tree-btn>span").attr("class", grid.data("parent-close-icon") || $default["parent-close-icon"]);
						$crow.hide();
						grid.triggerPlusEvent("ontreeclose", {
							selected : row.data("selected"),
							record : row.data("record"),
							row : row,
							status : "close"
						});
						$crow.each(function(index, e) {
							var $r = $(this);
							if (attributes.showDetails && $r.data("detail-show")) {
								var $a = attributes.bodyContent.find("div.table-row[data-index='" + $r.data("index") + "'] div.table-cell[data-type='detail'] a.detail-btn");
								$a && $a.length > 0 && $gridOpts.clickDbtn(grid, $a);
							}
							if (!$r.data("closed")) {
								$gridOpts.hideOrShowChild(grid, $(this), attributes);// 递归
							}
						});
					}
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			selectChild : function(grid, row, attributes) {// 选中
				var $cr = attributes.bodyContent.find("div.table-row[data-pindex='" + row.data("index") + "']");
				try {
					if ($cr.length > 0) {
						var $rowcheckboxs = $cr.find("input.subcheck:checkbox");
						if (row.data("selected")) {
							if ((attributes.selectChildren || attributes.selectAllChildren)) {
								$cr.addClass(attributes.selectedCls).data("selected", true);
								$checker.check.call($rowcheckboxs, true);
								attributes.selectAllChildren && $cr.each(function() {
									$gridOpts.selectChild(grid, $(this), attributes);
								});
							}
						} else if ((attributes.deSelectChildren || attributes.deSelectAllChildren)) {
							$cr.removeClass(attributes.selectedCls).data("selected", false);
							$checker.check.call($rowcheckboxs, false);
							attributes.deSelectAllChildren && $cr.each(function() {
								$gridOpts.selectChild(grid, $(this), attributes);
							});
						}
					}
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			selectParent : function(grid, row, attributes) {// 选中
				var $pr = attributes.bodyContent.find("div.table-row[data-index='" + row.data("pindex") + "']");
				try {
					if ($pr.length > 0) {
						var $rowcheckboxs = $pr.find("input.subcheck:checkbox");
						if (row.data("selected")) {
							if ((attributes.selectParent || attributes.selectAllParent)) {
								$pr.addClass(attributes.selectedCls).data("selected", true);
								$checker.check.call($rowcheckboxs, true);
								attributes.selectAllParent && $pr.each(function() {
									$gridOpts.selectParent(grid, $(this), attributes);
								});
							}
						} else if ((attributes.deSelectParent || attributes.deSelectAllParent)) {
							$pr.removeClass(attributes.selectedCls).data("selected", false);
							$checker.check.call($rowcheckboxs, false);
							attributes.deSelectAllParent && $pr.each(function() {
								$gridOpts.selectParent(grid, $(this), attributes);
							});
						}
					}
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			drawPager : function(grid, sizeList) {// 创建分页视图
				var $page = [], $i = 0, a = sizeList.length;
				$page[$i++] = '<div class="page-panel"><div class="p-left"><div class="page-count btn-group">';
				$page[$i++] = '<button class="btn btn-default no-opacity" disabled="disabled">' + $ct.showing + '</button>';
				var $select = $("<select class='bsplus-select' data-show-btn='false' data-input-type='button' data-width='auto' data-value-field='id' data-text-field='id' onchange='BsplusGrid.selectPageSize(this)' data-default='" + (grid.data("page-size") || $default["page-size"] || 10) + "'></select>")
				var $pageSizes = "[";
				for (var i = 0; i < a; i++) {
					if (i == sizeList.length - 1) {
						$pageSizes += "{id:" + sizeList[i] + "}";
					} else {
						$pageSizes += "{id:" + sizeList[i] + "},";
					}
				}
				$pageSizes += "]";
				$select.attr("data-list", $pageSizes);
				$page[$i++] = $select.prop("outerHTML");
				$page[$i++] = '<button class="btn btn-default no-opacity" disabled="disabled">' + $ct.record + '</button><button class="btn btn-default no-opacity" disabled="disabled" total-record="true">' + $ct.altogether + '0' + $ct.record + '</button>';
				$page[$i++] = '<button class="btn btn-default no-opacity" disabled="disabled" select-rows>' + $ct.currentlySelected + '0' + $ct.record + '</button></div></div><div class="p-right"><div class="page-nav btn-group" role="group">';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="first"><i class="fa fa-step-backward"></i></button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="prev"><i class="fa fa-chevron-left"></i></button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="go">' + $ct.jumpTo + '</button><input type="text" class="form-control" placeholder="1" current-page="true" value="1">';
				$page[$i++] = '<button type="button" class="btn btn-default no-opacity" disabled="disabled">' + $ct.page + '</button><button class="btn btn-default no-opacity" disabled="disabled" total-page="true">' + $ct.altogether + '0' + $ct.page + '</button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="next"><i class="fa fa-chevron-right"></i></button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="last"><i class="fa fa-step-forward"></i></button></div></div></div>';
				return $page.join("");
			},
			updatePager : function(grid) {// 更新分页视图数据
				try {
					var $pageVo = grid.data("pageVo"), $attributes = grid.data("attributes") || {};
					if ($pageVo && $attributes) {
						var $pages = $attributes.pages;
						$pages.find("button[total-record]").html($ct.altogether + $pageVo.totalRecord + $ct.record);
						$pages.find("button[total-page]").html($ct.altogether + $pageVo.totalPage + $ct.page);
						$pages.find("input[current-page]").val($pageVo.currentPage);
						if ($pageVo.currentPage == 1 || $pageVo.totalPage == 0) {
							$pages.find("button[data-action='first'],button[data-action='prev']").prop("disabled", true);
						} else {
							$pages.find("button[data-action='first'],button[data-action='prev']").prop("disabled", false);
						}
						if ($pageVo.currentPage == $pageVo.totalPage || $pageVo.totalPage == 0) {
							$pages.find("button[data-action='next'],button[data-action='last']").prop("disabled", true);
						} else {
							$pages.find("button[data-action='next'],button[data-action='last']").prop("disabled", false);
						}
					}
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			gotoPage : function(grid, attributes, action) {// 绑定翻页按钮点击事件
				try {
					var $pages = attributes.pages, $pageVo = grid.data("pageVo"), $input = $pages.find("input[current-page]"), $currentPage = $pageVo.currentPage, $totalPage = $pageVo.totalPage;
					if (!$common.isBlank(action, true)) {
						switch (action) {
						case "first":
							if ($currentPage == 1) {
								return $module;
							} else {
								$pageVo.currentPage = 1;
							}
							break;
						case "prev":
							if ($currentPage == 1) {
								return $module;
							} else if ($currentPage > 1) {
								$pageVo.currentPage = $currentPage - 1;
							}
							break;
						case "next":
							if ($currentPage == $totalPage) {
								return $module;
							} else if ($currentPage < $totalPage) {
								$pageVo.currentPage = $currentPage + 1;
							}
							break;
						case "last":
							if ($currentPage == $totalPage) {
								return $module;
							} else if ($currentPage < $totalPage) {
								$pageVo.currentPage = $totalPage;
							}
							break;
						case "go":
							$pageVo.currentPage = $input.val() * 1;
							break;
						default:
							break;
						}
						$module.search.call(grid);
					}
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			doAction : function(grid, element, action) {// 功能按钮点击事件
				if (!element.data("inited")) {
					$common.initActionBtn(element);// 初始化
				}
				var $options = element.data("options"), $events = element.data("events");
				if ($events && "function" === typeof $events.beforeaction) {// 执行相关操作之前执行的方法
					try {
						if (!element.triggerPlusEvent("beforeaction")) {
							return;
						}
					} catch (e) {
						BStools.log(e);
					}
				}
				switch (action) {
				case "search":// 点击查询按钮
					if (grid.data("pageVo")) {
						grid.data("pageVo").currentPage = 1;
					}
					$module.search.call(grid);
					break;
				case "add":
					bsplus.openModal($options);
					break;
				case "config":
					grid.find("div.bsplus-grid-config div.config-container").toggle(300, function() {
						BsplusGrid.showOrHideConfig($(this));
					});
					break;
				case "edit":
					var ids = $module.getSelectedKeys.call(grid);// 获取选中主键
					if (ids.length > 1) {
						bsplus.showToast({
							content : $ct.selectOnlyOne,
							backColor : "#F89406"
						});
						return;
					} else if (!ids || ids.length == 0) {
						bsplus.showToast({
							content : $ct.pleaseSelect
						});
						return;
					}
					var $default = {
						onload : function(opts) {// 模态框创建完成
							if (opts.inIframe && $common.exist(opts.iframe.contentWindow.BsplusForm)) {
								opts.iframe.contentWindow.BsplusForm.showEdit({
									objField : opts.dataField,
									primarykey : ids[0],
									dataUrl : opts.dataUrl,
									formId : opts.formId,
									paramName : opts.paramName,
									form : opts.form,
									modalopts : opts
								});
							}
						},
						onshown : function(opts) {
							if (!opts.inIframe) {
								BsplusForm.showEdit({
									objField : opts.dataField,
									primarykey : ids[0],
									dataUrl : opts.dataUrl,
									formId : opts.formId,
									paramName : opts.paramName,
									form : opts.form,
									paramData : opts.paramData
								});
							}
						}
					};
					BsplusModal.open($.extend($default, $options));
					break;
				case "delete":// 点击查询按钮
					$module.deleteDates.call(grid, $options);
					break;
				default:
					break;
				}
			},
			onSort : function(grid, element) {
				try {
					var $pageVo = grid.data("pageVo") || {
						sortOrder : "desc"
					}, $sortType = "desc";
					if ("desc" == $pageVo.sortOrder.toLowerCase()) {
						$sortType = "asc";
					} else {
						$sortType = "desc";
					}
					grid.data("pageVo", $pageVo);
					$module.sortByField.call(grid, element.data("sort-field"), $sortType);
				} catch (e) {
					$common.log(e);
				}
				return $module;
			},
			loadSuccess : function(grid, attributes) {// 表格数据加载完时执行
				var $rows = attributes.bodyContent.find("div.scroll-area div.table-row");
				grid.data("rows", $rows);
				attributes.bodyContent.find("div.table-cell[data-type='img'] img.grid-cell-img").error(function() {// 图片加载失败时显示默认图片
					var $this = $(this);
					if (!$this.data("onerror")) {
						this.src = $this.attr("default-img") || this.src;
						$this.data("onerror", true);
					}
				});
				$gridOpts.uopdateSortIcon(grid);// 更换排序图标
				if (attributes.showPager) {
					$gridOpts.updatePager(grid);// 更新分页视图数据
				}
				try {
					$checker.init.call(attributes.bodyTable.find("input:checkbox,input:radio"), {
						'cls' : grid.data("checker-cls") || $default["checker-cls"]
					});
				} catch (e) {
					$common.log(e);
				}
				try {
					attributes.bodyDiv.unloading();// 隐藏加载提示框
					$module.getSelectedNum.call(grid);
					grid.triggerPlusEvent("ondataload", {
						data : grid.data("dataList"),
						responseData : grid.data("responseData"),
						pageInfo : grid.data("pageVo")
					});
					$common.initToolTips(grid);
				} catch (e) {
					$common.log(e);
				}
				return $module;
			}
		};
		return {
			name : "BsplusGrid",
			initialize : function() {
				$module = this;
			},
			init : function(options) {
				var $grids = this;
				if (!$grids.hasClass("bsplus-grid")) {
					$grids = this.find("div.bsplus-grid");
					if (!$grids || $grids.length == 0) {
						// $grids = this;
						// $grids.addClass("bsplus-grid");
						return this;
					}
				}
				if (options && "object" === typeof options) {// 初始化data
					for ( var n in options) {
						$grids.data(n, options[n]);
					}
				}
				$grids.each(function() {// 表格初始化，为了减少页面的重绘（减少内存损耗），将常用对象缓存到data中，减少选择次数
					var $this = $(this), minHeight = $this.data("min-height"), $htArray = [ [], [], [] ], $visible = true, $init = false == $this.data("init") ? false : true, $comAttr = null, $ths = null, $columnType = null, $event = null, $fixed = null, $fixedLeft = false, $fixedRight = false, $leftArr = [], $centerArr = [], $rightArr = [], sp = $this.data("scroll-position") || "bottom", $tableScroll;
					if ($init && !$this.data("inited")) {
						this.style.display = "none";
						$comAttr = {
							isTree : "treegrid" === ($this.data("grid-type") || $default["grid-type"]),
							selectedCls : $this.data("row-selected-cls") || $default["row-selected-cls"],
							multiSelect : "boolean" === typeof $this.data("multi-select") ? $this.data("multi-select") : ("boolean" === typeof $default["multi-select"] ? $default["multi-select"] : true),
							showPager : "boolean" === typeof $this.data("show-pager") ? $this.data("show-pager") : ("boolean" === typeof $default["show-pager"] ? $default["show-pager"] : true),
							showSummary : "boolean" === typeof $this.data("show-summary") ? $this.data("show-summary") : ("boolean" === typeof $default["show-summary"] ? $default["show-summary"] : false),// 是否显示汇总行
							selectOnRowClick : "boolean" === typeof $this.data("select-onrowclick") ? $this.data("select-onrowclick") : ("boolean" === typeof $default["select-onrowclick"] ? $default["select-onrowclick"] : true),
							bodyDiv : $this.find("div.grid-body")
						};
						if ($comAttr.isTree) {
							$comAttr.selectChildren = $this.data("select-children") || false;
							$comAttr.selectAllChildren = $this.data("select-all-children") || false;
							$comAttr.selectParent = $this.data("select-parent") || false;
							$comAttr.selectAllParent = $this.data("select-all-parent") || false;
							$comAttr.deSelectChildren = $this.data("deselect-children") || false;
							$comAttr.deSelectAllChildren = $this.data("deselect-all-children") || false;
							$comAttr.deSelectParent = $this.data("deselect-parent") || false;
							$comAttr.deSelectAllParent = $this.data("deselect-all-parent") || false;
						}
						$event = {
							onbeforeload : $this.data("onbeforeload") || $default["onbeforeload"] || null,
							onloadsuccess : $this.data("onloadsuccess") || $default["onloadsuccess"] || null,
							onloaderror : $this.data("onloaderror") || $default["onloaderror"] || null,
							ondataload : $this.data("ondataload") || $default["ondataload"] || null,
							ondrawdetails : $this.data("ondrawdetails") || $default["ondrawdetails"] || null
						};
						$comAttr.bodyTable = $comAttr.bodyDiv.find("div.bsplus-table");
						$comAttr.showEmptyText = !$common.isBlank($this.data("empty-text") || $default["empty-text"], true);
						$comAttr.headCells = $comAttr.bodyTable.find("div.table-fields").children();
						$this.data("allow-wrap") ? $comAttr.bodyTable.addClass("table-wrap").removeClass("no-wrap") : $comAttr.bodyTable.addClass("no-wrap").removeClass("table-wrap");// 是否允许换行
						$comAttr.headCells.each(function(index, e) {
							var $thisCell = $(this), $helpText = $thisCell.data("help-text"), $cls = $thisCell.data("common-cls"), $width = $thisCell.data("width"), $cellHtml = $("<div class='table-head-cell' data-index='" + index + "'></div>");
							$fixed = $thisCell.data("fixed");
							$thisCell.attr("data-index", index);
							$visible = false != $thisCell.data("visible");
							if (!$common.isBlank($cls, true)) {
								$thisCell.addClass($cls);
							}
							$thisCell.listenPlusEvent({
								renderer : $thisCell.data("renderer") || null,
								ondrew : $thisCell.data("ondrew") || null
							});// 事件监听初始化
							$columnType = $thisCell.data("type");
							$columnType && $thisCell[0].className.indexOf("min-") < 0 && $thisCell.addClass("img" == $columnType ? "min-40" : "min-40");
							$columnType && $thisCell[0].className.indexOf("max-") < 0 && $thisCell.addClass("img" == $columnType ? "max-80" : "max-50");
							$cls = $thisCell[0].className;
							!$visible && ($cls += " bsplus-hidden");
							$cls && ($cellHtml.addClass($cls));
							$width && ($cellHtml.css("width", $width));
							$cellHtml.html($thisCell.html());
							if ($columnType) {
								if ("select" == $columnType && $(e).find("input:checkbox.checkAll").length == 0) {
									if ($comAttr.multiSelect) {
										$cellHtml.html("<input type='checkbox' class='checkAll' />");
									} else if ($common.isBlank(e.innerHTML, true)) {
										$cellHtml.html($ct.select);
									}
								} else if ("index" == $columnType) {
									if ($common.isBlank(e.innerHTML, true)) {
										$cellHtml.html($ct.index);
									}
								} else if ("img" == $columnType) {
									$thisCell.data("default-img", $thisCell.data("default-img") || $this.data("default-img") || $default["default-img"] || $path + "/images/default.png");
								} else if ("detail" == $columnType) {
									$comAttr.showDetails = true;
								}
							}
							$thisCell.data("sort-field", $thisCell.data("sort-field") || $thisCell.data("field"));
							if ($thisCell.data("allow-sort") && $thisCell.data("sort-field")) {
								$cellHtml.html($thisCell.html() + "<i class='fa fa-sort'></i>").addClass("table-sort").attr("data-sort-field", $thisCell.data("sort-field"));
							}
							if ($helpText) {
								$cellHtml.append("<span class='" + ($thisCell.data("help-icon") || "fa fa-question-circle") + " help-btn' data-toggle='tooltip' data-placement='top' title='" + $helpText + "'></span>");
							}
							if ($fixed) {
								if ("left" == $fixed) {
									$fixedLeft = true;
									$leftArr.push($cellHtml.prop("outerHTML"));
									$htArray[0].push({
										text : $thisCell.data("config-text") || ("select" == $columnType ? "选择" : $thisCell.html()),
										state : $visible,
										index : index,
										config : false == $thisCell.data("config") ? false : true
									});
								} else if ("right" == $fixed) {
									$fixedRight = true;
									$rightArr.push($cellHtml.prop("outerHTML"));
									$htArray[2].push({
										text : $thisCell.data("config-text") || ("select" == $columnType ? "选择" : $thisCell.html()),
										state : $visible,
										index : index,
										config : false == $thisCell.data("config") ? false : true
									});
								} else {
									$centerArr.push($cellHtml.prop("outerHTML"));
									$htArray[1].push({
										text : $thisCell.data("config-text") || ("select" == $columnType ? "选择" : $thisCell.html()),
										state : $visible,
										index : index,
										config : false == $thisCell.data("config") ? false : true
									});
								}
							} else {
								$htArray[0].push({
									text : $thisCell.data("config-text") || ("select" == $columnType ? "选择" : $thisCell.html()),
									state : $visible,
									index : index,
									config : false == $thisCell.data("config") ? false : true
								});
								$centerArr.push($cellHtml.prop("outerHTML"));
							}
						});
						$comAttr.tableHead = $("<div class='table-head'></div>");
						$comAttr.tableBody = $("<div class='table-body'></div>");
						minHeight && parseInt(minHeight) > 0 && $comAttr.tableBody.css("min-height", minHeight);
						$comAttr.bodyContent = $("<div class='table-body-content'></div>");
						$tableScroll = $("<div class='table-scroll-box'></div>");
						$comAttr.bodyTable.append($comAttr.tableHead).append($comAttr.tableBody);
						$comAttr.tableBody.append($comAttr.bodyContent);
						if ($this.data("show-foot")) {
							$comAttr.tableFoot = $("<div class='table-foot'></div>");
							$comAttr.bodyTable.append($comAttr.tableFoot)
						}
						var $cw = $this.data("scroll-width") || "100%", $lw = $this.data("left-width") || "20%", $rw = $this.data("right-width") || "20%";
						if ($fixedLeft) {
							$comAttr.tableHead.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"><div class="table-rows"><div class="table-head-row">' + $leftArr.join("") + '</div></div></div>');
							$comAttr.bodyContent.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"><div class="table-rows"></div></div>');
							if ($comAttr.tableFoot) {
								$comAttr.tableFoot.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"><div class="table-rows"><div class="table-head-row">' + $leftArr.join("") + '</div></div></div>');
							}
							$tableScroll.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"></div>');
						}
						$comAttr.tableHead.append('<div class="table-block scroll-area"><div class="table-rows" style="width:' + $cw + ';"><div class="table-head-row">' + $centerArr.join("") + '</div></div></div>');
						$comAttr.bodyContent.append('<div class="table-block scroll-area"><div class="table-rows" style="width:' + $cw + ';"></div></div>');
						if ($comAttr.tableFoot) {
							$comAttr.tableFoot.append('<div class="table-block scroll-area"><div class="table-rows" style="width:' + $cw + ';"><div class="table-head-row">' + $centerArr.join("") + '</div></div></div>');
						}
						$tableScroll.append('<div class="table-block scroll-area"><div class="table-scroll-bar"><div style="width: ' + $cw + '; height: 100%;"></div></div></div>');
						if ($fixedRight) {
							$comAttr.tableHead.append('<div class="table-block fixed-area fixed-right" style="width: ' + $rw + ';"><div class="table-rows"><div class="table-head-row">' + $rightArr.join("") + '</div></div></div>');
							$comAttr.bodyContent.append('<div class="table-block fixed-area fixed-right" style="width: ' + $rw + ';"><div class="table-rows"></div></div>');
							if ($comAttr.tableFoot) {
								$comAttr.tableFoot.append('<div class="table-block fixed-area fixed-right" style="width: ' + $rw + ';"><div class="table-rows"><div class="table-head-row">' + $rightArr.join("") + '</div></div></div>');
							}
							$tableScroll.append('<div class="table-block fixed-area" style="width: ' + $rw + ';"></div>');
						}
						$comAttr.scrolls = [];
						if ($this.data("scroll-width")) {
							if (sp.indexOf("top") >= 0) {
								var $scroll = $tableScroll.clone().addClass("scroll-front");
								$comAttr.scrolls.push($scroll[0]);
								$comAttr.bodyTable.prepend($scroll);
							}
							if (sp.indexOf("bottom") >= 0) {
								var $scroll = $tableScroll.clone().addClass("scroll-later");
								$comAttr.scrolls.push($scroll[0]);
								$comAttr.bodyTable.append($scroll);
							}
						}
						$comAttr.scrolls = $($comAttr.scrolls);
						if ($comAttr.scrolls.length > 0) {
							var $sar = $this.find("div.scroll-area>div.table-rows"), $sab = $comAttr.scrolls.find("div.scroll-area>div.table-scroll-bar"), sl;
							$comAttr.scrolls.css("display", "table").find("div.table-scroll-bar").off('scroll').on('scroll', function() {
								sl = $(this).scrollLeft();
								$sab.scrollLeft(sl);
								$sar.css({
									"marginLeft" : -sl
								});
							});
						}
						$this.data("pageVo", {
							sortField : $this.data("sort-field") || $default["sort-field"] || "",// 排序字段
							sortOrder : $this.data("sort-order") || $default["sort-order"] || "",// 排序方式
							pageSize : $this.data("page-size") || $default["page-size"] || 10,// 每页显示条数
							currentPage : 1,
							totalRecord : 0,
							totalPage : 0
						});
						if ($this.data("show-config") || $default["show-config"]) {
							var $container = null, _header = $this.find("div.grid-head");
							if (0 == _header.length) {
								$this.prepend('<div class="grid-head"><div class="grid-functions"></div></div>');
								$container = $this.find("div.grid-functions");
							} else {
								$container = _header.find("div.grid-functions");
								if (0 == $container.length) {
									$container = $this.find("div.grid-query");
									if (0 == $container.length) {
										$container = $('<div class="grid-functions"></div>');
										_header.append($container);
									}
								}
							}
							$container.append('<div class="bsplus-grid-config"><button type="button" class="btn btn-sm" data-action="config"><i class="fa fa-cog fa-fw"></i></button><div class="config-container"><div class="arrow"></div><div class="config-head">表格设置</div><div class="config-content"></div></div></div>');
							var $co = $this.data("config-options") || [ 'sort', 'cells' ], $config = $this.find("div.bsplus-grid-config div.config-content");
							if ($co.indexOf('sort') >= 0) {
								$config.append('<div class="form-group"><label class="config-title">排序方式：</label> <input type="checkbox" data-width="130" class="bsplus-switch select-sort" data-left-text="全部排序" data-right-text="当页排序" data-height="25" ' + ($this.data("sort-all") ? "checked='checked'" : "") + '/></div>');
								$config.find("input.bsplus-switch.select-sort:checkbox").change(function() {
									if (this.checked) {
										$this.data("sort-all", true);
									} else {
										$this.data("sort-all", false);
									}
								});
							}
							if ($co.indexOf('cells') >= 0) {
								var $lis = [], _default = [], $o = null, $disabled = true, $showSwitch = false;
								for (var i = 0, a = $htArray.length; i < a; i++) {
									for (var j = 0, b = $htArray[i].length; j < b; j++) {
										$o = $htArray[i][j];
										if ($o.config) {
											$lis.push('<li><input type="checkbox" data-after-text="' + $o.text + '" ' + ($o.state ? 'checked="checked"' : '') + ' data-index="' + $o.index + '"/></li>');
											($o.state && _default.push($o.index)) || ($showSwitch = true);
										} else {
											$disabled = false;
										}
									}
								}
								$config.append('<div class="form-group"><label class="config-title">显示设置：</label>' + ($showSwitch ? '<input type="checkbox" data-width="130" class="bsplus-switch select-cells" data-left-text="显示全部" data-right-text="显示默认"  data-height="25"/>' : '') + '</div>');
								$config.append("<ul>" + $lis.join("") + "</ul>").BsplusChecker("init", {
									cls : $this.data("checker-cls") || $default["checker-cls"]
								});
								$config.find("ul>li input:checkbox").change(function() {
									var index = $(this).data("index");
									$module.hideOrShowCell.call($this, index, this.checked);
									$this.find("div.table-fields>div[data-index='" + index + "']").data("visible", this.checked);
									var $checkeds = $config.find("ul>li input:checkbox:checked");
									if ($disabled) {
										if (1 == $checkeds.length) {
											$checkeds.disabled();
										} else {
											$config.find("ul>li input:checkbox:disabled").enabled();
										}
									}
								});
								$config.find("input.bsplus-switch.select-cells:checkbox").change(function() {
									if (this.checked) {
										$config.find("ul>li input:checkbox").not(":checked").check(true);
									} else {
										$config.find("ul>li input:checkbox").check(false).each(function() {
											(_default.indexOf($(this).data("index")) >= 0) && ($(this).check(true));
										});
									}
								});
								var key = $this[0].id;
								if (!key) {
									key = $this.data("url");
								}
								if (key) {
									var configDatas = Cookies.getCookie(key);
									if (configDatas) {
										configDatas = configDatas.split(",");
										$config.find("ul>li input:checkbox").check(false).each(function() {
											(configDatas.indexOf($(this).data("index") + "") >= 0) && ($(this).check(true));
										});
									}
								}
							}
						}
						if ($comAttr.showPager) {// 初始化分页信息
							var $pageVo = $this.data("pageVo"), sizeList = $this.data("size-list") || $default["size-list"] || [ 10, 50, 100 ], pp = $this.data("page-position") || "bottom";
							try {
								sizeList.push(parseInt($pageVo.pageSize));// 将默认的每页显示的条数加入到页面尺寸集合中
							} catch (e) {
								$common.log(e);
							}
							sizeList = sizeList.unique();
							sizeList.sort(function(a, b) {
								return a > b ? 1 : -1
							});// 数组按从小到大排序
							var $page = $this.find("div.grid-paging");
							if ($page.length == 0) {
								$page = $("<div class='grid-paging'></div>");
							}
							$page.html($gridOpts.drawPager($this, sizeList));// 创建分页视图
							$comAttr.pages = [];
							if (pp.indexOf("top") >= 0) {
								var $head = $this.find("div.grid-head");
								if ($head.length == 0) {
									$head = $("<div class='grid-head'></div>");
									$this.prepend($head);
								}
								var $topPage = $page.clone();
								$topPage.find("select.bsplus-select").BsplusSelect("init");
								$comAttr.pages.push($topPage[0]);
								$head.append($topPage);
							}
							if (pp.indexOf("bottom") >= 0) {
								var $gridFooter = $this.find("div.grid-footer");
								if ($gridFooter.length == 0) {
									$gridFooter = $("<div class='grid-footer'></div>");
								}
								var $bottomPage = $page.clone();
								$bottomPage.find("select.bsplus-select").BsplusSelect("init");
								$comAttr.pages.push($bottomPage[0]);
								$comAttr.bodyDiv.after($gridFooter.append($bottomPage));
							}
							$comAttr.pages = $($comAttr.pages);
						}
						if ($comAttr.showSummary) {
							if ($this.find("div.grid-summary").length == 0) {
								$comAttr.bodyDiv.after("<div class='grid-summary'></div>");
							}
							$event.ondrawsummary = $this.data("ondrawsummary") || $default["ondrawsummary"] || null;
						}
						$this.data("attributes", $comAttr);
						$this.data("inited", true);// 标注为表格已初始化
						$checker.init.call($comAttr.bodyDiv.find("input:checkbox,input:radio"), {
							"cls" : $this.data("checker-cls") || $default["checker-cls"]
						});
						this.style.display = "";
						$this.listenPlusEvent($event);// 事件监听初始化
						if (false != ($this.data("load-data") || $default["load-data"])) {
							$module.search.call($this);// 查询数据
						}
						$this.data("checkAllBox", $comAttr.bodyDiv.find("div.table-head div.table-head-cell input.checkAll,div.table-foot div.table-head-cell input.checkAll"));
						// 初始化表格自定义事件（放在数据加载完成之后）
						$event = {
							oncellclick : $this.data("oncellclick") || $default["oncellclick"] || null,// 单元格点击事件
							onrowclick : $this.data("onrowclick") || $default["onrowclick"] || null,// 行点击事件
							oncelldblclick : $this.data("oncelldblclick") || $default["oncelldblclick"] || null,// 单元格双击事件
							onrowdblclick : $this.data("onrowdblclick") || $default["onrowdblclick"] || null,// 行双击事件
							onselectallclick : $this.data("onselectallclick") || $default["onselectallclick"] || null,// 全选按钮点击事件
							onallselected : $this.data("onallselected") || $default["onallselected"] || null,// 全选事件
							ongridedit : $this.data("ongridedit") || $default["ongridedit"] || null,// 编辑表格
							ondetailopen : $this.data("ondetailopen") || $default["ondetailopen"] || null,// 详情显示事件
							ondetailclose : $this.data("ondetailclose") || $default["ondetailclose"] || null// 详情隐藏时触发的事件
						};
						$comAttr.clicktime = $event.onrowdblclick ? 300 : $event.oncelldblclick ? 300 : 0;// 单击生效延时时间（清除双击时触发的点击事件）
						if ($comAttr.isTree) {
							$event.ontreecontrolclick = $this.data("ontreecontrolclick") || $default["ontreecontrolclick"] || null;// 树节点展开收缩控制按钮点击事件
							$event.ontreeclose = $this.data("ontreeclose") || $default["ontreeclose"] || null;// 树节点收缩事件
							$event.ontreeopen = $this.data("ontreeopen") || $default["ontreeopen"] || null;// 树节点展开事件
						}
						$this.listenPlusEvent($event);// 事件监听初始化
						$gridOpts.agcl($this, $comAttr);// 绑定表格点击事件
						$gridOpts.hoverGrid($this);
					} else {
						if (false != ($this.data("load-data") || $default["ontreeopen"])) {
							$this.data("init", true);
							$module.search.call($this);// 查询数据
						}
					}
					var $rowl, $rowr, $row;
					$fixedLeft && ($rowl = $this.find("div.table-head div.fixed-left div.table-head-row,div.table-foot div.fixed-left div.table-head-row"));
					$fixedRight && ($rowr = $this.find("div.table-head div.fixed-right div.table-head-row,div.table-foot div.fixed-right div.table-head-row"));
					if ($rowl || $rowr) {
						$this.addClass("fixed");
						$row = $this.find("div.table-head div.scroll-area div.table-head-row,div.table-foot div.scroll-area div.table-head-row");
						var maxh = $gridOpts.getMaxHeight($row, $rowl, $rowr);
						$row.css("height", maxh + 2);
						$rowl && $rowl.css("height", maxh + 2);
						$rowr && $rowr.css("height", maxh + 2);
					}
					try {
						$module.tableLayOut.call($this, true);// 调整表格布局(隐藏时无法实现布局)
					} catch (e) {
						$common.log(e);
					}
					$this.find("input.bsplus-switch:checkbox").BsplusSwitch("init");
				});
				return this;
			},
			scroll : function(direction, num) {
				num = ("number" === typeof num && num > 0) ? num : 30;
				var $grid = this;
				if (!$grid.hasClass("bsplus-grid")) {
					$grid = this.find("div.bsplus-grid");
				}
				if (!$grid || $grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				var $attributes = $grid.data("attributes") || {};
				if ($attributes.scrolls.length > 0) {
					var $sar = $grid.find("div.scroll-area>div.table-rows"), $sa = $attributes.scrolls.find("div.scroll-area"), $sab = $sa.find("div.table-scroll-bar"), sl = $sab.scrollLeft();
					if ("left" === direction) {
						sl -= num;
						$sab.scrollLeft(sl);
						$sar.css({
							"marginLeft" : -$sab.scrollLeft()
						});
					} else if ("right" === direction) {
						sl += num;
						$sab.scrollLeft(sl);
						$sar.css({
							"marginLeft" : -$sab.scrollLeft()
						});
					}
				}
			},
			tableLayOut : function(init) {
				var $grid = this;
				if (!$grid.hasClass("bsplus-grid")) {
					$grid = this.find("div.bsplus-grid");
				}
				if (!$grid || $grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				var $scrollWidth = $common.getScrollBarWidth(), oldWidth = $common.scrollBarWidth;
				if (!init && $scrollWidth == oldWidth) {
					return this;
				}
				$common.scrollBarWidth = $scrollWidth;
				if ("chrome" == $common.browser.appname) {
					if ($scrollWidth > 90) {
						$scrollWidth -= 7;
					} else if ($scrollWidth > 25) {
						$scrollWidth -= 1;
					}
				}
				$grid.each(function() {
					var $this = $(this), $attributes = $this.data("attributes") || {}, $scrollHeight = $this.data("scroll-height") || $default["scroll-height"];
					if (!$attributes) {
						return $module;
					}
					$attributes.scrolls.css("height", $scrollWidth);
					if ($scrollHeight) {
						$attributes.tableBody.addClass("scroll-y").css("height", $scrollHeight);
						$attributes.tableHead && $attributes.tableHead.css("paddingRight", $scrollWidth);
						$attributes.tableFoot && $attributes.tableFoot.css("paddingRight", $scrollWidth);
					}
				});
				return this;
			},
			search : function(options) {
				var $grid = this;
				if (!$grid.hasClass("bsplus-grid")) {
					$grid = this.find("div.bsplus-grid");
				}
				if (!$grid || $grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				if (!$grid.data("inited")) {// 如果尚未初始化，则先初始化
					$module.init.apply($grid);
					return this;
				}
				var $attributes = $grid.data("attributes") || {}, $localDatas = $grid.data("localDatas"), searchData = null;
				$attributes.bodyDiv.loading({
					msg : $grid.data("loading-msg") || $default["loading-msg"] || $ct.loading
				});
				var $pageVo = $grid.data("pageVo") || {
					sortField : $grid.data("sort-field") || $default["sort-field"],// 排序字段
					sortOrder : $grid.data("sort-order") || $default["sort-order"],// 排序方式
					pageSize : $grid.data("page-size") || $default["page-size"],// 每页显示条数
					currentPage : 1
				// 当前页码
				};
				var $searchFormId = $grid.data("search-form"), searchData = null;
				if (options && "object" == typeof options) {
					if (!$common.isBlank(options.url, true)) {
						$grid.data("url", options.url).data("localDatas", null);
						$pageVo.currentPage = 1;
					}
					searchData = options.paramData;
					options.paramPage && $grid.data("search-param-page", options.paramPage);
					options.paramCondition && $grid.data("search-param-condition", options.paramCondition);
					options.contentType && $grid.data("search-content-type", options.contentType);
				} else {
					options = {};
				}
				if (!searchData) {
					if (!$common.isBlank($searchFormId, true)) {
						searchData = $common.getFormatData($("#" + $searchFormId));
					} else {
						searchData = $common.getFormatData($grid.find("div.grid-head form").first());
					}
				}
				if (($grid.data("search-content-type") || $default["search-content-type"]).indexOf("application/json") >= 0) {
					var $condition = {};
					$condition[$grid.data("search-param-page") || $default["search-param-page"]] = $pageVo;
					$condition[$grid.data("search-param-condition") || $default["search-param-page"]] = searchData;
					options.condition = $condition;
				} else {
					options.condition = $.extend({}, $pageVo, searchData);
				}
				$grid.triggerPlusEvent("onbeforeload", {
					paramData : {
						url : $grid.data("url"),
						pageVo : $pageVo,
						searchData : searchData
					}
				});// 数据加载前事件
				if ($localDatas && $localDatas.length > 0) {
					$gridOpts.showLocalData($grid, $attributes);
				} else {
					$gridOpts.loadData($grid, $attributes, options);
				}
				return this;
			},
			reload : function() {
				return $module.search.call(this);
			},
			showOrHideConfig : function(e) {
				if (e && !e.is(":visible")) {
					var $grid = e.closest("div.bsplus-grid");
					var key = $grid[0].id;
					if (!key) {
						key = $grid.data("url");
					}
					if (key) {
						var datas = [], $selecteds = e.find("ul input:checkbox:checked");
						$selecteds.each(function() {
							datas.push($(this).data("index"));
						});
						Cookies.setCookie(key, datas.join(","));
					}
				}
			},
			sortByField : function(sortField, sortType) {// 根据字段排序
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				try {
					if (!grid || !sortField || !sortType) {
						throw {
							name : "ParameterError",
							message : "Parameter of 'grid'、'sortField'、'sortType' is required for method 'sortByField'"
						};
					}
					var $pageVo = grid.data("pageVo"), $attributes = grid.data("attributes") || {};
					$pageVo.sortField = sortField;
					$pageVo.oldSortField = sortField;
					$pageVo.sortOrder = sortType;
					if ("boolean" === typeof grid.data("sort-all") ? grid.data("sort-all") : $default["sort-all"]) {// 数据源排序
						$module.search.call(grid);
					} else {
						var $datas = grid.data("dataList");
						if ("desc" == $pageVo.sortOrder.toLowerCase()) {
							$datas.sort(function(a, b) { // 根据列数排序
								return $common.getValueByField(b, $pageVo.sortField) > $common.getValueByField(a, $pageVo.sortField) ? 1 : -1;
							});
						} else {
							$datas.sort(function(a, b) { // 根据列数排序
								return $common.getValueByField(a, $pageVo.sortField) > $common.getValueByField(b, $pageVo.sortField) ? 1 : -1;
							});
						}
						if ($attributes.isTree) {
							$gridOpts.showTreeData(grid, $attributes);// 显示数据（树形结构）
						} else {
							$gridOpts.showData(grid, $attributes);// 显示数据
						}
					}
				} catch (e) {
					$common.log(e);
				}
				return this;
			},
			selectPageSize : function(e) {// 选择每页显示条数时刷新列表
				var grid = $(e).closest("div.bsplus-grid"), $pageVo = grid.data("pageVo"), attributes = grid.data("attributes") || {};
				attributes.pages.find("select.bsplus-select").each(function() {
					if ($(this).val() != e.value) {
						$(this).select(e.value, false);
					}
				});
				$pageVo.pageSize = e.value;
				$pageVo.currentPage = 1;
				$module.search.call(grid);
				return $module;
			},
			getSelectedNum : function() {
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					if (!grid || grid.length == 0) {
						throw {
							name : "Error",
							message : "Object not BsplusGrid"
						};
					}
				}
				try {
					var $selectRows = 0, $pages = (grid.data("attributes") || {}).pages;
					var $rows = grid.data("rows"), $obj, $inputs = grid.data("checkAllBox");
					$pages && $pages.length > 0 && ($obj = $pages.find("button[select-rows]"))
					if ($rows) {
						$rows.each(function() {
							if ($(this).data("selected")) {
								$selectRows++;
							}
						});
					}
					if ($obj && $obj.length > 0) {
						$obj.html($ct.currentlySelected + $selectRows + $ct.record);
					}
					if ($inputs) {
						if ($selectRows > 0 && $selectRows == $rows.length) {
							$checker.check.call($inputs, true);
							grid.triggerPlusEvent("onallselected", {
								data : grid.data("dataList")
							});
						} else {
							$checker.check.call($inputs, false);
						}
					}
					return $selectRows;
				} catch (e) {
					$common.log(e);
				}
				return 0;
			},
			getSelectedRows : function() {
				var $selectedRows = [], $this = null, $rows = this.data("rows");
				if ($rows) {
					$rows.each(function() {
						$this = $(this);
						if ($this.data("selected")) {
							$selectedRows.push($this);
						}
					});
				}
				return $selectedRows;
			},
			getSelectedKeys : function() {// 获取表格选中数据的主键
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					if (!grid || grid.length == 0) {
						throw {
							name : "Error",
							message : "Object not BsplusGrid"
						};
					}
				}
				try {
					var $ids = [], $this = null, $rows = grid.data("rows");
					if ($rows) {
						$rows.each(function() {
							$this = $(this);
							if ($this.data("selected")) {
								$ids.push($this.data("record")[grid.data("id-field") || $default["id-field"]]);
							}
						});
					}
					return $ids;
				} catch (e) {
					$common.log(e);
				}
				return null;
			},
			getData : function() {// 获取表格所有数据
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					if (!grid || grid.length == 0) {
						throw {
							name : "Error",
							message : "Object not BsplusGrid"
						};
					}
				}
				return grid.data("dataList");
			},
			getGridData : function() {// 获取表格所有数据
				var grid = this, list = [];
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					if (!grid || grid.length == 0) {
						throw {
							name : "Error",
							message : "Object not BsplusGrid"
						};
					}
				}
				grid.find("div.scroll-area>div.table-rows>div.table-row").each(function() {
					$(this).data("record") && (list.push($(this).data("record")));
				});
				return list;
			},
			getSelected : function() {// 获取表格选中数据数组
				var $datas = [], $this = null, $rows = this.data("rows");
				$rows.each(function() {
					$this = $(this);
					if ($this.data("selected")) {
						$this.data("record") && $datas.push($this.data("record"));
					}
				});
				return $datas;
			},
			selects : function(opts) {
				var grid = this, status = true;
				if (!grid.hasClass("bsplus-grid")) {
					return this;
				}
				status = false == opts.status ? false : true;
				var $indexs = [], $attributes = grid.data("attributes") || {}, $rows = grid.data("rows");
				if (opts && $rows && $rows.length > 0) {
					if (opts.field && opts.fieldValues) {// 根据字段选择
						if ($attributes.multiSelect) {
							$rows.each(function(index, e) {
								var $this = $(this), $record = $this.data("record"), $i = $this.data("index");
								if (opts.fieldValues.indexOf($record[opts.field]) >= 0 || opts.fieldValues.indexOf($record[opts.field].toString()) >= 0) {
									$indexs.push($i);
								}
							});
						} else {
							$rows.each(function() {
								var $this = $(this), $record = $this.data("record"), $i = $this.data("index");
								if ($this.data("selected")) {
									$module.selectRows.call(grid, [ $i ], false);
								}
								if (opts.fieldValues.indexOf($record[opts.field]) >= 0 || opts.fieldValues.indexOf($record[opts.field].toString()) >= 0) {
									$indexs.push($i);
								}
							});
						}
					} else if (opts.indexs) {// 根据序号选择
						$indexs = opts.indexs;
					}
				}
				$module.selectRows.call(grid, $indexs, status);
				return this;
			},
			hideOrShowCell : function(index, visible) {
				if (visible) {
					this.find("div.grid-body").find("div.table-head-cell[data-index='" + index + "'],div.table-cell[data-index='" + index + "']").removeClass("bsplus-hidden");
				} else {
					this.find("div.grid-body").find("div.table-head-cell[data-index='" + index + "'],div.table-cell[data-index='" + index + "']").addClass("bsplus-hidden");
				}
			},
			showAllDetails : function() {
				var $openCls = this.data("details-open-icon") || $default["details-open-icon"];
				this.find("div.detail-row").show();
				this.data("rows").data("detail-show", true).find("a.detail-btn").children("i").attr("class", $openCls);
			},
			hideAllDetails : function() {
				var $closeCls = this.data("details-close-icon") || $default["details-close-icon"];
				this.find("div.detail-row").hide();
				this.data("rows").data("detail-show", false).find("a.detail-btn").children("i").attr("class", $closeCls);
			},
			deleteDates : function(opts) {
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				try {
					var ids = $module.getSelectedKeys.call(grid);
					if (!ids || ids.length == 0) {
						bsplus.showToast({
							content : $ct.pleaseSelect
						});
						return this;
					}
					if (!opts.allowbatch && ids.length > 1) {
						bsplus.showToast({
							content : $ct.selectOnlyOne,
							backColor : "#F89406"
						});
						return this;
					}
					var $attributes = grid.data("attributes") || {};
					bsplus.confirm(opts.confirmMsg || $ct.confirmToDelete, function() {
						$attributes.bodyDiv.loading({
							msg : $ct.deleting
						});
						var obj = {};
						obj[opts.paramName] = ids.join(",");
						$.ajax({
							cache : true,
							type : "POST",
							url : opts.url,
							data : obj,
							error : function(request) {
								$common.log(request);
								$attributes.bodyDiv.unloading();// 隐藏加载提示框
								bsplus.showToast({
									content : $ct.requestError,
									backColor : "#C9302C"
								});
								return;
							},
							success : function(data) {
								$attributes.bodyDiv.unloading();// 隐藏加载提示框
								opts.data = data;
								$module.search.call(grid);// 刷新当前页
								$common.dataHandle(opts);
							}
						});
					});
				} catch (e) {
					$common.log(e);
				}
				return this;
			},
			removeData : function() {// 清除缓存数据
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				try {
					if (!grid) {
						return this;
					}
					var $attributes = grid.data("attributes") || {};
					if ($attributes) {
						if ($attributes.headCells) {
							$attributes.headCells.removeData();// 清除列data
						}
					}
					grid.find("button[data-action]").removeData();
					grid.data("rows").removeData();// 清除行data
					grid.removeData();// 清除grid data
					return this;
				} catch (e) {
					$common.log(e);
				}
				return this;
			},
			destroy : function() {// 销毁控件，清除缓存数据
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				try {
					if (!grid || grid.length == 0) {
						return false;
					}
					$module.removeData(grid);// 清除grid data
					grid.unbind("click").unbind("dblclick").remove();
					return true;
				} catch (e) {
					$common.log(e);
				}
				return false;
			},
			reset : function() {// 重置控件为初始状态（init之前状态）
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					return $module;
				}
				var $gridBody = grid.find("div.grid-body"), $tableHead = $gridBody.find("div.table-head"), $tableBody = $gridBody.find("div.table-body"), $tableFoot = $gridBody.find("div.table-foot"), $scrollBox = $gridBody.find("div.table-scroll-box"), $gridSummary = $gridBody.find("div.grid-summary"), $gridFooter = $gridBody.find("div.grid-footer");
				$tableHead.length > 0 && $tableHead.remove();
				$tableBody.length > 0 && $tableBody.remove();
				$tableFoot.length > 0 && $tableFoot.remove();
				$scrollBox.length > 0 && $scrollBox.remove();
				$gridSummary.length > 0 && $gridSummary.remove();
				$gridFooter.length > 0 && $gridFooter.remove();
				grid.removeData();
				return this;
			},
			selectRows : function(rows, selected) {
				var grid = this;
				if (!grid.hasClass("bsplus-grid")) {
					grid = this.find("div.bsplus-grid");
				}
				if (!grid || grid.length == 0) {
					throw {
						name : "Error",
						message : "Object not BsplusGrid"
					};
				}
				var attributes = grid.data("attributes") || {}, $row, $ri, $selectedCls;
				selected = false == selected ? false : true;
				if (!attributes || !rows || rows.length == 0) {
					return this;
				}
				$selectedCls = attributes.selectedCls;
				for (var i = 0, a = rows.length; i < a; i++) {
					$ri = rows[i];
					$row = attributes.bodyContent.find("div.scroll-area div.table-row[data-index='" + $ri + "']");
					if (selected) {
						if (attributes.multiSelect) {
							$row.data("selected", true);
							$checker.check.call(attributes.bodyContent.find("div.table-row[data-index='" + $ri + "']").addClass($selectedCls).find("div.table-cell[data-type='select'] input:checkbox"), true);
						} else {// 单选移除其它选中选项
							$checker.check.call(attributes.bodyContent.find("div.table-row").removeClass($selectedCls).data("selected", false).find("div.table-cell[data-type='select'] input:radio"), false);
							$checker.check.call(attributes.bodyContent.find("div.table-row[data-index='" + $ri + "']").data("selected", true).addClass($selectedCls).find("div.table-cell[data-type='select'] input:radio"), true);
						}
					} else {
						$row.data("selected", false);
						$checker.check.call(attributes.bodyContent.find("div.table-row[data-index='" + $ri + "']").removeClass($selectedCls).find("div.table-cell[data-type='select'] input:checkbox,div.table-cell[data-type='select'] input:radio"), false);
					}
					if (attributes.isTree && attributes.multiSelect) {// 联动选择子节点
						$gridOpts.selectChild(grid, $row, attributes);
						$gridOpts.selectParent(grid, $row, attributes);
					}
				}
				$module.getSelectedNum.call(grid);
				return this;
			},
			setData : function(data) {
				$gridOpts.setData(this, data);
				return this;
			},
			setUrl : function(url, load) {
				load = false == load ? false : true;
				if (!this || !url || !this.hasClass("bsplus-grid")) {
					return this;
				}
				this.data("url", url).data("localDatas", null);
				if (this.data("pageVo")) {
					this.data("pageVo").currentPage = 1;
				}
				load && $module.search.call(this);
				return this;
			}
		};
	}($, BsplusCommon);
	(function() {
		BsplusCommon.init();// Common初始化
	}());
	var isModule = {
		checker : "object" == typeof BsplusChecker,
		drag : "object" == typeof BsplusDrag,
		modal : "object" == typeof BsplusModal,
		select : "object" == typeof BsplusSelect,
		grid : "object" == typeof BsplusGrid,
		form : "object" == typeof BsplusForm,
		tab : "object" == typeof BsplusTab,
		date : "object" == typeof BsplusDate,
		bswitch : "object" == typeof BsplusSwitch
	};
	String.prototype.startWith = function(startStr) {
		return this.indexOf(startStr) == 0;
	}
	String.prototype.isUrl = function() {
		return this.startWith("http://") || this.startWith("https://");
	}
	String.prototype.endWith = function(endStr) {
		var d = this.length - endStr.length;
		return (d >= 0 && this.lastIndexOf(endStr) == d);
	}
	Date.prototype.format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"H+" : this.getHours(),
			"m+" : this.getMinutes(),
			"i+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	Array.prototype.clone = function() {
		var newArr = new Array();
		for (var i = 0; i <= this.length - 1; i++) {
			var itemi = this[i];
			if (itemi.length && itemi.push)
				itemi = itemi.clone();
			else if (typeof (itemi) == "object")
				itemi = BsplusCommon.objClone(itemi);
			newArr.push(itemi);
		}
		return newArr;
	}
	Array.prototype.unique = function() {
		var n = [];
		for (var i = 0; i < this.length; i++) {
			if (n.indexOf(this[i]) == -1)
				n.push(this[i]);
		}
		return n;
	}
	var Plugin = function() {
		return {
			name : "Plugin",
			init : function(module, extend) {
				extend = false == extend ? false : true;
				var $methods = [];
				module = module || {};
				if ("function" == typeof module.initialize) {
					module.initialize.call(module);
					delete module.initialize;
				}
				for ( var n in module) {
					if ("function" == typeof module[n]) {
						$methods.push(n);
					}
				}
				module.setMethod = function(methods) {
					if (methods && Object.prototype.toString.apply(methods) === '[object Array]') {
						$methods = $methods.concat(methods);
					} else if ("string" == typeof methods) {
						$methods.push(methods);
					}
				};
				module.getMethod = function() {
					return $methods;
				};
				if (extend) {
					module.extend = function(o) {
						if ("object" == typeof o) {
							for ( var n in o) {
								this.setMethod(n);
								module[n] = o[n];
							}
						}
					};
				}
				return this;
			},
			initPlugins : function() {
				isModule.checker && BsplusChecker.init.apply(this, arguments);
				isModule.date && BsplusDate.init.apply(this, arguments);
				isModule.grid && BsplusGrid.init.apply(this, arguments);
				isModule.select && BsplusSelect.init.apply(this, arguments);
				isModule.bswitch && BsplusSwitch.init.apply(this, arguments);
			}
		}
	}();
	var bsplusCall = function($this, module, args) {
		if (!module) {
			return;
		}
		var $method, $ms = module.getMethod();
		if (args.length > 0) {
			$method = args[0];
			args = Array.prototype.slice.call(args);
			args.shift();
			if ($ms && $ms.indexOf($method) >= 0) {
				return module[$method].apply($this, args);
			} else
				BsplusCommon.log("The method of " + $method + " is undefined for module " + module.name);
		} else {
			BsplusCommon.log("The method name can not be empty!");
		}
	};
	window.bsplus = function($, $common) {
		return {
			name : "bsplus",
			path : $common.getPath(),
			browser : $common.browser,
			getScrollBarWidth : function() {
				return $common.getScrollBarWidth();
			},
			openModal : function() {
				return BsplusModal.open.apply(this, arguments);
			},
			refrshModal : function() {
				return BsplusModal.refrsh.apply(this, arguments);
			},
			closeModal : function() {
				return BsplusModal.close.apply(this, arguments);
			},
			destroyModal : function() {
				return BsplusModal.destroy.apply(this, arguments);
			},
			alert : function() {
				return BsplusMessage.alert.apply(this, arguments);
			},
			showToast : function() {
				return BsplusMessage.showToast.apply(this, arguments);
			},
			hideLastToast : function() {
				return BsplusMessage.hideLastToast.apply(this, arguments);
			},
			confirm : function() {
				return BsplusMessage.confirm.apply(this, arguments);
			},
			insertContent : function() {
				return BsplusMessage.insertContent.apply(this, arguments);
			},
			submitData : function() {
				return BsplusCommon.submitData.apply(this, arguments);
			},
			showPhotos : function() {
				return BsplusPhotos.show.apply(this, arguments);
			},
			hidePhotos : function() {
				return BsplusPhotos.hide.apply(this, arguments);
			},
			getFormatData : function() {
				return BsplusCommon.getFormatData.apply(this, arguments);
			},
			extend : function(module, o) {
				var $module = null;
				switch (module) {
				case 'checker':
					$module = BsplusChecker;
					break;
				case 'drag':
					$module = BsplusDrag;
					break;
				case 'modal':
					$module = BsplusModal;
					break;
				case 'select':
					$module = BsplusSelect;
					break;
				case 'grid':
					$module = BsplusGrid;
					break;
				case 'form':
					$module = BsplusForm;
					break;
				case 'tab':
					$module = BsplusTab;
					break;
				case 'date':
					$module = BsplusDate;
					break;
				default:
					$module = null;
					break;
				}
				if ($module) {
					if ("function" == typeof $module.extend) {
						$module.extend(o);
					} else {
						$common.log("The module of " + module + " is can not be extended");
					}
				} else {
					$common.log("The module of " + module + " is undefined");
				}
			}
		}
	}($, BsplusCommon);
	Plugin.init(BsplusMessage).init(BsplusPhotos).init(isModule.checker ? BsplusChecker : null, false).init(isModule.drag ? BsplusDrag : null, false).init(isModule.modal ? BsplusModal : null).init(isModule.select ? BsplusSelect : null).init(isModule.grid ? BsplusGrid : null).init(isModule.form ? BsplusForm : null).init(isModule.tab ? BsplusTab : null).init(isModule.date ? BsplusDate : null).init(isModule.bswitch ? BsplusSwitch : null);
	$.fn.extend({
		listenPlusEvent : function(events) {
			return BsplusEvent.listen.call(this, events);
		},
		triggerPlusEvent : function(type, data) {
			return BsplusEvent.trigger.call(this, type, data || null);
		},
		BsplusChecker : function() {
			return bsplusCall(this, isModule.checker && BsplusChecker, arguments);
		},
		BsplusSelect : function() {
			return bsplusCall(this, isModule.select && BsplusSelect, arguments);
		},
		BsplusSwitch : function() {
			return bsplusCall(this, isModule.bswitch && BsplusSwitch, arguments);
		},
		BsplusDrag : function() {
			return bsplusCall(this, isModule.drag && BsplusDrag, arguments);
		},
		BsplusGrid : function() {
			return bsplusCall(this, isModule.grid && BsplusGrid, arguments);
		},
		BsplusTab : function() {
			return bsplusCall(this, isModule.tab && BsplusTab, arguments);
		},
		BsplusDate : function() {
			return bsplusCall(this, isModule.date && BsplusDate, arguments);
		},
		loading : function() {
			return BsplusMessage.loading.apply(this, arguments);
		},
		unloading : function() {
			return BsplusMessage.unloading.apply(this, arguments);
		},
		addTab : function() {
			return BsplusTab.addTab.apply(this, arguments);
		},
		closeTab : function(code) {
			return BsplusTab.closeTab(this, code);
		},
		initPlugins : function() {
			return Plugin.initPlugins.apply(this, arguments);
		},
		getSelectedKeys : function() {// 获取表格选中数据的主键
			return BsplusGrid.getSelectedKeys.apply(this, arguments);
		},
		showAllDetails : function() {
			return BsplusGrid.showAllDetails.apply(this, arguments);
		},
		hideAllDetails : function() {
			return BsplusGrid.hideAllDetails.apply(this, arguments);
		},
		getSelected : function() {// 获取表格、下拉菜单等选中数据
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.getSelected.apply(this, arguments);
			} else if (this.hasClass("bsplus-select")) {
				return BsplusSelect.getSelected.apply(this, arguments);
			}
		},
		getSelectedRows : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.getSelectedRows.apply(this, arguments);
			}
		},
		check : function() {
			return BsplusChecker.check.apply(this, arguments);
		},
		setData : function(datas) {
			if (this.is("form")) {
				return BsplusForm.setData.apply(this, arguments);
			} else if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.setData.apply(this, arguments);
			}
		},
		getData : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.getData.apply(this, arguments);
			}
		},
		getGridData : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.getGridData.apply(this, arguments);
			}
		},
		select : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.selects.apply(this, arguments);
			} else if (this.hasClass("bsplus-select")) {
				return BsplusSelect.select.apply(this, arguments);
			}
		},
		setUrl : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.setUrl.apply(this, arguments);
			} else if (this.hasClass("bsplus-select")) {
				return BsplusSelect.setUrl.apply(this, arguments);
			}
		},
		rebuild : function() {
			if (this.hasClass("bsplus-select")) {
				return BsplusSelect.rebuild.apply(this, arguments);
			}
		},
		search : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.search.apply(this, arguments);
			}
		},
		reload : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.reload.call(this);
			}
		},
		scrolls : function() {
			if (this.hasClass("bsplus-grid") && this.is(":visible")) {
				return BsplusGrid.scroll.apply(this, arguments);
			}
		},
		disabled : function() {
			if (this.is("input:checkbox,input:radio")) {
				return BsplusChecker.disabled.call(this);
			}
		},
		enabled : function() {
			if (this.is("input:checkbox,input:radio")) {
				return BsplusChecker.enabled.call(this);
			}
		}
	});
}(jQuery));
$(window).load(function() {
	$("input:checkbox,input:radio").BsplusChecker("init");
	$("input.bsplus-switch:checkbox").BsplusSwitch("init");
	$("select.bsplus-select").BsplusSelect("init");
	$("div.bsplus-grid").BsplusGrid("init");
	$("input.bsplus-date").BsplusDate("init");
	$(".bsplus-drag").BsplusDrag("init");
	$(window).resize(function() {
		$("div.bsplus-grid").BsplusGrid("tableLayOut");
	});
});