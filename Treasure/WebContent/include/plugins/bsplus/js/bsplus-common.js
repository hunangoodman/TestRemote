;
(function() {
	"use strict";// 严格模式
	window.BsplusCommon = function() {// 框架通用方法
		var $BsplusCommon;
		return {
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
				noOperate : "无需保存！",
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
				"sort-db" : false,// 是否允许数据库排序, 默认排序当前页
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
			initActionBtn : function(button) {// 初始化功能按钮
				if (!button || button.data("inited")) {
					return this;
				}
				var $action = button.data("action"), $options = button.data("options"), $param = null;
				button.listenPlusEvent({
					beforeaction : button.data("beforeaction") || null
				});
				try {
					if (!$BsplusCommon.isBlank($options, true)) {
						$options = eval('(' + $options + ')');
					}
					button.data("options", $options);
				} catch (e) {
					$BsplusCommon.log(e);
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
					var opts = $.extend({}, $BsplusCommon.dataField, options), backColor = "#2F96B4";
					if (opts.data) {
						opts.code = $BsplusCommon.getValueByField(opts.data, opts.codeField);
						opts.msg = $BsplusCommon.getValueByField(opts.data, opts.msgField);
						switch (opts.code) {
						case "true":
							backColor = "#51A351";
							opts.msg = opts.msg || $BsplusCommon.constants.operateSuccess;
							break;
						case "false":
							backColor = "#d9534f";
							opts.msg = opts.msg || $BsplusCommon.constants.operateFailure;
							break;
						case "error":
							backColor = "#C9302C";
							opts.msg = opts.msg || $BsplusCommon.constants.operateError;
							break;
						case "info":
							backColor = "#2F96B4";
							opts.msg = opts.msg || $BsplusCommon.constants.noOperate;
							break;
						default:
							opts.msg = $BsplusCommon.constants.operateFailure;
							break;
						}
					}
					var $toastOptions = {
						content : opts.msg,
						backColor : backColor
					};
					bsplus.showToast($.extend({}, $toastOptions, opts.toastOptions));
					"function" === typeof opts.onHandleSuccess && opts.onHandleSuccess(opts);
				} catch (e) {
					$BsplusCommon.log(e);
				}
				return this;
			},
			clickListener : function() {
				$(window).click(function(e) {
					var $event = e || window.event, $element = $($event.target || $event.srcElement), $li = null, $array = [ 'reset', 'clear', 'openModal' ], $events, $form, $bv;
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
									$BsplusCommonSelectMenu.clickItem($li);
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
						if (!$BsplusCommon.isBlank($action, true)) {
							if (!$button.data("inited")) {
								$BsplusCommon.initActionBtn($button);
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
									$BsplusCommon.log(e);
								}
							}
							switch ($action) {
							case "reset":
								e.preventDefault();
								var $dataformId = $button.data("formId");
								if (!$BsplusCommon.isBlank($dataformId, true)) {
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
										$BsplusCommon.log(e);
									}
								}
								break;
							case "clear":
								e.preventDefault();
								var $dataformId = $button.data("formId");
								if (!$BsplusCommon.isBlank($dataformId, true)) {
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
										$BsplusCommon.log(e);
									}
								}
								break;
							case "openModal":
								e.preventDefault();
								$BsplusCommonModal.open($options);
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
							}
						}());
					default:
						break;
					}
					if (!$element.hasClass("config-container") && $element.closest("div.config-container").length == 0) {
						$("div.config-container").hide();
					}
				});
			},
			init : function() {
				$BsplusCommon = this;
				if ("object" == typeof BsplusConfig) {
					"object" == typeof BsplusConfig.gridConfig && ($BsplusCommon.gridConfig = $.extend($BsplusCommon.gridConfig, BsplusConfig.gridConfig));
					"object" == typeof BsplusConfig.dataField && ($BsplusCommon.dataField = $.extend($BsplusCommon.dataField, BsplusConfig.dataField));
					"object" == typeof BsplusConfig.constants && ($BsplusCommon.constants = $.extend($BsplusCommon.constants, BsplusConfig.constants));
					"object" == typeof BsplusConfig.checkerConfig && ($BsplusCommon.checkerConfig = $.extend($BsplusCommon.checkerConfig, BsplusConfig.checkerConfig));
					"object" == typeof BsplusConfig.loadingConfig && ($BsplusCommon.loadingConfig = $.extend($BsplusCommon.loadingConfig, BsplusConfig.loadingConfig));
					"object" == typeof BsplusConfig.alertConfig && ($BsplusCommon.alertConfig = $.extend($BsplusCommon.alertConfig, BsplusConfig.alertConfig));
					"object" == typeof BsplusConfig.toastConfig && ($BsplusCommon.toastConfig = $.extend($BsplusCommon.toastConfig, BsplusConfig.toastConfig));
					"object" == typeof BsplusConfig.submitParam && ($BsplusCommon.submitParam = $.extend($BsplusCommon.submitParam, BsplusConfig.submitParam));
				}
				try {
					var userAgent = window.navigator.userAgent.toLowerCase();
					if (/(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test(userAgent)) {
						$BsplusCommon.browser = {
							appname : RegExp.$1,
							version : RegExp.$2
						};
					} else if (/version\D+(\d[\d.]*).*safari/.test(userAgent)) { // safari
						$BsplusCommon.browser = {
							appname : 'safari',
							version : RegExp.$2
						};
					} else {
						$BsplusCommon.browser = {
							appname : 'unknown',
							version : 0
						};
					}
				} catch (e) {
					$BsplusCommon.log(e);
				}
				$BsplusCommon.clickListener();// 监听点击事件
			},
			getPath : function() {
				$BsplusCommon = this;
				if (!$BsplusCommon.path) {
					try {// 路径初始化
						var curWwwPath = window.document.location.href;
						var pathName = window.document.location.pathname;
						$BsplusCommon.path = curWwwPath.substring(0, curWwwPath.indexOf(pathName)) + pathName.substring(0, pathName.substr(1).indexOf('/') + 1) + "/";
					} catch (e) {
					}
				}
				return $BsplusCommon.path;
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
				if ("undefined" === typeof obj || null == obj || "" == obj)
					return true;
				if ("string" === typeof obj && trim && "" == obj.trim())
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
			absolutePosition : function(e) { // 获取元素绝对位置
				var t = e.offsetTop;
				var l = e.offsetLeft;
				while (e = e.offsetParent) {
					t += e.offsetTop;
					l += e.offsetLeft;
				}
				return {
					left : l,
					top : t
				};
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
					$BsplusCommon.log(e);
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
							$BsplusCommon.log(e);
						},
						success : function(data) {
							if (data) {
								if ("object" === typeof data) {// 查询数据成功，且数据不为空
									var listField = options.listField || $BsplusCommon.dataField.listField;
									if (listField) {
										$dataList = $BsplusCommon.getValueByField(data, listField);
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
					$BsplusCommon.log(e);
				}
				return null;
			},
			getBodyHtml : function(html) {
				try {
					if (!$BsplusCommon.isBlank(html, true)) {
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
					$BsplusCommon.log(e);
				}
				return null;
			},
			getValueByField : function(data, field) {// 根据字段获取值
				var $fileds, $value;
				try {
					if (!$BsplusCommon.isBlank(field, true)) {
						if (field.indexOf(".") > 0) {
							$fileds = field.split(".");
							if ($fileds.length > 0) {
								$value = data[$fileds[0]];
								if ($fileds.length > 1) {
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
					$BsplusCommon.log(e);
				}
				return $value;
			},
			getScrollBarWidth : function() {// 获取滚动条宽度
				if ($BsplusCommon.browser.appname.indexOf("ie") >= 0) {
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
				return $BsplusCommon.getFormatDataByInputs(true == justVisible ? f.find("input[name]:visible,textarea[name]:visible,select[name]:visible") : f.find("input[name],textarea[name],select[name]"), fl);
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
							if ($this.hasClass("select2") || $this.hasClass("$BsplusCommon-multiselect") || $this.hasClass("$BsplusCommon-check-list")) {
								if ($value) {
									var $dataType = $this.data("data-type") || "string";
									if ($BsplusCommon.isArray($value)) {
										$value = $value.unique();// 去重
										if ("string" == $dataType) {
											$value = $value.join(",");
										}
									} else if ("string" === typeof $value && "array" == $dataType) {
										$value = $value.split($this.data("separator") || ",");
									}
								} else {
									return true;
								}
							}
							if ($this.is("input:checkbox,input:radio")) {
								if (!this.checked) {
									return true;// false时相当于for的break,
									// 如果return
									// true,就相当于for的continure
								}
							}
							if (!$BsplusCommon.isBlank($name, true)) {
								if ($name.indexOf(".") > 0) {// 深度封装
									$fileds = $name.split(".");
									if ($fileds.length > 0) {
										var $o = $BsplusCommon.creatObjByName(data, $fileds[0]);
										if ($fileds.length > 1) {
											for (var i = 1, a = $fileds.length - 1; i < a; i++) {
												$o = $BsplusCommon.creatObjByName($o, $fileds[i]);
											}
										}
										$BsplusCommon.creatObjByName($o, $fileds[$fileds.length - 1], $value || fl || "");
									}
								} else {
									$BsplusCommon.creatObjByName(data, $name, $value || fl || "");
								}
							}
						});
						return data;
					}
				} catch (e) {
					$BsplusCommon.log(e);
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
					if ($BsplusCommon.isArray(obj)) {
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
					var opts = $.extend({}, $BsplusCommon.submitParam, options);
					if ($BsplusCommon.isBlank(opts.url, true)) {
						throw new Error("URL" + $BsplusCommon.constants.canNotBeEmpty);
					}
					opts.blockTarget && opts.blockTarget.loading({
						boxed : true,
						msg : opts.msg || $BsplusCommon.constants.submitting
					});
					$.ajax($.extend({
						error : function(request) {
							$BsplusCommon.log(request);
							if ("function" === typeof opts.onError) {
								opts.onError(request);
							} else {
								opts.blockTarget && opts.blockTarget.unloading();
								bsplus.showToast({
									content : $BsplusCommon.constants.requestError,
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
								$BsplusCommon.dataHandle(opts);
							}
						}
					}, opts));
				} catch (e) {
					$BsplusCommon.log(e);
				}
			}
		}
	}();
	BsplusCommon.init();// BsplusCommon初始化
}());