//获取工程跟路径
function getRootPath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	return curWwwPath.substring(0, curWwwPath.indexOf(pathName)) + pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
}
jQuery(document).ready(function() {// 首页菜单
	$("ul.navbar-nav>li.mega-menu-dropdown").mouseover(function() {
		$(this).addClass("open");
	}).mouseout(function() {
		$("ul.navbar-nav>li.open").removeClass("open");
	});
	$(window).click(function(e) {
		var _element = $(e.target);
		switch (_element[0].tagName.toLowerCase()) {
		case "i":
		case "a":
			var _a = _element;
			if (!_a.is("a")) {
				_a = _element.closest("a");
			}
			if (_a.hasClass("show-btn")) {
				_a.hide().next("ul.fixed-boxlist").show(300);
			}
		case "span":
			if (_element.hasClass("view-password")) {
				_element.toggleClass("fa-eye fa-eye-slash");
				var $input = _element.prevAll("input");
				if (_element.hasClass("fa-eye")) {
					$input.attr("type", "text");
				} else {
					$input.attr("type", "password");
				}
			}
		case "li":
			var _li = _element;
			if (!_li.is("li")) {
				_li = _element.closest("li");
			}
			if (_li.hasClass("hide-btn")) {
				_li.closest("ul.fixed-boxlist").hide(300).prev("a.show-btn").show();
			} else if (_li.hasClass("to-top")) {
				$("html,body").animate({
					scrollTop : 0
				}, 300, function() {
					$("#product-list-grid,#product-list-grid,#change-list-grid,#remark-list-grid").find("div.table-head.grid-fixed,div.grid-head>div.grid-paging.grid-fixed,div.grid-body div.table-scroll-box.grid-fixed").removeClass("grid-fixed").css({
						top : "",
						width : ""
					});
				});
			}
			break;
		default:
			break;
		}
	});
	$("#update-password").unbind("click").click(function() {
		bsplus.openModal({
			url : bsplus.path + "/mgr/user/updatepassword",
			title : "修改密码",
			iconCls : "fa fa-edit",
			saveBtnText : "确认修改",
			onHandleSuccess : function(e) {
				setTimeout(function() {
					"true" == e.data.code && (window.top.location.href = bsplus.path + "/mgr/user/exit");
				}, 2000);
			}
		});
	});
});
window.Home = function() {
	"use strict";
	var $tabs = $("#bsplus-tabs"), $cidNum = $("#notice-cid-num"), $bell = $("#fa-bell"), $taskNum = $("#task-num"), $msg1 = $("#task-msg1"), $msg2 = $("#task-msg2"), $noticeMsg = $("#notice-msg"), $viewBtn = $("#view-btn-li"), obj;
	$viewBtn.unbind("click").click(function() {
		$tabs.addTab({
			inIframe : false,
			code : "189",
			title : "属性变更",
			url : bsplus.path + "/product/changelist?collect=true&sourceId=189&startTime=" + obj.startTime + "&endTime=" + obj.endTime,
			oncontentload : function() {
				$.ajax({
					cache : true,
					type : 'POST',
					url : bsplus.path + "product/addNoticeLog",
					data : obj,
					success : function(data) {
						if (data && "true" == data.code) {
							Home.initNotice();
						}
					}
				});
			}
		});
	});
	return {
		init : function() {
			$.ajax({
				cache : true,
				type : 'POST',
				url : bsplus.path + "schedule/showTask",
				success : function(data) {
					if (data && "true" == data.code && data.object) {
						var o = data.object;
						$taskNum.html(o.undoneNum || 0);
						$msg1.html("已完成<span class='task-num'>" + (o.completedNum || 0) + "</span>个任务");
						$msg2.html("有<span class='task-num'>" + (o.undoneNum || 0) + "</span>个任务待完成");
					}
				}
			});
		},
		initNotice : function() {
			$.ajax({
				cache : true,
				type : 'POST',
				url : bsplus.path + "product/showNotice",
				success : function(data) {
					if (data && "true" == data.code && data.object) {
						obj = data.object;
						if (obj) {
							if (obj.cidNum > 0) {
								$cidNum.html(obj.cidNum);
								$viewBtn.show();
								$noticeMsg.html("您监控的产品中，从" + obj.startTime + "到" + obj.endTime + "有<span class='task-num'>" + obj.cidNum + "</span>个产品共发生了<span class='task-num'>" + obj.totalNum + "</span>次变化。");
								$bell.css({
									color : "#ce3939"
								});
							} else {
								$cidNum.html(0);
								$viewBtn.hide();
								$noticeMsg.html(obj.msg);
								$bell.css({
									color : "#444D58"
								});
							}
						}
					}
				}
			});
		}
	}
}();