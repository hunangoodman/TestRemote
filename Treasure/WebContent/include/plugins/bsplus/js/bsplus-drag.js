var BsplusDrag = function($, $common) {// 拖拽
	var $defaults = {
		handle : null, // 点击哪个元素开始拖动,可为空。不为空时，需要为被拖动元素的子元素。
		onDrag : null, // 拖动时触发的回调。
		direction : 'all', // 拖动方向：['all','vertical','horizontal']
		outParent : false,// 是否允许拖拽出父级标签
		dragArea : null
	// 限制在哪个区域拖动,以数组形式提供[minX,maxX,minY,maxY]
	}, mousePosotion = function(ev) {// 获取鼠标当前坐标
		if (ev.pageX || ev.pageY) {
			return {
				x : ev.pageX,
				y : ev.pageY
			};
		}
		return {
			x : ev.clientX + document.body.scrollLeft - document.body.clientLeft,
			y : ev.clientY + document.body.scrollTop - document.body.clientTop
		};
	}, $draging = false, $opts, $currentDrag, $handle, $mp, $params = {// initDiffX|Y
		// :
		// 初始时，鼠标与被移动元素原点的距离
		// moveX|Y : 移动时，被移动元素定位位置 (新鼠标位置与initDiffX|Y的差值)
		// 如果定义了移动中的回调函数，该对象将以参数传入回调函数。
		initDiffX : '',
		initDiffY : '',
		moveX : '',
		moveY : ''
	};
	$(window).mousemove(function(e) {
		if ($draging) {
			// 被移动元素的新位置，实际上鼠标当前位置与原位置之差
			// 实际上，被移动元素的新位置，也可以直接是鼠标位置，这也能体现拖拽，但是元素的位置就不会精确。
			$mp = mousePosotion(e);
			$params.moveX = $mp.x - $params.initDiffX;
			$params.moveY = $mp.y - $params.initDiffY;
			// 是否限定在某个区域中移动.
			// dragArea格式: [x轴最小值,x轴最大值,y轴最小值,y轴最大值]
			if ($opts.dragArea && $common.isArray($opts.dragArea) && $opts.dragArea.length == 4) {
				if ($params.moveX < $opts.dragArea[0]) {
					$params.moveX = $opts.dragArea[0]
				}
				if ($params.moveX > $opts.dragArea[1]) {
					$params.moveX = $opts.dragArea[1]
				}
				if ($params.moveY < $opts.dragArea[2]) {
					$params.moveY = $opts.dragArea[2]
				}
				if ($params.moveY > $opts.dragArea[3]) {
					$params.moveY = $opts.dragArea[3]
				}
			}
			// 移动方向：可以是不限定、垂直、水平。
			if ('all' == $opts.direction) {
				$currentDrag.css({
					'left' : $params.moveX,
					'top' : $params.moveY
				});
			} else if ('vertical' == $opts.direction) {
				$currentDrag.css('top', $params.moveY);
			} else if ('horizontal' == $opts.direction) {
				$currentDrag.css('left', $params.moveX);
			}
			// 如果有回调
			if ($opts.onDrag) {
				// 将$params作为参数传递
				$opts.onDrag.call($opts.onDrag, $params);
			}
		}
	});
	// 鼠标弹起时，标记为取消移动
	$(window).mouseup(function(e) {
		if ($draging) {
			$draging = false;
			$handle[0].releaseCapture && $handle[0].releaseCapture();
		}
	});
	return {
		name : "BsplusDrag",
		init : function(o) {
			if (!this.hasClass("bsplus-drag")) {
				this.addClass("bsplus-drag");
			}
			if (o && "object" === typeof o) {// 初始化data
				for ( var n in o) {
					this.data(n, o[n]);
				}
			}
			this.each(function() {
				var $this = $(this), $do, opts;// 是否正在拖动
				if ($this.data("inited")) {
					return false;
				}
				opts = {
					handle : $this.data("drag-handle") || "",
					onDrag : $this.data("ondrag") || null,
					direction : $this.data("direction") || 'all',
					outParent : $this.data("out-parent") || false,
					dragArea : $this.data("drag-area") || null
				}
				opts = $.extend({}, $defaults, opts);
				if (!opts.dragArea && !opts.outParent) {
					var $parent = $this.parent();
					if ($parent && $parent.length > 0) {
						opts.dragArea = [ 0, $parent.width() - $this.width(), 0, $parent.height() - $this.height() ];
					}
				}
				// 点击哪个元素，以触发移动。
				// 该元素需要是被移动元素的子元素（比如标题等）
				var handle = opts.handle ? $(opts.handle, $this) : $this;
				if (!handle || handle.length <= 0) {
					$common.log('handle is not found! the element must be a child of ' + this.id);
					return false;
				}
				// 被移动元素，需要设置定位样式，否则拖拽效果将无效。
				"static" == $this.css('position') && $this.css('position', 'absolute');
				// 点击时，记录鼠标位置
				handle.addClass("drag-handle").bind('mousedown', function(e) {
					// 标记开始移动
					$opts = opts, $currentDrag = $this, $draging = true, $handle = handle;
					// 改变鼠标形状
					$this.css('cursor', 'move');
					// 捕获事件。（该用法，还有个好处，就是防止移动太快导致鼠标跑出被移动元素之外）
					handle[0].setCapture && handle[0].setCapture();
					// （实际上是鼠标当前位置相对于被移动元素原点的距离）
					$mp = mousePosotion(e);
					$params.initDiffX = $mp.x - $this.position().left;
					$params.initDiffY = $mp.y - $this.position().top;
				});
				// 移动过程
				$this.data("inited", true);
			});
			return this;
		}
	}
}($, Common);