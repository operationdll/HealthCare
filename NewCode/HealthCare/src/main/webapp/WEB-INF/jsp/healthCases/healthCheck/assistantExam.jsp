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
                    <td align="center" rowspan="2">辅助检查</td>
                    <td>
                        <table width="100%" height="100%">
                            <tr height="30px">
                                <td align="center" >
                                   	血型
                                </td>
                                <td align="center" >
                                   	<label>ABO</label>
                                </td>
                                <td colspan="2">
                                    <select id="itemField95">
                                    	<option value="">---请选择---</option>
										<option value="1">A型</option>
										<option value="2">B型</option>
										<option value="3">O型</option>
										<option value="4">AB型</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	 血常规
                                </td>
                                <td align="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td align="center" style="border:0px;">
                                            	血红蛋白<input type="text" style="width:40%" ng-model="itemField97">g/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	白细胞<input type="text" style="width:40%" ng-model="itemField98">x109/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	血小板<input type="text" style="width:40%" ng-model="itemField99">x109/L
                                            </td>
                                            <td align="center" style="border:0px;">
                                            	其他<input type="text" style="width:40%" ng-model="itemField100">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	尿常规
                                </td>
                                <td align="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td align="center" style="border:0px; " >
                                            	尿蛋白<input type="text" style="width:40%" ng-model="itemField101">
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	尿糖<input type="text" style="width:40%" ng-model="itemField102">
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	尿酮体<input type="text" style="width:40%" ng-model="itemField103">
                                            </td>
                                            <td align="center" style="border:0px;">
                                            	尿潜血<input type="text" style="width:40%" ng-model="itemField104">
                                            </td>
                                            <td align="center" style="border:0px;">
                                            	其他<input type="text" style="width:40%" ng-model="itemField105">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr> 
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" height="100%">
                            <tr height="30px">
                                <td align="center" >
                                	空腹血糖
                                </td>
                                <td>
                                    <input type="text" style="width:30%" ng-model="itemField106">mmol/L
                                </td>
                                <td align="center" >
                                	同型半胱氨酸
                                </td>
                                <td>
                                    <input type="text" style="width:30%" ng-model="itemField108">
                                </td>
                            </tr>
                            <tr height="50px">
                                <td align="center" >
                                	肝功能
                                </td>
                                <td align="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td align="center" style="border:0px; " >
                                            	血清谷丙转氨酶<input type="text" style="width:40%" ng-model="itemField114">U/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	血清谷草转氨酶<input type="text" style="width:40%" ng-model="itemField115">U/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	白蛋白<input type="text" style="width:40%" ng-model="itemField116">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" style="border:0px;">
                                            	总胆红素<input type="text" style="width:40%" ng-model="itemField117">μmol/L
                                            </td>
                                            <td align="center" style="border:0px;" colspan="2">
                                            	结合胆红素<input type="text" style="width:40%" ng-model="itemField118">μmol/L
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	肾功能
                                </td>
                                <td align="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td align="center" style="border:0px;">
                                            	血清肌酐<input type="text" style="width:40%" ng-model="itemField119">μmol/L
                                            </td>
                                            <td align="center" style="border:0px;">
                                            	血尿素氮<input type="text" style="width:40%" ng-model="itemField120">μmol/L
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="50px">
                                <td align="center" >
                                	血脂
                                </td>
                                <td align="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td align="center" style="border:0px; " >
                                            	总胆固醇<input type="text" style="width:40%" ng-model="itemField123">mmol/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	甘油三酯<input type="text" style="width:40%" ng-model="itemField124">mmol/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	低密度<input type="text" style="width:40%" ng-model="itemField125">mmol/L
                                            </td>
                                            <td align="center" style="border:0px; ">
                                            	高密度<input type="text" style="width:40%" ng-model="itemField126">mmol/L
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="50px">
                                <td align="center" >
                                	心电图<input id="itemField127" type="hidden">
                                </td>
                                <td colspan="3">
                                    <table width="100%" height="75%" border="0">
                                        <tr>
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="1" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	正常
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="2" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                                ST-T改变
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="3" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	陈旧性心肌梗塞
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="4" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	窦性心动过速
                                            </td>
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="5" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	窦性心动过缓
                                            </td>  
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="6" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	早搏
                                            </td> 
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="7" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	房颤
                                            </td> 
                                        </tr>
                                        <tr>
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="8" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;">
                                            	房室传导阻滞
                                            </td> 
                                            <td style="border:0px;">
                                                <input name="field127" type="checkbox" value="9" style="width:15px;height:15px;">
                                            </td>
                                            <td style="border:0px;" colspan="11">
                                            	其他 &nbsp;<input type="text" style="width:40%" ng-model="itemField128">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="50px">
                                <td align="center" >
                                   	 胸部X片
                                </td>
                                <td colspan="3">
									<select id="itemField129">
										<option value="">---请选择---</option>
										<option value="1">正常</option>
										<option value="2">异常</option>
									</select>
								</td>
                            </tr>
                            <tr height="50px">
                                <td align="center" >
                                    B超
                                </td>
                                <td colspan="3">
                                    <table width="100%" border="0">	
                                        <tr>
                                            <td style="border:0px">
                                            	腹部
                                            </td>
                                            <td style="border:0px">
                                        		<select id="itemField130">
                                        			<option value="">---请选择---</option>
													<option value="1">正常</option>
													<option value="2">异常</option>
												</select>
											</td>
                                            <td style="border:0px">
                                            	<input type="text" ng-model="itemField245">
                                            </td>
                                            <td style="width:5%; border:0px">&nbsp;</td>
                                            <td style="border:0px">
                                            	其他
                                            </td>
                                            <td style="border:0px">
                                        		<select id="itemField132">
													<option value="1">正常</option>
													<option value="2">异常</option>
												</select>
											</td>
                                            <td style="border:0px">
                                            	<input type="text" ng-model="itemField133">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="50px">
                                <td align="center" >
                                	其他
                                </td>
                                <td colspan="3">
									<input type="text" ng-model="itemField136">
								</td>
                            </tr>
                            <tr height="30px">
                                <td align="center" colspan="4">
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
			//验证尿的值（选项值分别为：-、+-、1+、+、2+、3+、4+）（Api值分别为：-、+-、+、++、+++、++++）
			function valiUrine(v){
				let str="、-、+-、1+、+、2+、3+、4+、";
				let bol=false;
				if(v!="" && str.indexOf("、"+v+"、")==-1){
					bol=true;
				}
				return bol;
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
			        //血红蛋白
			        $scope.itemField97 = changeNullValue(pObj.field97);
					//白细胞
					$scope.itemField98 = changeNullValue(pObj.field98);
					//血小板
					$scope.itemField99 = changeNullValue(pObj.field99);
					//血常规其他
					$scope.itemField100 = changeNullValue(pObj.field100);
					//尿蛋白
					$scope.itemField101 = changeNullValue(pObj.field101);
					//尿糖
					$scope.itemField102 = changeNullValue(pObj.field102);
					//尿酮体
					$scope.itemField103 = changeNullValue(pObj.field103);
					//尿潜血
					$scope.itemField104 = changeNullValue(pObj.field104);
					//尿其他
					$scope.itemField105 = changeNullValue(pObj.field105);
					//空腹血糖mmol/L 
					$scope.itemField106 = changeNullValue(pObj.field106);
					//同型半胱氨酸
					$scope.itemField108 = changeNullValue(pObj.field108);
					//血清谷丙转氨酶
					$scope.itemField114 = changeNullValue(pObj.field114);
					//血清谷草转氨酶
					$scope.itemField115 = changeNullValue(pObj.field115);
					//白蛋白
					$scope.itemField116 = changeNullValue(pObj.field116);
					//总胆红素
					$scope.itemField117 = changeNullValue(pObj.field117);
					//结合胆红素
					$scope.itemField118 = changeNullValue(pObj.field118);
					//血清肌酐
					$scope.itemField119 = changeNullValue(pObj.field119);
					//血尿素氮
					$scope.itemField120 = changeNullValue(pObj.field120);
					//总胆固醇
					$scope.itemField123 = changeNullValue(pObj.field123);
					//甘油三酯
					$scope.itemField124 = changeNullValue(pObj.field124);
					//低密度
					$scope.itemField125 = changeNullValue(pObj.field125);
					//高密度
					$scope.itemField126 = changeNullValue(pObj.field126);
					//心电图其他
					$scope.itemField128 = changeNullValue(pObj.field128);
					//B超腹部
					$scope.itemField245 = changeNullValue(pObj.field245);
					//腹部其他
					$scope.itemField133 = changeNullValue(pObj.field133);
					//其他2 
					$scope.itemField136 = changeNullValue(pObj.field136);
					$(function(){
						//ABO 
						$("#itemField95").val(changeNullValue(pObj.field95));
						//胸部X片
						$("#itemField129").val(changeNullValue(pObj.field129));
				        //腹部B超
						$("#itemField130").val(pObj.field130);
						//其他
						$("#itemField132").val(pObj.field132);
					
						//###复选框
						//心电图[field127]
						$scope.itemField127 = pObj.field127;
						initCheckBox("field127",pObj.field127);
						
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
					//血红蛋白
					let field97 = changeNullValue($scope.itemField97);
					if(field97!=""){
						if(!validateFloat(field97)){
							alert("血红蛋白格式不正确!");
							return;
						}
					}
					//白细胞
					let field98 = changeNullValue($scope.itemField98);
					if(field98!=""){
						if(!validateFloat(field98)){
							alert("白细胞格式不正确!");
							return;
						}
					}
					//血小板
					let field99 = changeNullValue($scope.itemField99);
					if(field99!=""){
						if(!validateFloat(field99)){
							alert("血小板格式不正确!");
							return;
						}
					}
					//血常规其他
					let field100 = encodeURI(changeNullValue($scope.itemField100));
					//尿蛋白
					let field101 = changeNullValue($scope.itemField101);
					if(valiUrine(field101)){
						alert("尿蛋白格式不正确,值应为-、+-、1+、+、2+、3+、4+");
						return;
					}
					//尿糖
					let field102 = changeNullValue($scope.itemField102);
					if(valiUrine(field102)){
						alert("尿糖格式不正确,值应为-、+-、1+、+、2+、3+、4+");
						return;
					}
					//尿酮体
					let field103 = changeNullValue($scope.itemField103);
					if(valiUrine(field103)){
						alert("尿酮体格式不正确,值应为-、+-、1+、+、2+、3+、4+");
						return;
					}
					//尿潜血
					let field104 = changeNullValue($scope.itemField104);
					if(valiUrine(field104)){
						alert("尿潜血格式不正确,值应为-、+-、1+、+、2+、3+、4+");
						return;
					}
					//尿其他
					let field105 = encodeURI(changeNullValue($scope.itemField105));
					//空腹血糖mmol/L 
					let field106 = changeNullValue($scope.itemField106);
					if(field106!=""){
						if(!validateFloat(field106)){
							alert("空腹血糖格式不正确!");
							return;
						}
					}
					//同型半胱氨酸
					let field108 = changeNullValue($scope.itemField108);
					//血清谷丙转氨酶
					let field114 = changeNullValue($scope.itemField114);
					if(field114!=""){
						if(!validateFloat(field114)){
							alert("血清谷丙转氨酶格式不正确!");
							return;
						}
					}
					//血清谷草转氨酶
					let field115 = changeNullValue($scope.itemField115);
					if(field115!=""){
						if(!validateFloat(field115)){
							alert("血清谷草转氨酶格式不正确!");
							return;
						}
					}
					//白蛋白
					let field116 = changeNullValue($scope.itemField116);
					if(field116!=""){
						if(!validateFloat(field116)){
							alert("白蛋白格式不正确!");
							return;
						}
					}
					//总胆红素
					let field117 = changeNullValue($scope.itemField117);
					if(field117!=""){
						if(!validateFloat(field117)){
							alert("总胆红素格式不正确!");
							return;
						}
					}
					//结合胆红素
					let field118 = changeNullValue($scope.itemField118);
					if(field118!=""){
						if(!validateFloat(field118)){
							alert("结合胆红素格式不正确!");
							return;
						}
					}
					//血清肌酐
					let field119 = changeNullValue($scope.itemField119);
					if(field119!=""){
						if(!validateFloat(field119)){
							alert("血清肌酐格式不正确!");
							return;
						}
					}
					//血尿素氮
					let field120 = changeNullValue($scope.itemField120);
					if(field120!=""){
						if(!validateFloat(field120)){
							alert("血尿素氮格式不正确!");
							return;
						}
					}
					//总胆固醇
					let field123 = changeNullValue($scope.itemField123);
					if(field123!=""){
						if(!validateFloat(field123)){
							alert("总胆固醇格式不正确!");
							return;
						}
					}
					//甘油三酯
					let field124 = changeNullValue($scope.itemField124);
					if(field124!=""){
						if(!validateFloat(field124)){
							alert("甘油三酯格式不正确!");
							return;
						}
					}
					//低密度
					let field125 = changeNullValue($scope.itemField125);
					if(field125!=""){
						if(!validateFloat(field125)){
							alert("低密度格式不正确!");
							return;
						}
					}
					//高密度
					let field126 = changeNullValue($scope.itemField126);
					if(field126!=""){
						if(!validateFloat(field126)){
							alert("高密度格式不正确!");
							return;
						}
					}
					//心电图其他
					let field128 = encodeURI(changeNullValue($scope.itemField128));
					//B超腹部
					let field245 = changeNullValue($scope.itemField245);
					//腹部其他
					let field133 = encodeURI(changeNullValue($scope.itemField133));
					//其他2 
					let field136 = encodeURI(changeNullValue($scope.itemField136));

					//###下拉选择框
					//ABO 
					let field95 = $("#itemField95").val();
					//胸部X片
					let field129 = $("#itemField129").val();
					//腹部B超
					let field130 = $("#itemField130").val();
					//其他
					let field132 = $("#itemField132").val();

					//###复选框
					//心电图[field127]
					let field127 = getCheckBox('field127');
					$scope.itemField127 = field127;
					
					$scope.loadingShow = true;
					
					//拼装参数
					let patientDto = {pid:pid,field97:field97,field98:field98,field99:field99,
							field100:field100,field101:field101,field102:field102,field103:field103,
							field104:field104,field105:field105,field106:field106,field108:field108,
							field114:field114,field115:field115,field116:field116,field117:field117,
							field118:field118,field119:field119,field120:field120,field123:field123,
							field124:field124,field125:field125,field126:field126,field128:field128,
							field245:field245,field133:field133,field136:field136,field95:field95,
							field129:field129,field130:field130,field132:field132,field127:field127
						};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updAssistantExam.do",
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
				        alert("healthCheck->updAssistantExam.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
