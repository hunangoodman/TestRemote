var BsplusSelect = function($, $common, $checker) {// 多选/单选按钮组
	var $module, $dataField = $common.dataField, $activeSelect, $constants = $common.constants, clientHeight = document.body.clientHeight;
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
							select.val($multiple ? $values : $values.join($separator)).triggerPlusEvent("onitemclick", {
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
	var clickItem = function(select, $target) {
		var $controller = select.closest("div.selecter"), $selectText = $controller.find("input.select-text"), $ul = $controller.find("ul.select-list"), $event, $element, $multiple = $common.exist(select.attr("multiple")), $selectedCls = select.data("selected-cls") || "selected", $separator = select.data("separator") || ",", $empty = select.data("empty-option"), $lis = $ul.children("li.select-item"), $allLis = $ul.children("li.select-item,li.select-checkall,li.empty-item"), $length = $lis.length, $filter = $ul.children("li.select-filter").find("input.select-filter-text");
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
				var $this = $(this), $direction = $this.data("direction") || "auto", $showBtn = false == $this.data("show-btn") ? false : true, $selectGroup = $('<div class="select-group"><input type="button" class="form-control select-text" readonly="readonly" placeholder="' + ($this.data("placeholder") || "") + '"/>' + ($showBtn ? '<span class="select-btn"><i class="fa direction"></i></span>' : '<i class="fa direction"></i>') + '</div>'), $ul = $("<ul class='select-list'></ul>");
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
				} else if (!$common.isBlank($url, true)) {
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
								$content = $common.getValueByField($record, $textField);
								$thisvalue = $common.getValueByField($record, $valueField);
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
							$content = $common.getValueByField($record, $textField);
							$thisvalue = $common.getValueByField($record, $valueField);
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
				}
				$this.triggerPlusEvent("ondataload", {// 数据加载完成事件
					data : $dataList
				});
				if ($value && $this.attr("multiple")) {
					try {
						$value = eval("(" + $value + ")");
					} catch (e) {
						$common.log(e);
					}
				}
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
				var $this = $(this),texts=[],$separator = $this.data("separator") || ",", $selectText, $ul, $li, $optgroup, $controller, $selectedCls = $this.data("selected-cls") || "selected", $selectedCls, $multiple = $common.exist($this.attr("multiple")), $showChecker = $this.data("show-checker");
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
						$checker.init.call($ul.find("input:checkbox,input:radio"), {
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
				$maxHeight = $liH > $maxHeight ? $maxHeight : $liH;
				direction = clientHeight - (selecter.offset().top - $(window).scrollTop()) - 34 > $maxHeight ? "down" : "up";
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
		select : function(values) {
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
						clickItem($select, $this);
						if (!$multiple) {
							return false;
						}
					}
				});
			} else {
				clickItem($select, $selecter.find("li.empty-item"));
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
}($, Common, BsplusChecker);