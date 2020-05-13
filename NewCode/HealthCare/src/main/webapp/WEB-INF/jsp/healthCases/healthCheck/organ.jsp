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
           <table width="100%" height="100%" >
                <tr>
                    <td align="center">脏器功能</td>
                    <td>
                        <table width="100%" height="100%">
                            <tr height="30px">
                                <td align="center" rowspan="6" >
                                   	口腔
                                </td>
                                <td align="center" >
                                   	 口唇
                                </td>
                                <td colspan="3">
                                    <select id="itemField49">
										<option value="1">红润</option>
										<option value="2">苍白</option>
										<option value="3">发绀</option>
										<option value="4">皲裂</option>
										<option value="5">疱疹</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                    <td align="center" >
                                       	 齿列<input id="itemField50" type="hidden">
                                    </td>
                                    <td align="center" colspan="4">
                                            <table width="100%" border="0">
                                                <tr>
                                                    <td style="border:0px;">
                                                        <input name="field50" type="checkbox" value="1" style="width:15px;height:15px;">
                                                    </td>
                                                    <td style="border:0px;">
                                                    	正常
                                                    </td>
                                                    <td style="border:0px;">
                                                        <input name="field50" type="checkbox" value="2" style="width:15px;height:15px;">
                                                    </td>
                                                    <td style="border:0px;">
                                                       	 缺齿
                                                    </td>
                                                    <td style="border:0px;">
                                                        <input name="field50" type="checkbox" value="3" style="width:15px;height:15px;">
                                                    </td>
                                                    <td style="border:0px;">
                                                      	  龋齿
                                                    </td>
                                                    <td style="border:0px;">
                                                        <input name="field50" type="checkbox" value="4" style="width:15px;height:15px;">
                                                    </td>
                                                    <td style="border:0px;">
                                                    	义齿（假牙）
                                                    </td>
                                                </tr>
                                                
                                            </table>
                                        </td>
                                </tr>
                                <tr height="40px">
                                    <td align="center">
                                    	缺齿
                                    </td>
                                    <td align="center" colspan="4">
                                        <table width="75%" height="75%" border="0">
                                            <tr>
                                                <td>
                                                    <input type="text" ng-model="itemField51">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField52">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="text" ng-model="itemField53">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField54">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr height="40px">
                                    <td align="center">
                                       	 龋齿
                                    </td>
                                    <td align="center" colspan="4">
                                        <table width="75%" height="75%" border="0">
                                            <tr>
                                                <td>
                                                    <input type="text" ng-model="itemField55">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField56">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="text" ng-model="itemField57">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField58">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr height="40px">
                                    <td align="center">
                                    	义齿
                                    </td>
                                    <td align="center" colspan="4">
                                        <table width="75%" height="75%" border="0">
                                            <tr>
                                                <td>
                                                    <input type="text" ng-model="itemField59">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField60">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="text" ng-model="itemField61">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField62">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            <tr height="30px">
                                <td align="center" >
                                	咽部
                                </td>
                                <td  colspan="4">
                                    <select id="itemField63">
										<option value="1">无充血</option>
										<option value="2">充血</option>
										<option value="3">淋巴滤泡增生</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	视力
                                </td>
                                <td colspan="5">
                                    <table width="100%">
                                        <tr align="center">
                                            <td style="width:12.5%">
                                            	左眼视力
                                            </td>
                                            <td style="width:12.5%">
                                                <input type="text" ng-model="itemField64">
                                            </td>
                                            <td style="width:12.5%">
                                            	右眼视力
                                            </td>
                                            <td style="width:12.5%">
                                                <input type="text" ng-model="itemField65">
                                            </td>
                                            <td style="width:12.5%">
                                            	左眼矫正视力
                                            </td>
                                            <td style="width:12.5%">
                                                <input type="text" ng-model="itemField66">
                                            </td>
                                            <td style="width:12.5%">
                                            	右眼矫正视力
                                            </td>
                                            <td style="width:12.5%">
                                                <input type="text" ng-model="itemField67">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	听力
                                </td>
                                <td  colspan="5">
                                    <select id="itemField68">
										<option value="1">听见</option>
										<option value="2">听不清或无法听见</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	运动功能
                                </td>
                                <td  colspan="5">
                                    <select id="itemField69">
										<option value="1">可顺利完成</option>
										<option value="2">无法独立完成其中任何一个动作</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" colspan="6" style="height:50px;">
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
					//缺齿1
					$scope.itemField51 = changeNullValue(pObj.field51);
					//缺齿2
					$scope.itemField52 = changeNullValue(pObj.field52);
					// 缺齿3
					$scope.itemField53 = changeNullValue(pObj.field53);
					// 缺齿4
					$scope.itemField54 = changeNullValue(pObj.field54);
					// 龋齿1
					$scope.itemField55 = changeNullValue(pObj.field55);
					// 龋齿2
					$scope.itemField56 = changeNullValue(pObj.field56);
					// 龋齿3
					$scope.itemField57 = changeNullValue(pObj.field57);
					// 龋齿4
					$scope.itemField58 = changeNullValue(pObj.field58);
					// 义齿1
					$scope.itemField59 = changeNullValue(pObj.field59);
					// 义齿2
					$scope.itemField60 = changeNullValue(pObj.field60);
					// 义齿3
					$scope.itemField61 = changeNullValue(pObj.field61);
					// 义齿4
					$scope.itemField62 = changeNullValue(pObj.field62);
					// 左眼视力
					$scope.itemField64 = changeNullValue(pObj.field64);
					// 右眼视力
					$scope.itemField65 = changeNullValue(pObj.field65);
					// 左眼矫正视力
					$scope.itemField66 = changeNullValue(pObj.field66);
					// 右眼矫正视力
					$scope.itemField67 = changeNullValue(pObj.field67);
			        
					$(function(){
						//###下拉选择框
						//口唇
						if(pObj.field49===undefined){
							pObj.field49 = 1;
						}
						$("#itemField49").val(pObj.field49);
						//咽部
						if(pObj.field63===undefined){
							pObj.field63 = 1;
						}
						$("#itemField63").val(pObj.field63);
						//听力
						if(pObj.field68===undefined){
							pObj.field68 = 1;
						}
						$("#itemField68").val(pObj.field68);
						//运动功能
						if(pObj.field69===undefined){
							pObj.field69 = 1;
						}
						$("#itemField69").val(pObj.field69);
						
						//###复选框
			        	//齿列 [field50]
						$scope.itemField50 = pObj.field50;
						initCheckBox("field50",pObj.field50);
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
			        // 缺齿1
					let field51 = encodeURI(changeNullValue($scope.itemField51));
					if(field51!=""){
						if(!validateNum(field51)){
							alert("缺齿必须为整数!");
							return;
						}
					}
					// 缺齿2
					let field52 = encodeURI(changeNullValue($scope.itemField52));
					if(field52!=""){
						if(!validateNum(field52)){
							alert("缺齿必须为整数!");
							return;
						}
					}
					// 缺齿3
					let field53 = encodeURI(changeNullValue($scope.itemField53));
					if(field53!=""){
						if(!validateNum(field53)){
							alert("缺齿必须为整数!");
							return;
						}
					}
					// 缺齿4
					let field54 = encodeURI(changeNullValue($scope.itemField54));
					if(field54!=""){
						if(!validateNum(field54)){
							alert("缺齿必须为整数!");
							return;
						}
					}
					// 龋齿1
					let field55 = encodeURI(changeNullValue($scope.itemField55));
					if(field55!=""){
						if(!validateNum(field55)){
							alert("龋齿必须为整数!");
							return;
						}
					}
					// 龋齿2
					let field56 = encodeURI(changeNullValue($scope.itemField56));
					if(field56!=""){
						if(!validateNum(field56)){
							alert("龋齿必须为整数!");
							return;
						}
					}
					// 龋齿3
					let field57 = encodeURI(changeNullValue($scope.itemField57));
					if(field57!=""){
						if(!validateNum(field57)){
							alert("龋齿必须为整数!");
							return;
						}
					}
					// 龋齿4
					let field58 = encodeURI(changeNullValue($scope.itemField58));
					if(field58!=""){
						if(!validateNum(field58)){
							alert("龋齿必须为整数!");
							return;
						}
					}
					// 义齿1
					let field59 = encodeURI(changeNullValue($scope.itemField59));
					if(field59!=""){
						if(!validateNum(field59)){
							alert("义齿必须为整数!");
							return;
						}
					}
					// 义齿2
					let field60 = encodeURI(changeNullValue($scope.itemField60));
					if(field60!=""){
						if(!validateNum(field60)){
							alert("义齿必须为整数!");
							return;
						}
					}
					// 义齿3
					let field61 = encodeURI(changeNullValue($scope.itemField61));
					if(field61!=""){
						if(!validateNum(field61)){
							alert("义齿必须为整数!");
							return;
						}
					}
					// 义齿4
					let field62 = encodeURI(changeNullValue($scope.itemField62));
					if(field62!=""){
						if(!validateNum(field62)){
							alert("义齿必须为整数!");
							return;
						}
					}
					// 左眼视力
					let field64 = changeNullValue($scope.itemField64);
					if(field64!=""){
						if(!validateFloat(field64)){
							alert("左眼视力格式不正确!");
							return;
						}
					}
					// 右眼视力
					let field65 = changeNullValue($scope.itemField65);
					if(field65!=""){
						if(!validateFloat(field65)){
							alert("右眼视力格式不正确!");
							return;
						}
					}
					// 左眼矫正视力
					let field66 = changeNullValue($scope.itemField66);
					if(field66!=""){
						if(!validateFloat(field66)){
							alert("左眼矫正视力格式不正确!");
							return;
						}
					}
					// 右眼矫正视力
					let field67 = changeNullValue($scope.itemField67);
					if(field67!=""){
						if(!validateFloat(field67)){
							alert("右眼矫正视力格式不正确!");
							return;
						}
					}
					
					//听力
					let field68 = $("#itemField68").val();
					//运动功能
					let field69 = $("#itemField69").val();
					//咽部
					let field63 = $("#itemField63").val();
					//口唇
					let field49 = $("#itemField49").val();

					//###复选框
		        	//齿列[field50]
					let field50 = getCheckBox('field50');
					$scope.itemField50 = field50;
					
					$scope.loadingShow = true;
					
					//拼装参数
					let healthCheckDto = {pid:pid,field51:field51,field52:field52,
							field53:field53,field54:field54,field55:field55,
							field56:field56,field57:field57,field58:field58,
							field59:field59,field60:field60,field61:field61,
							field62:field62,field64:field64,field65:field65,
							field66:field66,field67:field67,field68:field68,
							field69:field69,field63:field63,field49:field49,
							field50:field50
					};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updOrgan.do",
				        params: healthCheckDto
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("healthCheck->updOrgan.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
