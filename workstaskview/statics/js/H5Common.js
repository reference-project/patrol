var wxTrue=false;
//自适应
(function() {
    var html = document.documentElement;
    var width = html.getBoundingClientRect().width;
    html.style.fontSize = width / 15 + 'px';
    //至于除数15可自行设置
    //1rem=50;
})()

$(function(){

    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    var url = localhostPaht+projectName;
    if(url.indexOf("/code")==-1){
        url+="/code";
    }
    //底部动态显示
    //foot(url);

    //判断是否为微信浏览器
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        wxTrue=true;
        $("header").remove();
        $(".container-body").css("top","0rem");

    }else{
        wxTrue=false;
        $(".container-top").show();

    }
})
//追加底部
function foot(url){
    var divClass=$('body').find('div:first').attr("class");
    var html='<nav class="mui-bar mui-bar-tab">' +
        '<a class="mui-tab-item" onclick=window.location="'+url+'/shopPosition.html">' +
        '<span class="mui-icon icon-shopping"></span>' +
        '<span class="mui-tab-label">买东西</span>' +
        '</a>' +
        '<a class="mui-tab-item" onclick=window.location.href="'+url+'/oneCard.html">' +
        '<span class="mui-icon icon-one-card"></span>' +
        '<span class="mui-tab-label">一卡通</span>' +
        '</a>' +
        '<a class="mui-tab-item" onclick=window.location.href="'+url+'/findNurse.html">' +
        '<span class="mui-icon icon-find-nurse"></span>' +
        '<span class="mui-tab-label">找护工</span>' +
        '</a>' +
        '<a class="mui-tab-item" onclick=window.location.href="'+url+'/index.html">' +
        '<span class="mui-icon icon-home"></span>' +
        '<span class="mui-tab-label">首页</span>' +
        '</a>' +
        '</nav>'

    $("."+divClass).after(html);
}