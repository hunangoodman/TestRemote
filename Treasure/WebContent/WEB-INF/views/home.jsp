<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>大盘智联资产</title>
<meta name="description"
	content="This is page-header (.page-header &gt; h1)" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="${path}/plugins/bootstrap-3.3.5/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${path}/plugins/bsplus/css/font-awesome.min.css" type="text/css">
<link
	href="${path}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="${path}/css/layout.css?v=${version}"
	type="text/css">
<link rel="stylesheet"
	href="${path}/plugins/bsplus/css/bsplus.css?v=${version}"
	type="text/css">
<link rel="stylesheet" href="${path}/css/custom.css?v=${version}"
	type="text/css">
<link rel="shortcut icon" href="${path}/images/favicon.ico?v=${version}">
</head>
<body style="background: #3b434c;">
	<div class="page-header" id="page-header">
		<div class="page-header-top">
			<div class="navbar navbar-default" id="navbar">
				<div class="navbar-container" id="navbar-container">
					<div class="navbar-header pull-left">
						<a href="${path}/home" class="navbar-brand" tabindex="-1"> <img
							src=""
							style="float: left; height: 60px; margin-top: -20px; margin-left: -30px;" />
							<span style="height: 60px; float: left; ">智联资产后台管理
								V${version}</span>
						</a>
					</div>
					<div class="navbar-header pull-right" role="navigation">
						<ul class="nav ace-nav">
							<li class="light-blue"><a data-toggle="dropdown" href="#"
								class="dropdown-toggle" style="color: white;"><span
									class="glyphicon fa fa-user" style="font-size: 20px;"></span><span
									class="user-info"> &nbsp;${!empty loginInfo.userName?loginInfo.userName:'游客'}
								</span> <i class="fa fa-caret-down"></i> </a>
								<ul
									class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
									<!-- <li><a href="#"> <i class="fa fa-cog"></i> 设置
									</a></li> -->
									<li><a href="JavaScript:void(0)" id="update-password">
											<i class="fa fa-user"></i> 修改密码
									</a></li>
									<li class="divider"></li>
									<li><a href="${path}/mgr/user/exit"> <i
											class="fa fa-sign-out"></i> 退出
									</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div style="height: 50px;">
			<div class="page-header-menu">
				<div class="container-fluid">
					<div class="hor-menu ">
						<ul class="nav navbar-nav">
							<c:if test="${!empty dataList}">
								<c:forEach var="resource" items="${dataList}" varStatus="i">
									<li
										class="menu-dropdown mega-menu-dropdown ${(resource.moduleName=='API'||resource.moduleName=='demo')?'mega-menu-full':''}"><a
										${!empty resource.path?'addtabs':''}
										data-hover="megamenu-dropdown" data-close-others="true"
										data-toggle="dropdown" class="dropdown-toggle"
										data-url="${resource.path}" data-id="${resource.moduleId}"
										href="javascript:void(0)"> <i
											class="${!empty resource.icon?resource.icon:'fa fa-list-alt'}"></i>
											${resource.moduleName}<i class="fa fa-sort-down"></i>
									</a> <c:if
											test="${!empty resource.childList&&fn:length(resource.childList)>0}">
											<fmt:formatNumber type="number" var="num"
												value="${12/fn:length(resource.childList)}"
												maxFractionDigits="0" />
											<ul class="dropdown-menu">
												<li>
													<div class="mega-menu-content">
														<div class="row">
															<c:forEach var="cresource" items="${resource.childList}">
																<div class='col-md-${num}'>
																	<ul class="mega-menu-submenu">
																		<li style="width: 120px;">
																			<h3>
																				<i
																					class="${!empty cresource.icon?cresource.icon:'fa fa-th-large'}"
																					aria-hidden="true"></i>&nbsp;
																				${cresource.moduleName}
																			</h3>
																		</li>
																		<c:if
																			test="${!empty cresource.childList&&fn:length(cresource.childList)>0}">
																			<c:forEach var="subresource"
																				items="${cresource.childList}">
																				<li style="width: 150px;"><a
																					title="${subresource.description}"
																					data-id="${subresource.moduleId}"
																					${!empty subresource.path?'addtabs':''}
																					href="javascript:void(0)"
																					data-url="${subresource.path}"
																					data-tab-title='${subresource.moduleName}'>&nbsp;&nbsp;&nbsp;<i
																						class="${!empty subresource.icon?subresource.icon:'fa fa-th-large'}"
																						aria-hidden="true"></i>&nbsp;${subresource.moduleName}
																				</a></li>
																			</c:forEach>
																		</c:if>
																	</ul>
																</div>
															</c:forEach>
														</div>
													</div>
												</li>
											</ul>
										</c:if></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="page-container" style="padding: 5px 10px;">
		<div style="display: table; position: relative; width: 100%;">
			<div class="tabbable bsplus-tab" id="bsplus-tabs" data-max-size="12"></div>
		</div>
	</div>
	<div class="page-footer">
		<div class="container-fluid">
			2017 &copy;tuanli &nbsp;
			<script type="text/javascript">
				var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
				document.write(unescape("%3Cspan id='cnzz_stat_icon_1262081168'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1262081168%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
			</script>
		</div>
	</div>
	<script src='${path}/js/jquery-2.1.4.min.js'></script>
	<script src="${path}/plugins/bootstrap-3.3.5/js/bootstrap.js"
		type="text/javascript" charset="UTF-8"></script>
	<script
		src="${path}/plugins/bootstrapValidator/js/bootstrapValidator.js"
		type="text/javascript" charset="UTF-8"></script>
	<script
		src="${path}/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"
		type="text/javascript" charset="UTF-8"></script>
	<script src="${path}/plugins/bsplus/js/bsplus-config.js?v=${version}"
		type="text/javascript" charset="UTF-8"></script>
	<script src="${path}/plugins/bsplus/js/bsplus.all.js?v=${version}"
		type="text/javascript" charset="UTF-8"></script>
	<script src="${path}/js/common.js?v=${version}" type="text/javascript"
		charset="UTF-8"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			var $tabs = $("#bsplus-tabs");
			Home.init();
			Home.initNotice();
			$tabs.addTab({
				code : "index",
				title : "首页",
				url : "${path}/mgr/user/index",
				showClose : false
			});
			var _this = null;
			$("[addtabs]").click(function() {
				_this = $(this);
				setTimeout(function() {
					$tabs.addTab({
						inIframe : "2" == _this.data("type") ? true : false,
						code : _this.data("id"),
						title : _this.data('tab-title'),
						url : bsplus.path + _this.data('url') + "?sourceId=" + _this.data("id")
					});
				}, 50);
			});
			var h = 50;
			$(window).scroll(function(e) {//固定菜单
				var t = document.documentElement.scrollTop || document.body.scrollTop;
				if (t >= h) {
					var $p = $("#product-list-grid");
					if ($p.length > 0) {
						if (t >= 275) {
							var $grid = null, w;
							$p.each(function() {
								$grid = $(this);
								w = $grid.find("div.table-body").width();
								$grid.find("div.grid-head>div.grid-paging").addClass("grid-fixed").css({
									top : 0,
									width : w
								});
								$grid.find("div.table-head").addClass("grid-fixed").css({
									top : 50,
									width : w
								});
							});
						} else {
							$p.find("div.table-head,div.grid-head>div.grid-paging").removeClass("grid-fixed").css({
								top : "",
								width : ""
							});
						}
					}
				}
			});
			$(window).resize(function() {
				var $p = $("#product-list-grid,#collect-list-grid");
				if ($p.length > 0) {
					$p.each(function() {
						$(this).find("div.grid-fixed").css("width", $(this).find("div.table-body").width());
					});
				}
				var $ch = 0;
				if (window.innerHeight)
					$ch = window.innerHeight;
				else if ((document.body) && (document.body.clientHeight))
					$ch = document.body.clientHeight;
				$tabs.css("min-height", $ch - $("#page-header").height() - 48);
			});
			var $ch = 0;
			if (window.innerHeight)
				$ch = window.innerHeight;
			else if ((document.body) && (document.body.clientHeight))
				$ch = document.body.clientHeight;
			$tabs.css("min-height", $ch - $("#page-header").height() - 48);
		});
	</script>
</body>
</html>