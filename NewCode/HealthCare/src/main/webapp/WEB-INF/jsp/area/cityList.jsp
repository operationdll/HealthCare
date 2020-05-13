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
		<div class="content" ng-show="listShow">
			<div class="content">
				<div class="module">
					<div class="module-head">
						<h3>
							城市信息
						</h3>
					</div>
					<div class="module-body table">
						<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
							role="grid">
							<div id="DataTables_Table_0_length" class="dataTables_length">
								<label> 城市名称
									<input type="text" style="width:150px;" ng-model="sName"/>
									<a class="btn btn-success" ng-click="search()">搜索</a> 
									<a class="btn btn-success" ng-click="addItem()">添加</a> 
								</label>
							</div>
							<table class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>
											城市代码
										</th>
										<th>
											城市名称
										</th>
										<th>
											创建日期
										</th>
										<th>
											修改日期
										</th>
										<th>
											操作
										</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in items">
										<td>
											{{ item.code }}
										</td>
										<td>
											{{ item.name}}
										</td>
										<td>
											{{item.createDate | date:'yyyy-MM-dd HH:mm:ss'}}
										</td>
										<td>
											{{item.updateDate | date:'yyyy-MM-dd HH:mm:ss'}}
										</td>
										<td>
											<a class="btn btn-primary"
												ng-click="showUpdItem(item)">修改</a>
											<a class="btn btn-primary"
												ng-click="showDistrict(item)">添加区\县</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!--/.module-->
			</div>
		</div>
		<!-- area add -->
		<div class="content" ng-show="addShow">
			<div class="module">
				<div class="module-head">
					<h3>
						城市
					</h3>
				</div>
				<div class="module-body">
					<div class="alert alert-error" ng-show="addError">
						<strong>错误:</strong> <div>{{errTxt}}</div>
					</div>
					<form class="form-horizontal row-fluid">
						<div class="control-group">
							<label class="control-label" for="basicinput">
								城市代码
							</label>
							<div class="controls">
								<input type="text" ng-model="itemCode" placeholder="请填写城市代码"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								城市名称
							</label>
							<div class="controls">
								<input type="text" ng-model="itemName" placeholder="请填写城市名称"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn" type="submit" ng-click="addItemSub()">
									提交
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
		<input id="pname" type="hidden" value="${pname}"/>
		<input id="pcode" type="hidden" value="${pcode}"/>
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			//初始化List信息
			function initListData($http,$scope){
				let sName = $scope.sName;
				sName = encodeURI(sName);
				let pcode = $("#pcode").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>area/getAreaList.do",
				    params: {name:sName,level:2,pcode:pcode}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        $scope.items = response.data.datas;
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("area->getAreaList.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//初始化搜索条件
				$scope.sName = "";
				//初始化信息
			    initListData($http,$scope);
			    //显示列表页面
			    $scope.listShow = true;
			    //隐藏加载框
				$scope.loadingShow = true;
				//是否显示添加区域
				$scope.addShow = false;
				//错误信息
				$scope.errTxt = '';
				$scope.addError = false;
				//表单信息
				$scope.itemName = '';
				$scope.itemCode = '';
				$scope.itemOldCode = '';
				$scope.itemId = '';
				$scope.itemCode = '';
				//搜索方法
			    $scope.search = function() {
				   initListData($http,$scope);
			    };
			    //添加信息
			    $scope.addItem = function() {
			       $scope.itemId = '';
			       $scope.itemName = '';
			       $scope.itemCode = '';
			       $scope.listShow = false;
				   $scope.addShow = true;
			    };
			    //添加提交
			    $scope.addItemSub = function() {
			    	if($scope.itemName == ''){
			    		$scope.addError = true;
			    		$scope.errTxt = '城市名称不能为空';
			    		return;
			    	}
			    	
			    	if($scope.itemCode == ''){
			    		$scope.addError = true;
			    		$scope.errTxt = '城市代码不能为空';
			    		return;
			    	}
			    	
			    	let pcode = $("#pcode").val();
			    	let itemCode = $scope.itemCode;
			    	if(itemCode.indexOf($("#pcode").val())!=0){
			    		$scope.addError = true;
			    		$scope.errTxt = "城市代码必须以"+pcode+"开头";
			    		return;
			    	}

			    	$scope.loadingShow = true;
		    		let itemName = $scope.itemName;
		    		itemName = encodeURI(itemName);
		    		let pName = $("#pname").val() + "-" + $scope.itemName;
		    		pName = encodeURI(pName);
		    		if($scope.itemId==''){
		    			//增加信息
					    $http({
					        method : "POST",
					        url : "<%=basePath%>area/insertArea.do",
					        params: {
					        	name:itemName,code:itemCode,pname:pName,level:2,pcode:pcode
							}
					    }).then(function mySucces(response) {
					    	$scope.loadingShow = false;
					        if(response.data.code==0){
					            //初始化区域信息
				   				initListData($http,$scope);
							    $scope.addShow = false;
				    			$scope.listShow = true;
				    			$scope.itemName = '';
							    $scope.errTxt = '';
							    $scope.addError = false;
					        	alert('添加成功!');
					        }else{
					        	alert('添加失败!');
					        }
					    }, function myError(response) {
					    	$scope.loadingShow = false;
					        alert("insertArea.do访问错误出错!");
					        console.log(response.statusText);
					    });
		    		}else{
		    			let id = $scope.itemId;
		    			let oldCode = $scope.itemOldCode;
		    			//修改信息
					    $http({
					        method : "POST",
					        url : "<%=basePath%>area/updArea.do",
					        params: {
					        	id:id,name:itemName,code:itemCode,pname:pName,level:2,pcode:pcode,oldCode:oldCode
							}
					    }).then(function mySucces(response) {
					    	$scope.loadingShow = false;
					        if(response.data.code==0){
					            //初始化信息
				   				initListData($http,$scope);
							    $scope.addShow = false;
				    			$scope.listShow = true;
				    			$scope.itemName = '';
				    			$scope.itemCode = '';
				    			$scope.itemOldCode = '';
							    $scope.errTxt = '';
							    $scope.addError = false;
					        	alert('修改成功!');
					        }else{
					        	alert('修改失败!');
					        }
					    }, function myError(response) {
					    	$scope.loadingShow = false;
					        alert("updArea.do访问错误出错!");
					        console.log(response.statusText);
					    });
		    		}
			    };
			    //显示修改信息
			    $scope.showUpdItem = function(item) {
			    	$scope.itemName = item.name;
			    	$scope.itemCode = item.code;
			    	$scope.itemId = item.id;
			    	$scope.itemOldCode = item.code;
			    	$scope.listShow = false;
				    $scope.addShow = true;
			    };
			    //添加区县信息
			    $scope.showDistrict = function(item) {
			    	let pcode = item.code;
			    	let pname = encodeURI(item.pname);
			    	window.parent.contentIframe.src = "<%=basePath%>area/toDistrict.do?pcode="+pcode+"&pname="+pname;
			    };
			    
			});
		</script>
	</body>
</html>
