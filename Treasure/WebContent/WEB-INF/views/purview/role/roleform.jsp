<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form class="form-horizontal" method="post"
	action="${path}/mgr/role/addRole">
	<input name="roleId" type="hidden" />
	<div class="modal-body">
		<div class="form-body">
			<div class="form-group">
				<label for="roleName" class="col-sm-3 control-label">角色名称</label>
				<div class="col-sm-9">
					<div class="input-icon">
						<i class="fa fa-asterisk"></i> <input type="text"
							class="form-control" name="roleName" maxlength="50"
							placeholder="请输入角色名称" data-bv-notempty
							data-bv-notempty-message="请输入角色名称">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-3 control-label">描述</label>
				<div class="col-sm-9">
					<textarea class="form-control" rows="2" name="description"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	function beforsubmit() {
		return true;
	}
	function aftersubmit(e) {
		//alert("保存成功"+e.test);
	}
	function validatorere() {
		return $("#testForm").bootstrapValidator({
			excluded : ':disabled',
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'fa fa-ok vicon',
				invalid : 'fa fa-remove vicon',
				validating : 'fa fa-refresh vicon'
			}
		});
	}
	var selectList = [ {
		text : "组一",
		child : [ {
			id : 1,
			text : 111
		}, {
			id : 2,
			text : 222
		}, {
			id : 3,
			text : 333
		} ]
	}, {
		text : "组二",
		child : [ {
			id : 4,
			text : 444
		}, {
			id : 5,
			text : 555
		}, {
			id : 6,
			text : 666
		} ]
	} ];
</script>