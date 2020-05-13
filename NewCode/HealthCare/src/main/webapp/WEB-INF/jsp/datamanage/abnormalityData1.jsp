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
		
		.ico_up{
			float: right;
		    width: 0;
		    height: 0;
		    border: 4px solid transparent ;
		    border-bottom-color:#B0B0B0 ;
		    margin-top: 3px;
		}
		
		.ico_down{
		    float: right;
		    width: 0;
		    height: 0;
		    border: 4px solid transparent ;
		    border-top-color:#B0B0B0 ;
			margin-top: 8px;
		}
		
		/* 表单样式 */
		.data-grid {
			position:relative;
			overflow:hidden;
			font-size:14px;
			color:#444;
			box-sizing:border-box;
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
		/* 图表样式 */
		canvas {
			-moz-user-select: none;
			-webkit-user-select: none;
			-ms-user-select: none;
		}
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<div class="content">
			<div class="module">
				<div class="module-head">
					<h3>
						体检异常
					</h3>
				</div>
				<table border="0">
					<tr>
						<td align="center">
							<label>日期</label>
						</td>
						<td align="left" width="150px">
							<input type="text" id="startDate" style="width:150px;margin-top:5px;" readonly="readonly"/>
						</td>
						<td align="center">
							<label>-----</label>
						</td>
						<td align="left" width="150px">
							<input type="text" id="endDate" style="width:150px;margin-top:5px;" readonly="readonly"/>
						</td>
						<td align="center">
							<label>年龄</label>
						</td>
						<td align="left" width="60px">
							<input type="number" id="startAge" style="width:60px;margin-top:5px;"/>
						</td>
						<td align="center">
							<label>-</label>
						</td>
						<td align="left" width="60px">
							<input type="number" id="endAge" style="width:60px;margin-top:5px;"/>
						</td>
						<td align="center">
							<a class="btn btn-success" ng-click="search()">查询</a> 
						</td>
					</tr>
				</table>
			</div>
			<!--/.module-->
		</div>
		
		<table width="100%">
			<tr>
				<td style="width:650px;">
					<div id="chatDiv" style="display: block; width:650px; height: 400px;">
						<canvas id="myChart" width="650" height="400"></canvas>
					</div>
				</td>
				<td rowspan="2" style="vertical-align: top;">
					<table width="60%" style="margin-left:100px;">
						<tr style="border:1px solid grey;">
							<th style="border-right:1px solid grey;">
								项目
							</th>
							<th style="border-right:1px solid grey;">
								人数
							</th>
							<th>
								百分比
							</th>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								BMI异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep1"></span>
							</td>
							<td>
								<span id="per1"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								高血压异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep2"></span>
							</td>
							<td>
								<span id="per2"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								高血糖异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep3"></span>
							</td>
							<td>
								<span id="per3"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								肝功异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep4"></span>
							</td>
							<td>
								<span id="per4"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								肾功异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep5"></span>
							</td>
							<td>
								<span id="per5"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								血脂异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep6"></span>
							</td>
							<td>
								<span id="per6"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								血小板异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep7"></span>
							</td>
							<td>
								<span id="per7"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								血红蛋白异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep8"></span>
							</td>
							<td>
								<span id="per8"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								白细胞异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep9"></span>
							</td>
							<td>
								<span id="per9"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								心电图异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep10"></span>
							</td>
							<td>
								<span id="per10"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								尿液分析异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep11"></span>
							</td>
							<td>
								<span id="per11"></span>
							</td>
						</tr>
						<tr style="border:1px solid grey;">
							<td style="border-right:1px solid grey;">
								B超异常人数
							</td>
							<td style="border-right:1px solid grey;">
								<span id="ep12"></span>
							</td>
							<td>
								<span id="per12"></span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div style="display: block; width:650px;text-align: center;">
						<span style="color:red;" id="total"></span>
					</div>
				</td>
			</tr>
		</table>
		
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
		<div id="tooltip" class="tip"></div>
		<input type="hidden" id="hcid" value="${hcid}"/>
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
  		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
  		<!--图标类-->
  		<script src="<%=basePath%>js/Chart.min.js"></script>
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
			});
			
			//异常人员
			function createIView(labels,data){
				$('#myChart').remove();
				$('#chatDiv').append('<canvas id="myChart" width="650" height="400"></canvas>');
				Chart.defaults.global.legend.display = false;
				var ctx = document.getElementById('myChart');
				var myChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: labels,
				        datasets: [{
				            data: data,
				            backgroundColor: [
				                'rgba(255, 99, 132, 0.2)',
				                'rgba(154, 182, 235, 0.2)',
				                'rgba(215, 206, 86, 0.2)',
				                'rgba(75, 192, 192, 0.2)',
				                'rgba(153, 102, 255, 0.2)',
				                'rgba(255, 159, 64, 0.2)',
				                'rgba(128,128,128, 0.2)',
								'rgba(128,0,0, 0.2)',
								'rgba(255,0,255, 0.2)',
								'rgba(255,255,0, 0.2)',
								'rgba(0,128,0, 0.2)',
								'rgba(0,0,255, 0.2)'
				            ],
				            borderColor: [
				                'rgba(255, 99, 132, 1)',
				                'rgba(154, 182, 235, 1)',
				                'rgba(215, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)',
				                'rgba(255, 159, 64, 1)',
								'rgba(128,128,128,1)',
								'rgba(128,0,0, 1)',
								'rgba(255,0,255, 1)',
								'rgba(255,255,0, 1)',
								'rgba(0,128,0, 1)',
								'rgba(0,0,255, 1)'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero: true
				                }
				            }]
				        },
						title: {
				            display: true,
				            text: '体检各项异常(人数)'
				        }
				    }
				});
			}			
			
			//初始化信息
			function initListData($http,$scope){
				$(function(){
				    //开始日期
				    let startDate = $("#startDate").val();
				    //结束日期
				    let endDate = $("#endDate").val();
				    //开始年龄
				    let startAge = $("#startAge").val();
				    //结束年龄
				    let endAge = $("#endAge").val();
				    //卫生院id
				    let hcid = $("#hcid").val();
				    $http({
				        method : "GET",
				        url : "<%=basePath%>DMG/getALData1.do",
				        params: {startDate:startDate,endDate:endDate,startAge:startAge,endAge:endAge,hcid:hcid}
				    }).then(function mySucces(response) {
				    	$(".loading").hide();
				    	if(response.data!=null&&response.data!=""){
				    		let rdate = response.data;
				    		let total = Number(rdate.total);
				    		$("#total").html("体检总人数:"+total+"人");
				    		let labels = [];
				    		labels.push('BMI异常');
				    		labels.push('高血压异常');
				    		labels.push('高血糖异常');
				    		labels.push('肝功异常');
				    		labels.push('肾功异常');
				    		labels.push('血脂异常');
				    		labels.push('血小板异常');
				    		labels.push('血红蛋白异常');
				    		labels.push('白细胞异常');
				    		labels.push('心电图异常');
				    		labels.push('尿液分析异常');
				    		labels.push('B超异常');
				    		let data = [];
				    		
				    		
				    		let p1 = rdate.p1.length;
				    		data.push(p1);
				    		$("#ep1").html(p1);
				    		$("#per1").html(toPercent(p1, total));
				    		
				    		let p2 = rdate.p2.length;
				    		data.push(p2);
				    		$("#ep2").html(p2);
				    		$("#per2").html(toPercent(p2, total));
				    		
				    		let p3 = rdate.p3.length;
				    		data.push(p3);
				    		$("#ep3").html(p3);
				    		$("#per3").html(toPercent(p3, total));
				    		
				    		let p4 = rdate.p4.length;
				    		data.push(p4);
				    		$("#ep4").html(p4);
				    		$("#per4").html(toPercent(p4, total));
				    		
				    		let p5 = rdate.p5.length;
				    		data.push(p5);
				    		$("#ep5").html(p5);
				    		$("#per5").html(toPercent(p5, total));
				    		
				    		let p6 = rdate.p6.length;
				    		data.push(p6);
				    		$("#ep6").html(p6);
				    		$("#per6").html(toPercent(p6, total));
				    		
				    		let p7 = rdate.p7.length;
				    		data.push(p7);
				    		$("#ep7").html(p7);
				    		$("#per7").html(toPercent(p7, total));
				    		
				    		let p8 = rdate.p8.length;
				    		data.push(p8);
				    		$("#ep8").html(p8);
				    		$("#per8").html(toPercent(p8, total));
				    		
				    		let p9 = rdate.p9.length;
				    		data.push(p9);
				    		$("#ep9").html(p9);
				    		$("#per9").html(toPercent(p9, total));
				    		
				    		let p10 = rdate.p10.length;
				    		data.push(p10);
				    		$("#ep10").html(p10);
				    		$("#per10").html(toPercent(p10, total));
				    		
				    		let p11 = rdate.p11.length;
				    		data.push(p11);
				    		$("#ep11").html(p11);
				    		$("#per11").html(toPercent(p11, total));
				    		
				    		let p12 = rdate.p12.length;
				    		data.push(p12);
				    		$("#ep12").html(p12);
				    		$("#per12").html(toPercent(p12, total));
				    		
				    		createIView(labels,data);
				    	}
				    }, function myError(response) {
				    	$(".loading").hide();
				        alert("DMGListControl->getALData1.do访问错误出错!");
				    });
				});
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//隐藏加载框
				$(".loading").show();
				//初始化信息
			    initListData($http,$scope);
				//搜索方法
			    $scope.search = function() {
					$(".loading").show();
			    	initListData($http,$scope);
			    };
			});
		</script>
	</body>
</html>
