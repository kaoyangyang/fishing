<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.js"></script>
<title>管理页面</title>
<style type="text/css">
	body{margin:0 0;}
	
	.header{
		height:100px;
		margin:0 0;
	    text-align:center;
		background: #1296db;
		line-height:100px;
		padding:5px;
		color:#fff;
	}
	
	.description{
		margin-top:10px;
		margin-bottom:10px;
		margin-left: 20px;
		margin-right: 20px;
	}
	
	.userName{
		margin-left: 10px;
	}
	
	.container{
		height:100px;
		margin-left: 20px;
		margin-right: 20px;
	}
	
	.tb_border{
		border-right: 1px solid #804040;
		border-bottom: 1px solid #804040;
		border-collapse:collapse;
	}
	
	.tb_border td{
		border-left: 1px solid #804040;
		border-top: 1px solid #804040;
	}
	
	table td{
		text-align: center;
		padding:5px;
	}
	table div{
		height:25px;
		line-height:25px;
		overflow: hidden;
	}
	
	.td_content{
		width:40%;
	}
	
	.td_title{
		width:20%;
	}
	
	.td_area{
		width:10%;
	}
	
	.td_publish_time{
		width:200px;
	}
	.isPush{
		width:100px;
	}

	.check_span{
		color:white;
		border-radius:5px;
		background: #1296db;
		padding:2px;
	}
	
	.image{
		width:100px;
		height: 100px;
	}
	.red{
		color:#ff0000;
	}
</style>
</head>
<body>
<h1 class="header">小广告内容审核平台</h1>
	<div class="description">
		<span class="areaName">全国</span><span class="userName">超级管理员：张三</span>
	</div>
	<div class="container">
		<table class="tb_border" id="tb_container">
			<tr>
				<td class="td_title">
					<div>标题</div>
				</td>
				<td class="td_content">
					<div >内容</div>
				</td>
				<td class="td_area">
					<div>发布区域</div>
				</td>
				<td class="td_publish_time">
					<div>发布时间</div>
				</td>
				<td class="isPush">
					<div>是否是推送</div>
				</td>
				<td>
					<div class="option">操作</div>
				</td>
			</tr>
		</table>
	</div>
</body>

<script type="text/javascript">

//加载完页面即执行
$(function(){
	fnLoadData();
	//window.location.href="./jsp/login.jsp";//需要跳转的地址
});

//获取数据
function fnLoadData(){
    <%--var id=${param.id};--%>
	$.ajax({
		url: "${pageContext.request.contextPath}/manager/getMyAreaNeedCheckAd.do",
		type: "POST",
		dataType:"json",
	    //contentType: "application/json; charset=utf-8",
		success:function(result){
		    console.log(result);
		    if(result.status==1){
                fnShowData(result);
            }else{
                window.location.href="${pageContext.request.contextPath}/login.do";//需要跳转的地址
            }
		},
		error:function(result){
//			alert(result.status);
		}
	});
};

//展示数据
function fnShowData(result){
	var tb_container=$("#tb_container");
	var resultJson=result.data;
	for(var i=0;i<resultJson.length;i++){
		var timeStamp=new Date(resultJson[i].pull_time);
		var month=timeStamp.getMonth()+1;
		var time=timeStamp.getFullYear()+'-'+month+'-'+timeStamp.getDate()+' '+timeStamp.getHours()+':'+timeStamp.getMinutes()+':'+timeStamp.getSeconds();
		var html='';
		html+='<tr>';
		html+='<td class="td_title">';
		html+='	<div>'+resultJson[i].title+'</div>';
		html+='</td>';
		html+='<td class="td_content">';
		html+='	<div >'+resultJson[i].content+'</div>';
		html+='</td>';
		html+='<td class="td_area">';
		html+='	<div>'+resultJson[i].publishAreaName+'</div>';
		html+='</td>';
		html+='<td class="td_publish_time">';
		html+='	<div>'+time+'</div>';
		html+='</td>';
		html+='<td class="isPush" >';
		if(resultJson[i].ispush==0){
			html+='	<div>否</div>';
		}else{
			html+='	<div class="red">是</div>';
		}
		html+='</td>';
		html+='<td class="check">';
		html+='<div class="check">';
		html+='	<span class="check_span" id="'+resultJson[i].id+','+resultJson[i].ispush+'" onclick="fnCheck(this);">详情</span>';
		html+='</div>';
		html+='</td>';
		html+='</tr>';
		tb_container.append(html);
		//alert(resultJson[i].ispush);
	}
}

//获取详情
function fnCheck(one){
//	alert(one.id);
	var values=one.id.split(',');
	window.location.href="${pageContext.request.contextPath}/manager/tocheck.do?id="+values[0]+"&ispush="+values[1];
}


</script>
</html>