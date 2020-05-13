<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<body ng-app="myApp" ng-controller="myCtrl">
		<div class="mytitle">
			<h3>
            	修改检验员
            </h3>
        </div>
		<table width="100%" height="100%" border="0">
			<tr height="30px">
				<td align="center">
					体检日期
				</td>
				<td align="left">
					<input type="text" id="field1" readonly="readonly" style="width:200px;"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					血常规检查员
				</td>
				<td align="left">
					<input type="text" id="field14" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					尿常规检查员
				</td>
				<td align="left">
					<input type="text" id="field247" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					生化检查员
				</td>
				<td align="left">
					<input type="text" id="field248" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					心电图检查员
				</td>
				<td align="left">
					<input type="text" id="field249" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					B超检查员
				</td>
				<td align="left">
					<input type="text" id="field250" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					建档人
				</td>
				<td align="left">
					<input type="text" id="fileuser" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					责任医生
				</td>
				<td align="left">
					<input type="text" id="doctor" style="width:200px;" value=""/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" colspan="2">
					<button class="btn btn-success" ng-click="save()">
						录入
					</button>
				</td>
			</tr>
			<tr height="100%;">
				<td align="center" colspan="2" style="border:0px;">
					&nbsp;
				</td>
			</tr>
		</table>   
		<!-- loading -->
		<div class="loading" ng-show="loadingShow">
			<div class="loading_anim">
				<div class="loader" style="flex-grow: 1;"></div>
				<div
					style="flex-grow: 1; text-align: center; line-height: 52px; height: 52px;">
					数据提交中请稍后
				</div>
			</div>
		</div>
		<input id="ids" type="hidden" value="${ids}"/>
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
  		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
		<script type="text/javascript">
			//初始化时间控件
			$(function(){
				$("#field1").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#field1").datepicker('setDate', new Date());
			});	
		
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				$scope.loadingShow = false;
				//保存
			    $scope.save = function() {
			    	$scope.loadingShow = true;
					let field14 = encodeURI($("#field14").val());
					let field247 = encodeURI($("#field247").val());
					let field248 = encodeURI($("#field248").val());
					let field249 = encodeURI($("#field249").val());
					let field250 = encodeURI($("#field250").val());
					let field1 = $("#field1").val();
					let ids = $("#ids").val();
					let fileuser = encodeURI($("#fileuser").val());
					let doctor = encodeURI($("#doctor").val());
					//拼装参数
					let healthCheckDto = {field14:field14,field247:field247,
								field248:field248,field249:field249,field250:field250,
								field1Str:field1,ids:ids,fileuser:fileuser,doctor:doctor
						};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>print/updAllCP.do",
				        params: healthCheckDto
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("print->updAllCP.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
