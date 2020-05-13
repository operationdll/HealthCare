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
                    <td>
                        <table width="100%" height="100%" >
                            <tr>
                                <td align="center" rowspan="7">现存主要健康问题</td>
                                <td>
                                    <table width="100%" height="100%">
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	脑血管疾病<input id="itemField137" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table >
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field137" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                           	 未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field137" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	缺血性卒中
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field137" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	脑出血
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field137" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	蛛网膜下腔出血
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field137" type="checkbox" value="5" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	短暂性脑缺血
                                                        </td>      
                                                    </tr>
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field137" type="checkbox" value="6" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他
                                                        </td> 
                                                        <td style="border:0px;" colspan="8">
                                                        	&nbsp;<input type="text" id="otherTex137" style="width:60%" ng-model="itemField138" disabled>
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
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	肾脏疾病<input id="itemField139" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table border="0">
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field139" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field139" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	糖尿病肾病
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field139" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	肾功能衰竭
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field139" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	急性肾炎
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field139" type="checkbox" value="5" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	慢性肾炎
                                                        </td>      
                                                    </tr>
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field139" type="checkbox" value="6" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他&nbsp;
                                                        </td>
                                                        <td style="border:0px;" colspan="8">
                                                        	&nbsp;<input type="text" id="otherTex139" style="width:60%" ng-model="itemField140" disabled>
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
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	心脏疾病<input id="itemField141" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table border="0">
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	心肌梗死
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	心绞痛
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	冠状动脉血运重建
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="5" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	充血性心力衰竭
                                                        </td>  
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="6" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	心前区疼痛
                                                        </td>     
                                                    </tr>
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field141" type="checkbox" value="7" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他
                                                        </td> 
                                                        <td style="border:0px;" colspan="10">
                                                        	&nbsp;<input type="text" id="otherTex141" style="width:60%" ng-model="itemField142" disabled>
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
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	血管疾病<input id="itemField268" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table border="0">
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field268" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field268" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	夹层动脉瘤
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field268" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	 动脉闭塞性疾病
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field268" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他
                                                        </td> 
                                                        <td style="border:0px;">
                                                        	&nbsp;<input type="text" id="otherTex268" style="width:60%" ng-model="itemField269" disabled>
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
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	眼部疾病<input id="itemField143" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table border="0">
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field143" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field143" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	视网膜出血或渗出
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field143" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	视乳头水肿
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field143" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	白内障
                                                        </td>   
                                                        <td style="border:0px;">
                                                            <input name="field143" type="checkbox" value="5" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他
                                                        </td> 
                                                        <td style="border:0px;">
                                                        	&nbsp;<input type="text" id="otherTex143" style="width:60%" ng-model="itemField144" disabled>
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
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	神经系统疾病<input id="itemField145" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table border="0">
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field145" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field145" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                           	阿尔茨海默症（老年性痴呆）
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field145" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                           	 帕金森病
                                                        </td>  
                                                        <td style="border:0px;">
                                                            <input name="field145" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他
                                                        </td> 
                                                        <td style="border:0px;">
                                                        	&nbsp;<input type="text" id="otherTex145" style="width:60%" ng-model="itemField146" disabled>
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
                                        <tr height="50px">
                                            <td align="center" style="width:102px;">
                                            	其他系统疾病<input id="itemField147" type="hidden">
                                            </td>
                                            <td colspan="3">
                                                <table border="0">
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="1" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	未发现
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="2" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	糖尿病
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="3" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	慢性支气管炎
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="4" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	慢性阻塞性肺气肿
                                                        </td>
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="5" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	恶性肿瘤
                                                        </td>  
                                                    </tr>
                                                    <tr>
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="6" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	老年性骨关节病
                                                        </td> 
                                                        <td style="border:0px;">
                                                            <input name="field147" type="checkbox" value="7" style="width:15px;height:15px;">
                                                        </td>
                                                        <td style="border:0px;">
                                                        	其他
                                                        </td> 
                                                        <td style="border:0px;" colspan="6">
                                                        	&nbsp;<input type="text" id="otherTex147" style="width:60%" ng-model="itemField148" disabled>
                                                        </td> 
                                                    </tr>
                                                </table>
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
			//是否第一个选中框选中
			function exOne($scope){
				$("input[type='checkbox']").change(function(){
					let v = $(this).val();
					let name = $(this).attr("name");
					let otherName = "#otherTex"+name.substring(5);
					if($(this).val()==1){
						if($(this).get(0).checked){
							let checked = $("input[name='"+name+"']:checked");
							for(let i=1;i<checked.length;i++){
								$(checked[i]).get(0).checked = false;
							}
						}
						$(otherName).val("");
						$(otherName).attr('disabled','disabled');
					}else{
						$("input[name='"+name+"']:eq(0)").get(0).checked = false;
						let islast = $("input[name='"+name+"']:last").get(0).checked;
						if(islast){
							$(otherName).removeAttr('disabled');
						}else{
							if(name=="field137"){
								$scope.itemField138 = "";
							}else if(name=="field139"){
								$scope.itemField140 = "";
							}else if(name=="field141"){
								$scope.itemField142 = "";
							}else if(name=="field143"){
								$scope.itemField144 = "";
							}else if(name=="field145"){
								$scope.itemField146 = "";
							}else if(name=="field147"){
								$scope.itemField148 = "";
							}
							$(otherName).val("");
							$(otherName).attr('disabled','disabled');
						}
					}
				});
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
					//脑血管疾病其他
					$scope.itemField138 = changeNullValue(pObj.field138);
					//肾脏疾病其他
					$scope.itemField140 = changeNullValue(pObj.field140);
					//心血管疾病其他
					$scope.itemField142 = changeNullValue(pObj.field142);
					//血管疾病其他
					$scope.itemField269 = changeNullValue(pObj.field269);
					//眼部疾病其他
					$scope.itemField144 = changeNullValue(pObj.field144);
					//神经系统疾病其他
					$scope.itemField146 = changeNullValue(pObj.field146);
					//其他系统疾病其他
					$scope.itemField148 = changeNullValue(pObj.field148);
			        
					$(function(){
						//是否第一个选中框选中
						exOne($scope);
						//###复选框
						//脑血管疾病[field137]
						$scope.itemField137 = changeNullValue(pObj.field137);
						if($scope.itemField137 == ""){
							$scope.itemField137 = "1";
						}
						initCheckBox("field137",$scope.itemField137);
						
						let islast137 = $("input[name='field137']:last").get(0).checked;
						if(islast137){
							$("#otherTex137").removeAttr('disabled');
						}
						
						//肾脏疾病[field139]
						$scope.itemField139 = changeNullValue(pObj.field139);
						if($scope.itemField139 == ""){
							$scope.itemField139 = "1";
						}
						initCheckBox("field139",$scope.itemField139);
						
						let islast139 = $("input[name='field139']:last").get(0).checked;
						if(islast139){
							$("#otherTex139").removeAttr('disabled');
						}
						
						//心血管疾病[field141]
						$scope.itemField141 = changeNullValue(pObj.field141);
						if($scope.itemField141 == ""){
							$scope.itemField141 = "1";
						}
						initCheckBox("field141",$scope.itemField141);
						
						let islast141 = $("input[name='field141']:last").get(0).checked;
						if(islast141){
							$("#otherTex141").removeAttr('disabled');
						}
						
						//血管疾病[field268]
						$scope.itemField268 = changeNullValue(pObj.field268);
						if($scope.itemField268 == ""){
							$scope.itemField268 = "1";
						}
						initCheckBox("field268",$scope.itemField268);
						
						let islast268 = $("input[name='field268']:last").get(0).checked;
						if(islast268){
							$("#otherTex268").removeAttr('disabled');
						}
						
						//眼部疾病[field143]
						$scope.itemField143 = changeNullValue(pObj.field143);
						if($scope.itemField143 == ""){
							$scope.itemField143 = "1";
						}
						initCheckBox("field143",$scope.itemField143);
						
						let islast143 = $("input[name='field143']:last").get(0).checked;
						if(islast143){
							$("#otherTex143").removeAttr('disabled');
						}
						
						//神经系统疾病[field145]
						$scope.itemField145 = changeNullValue(pObj.field145);
						if($scope.itemField145 == ""){
							$scope.itemField145 = "1";
						}
						initCheckBox("field145",$scope.itemField145);
						
						let islast145 = $("input[name='field145']:last").get(0).checked;
						if(islast145){
							$("#otherTex145").removeAttr('disabled');
						}
						
						//其他系统疾病[field147]
						$scope.itemField147 = changeNullValue(pObj.field147);
						if($scope.itemField147 == ""){
							$scope.itemField147 = "1";
						}
						initCheckBox("field147",$scope.itemField147);
						
						let islast147 = $("input[name='field147']:last").get(0).checked;
						if(islast147){
							$("#otherTex147").removeAttr('disabled');
						}
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
					//脑血管疾病其他
					let field138 = encodeURI(changeNullValue($scope.itemField138));
					//肾脏疾病其他
					let field140 = encodeURI(changeNullValue($scope.itemField140));
					//心血管疾病其他
					let field142 = encodeURI(changeNullValue($scope.itemField142));
					//血管疾病其他
					let field269 = encodeURI(changeNullValue($scope.itemField269));
					//眼部疾病其他
					let field144 = encodeURI(changeNullValue($scope.itemField144));
					//神经系统疾病其他
					let field146 = encodeURI(changeNullValue($scope.itemField146));
					//其他系统疾病其他
					let field148 = encodeURI(changeNullValue($scope.itemField148));

					//###复选框
					//脑血管疾病
					let field137 = getCheckBox('field137');
					$scope.itemField137 = field137;
					//肾脏疾病
					let field139 = getCheckBox('field139');
					$scope.itemField139 = field139;
					//心血管疾病
					let field141 = getCheckBox('field141');
					$scope.itemField141 = field141;
					//血管疾病
					let field268 = getCheckBox('field268');
					$scope.itemField268 = field268;
					//眼部疾病
					let field143 = getCheckBox('field143');
					$scope.itemField143 = field143;
					//神经系统疾病
					let field145 = getCheckBox('field145');
					$scope.itemField145 = field145;
					//其他系统疾病
					let field147 = getCheckBox('field147');
					$scope.itemField147 = field147;
					
					$scope.loadingShow = true;

					//拼装参数
					let patientDto = {pid:pid,field138:field138,field140:field140,field142:field142,
							field144:field144,field146:field146,field148:field148,field137:field137,
							field139:field139,field141:field141,field143:field143,field145:field145,
							field147:field147,field268:field268,field269:field269
						};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updHealthyProblem.do",
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
				        alert("healthCheck->updHealthyProblem.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
