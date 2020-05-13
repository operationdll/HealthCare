<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<link rel="stylesheet"
			href="<%=basePath%>bootstrap/css/bootstrap.min.css">
		<link type="text/css"
			href="<%=basePath%>bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet">
		<link type="text/css" href="<%=basePath%>index/css/theme.css"
			rel="stylesheet">
		<link type="text/css"
			href="<%=basePath%>index/images/icons/css/font-awesome.css"
			rel="stylesheet">
		<link type="text/css"
			href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600'
			rel='stylesheet'>

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
		
		/* 表单样式 */
		.data-grid {
			position:relative;
			overflow:hidden;
			font-size:14px;
			color:#444;
			box-sizing:border-box;
			border-color:#95B8E7;
			border-width:1px;
			border-style:solid;
		}
		.data-view {
			position:absolute;
			overflow:hidden;
			top:0px;
			right:0px;
		}
		.grid-head {
			border-color:#DDD;
			overflow:hidden;
			cursor:default;
			border-width:0px 0px 1px;
			border-style:solid;
			background-color:#efefef;
		}
		.grid-head-inner {
			display:block;
			float:left;
		}
		.data-table {
			border-collapse:separate;
		}
		.data-table td {
			border-color:#CCC;
			border-width:0px 1px 1px 0px;
			border-style:dotted;
			margin:0px;
			padding:0px;
		}
		.datagrid-cell {
			margin:0px;
			padding:0px 4px;
			font-size:12px;
		}
		.cell-c1 {
			width:40px;
			height:auto;
		    text-align:center;
		}
		.cell-c2 {
			width:160px;
			height:auto;
		    text-align:center;
		}
		.cell-c3 {
			width:60px;
			height:auto;
		    text-align:center;
		}
		.cell-c4 {
			width:100px;
			height:auto;
		    text-align:center;
		}
		.cell-c5 {
			width:50px;
			height:auto;
		    text-align:center;
		}
		.cell-c6 {
			width:100px;
			height:auto;
		    text-align:center;
		}
		.cell-c7 {
			width:100px;
			height:auto;
		    text-align:center;
		}
		.cell-c8 {
			width:150px;
			height:auto;
		    text-align:center;
		}
		.cell-c9 {
			width:150px;
			height:auto;
		    text-align:center;
		}
		.grid-body {
			overflow:auto;
			margin:0px;
			padding:0px;
		}
		.datagrid-btable {
			border-collapse:separate;
		}
		.datagrid-btable tr {
			height:25px;
		}
		.datagrid-btable tr td {
			border-color:#CCC;
			border-width:0px 1px 1px 0px;
			border-style:dotted;
			margin:0px;
			padding:0px;
		}
		
		/* 进度条 */
		.probarmain{
		    text-align: center; 
		    width: 300px;
		    height: 30px;
		    margin: auto;
		    position: absolute;
		    top: 0;
		    left: 0;
		    right: 0;
		    bottom: 0;
		}
		
		.probarmain div{
		   display: block;
		}
		
		.probarbg{
		    width:100%;
			position: relative;
			overflow: hidden;
			height: 30px;
			background-color: rgba(0,80,80,.3);
		}
		
		.probarmain span{
		    right: 0;
			color: #000;
			font-weight: 700;
			padding: 5px; 
			position: absolute;
			top: 0;
			z-index: 9;
		}
		
		.probarbar{
		    z-index: 8;
			position: absolute;
			top: 0;
			left: 0;
			height:30px;
			width: 0%;
			background-color:#177fcb;
		}
		
		.probarout {
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
			display:none;
		}
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<div class="content">
			<div class="content">
				<div class="module">
					<div class="module-head">
						<h3>
							省数据同步
						</h3>
					</div>
					<table border="0" width="100%">
						<tr>
							<td align="center">
								<label>体检日期</label>
							</td>
							<td align="left" width="150px">
								<input type="text" id="startDate" style="width:150px;" readonly="readonly"/>
							</td>
							<td align="center">
								<label>-----</label>
							</td>
							<td align="left" width="150px">
								<input type="text" id="endDate" style="width:150px;" readonly="readonly"/>
							</td>
							<td align="center">
								<label>责任医生</label>
							</td>
							<td align="left">
								<select style="width: auto;" id="doctor">
									<c:forEach items="${doctors}" var="doctor">
										<option value="${doctor.ID};${doctor.NAME}">${doctor.NAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left">
								<label>身份证号码</label>
							</td>
							<td align="left">
								<input id="idCard" type="text"/>
							</td>
							<td align="left">
								<label>卫生院</label>
							</td>
							<td align="left" colspan="3">
								<input type="text" value="${hname}" readonly="readonly"/>
								<a class="btn btn-success" ng-click="synData()" style="margin-left:2px;margin-top:-10px;">同步数据</a> 
							</td>
						</tr>
					</table>
				</div>
				<!--/.module-->
			</div>
		</div>
		<div id="rdiv" style="width:100%;height:100%;">
		</div>
		
		<!-- loading -->
		<div class="loading">
			<div class="loading_anim">
				<div class="loader" style="flex-grow: 1;"></div>
				<div
					style="flex-grow: 1; text-align: center; line-height: 52px; height: 52px;">
					数据提交中请稍后
				</div>
			</div>
		</div>
		
		<!-- progress bar -->
		<div class="probarout">
			<div class="probarmain">
				<div>
					<div class="probarbg">
					  <span>0%</span>
					  <div class="probarbar"></div>
					</div>
				</div>
			</div>
		</div>
		
		<input id="hcid" type="hidden" value="${hcid}"/>
		<input id="RegionID" type="hidden" value="${RegionID}">
		<input id="RegionCode" type="hidden" value="${RegionCode}">
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
		<script type="text/javascript">
			let ProgressTime = null;
			//请求后台查询进度
			function getProgress(){
		    	$.ajax({
					type: "POST",
					dataType: "json",
					url: "<%=basePath%>system/getProgress.do",
					data: {'sName':'SynProgress'},
					success: function(data){
						let v = data.num;
						$(".probarbg span").html(v);
				    	$(".probarbar").css({"width":v});
					}
				});
			}
		
			//初始化时间控件
			$(function(){
				//查看是否管理员
				$("#startDate").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#startDate").datepicker('setDate', new Date());
				$("#endDate").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#endDate").datepicker('setDate', new Date());
			});
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				$(".loading").hide();
			    //同步数据
			    $scope.synData = function() {
			    	//开始日期
			    	let startDate=$("#startDate").val();
			    	//结束日期
			    	let endDate=$("#endDate").val();
			    	//责任医生
			    	let doctor=$("#doctor").val();
			    	if(doctor==null||doctor==""){
			    		alert("请选择医生");
			    		return;
			    	}
			    	//责任医生编码
			    	let dcode = doctor.split(";")[0];
			    	//责任医生名称
			    	let dname = encodeURI(doctor.split(";")[1]);
			    	//身份证号码
			    	let idCard=$("#idCard").val();
			    	$("#rdiv").html("");
			    	//初始化进度条
			    	$(".probarbg span").html("0%");
			    	$(".probarbar").css({"width":"0%"});
			    	$(".probarout").show();
			    	ProgressTime = window.setInterval("getProgress();", 2000);
			    	//卫生院ID
			    	let hcid = $("#hcid").val();
			    	// 区域ID
					let RegionID = $("#RegionID").val();
					// 区域CODE
					let RegionCode = $("#RegionCode").val();
				    $http({
				        method : "POST",
				        url : "<%=basePath%>system/synData.do",
				        params: {startDate:startDate,endDate:endDate,hcid:hcid,dcode:dcode,dname:dname,idCard:idCard,RegionID:RegionID,RegionCode:RegionCode}
				    }).then(function mySucces(response) {
				    	if(ProgressTime!=null){
				    		clearInterval(ProgressTime);
				    	}
				    	//隐藏进度条
				    	$(".probarbg span").html("100%");
				    	$(".probarbar").css({"width":"100%"});
				    	$(".probarout").hide();
				    	if(response.data!=null&&response.data!=""){
				    		let rmsg = response.data.rmsg;
				    		if(rmsg==""){
				    			alert("同步成功!");
				    		}else{
				    			$("#rdiv").html(response.data.rmsg);
				    		}
				    	}else{
				    		alert("同步失败!");
				    	}
				    }, function myError(response) {
				    	$(".probarout").hide();
				        alert("system->synData.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
