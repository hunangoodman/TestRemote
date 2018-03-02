var BsplusConfig = {
	initBStools : false,// 是否加载完页面自动初始化BStools插件
	constants : {// 常量国际化处理
		operateSuccess : "操作成功！",
		operateFailure : "操作失败！",
		operateError : "操作异常！",
		noOperate : "无任何操作！",
		promptTitle : "温馨提示",
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
	dataField : {// 默认数字字段
		codeField : "code",// 执行返回结果代码字段
		msgField : "msg",// 执行返回结果消息字段
		listField : "list",// 执行返回结果数据集合字段
		objField : "object",// 执行返回结果数据对象字段
		pageField : "pageInfo"// 执行返回结果分页信息对象字段（针对数据表格）
	},
	gridConfig : {// 表格统一配置(其他属性不支持统一配置，请为表格单独设置)
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
		"checker-cls" : "check3",
		"multi-select" : true,
		"empty-cls" : "grid-empty-cls",// 空行样式
		"hide-otherdetails-when-show" : true,
		"default-img" : ""// 默认图片路径
	},
	checkerConfig : {// 选择按钮配置
		"cls" : "check3",
		"color" : "",
		"border-color" : ""
	},
	codeConfig : {
		"true" : {
			success : true,// 标识是否成功
			showToast : true,
			toastOptions : {
				backColor : "#51A351",
				defaultMsg : "操作成功！"
			}
		},
		"false" : {
			success : false,
			showToast : true,
			toastOptions : {
				backColor : "#d9534f",
				defaultMsg : "操作失败！"
			},
			callback : null
		},
		"error" : {
			success : false,
			showToast : true,
			toastOptions : {
				backColor : "#C9302C",
				defaultMsg : "操作异常！"
			},
			callback : null
		},
		"info" : {
			success : false,
			showToast : true,
			toastOptions : {
				backColor : "#2F96B4",
				defaultMsg : "无任何操作！"
			},
			callback : null
		},
		"defaults" : {
			success : false,
			showToast : true,
			toastOptions : {
				backColor : "#2F96B4",
				defaultMsg : "操作失败！"
			},
			callback : null
		}
	}
};