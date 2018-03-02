<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post" id="setting-form">
	<input type="hidden" name="id" value="${setting.id}" />
	<div style="padding: 10px 20px;">
		<div class="portlet">
			<div class="portlet-title">
				<div class="title">大盘信息设置</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">大盘总数</label>
							<div class="col-sm-9">
								<input class="form-control" name="totalCount"
									placeholder="系统大盘总量" value="${setting.tc}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">单次可购买银币数</label>
							<div class="col-sm-9">
								<input class="form-control" name="maxBuyCount"
									placeholder="单次可购买银币数" value="${setting.maxBuyCount}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">大盘单价涨幅</label>
							<div class="col-sm-9">
								<input class="form-control" name="amplitude"
									placeholder="大盘单价和数量涨跌幅度" value="${setting.amp}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">大盘数量涨幅</label>
							<div class="col-sm-9">
								<input class="form-control" name="countAmplitude"
									placeholder="银币数量涨幅" value="${setting.camp}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<%-- <div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">银币单价</label>
							<div class="col-sm-9">
								<input class="form-control" name="silverPrice"
									placeholder="银币单价" value="${setting.silverPrice}" />
							</div>
						</div>
					</div> --%>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">大盘单价</label>
							<div class="col-sm-9">
								<input class="form-control" name="integralPrice"
									placeholder="大盘单价" value="${setting.integralPrice}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">提现费率</label>
							<div class="col-sm-9">
								<input class="form-control" name="withdrawRate"
									placeholder="提现费率" value="${setting.withdrawRate}" />
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">单价最低</label>
							<div class="col-sm-9">
								<input class="form-control" name="lowest" placeholder="大盘价格可跌最低"
									value="${setting.lowest}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">单价最高</label>
							<div class="col-sm-9">
								<input class="form-control" name="highest"
									placeholder="大盘价格可涨最高" value="${setting.highest}" />
							</div>
						</div>
					</div>
				</div> --%>
				<div class="row">
					
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">能量值比例</label>
							<div class="col-sm-9">
								<input class="form-control" name="consumeBeanRate"
									placeholder="提现获得消费豆比例" value="${setting.consumeBeanRate}" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-title">
				<div class="title">用户静态持币算力</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">0~300</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate1"
									value="${setting.rate1}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">300~1000</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate2"
									value="${setting.rate2}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">1000~5000</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate3"
									value="${setting.rate3}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">5000以上</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate4"
									value="${setting.rate4}" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="portlet-title">
				<div class="title">用户动态持币算力</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">500~999</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate5"
									value="${setting.rate5}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">1000~4999</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate6"
									value="${setting.rate6}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">5000及以上</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate7"
									value="${setting.rate7}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6"></div>
				</div>
			</div>
			<%-- <div class="portlet-title">
				<div class="title">用户大盘数量区间涨幅</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">1500~2999</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate1"
									value="${setting.rate1}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">3000~4999</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate2"
									value="${setting.rate2}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">5000~9999</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate3"
									value="${setting.rate3}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">10000~20000</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate4"
									value="${setting.rate4}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">20000以上</label>
							<div class="col-sm-9">
								<input class="form-control" name="rate5"
									value="${setting.rate5}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6"></div>
				</div>
			</div> --%>
			
			
			<div class="portlet-title">
				<div class="title">投资收益</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">投资总金额</label>
							<div class="col-sm-9">
								<input class="form-control" name="moneymanagement"
									value="${setting.moneymanagement}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">活期利率</label>
							<div class="col-sm-9">
								<input class="form-control" name="interestrate"
									value="${setting.interestrate}" />
							</div>
						</div>
					</div>
				</div>
				<div class="portlet-title">
				<div class="title">定期利率</div>
			</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">30天</label>
							<div class="col-sm-9">
								<input class="form-control" name="interestrate1"
									value="${setting.interestrate1}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">60天</label>
							<div class="col-sm-9">
								<input class="form-control" name="interestrate2"
									value="${setting.interestrate2}" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">90天</label>
							<div class="col-sm-9">
								<input class="form-control" name="interestrate3"
									value="${setting.interestrate3}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label class="col-sm-3 control-label">180天</label>
							<div class="col-sm-9">
								<input class="form-control" name="interestrate3"
									value="${setting.interestrate4}" />
							</div>
						</div>
					</div>
					<div class="col-xs-6"></div>
				</div>
			</div>
			
			
			
			
			<div class="portlet-title"
				style="background: none; text-align: center;">
				<button type="submit" class="btn green-haze btn-sm">
					<span class="fa fa-save" aria-hidden="true"></span>&nbsp; 保存
				</button>
			</div>
			<div class="portlet-body"></div>
		</div>
	</div>
</form>
<script type="text/javascript">
	var $form = $("#setting-form");
	$form.bootstrapValidator({
		excluded : ':disabled',
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'fa fa-check vicon',
			invalid : 'fa fa-close vicon',
			validating : 'fa fa-refresh vicon'
		}
	}).on('success.form.bv', function(e) {// 表单验证成功
		e.preventDefault();
		$.ajax({
			url : bsplus.path + "/setting/mgr/save",
			type : "POST",
			data : $form.serialize(),
			success : function(data) {
				if ("true" === data.code) {
					bsplus.showToast({
						content : data.msg,
						backColor : "#51A351"
					});
				} else {
					$form.data('bootstrapValidator').resetForm(true);// 重置验证
					bsplus.showToast({
						content : data.msg,
						backColor : "#C9302C"
					});
				}
			}
		});
	});
</script>