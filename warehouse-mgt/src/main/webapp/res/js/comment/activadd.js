$(function(){
	$(".upload-wrap").each(function() {
		$(this).on('click',function(){
	        var this_ = $(this );
	        var ua = navigator.userAgent.toLowerCase();
	        var isiOS = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);  // ios终端
	        if(!isiOS){
	            this_.next("input").attr('capture','camera');
	        }
	        this_.next("input").fadeOut();
	        // 这里是为了能够多次选定同一张图片
	        this_.after('<input type="file" style="display:none" id="upimg4" accept="image/*">');
	        this_.next("input").click().off("change").on('change',function(){
	            var size = (this_.next("input")[0].files[0].size / 1024).toFixed(2);
	            if(size <= 1024){
	                var img = this_.next("input")[0].files[0];
	                var formData = new FormData();
	                formData.append("picture",img);
	                formData.append("picType","4");
	                uploadPic(formData,this_);
	            } else {
	            	alertMsg.alert("您的图片超过1M");
	            }
	        });
	    });
	})
	
})

var uploadPic = function(formData,this_){
    $.ajax({
        type:"post",
        url:"fileupload/uploadpic",
        data:formData,
        cache: false,
        processData : false,
        contentType : false,
        beforeSend: function(XMLHttpRequest){
            $('.swal2-confirm' ).css({'background-color':'#c1c1c1','border-left-color':'#c1c1c1','border-right-color':'#c1c1c1'})
        },
        success:function(data){
            var msg = $.parseJSON(data);
            if(msg.returnCode == 1){
            	this_.css({"background":"url('"+picUrl+msg.returnPath+"') no-repeat center center","background-size":"contain"});
            	this_.find("input").attr("picUrl",msg.returnPath);
            } else {
                return '';
            }
        },
        error:function(data){
            alert(JSON.stringify(data));
        }
    });
}