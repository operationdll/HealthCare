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
							用户信息
						</h3>
					</div>
					<div class="module-body table">
						<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
							role="grid">
							<div id="DataTables_Table_0_length" class="dataTables_length">
								<label> 用户名称
									<input type="text" style="width:150px;" ng-model="userName"/>
									<a class="btn btn-success" ng-click="search()">搜索</a> 
									<a class="btn btn-success" ng-click="addItem()">添加</a> 
								</label>
							</div>
							<table class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>
											ID
										</th>
										<th>
											用户名
										</th>
										<th>
											卫生所
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
											{{ item.id }}
										</td>
										<td>
											{{ item.userName}}
										</td>
										<td>
											{{ item.healthName}}
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
										</td>
									</tr>
								</tbody>
							</table>
							<div style="float:right;margin-right:20px;">
								页数<select style="width:70px;" ng-change="changePage()" ng-model="selectedPage" id="selPage">
								  </select>
							</div>
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
						用户
					</h3>
				</div>
				<div class="module-body">
					<div class="alert alert-error" ng-show="addError">
						<strong>错误:</strong> <div>{{errTxt}}</div>
					</div>
					<form class="form-horizontal row-fluid">
						<div class="control-group">
							<label class="control-label" for="basicinput">
								省
							</label>
							<div class="controls">
								<select id="province" style="width: auto;">
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								市
							</label>
							<div class="controls">
								<select id="city" style="width: auto;">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								区/县
							</label>
							<div class="controls">
								<select id="district" style="width: auto;">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								卫生院
							</label>
							<div class="controls">
								<select id="healthcenter" style="width: auto;">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								名称
							</label>
							<div class="controls">
								<input type="text" ng-model="itemName" placeholder="请填写用户名称"
									class="span8 tip">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="basicinput">
								密码
							</label>
							<div class="controls">
								<input type="password" ng-model="itemPwd" placeholder="请填写用户密码"
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
		
		<input type="hidden" id="updCity"/>
		<input type="hidden" id="updDistrict"/>
		<input type="hidden" id="updHcenter"/>
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			//初始化方法
			$(function(){
				//省列表变化
				$("#province").change(function(){
					$(".loading").show();
					let pcode=$(this).val();
					if(pcode==""){
				    	$("#city").html('<option value="">--请选择--</option>');
				    	$("#district").html('<option value="">--请选择--</option>');
						return;
					}
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "<%=basePath%>area/getAreaList.do",
						async: true,
						data: {level:2,pcode:pcode},
						success: function(data){
							$(".loading").hide();
							var data = data.datas;
							let cityHtml = '<option value="">--请选择--</option>';
					    	for(let i=0;i<data.length;i++){
					    		cityHtml = cityHtml + '<option value="'+data[i].code+'">'+data[i].name+'</option>';;
					    	}
					    	$("#city").html(cityHtml);
					    	let updCity = $("#updCity").val();
					    	if(updCity!=""){
					    		$("#city").val(updCity);
					    		$('#city').trigger("change");
					    		$("#updCity").val("");
					    	}
						}
					});
				});
				//市列表变化
				$("#city").change(function(){
					$(".loading").show();
					let pcode=$(this).val();
					if(pcode==""){
				    	$("#district").html('<option value="">--请选择--</option>');
						return;
					}
					$.ajax({
						type: "GET",
						dataType: "json",
						url: "<%=basePath%>area/getAreaList.do",
						async: true,
						data: {level:3,pcode:pcode},
						success: function(data){
							$(".loading").hide();
							var data = data.datas;
							let districtHtml = '<option value="">--请选择--</option>';
					    	for(let i=0;i<data.length;i++){
					    		districtHtml = districtHtml + '<option value="'+data[i].code+'">'+data[i].name+'</option>';;
					    	}
					    	$("#district").html(districtHtml);
					    	let updDistrict = $("#updDistrict").val();
					    	if(updDistrict!=""){
					    		$("#district").val(updDistrict);
					    		$('#district').trigger("change");
					    		$("#updDistrict").val("");
					    	}
						}
					});
				});
				//区/县列表变化
				$("#district").change(function(){
					$(".loading").show();
					let code=$(this).val();
					if(code!=""){
						$.ajax({
							type: "GET",
							dataType: "json",
							url: "<%=basePath%>system/getHealthByDistrict.do",
							async: true,
							data: {district:code},
							success: function(data){
								$(".loading").hide();
								var data = data.datas;
								let hHtml = '<option value="">--请选择--</option>';
						    	for(let i=0;i<data.length;i++){
						    		hHtml = hHtml + '<option value="'+data[i].id+'">'+data[i].name+'</option>';;
						    	}
						    	$("#healthcenter").html(hHtml);
						    	let updHcenter = $("#updHcenter").val();
						    	if(updHcenter!=""){
						    		$("#healthcenter").val(updHcenter);
						    		$("#updHcenter").val("");
						    	}
							}
						});
					}else{
						$("#healthcenter").html('<option value="">--请选择--</option>');
					}
				});
			});
			
			//获取省列表信息
			function getProvince($http,$scope){
				$scope.loadingShow = true;
			    $http({
			        method : "GET",
			        url : "<%=basePath%>area/getAreaList.do",
				    params: {level:1}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			    	let data = response.data.datas;
			    	let proHtml = '<option value="">--请选择--</option>';
			    	for(let i=0;i<data.length;i++){
			    		proHtml = proHtml + '<option value="'+data[i].code+'">'+data[i].name+'</option>';;
			    	}
			    	$("#province").html(proHtml);
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("area->getAreaList.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
		
			//是否刷新page页
			let isFreshPage = true;
			//初始化信息
			function initListData($http,$scope,page){
				if(page==1){
					$scope.selectedPage = 1;
					isFreshPage = true;
				}
				let userName = $scope.userName;
			    userName = encodeURI(userName);
			    $http({
			        method : "GET",
			        url : "<%=basePath%>system/getUserList.do",
				       params: {page:page,name:userName}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        $scope.items = response.data.datas.pageList;
			        if(isFreshPage){
			        	let $html = '';
				        for(let i =0;i<response.data.datas.totalPages;i++){
				        	let num = i+1;
				        	$html = $html + `<option value="`+num+`">`+num+`</option>`;
				        }
				        if($html == ''){
				        	$html = `<option value="1">1</option>`;
				        }
				        $("#selPage").html($html);
				        isFreshPage = false;
			        }
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("system->getAreaList.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//初始化搜索条件
				$scope.userName = "";
				//初始化信息
			    initListData($http,$scope,1);
			    //获取省列表信息
				getProvince($http,$scope);
			    //搜索信息
			    $scope.healthName = "";
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
				$scope.itemPwd = '';
				$scope.itemId = '';
				//搜索方法
			    $scope.search = function() {
				   $scope.selectedPage = 1;
				   isFreshPage = true;
				   initListData($http,$scope,1);
			    };
			    //添加信息
			    $scope.addItem = function() {
			       $scope.itemName = '';
				   $scope.itemPwd = '';
				   $scope.itemId = '';
			       $scope.listShow = false;
				   $scope.addShow = true;
				   $("#province").val("");
				   $('#province').trigger("change");
			    };
			    //添加提交
			    $scope.addItemSub = function() {
			    	if($scope.itemName == ''){
			    		$scope.addError = true;
			    		$scope.errTxt = '用户名不能为空';
			    		return;
			    	}
			    	if($scope.itemPwd == ''){
			    		$scope.addError = true;
			    		$scope.errTxt = '密码不能为空';
			    		return;
			    	}
			    	$scope.loadingShow = true;
			    	let itemPwd = $scope.itemPwd;
		    		let itemName = $scope.itemName;
		    		itemName = encodeURI(itemName);
		    		// 省份代码
		    		let province = $("#province").val();
		    		// 城市代码
		    		let city = $("#city").val();
		    		// 区/县代码
		    		let district = $("#district").val();
		    		// 卫生所
		    		let hcid = $("#healthcenter").val();
		    		if($scope.itemId==''){
		    			//增加信息
					    $http({
					        method : "POST",
					        url : "<%=basePath%>system/addUser.do",
					        params: {
					        	userName:itemName,
					        	userPassword:itemPwd,
					        	hcid:hcid,
					        	province:province,
					        	city:city,
					        	district:district
							}
					    }).then(function mySucces(response) {
					    	$scope.loadingShow = false;
					    	if(response.data.code==2){
					    		alert('该用户已存在!');
					    	}else if(response.data.code==0){
					            //初始信息
			    				isFreshPage = true;
				   				initListData($http,$scope,1);
							    $scope.addShow = false;
				    			$scope.listShow = true;
				    			$scope.itemName = '';
				    			$scope.itemPwd = '';
							    $scope.errTxt = '';
							    $scope.addError = false;
					        	alert('添加成功!');
					        }else{
					        	alert('添加失败!');
					        }
					    }, function myError(response) {
					    	$scope.loadingShow = false;
					        alert("addUser.do访问错误出错!");
					        console.log(response.statusText);
					    });
		    		}else{
		    			let id = $scope.itemId;
		    			let oldName = $scope.oldName;
		    			oldName = encodeURI(oldName);
		    			//修改信息
					    $http({
					        method : "POST",
					        url : "<%=basePath%>system/updUser.do",
					        params: {
					        	id:id,
					        	name:itemName,
					        	oldName:oldName,
					        	pwd:itemPwd,
					        	hcid:hcid,
					        	province:province,
					        	city:city,
					        	district:district
							}
					    }).then(function mySucces(response) {
					    	$scope.loadingShow = false;
					    	if(response.data.code==2){
					    		alert('该用户已存在!');
					    	}else if(response.data.code==0){
					            //初始信息
			    				isFreshPage = true;
				   				initListData($http,$scope,1);
							    $scope.addShow = false;
				    			$scope.listShow = true;
				    			$scope.itemName = '';
				    			$scope.itemPwd = '';
							    $scope.errTxt = '';
							    $scope.addError = false;
					        	alert('修改成功!');
					        }else{
					        	alert('修改失败!');
					        }
					    }, function myError(response) {
					    	$scope.loadingShow = false;
					        alert("updUser.do访问错误出错!");
					        console.log(response.statusText);
					    });
		    		}
			    };
			    //显示修改信息
			    $scope.showUpdItem = function(item) {
			    	$scope.itemName = item.userName;
			    	$scope.oldName = item.userName;
			    	$scope.itemId = item.id;
			    	$scope.listShow = false;
				    $scope.addShow = true;
				    $("#updCity").val(item.city);
				    $("#updDistrict").val(item.district);
				    $("#updHcenter").val(item.hcid);
				    $("#province").val(item.province);
				    $('#province').trigger("change");
			    };
			    //改变页数
			    $scope.changePage = function() {
			  		let selectedItem = $scope.selectedPage;
					initListData($http,$scope,selectedItem);
			  	}
			});
		</script>
	</body>
</html>
