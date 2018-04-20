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
	<form id="upload" class="upload" enctype="multipart/form-data">
		<input type="file" id="file" value=""/>
	</form>
	<button class="button" onclick="fnSubmit();">提交</button>
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
			url: 'http://localhost:8080/adlet/fileupload.do',
			type: 'POST',
			cache: false,
			data: new FormData($('#uploadForm')[0]),
			processData: false,
			contentType: false,
			}).done(function(res) {
				alert("尼玛，成功了");
			}).fail(function(res) {
				alert("你大爷 失败了");
			});
	
	};
</script>
</html>