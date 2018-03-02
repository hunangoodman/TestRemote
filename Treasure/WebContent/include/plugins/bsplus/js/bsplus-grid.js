var BsplusGrid = function($, $common, $checker) {
	"use strict";
	var $module = null, $checker = "object" == typeof BsplusChecker ? BsplusChecker : null, $ct = $common.constants, $path = $common.getPath(), $default = $common.gridConfig, $gridOpts = {
		agcl : function(grid, attributes) {// 事件托管
			var $event, $element, $time, $selectedCls = attributes.selectedCls, $events = grid.data("events"), array = [ 'search', 'add', 'edit', 'delete', 'config' ];
			grid.unbind("click").click(function(e) {
				if (!grid.data("inited")) {// 如果尚未初始化，则先初始化
					$module.init.apply(grid);
					return $module;
				}
				$event = e || window.event, $element = $($event.target || $event.srcElement);
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
						var $action = $button.data("action");
						if (!$common.isBlank($action, true)) {
							if ($button.hasClass("pager-btn")) {
								e.preventDefault();
								$gridOpts.gotoPage(grid, attributes, $action);// 分页按钮点击
							} else {
								e.preventDefault();
								$gridOpts.doAction(grid, $button, $action);// 功能按钮点击
							}
						}
						$button.blur();// 失去焦点
						break;
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
								if ("function" === typeof $events.ongridedit) {
									var $type = grid.data("edit-when");
									if (1 == $type || "rowclick" == $type) {
										BsplusGrid.editRow($row, $events.ongridedit({
											type : "ongridedit",
											target : grid
										}));
									} else if (3 == $type || "cellclick" == $type) {
										BsplusGrid.editCell($cell, $events.ongridedit({
											type : "ongridedit",
											target : grid
										}));
									}
								}
							}, attributes.clickcelltime);
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
					case "td":
						var $cell = $element;
						if ("td" != $element[0].tagName.toLowerCase()) {
							$cell = $element.closest("td");
						}
						var $row = $cell.closest("tr[role='row']");
						grid.triggerPlusEvent("oncelldblclick", {
							row : $row,
							cell : $cell,
							record : $row.data("record"),
							clickTarget : $element
						});
						if ("function" === typeof $events.ongridedit && "td" == $element[0].tagName.toLowerCase()) {
							var $type = grid.data("edit-when");
							if (2 == $type || "rowdblclick" == $type) {
								BsplusGrid.editRow($row, $events.ongridedit({
									type : "ongridedit",
									target : grid
								}));
							} else if (4 == $type || "celldblclick" == $type) {
								BsplusGrid.editCell($cell, $events.ongridedit({
									type : "ongridedit",
									target : grid
								}));
							}
						}
					case "tr":
						var $row = $element.closest("tr[role='row']");
						grid.triggerPlusEvent("onrowdblclick", {
							selected : $row.data("selected"),
							record : $row.data("record"),
							row : $row,
							clickTarget : $element
						});
						break;
					default:
						break;
					}
				}
			});
			if (attributes.showPager) {
				var $pages = attributes.page, $input = $pages.find("input[current-page]"), $currentPage = 1;
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
				});
				$input.keydown(function(e) {// 回车跳转页面
					if (e.keyCode == 13) {
						$currentPage = $input.val();
						if (!$common.isBlank($currentPage, true)) {
							$currentPage = parseInt($currentPage);
						}
						grid.data("pageVo").currentPage = $currentPage;
						$module.search.call(grid);
					}
				});
				$input.change(function(e) {// 只改变时跳转页面
					$currentPage = $input.val();
					if (!$common.isBlank($currentPage, true)) {
						$currentPage = parseInt($currentPage);
					}
					grid.data("pageVo").currentPage = $currentPage;
					$module.search.call(grid);
				});
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
			var attributes = grid.data("attributes"), $openCls = grid.data("details-open-icon") || $default["details-open-icon"], $closeCls = grid.data("details-close-icon") || $default["details-close-icon"];
			as.each(function() {
				var $this = $(this), $ci = $this.closest("div.table-row").data("index"), $detailRows = attributes.bodyContent.find("div.detail-row[data-index='" + $ci + "']"), $row = attributes.bodyContent.find("div.scroll-area div.table-row[data-index='" + $ci + "']");
				if ($row.data("detail-show")) {
					$detailRows.hide();
					$row.data("detail-show", false);
					$this.children("i").attr("class", $closeCls);
					try {
						grid.triggerPlusEvent("ondetailclose", {
							detailRows : $detailRows,
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
										detailRows : $odrs,
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
							detailRows : $detailRows,
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
			var attributes = grid.data("attributes"), rcls = grid.data("row-hover-cls") || "hover-cls", ccls = grid.data("cell-hover-cls");
			grid.unbind("mouseover").mouseover(function(e) {
				var $event = e || window.event, $element = $($event.target || $event.srcElement), $cell = null;
				if ($element.length > 0) {
					if ($element.hasClass("table-cell")) {
						$cell = $element;
					} else {
						$cell = $element.closest("div.table-cell");
					}
					ccls && attributes.bodyContent.find("div.table-cell").removeClass(ccls) && attributes.bodyContent.find("div.table-cell[data-index='" + $cell.data("index") + "']").addClass(ccls);
					rcls && attributes.bodyContent.find("div.table-row").removeClass(rcls) && attributes.bodyContent.find("div.table-row[data-index='" + $cell.parent("div.table-row").data("index") + "']").addClass(rcls);
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
			console.log(options.condition);
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
				$gridOpts.showLocalData(grid, grid.data("attributes"));
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
			var pageVo = grid.data("pageVo"), attributes = grid.data("attributes"), $hs = null, $fs = null;
			if (!pageVo || !attributes) {
				return;
			}
			if (pageVo.sortField) {
				attributes.tableHead && attributes.tableHead.length > 0 && (attributes.tableHead.find("div.table-head-cell.table-sort").find("i").attr("class", "fa fa-sort")) && ($hs = attributes.tableHead.find("div.table-head-cell[data-sort-field='" + pageVo.oldSortField + "']"));
				attributes.tableFoot && attributes.tableFoot.length > 0 && (attributes.tableFoot.find("div.table-head-cell.table-sort").find("i").attr("class", "fa fa-sort")) && ($fs = attributes.tableFoot.find("div.table-head-cell[data-sort-field='" + pageVo.oldSortField + "']"))
				if ("desc" == pageVo.sortOrder.toLowerCase()) {
					$hs && $hs.length > 0 && $hs.find("i").attr("class", "fa fa-sort-down sorting");
					$fs && $fs.length > 0 && $fs.find("i").attr("class", "fa fa-sort-down sorting");
				} else if ("asc" == pageVo.sortOrder.toLowerCase()) {
					$hs && $hs.length > 0 && $hs.find("i").attr("class", "fa fa-sort-up sorting");
					$fs && $fs.length > 0 && $fs.find("i").attr("class", "fa fa-sort-up sorting");
				}
			}
		},
		showData : function(grid, attributes) {// 显示数据
			var $ths = attributes.headCells, $dataList = grid.data("dataList"), $rows = null, $scrollRows = attributes.tableBody.find("div.scroll-area>div.table-rows"), $fixedLeft = attributes.tableBody.find("div.fixed-left>div.table-rows"), $fixedRight = attributes.tableBody.find("div.fixed-right>div.table-rows"), $l = $fixedLeft.length > 0, $r = $fixedRight.length > 0;
			if ($dataList && $dataList.length > 0) {
				if (!$ths || $ths.length == 0) {
					return $module;
				}
				$rows = grid.data("rows");
				if ($rows) {
					$rows.removeData();// 清除数据缓存
				}
				var $fixed = null, $dhtml = "", $details = "", $idField = grid.data("id-field") || $default["id-field"], $rowCls = grid.data("row-cls") || $default["row-cls"], $record = null, $events = grid.data("events"), $pageVo = grid.data("pageVo"), $openBtn = null, $detailsRow = null, $showDetail = grid.data("show-details-onload") || $default["show-details-onload"], $detailsCls = grid.data("details-cls") || $default["details-cls"], $detailsCell = null, $doi = grid.data("details-open-icon") || $default["details-open-icon"], $dci = grid.data("details-close-icon") || $default["details-close-icon"], $loadingCls = grid.data("details-loading-icon") || $default["details-loading-icon"], $row = null, $rowl = null, $rowr = null, $cell = null, $cellContent = null, $responseData = grid.data("responseData");
				$scrollRows.length > 0 && $scrollRows.empty();
				$fixedLeft.length > 0 && $fixedLeft.empty();
				$fixedRight.length > 0 && $fixedRight.empty();
				for (var i = 0, length = $dataList.length; i < length; i++) {
					$record = $dataList[i];
					if (!$record) {
						continue;
					}
					$row = $("<div class='table-row " + $rowCls + "' data-index=" + i + "></div>");
					$scrollRows.append($row.data("record", $record));
					if ($l) {
						$rowl = $("<div class='table-row " + $rowCls + "' data-index=" + i + "></div>");
						$fixedLeft.append($rowl);
					}
					if ($r) {
						$rowr = $("<div class='table-row " + $rowCls + "' data-index=" + i + "></div>");
						$fixedRight.append($rowr);
					}
					$ths.each(function(index, e) {
						var $this = $(this), $showTips = $this.data("show-tips"), $width = $this.data("width"), $hasRender = false, $content = "", $field = $this.data("field"), $dateFormat = $this.data("date-format"), $columnType = $this.data("type"), $cellEvent = $this.data("events"), $value = "";
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
									cell : $cell
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
						if ($value == null || "null" == $value) {
							$value = "";
						}
						if ($value === 0) {
							$value = "0";
						}
						if ($value === false) {
							$value = "false";
						}
						if ($columnType) {
							$cell.attr("data-type", $columnType);
							if ($cell.attr("class").indexOf("min-") < 0)
								$cell.addClass("img" == $columnType ? "min-40" : "min-40");
							if ($cell.attr("class").indexOf("max-") < 0)
								$cell.addClass("img" == $columnType ? "max-80" : "max-50");
							if ("index" == $columnType) {
								$cellContent.html(i + 1);
							} else if ("select" == $columnType) {
								if (attributes.multiSelect) {// 默认多选
									$cellContent.html("<input type='checkbox' class='subcheck' value='" + ($record[$idField] || "") + "'/>");
								} else {// 单选
									$cellContent.html("<input type='radio' class='singlecheck' value='" + ($record[$idField] || "") + "'/>");
								}
							} else if ("img" == $columnType) {
								var $defaultUrl = $this.data("default-img") || $default["default-img"], $imgUrl = $value || $defaultUrl;
								var linkUrl = $this.data('link') || $imgUrl;
								if (!$this.data('link') && $this.data('link-field')) {
									linkUrl = $common.getValueByField($record, $this.data('link-field'));
								}
								if ("string" == typeof $value) {
									$cellContent.html('<a target="' + ($this.data('target') || "_blank") + '" href="' + linkUrl + '"><img class="grid-cell-img" src="' + $imgUrl + '" style="width:' + ($this.data("img-width") || 120) + ';height:' + ($this.data("img-height") || 100) + ';" alt="' + ($this.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/></a>');
								} else if ($common.isArray($value)) {// 数组
									$cellContent.data("imgList", $value);
									$imgUrl = $value[0];
									if ($value.length > 0) {
										$cellContent.html('<a target="' + ($this.data('target') || "_blank") + '" href="' + linkUrl + '"><img class="grid-cell-img" src="' + $imgUrl + '" style="width:' + ($this.data("img-width") || 120) + ';height:' + ($this.data("img-height") || 100) + ';" alt="' + ($this.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/></a>');
									}
								}
							} else if ("detail" == $columnType) {
								$cell.css("fontSize", "16px");
								$openBtn = $("<a href='javascript:void(0)' class='detail-btn'><i class='" + ($showDetail ? $doi : $dci) + "'></i></a>");
								$cellContent.append($openBtn);
							}
						} else {
							$value = ($this.data("before") || "") + ($value || $this.data("default") || "") + ($this.data("after") || "");
							$cellContent.append($value);
						}
						if ($showTips) {
							if ($this.data("tips-msg")) {
								$cellContent.attr("title", $this.data("tips-msg")).attr("data-toggle", "tooltip");
							} else if (!$hasRender) {
								$cellContent.attr("title", $value).attr("data-toggle", "tooltip");
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
									detailsRow : $detailsRow
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
					var $fixed = null, $count = 0, $details = "", $dhtml = "", $headcontent = "", $expand = grid.data("expand-tree-onload"), $pci = grid.data("parent-close-icon") || $default["parent-close-icon"], $poi = grid.data("parent-open-icon") || $default["parent-open-icon"], $ci = grid.data("child-icon") || $default["child-icon"], $responseData = grid.data("responseData");
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
					var drawRows = function(list, pindex, num) {
						for (var i = 0, length = list.length; i < length; i++) {
							$record = list[i];
							$datas.push($record);
							$headcontent = "";
							if (!$record) {
								continue;
							}
							$row = $("<div class='table-row " + $rowCls + "' data-index=" + $count + "></div>");
							$scrollRows.append($row.data("record", $record));
							if ($l) {
								$rowl = $("<div class='table-row " + $rowCls + "' data-index=" + $count + "></div>");
								$fixedLeft.append($rowl);
							}
							if ($r) {
								$rowr = $("<div class='table-row " + $rowCls + "' data-index=" + $count + "></div>");
								$fixedRight.append($rowr);
							}
							if (pindex >= 0) {
								$row.attr("data-pindex", pindex);
								$rowl && $rowl.attr("data-pindex", pindex);
								$rowr && $rowr.attr("data-pindex", pindex);
							}
							$ths.each(function(index, e) {
								var $this = $(this), $showTips = $this.data("show-tips"), $width = $this.data("width"), $hasRender = false, $content = "", $field = $this.data("field"), $dateFormat = $this.data("date-format"), $columnType = $this.data("type"), $cellEvent = $this.data("events"), $value = "";
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
											cell : $cell
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
								if ($value == null || "null" == $value) {
									$value = "";
								}
								if ($value === 0) {
									$value = "0";
								}
								if ($value === false) {
									$value = "false";
								}
								if (!$common.isBlank($field, true) && $field == $treeColumn) {
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
										$headcontent += "<a href='javascript:void(0)' class='tree-btn'><span class='" + ($expand ? $poi : $pci) + "'></span></a>";
									} else {
										$headcontent += "<span class='" + $ci + "'></span>";
									}
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
											$cellContent.html('<a target="' + ($this.data('target') || "_blank") + '" href="' + ($this.data('link') || $imgUrl) + '"><img class="grid-cell-img" src="' + $imgUrl + '" width="' + ($this.data("img-width") || 120) + '" height="' + ($this.data("img-height") || 100) + '" alt="' + ($this.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/></a>');
										} else if ($common.isArray($value)) {// 数组
											$cell.data("imgList", $value);
											$imgUrl = $value[0];
											if ($value.length > 0) {
												$cell.html('<a target="' + ($this.data('target') || "_blank") + '" href="' + ($this.data('link') || $imgUrl) + '"><img class="grid-cell-img" src="' + $imgUrl + '" width="' + ($this.data("img-width") || 120) + '" height="' + ($this.data("img-height") || 100) + '" alt="' + ($this.data("img-alt") || $imgUrl) + '" default-img="' + $defaultUrl + '"/></a>');
											}
										}
									} else if ("detail" == $columnType) {
										$cell.css("fontSize", "16px");
										$openBtn = $("<a href='javascript:void(0)' class='detail-btn'><i class='" + ($showDetail ? $doi : $dci) + "'></i></a>");
										$cellContent.append($openBtn);
									}
								} else {
									$value = ($this.data("before") || "") + ($value || $this.data("default") || "") + ($this.data("after") || "");
									$cellContent.append($value);
								}
								if ($showTips) {
									if ($this.data("tips-msg")) {
										$cellContent.attr("title", $this.data("tips-msg")).attr("data-toggle", "tooltip").attr("data-placement",$this.data("placement")||"top");
									} else if (!$hasRender) {
										$cellContent.attr("title", $value).attr("data-toggle", "tooltip").attr("data-placement",$this.data("placement")||"top");;
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
											detailsRow : $detailsRow
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
		drawPager : function(grid, attributes, sizeList) {// 创建分页视图
			if (attributes.showPager) {
				var $page = [], $i = 0, a = sizeList.length;
				$page[$i++] = '<div class="page-panel"><div class="p-left"><div class="page-count btn-group">';
				$page[$i++] = '<button class="btn btn-default" disabled="disabled">' + $ct.showing + '</button>';
				var $select = $("<select class='bsplus-select' data-show-btn='false' data-width='auto' data-option-width='100px' data-value-field='id' data-text-field='id' data-onitemclick='BsplusGrid.selectPageSize' data-default='" + (grid.data("page-size") || $default["page-size"] || 10) + "'></select>")
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
				$page[$i++] = '<button class="btn btn-default" disabled="disabled">' + $ct.record + '</button><button class="btn btn-default" disabled="disabled" total-record="true">' + $ct.altogether + '0' + $ct.record + '</button>';
				$page[$i++] = '<button class="btn btn-default" disabled="disabled" select-rows>' + $ct.currentlySelected + '0' + $ct.record + '</button></div></div><div class="p-right"><div class="page-nav btn-group" role="group">';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="first"><i class="fa fa-step-backward"></i></button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="prev"><i class="fa fa-chevron-left"></i></button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="go">' + $ct.jumpTo + '</button><input type="text" class="form-control" placeholder="1" current-page="true" value="1">';
				$page[$i++] = '<button type="button" class="btn btn-default" disabled="disabled">' + $ct.page + '</button><button class="btn btn-default" disabled="disabled" total-page="true">' + $ct.altogether + '0' + $ct.page + '</button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="next"><i class="fa fa-chevron-right"></i></button>';
				$page[$i++] = '<button type="button" class="btn btn-default pager-btn" data-action="last"><i class="fa fa-step-forward"></i></button></div></div></div>';
				attributes.page.html($page.join(""));
				try {
					attributes.page.find("select.bsplus-select").BsplusSelect("init");
				} catch (e) {
					$common.log(e);
				}
			}
			return $module;
		},
		updatePager : function(grid) {// 更新分页视图数据
			try {
				var $pageVo = grid.data("pageVo"), $attributes = grid.data("attributes");
				if ($pageVo && $attributes) {
					$attributes.page.find("button[total-record]").html($ct.altogether + $pageVo.totalRecord + $ct.record);
					$attributes.page.find("button[total-page]").html($ct.altogether + $pageVo.totalPage + $ct.page);
					$attributes.page.find("input[current-page]").val($pageVo.currentPage);
					if ($pageVo.currentPage == 1 || $pageVo.totalPage == 0) {
						$attributes.page.find("button[data-action='first'],button[data-action='prev']").prop("disabled", true);
					} else {
						$attributes.page.find("button[data-action='first'],button[data-action='prev']").prop("disabled", false);
					}
					if ($pageVo.currentPage == $pageVo.totalPage || $pageVo.totalPage == 0) {
						$attributes.page.find("button[data-action='next'],button[data-action='last']").prop("disabled", true);
					} else {
						$attributes.page.find("button[data-action='next'],button[data-action='last']").prop("disabled", false);
					}
				}
			} catch (e) {
				$common.log(e);
			}
			return $module;
		},
		gotoPage : function(grid, attributes, action) {// 绑定翻页按钮点击事件
			try {
				var $pages = attributes.page, $pageVo = grid.data("pageVo"), $input = $pages.find("input[current-page]"), $currentPage = $pageVo.currentPage, $totalPage = $pageVo.totalPage;
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
				grid.find("div.bsplus-grid-config div.config-container").toggle(300);
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
				var $pageVo = grid.data("pageVo"), $sortType = "desc";
				if ("desc" == $pageVo.sortOrder.toLowerCase()) {
					$sortType = "asc";
				} else {
					$sortType = "desc";
				}
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
					data : grid.data("dataList")
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
				var $this = $(this), $htArray = [ [], [], [] ], $visible = true, $init = false == $this.data("init") ? false : true, $comAttr = null, $ths = null, $columnType = null, $event = null, $fixed = null, $fixedLeft = false, $fixedRight = false, $leftArr = [], $centerArr = [], $rightArr = [];
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
						onloaderror : $this.data("onloaderror") || $default["onloaderror"] || null,
						ondataload : $this.data("ondataload") || $default["ondataload"] || null,
						ondrawdetails : $this.data("ondrawdetails") || $default["ondrawdetails"] || null
					};
					$comAttr.bodyTable = $comAttr.bodyDiv.find("div.bsplus-table");
					$comAttr.showEmptyText = !$common.isBlank($this.data("empty-text") || $default["empty-text"], true);
					$comAttr.headCells = $comAttr.bodyTable.find("div.table-fields").children();
					$this.data("allow-wrap") ? $comAttr.bodyTable.addClass("table-wrap").removeClass("no-wrap") : $comAttr.bodyTable.addClass("no-wrap").removeClass("table-wrap");// 是否允许换行
					$comAttr.headCells.each(function(index, e) {
						var $thisCell = $(this), $cls = $thisCell.data("common-cls"), $width = $thisCell.data("width"), $cellHtml = "", $style = "";
						$fixed = $thisCell.data("fixed");
						$thisCell.attr("data-index", index);
						$visible = false != $thisCell.data("visible");
						if (!$common.isBlank($cls, true)) {
							$thisCell.addClass($cls);
						}
						$thisCell.listenPlusEvent({
							renderer : $thisCell.data("renderer") || null
						});// 事件监听初始化
						$columnType = $thisCell.data("type");
						$columnType && $thisCell[0].className.indexOf("min-") < 0 && $thisCell.addClass("img" == $columnType ? "min-40" : "min-40");
						$columnType && $thisCell[0].className.indexOf("max-") < 0 && $thisCell.addClass("img" == $columnType ? "max-80" : "max-50");
						$cls = $thisCell[0].className;
						!$visible && ($cls += " bsplus-hidden");
						$style = $width ? "style='width:" + $width + ";'" : "";
						if ($columnType) {
							if ("select" == $columnType && $(e).find("input:checkbox.checkAll").length == 0) {
								if ($comAttr.multiSelect) {
									$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'><input type='checkbox' class='checkAll' /></div>";
								} else if ($common.isBlank(e.innerHTML, true)) {
									$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $ct.select + "</div>";
								} else {
									$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $thisCell.html() + "</div>";
								}
							} else if ("index" == $columnType) {
								if ($common.isBlank(e.innerHTML, true)) {
									$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $ct.index + "</div>";
								} else {
									$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $thisCell.html() + "</div>";
								}
							} else if ("img" == $columnType) {
								$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $thisCell.html() + "</div>";
								$thisCell.data("default-img", $thisCell.data("default-img") || $this.data("default-img") || $default["default-img"] || $path + "/images/default.png");
							} else if ("detail" == $columnType) {
								$comAttr.showDetails = true;
							}
						} else if ($thisCell.data("allow-sort")) {
							$thisCell.data("sort-field", $thisCell.data("sort-field") || $thisCell.data("field"));
							$cellHtml = "<div class='table-head-cell table-sort " + $cls + "' data-sort-field='" + ($thisCell.data("sort-field")) + "' " + $style + " data-index='" + index + "'>" + $thisCell.html() + "<i class='fa fa-sort'></i></div>";
						} else {
							$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $thisCell.html() + "</div>";
						}
						if (!$cellHtml) {
							$cellHtml = "<div class='table-head-cell " + $cls + "' " + $style + " data-index='" + index + "'>" + $thisCell.html() + "</div>";
						}
						if ($fixed) {
							if ("left" == $fixed) {
								$fixedLeft = true;
								$leftArr.push($cellHtml);
								$htArray[0].push({
									text : "select" == $columnType ? "选择" : $thisCell.html(),
									state : $visible
								});
							} else if ("right" == $fixed) {
								$fixedRight = true;
								$rightArr.push($cellHtml);
								$htArray[2].push({
									text : "select" == $columnType ? "选择" : $thisCell.html(),
									state : $visible
								});
							} else {
								$centerArr.push($cellHtml);
								$htArray[1].push({
									text : "select" == $columnType ? "选择" : $thisCell.html(),
									state : $visible
								});
							}
						} else {
							$htArray[0].push({
								text : "select" == $columnType ? "选择" : $thisCell.html(),
								state : $visible
							});
							$centerArr.push($cellHtml);
						}
					});
					$comAttr.tableHead = $("<div class='table-head'></div>");
					$comAttr.tableBody = $("<div class='table-body'></div>");
					$comAttr.bodyContent = $("<div class='table-body-content'></div>");
					$comAttr.tableScroll = $("<div class='table-scroll-box'></div>");
					$comAttr.bodyTable.append($comAttr.tableHead).append($comAttr.tableBody);
					$comAttr.tableBody.append($comAttr.bodyContent);
					if ($this.data("show-foot")) {
						$comAttr.tableFoot = $("<div class='table-foot'></div>");
						$comAttr.bodyTable.append($comAttr.tableFoot)
					}
					$comAttr.bodyTable.append($comAttr.tableScroll);
					var $cw = $this.data("scroll-width") || "100%", $lw = $this.data("left-width") || "20%", $rw = $this.data("right-width") || "20%";
					if ($fixedLeft) {
						$comAttr.tableHead.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"><div class="table-rows"><div class="table-head-row">' + $leftArr.join("") + '</div></div></div>');
						$comAttr.bodyContent.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"><div class="table-rows"></div></div>');
						if ($comAttr.tableFoot) {
							$comAttr.tableFoot.append('<div class="table-block fixed-area fixed-left" style="width: ' + $lw + ';"><div class="table-rows"><div class="table-head-row">' + $leftArr.join("") + '</div></div></div>');
						}
						$comAttr.tableScroll.append('<div class="table-block fixed-area" style="width: ' + $lw + ';"></div>');
					}
					$comAttr.tableHead.append('<div class="table-block scroll-area"><div class="table-rows" style="width:' + $cw + ';"><div class="table-head-row">' + $centerArr.join("") + '</div></div></div>');
					$comAttr.bodyContent.append('<div class="table-block scroll-area"><div class="table-rows" style="width:' + $cw + ';"></div></div>');
					if ($comAttr.tableFoot) {
						$comAttr.tableFoot.append('<div class="table-block scroll-area"><div class="table-rows" style="width:' + $cw + ';"><div class="table-head-row">' + $centerArr.join("") + '</div></div></div>');
					}
					$comAttr.tableScroll.append('<div class="table-block scroll-area"><div class="table-scroll-bar"><div style="width: ' + $cw + '; height: 100%;"></div></div></div>');
					if ($fixedRight) {
						$comAttr.tableHead.append('<div class="table-block fixed-area fixed-right" style="width: ' + $rw + ';"><div class="table-rows"><div class="table-head-row">' + $rightArr.join("") + '</div></div></div>');
						$comAttr.bodyContent.append('<div class="table-block fixed-area fixed-right" style="width: ' + $rw + ';"><div class="table-rows"></div></div>');
						if ($comAttr.tableFoot) {
							$comAttr.tableFoot.append('<div class="table-block fixed-area fixed-right" style="width: ' + $rw + ';"><div class="table-rows"><div class="table-head-row">' + $rightArr.join("") + '</div></div></div>');
						}
						$comAttr.tableScroll.append('<div class="table-block fixed-area" style="width: ' + $rw + ';"></div>');
					}
					$this.data("pageVo", {
						sortField : $this.data("sort-field") || $default["sort-field"] || "",// 排序字段
						sortOrder : $this.data("sort-order") || $default["sort-order"] || "",// 排序方式
						pageSize : $this.data("page-size") || $default["page-size"] || 10,// 每页显示条数
						currentPage : 1,
						totalRecord : 0,
						totalPage : 0
					});
					if ($comAttr.showPager) {// 初始化分页信息
						var $pageVo = $this.data("pageVo"), sizeList = $this.data("size-list") || $default["size-list"] || [ 10, 50, 100 ];
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
							var $gridFooter = $this.find("div.grid-footer");
							if ($gridFooter.length == 0) {
								$gridFooter = $("<div class='grid-footer'></div>");
							}
							$comAttr.bodyDiv.after($gridFooter.html($page));
						}
						if ($comAttr.showSummary) {
							if ($this.find("div.grid-summary").length == 0) {
								$comAttr.bodyDiv.after("<div class='grid-summary'></div>");
							}
							$event.ondrawsummary = $this.data("ondrawsummary") || $default["ondrawsummary"] || null;
						}
						$comAttr.page = $page;
						$gridOpts.drawPager($this, $comAttr, sizeList);// 创建分页视图
					}
					$this.data("attributes", $comAttr);
					$this.data("inited", true);// 标注为表格已初始化
					$checker.init.call($comAttr.bodyDiv.find("input:checkbox,input:radio"), {
						"cls" : $this.data("checker-cls") || $default["checker-cls"]
					});
					if ($this.data("scroll-width")) {
						$this.find("div.table-scroll-box").css("display", "table").find("div.table-scroll-bar").off('scroll').on('scroll', function() {
							$this.find("div.scroll-area>div.table-rows").css({
								"marginLeft" : -$(this).scrollLeft()
							});
						});
					}
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
					$comAttr.clickrowtime = $event.onrowdblclick ? 300 : 0;// 单击生效延时时间（清除双击时触发的点击事件）
					$comAttr.clickcelltime = $event.oncelldblclick ? 300 : 0;// 单击生效延时时间（清除双击时触发的点击事件）
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
				if ($this.data("show-config") || $default["show-config"]) {
					if (0 == $this.find("div.grid-head").length) {
						$this.prepend('<div class="grid-head"><div class="grid-functions"></div></div>');
					} else if (0 == $this.find("div.grid-functions").length) {
						$this.find("div.grid-head").append('<div class="grid-functions"></div>');
					}
					$this.find("div.grid-functions").append('<div class="bsplus-grid-config"><button type="button" class="btn btn-sm" data-action="config"><span class="fa fa-cog fa-fw"></span></button><div class="config-container"><div class="arrow"></div><div class="config-content"><div class="form-group"></div></div></div></div>');
					var $co = $this.data("config-options") || [ 'sort', 'cells' ], $config = $this.find("div.grid-functions div.bsplus-grid-config div.config-content");
					if ($co.indexOf('sort') >= 0) {
						$config.children("div.form-group").append('<label class="config-title">排序方式：</label> <input type="checkbox" data-width="130" class="bsplus-switch" data-left-text="全部排序" data-right-text="当页排序" />');
						$config.find("input.bsplus-switch:checkbox").change(function() {
							if (this.checked) {
								$this.data("sort-all", true);
							} else {
								$this.data("sort-all", false);
							}
						});
					}
					if ($co.indexOf('cells') >= 0) {
						$config.children("div.form-group").append('<label class="config-title">显示设置：</label>');
						$config.append('<ul></ul>');
						var $lis = [], $lisn = 0;
						for (var i = 0, a = $htArray.length; i < a; i++) {
							for (var j = 0, b = $htArray[i].length; j < b; j++) {
								$lis[$lisn] = '<li><input type="checkbox" data-after-text="' + $htArray[i][j].text + '" ' + ($htArray[i][j].state ? 'checked="checked"' : '') + ' data-index="' + $lisn + '"/></li>';
								$lisn++;
							}
						}
						$config.find("ul").append($lis.join("")).BsplusChecker("init");
						$config.find("ul>li input:checkbox,input:radio").change(function() {
							var index = $(this).data("index");
							$module.hideOrShowCell.call($this, index, this.checked);
							$this.find("div.table-fields>div[data-index='" + index + "']").data("visible", this.checked);
							var $checkeds = $config.find("ul>li input:checkbox:checked,input:radio:checked");
							if (1 == $checkeds.length) {
								$checkeds.disabled();
							} else {
								$config.find("ul>li input:checkbox:disabled,input:radio:disabled").enabled();
							}
						});
					}
				}
				try {
					$module.tableLayOut.call($this, true);// 调整表格布局(隐藏时无法实现布局)
				} catch (e) {
					$common.log(e);
				}
			});
			return this;
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
				var $this = $(this), $attributes = $this.data("attributes"), $scrollHeight = $this.data("scroll-height") || $default["scroll-height"];
				if (!$attributes) {
					return $module;
				}
				$attributes.tableScroll.css("height", $scrollWidth);
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
			var $attributes = $grid.data("attributes"), $localDatas = $grid.data("localDatas"), searchData = null;
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
					searchData = $common.getFormatData($grid.find("div.grid-head form"));
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
				var $pageVo = grid.data("pageVo"), $attributes = grid.data("attributes");
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
			var $this = e.target, grid = $this.closest("div.bsplus-grid"), $pageVo = grid.data("pageVo");
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
				var $selectRows = 0;
				var $rows = grid.data("rows"), $obj = grid.find("button[select-rows]"), $inputs = grid.data("checkAllBox");
				if ($rows) {
					$rows.each(function() {
						if ($(this).data("selected")) {
							$selectRows++;
						}
					});
				}
				if ($obj.length > 0) {
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
			var $indexs = [], $attributes = grid.data("attributes"), $rows = grid.data("rows");
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
				var $attributes = grid.data("attributes");
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
				var $attributes = grid.data("attributes");
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
			var attributes = grid.data("attributes"), $row, $ri, $selectedCls = attributes.selectedCls;
			selected = false == selected ? false : true;
			if (!attributes || !rows || rows.length == 0) {
				return this;
			}
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
}($, Common, BsplusChecker);