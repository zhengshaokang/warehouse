var mypopup={
	log:function(obj,msg) {
		stopBubble(this);
		
		var id = "log"+(new Date()).getTime();
		var pop = $(".log_system_rm");
		if(pop.length >0) {
			var value = $(obj).val();
			var x = $(obj).offset().left;
			var y = $(obj).offset().top;
			x = x - 200;
			y = y - 60 ;
			var html = "<div id="+id+" class='log_system_rm' style='background-color: #ededed;text-align:left; border: 1px solid #ed9b5f; min-height: 200px;width: 200px;position: relative;top:"+y+"px;left:"+x+"px;z-index:10000009'>"+msg+"</div>";
			$(pop).each(function() {
				var oldid = $(this).attr("id");
				$(this).remove();
				if(oldid != value) {
					$("body").append(html);
					$(obj).attr("value",id)
					setTimeout(function(){mypopup.close(id);}, 60000);
				}
			})
		} else {
			var x = $(obj).offset().left;
			var y = $(obj).offset().top;
			x = x - 200;
			y = y - 60 ;
			var html = "<div id="+id+" class='log_system_rm' style='background-color: #ededed;text-align:left; border: 1px solid #ed9b5f; min-height: 200px;width: 200px;position: relative;top:"+y+"px;left:"+x+"px;z-index:10000009'>"+msg+"</div>";
			$("body").append(html);
			$(obj).attr("value",id)
			setTimeout(function(){mypopup.close(id);}, 60000);
		}
	},
	alert:function(msg){
		var id = "info"+(new Date()).getTime();
		var html = "<div id="+id+"><div class=\"prompt_bg\"></div>";
		html += "<div class=\"prompt_info_content\">";
		html += "<div class=\"prompt_info_msg\">"+msg+"</div>";
		html += "<div class=\"prompt_info_bottom\">";
		html += "<div class=\"prompt_info_btn\"><div class=\"prompt_info_btn_ok\" onclick=\"mypopup.close('"+id+"')\">确定</div></div></div></div></div>";
		$("body").append(html);
		setTimeout(function(){mypopup.close(id);}, 3500);
	},
	confirm:function(msg,callback,params){
		var id = "info"+(new Date()).getTime();
		var html = "<div id="+id+"><div class=\"prompt_bg\"></div>";
		html += "<div class=\"prompt_confirm_content\">";
		html += "<div class=\"prompt_confirm_msg\">"+msg+"</div>";
		html += "<div class=\"prompt_confirm_bottom\">";
		html += "<div class=\"prompt_confirm_btn\"><div class='prompt_confirm_btn_ok' id='promptCofirm_"+id+"'>确定</div></div>";
		html += "<div class=\"prompt_confirm_btn\"><div class='prompt_confirm_btn_close' onclick=\"mypopup.close('"+id+"')\">取消</div></div>";
		html += "</div></div></div>";
		$("body").append(html);
		$("#promptCofirm_"+id).click(function(){
			mypopup.ok(id,callback,params);
		});
		setTimeout(function(){mypopup.close(id);}, 3500);
	},
	close:function(id){
		$("#"+id).remove();
	},
	curclose:function(obj){
	    var id = $(obj).attr("value") ;
		$("#"+id).remove();
	},
	ok:function(id,callback,params){
		mypopup.close(id);
		if(typeof params !="undefined"){
			eval(callback+'(params)');
		} else {
			eval(callback+"()");
		}
	}
};

