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
		
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<div class="content">
			<div class="content">
				<div class="module">
					<div class="module-head">
						<h3>
							报告打印
						</h3>
					</div>
					<table border="0" width="100%">
						<tr>
							<td align="center">
								<label>日期</label>
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
								<label>打印项目</label>
							</td>
							<td align="left" width="200px">
								<select id="selReport" style="width: auto;">
									<option value="1">
										个人基本信息
									</option>
									<option value="2">
										健康档案封面
									</option>
									<option value="3">
										健康档案封面A4
									</option>
									<option value="4">
										健康体检
									</option>
									<option value="5">
										自理能力
									</option>
									<option value="6">
										中医体质
									</option>
									<option value="7">
										体检报告(单页)
									</option>
									<option value="8">
										体检报告(双面)
									</option>
									<option value="9">
										体检报告(A3)
									</option>
									<option value="10">
										存档报告(65岁)A3
									</option>
									<option value="11">
										存档报告A3(国)
									</option>
									<option value="12">
										体检统计报表
									</option>
									<option value="13">
										生化报告
									</option>
									<option value="14">
										血常规报告
									</option>
									<option value="15">
										尿常规报告
									</option>
									<option value="16">
										B超报告
									</option>
									<option value="17">
										心电图报告
									</option>
									<option value="18">
										高血糖
									</option>
									<option value="19">
										高血压
									</option>
									<option value="20">
										家医签约
									</option>
								</select>
							</td>
							<td align="left">
								<a class="btn btn-success" ng-click="search()">查询</a> 
							</td>
						</tr>
						<tr>
							<td align="center">
								<label>身份证</label>
							</td>
							<td align="left">
								<input type="text" id="idCard" style="width:150px;"/>
							</td>
							<td align="center">
								<label>姓名</label>
							</td>
							<td align="left">
								<input type="text" id="sname" style="width:150px;"/>
							</td>
							<td align="center">
								<label>条码号</label>
							</td>
							<td align="left">
								<input type="text" id="scode" style="width:150px;"/>
							</td>
							<td align="left">
								<label style="color:red">{{totalNum}}</label>
							</td>
						</tr>
						<tr>
							<td align="center">
								<label>年龄范围</label>
							</td>
							<td align="left">
								<select id="selAge"
									style="width: auto;">
									<option value="1">全部</option>
									<option value="2">65岁以上</option>
									<option value="3">65岁以下</option>
								</select>
							</td>
							<td align="left" colspan="4">
								批量打印范围&nbsp;<input type="number" id="sprint" style="width:50px;margin-top:6px;" value="0"/>-<input type="number" id="eprint" style="width:50px;margin-top:6px;" value="0"/>
							</td>
							<td align="left">
								<a class="btn btn-success" ng-click="updateAllCP()">修改所有检验员</a>
								<a class="btn btn-success" ng-click="printAllPDF()">批量打印</a>
							</td>
						</tr>
					</table>
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
				                        <div class="datagrid-cell cell-c1">
				                        	<div>ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c2">
				                        	<div>身份证</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c3">
				                        	<div>姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c4">
				                        	<div>条码号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c5">
				                       		<div>年龄</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c6">
				                       		<div>体检日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c7">
				                       		<div>责任医生</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c8">
				                       		<div>机构名称</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell-c9">
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
		                	<tr ng-repeat="item in items">
								<td>
									<div class="datagrid-cell cell-c1"> 
										<span>
											<input type="checkbox" name="ids" value="{{item.pid}}"/>
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c2"> 
										<span>
											{{item.idCard}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c3"> 
										<span>
											{{item.name}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c4"> 
										<span>
											{{item.code}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c5"> 
										<span>
											{{item.age}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c6"> 
										<span>
											{{item.examDate | date:'yyyy-MM-dd'}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c7"> 
										<span>
											{{item.resDoctor}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c8"> 
										<span>
											{{item.insName}}
										</span>
									</div>	
								</td>
								<td>
									<div class="datagrid-cell cell-c9" style="padding:5px;"> 
										<span>
											<a class="btn btn-success" ng-click="updateCP(item)">修改检验员</a>
										</span>
										<span>
											<a class="btn btn-success" ng-click="print(item)">打印</a></div>
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
		<div class="loading">
			<div class="loading_anim">
				<div class="loader" style="flex-grow: 1;"></div>
				<div
					style="flex-grow: 1; text-align: center; line-height: 52px; height: 52px;">
					数据提交中请稍后
				</div>
			</div>
		</div>
		<!-- form -->
		<form id="f1" action="<%=basePath%>print/toAllCP.do" method="post">
			<input id="fidCard" name="fidCard" type="hidden" value=""/>
			<input id="fname" name="fname" type="hidden" value=""/>
			<input id="fcode" name="fcode" type="hidden" value=""/>
			<input id="fstartDate" name="fstartDate" type="hidden" value=""/>
			<input id="fendDate" name="fendDate" type="hidden" value=""/>
			<input id="fselAge" name="fselAge" type="hidden" value=""/>
		</form>
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
    			$(".grid-body").css({"height":$(window).height()-270});
    			$(".data-grid").css({"height":$(window).height()-237});
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
			//初始化信息
			function initListData($http,$scope){
				$(function(){
					//身份证
				    let idCard = $("#idCard").val();
				    //姓名
				    let name = encodeURI($("#sname").val());
				    //条码号
				    let code = $("#scode").val();
				    //开始日期
				    let startDate = $("#startDate").val();
				    //结束日期
				    let endDate = $("#endDate").val();
				    //年龄
				    let selAge = $("#selAge").val();
				    $http({
				        method : "GET",
				        url : "<%=basePath%>print/getPrintList.do",
				        params: {idCard:idCard,name:name,code:code,startDate:startDate,endDate:endDate,selAge:selAge}
				    }).then(function mySucces(response) {
				    	$(".loading").hide();
				    	if(response.data!=null&&response.data!=""){
				    		$scope.items = response.data.datas;
				        	$scope.totalNum = "当前记录总数:"+response.data.datas.length+"条数据";
				    	}
				    }, function myError(response) {
				    	$(".loading").hide();
				        alert("print->getPrintList.do访问错误出错!");
				        console.log(response.statusText);
				    });
				});
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//总条数
				$scope.totalNum = "当前记录总数:0条数据";
				//隐藏加载框
				$(".loading").show();
				//初始化信息
			    initListData($http,$scope);
			    //搜索信息
			    $scope.healthName = "";
				//搜索方法
			    $scope.search = function() {
					$(".loading").show();
			    	initListData($http,$scope);
			    };
			    //打印方法
			    $scope.print = function(item) {
			    	let selReport = $("#selReport").val();
			    	if(selReport==12){//体检统计报表
			    		//身份证
					    let idCard = $("#idCard").val();
					    //姓名
					    let name = encodeURI($("#sname").val());
					    //条码号
					    let code = $("#scode").val();
					    //开始日期
					    let startDate = $("#startDate").val();
					    //结束日期
					    let endDate = $("#endDate").val();
					    //年龄
					    let selAge = $("#selAge").val();
			    		$("#toDoBnt").attr("href","<%=basePath%>print/downLoad.do?idCard="+idCard+"&name="+name+"&code="+code+"&startDate="+startDate+"&endDate="+endDate+"&selAge="+selAge);
							$("#toDoBnt")[0].click();
			    	} else if(selReport==18||selReport==19){//高血糖//高血压
			    		//身份证
					    let idCard = $("#idCard").val();
					    //姓名
					    let name = encodeURI($("#sname").val());
					    //条码号
					    let code = $("#scode").val();
					    //开始日期
					    let startDate = $("#startDate").val();
					    //结束日期
					    let endDate = $("#endDate").val();
					    //年龄
					    let selAge = $("#selAge").val();
					    //状态(0高血糖,1高血压)
					    let stauts = 0;
					    if(selReport==19){
					    	stauts = 1;
					    }
			    		$("#toDoBnt").attr("href","<%=basePath%>print/gxtyDownLoad.do?idCard="+idCard+"&name="+name+"&code="+code+"&startDate="+startDate+"&endDate="+endDate+"&selAge="+selAge+"&stauts="+stauts);
						  $("#toDoBnt")[0].click();
			    	} else {
			    		window.open("<%=basePath%>print/printPDF.do?pid="+item.pid+"&selReport="+selReport);
			    	}
			    };
			    //修改检验员
			    $scope.updateCP = function(item) {
			    	window.location = "<%=basePath%>print/updateCP.do?pid="+item.pid;
			    };
			    //修改所有检验员
			    $scope.updateAllCP = function() {
			    	//身份证
				    let idCard = $("#idCard").val();
				    $("#fidCard").val(idCard);
				    //姓名
				    let name = encodeURI($("#sname").val());
				    $("#fname").val(name);
				    //条码号
				    let code = $("#scode").val();
				    $("#fcode").val(code);
				    //开始日期
				    let startDate = $("#startDate").val();
				    $("#fstartDate").val(startDate);
				    //结束日期
				    let endDate = $("#endDate").val();
				    $("#fendDate").val(endDate);
				    //年龄
				    let selAge = $("#selAge").val();
				    $("#fselAge").val(selAge);
				    $("#f1").submit();
			    };
			    //批量打印
			    $scope.printAllPDF = function() {
			    	//判断批量打印范围
			    	let sprint = Number($("#sprint").val());
			    	let eprint = Number($("#eprint").val());
			    	if(sprint!=0&&sprint<eprint){
			    		for(let i=sprint;i<=eprint;i++){
			    			let num = i-1;
			    			$("input[name='ids']:eq("+num+")").attr( "checked","checked");
			    		}
			    	}
			    	let ids = $("input[name='ids']:checked");
			    	if(ids.length==0){
			    		alert("请选择打印项!");
			    		return;
			    	}
			    	let idsStr = "";
			    	for(let i=0;i<ids.length;i++){
			    		idsStr = idsStr + $(ids[i]).val()+";";
			    	}
			    	let selReport = $("#selReport").val();
			    	window.open("<%=basePath%>print/printAllPDF.do?pids="+idsStr+"&selReport="+selReport);
			    };
			});
		</script>
	</body>
</html>
