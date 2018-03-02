window.BsplusPhotos = function($, $common) {// 相册
	return {
		name : "BsplusPhotos",
		show : function(source) {
			$("body").append('<div class="bsplus-photos"></div>');
		},
		hide : function() {

		}
	}
}(jQuery, BsplusCommon);