<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传</title>
<style type="text/css">
	body{
		margin:0 0;
		padding:0 0;
	}
	.upload{
		width:100px;
		height:50px;
	}
	.button{
		width:100px;
		height:50px;
	}
</style>
</head>
<body>
	<form id="uploadForm" enctype="multipart/form-data">
		标题：<input type="text" id="title" name="title"><br/>
	            内容：<input type="text" id="content" name="content"><br/>
	            电话：<input type="text" id="tel" name="tel"><br/>
	            地址：<input type="text" id="addr" name="address"><br/>
	        用户id<input type="text" id="addr" name="user_id"><br/>
	        经度<input type="text" id="addr" name="lng"><br/>
	        纬度<input type="text" id="addr" name="lat"><br/>
	        省<input type="text" id="addr" name="province"><br/>
	        城市<input type="text" id="addr" name="city"><br/>
	        县<input type="text" id="addr" name="county"><br/>
	    <input  type="file" name="file1"/>
	    <input  type="file" name="file2">
	</form>
	<button id="upload" type="button" onclick="fnSubmit();">upload</button>
</body>
<script type="text/javascript" src="../scripts/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    //加载完页面即执行
	$(function(){
		//alert("你阿妈");
	});
	//提交数据
	function fnSubmit(){
		//alert($("#upload").val());
		$.ajax({
			url: 'http://localhost:8080/adlet/ad/stickAd.do',
			type: 'POST',
			cache: false,
			data: new FormData($('#uploadForm')[0]),
			processData: false,
			contentType: false,
			}).done(function(res) {
				alert(res);
			}).fail(function(res) {
				alert("失败");
		});
	};
</script>
</html>