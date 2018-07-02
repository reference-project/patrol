
   var url="http://192.168.1.201:8070/workstask/";
//http://182.92.208.25:8070/workstask/
   var oLanguageData = {
       "sProcessing" : "处理中...",
       "sLengthMenu" : "显示 _MENU_ 项结果",
       "sZeroRecords" : "没有匹配结果",
       "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
       "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
       "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
       "sInfoPostFix" : "",
       "sSearch" : "搜索:",
       "sUrl" : "",
       "sEmptyTable" : "表中数据为空",
       "sLoadingRecords" : "载入中...",
       "sInfoThousands" : "显示 _MENU_ 条",
       "oPaginate" : {
           "sFirst" : "首页",
           "sPrevious" : "上页",
           "sNext" : "下页",
           "sLast" : "末页"
       },
       "oAria" : {
           "sSortAscending" : ": 以升序排列此列",
           "sSortDescending" : ": 以降序排列此列"
       }
   };


	function initJs(fromName,modalName,divName){
		$("#"+divName).hide();
		$("#"+divName).fadeToggle(500);
		$("#"+fromName)[0].reset();
		$("#"+modalName).on('hide.bs.modal', function () {
			  // 执行一些动作...
              $("#"+fromName).clearfrom();
			  $("#"+fromName)[0].reset();
		});
	}

   /**
	*
    * @param tableId 要渲染的表单id
	* @param datas 要渲染的数据源
    * @param ids 想要隐藏的id列 如 id,fid
    * @param html 最后操作列的html代码如<button class="btn btn-" onclick="test(this)">
    * @constructor
    */
   function ShowDataTable (tableId,datas,ids){
       $("#"+tableId).dataTable();

       var jstr="[";
       $('#'+tableId+' th').each(function(){
           var jsons;
           jsons='{"mDataProp":"' +$(this).attr("name")+ '"}';
           if($(this).attr('name')==ids)
               jsons='{"mDataProp":"' +$(this).attr("name")+ '","bVisible":'+null+',}';
           jstr+=jsons+",";
       });
       jstr= jstr.substring(0,jstr.length-1)+"]";
       console.log("-------------"+jstr);

       $('#'+tableId).dataTable( {
           aaSorting: [[ 0, "desc" ]],
           sPaginationType: "full_numbers",
           searching:true, //去掉搜索框
           bLengthChange:true,//去掉每页多少条框体
           language: oLanguageData,
           destroy:true, //Cannot reinitialise DataTable,解决重新加载表格内容问题
           data: datas,// 后台传过来的json是字符串转成对象
           aoColumns:eval('('+jstr+')'),
       } );
   }


   function showHtmlRight(url,datas){
       var result;
       $.ajax({
           //返回相同的session
           xhrFields: {
               withCredentials: true
           },
           url:url,  //http://192.168.1.112:8181/customer/selectAllCustomer
           type:'POST', //http://localhost:8181/Region/selectAllRegion
           async:false,    //或false,是否异步
           data:datas,
           cache:false,
           processData: false,
           contentType: false,
           timeout:90000,    //超时时间
           dataType:'html',    //返回的数据格式：json/xml/html/script/jsonp/text
           beforeSend:function(xhr){
               console.log('发送前')
           },

           success:function(data,textStatus,jqXHR){

               result= data;

               //有数据显示table
               $("#rightContent").show();
               $("#rightContent").html('');
               $("#rightContent").html(data.substring(data.indexOf("<abbr>")+6,data.indexOf("</abbr>")));

               $(".modal-backdrop").remove();
           },
           error:function(xhr,textStatus){

               /*if(code==222){//1.请重新登录
                   loginAbnormal();
               }else{//2.系统异常，请联系管理员
                   systemAbnormal();
               }*/

           },
           complete:function(){

           }

       });
       return result;

   }


	function showHtml(url,datas){
		var result;
		$.ajax({
			//返回相同的session
            xhrFields: {
                withCredentials: true
            },
		    url:url,  //http://192.168.1.112:8181/customer/selectAllCustomer
		    type:'POST', //http://localhost:8181/Region/selectAllRegion
		    async:false,    //或false,是否异步
	    	data:datas,
	    	cache:false,
            processData: false,
            contentType: false,
		    timeout:10000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		    },
			
		    success:function(data,textStatus,jqXHR){

		    	result= data;
		    	
		    	//有数据显示table
		    	//$("#rightContent").show();
		    	//$("#"+div).html('');
			 	//$("#"+div).html(data.substring(data.indexOf("<abbr>")+6,data.indexOf("</abbr>")));
				
			 	//$(".modal-backdrop").remove(); 
		    },
		    error:function(xhr,textStatus){
                var code=xhr.status;
                console.log(code);
                if(code==222){//1.请重新登录
                    loginAbnormal();
                }else{//2.系统异常，请联系管理员
                    systemAbnormal();
                }
		    }
		});
		return result;
	}

   /**
    * ysk
    * 解决冲突
    * 加载提交数据
    */
   function addData(url,datas){
       var result;
       $.ajax({
           //返回相同的session
           xhrFields: {
               withCredentials: true
           },
           url:url,
           type:'POST',
           async:false,    //或false,是否异步
           data:datas,
           cache:false,
           timeout:10000,    //超时时间
           dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
           beforeSend:function(xhr){
               console.log(xhr)
               console.log('发送前')
           },

           success:function(data,textStatus,jqXHR){

               result= data;

           },
           error:function(xhr,textStatus){

               var code=xhr.status;
              /* if(code==222){//1.请重新登录
                   loginAbnormal();
               }else{//2.系统异常，请联系管理员
                   systemAbnormal();
               }*/
           },
           complete:function(){

           }

       });
       return result;

   }


	
	/**
	 * sqz
	 * 清空from 表单，但保留 checke的值
	 */
	$.fn.clearfrom=function(){
		$(this)[0].reset();
		/*:hidden, */
		$(this).find("input").not(":button, :submit, :reset, :checkbox, :radio").val("");  
		$(this).find("input").removeAttr("checked").remove("selected");  
	}
	
	/**sqz
	 * 将form里面的内容序列化成json
	 * */
	$.fn.serializeJson=function(otherString){
	  /*var serializeObj={},
	    array=this.serializeArray();
	  $(array).each(function(){
	    if(serializeObj[this.name]){
	      serializeObj[this.name]+=','+this.value;
	    }else{
	      serializeObj[this.name]=this.value;
	    }
	  });*/
        /*
         if(otherString!=undefined){
           var otherArray = otherString.split(';');
           $(otherArray).each(function(){
             var otherSplitArray = this.split(':');
             serializeObj[otherSplitArray[0]]=otherSplitArray[1];
           });
         }*/
	  return new FormData(this[0]);
	};
	 
	/**sqz
	 * 将josn对象赋值给form
	 * */
	$.fn.setForm = function(jsonValue){
	  var obj = this;
	  $.each(jsonValue,function(name,ival){
	    var $oinput = obj.find("input[name="+name+"]");
	    if($oinput.attr("type")=="checkbox"){
	      if(ival !== null){
	        var checkboxObj = $("[name="+name+"]");
	        var checkArray = ival.split(";");
	        for(var i=0;i<checkboxObj.length;i++){
	          for(var j=0;j<checkArray.length;j++){
	            if(checkboxObj[i].value == checkArray[j]){
	              checkboxObj[i].click();
	            }
	          }
	        }
	      }
	    }
	    else if($oinput.attr("type")=="radio"){
	      $oinput.each(function(){
	        var radioObj = $("[name="+name+"]");
	        for(var i=0;i<radioObj.length;i++){
	          if(radioObj[i].value == ival){
	            radioObj[i].click();
	          }
	        }
	      });
	    }
	    else if($oinput.attr("type")=="textarea"){
	      obj.find("[name="+name+"]").html(ival);
	    }
	    else{
	      obj.find("[name="+name+"]").val(ival);
	    }
	  });
	};


   //提示信息确定
   function is_hide(){ $(".alert").animate({"top":"-300%"}, 300); };
   function is_show(varId,message,flag){
       $("#password").blur();
       $(varId).html("");
       $(varId).html(message);
       if(flag){
           $(".ts").show().animate({"top":"0%"}, 300);
       }else{
           $(".sx").show().animate({"top":"0%"}, 300);
       }
   };

    //自定义模态框
   function loginAbnormal() {
       $(".myModal").show().animate({"top":"0%"}, 500);
       $(".myInfo").text("尚未登录，请重新登录");
   }
   function systemAbnormal() {
       $(".myModal").show().animate({"top":"0%"}, 500);
       $(".myInfo").text("请重新登录");
   }
