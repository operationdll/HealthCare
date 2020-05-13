<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>智慧健康云平台</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<!-- Bootstrap Core CSS -->
	    <link href="<%=basePath%>backEnd/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	    <!-- MetisMenu CSS -->
	    <link href="<%=basePath%>backEnd/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="<%=basePath%>backEnd/dist/css/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="<%=basePath%>backEnd/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->

		<!-- datepicker -->
		<link rel="stylesheet" href="<%=basePath%>js/datepicker/css/datepicker.css">
		<style>
		body {
			background-color: white;
		}
		.loader {
			border: 16px solid #f3f3f3;
			border-radius: 50%;
			border-top: 16px solid #3498db;
			width: 10px;
			height: 10px;
			-webkit-animation: spin 2s linear infinite; /* Safari */
			animation: spin 2s linear infinite;
		}
		
		/* Safari */
		@-webkit-keyframes spin {
		    0% { -webkit-transform: rotate(0deg); }
		    100% { -webkit-transform: rotate(360deg); }
		}
		
		@keyframes spin {
		    0% { transform: rotate(0deg); }
		    100% { transform: rotate(360deg); }
		}
		
		.loading {
			position: absolute;
			left: 0;
			top: 0;
			z-index: 1000;
			width: 100%;
			height: 100%;
			min-height: 100%;
			background: white;
			opacity: 0.8;
			text-align: center;
			color: Teal;
		}
		
		.loading_anim {
			position: absolute;
			display: flex;
			left: 50%;
			top: 50%;
			transform: translate(-50%, -50%);
			z-index: 1010;
		}
		
		.dropdown-menu{
			display:block;
		}
		
		.mytitle{
			color: black;
		    background-color: #f6f6f6;
		    border-color: #e9e9e9;
		    padding: 10px 15px;
		    border-bottom: 1px solid transparent;
		    border-top-right-radius: 3px;
		    border-top-left-radius: 3px;
		}
		
		.mytitle h3{
			font-size: 14px;
			line-height: 20px;
			height: 20px;
			margin: 0;
			font-weight: bold
		}
		
		table td{
			border:1px solid #E6E6FA;
			padding:2px;
		}
		
		table input{
			width:100%;
			height:28px;
		}
		
		table select{
			width:100%;
			height:28px;
		}
	</style>
	</head>
	<body>
		<div class="mytitle">
			<h3>
            	身份证扫描
            </h3>
        </div>
		<table width="100%" height="100%" border="0">
			<!--<tr>
				<td align="center">
					身份证照片
				</td>
				<td align="left">
					<img id="idImg" style="width:102px;height:126px;"/>
				</td>
			</tr>-->
			<tr height="30px">
				<td align="center">
					条码
				</td>
				<td align="left">
					<input type="text" readonly="readonly" id="itemCode" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					姓名
				</td>
				<td align="left">
					<input type="text" readonly="readonly" id="itemName" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					性别
				</td>
				<td align="left">
					<input type="text" readonly="readonly" id="itemSex" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					民族
				</td>
				<td align="left">
					<input type="text" readonly="readonly" id="itemNationality" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					出生日期
				</td>
				<td align="left">
					<input type="text" readonly="readonly" id="itemBirthdate" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					身份证号
				</td>
				<td align="left">
					<input type="text" readonly="readonly" id="itemIdcard" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					地址
				</td>
				<td align="center">
					<input type="text" readonly="readonly" id="itemAddress1"/>
				</td>
			</tr>
			<tr height="100%;">
				<td align="center" colspan="2" style="border:0px;">
					&nbsp;
				</td>
			</tr>
		</table>   
		<a style="display:none;" id="toDoBnt"></a>
		<input id="hcid" type="hidden" value="${user.hcid}"/>
		<input id="oldImg" type="hidden" value=""/>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#toDoBnt").attr("href","IdReader://"+$("#hcid").val());
				$("#toDoBnt")[0].click();
				setInterval(function(){
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "<%=basePath%>patient/scanGetPatient.do?hcid="+$("#hcid").val(),
						success: function(data){
							if(data.datas!=undefined){
								//条码
								let itemCode = changeNullValue(data.datas.code);
								$("#itemCode").val(itemCode);
								//姓名
								$("#itemName").val(changeNullValue(data.datas.name));
								//性别(1男2女3未说明的性别4未知的性别)
								let sex = changeNullValue(data.datas.sex);
								if(sex!=''){
									sex = Number(sex);
									let sexText = "";
									if(sex == 1){
										sexText = "男";
									}else if(sex == 2){
										sexText = "女";
									}else if(sex == 3){
										sexText = "未说明的性别";
									}else if(sex == 4){
										sexText = "未知的性别";
									}
									$("#itemSex").val(sexText);
								}
								//民族
								$("#itemNationality").val(changeNullValue(data.datas.nationality));
							 	//出生日期
							 	let birthdate = timestampToTime(changeNullValue(data.datas.birthdate));
							 	$("#itemBirthdate").val(birthdate);
							 	//身份证
							 	$("#itemIdcard").val(changeNullValue(data.datas.idcard));
							 	//地址
							 	$("#itemAddress1").val(changeNullValue(data.datas.address1));
							 	//如果身份证已经存在提示信息
							 	if(data.msg!=undefined&&data.msg==1){
							 		setTimeout(function(){ 
										alert("身份证信息已存在!");
									}, 1000);
							 	}
							}
						}
					});
				}, 2000);
			});
		</script>
	</body>
</html>
