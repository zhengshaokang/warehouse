function stopBubble(event){
	event = event || window.event;
    if(event.stopPropagation){
        event.stopPropagation();
    }else{
        event.cancelBubble = true;
    }
}

function stopDefault( e ) {
	event = event || window.event;
    if(event.preventDefault){
            event.preventDefault();
    }else{
            event.returnValue = false;
    }
}

function countDays(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
    
	var d1 = new Date(sDate1.replace(/-/g,"/"));
	var d2 = new Date(sDate2.replace(/-/g,"/"));
	
	var iDays = (d2-d1)/(1000 * 60 * 60 *24) ;
	
    return  iDays  
}   

function getSessionId(){
   var c_name = 'JSESSIONID';
   if(document.cookie.length>0){
      c_start=document.cookie.indexOf(c_name + "=")
      if(c_start!=-1){ 
        c_start=c_start + c_name.length+1 
        c_end=document.cookie.indexOf(";",c_start)
        if(c_end==-1) c_end=document.cookie.length
        return unescape(document.cookie.substring(c_start,c_end));
      }
   }
}



	 