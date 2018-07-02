
function messagePrompt(result){
    var messageFlag=false;
    var message="操作失败!";
    if(result!=null){
        var flag=result.flag;
        if(flag!=null){
            messageFlag=flag;
        }
    }
    var resultmessage=result.message;
    if(resultmessage!=null){
        message=resultmessage
    }else{
        if(messageFlag){
            message="操作成功!";
        }
    }
    $(".messagePromptDiv").remove();

    var width = document.documentElement.clientWidth || document.body.clientWidth;
    var Fontsize = 18;
    if(width<415){
        width=width/2;
        var widths = width*2;
        var left=(widths-widths/2)/2;
        Fontsize = 14;
    }else if(width<980){
        width=width/3;
        var widths = width*3;
        var left=(widths-widths/3)/2;
        Fontsize = 18;
    }else{
        width=width/4;
        var widths = width*4;
        var left=(widths-widths/4)/2;
        Fontsize = 18;
    }
    //遮罩层样式
    var messagePromptStyle="filter:alpha(opacity=1);opacity:1;border:1px solid #009ACD;width:"+width+"px;padding-bottom: 8px; background-color: #F8F8FF;position: fixed;top: "+width+"px;left: "+left+"px;margin:0 auto;z-index:888888;display:none;border-radius:4px;"
    var messagePromptDiv='<div class="messagePromptDiv" style="'+messagePromptStyle+'">' +
        '<div style="height:40px;line-height: 40px; background-color: #009ACD;">' +
        '<span style="color:#FFFFFF;font-size: '+Fontsize+'px;margin-top: 5px;margin-left:10px; ">消息提示</span>'+
        '</div>' +
        '<div style="height:100px; line-height: 100px;text-align: center">' +
        '<p style="font-size: '+Fontsize+'px; color:#009ACD;">'+message+'</p>'+
        '</div>' +
        '</div>';
    $('body').append(messagePromptDiv);
    fadeClass("messagePromptDiv",1500);
    return messageFlag;
}

function closeMessagePrompt(){
    $(".messagePromptDiv").hide();
    $(".messagePromptDiv").remove();
}
//id淡入淡出
function fadeId(divId,time){
    $("#"+divId).fadeIn(time);
    $("#"+divId).fadeOut(time);
}
//class淡入淡出
function fadeClass(divClass,time){
    $("."+divClass).fadeIn(time);
    $("."+divClass).fadeOut(time);
}