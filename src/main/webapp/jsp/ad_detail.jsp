<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广告详情</title>
<style type="text/css">
.title {
	margin-top: 5px;
	font-family: 微软雅黑;
	font-size: 20px;
	line-height: 20px;
	font-weight: bold;
}

.contain {
	margin-top: 10px;
	margin-left: 8px;
	margin-right: 8px;
	font-family: 微软雅黑;
	font-size: 16px;
	margin-bottom: 60px;
}

.tel, .address {
	width: 100%;
	line-height: 25px;
	font-size: 16px;
	font-family: 微软雅黑;
}

.contcontain {
	margin-top: 10px;
}

.image {
	margin-top: 10px;
	width: 100%;
}

.imagesize {
	margin-top: 5px;
	margin-right:5px;
	width:200px;
}

.address {
	position: relative;
	width: 100%;
	height: 40px;
	text-indent: 0px;
	line-height: 40px;
	margin-top: 20px;
	color: #888;
	overflow: hidden;
	border-top: 1px solid #eee;
	border-bottom: 1px solid #eee;
}

.tel {
	position: absolute;
	right: 0px;
	top: 0px;
	width: 40px;
	height: 40px;
	line-height: 40px;
	border-left: 1px solid #eee;
}

.phone {
	margin-left: 8px;
	margin-top: 8px;
	width: 24px;
	height: 24px;
	text-align: center;
}
.btn_container{
	margin-top: 20px;
}
.shenheyiju{
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="contain">
		<div id="contain"></div>
		<div class="title" id="title"></div>
		<div class="contcontain">
			<div class="content" id="content"></div>
			<div id="image_container">
			 <!--  <img class="imagesize" id="image" src="http://47.94.166.156:8080/adlet/upload/icon/52.png" id="adimg" />-->
			</div>
		</div>
		<div class="btn_container">
			<button  onclick="fnPass();">通过</button>
			<button onclick="fnRefuse();">拒绝</button>
		</div>
		<div class="shenheyiju"> 审核依据：1、标题不能是标题党2、内容不能是虚假类的，尤其药品类的广告3、图片不能为色情，暴力</div>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="../js/constantValue.js"></script>
<script type="text/javascript">
    //当前广告的id
	var id;
    //是否是推送广告
    var ispush;
	//加载完页面即执行
	$(function(){
		id =${param.id};
		ispush=${param.ispush};
		//alert(ispush);
		fnLoadData();
		
	});

	//获取数据
	function fnLoadData(){
		$.ajax({
			url: "${pageContext.request.contextPath}/ad/getAdInfo.do?id="+id,
			type: "GET",
			timeout:10000,
			dataType:"json", 
		    //contentType: "application/json; charset=utf-8",
			success:function(json){
			    dataShow(json);
			}
		});
	};
	//展示数据
	function dataShow(json){
		document.getElementById("title").innerHTML=json.data.title;
		document.getElementById("content").innerHTML=json.data.content;
		var image_container=document.getElementById("image_container");
		var photos=json.data.photos.split(',');
		for(var i=0;i<photos.length;i++){
			var img=document.createElement('img');
			img.className="imagesize";
			img.src=serverUrl+photos[i];
			image_container.appendChild(img);
		}
	}
	//通过
	function fnPass(){
		//alert(id);
		$.ajax({
			url:"${pageContext.request.contextPath}/manager/passAd.do",
			type:"POST",
			data:{adid:id,ispush:ispush},
			dataType:"json",
			success:function(result){
                alert("审批成功！");
			},
			error:function(result){
                if(result.status==403){
                    alert("请先登录！");
                }else{
                    alert("网络异常！");
                }
			}
		});
	}
	//拒绝
	function fnRefuse(){
		$.ajax({
			url:"${pageContext.request.contextPath}/manager/refuseAd.do",
			type:"POST",
			data:{adid:id},
			dataType:"json",
			success:function(result){
                alert("审批成功！");
			},
			error:function(result){
                if(result.status==403){
                    console.log(result.getResponseHeader('CONTEXTPATH'));

                    console.log(result.getAllResponseHeaders());
                }else{
                    alert("网络异常！");
                }

			}
		});
	}
</script>
</html>