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
		<input id="psex" type="hidden" value="${patient.sex}"/>
		<div class="tabcontent">
			<table width="100%" height="100%" border="0">
				<tr>
					<td align="center">家医管理</td>
					<td>
						<table width="100%" height="100%">
							<tr height="30px">
								<td align="center">
									团队⻓
								</td>
								<td>
									<input type="text" ng-model="field1" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field3" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field2" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									团队医⽣
								</td>
								<td>
									<input type="text" ng-model="field4" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field6" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field5" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									护⼠
								</td>
								<td>
									<input type="text" ng-model="field7" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field9" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field8" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									公卫⼈员
								</td>
								<td>
									<input type="text" ng-model="field10" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field12" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field11" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									其他⼈员
								</td>
								<td>
									<input type="text" ng-model="field13" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field15" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field14" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									其他⼈员
								</td>
								<td>
									<input type="text" ng-model="field16" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field18" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field17" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									其他⼈员
								</td>
								<td>
									<input type="text" ng-model="field19" maxlength="25" />
								</td>
								<td align="center">
									联系电话
								</td>
								<td>
									<input type="text" ng-model="field21" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									单位地址
								</td>
								<td colspan="3">
									<input type="text" ng-model="field20" maxlength="50" />
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									监督单位
								</td>
								<td>
									<input type="text" ng-model="field22" maxlength="25" />
								</td>
								<td align="center">
									监督电话
								</td>
								<td>
									<input type="text" ng-model="field23" maxlength="50" />
								</td>
							</tr>
							<tr height="60px">
								<td align="center">
									服务对象类型<input id="field24" type="hidden">
								</td>
								<td align="center" colspan="3">
									<table width="100%" border="0">
										<tr>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="1" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												孕妇
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="2" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												⼉童
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="3" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												⾼⾎压
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="4" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												糖尿病
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="5" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												⽼年⼈
											</td>
										</tr>
										<tr>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="6" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												慢阻肺
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="7" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												计⽣特殊家庭
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="8" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												贫困⼈⼝
											</td>
											<td style="border:0px;">
												<input name="field24" type="checkbox" value="9" style="width:15px;height:15px;">
											</td>
											<td style="border:0px;">
												其它
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									协议服务内容
								</td>
								<td colspan="3">
									甲⽅根据本⼈情况，决定选择<input type="text" ng-model="field25" maxlength="50" style="width:300px;"/>类型服务包，并⼀次性预缴服务费⽤<input type="text" ng-model="field26" maxlength="50"  style="width:100px;"/>元。
								</td>
							</tr>
							<tr height="30px">
								<td align="center">
									⼄⽅签字盖章
								</td>
								<td colspan="3">
									<input type="text" ng-model="field27" maxlength="50"/>
								</td>
							</tr>
							<tr height="30px">
								<td align="center" colspan="4">
									<button class="btn btn-success" ng-click="save()" value="">保存</button>
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
		<input type="hidden" id="objId" value=""/>
		<script src="<%=basePath%>js/angular1.4.8.min.js"
			type="text/javascript"></script>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--工具类-->
		<script src="<%=basePath%>js/util.js"></script>
		
		<script type="text/javascript">
			//初始化List信息
			function initListData($http,$scope){
					let hcid = '${hcid}';
			    $http({
			        method : "GET",
			        url : "<%=basePath%>doctor/getDoctor.do",
				    params: {hcid:hcid}
			    }).then(function mySucces(response) {
			    		$scope.loadingShow = false;
			        let obj = response.data.doctor;
							if(obj!=undefined){
								console.log(obj);
								//ID
								$scope.id = obj.id;
								// 团队长名称
								$scope.field1 = obj.field1;
								// 团队长地址
								$scope.field2 = obj.field2;
								// 团队长电话
								$scope.field3 = obj.field3;
								// 团队医生名称
								$scope.field4 = obj.field4;
								// 团队医生地址
								$scope.field5 = obj.field5;
								// 团队医生电话
								$scope.field6 = obj.field6;
								// 护士名称
								$scope.field7 = obj.field7;
								// 护士地址
								$scope.field8 = obj.field8;
								// 护士电话
								$scope.field9 = obj.field9;
								// 公务人员名称
								$scope.field10 = obj.field10;
								// 公务人员地址
								$scope.field11 = obj.field11;
								// 公务人员电话
								$scope.field12 = obj.field12;
								// 其他人员名称
								$scope.field13 = obj.field13;
								// 其他人员地址
								$scope.field14 = obj.field14;
								// 其他人员电话
								$scope.field15 = obj.field15;
								// 其他人员2名称
								$scope.field16 = obj.field16;
								// 其他人员2地址
								$scope.field17 = obj.field17;
								// 其他人员2电话
								$scope.field18 = obj.field18;
								// 其他人员3名称
								$scope.field19 = obj.field19;
								// 其他人员3地址
								$scope.field20 = obj.field20;
								// 其他人员3电话
								$scope.field21 = obj.field21;
								// 监督单位
								$scope.field22 = obj.field22;
								// 监督电话
								$scope.field23 = obj.field23;
								// 服务类型
								$scope.field25 = obj.field25;
								// 费用
								$scope.field26 = obj.field26;
								// 签字盖章
								$scope.field27 = obj.field27;
								$(function(){
									//###复选框
									//服务对象类型[field24]
									$scope.field24 = obj.field24;
									initCheckBox("field24",obj.field24);
								});
							}
			    }, function myError(response) {
			    		$scope.loadingShow = false;
			        alert("doctor->getDoctor.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
		
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				let hcid = '${hcid}';
				$scope.loadingShow = true;
				//初始化信息
			  initListData($http,$scope);
			    //保存
			    $scope.save = function() {
						//ID
						let id = changeNullValue($scope.id);
						// 团队长名称
						let field1 = changeNullValue($scope.field1);
						// 团队长地址
						let field2 = changeNullValue($scope.field2);
						// 团队长电话
						let field3 = changeNullValue($scope.field3);
						// 团队医生名称
						let field4 = changeNullValue($scope.field4);
						// 团队医生地址
						let field5 = changeNullValue($scope.field5);
						// 团队医生电话
						let field6 = changeNullValue($scope.field6);
						// 护士名称
						let field7 = changeNullValue($scope.field7);
						// 护士地址
						let field8 = changeNullValue($scope.field8);
						// 护士电话
						let field9 = changeNullValue($scope.field9);
						// 公务人员名称
						let field10 = changeNullValue($scope.field10);
						// 公务人员地址
						let field11 = changeNullValue($scope.field11);
						// 公务人员电话
						let field12 = changeNullValue($scope.field12);
						// 其他人员名称
						let field13 = changeNullValue($scope.field13);
						// 其他人员地址
						let field14 = changeNullValue($scope.field14);
						// 其他人员电话
						let field15 = changeNullValue($scope.field15);
						// 其他人员2名称
						let field16 = changeNullValue($scope.field16);
						// 其他人员2地址
						let field17 = changeNullValue($scope.field17);
						// 其他人员2电话
						let field18 = changeNullValue($scope.field18);
						// 其他人员3名称
						let field19 = changeNullValue($scope.field19);
						// 其他人员3地址
						let field20 = changeNullValue($scope.field20);
						// 其他人员3电话
						let field21 = changeNullValue($scope.field21);
						// 监督单位
						let field22 = changeNullValue($scope.field22);
						// 监督电话
						let field23 = changeNullValue($scope.field23);
						// 服务类型
						let field25 = changeNullValue($scope.field25);
						// 费用
						let field26 = changeNullValue($scope.field26);
						// 签字盖章
						let	field27 = changeNullValue($scope.field27);
						//###复选框
						//服务对象类型[field24]
						let field24 = getCheckBox('field24');
						$scope.field24 = field24;
						
						//拼装参数
						let doctorSignDto = {
							id:id,
							field1:field1,
							field2:field2,
							field3:field3,
							field4:field4,
							field5:field5,
							field6:field6,
							field7:field7,
							field8:field8,
							field9:field9,
							field10:field10,
							field11:field11,
							field12:field12,
							field13:field13,
							field14:field14,
							field15:field15,
							field16:field16,
							field17:field17,
							field18:field18,
							field19:field19,
							field20:field20,
							field21:field21,
							field22:field22,
							field23:field23,
							field24:field24,
							field25:field25,
							field26:field26,
							field27:field27,
							hcid:hcid,
						};
						$scope.loadingShow = true;
				    $http({
				        method : "POST",
				        url : "<%=basePath%>doctor/changeDoctor.do",
				        params: doctorSignDto
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("doctor->changeDoctor.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
