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
		
		.list-name-input{
	   color: #333;
	   font-family: tahoma, 'Microsoft YaHei', 'Segoe UI', Arial, 'Microsoft Yahei', Simsun, sans-serif;
	   font-size: 15px;
	   font-weight: bold;
	   height: 50px;
	   margin: 0px;
	   padding: 0px;
	   position: relative;
	   width: 530px;
	  }
	  .list-name-for-select{
	   border: 0;
	   color: #555;
	   height: 20px;
	   lighting-color: rgb(255, 255, 255);
	   line-height: 20px;
	   margin:0 0 10px 0;  
	    outline-color: #555;
	   outline-offset: 0px;
	   outline-style: none;
	   outline-width: 0px; 
	    padding: 4px 6px;
	   position: absolute;
	   top: 1px;
	   left: 3px;
	   vertical-align: middle;
	   width: 486px;
	  }
	  .list-name-input-for-select:focus{
	   border: 0;
	   border-radius: 0;
	  }
	  .list-select{
	   background-color: #FFF;
	   border:1px #ccc solid;
	   border-radius: 4px;
	   color: #555;
	   cursor: pointer;
	   height: 30px;
	   left: 0px;
	   margin:0 0 10px 0;
	   padding: 4px 6px;
	   position: absolute;
	   top: 0px;
	   vertical-align: middle;
	   white-space: pre;
	   width: 530px;
	  }
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<input id="pid" type="hidden" value="${patient.id}"/>
        <div class="tabcontent">
            <table width="100%" height="100%" border="0">
                <tr>
                    <td>
                        <table width="100%" height="100%" border="0">
                            <tr>
                                <td align="center" >
                                	住院情况
                                </td>
                                <td colspan="4" >	
                                    <table width="100%" height="100%">
                                        <tr>
                                            <td align="center" style="width:40%">
                                            	入出院日期
                                            </td>
                                            <td align="center" style="width:30%" >
                                            	住院原因
                                            </td>
                                            <td align="center" style="width:15%">
                                            	医疗机构名称
                                            </td>
                                            <td align="center" style="width:15%">
                                            	病案号
                                            </td>
                                        </tr>
                                        <tr align="center">
                                            <td>
                                            	<input type="text" ng-model="itemField149" id="field149" style="width:100px;"/>&nbsp;<input type="text" ng-model="itemField150" id="field150" style="width:100px;"/>
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField153">
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField155">
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField157">
                                            </td>
                                        </tr>
                                        <tr align="center">
                                            <td>
                                                <input type="text" ng-model="itemField151" id="field151" style="width:100px;"/>&nbsp;<input type="text" ng-model="itemField152" id="field152" style="width:100px;"/>
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField154">
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField156">
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField158">
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" >
                                   	 主要用药情况
                                </td>
                                <td colspan="4">
                                    <table width="100%" height="100%">
                                        <tr align="center">
                                            <td align="center" style="width:27.5%">
                                            	药物名称
                                            </td>
                                            <td align="center" style="width:15%" >
                                               	 用法
                                            </td>
                                            <td align="center" style="width:27.5%">
                                            	用量
                                            </td>
                                            <td align="center" style="width:15%">
                                            	用药时间
                                            </td>
                                            <td align="center" style="width:15%">
                                            	服药依从性
                                            </td>
                                        </tr>
                                        <tr align="center">
                                            <td height="30px">
												 <div id="list-name-input" class="list-name-input" style="width:300px;height:30px;">
												     <select type="text" class="list-select" id="list-select1" style="width:300px;">
												     	<option value="">---请选择---</option>
														<option value="复方利血平">复方利血平</option>
														<option value="美托洛尔">美托洛尔</option>
														<option value="缬沙坦">缬沙坦</option>
														<option value="硝苯地平">硝苯地平</option>
														<option value="利血平">利血平</option>
														<option value="氨氯地平">氨氯地平</option>
														<option value="阿司匹林">阿司匹林</option>
														<option value="辛伐他汀">辛伐他汀</option>
														<option value="罗布麻">罗布麻</option>
														<option value="依那普利">依那普利</option>
														<option value="胰岛素">胰岛素</option>
														<option value="格列吡嗪">格列吡嗪</option>
														<option value="格列美脲">格列美脲</option>
														<option value="瑞格列奈">瑞格列奈</option>
														<option value="二甲双胍">二甲双胍</option>
														<option value="阿卡波糖">阿卡波糖</option>
														<option value="单硝酸异山梨酯">单硝酸异山梨酯</option>
												     </select>
												     <input type="text" class="name item-width list-name-for-select" style="height:28px;width:280px;" id="itemField169">
												 </div>
                                            </td>
                                            <td>
                                            	<select id="itemField170">
                                            		<option value="">---请选择---</option>
													<option value="1">口服</option>
													<option value="2">皮下注射</option>
													<option value="3">静脉注射</option>
													<option value="4">肌肉注射</option>
												</select>
                                            </td>
                                            <td>
                                                <input type="text" ng-model="itemField171">
                                            </td>
                                            <td>
                                                <select id="itemField172">
                                                	<option value="">---请选择---</option>
													<option value="1">1周</option>
													<option value="2">2周</option>
													<option value="3">3周</option>
													<option value="4">1个月</option>
													<option value="5">2个月</option>
													<option value="6">3个月</option>
													<option value="7">4个月</option>
													<option value="8">5个月</option>
													<option value="9">6个月</option>
													<option value="10">7个月</option>
													<option value="11">8个月</option>
													<option value="12">9个月</option>
													<option value="13">10个月</option>
													<option value="14">11个月</option>
													<option value="15">1年</option>
													<option value="16">2年</option>
													<option value="17">3年</option>
													<option value="18">4年</option>
													<option value="19">5年</option>
												</select>
                                            </td>
                                            <td>
                                                <select id="itemField173">
                                                	<option value="">---请选择---</option>
													<option value="1">规律</option>
													<option value="2">间断</option>
													<option value="3">不服药</option>
												</select>
                                            </td>
                                        </tr>
                                        <tr align="center">
                                                <td height="30px">
                                                	<div id="list-name-input" class="list-name-input" style="width:300px;height:30px;">
													     <select type="text" class="list-select" id="list-select2" style="width:300px;">
													     	<option value="">---请选择---</option>
															<option value="复方利血平">复方利血平</option>
															<option value="美托洛尔">美托洛尔</option>
															<option value="缬沙坦">缬沙坦</option>
															<option value="硝苯地平">硝苯地平</option>
															<option value="利血平">利血平</option>
															<option value="氨氯地平">氨氯地平</option>
															<option value="阿司匹林">阿司匹林</option>
															<option value="辛伐他汀">辛伐他汀</option>
															<option value="罗布麻">罗布麻</option>
															<option value="依那普利">依那普利</option>
															<option value="胰岛素">胰岛素</option>
															<option value="格列吡嗪">格列吡嗪</option>
															<option value="格列美脲">格列美脲</option>
															<option value="瑞格列奈">瑞格列奈</option>
															<option value="二甲双胍">二甲双胍</option>
															<option value="阿卡波糖">阿卡波糖</option>
															<option value="单硝酸异山梨酯">单硝酸异山梨酯</option>
													     </select>
													     <input type="text" class="name item-width list-name-for-select" style="height:28px;width:280px;" id="itemField174">
													 </div>
                                                </td>
                                                <td>
                                                    <select id="itemField175">
                                                    	<option value="">---请选择---</option>
														<option value="1">口服</option>
														<option value="2">皮下注射</option>
														<option value="3">静脉注射</option>
														<option value="4">肌肉注射</option>
													</select>
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="itemField176">
                                                </td>
                                                <td>
                                                    <select id="itemField177">
                                                    	<option value="">---请选择---</option>
														<option value="1">1周</option>
														<option value="2">2周</option>
														<option value="3">3周</option>
														<option value="4">1个月</option>
														<option value="5">2个月</option>
														<option value="6">3个月</option>
														<option value="7">4个月</option>
														<option value="8">5个月</option>
														<option value="9">6个月</option>
														<option value="10">7个月</option>
														<option value="11">8个月</option>
														<option value="12">9个月</option>
														<option value="13">10个月</option>
														<option value="14">11个月</option>
														<option value="15">1年</option>
														<option value="16">2年</option>
														<option value="17">3年</option>
														<option value="18">4年</option>
														<option value="19">5年</option>
													</select>
                                                </td>
                                                <td>
                                                    <select id="itemField178">
                                                    	<option value="">---请选择---</option>
														<option value="1">规律</option>
														<option value="2">间断</option>
														<option value="3">不服药</option>
													</select>
                                                </td>
                                            </tr>
                                            <tr align="center">
	                                            <td height="30px">
													<div id="list-name-input" class="list-name-input" style="width:300px;height:30px;">
													     <select type="text" class="list-select" id="list-select3" style="width:300px;">
													     	<option value="">---请选择---</option>
															<option value="复方利血平">复方利血平</option>
															<option value="美托洛尔">美托洛尔</option>
															<option value="缬沙坦">缬沙坦</option>
															<option value="硝苯地平">硝苯地平</option>
															<option value="利血平">利血平</option>
															<option value="氨氯地平">氨氯地平</option>
															<option value="阿司匹林">阿司匹林</option>
															<option value="辛伐他汀">辛伐他汀</option>
															<option value="罗布麻">罗布麻</option>
															<option value="依那普利">依那普利</option>
															<option value="胰岛素">胰岛素</option>
															<option value="格列吡嗪">格列吡嗪</option>
															<option value="格列美脲">格列美脲</option>
															<option value="瑞格列奈">瑞格列奈</option>
															<option value="二甲双胍">二甲双胍</option>
															<option value="阿卡波糖">阿卡波糖</option>
															<option value="单硝酸异山梨酯">单硝酸异山梨酯</option>
													     </select>
													     <input type="text" class="name item-width list-name-for-select" style="height:28px;width:280px;" id="itemField179">
													 </div>
	                                            </td>
	                                            <td>
	                                                <select id="itemField180">
	                                                	<option value="">---请选择---</option>
														<option value="1">口服</option>
														<option value="2">皮下注射</option>
														<option value="3">静脉注射</option>
														<option value="4">肌肉注射</option>
													</select>
	                                            </td>
	                                            <td>
	                                                <input type="text" ng-model="itemField181">
	                                            </td>
	                                            <td>
	                                                <select id="itemField182">
	                                                	<option value="">---请选择---</option>
														<option value="1">1周</option>
														<option value="2">2周</option>
														<option value="3">3周</option>
														<option value="4">1个月</option>
														<option value="5">2个月</option>
														<option value="6">3个月</option>
														<option value="7">4个月</option>
														<option value="8">5个月</option>
														<option value="9">6个月</option>
														<option value="10">7个月</option>
														<option value="11">8个月</option>
														<option value="12">9个月</option>
														<option value="13">10个月</option>
														<option value="14">11个月</option>
														<option value="15">1年</option>
														<option value="16">2年</option>
														<option value="17">3年</option>
														<option value="18">4年</option>
														<option value="19">5年</option>
													</select>
	                                            </td>
	                                            <td>
	                                                <select id="itemField183">
	                                                	<option value="">---请选择---</option>
														<option value="1">规律</option>
														<option value="2">间断</option>
														<option value="3">不服药</option>
													</select>
	                                            </td>
	                                        </tr>
	                                        <tr align="center">
	                                            <td height="30px">
													<div id="list-name-input" class="list-name-input" style="width:300px;height:30px;">
													     <select type="text" class="list-select" id="list-select4" style="width:300px;">
													     	<option value="">---请选择---</option>
															<option value="复方利血平">复方利血平</option>
															<option value="美托洛尔">美托洛尔</option>
															<option value="缬沙坦">缬沙坦</option>
															<option value="硝苯地平">硝苯地平</option>
															<option value="利血平">利血平</option>
															<option value="氨氯地平">氨氯地平</option>
															<option value="阿司匹林">阿司匹林</option>
															<option value="辛伐他汀">辛伐他汀</option>
															<option value="罗布麻">罗布麻</option>
															<option value="依那普利">依那普利</option>
															<option value="胰岛素">胰岛素</option>
															<option value="格列吡嗪">格列吡嗪</option>
															<option value="格列美脲">格列美脲</option>
															<option value="瑞格列奈">瑞格列奈</option>
															<option value="二甲双胍">二甲双胍</option>
															<option value="阿卡波糖">阿卡波糖</option>
															<option value="单硝酸异山梨酯">单硝酸异山梨酯</option>
													     </select>
													     <input type="text" class="name item-width list-name-for-select" style="height:28px;width:280px;" id="itemField184">
													 </div>
	                                            </td>
	                                            <td>
	                                                <select id="itemField185">
	                                                	<option value="">---请选择---</option>
														<option value="1">口服</option>
														<option value="2">皮下注射</option>
														<option value="3">静脉注射</option>
														<option value="4">肌肉注射</option>
													</select>
	                                            </td>
	                                            <td>
	                                                <input type="text" ng-model="itemField186">
	                                            </td>
	                                            <td>
	                                                <select id="itemField187">
	                                                	<option value="">---请选择---</option>
														<option value="1">1周</option>
														<option value="2">2周</option>
														<option value="3">3周</option>
														<option value="4">1个月</option>
														<option value="5">2个月</option>
														<option value="6">3个月</option>
														<option value="7">4个月</option>
														<option value="8">5个月</option>
														<option value="9">6个月</option>
														<option value="10">7个月</option>
														<option value="11">8个月</option>
														<option value="12">9个月</option>
														<option value="13">10个月</option>
														<option value="14">11个月</option>
														<option value="15">1年</option>
														<option value="16">2年</option>
														<option value="17">3年</option>
														<option value="18">4年</option>
														<option value="19">5年</option>
													</select>
	                                            </td>
	                                            <td>
	                                                <select id="itemField188">
	                                                	<option value="">---请选择---</option>
														<option value="1">规律</option>
														<option value="2">间断</option>
														<option value="3">不服药</option>
													</select>
	                                            </td>
	                                        </tr>
	                                        <tr>
				                                <td align="center" colspan="5">
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
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
		<!--工具类-->
  		<script src="<%=basePath%>js/util.js"></script>
		<script type="text/javascript">
			//初始化时间控件
			$(function(){
				$("#field149").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#field150").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#field151").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#field152").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
			});
			
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
			        //入出院日期1 
			        $scope.itemField149 = timestampToTime(changeNullValue(pObj.field149));
					//入出院日期2 
					$scope.itemField150 = timestampToTime(changeNullValue(pObj.field150));
					//入出院日期3 
					$scope.itemField151 = timestampToTime(changeNullValue(pObj.field151));
					//入出院日期4 
					$scope.itemField152 = timestampToTime(changeNullValue(pObj.field152));
					//原因1 
					$scope.itemField153 = changeNullValue(pObj.field153);
					//原因2 
					$scope.itemField154 = changeNullValue(pObj.field154);
					//医疗机构名称1 
					$scope.itemField155 = changeNullValue(pObj.field155);
					//医疗机构名称2 
					$scope.itemField156 = changeNullValue(pObj.field156);
					//病案号1 
					$scope.itemField157 = changeNullValue(pObj.field157);
					//病案号2 
					$scope.itemField158 = changeNullValue(pObj.field158);
					//用量1 
					$scope.itemField171 = changeNullValue(pObj.field171);
					//用量2 
					$scope.itemField176 = changeNullValue(pObj.field176);
					//用量3 
					$scope.itemField181 = changeNullValue(pObj.field181);
					//用量4 
					$scope.itemField186 = changeNullValue(pObj.field186);
					
					$(function(){
						//###下拉选择框
						//药物名称1 
						$("#itemField169").val(changeNullValue(pObj.field169));
						//药物名称2 
						$("#itemField174").val(changeNullValue(pObj.field174));
						//药物名称3 
						$("#itemField179").val(changeNullValue(pObj.field179));
						//药物名称4 
						$("#itemField184").val(changeNullValue(pObj.field184));
						//用法(1) 
						$("#itemField170").val(changeNullValue(pObj.field170));
						//用法(2) 
						$("#itemField175").val(changeNullValue(pObj.field175));
						//用法(3) 
						$("#itemField180").val(changeNullValue(pObj.field180));
						//用法(4) 
						$("#itemField185").val(changeNullValue(pObj.field185));
						//用药时间(1) 
						$("#itemField172").val(changeNullValue(pObj.field172));
						//用药时间(2) 
						$("#itemField177").val(changeNullValue(pObj.field177));
						//用药时间(3) 
						$("#itemField182").val(changeNullValue(pObj.field182));
						//用药时间(4) 
						$("#itemField187").val(changeNullValue(pObj.field187));
						//服药依从性(1) 
						$("#itemField173").val(changeNullValue(pObj.field173));
						//服药依从性(2) 
						$("#itemField178").val(changeNullValue(pObj.field178));
						//服药依从性(3) 
						$("#itemField183").val(changeNullValue(pObj.field183));
						//服药依从性(4) 
						$("#itemField188").val(changeNullValue(pObj.field188));
						//药物名称1
						$("#list-select1").change(function(){
							$("#itemField169").val($(this).val());
						});
						//药物名称2
						$("#list-select2").change(function(){
							$("#itemField174").val($(this).val());
						});
						//药物名称3
						$("#list-select3").change(function(){
							$("#itemField179").val($(this).val());
						});
						//药物名称4
						$("#list-select4").change(function(){
							$("#itemField184").val($(this).val());
						});
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
			    	let reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
			    	let regExp =new RegExp(reg);
			    	//病人id信息
			    	let pid = $("#pid").val();
					// 入出院日期1
			        let field149Time = $("#field149").val();
			        if(field149Time!=""&&!regExp.test(field149Time)){
			        	alert("入出院日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					// 入出院日期2
					let field150Time = $("#field150").val();
					if(field150Time!=""&&!regExp.test(field150Time)){
			        	alert("入出院日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//入出院日期3
					let field151Time = $("#field151").val();
					if(field151Time!=""&&!regExp.test(field151Time)){
			        	alert("入出院日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//入出院日期4
					let field152Time = $("#field152").val();
					if(field152Time!=""&&!regExp.test(field152Time)){
			        	alert("入出院日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//原因1 
					let field153 = encodeURI(changeNullValue($scope.itemField153));
					//原因2 
					let field154 = encodeURI(changeNullValue($scope.itemField154));
					//医疗机构名称1 
					let field155 = encodeURI(changeNullValue($scope.itemField155));
					//医疗机构名称2 
					let field156 = encodeURI(changeNullValue($scope.itemField156));
					//病案号1 
					let field157 = encodeURI(changeNullValue($scope.itemField157));
					//病案号2 
					let field158 = encodeURI(changeNullValue($scope.itemField158));
					//用量1 
					let field171 = encodeURI(changeNullValue($scope.itemField171));
					//用量2 
					let field176 = encodeURI(changeNullValue($scope.itemField176));
					//用量3 
					let field181 = encodeURI(changeNullValue($scope.itemField181));
					//用量4 
					let field186 = encodeURI(changeNullValue($scope.itemField186));
					//药物名称1 
					let field169 = encodeURI($("#itemField169").val());
					//药物名称2 
					let field174 = encodeURI($("#itemField174").val());
					//药物名称3 
					let field179 = encodeURI($("#itemField179").val());
					//药物名称4 
					let field184 = encodeURI($("#itemField184").val());
					
					//###下拉选择框
					//用法(1) 
					let field170 = $("#itemField170").val();
					//用法(2) 
					let field175 = $("#itemField175").val();
					//用法(3) 
					let field180 = $("#itemField180").val();
					//用法(4) 
					let field185 = $("#itemField185").val();
					//用药时间(1) 
					let field172 = $("#itemField172").val();
					//用药时间(2) 
					let field177 = $("#itemField177").val();
					//用药时间(3) 
					let field182 = $("#itemField182").val();
					//用药时间(4) 
					let field187 = $("#itemField187").val();
					//服药依从性(1) 
					let field173 = $("#itemField173").val();
					//服药依从性(2) 
					let field178 = $("#itemField178").val();
					//服药依从性(3) 
					let field183 = $("#itemField183").val();
					//服药依从性(4) 
					let field188 = $("#itemField188").val();
					
					$scope.loadingShow = true;
					//拼装参数
					let patientDto = {pid:pid,field149Time:field149Time,field150Time:field150Time,field151Time:field151Time,
							field152Time:field152Time,field153:field153,field154:field154,field155:field155,
							field156:field156,field157:field157,field158:field158,field171:field171,
							field176:field176,field181:field181,field186:field186,field169:field169,
							field174:field174,field179:field179,field184:field184,field170:field170,
							field175:field175,field180:field180,field185:field185,field172:field172,
							field177:field177,field182:field182,field187:field187,field173:field173,
							field178:field178,field183:field183,field188:field188
					};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updHospitalDrug.do",
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
