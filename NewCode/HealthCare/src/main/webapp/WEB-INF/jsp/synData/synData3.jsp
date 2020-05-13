<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<link rel="stylesheet"
	href="<%=basePath%>js/datepicker/css/datepicker.css">
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
@
-webkit-keyframes spin { 0% {
	-webkit-transform: rotate(0deg);
}

100%
{
-webkit-transform












:






 






rotate












(360
deg










);
}
}
@
keyframes spin { 0% {
	transform: rotate(0deg);
}

100%
{
transform












:






 






rotate












(360
deg










);
}
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

.dropdown-menu {
	display: block;
}

/* 表单样式 */
.data-grid {
	position: relative;
	overflow: hidden;
	font-size: 14px;
	color: #444;
	box-sizing: border-box;
	border-color: #95B8E7;
	border-width: 1px;
	border-style: solid;
}

.data-view {
	position: absolute;
	overflow: hidden;
	top: 0px;
	right: 0px;
}

.grid-head {
	border-color: #DDD;
	overflow: hidden;
	cursor: default;
	border-width: 0px 0px 1px;
	border-style: solid;
	background-color: #efefef;
}

.grid-head-inner {
	display: block;
	float: left;
}

.data-table {
	border-collapse: separate;
}

.data-table td {
	border-color: #CCC;
	border-width: 0px 1px 1px 0px;
	border-style: dotted;
	margin: 0px;
	padding: 0px;
}

.datagrid-cell {
	margin: 0px;
	padding: 0px 4px;
	font-size: 12px;
}

.cell-c1 {
	width: 40px;
	height: auto;
	text-align: center;
}

.cell-c2 {
	width: 160px;
	height: auto;
	text-align: center;
}

.cell-c3 {
	width: 60px;
	height: auto;
	text-align: center;
}

.cell-c4 {
	width: 100px;
	height: auto;
	text-align: center;
}

.cell-c5 {
	width: 50px;
	height: auto;
	text-align: center;
}

.cell-c6 {
	width: 100px;
	height: auto;
	text-align: center;
}

.cell-c7 {
	width: 100px;
	height: auto;
	text-align: center;
}

.cell-c8 {
	width: 150px;
	height: auto;
	text-align: center;
}

.cell-c9 {
	width: 150px;
	height: auto;
	text-align: center;
}

.grid-body {
	overflow: auto;
	margin: 0px;
	padding: 0px;
}

.datagrid-btable {
	border-collapse: separate;
}

.datagrid-btable tr {
	height: 25px;
}

.datagrid-btable tr td {
	border-color: #CCC;
	border-width: 0px 1px 1px 0px;
	border-style: dotted;
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
	<div class="content">
		<div class="content">
			<div class="module">
				<div class="module-head">
					<h3>省错误数据上传</h3>
				</div>
				<table border="0" width="100%">
					<tr>
						<td align="center" height="30px">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center">
							<table border="0" width="70%">
								<tr>
									<td align="right" width="50px;">
										<label>机构</label>
									</td>
									<td align="left">
										<select size="1" id="hcid" name="hcid" style="width: auto;">
											<option value="">--请选择--</option>
											<c:forEach items="${centers}" var="center">
												<option value="${center.id}">${center.name}</option>
											</c:forEach>
										</select>
									</td>
									<td align="left">
										<a class="btn btn-success" ng-click="synData()" style="margin-left:2px;margin-top:-10px;">同步数据</a> 
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
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
	<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
	<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope,$http) {
			$(".loading").hide();
		    //同步数据
		    $scope.synData = function() {
		    	$(".loading").show();
				// 机构
				let hcid = $("#hcid").val();
			    $http({
			        method : "POST",
			        url : "<%=basePath%>system/getHislog.do",
			        params: {hcid:hcid}
			    }).then(function mySucces(response) {
			    	$(".loading").hide();
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
			    	$(".loading").hide();
			        alert("system->getHislog.do访问错误出错!");
			        console.log(response.statusText);
			    });
		    };
		});
	</script>
</body>
</html>
