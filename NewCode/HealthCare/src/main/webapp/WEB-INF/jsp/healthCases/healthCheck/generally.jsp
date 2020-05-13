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
                <tr height="30px">
                    <td  align="center" >
                        	体检日期
                    </td>
                    <td align="center" >
                        <lable>
                        	<input type="text" ng-model="itemField1" id="itemField1" style="width:100px;"/>
                        </lable>
                    </td>
                    <td align="center" >
                        	责任医生
                    </td>
                    <td align="center" >
                        <lable>${patient.doctor}</lable>
                    </td>
                    <td  align="center" >
                        	档案编号
                    </td>
                    <td  align="center">
                        <lable>${patient.code}</lable>
                    </td>
                </tr>
                <tr height="30px">
                    <td  align="center" >
                        	内容
                    </td>
                    <td align="center" colspan="5">
                        	检查项目
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        	症状<input id="itemField2" type="hidden">
                    </td>
                    <td align="center" colspan="5">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="1" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	无症状
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="2" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	头痛
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="3" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	头晕
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="4" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                   	 心悸
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="5" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	胸闷
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="6" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                   	 胸痛
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="7" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	慢性咳嗽
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="8" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	咳痰
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="9" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                   	 呼吸困难
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="10" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	多饮
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="11" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	多尿
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="12" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	体重下降
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="13" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	乏力
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="14" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	关节肿痛
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="15" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	视力模糊
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="16" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	手脚麻木
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="17" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	尿急
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="18" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                   	 尿痛
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="19" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	便秘
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="20" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	腹泻
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="21" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	恶心呕吐
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="22" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	眼花
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="23" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	耳鸣
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="24" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;">
                                	乳房胀痛
                                </td>
                                <td style="border:0px;">
                                    <input name="field2" type="checkbox" value="25" style="width:15px;height:15px;">
                                </td>
                                <td style="border:0px;" colspan="5">
                                	其他&nbsp;<input ng-model="itemField3" type="text" style="width:60%">
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="center" >
                    	一般情况
                    </td>
                    <td  colspan="5" >	
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" style="width:16%">
                                	体温
                                </td>
                                <td align="center" style="width:16%" >
                                    <input type="text" ng-model="itemField4">
                                </td>
                                <td align="center" style="width:16%">
                                	脉率
                                </td>
                                <td align="left" style="width:16%">
                                    <input type="text" style="width:60%;" ng-model="itemField5">次/分钟
                                </td>
                                
                            </tr>
                            <tr>
                                <td align="center">
                                   	 呼吸频率
                                </td>
                                <td align="left" style="width:16%">
                                    <input type="text" style="width:60%;" ng-model="itemField6">次/分钟
                                </td>
                                <td align="center">
                                	血压
                                </td>
                                <td align="center">
                                    <table width="100%" height="100%">
                                        <tr><td align="center">左侧</td></tr>
                                        <tr><td align="center">右侧</td></tr>
                                    </table>
                                </td>
                                <td align="center">
                                    <table width="100%" height="100%">
                                       <tr>
                                           <td align="center" style="width:16%">
                                               <input type="text" style="width:30%;" ng-model="itemField7">/<input type="text" style="width:30%;" ng-model="itemField8">mmHg
                                           </td>
                                       </tr>
                                       <tr>
                                            <td align="center" style="width:16%">
                                               <input type="text" style="width:30%;" ng-model="itemField9">/<input type="text" style="width:30%;" ng-model="itemField10">mmHg
                                           </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="width:16%">
                                	身高
                                </td>
                                <td align="left" style="width:16%" >
                                    <input type="text" style="width:60%;" ng-model="itemField11" id="highIPT">cm
                                </td>
                                <td align="center" style="width:16%">
                                	体重
                                </td>
                                <td align="left" style="width:16%">
                                    <input type="text" style="width:60%;" ng-model="itemField12" id="weightIPT">kg
                                </td>
                                <td style="width:32%">
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="width:16%">
                                   	 腰围
                                </td>
                                <td align="left" style="width:16%" >
                                    <input type="text" style="width:60%;" ng-model="itemField13">cm
                                </td>
                                <td align="center" style="width:16%">
                                	体质指数
                                </td>
                                <td align="left" style="width:16%">
                                    <input type="text" ng-model="itemField219">
                                </td>
                                <td style="width:32%">
                                </td>
                            </tr>
                            <tr>
                                <td align="center" >
                                	老年人健康状态评估
                                </td>
                                <td colspan="6">
                                    <select id="itemField15">
                                    	<option value="">---请选择---</option>
										<option value="1">满意</option>
										<option value="2">基本满意</option>
										<option value="3">说不清楚</option>
										<option value="4">不太满意</option>
										<option value="5">不满意</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" >
                                	老年人自理能力评估
                                </td>
                                <td colspan="6">
                                    <select id="itemField16">
                                    	<option value="">---请选择---</option>
										<option value="1">可自理</option>
										<option value="2">轻度依赖</option>
										<option value="3">中度依赖</option>
										<option value="4">不能自理</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" >
                                	老年人认知功能
                                </td>
                                <td colspan="6">
                                    <select id="itemField17">
                                    	<option value="">---请选择---</option>
										<option value="1">粗筛阴性</option>
										<option value="2">粗筛阳性</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" >
                                	老年人情感状态
                                </td>
                                <td colspan="6">
                                    <select id="itemField18">
                                    	<option value="">---请选择---</option>
										<option value="1">粗筛阴性</option>
										<option value="2">粗筛阳性</option>
									</select>
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
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
		<script type="text/javascript">
			//初始化时间控件
			$(function(){
				$("#itemField1").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
			});	
			
			//计算体质指数(v1身高v2体重)
			function getBMI(v1,v2){
				let bmi = "";
				if(v1!=""&&v2!=""){
					v1 = v1/100;
					let high = (v1*v1).toFixed(2);
					bmi = (Number(v2)/(Number(high))).toFixed(2);
				}
				return bmi;
			}
			
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
			        //体检日期
			        $scope.itemField1 = timestampToTime(changeNullValue(pObj.field1));
			        //症状其他
					$scope.itemField3 = changeNullValue(pObj.field3);
			        //体温
			        $scope.itemField4 = changeNullValue(pObj.field4);
        			//脉率
        			$scope.itemField5 = changeNullValue(pObj.field5);
					//呼吸频率
					$scope.itemField6 = changeNullValue(pObj.field6);
					//血压左侧
					$scope.itemField7 = changeNullValue(pObj.field7);
					$scope.itemField8 = changeNullValue(pObj.field8);
					//血压右侧
					$scope.itemField9 = changeNullValue(pObj.field9);
					$scope.itemField10 = changeNullValue(pObj.field10);
					//身高
					$scope.itemField11 = changeNullValue(pObj.field11);
					//体重
					$scope.itemField12 = changeNullValue(pObj.field12);
					//腰围
					$scope.itemField13 = changeNullValue(pObj.field13);
					//体质指数
					let BMI = changeNullValue(pObj.field219);
					if(BMI==""){
						BMI = getBMI($scope.itemField11,$scope.itemField12);
					}
					$scope.itemField219 = BMI;
					$(function(){
						//老年人健康状态评估
						$("#itemField15").val(changeNullValue(pObj.field15));
						//老年人自理能力评估
						$("#itemField16").val(changeNullValue(pObj.field16));
						//老年人认知功能
						$("#itemField17").val(changeNullValue(pObj.field17));
						//老年人情感状态
						$("#itemField18").val(changeNullValue(pObj.field18));
						//###复选框
			        	//症状[field2]
						$scope.itemField2 = pObj.field2;
						initCheckBox("field2",pObj.field2);
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
			        //体检日期
			        let field1 = $("#itemField1").val();
			        let reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
			    	let regExp =new RegExp(reg);
			    	if(field1!=""&&!regExp.test(field1)){
			        	alert("体检日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
			        //症状其他
					let field3 = encodeURI($scope.itemField3);
					//体温
			        let field4 = changeNullValue($scope.itemField4);
					if(field4!=""){
						if(!validateFloat(field4)){
							alert("体温格式不正确!");
							return;
						}
					}
        			//脉率
        			let field5 = changeNullValue($scope.itemField5);
        			if(field5!=""){
						if(!validateNum(field5)){
							alert("脉率必须为整数!");
							return;
						}else if(Number(field5)<10||Number(field5)>200){
							alert("脉率必须为10-200之间!");
							return;
						}
					}
					//呼吸频率
					let field6 = changeNullValue($scope.itemField6);
					if(field6!=""){
						if(!validateNum(field6)){
							alert("呼吸频率必须为整数!");
							return;
						}else if(Number(field6)<5||Number(field6)>99){
							alert("呼吸频率必须为5-99之间!");
							return;
						}
					}
					//血压左侧
					let field7 = changeNullValue($scope.itemField7);
					let field8 = changeNullValue($scope.itemField8);
					if(field7!=""||field8!=""){
						if(!validateFloat(field7)||!validateFloat(field8)){
							alert("血压左侧格式不正确!");
							return;
						}
					}
					//血压右侧
					let field9 = changeNullValue($scope.itemField9);
					let field10 = changeNullValue($scope.itemField10);
					if(field9!=""||field10!=""){
						if(!validateFloat(field9)||!validateFloat(field10)){
							alert("血压右侧格式不正确!");
							return;
						}
					}
					//身高
					let field11 = changeNullValue($scope.itemField11);
					if(field11!=""){
						if(!validateFloat(field11)){
							alert("身高格式不正确!");
							return;
						}
					}
					//体重
					let field12 = changeNullValue($scope.itemField12);
					if(field12!=""){
						if(!validateFloat(field12)){
							alert("体重格式不正确!");
							return;
						}
					}
					//腰围
					let field13 = changeNullValue($scope.itemField13);
					if(field13!=""){
						if(!validateFloat(field13)){
							alert("腰围格式不正确!");
							return;
						}
					}
					//体质指数
					let field219 = changeNullValue($scope.itemField219);
					if(field219!=""){
						if(!validateFloat(field219)){
							alert("体质指数格式不正确!");
							return;
						}
					}
					//老年人健康状态评估
					let field15 = $("#itemField15").val();
					//老年人自理能力评估
					let field16 = $("#itemField16").val();
					//老年人认知功能
					let field17 = $("#itemField17").val();
					//老年人情感状态
					let field18 = $("#itemField18").val();
					//###复选框
		        	//症状[field2]
					let field2 = getCheckBox('field2');
					$scope.itemField2 = field2;
					
					$scope.loadingShow = true;
					
					//拼装参数
					let patientDto = {pid:pid,field1Str:field1,field2:field2,field3:field3,field4:field4,
					field5:field5,field6:field6,field7:field7,field8:field8,field9:field9,field10:field10,
					field11:field11,field12:field12,field13:field13,field219:field219,field15:field15,field16:field16,
					field17:field17,field18:field18};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updGenerally.do",
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
				        alert("healthCheck->updGenerally.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			    $(function(){
			    	//身高改变
			    	$("#highIPT").change(function(){
			    		$scope.itemField219 = getBMI($scope.itemField11,$scope.itemField12);
			    	});
			    	//体重改变
			    	$("#weightIPT").change(function(){
			    		$scope.itemField219 = getBMI($scope.itemField11,$scope.itemField12);
			    	});
			    });
			});
		</script>
	</body>
</html>
