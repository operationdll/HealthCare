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
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
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
											日期
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
											 <a class="btn btn-success" ng-click="search()">搜索</a>
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
		<div style="width:99%;margin:0 auto;">
			<table class="table table-striped table-bordered table-condensed">
	            <tr>
		             <td>
		                 <div>
		                 	<div>卫生院</div>
		                 </div>
		             </td>
		             <td>
		                 <div>
		                 	<div>人数</div>
		                 </div>
		             </td>
	            </tr>
	            <tr ng-repeat="item in items">
					<td>
						<div> 
							<span>
								{{ item.field1 }} - ({{item.field2}})
							</span>
						</div>	
					</td>
					<td>
						<div> 
							<span>
								{{ item.field3}}
							</span>
						</div>	
					</td>
				 </tr>
				 <tr>
				 	<td colspan="2" height="200px;">&nbsp;</td>
				 </tr>
	        </table>
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
			});
			//初始化List信息
			function initListData($http,$scope){
				//开始日期
			    let startDate = $("#startDate").val();
			    //结束日期
			    let endDate = $("#endDate").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>system/getCData.do",
				    params: {startDate:startDate,endDate:endDate}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			    	console.log(response);
			    	if(response.data!=""){
			    		$scope.items = response.data.datas;
			    	}
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("system->getCData.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//隐藏加载框
				$scope.loadingShow = true;
				$(function(){
					initListData($http,$scope);
				});
				//搜索方法
			    $scope.search = function() {
			       $scope.loadingShow = true;
				   initListData($http,$scope);
			    };
			});
		</script>
	</body>
</html>
