$(function(){
	$('.upload-btn' ).on('click',function(){
        var this_ = $(this );
        var ua = navigator.userAgent.toLowerCase();
        var isiOS = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);  // ios终端
        if(!isiOS){
            this_.next("input").attr('capture','camera');
        }
        this_.next("input").fadeOut();
        // 这里是为了能够多次选定同一张图片
        this_.after('<input type="file" class="hide" id="upimg4" accept="image/*">');
       
        this_.next("input").click().off("change").on('change',function(){
            var size = (this_.next("input")[0].files[0].size / 1024).toFixed(2);
            if(size <= 1024){
                var img = this_.next("input")[0].files[0];
                var formData = new FormData();
                formData.append("picture",img);
                formData.append("picType","3");
                uploadPic(formData,this_);
            } else {
            	mypopup.alert("您的图片超过1M");
            }
        });
    });
    $("#orderSubmit").click(function() {
    	var imglen = $(".upload-img").length;
    	if(imglen == 0) {
    		mypopup.alert("请上传图片");
    		return false;
    	}
    	var orderNo = $("#orderNo").val();
    	if(orderNo == "") {
    		mypopup.alert("请输入订单号");
    		return false;
    	}
    	var pics ="";
    	$(".upload-img").each(function(){
		    pics += $(this).attr("picUrl")+",";
		});
    	if(pics != "") {
    	  pics = pics.substring(0,pics.length -1);
    	}
    	var param = {
    		orderNo:orderNo,
    		picUrl:pics,
    		userId:$("#userId").val()
    	}
    	orderNoSubmit(param);
    });
    
    $("#reback").click(function() {
    	$(".index2").hide();
    	$(".index").show();
    	$("#orderNo").val("");
    	window.location.reload();
    })
})
function orderNoSubmit(param){
	$.ajax({
        type:"post",
        url:"wxaddpicsubmit",
        data:param,
        dataType:"json",
        success:function(data){
            if(data.statusCode == 200){
            	$(".index2").show();
            	$(".index").hide();
            	//mypopup.alert("提交成功  我们已收到您的信息，请联系商家审核");
            	//window.location.reload();
            } else {
            	mypopup.alert(data.message);
            }
        },
        error:function(data){
        	mypopup.alert(JSON.stringify(data));
        }
    });
}

function uploadImageRemove(obj){
	$(obj).parent().remove();
}


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
           // alert(data)
           // swal.close();
            var msg = $.parseJSON(data);
            if(msg.returnCode == 1){
            	var imgPath = msg.returnPath.split(".");
            	var newPath = imgPath[0].substring(0,imgPath[0].length) +"60."+imgPath[1];
				var html = '<div class="upload-itme"><div onclick="uploadImageRemove(this)" class="image-close">x</div>'
					html +='<div class="upload-img" picUrl="'+msg.returnPath+'" style="background: url('+picUrl+newPath+')  no-repeat center center;background-size:contain;"></div></div>';
				$(".upload_c").prepend(html);
				
				 var imglen = $(".upload-img").length;
			     if(imglen == 3) {
			        	$(".upload-c1").remove();
			        	//return false;
			     }
            } else {
                return '';
            }
        },
        error:function(data){
            alert(JSON.stringify(data));
        }
    });
}