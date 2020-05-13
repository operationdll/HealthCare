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
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<input id="uid" type="hidden" value="${uid}"/>
		<div class="content">
			<div class="module">
				<div class="module-head">
					<h3>
						居民健康档案
					</h3>
				</div>
				<div class="module-body">
					<form class="form-horizontal row-fluid">
						<div class="control-group">
							<label class="control-label" for="basicinput">
								编号
							</label>
							<div class="controls" style="margin-top: 5px;">
								{{itemCode}}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								姓名
							</label>
							<div class="controls">
								<input type="text" ng-model="itemName" placeholder="请填写姓名"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								现住址
							</label>
							<div class="controls">
								<input type="text" ng-model="itemAddress" placeholder="请填写现住址"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								户籍地址
							</label>
							<div class="controls">
								<input type="text" ng-model="itemAddress1" placeholder="请填写户籍地址"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								联系电话
							</label>
							<div class="controls">
								<input type="text" ng-model="itemPhoneno" placeholder="请填写联系电话"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								乡镇(街道)名称
							</label>
							<div class="controls">
								<input type="text" ng-model="itemCountyname" placeholder="请填写乡镇(街道)名称"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								村(居)委会名称
							</label>
							<div class="controls">
								<input type="text" ng-model="itemVcname" placeholder="请填写村(居)委会名称"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								建档单位
							</label>
							<div class="controls">
								<input type="text" ng-model="itemFileunit" placeholder="请填写建档单位"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								建档人
							</label>
							<div class="controls">
								<input type="text" ng-model="itemFileuser" placeholder="请填写建档人"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								责任医生
							</label>
							<div class="controls">
								<input type="text" ng-model="itemDoctor" placeholder="请填写责任医生"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								体检日期
							</label>
							<div class="controls" style="margin-top: 5px;">
								{{itemFiledate | date:'yyyy年MM月dd日'}}
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-success" type="submit" ng-click="updItemSub()">
									保存
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
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
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
		<script type="text/javascript">
			//初始化List信息
			function initListData($http,$scope){
				let id = $("#uid").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>patient/getPatient.do",
				    params: {id:id}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        let pObj = response.data.datas;
			        $scope.itemName = changeNullValue(pObj.name);
			        $scope.itemAddress = changeNullValue(pObj.address);
			        $scope.itemAddress1 = changeNullValue(pObj.address1);
			        $scope.itemPhoneno = changeNullValue(pObj.phoneno);
			        $scope.itemCountyname = changeNullValue(pObj.countyname);
			        $scope.itemVcname = changeNullValue(pObj.vcname);
			        $scope.itemFileunit = changeNullValue(pObj.fileunit);
			        $scope.itemFileuser = changeNullValue(pObj.fileuser);
			        $scope.itemDoctor = changeNullValue(pObj.doctor);
			        $scope.itemFiledate = changeNullValue(response.data.healthcheck.field1);
			        $scope.itemCode = changeNullValue(pObj.code);
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("patient->getPatient.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//初始化信息
			    initListData($http,$scope);
			    //修改信息
			    $scope.updItemSub = function() {
			    	let id = $("#uid").val();
			        let name = encodeURI($scope.itemName);
			        let address = encodeURI($scope.itemAddress);
			        let address1 = encodeURI($scope.itemAddress1);
			        let phoneno = $scope.itemPhoneno;
			        let countyname = encodeURI($scope.itemCountyname);
			        let vcname = encodeURI($scope.itemVcname);
			        let fileunit = encodeURI($scope.itemFileunit);
			        let fileuser = encodeURI($scope.itemFileuser);
			        let doctor = encodeURI($scope.itemDoctor);
				    $http({
				        method : "POST",
				        url : "<%=basePath%>patient/updPatientHeath.do",
				        params: {
				        	id:id,
				        	name:name,
				        	address:address,
				        	address1:address1,
				        	phoneno:phoneno,
				        	countyname:countyname,
				        	vcname:vcname,
				        	fileunit:fileunit,
				        	fileuser:fileuser,
				        	doctor:doctor
						}
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("updPatientHeath.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
