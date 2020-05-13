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
                    <td align="center">生活方式</td>
                    <td>
                    	<table width="100%" height="100%">
                            <tr height="30px">
                                <td align="center" rowspan="3">
                                	体育锻炼
                                </td>
                                <td align="center">
                                	锻炼频率
                                </td>
                                <td colspan="3" >
                                    <select id="itemField19">
										<option value="1">每天</option>
										<option value="2">每周一次以上</option>
										<option value="3">偶尔</option>
										<option value="4">不锻炼</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center"  style="width:25%">
                                	每次锻炼时间
                                </td>
                                <td align="left" style="width:25%;">
                                    <input type="text" ng-model="itemField20" style="width:60%;"/>分钟
                                </td>
                                <td align="center" style="width:25%;">
                                	坚持锻炼时间
                                </td>
                                <td align="left" colspan="4" style="width:25%;">
                                    <input type="text" ng-model="itemField21" style="width:60%;"/>年
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center">
                                	锻炼方式
                                </td>
                                <td  colspan="6" >
                                    <input type="text" ng-model="itemField22"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                	饮食习惯<input id="itemField23" type="hidden">
                                </td>
                                <td colspan="6">
                                    <table width="100%" height="100%" border="0">
                                        <tr>
                                            <td style="border:0px;">
                                                <input name="field23" type="checkbox" value="1" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	荤素均衡
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field23" type="checkbox" value="2" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	荤食为主
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field23" type="checkbox" value="3" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	素食为主
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field23" type="checkbox" value="4" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	嗜盐
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field23" type="checkbox" value="5" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	嗜油
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field23" type="checkbox" value="6" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	嗜糖
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" rowspan="3" >
                                	吸烟情况
                                </td>
                                <td align="center">
                                	吸烟状况
                                </td>
                                <td  colspan="5" >
                                    <select id="itemField24">
										<option value="1">从不吸烟</option>
										<option value="2">已戒烟</option>
										<option value="3">吸烟</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center">
                                	日吸烟量
                                </td>
                                <td  colspan="5" >
                                	平均<input type="text" ng-model="itemField25" style="width:100px;"/>支
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center"  style="width:25%">
                                	开始吸烟年龄
                                </td>
                                <td align="left" style="width:25%;">
                                    <input type="text" ng-model="itemField26" style="width:60%;"/>岁
                                </td>
                                <td align="center" style="width:25%;">
                                	戒烟年龄
                                </td>
                                <td align="left" colspan="3" style="width:25%;">
                                    <input type="text" ng-model="itemField27" style="width:60%;"/>岁
                                </td>
							</tr>
							<tr height="30px">
                                <td align="center" rowspan="4" >
                                	饮酒情况
                                </td>
                                <td align="center">
                                	饮酒频率
                                </td>
                                <td  colspan="5" >
                                    <select id="itemField28">
										<option value="1">从不</option>
										<option value="2">偶尔</option>
										<option value="3">经常</option>
										<option value="4">每天</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center">
                                	日饮酒量
                                </td>
                                <td  colspan="5" >
                                	平均<input type="text" ng-model="itemField29" style="width:100px;"/>两
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center"  style="width:25%">
                                	是否戒酒
                                </td>
                                <td style="width:25%;">
									<select id="itemField30">
										<option value="">请选择</option>
										<option value="1">未戒酒</option>
										<option value="2">已戒酒</option>
									</select>
								</td>
                                <td align="center" style="width:25%;">
                                	戒酒年龄
                                </td>
                                <td align="left" colspan="3" style="width:25%;">
                                    <input type="text" ng-model="itemField31" style="width:60%;"/>岁
                                </td>
							</tr>
							<tr height="30px">
                                <td  align="center" style="width:25%">
                                	开始饮酒年龄
                                </td>
                                <td style="width:25%;">
									<input type="text" ng-model="itemField32" style="width:60%;"/>岁
								</td>
                                <td align="center" style="width:25%;">
                                	近一年是否曾醉酒
                                </td>
								<td style="width:25%" colspan="3">
									<select id="itemField33">
										<option value="1">是</option>
										<option value="2">否</option>
									</select>
								</td>
							</tr>
							<tr>
                                <td align="center">
                                	饮酒种类<input id="itemField34" type="hidden">
                                </td>
                                <td  colspan="6">
                                    <table width="100%" height="100%" border="0">
                                        <tr>
                                            <td style="border:0px;">
                                                <input name="field34" type="checkbox" value="1" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	白酒
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field34" type="checkbox" value="2" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	啤酒
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field34" type="checkbox" value="3" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	红酒
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field34" type="checkbox" value="4" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	黄酒
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field34" type="checkbox" value="5" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	其他&nbsp;<input type="text" style="width:40%" ng-model="itemField35">
                                            </td>     
                                        </tr>
                                    </table>
                                </td>
							</tr>
							<tr height="30px">
								<td align="center">
									职业病危害
								</td>
								<td align="center" colspan="6">
									<table width="100%" border="0">
										<tr>
											<td style="border:0px;">
												<label class="radio-inline">
													<input type="radio" value="1" name="field36" checked style="width:20px;margin-top:-2px;">无
												</label>
											</td>
											<td style="border:0px;">
												<label class="radio-inline">
													<input type="radio" value="2" name="field36" style="width:20px;margin-top:-2px;">有
												</label>
											</td>
											<td style="border:0px; width:80%">
													&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
                                <td align="center" colspan="7">
                                	<button class="btn btn-success" ng-click="save()">
										保存
									</button>
                                </td>
                            </tr>
                        </table>
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
			        //每次锻炼时间
			        $scope.itemField20 = changeNullValue(pObj.field20);
					//坚持锻炼时间
					$scope.itemField21 = changeNullValue(pObj.field21);
					//锻炼方式
					$scope.itemField22 = changeNullValue(pObj.field22);
					//日吸烟量
					$scope.itemField25 = changeNullValue(pObj.field25);
					//开始吸烟年龄
					$scope.itemField26 = changeNullValue(pObj.field26);
					//戒烟年龄
					$scope.itemField27 = changeNullValue(pObj.field27);
					//日饮酒量
					$scope.itemField29 = changeNullValue(pObj.field29);
					//戒酒年龄
					$scope.itemField31 = changeNullValue(pObj.field31);
					//开始饮酒年龄
					$scope.itemField32 = changeNullValue(pObj.field32);
					//饮酒种类其他
					$scope.itemField35 = changeNullValue(pObj.field35);
					$(function(){
						//###下拉选择框
						//锻炼频率
						if(pObj.field19===undefined){
							pObj.field19 = 4;
						}
						$("#itemField19").val(pObj.field19);
						//吸烟状况
						if(pObj.field24===undefined){
							pObj.field24 = 1;
						}
						$("#itemField24").val(pObj.field24);
						//饮酒频率
						if(pObj.field28===undefined){
							pObj.field28 = 1;
						}
						$("#itemField28").val(pObj.field28);
						//是否戒酒
						if(pObj.field30===undefined){
							pObj.field30 = "";
						}
						$("#itemField30").val(pObj.field30);
						//近一年是否曾醉酒
						if(pObj.field33===undefined){
							pObj.field33 = 2;
						}
						$("#itemField33").val(pObj.field33);
						//###复选框
			        	//饮食习惯[field23]
			        	if(pObj.field23===undefined){
							pObj.field23 = "1";
						}
						$scope.itemField23 = pObj.field23;
						initCheckBox("field23",pObj.field23);
						//饮酒种类[field34]
						$scope.itemField34 = pObj.field34;
						initCheckBox("field34",pObj.field34);
						//###单选框
						//职业病危害[field36]
						initRedio("field36",pObj.field36);
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
			    	//病人id信息
			    	let pid = $("#pid").val();
					//每次锻炼时间
			        let field20 = changeNullValue($scope.itemField20);
			        if(field20!=""){
				        if(!validateFloat(field20)){
							alert("每次锻炼时间格式不正确!");
							return;
						}
			        }
					//坚持锻炼时间
					let field21 = changeNullValue($scope.itemField21);
					if(field21!=""){
						if(!validateFloat(field21)){
							alert("坚持锻炼时间格式不正确!");
							return;
						}
					}
					//锻炼方式
					let field22 = changeNullValue(encodeURI($scope.itemField22));
					//日吸烟量
					let field25 = changeNullValue($scope.itemField25);
					if(field25!=""){
						if(!validateFloat(field25)){
							alert("日吸烟量格式不正确!");
							return;
						}
					}
					//开始吸烟年龄
					let field26 = changeNullValue($scope.itemField26);
					if(field26!=""){
						if(!validateFloat(field26)){
							alert("开始吸烟年龄格式不正确!");
							return;
						}
					}
					//戒烟年龄
					let field27 = changeNullValue($scope.itemField27);
					if(field27!=""){
						if(!validateFloat(field27)){
							alert("戒烟年龄格式不正确!");
							return;
						}
					}
					//日饮酒量
					let field29 = changeNullValue($scope.itemField29);
					if(field29!=""){
						if(!validateFloat(field29)){
							alert("日饮酒量格式不正确!");
							return;
						}
					}
					//戒酒年龄
					let field31 = changeNullValue($scope.itemField31);
					if(field31!=""){
						if(!validateFloat(field31)){
							alert("戒酒年龄格式不正确!");
							return;
						}
					}
					//开始饮酒年龄
					let field32 = changeNullValue($scope.itemField32);
					if(field32!=""){
						if(!validateFloat(field32)){
							alert("开始饮酒年龄格式不正确!");
							return;
						}
					}
					//饮酒种类其他
					let field35 = encodeURI(changeNullValue($scope.itemField35));

					//###下拉选择框
					//锻炼频率
					let field19 = $("#itemField19").val();
					//饮酒频率
					let field28 = $("#itemField28").val();
					//吸烟状况
					let field24 = $("#itemField24").val();
					//是否戒酒
					let field30 = $("#itemField30").val();
					//近一年是否曾醉酒
					let field33 = $("#itemField33").val();

					//###复选框
		        	//饮食习惯[field23]
					let field23 = getCheckBox('field23');
					$scope.itemField23 = field23;
					//饮酒种类[field34]
					let field34 = getCheckBox('field34');
					$scope.itemField34 = field34;

					//###单选框
					//职业病危害[field36]
					let field36 = getRedio("field36");
					
					$scope.loadingShow = true;
					//拼装参数
					let patientDto = {pid:pid,field20:field20,field21:field21,field22:field22,
							field25:field25,field26:field26,field27:field27,field28:field28,
							field29:field29,field31:field31,field32:field32,field19:field19,
							field24:field24,field30:field30,field33:field33,field23:field23,
							field34:field34,field35:field35,field36:field36};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updLifestyle.do",
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
				        alert("healthCheck->updLifestyle.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
