;
(function($) {
	"use strict";// 严格模式
	var isModule = {
		checker : "object" == typeof BsplusChecker,
		drag : "object" == typeof BsplusDrag,
		modal : "object" == typeof BsplusModal,
		select : "object" == typeof BsplusSelect,
		grid : "object" == typeof BsplusGrid,
		form : "object" == typeof BsplusForm,
		tab : "object" == typeof BsplusTab,
		date : "object" == typeof BsplusDate
	};
	String.prototype.endWith = function(endStr) {// 字符串判断
		var d = this.length - endStr.length;
		return (d >= 0 && this.lastIndexOf(endStr) == d);
	}
	Date.prototype.format = function(fmt) {// 日期格式化
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours(), // 小时
			"H+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"i+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
		// 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	Array.prototype.clone = function() {// 为数组添加克隆自身方法，使用递归可用于多级数组
		var newArr = new Array();
		for (var i = 0; i <= this.length - 1; i++) {
			var itemi = this[i];
			if (itemi.length && itemi.push)
				itemi = itemi.clone();// 数组对象，进行递归
			else if (typeof (itemi) == "object")
				itemi = Common.objClone(itemi);// 非数组对象，用上面的objClone方法克隆
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
	var Plugin = function() {// 所有插件基类
		return {
			name : "Plugin",
			init : function(module, extend) {
				extend = false == extend ? false : true;
				var $methods = [];
				module = module || {};
				if ("function" == typeof module.initialize) {
					module.initialize.call(module);// 模块初始化
					delete module.initialize;// 防止用户调用将this指向其它对象
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
				if (extend) {// 模块是否可扩展
					module.extend = function(o) {// 模块扩展
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
			args = Array.prototype.slice.call(args);// 将args转为数组
			args.shift();// 删除第一个元素
			if ($ms && $ms.indexOf($method) >= 0) {
				return module[$method].apply($this, args);
			} else
				Common.log("The method of " + $method + " is undefined for module " + module.name);
		} else {
			Common.log("The method name can not be empty!");
		}
	};
	window.bsplus = function($, $common) {
		return {
			name : "bsplus",
			path : $common.path,
			browser : $common.browser,
			openModal : function(o) {
				return BsplusModal.open(o);
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
			submitData : function() {
				return Common.submitData.apply(this, arguments);
			},
			extend : function(module, o) {// 插件扩展
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
	}($, Common);
	// 模块(初始化)继承Plugin,初始化方法
	Plugin.init(BsplusMessage)// 消息模块
	.init(isModule.checker ? BsplusChecker : null, false)// 选择框，不允许扩展
	.init(isModule.drag ? BsplusDrag : null, false)// 拖拽模块，不允许扩展
	.init(isModule.modal ? BsplusModal : null)// 模态框模块
	.init(isModule.select ? BsplusSelect : null)// 下拉选择模块
	.init(isModule.grid ? BsplusGrid : null)// 数据表格模块
	.init(isModule.form ? BsplusForm : null)// 表单模块
	.init(isModule.tab ? BsplusTab : null)// tab模块
	.init(isModule.date ? BsplusDate : null);// date模块
	$.fn.extend({
		listenPlusEvent : function(events) {// 事件监听
			return BsplusEvent.listen.call(this, events);
		},
		triggerPlusEvent : function(type, data) {// 事件触发
			return BsplusEvent.trigger.call(this, type, data || null);
		},
		BsplusChecker : function() {
			return bsplusCall(this, isModule.checker && BsplusChecker, arguments);
		},
		BsplusSelect : function() {
			return bsplusCall(this, isModule.select && BsplusSelect, arguments);
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
		initGrid : function() {
			return BsplusGrid.init.apply(this, arguments);
		},
		getSelectedKeys : function() {// 获取表格选中数据的主键
			return BsplusGrid.getSelectedKeys.apply(this, arguments);
		},
		getSelectedNum : function() {// 获取表格选中数据的主键
			return BsplusGrid.getSelectedNum.apply(this, arguments);
		},
		getSelected : function() {// 获取表格、下拉菜单等选中数据
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.getSelected.apply(this, arguments);
			} else if (this.hasClass("bsplus-select")) {
				return BsplusSelect.getSelected.apply(this, arguments);
			}
		},
		getSelectedRows : function() {
			return BsplusGrid.getSelectedRows.apply(this, arguments);
		},
		check : function() {
			return BsplusChecker.check.apply(this, arguments);
		},
		setData : function(datas) {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.setData.apply(this, arguments);
			}
		},
		getData : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.getData.apply(this, arguments);
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
		search : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.search.apply(this, arguments);
			}
		},
		sortByField : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.sortByField.apply(this, arguments);
			}
		},
		reload : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.reload.call(this);
			} else if (this.hasClass("bsplus-select")) {
				return BsplusSelect.reload.call(this);
			}
		},
		build : function() {
			if (this.hasClass("bsplus-select")) {
				return BsplusSelect.build.call(this, arguments);
			}
		},
		rebuild : function() {
			if (this.hasClass("bsplus-select")) {
				return BsplusSelect.rebuild.call(this, arguments);
			}
		},
		showdropdown : function() {
			if (this.hasClass("bsplus-select")) {
				return BsplusSelect.showdropdown.call(this);
			}
		},
		hidedropdown : function() {
			if (this.hasClass("bsplus-select")) {
				return BsplusSelect.hidedropdown.call(this);
			}
		},
		refresh : function() {
			if (this.hasClass("bsplus-select")) {
				return BsplusSelect.refresh.call(this);
			}
		},
		destroy : function() {
			if (this.hasClass("bsplus-grid")) {
				return BsplusGrid.destroy.call(this);
			}
		}
	});
}(jQuery));
$(window).load(function() {
	$("input:checkbox,input:radio").BsplusChecker("init");// 初始化checkbox/radio
	$("select.bsplus-select").BsplusSelect("init");// 初始化checkbox/radio
	$("div.bsplus-grid").BsplusGrid("init");// 初始化表格
	$("input.bsplus-date").BsplusDate("init");// 初始化日期控件
	$(".bsplus-drag").BsplusDrag("init");// 初始化拖拽控件
	$(window).resize(function() {// 窗体改变时重新布局表格
		$("div.bsplus-grid").BsplusGrid("tableLayOut");// 插件初始化
	});
});