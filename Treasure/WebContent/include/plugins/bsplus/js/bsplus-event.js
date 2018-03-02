var BsplusEvent = function($, $common) {// 事件管理
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
												$arg[i] = $.extend({
													target : this,
													type : type
												}, data);
											} else if ("string" === typeof $arg[i] && $arg[i].indexOf("event.") >= 0) {
												$arg[i] = $.extend({
													target : this,
													type : type
												}, data)[$arg[i].split(".")[1]];
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
					return;
				}
				if ("function" === typeof $events[type]) {
					$arg = $args[type];// 参数数组
					if ($arg && $arg.length > 0) {
						return $events[type].apply(this, $arg);// 将this指向target对象
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
}($, Common);