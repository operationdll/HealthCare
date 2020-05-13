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
           <table width="100%" height="100%"  border="0">
                <tr>
                    <td align="center">查体</td>
                    <td>
                        <table width="100%" height="100%">
                            <tr height="30px">
                                <td align="center" >
                                   	眼底
                                </td>
                                <td colspan="3">
                                    <select id="itemField70">
										<option value="1">正常</option>
										<option value="2">异常</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	 皮肤
                                </td>
                                <td colspan="3">
                                    <select id="itemField71">
										<option value="1">正常</option>
										<option value="2">潮红</option>
										<option value="3">苍白</option>
										<option value="4">发绀</option>
										<option value="5">黄染</option>
										<option value="6">色素沉着</option>
										<option value="7">其他</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	巩膜
                                </td>
                                <td colspan="3">
                                	<select id="itemField72">
										<option value="1">正常</option>
										<option value="2">黄染</option>
										<option value="3">充血</option>
										<option value="4">其他</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	淋巴结
                                </td>
                                <td colspan="3">
                                	<select id="itemField73">
										<option value="1">未触及</option>
										<option value="2">锁骨上</option>
										<option value="3">腋窝</option>
										<option value="4">其他</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	肺
                                </td>
                                <td colspan="3">
                                    <table width="100%" border="0">
                                        <tr>
                                            <td align="center"  style="width:20%">
                                            	桶状胸
                                            </td>
                                            <td style="width:30%;">
                                               	<select id="itemField74">
													<option value="1">否</option>
													<option value="2">是</option>
												</select>
                                            </td>
                                            <td align="center" style="width:20%;">
                                            	呼吸音
                                            </td>
                                            <td style="width:30%;">
                                                <select id="itemField75">
													<option value="1">正常</option>
													<option value="2">异常</option>
												</select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                            	罗音
                                            </td>
                                            <td colspan="3">
                                                <select id="itemField76">
													<option value="1">无</option>
													<option value="2">干罗音</option>
													<option value="3">湿罗音</option>
													<option value="4">其他</option>
												</select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	心脏
                                </td>
                                <td colspan="3">
                                    <table width="100%" border="0">
                                        <tr>
                                            <td align="center"  style="width:20%">
                                            	心率
                                            </td>
                                            <td style="width:30%;">
                                                <input type="text" ng-model="itemField77" style="width:40%;"/>
                                            </td>
                                            <td align="center" style="width:20%;">
                                            	心律
                                            </td>
                                            <td style="width:30%;">
                                            	<select id="itemField78">
													<option value="1">齐</option>
													<option value="2">不齐</option>
													<option value="3">绝对不齐</option>
												</select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                            	杂音
                                            </td>
                                            <td colspan="3">
                                                <select id="itemField79">
													<option value="1">无</option>
													<option value="2">有</option>
												</select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                	腹部
                                </td>
                                <td colspan="3">
                                    <table width="100%" border="0">
                                        <tr>
                                            <td align="center"  style="width:20%">
                                            	压痛
                                            </td>
                                            <td style="width:30%;">
                                                <select id="itemField80">
													<option value="1">无</option>
													<option value="2">有</option>
												</select>
                                            </td>
                                            <td align="center" style="width:50%;">
                                            	&nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" style="width:20%;">
                                            	包块
                                            </td>
                                            <td style="width:30%;">
                                                 <select id="itemField81">
													<option value="1">无</option>
													<option value="2">有</option>
												</select>
                                            </td>
                                            <td align="center" style="width:50%;">
                                            	&nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center"  style="width:20%">
                                            	肝大
                                            </td>
                                            <td style="width:30%;">
                                                <select id="itemField82">
													<option value="1">无</option>
													<option value="2">有</option>
												</select>
                                            </td>
                                            <td align="center" style="width:50%;">
                                            	&nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" style="width:20%;">
                                            	脾大
                                            </td>
                                            <td style="width:30%;">
                                                <select id="itemField83">
													<option value="1">无</option>
													<option value="2">有</option>
												</select>
                                            </td>
                                            <td align="center" style="width:50%;">
                                            	&nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                            	移动性浊音
                                            </td>
                                            <td style="width:30%;">
                                                <select id="itemField84">
													<option value="1">无</option>
													<option value="2">有</option>
												</select>
                                            </td>
                                            <td align="center" style="width:50%;">
                                            	&nbsp;
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	下肢水肿
                                </td>
                                <td colspan="3">
	                            	<select id="itemField85">
										<option value="1">无</option>
										<option value="2">单侧</option>
										<option value="3">双侧不对称</option>
										<option value="4">双侧对称</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	足背动脉搏动
                                </td>
                                <td colspan="3">
                                    <select id="itemField86">
										<option value="1">未触及</option>
										<option value="2">触及双侧对称</option>
										<option value="3">触及左侧弱或消失</option>
										<option value="4">触及右侧弱或消失</option>
									</select>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	肛门指诊
                                </td>
                                <td colspan="3">
                                    <select id="itemField87">
										<option value="1">未及异常</option>
										<option value="2">触痛</option>
										<option value="3">包块</option>
										<option value="4">前列腺异常</option>
										<option value="5">其他</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                   	乳腺
                                </td>
                                <td colspan="3">
                                	<select id="itemField88">
										<option value="1">未见异常</option>
										<option value="2">乳房切除</option>
										<option value="3">异常泌乳</option>
										<option value="4">乳腺包块</option>
										<option value="5">其他</option>
									</select>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                	<table width="100%" height="100%" border="0">
                                		<tr>
                                			<td style="border:0px;" rowspan="5" align="center">
                                				妇科
                                			</td>
                                			<td align="center">
                                				外阴
                                			</td>
                                		</tr>
                                		<tr>
                                			<td align="center">
                                				阴道
                                			</td>
                                		</tr>
                                		<tr>
                                			<td align="center">
                                				宫颈
                                			</td>
                                		</tr>
                                		<tr>
                                			<td align="center">
                                				宫体
                                			</td>
                                		</tr>
                                		<tr>
                                			<td align="center">
                                				附件
                                			</td>
                                		</tr>
                                	</table>
                                </td>
                                <td colspan="3">
                                    <table width="100%" height="100%" border="0">
                                		<tr>
                                			<td style="border:0px;">
                                				<select id="itemField89">
													<option value="1">未见异常</option>
													<option value="2">异常</option>
												</select>
                                			</td>
                                			<td style="border:0px;">
                                				<input type="text" ng-model="itemField131"/>
                                			</td>
                                		</tr>
                                		<tr>
                                			<td style="border:0px;">
                                				<select id="itemField90">
													<option value="1">未见异常</option>
													<option value="2">异常</option>
												</select>
                                			</td>
                                			<td style="border:0px;">
                                				<input type="text" ng-model="itemField220"/>
                                			</td>
                                		</tr>
                                		<tr>
                                			<td style="border:0px;">
                                				<select id="itemField91">
													<option value="1">未见异常</option>
													<option value="2">异常</option>
												</select>
                                			</td>
                                			<td style="border:0px;">
                                				<input type="text" ng-model="itemField221"/>
                                			</td>
                                		</tr>
                                		<tr>
                                			<td style="border:0px;">
                                				<select id="itemField92">
													<option value="1">未见异常</option>
													<option value="2">异常</option>
												</select>
                                			</td>
                                			<td style="border:0px;">
                                				<input type="text" ng-model="itemField256"/>
                                			</td>
                                		</tr>
                                		<tr>
                                			<td style="border:0px;">
                                				<select id="itemField93">
													<option value="1">未见异常</option>
													<option value="2">异常</option>
												</select>
                                			</td>
                                			<td style="border:0px;">
                                				<input type="text" ng-model="itemField257"/>
                                			</td>
                                		</tr>
                                	</table>
                                </td>
                            </tr>
                            <tr height="30px">
                                <td align="center" >
                                   	其他
                                </td>
                                <td colspan="3">
                                    <input type="text" ng-model="itemField94"/>
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
					//心率
					$scope.itemField77 = changeNullValue(pObj.field77);
					//乳腺其他
					$scope.itemField94 = changeNullValue(pObj.field94);
					//妇科异常1
					$scope.itemField131 = changeNullValue(pObj.field131);
					//妇科异常2
					$scope.itemField220 = changeNullValue(pObj.field220);
					//妇科异常3
					$scope.itemField221 = changeNullValue(pObj.field221);
					//妇科异常4
					$scope.itemField256 = changeNullValue(pObj.field256);
					//妇科异常5
					$scope.itemField257 = changeNullValue(pObj.field257);
					$(function(){
						//###下拉选择框
						//眼底
						if(pObj.field70===undefined){
							pObj.field70 = 1;
						}
						$("#itemField70").val(pObj.field70);
						//皮肤
						if(pObj.field71===undefined){
							pObj.field71 = 1;
						}
						$("#itemField71").val(pObj.field71);
						//巩膜
						if(pObj.field72===undefined){
							pObj.field72 = 1;
						}
						$("#itemField72").val(pObj.field72);
						//淋巴结
						if(pObj.field73===undefined){
							pObj.field73 = 1;
						}
						$("#itemField73").val(pObj.field73);
						//桶状胸
						if(pObj.field74===undefined){
							pObj.field74 = 1;
						}
						$("#itemField74").val(pObj.field74);
						//呼吸音
						if(pObj.field75===undefined){
							pObj.field75 = 1;
						}
						$("#itemField75").val(pObj.field75);
						//罗音
						if(pObj.field76===undefined){
							pObj.field76 = 1;
						}
						$("#itemField76").val(pObj.field76);
						//心律
						if(pObj.field78===undefined){
							pObj.field78 = 1;
						}
						$("#itemField78").val(pObj.field78);
						//杂音
						if(pObj.field79===undefined){
							pObj.field79 = 1;
						}
						$("#itemField79").val(pObj.field79);
						//压痛
						if(pObj.field80===undefined){
							pObj.field80 = 1;
						}
						$("#itemField80").val(pObj.field80);
						//包块
						if(pObj.field81===undefined){
							pObj.field81 = 1;
						}
						$("#itemField81").val(pObj.field81);
						//肝大
						if(pObj.field82===undefined){
							pObj.field82 = 1;
						}
						$("#itemField82").val(pObj.field82);
						//脾大
						if(pObj.field83===undefined){
							pObj.field83 = 1;
						}
						$("#itemField83").val(pObj.field83);
						//移动性浊音
						if(pObj.field84===undefined){
							pObj.field84 = 1;
						}
						$("#itemField84").val(pObj.field84);
						//下肢水肿
						if(pObj.field85===undefined){
							pObj.field85 = 1;
						}
						$("#itemField85").val(pObj.field85);
						//足背动脉搏动
						if(pObj.field86===undefined){
							pObj.field86 = 1;
						}
						$("#itemField86").val(pObj.field86);
						//肛门指诊
						if(pObj.field87===undefined){
							pObj.field87 = 1;
						}
						$("#itemField87").val(pObj.field87);
						//判断性别是否为男
						if($("#psex").val()==1){
							let optHtml = '<option value="">无</option>';
							//妇科1
							pObj.field89 = "";
							$("#itemField89").html(optHtml);
							$("#itemField89").val(pObj.field89);
							//妇科2
							pObj.field90 = "";
							$("#itemField90").html(optHtml);
							$("#itemField90").val(pObj.field90);
							//妇科3
							pObj.field91 = "";
							$("#itemField91").html(optHtml);
							$("#itemField91").val(pObj.field91);
							//妇科4
							pObj.field92 = "";
							$("#itemField92").html(optHtml);
							$("#itemField92").val(pObj.field92);
							//妇科5
							pObj.field93 = "";
							$("#itemField93").html(optHtml);
							$("#itemField93").val(pObj.field93);
						}else{
							//妇科1
							if(pObj.field89===undefined){
								pObj.field89 = "1";
							}
							$("#itemField89").val(pObj.field89);
							//妇科2
							if(pObj.field90===undefined){
								pObj.field90 = "1";
							}
							$("#itemField90").val(pObj.field90);
							//妇科3
							if(pObj.field91===undefined){
								pObj.field91 = "1";
							}
							$("#itemField91").val(pObj.field91);
							//妇科4
							if(pObj.field92===undefined){
								pObj.field92 = "1";
							}
							$("#itemField92").val(pObj.field92);
							//妇科5
							if(pObj.field93===undefined){
								pObj.field93 = "1";
							}
							$("#itemField93").val(pObj.field93);
						}
			        	//乳腺[field88]
						if(pObj.field88===undefined){
							pObj.field88 = 1;
						}
						$("#itemField88").val(pObj.field88);
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
					//心率
			        let field77 = changeNullValue($scope.itemField77);
			        if(field77!=""){
						if(!validateNum(field77)){
							alert("心率必须为整数!");
							return;
						}else if(Number(field77)<10||Number(field77)>200){
							alert("心率必须为10-200之间!");
							return;
						}
					}
			        //妇科其他
			        let field94 = encodeURI(changeNullValue($scope.itemField94));
			        //妇科异常1
			        let field131 = encodeURI(changeNullValue($scope.itemField131));
					//妇科异常2
					let field220 = encodeURI(changeNullValue($scope.itemField220));
					//妇科异常3
					let field221 = encodeURI(changeNullValue($scope.itemField221));
					//妇科异常4
					let field256 = encodeURI(changeNullValue($scope.itemField256));
					//妇科异常5
					let field257 = encodeURI(changeNullValue($scope.itemField257));
			        
					//###下拉选择框
					//眼底
					let field70 = $("#itemField70").val();
					//皮肤
					let field71 = $("#itemField71").val();
					//巩膜
					let field72 = $("#itemField72").val();
					//淋巴结
					let field73 = $("#itemField73").val();
					//桶状胸
					let field74 = $("#itemField74").val();
					//呼吸音
					let field75 = $("#itemField75").val();
					//罗音
					let field76 = $("#itemField76").val();
					//心律
					let field78 = $("#itemField78").val();
					//杂音
					let field79 = $("#itemField79").val();
					//压痛
					let field80 = $("#itemField80").val();
					//包块
					let field81 = $("#itemField81").val();
					//肝大
					let field82 = $("#itemField82").val();
					//脾大
					let field83 = $("#itemField83").val();
					//移动性浊音
					let field84 = $("#itemField84").val();
					//下肢水肿
					let field85 = $("#itemField85").val();
					//足背动脉搏动
					let field86 = $("#itemField86").val();
					//肛门指诊
					let field87 = $("#itemField87").val();
					//妇科1
					let field89 = $("#itemField89").val();
					//妇科2
					let field90 = $("#itemField90").val();
					//妇科3
					let field91 = $("#itemField91").val();
					//妇科4
					let field92 = $("#itemField92").val();
					//妇科5
					let field93 = $("#itemField93").val();
		        	//饮食习惯
					let field88 = $("#itemField88").val();
					
					$scope.loadingShow = true;
					
					//拼装参数
					let patientDto = {pid:pid,field77:field77,field70:field70,field71:field71,
							field72:field72,field73:field73,field74:field74,field75:field75,
							field76:field76,field78:field78,field79:field79,field80:field80,
							field81:field81,field82:field82,field83:field83,field84:field84,
							field85:field85,field86:field86,field94:field94,field87:field87,
							field89:field89,field90:field90,field91:field91,field92:field92,
							field93:field93,field88:field88,field131:field131,field220:field220,
							field221:field221,field256:field256,field257:field257
						};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updExam.do",
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
				        alert("healthCheck->updExam.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
