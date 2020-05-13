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
		.cell {
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
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<input id="status" type="hidden" value="${status}"/>
		<div class="content">
			<div class="content">
				<div class="module">
					<div class="module-head">
						<h3>
							体检人员信息
						</h3>
					</div>
					<div class="module-body table">
						<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper"
							role="grid">
							<div id="DataTables_Table_0_length" class="dataTables_length">
								<table width="100%" border="0">
									<tr>
										<td style="border:0px;">
											建档日期
										</td>
										<td style="border:0px;">
											<input type="text" id="startDate" style="width:150px;" readonly="readonly"/>
										</td>
										<td style="border:0px;text-align:center">
											-
										</td>
										<td style="border:0px;">
											 <input type="text" id="endDate" style="width:150px;" readonly="readonly"/>
										</td>
										<td style="border:0px;">
											档案编号
										</td>
										<td colspan="2" style="border:0px;">
											<input type="text" style="width:150px;" ng-model="code"/>
										</td>
									</tr>
									<tr>
										<td style="border:0px;">
											姓名
										</td>
										<td style="border:0px;">
											<input type="text" style="width:150px;" ng-model="pname"/>
										</td>
										<td style="border:0px;">
											身份证
										</td>
										<td style="border:0px;">
											<input type="text" style="width:150px;" ng-model="idcard"/>
										</td>
										<td colspan="3" style="border:0px;">
											<a class="btn btn-success" ng-click="syndata(1)">脉率同步心率</a>
											<a class="btn btn-success" ng-click="syndata(2)">心率同步脉率</a>
											<a class="btn btn-success" ng-click="search()">搜索</a>
											<a class="btn btn-success" ng-click="checkRES()">健康评价</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!--/.module-->
			</div>
		</div>
		
		<div class="data-grid">
	        <div class="data-view">
	            <div class="grid-head">
		            <div class="grid-head-inner">
		                <table class="data-table">
			                <tbody>
			                    <tr class="data-table-row">
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div>ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div>档案编号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:60px;">
				                        	<div>姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div>性别</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div>出生日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:160px;">
				                        	<div>身份证</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div>建档日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:150px;">
				                        	<div>机构名称</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:150px;">
				                        	<div>操作</div>
				                        </div>
				                    </td>
			                    </tr>
			                </tbody>
		                </table>
		            </div>
	            </div>
	            
	            <div class="grid-body">
		            <table class="datagrid-btable">
		                <tbody>
		                	<tr ng-repeat="item in items" ng-dblclick="patienInfo(item)">
								<td>
									<div class="datagrid-cell cell" style="width:40px;"> 
										<span>
											{{ item.id }}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:100px;"> 
										<span>
											{{ item.code}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:60px;"> 
										<span>
											{{ item.name}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:40px;"> 
										<span ng-if="item.sex == 1">
										   	男
										</span>
										<span ng-if="item.sex == 2">
										   	女
										</span>
										<span ng-if="item.sex == 3">
										   	未说明的性别
										</span>
										<span ng-if="item.sex == 4">
										   	未知的性别
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:100px;"> 
										<span>
											{{item.birthdate | date:'yyyy-MM-dd'}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:160px;"> 
										<span>
											{{ item.idcard}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:100px;"> 
										<span>
											{{item.filedate | date:'yyyy-MM-dd'}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:150px;"> 
										<span>
											{{ item.fileunit}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell" style="width:150px;"> 
										<span>
											<a class="btn btn-success" ng-click="printCode(item)">重打条码</a></br>
											<a class="btn btn-success" style="margin-top:3px;" ng-click="delPatient(item)">删除</a>
										</span>
									</div>	
								</td>
							</tr>
		                </tbody>
		            </table>
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
		<a style="display:none;" id="toDoBnt"></a>
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
		<script type="text/javascript">
			//初始化时间控件
			$(function(){
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
				//设置表单高度
    			$(".grid-body").css({"height":$(window).height()-250});
    			$(".data-grid").css({"height":$(window).height()-217});
    			//设置表单宽度
    			$(".data-grid").css({"width":$(window).width()});
    			$(".data-view").css({"width":$(window).width()});
    			$(".grid-head").css({"width":$(window).width()});
    			$(".grid-body").css({"width":$(window).width()});
				//添加横向滚动
				$( ".grid-body" ).scroll(function() {
			        let ml="-"+$(this).scrollLeft()+"px";
			        $(".grid-head-inner").css({"margin-left":ml});
			    });	
			});
			//是否刷新page页
			let isFreshPage = true;
			//初始化List信息
			function initListData($http,$scope){
				let code=$scope.code;
				let pname=$scope.pname;
			    pname = encodeURI(pname);
				let idcard=$scope.idcard;
				//开始日期
			    let startDate = $("#startDate").val();
			    //结束日期
			    let endDate = $("#endDate").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>patient/getList.do",
				    params: {code:code,pname:pname,idcard:idcard,startDate:startDate,endDate:endDate}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			    	if(response.data!=""){
			    		$scope.items = response.data.datas;
			    	}
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("patient->getList.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//初始化搜索条件
				$scope.code = "";
				$scope.pname = "";
				$scope.idcard = "";
				//初始化信息(定时刷新)
				$(function(){
					let status = $("#status").val();
					//0不刷新1刷新
					if(status==0){
						initListData($http,$scope);
					}else{
						//暂时先不刷新列表
						//setInterval(function(){ initListData($http,$scope); }, 3000);
						initListData($http,$scope);
					}
				});
			    //隐藏加载框
				$scope.loadingShow = true;
				//搜索方法
			    $scope.search = function() {
			       $scope.loadingShow = true;
				   initListData($http,$scope);
			    };
			    //双击方法
			    $scope.patienInfo = function(item) {
			       window.open("patient/detail.do?id="+item.id);  
			    };
			    //重打条码
			    $scope.printCode = function(item) {
			       $("#toDoBnt").attr("href","PrintCode://"+item.code);
				   $("#toDoBnt")[0].click();
				   alert("已打印");
			    };
			    //删除病人
			    $scope.delPatient = function(item) {
			       let id = item.id;
		    	   if (!window.confirm("是否删除选中的用户!")) { 
				   		return;
				   }
			       $http({
				        method : "GET",
				        url : "<%=basePath%>patient/delPatient.do",
					    params: {id:id}
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data!=""){
				    		console.log(response.data.datas);
				    		if(response.data.datas>0){
				    			alert("删除成功!");
				    			window.location.reload();
				    		}else{
				    			alert("删除失败!");
				    		}
				    	}
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("patient->delPatient.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			    //健康评价
			    $scope.checkRES = function() {
			    	let isNum = true;
			    	if (window.confirm("生成健康评价,确定生成不带数值,取消生成带数值!")) { 
					  	isNum = false;
					}
			    	$scope.loadingShow = true;
					let code=$scope.code;
					let pname=$scope.pname;
				    pname = encodeURI(pname);
					let idcard=$scope.idcard;
					//开始日期
				    let startDate = $("#startDate").val();
				    //结束日期
				    let endDate = $("#endDate").val();
				    $http({
				        method : "GET",
				        url : "<%=basePath%>healthCheck/getAllResult.do",
					    params: {code:code,pname:pname,idcard:idcard,startDate:startDate,endDate:endDate,isNum:isNum}
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data!=""){
				    		if(response.data.code==0){
				    			alert("更新完毕!");
				    		}else if(response.data.code==1){
				    			alert("更新出错!");
				    		}else{
				    			alert("已更新"+response.data+"条数据!");
				    		}
				    	}
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("healthCheck->getAllResult.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			    //同步
			    $scope.syndata = function(v) {
			    	if (window.confirm("是否同步?")) { 
			    		$scope.loadingShow = true;
						let code=$scope.code;
						let pname=$scope.pname;
					    pname = encodeURI(pname);
						let idcard=$scope.idcard;
						//开始日期
					    let startDate = $("#startDate").val();
					    //结束日期
					    let endDate = $("#endDate").val();
					    $http({
					        method : "GET",
					        url : "<%=basePath%>healthCheck/syndata.do",
						    params: {code:code,pname:pname,idcard:idcard,startDate:startDate,endDate:endDate,synnum:v}
					    }).then(function mySucces(response) {
					    	$scope.loadingShow = false;
					    	if(response.data!=""){
					    		if(response.data.code>0){
					    			alert("更新完毕!");
					    		}else{
					    			alert("更新出错!");
					    		}
					    	}
					    }, function myError(response) {
					    	$scope.loadingShow = false;
					        alert("healthCheck->syndata.do访问错误出错!");
					        console.log(response.statusText);
					    });
					}
			    };
			});
		</script>
	</body>
</html>
