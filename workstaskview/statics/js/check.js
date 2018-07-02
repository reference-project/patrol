function Trim(str)
{
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//获取表单中所有data-id属性
function check_Form(formId){
    var checkflag=false;
    var count=1;
    $(formId).find("input,textarea,select").each(function(){
        count++;
        var data_check=$(this).attr("data-id");
        if(data_check==null){
            return true;//相当于 continue;
        }else{
            checkflag=checkElement(data_check,this);
            return checkflag;//相当于 break;
        }
    })
    return checkflag;
}
//验证
function checkElement(data_check,element){
    var reg="";
    message="";
    var value=Trim($(element).val());//获取元素的值
    if(data_check=='phone'){
        reg=/^[1][3,4,5,7,8][0-9]{9}$/;
        message="输入有误，请重新输入";
    }
    if(data_check=='idcode'){
        reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        message="输入有误，请重新输入";
    }
    if(data_check=='money'){
        reg = /^[0-9]*(\.[0-9]{1,2})?$/;
        message="输入有误（最多两位小数），请重新输入";
    }
    /**          验证*/
    if(data_check=='notnull'){
        message="请输入";
        var flag=false;
        if(value==""||value==null){
            var text=$(element).parent().siblings("label").text();
            checkMessage(message+text);
        }else{
            flag=true;
        }
        return flag;
    }
    return checkProperty(value,reg,message,element);
}
function checkProperty(value,reg,message,element){
    var flag=false;
    console.log($(element).siblings("label"));
    if(reg!=""){
        if(!reg.test(value)){
            var text=$(element).parent().siblings("label").text();
            checkMessage(text+message);
        }else{
            flag=true;
        }
    }else{
        flag=true;
    }
    return flag;
}
function checkMessage(message){
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

    $(".overlay").remove();
    $(".checkmessage").remove();
    //遮罩层样式
    var overlayStyle="position: fixed;top: 0px;left: 0px;right: 0px;bottom: 0px;text-align: center; z-index: 888888;" +
        "width:100%;height:100%;filter:alpha(opacity=60);background-color: #ABABAB;opacity:0.7;"
    var overlay='<div class="overlay"  style="'+overlayStyle+'" oncilck="closeOverlay()"></div>';
    //消息提示框样式
    var messageStyle="filter:alpha(opacity=1);opacity:1;border:1px solid red;width:"+width+"px; position: fixed;top: "+width+"px;left: "+left+"px; padding-bottom: 8px; background-color: #F8F8FF;margin:0 auto;z-index:888889;border-radius:4px;"
    var messageDiv='<div class="checkmessage" style="'+messageStyle+'">' +
        '<div style="height:40px;line-height: 40px; background-color: #FF4040;">' +
        '<span style="color:#FFFFFF;font-size: '+Fontsize+'px;margin-top: 5px;margin-left:10px; ">消息提醒</span>'+
        '</div>' +
        '<div style="padding: 40px 18px; text-align: center;">' +
        '<p style="font-size: '+Fontsize+'px;">'+message+'</p>'+
        '</div>' +
        '<div style="height:30px;"><button class="messagebtn" style="float: right;padding: 3px 8px; background-color: #ffffff; border: 1px solid #FF4040; border-radius: 3px; margin-right: 30px;" onclick="closeOverlay()">确 定</button></div>' +
        '</div>';
    $('body').append(messageDiv);
    $('body').append(overlay);
}
function closeOverlay(){
    $(".overlay").hide();
    $(".checkmessage").hide();
    $(".overlay").remove();
    $(".checkmessage").remove();
}

//日期比较
function CompareDate(d1,d2){
    return (new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/")));
}

//日期格式化（虽然写这里不合适）
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,         //月份
        "d+" : this.getDate(),          //日
        "h+" : this.getHours(),          //小时
        "m+" : this.getMinutes(),         //分
        "s+" : this.getSeconds(),         //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds()       //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}