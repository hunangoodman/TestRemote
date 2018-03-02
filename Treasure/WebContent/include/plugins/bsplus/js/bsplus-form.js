var BsplusForm = function($, $common) {// 表单处理
	"use strict";// 严格模式
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
							if ("true" === data.code) {
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
							opts.data = $common.getValueByField(data, $common.dataField.objField);
							var code = $common.getValueByField(data, $common.dataField.codeField);
							setTimeout(function() {
								$module.setFormData(opts);// 设置数据到表单
							}, 10);
							if ("error" == code || "info" == code || "false" == code) {
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
		validForm : function(form) {// 验证表单并返回验证结果，Boolean：true验证通过，false 验证未通过
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
}($, Common);