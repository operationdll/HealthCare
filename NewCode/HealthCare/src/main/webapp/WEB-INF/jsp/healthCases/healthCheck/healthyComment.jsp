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

		/* tab themes */

		
		* {box-sizing: border-box}

		/* Set height of body and the document to 100% */
		body, html {
		height: 100%;
		margin: 0;
		font-family: Arial;
		}

		/* Style tab links */
		.tablink {
		background-color: white;
		color: black;
		float: left;
		border: none;
		outline: none;
		cursor: pointer;
		padding: 14px 16px;
		font-size: 17px;
		width: 12.5%;
		}

		.tablink:hover {
		background-color: #777;
		}

		/* Style the tab content (and add height:100% for full page content) */
		.tabcontent {
		color: black;
		padding: 3px 20px;
		height: 100%;
		}
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<input id="pid" type="hidden" value="${patient.id}"/>
        <div class="tabcontent">
            <table width="100%" height="100%" border="0">
                <tr>
                    <td align="center">
                    	健康评价
                    </td>
                    <td colspan="2">
                        <table width="100%" height="100%" border="0">
                            <tr>
                                <td>
                                    <select id="itemField208" style="width:300px;">
										<option value="1">体检无异常</option>
										<option value="2">有异常</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                	健康评价异常1<input type="text" style="width:70%" ng-model="itemField209">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                	健康评价异常2<input type="text" style="width:70%" ng-model="itemField211">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                	健康评价异常3<input type="text" style="width:70%" ng-model="itemField212">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                	健康评价异常4<input type="text" style="width:70%" ng-model="itemField213">
                                </td>
                            </tr>
                        </table>
                    </td>   
                </tr>
                <tr>
                    <td align="center">
                    	健康指导<input id="itemField214" type="hidden">
                    </td>
                    <td align="center" style="height:80px">
                        <table width="100%">
                            <tr>
                                <td style="border:0px;">
                                    <input name="field214" type="checkbox" value="1" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	定期随访
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field214" type="checkbox" value="2" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	纳入慢性病患者健康管理
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field214" type="checkbox" value="3" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                   	 建议复查
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field214" type="checkbox" value="4" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	建议转诊
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table>
                        	<tr>
                        		<td align="center" colspan="10" style="border:0px;">
			                    	危险因素控制<input id="itemField215" type="hidden">
			                    </td>
                        	</tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field215" type="checkbox" value="1" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	戒烟
                                </td>
                                <td style="border:0px;">
                                    <input name="field215" type="checkbox" value="2" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	健康饮酒
                                </td>
                                <td style="border:0px;">
                                    <input name="field215" type="checkbox" value="3" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	饮食
                                </td>
                                <td style="border:0px;">
                                    <input name="field215" type="checkbox" value="4" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                   	 锻炼
                                </td>
                                <td style="border:0px;">
                                    <input name="field215" type="checkbox" value="5" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	减体重 （目标 <input type="text" style="width:30%" ng-model="itemField216">)
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field215" type="checkbox" value="6" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	建议接种疫苗
                                </td>
                                <td style="border:0px;" colspan="8">
                                	<input type="text" style="width:60%" ng-model="itemField217">
                                </td>
                            </tr>
                            <tr>
                            	<td style="border:0px;">
                                    <input name="field215" type="checkbox" value="7" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	其他
                                </td>
                                <td style="border:0px;" colspan="8">
                                	<input type="text" style="width:60%" ng-model="itemField218">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td align="center" colspan="3">
                    	<button class="btn btn-success" ng-click="getResult()">
							自动生成结果
						</button>
                    	<button class="btn btn-success" ng-click="save()">
							保存
						</button>
                    </td>
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
		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
		<script type="text/javascript">
			//初始化List信息
			function initListData($http,$scope){
				let pid = $("#pid").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>healthCheck/getHealthCheck.do",
				    params: {pid:pid}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        let pObj = response.data.datas;
					//健康评价异常1
					$scope.itemField209 = changeNullValue(pObj.field209);
					//健康评价异常2 
					$scope.itemField211 = changeNullValue(pObj.field211);
					//健康评价异常3 
					$scope.itemField212 = changeNullValue(pObj.field212);
					//健康评价异常4 
					$scope.itemField213 = changeNullValue(pObj.field213);
					//目标 
					$scope.itemField216 = changeNullValue(pObj.field216);
					//建议接种疫苗 
					$scope.itemField217 = changeNullValue(pObj.field217);
					//健康体检其他 
					$scope.itemField218 = changeNullValue(pObj.field218);
					$(function(){
						//###下拉选择框
						//健康评价
						if(pObj.field208===undefined){
							pObj.field208 = 1;
						}
						$("#itemField208").val(pObj.field208);
						
						//###复选框
						//健康指导[field214]
						$scope.itemField214 = pObj.field214;
						initCheckBox("field214",pObj.field214);
						//危险因素控制[field215]
						$scope.itemField215 = pObj.field215;
						initCheckBox("field215",pObj.field215);
					});
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("healthCheck->getHealthCheck.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
		
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//初始化信息
			    initListData($http,$scope);
			    //保存
			    $scope.save = function() {
			    	$scope.loadingShow = true;
			    	//病人id信息
			    	let pid = $("#pid").val();
					//健康评价异常1 
					let field209 = encodeURI(changeNullValue($scope.itemField209));
					//健康评价异常2 
					let field211 = encodeURI(changeNullValue($scope.itemField211));
					//健康评价异常3 
					let field212 = encodeURI(changeNullValue($scope.itemField212));
					//健康评价异常4 
					let field213 = encodeURI(changeNullValue($scope.itemField213));
					//目标 
					let field216 = encodeURI(changeNullValue($scope.itemField216));
					//建议接种疫苗 
					let field217 = encodeURI(changeNullValue($scope.itemField217));
					//健康体检其他 
					let field218 = encodeURI(changeNullValue($scope.itemField218));

					//###下拉选择框
					//健康评价
					let field208 = $("#itemField208").val();

					//###复选框
					//健康指导
					let field214 = getCheckBox('field214');
					$scope.itemField214 = field214;
					//危险因素控制		        	
					let field215 = getCheckBox('field215');
					$scope.itemField215 = field215;

					//拼装参数
					let patientDto = {pid:pid,field209:field209,field211:field211,field212:field212,
							field213:field213,field216:field216,field217:field217,field218:field218,
							field208:field208,field214:field214,field215:field215
					};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updHealthyComment.do",
				        params: patientDto
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("healthCheck->updHealthyComment.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			    //自动生成结果
			    $scope.getResult = function() {
			    	$scope.loadingShow = true;
			    	//病人id信息
			    	let pid = $("#pid").val();
			    	$http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/getResult.do",
				        params: {pid:pid}
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('生成成功!');
				        	initListData($http,$scope);
				        }else{
				        	alert('生成失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("healthCheck->getResult.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
