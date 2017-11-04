
window.soso = window.soso || {};
soso.maps = soso.maps || {};

(function() {
    soso.maps.__load = function(apiLoad){
        delete soso.maps.__load;
        apiLoad(["http://api.map.soso.com/","v2.0.9","",1399637308524,true,"","20130701"], loadScriptTime);
    };
    var loadScriptTime = (new Date).getTime();
    
})();


