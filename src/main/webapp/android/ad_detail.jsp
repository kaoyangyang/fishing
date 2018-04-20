<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
	width: 100%;
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
</style>
</head>
<body>
	<div class="contain">
		<div id="contain"></div>
		<div class="title" id="title"></div>
		<div class="contcontain">
			<div class="content" id="content"></div>
			<div id="image_container">
			<!-- <img class="imagesize" id="image" src="http://47.94.166.156:8080/adlet/upload/icon/52.png" id="adimg" /> -->
			</div>
		</div>
		<div class="address"> 
			<span id="address">地址:</span>
			<div class="tel">
				<a id="telnum" href="tada:tel/"><img class="phone" src="../image/phone.png"></a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../scripts/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/constantValue.js"></script>
<script type="text/javascript">

	//加载完页面即执行
	$(function(){
		fnLoadData();
		//alert(id);
	});

	//获取数据
	function fnLoadData(){
	  var id=${param.id};
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
		
		var address=document.getElementById("address");
		address.innerHTML='地址:'+json.data.address;
		var telnum=document.getElementById("telnum");
		telnum.href="tada:tel/"+json.data.tel;
		
		var image_container=document.getElementById("image_container");
		var photos=json.data.photos.split(',');
		for(var i=0;i<photos.length;i++){
			var img=document.createElement('img');
			img.className="imagesize";
			img.src=serverUrl+photos[i];
			image_container.appendChild(img);
		}
	}
</script>
</html>