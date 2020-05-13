<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <div class="mytitle">
			<label for="">条码号</label>
			${patient.code}
        </div>
		<table width="100%" height="100%" border="0">
			<tr height="30px">
				<td width="15%" align="center">
					姓名
				</td>
				<td width="18%" align="center">
					${patient.name}
				</td>
				<td width="15%" align="center">
					身份证号
				</td>
				<td width="18%" align="center">
					${patient.idcard}
				</td>
				<td width="15%" align="center">
					随访日期
				</td>
				<td width="19%" align="center">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${patient.createDate}" />
				</td>
			</tr>
			<tr height="60px">
					<td align="center">
						评估事项、内容与评分
					</td>
					<td align="center" colspan="4">
						<table width="100%" border="0" >
							<tr>
								<td align="center" colspan="5">程序等级</td>
							</tr>
							<tr>
								<td style="border:0px;" align="center">
									可自理
								</td>
								<td style="border:0px;" align="center">
									轻度依赖
								</td>
								<td style="border:0px;" align="center">
									重度依赖
								</td>
								<td style="border:0px;" align="center">
									不能自理
								</td>		
							</tr>
						</table>
					</td>
					<td align="center">
						得分
					</td>
				</tr>
			<tr height="60px">
				<td align="center">
					（1）进餐使用餐具将饭菜送入口、咀嚼、吞咽等活动
				</td>
				<td align="center" colspan="4">
					<table width="100%" border="0" >
						<tr>
							<td width="5%" style="border:0px;">
								&nbsp;
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" checked="checked" name="field1" value="1" style="width:15px;height:15px;" score="0">独立完成 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field1" value="2" style="width:15px;height:15px;" score="0">----- 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field1" value="3" style="width:15px;height:15px;" score="3">需要协助，如切碎、搅拌食物等 3分
							</td>
							<td style="border:0px;" width="25%">
								<input type="radio" name="field1" value="4" style="width:15px;height:15px;" score="5">完全需要帮助 5分
							</td>
						</tr>
					</table>
				</td>
				<td align="center">
					<div id="field1">0</div>
				</td>
			</tr>
			<tr height="60px">
				<td align="center">
					（2）梳洗：梳头、洗脸、刷牙、剃须洗澡等活动
				</td>
				<td align="center" colspan="4">
					<table width="100%" border="0">
						<tr>
							<td width="5%" style="border:0px;">
								&nbsp;
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" checked="checked" name="field2" value="1" style="width:15px;height:15px;" score="0">独立完成 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field2" value="2" style="width:15px;height:15px;" score="1">能独立洗头、梳头洗脸、刷牙、剃须等，洗澡需要帮助 1分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field2" value="3" style="width:15px;height:15px;" score="3">在协助下和适当的时间内能完成部分梳头活动 3分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field2" value="4" style="width:15px;height:15px;" score="7">完全需要帮助 7分
							</td>
						</tr>
					</table>
				</td>
				<td align="center">
					<div id="field2">0</div>
				</td>
			</tr>
			<tr height="60px">
				<td align="center">
					（3）穿衣：穿衣裤、袜子、鞋子等活动
				</td>
				<td align="center" colspan="4">
					<table width="100%" border="0">
						<tr>
							<td width="5%" style="border:0px;">
								&nbsp;
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" checked="checked" name="field3" value="1" style="width:15px;height:15px;" score="0">独立完成 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field3" value="2" style="width:15px;height:15px;" score="0">----- 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field3" value="3" style="width:15px;height:15px;" score="3">需要协助，在适当的时间内完成部分穿衣 3分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field3" value="4" style="width:15px;height:15px;" score="5">完全需要帮助 5分
							</td>
						</tr>
					</table>
				</td>
				<td align="center">
					<div id="field3">0</div>
				</td>
			</tr>
			<tr height="60px">
				<td align="center">
					（4）如厕：小便、大便等活动及自控
				</td>
				<td align="center" colspan="4">
					<table width="100%" border="0">
						<tr >
							<td width="5%" style="border:0px;">
								&nbsp;
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" checked="checked" name="field4" value="1" style="width:15px;height:15px;" score="0">不需要协助可自控 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field4" value="2" style="width:15px;height:15px;" score="1">偶尔失禁，但基本上能如厕或使用便具 1分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field4" value="3" style="width:15px;height:15px;" score="5">经常失禁，在很多提示和协助下尚能如厕或使用便具 5分
							</td>
							<td style="border:0px;" width="25%">
								<input type="radio" name="field4" value="4" style="width:15px;height:15px;" score="10">完全失禁，完全需要帮助 10分
							</td>
						</tr>
					</table>
				</td>
				<td align="center">
					<div id="field4">0</div>
				</td>
			</tr>
			<tr height="60px">
				<td align="center">
					（5）活动：站立、室内行走、上下楼梯、户外活动
				</td>
				<td align="center" colspan="4">
					<table width="100%" border="0">
						<tr>
							<td width="5%" style="border:0px;">
								&nbsp;
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" checked="checked" name="field5" value="1" style="width:15px;height:15px;" score="0">独立完成所有活动 0分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field5" value="2" style="width:15px;height:15px;" score="1">借助较小的外力或辅助装置能完成站立、行走、上下楼梯等 1分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field5" value="3" style="width:15px;height:15px;" score="5">借助较大的外力才能完成站立、行走，不能上下楼梯 5分
							</td>
							<td style="border:0px;"  width="25%">
								<input type="radio" name="field5" value="4" style="width:15px;height:15px;" score="10">借助较大的外力才能完成站立、行走，不能上下楼梯 10分
							</td>
						</tr>
					</table>
				</td>
				<td align="center">
					<div id="field5">0</div>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" colspan="5">总得分</td>
				<td align="center">
					<input type="text" id="itemField6" readonly="readonly"/>
				</td>
			</tr>
			<tr height="60px">
				<td align="center">下次随访目标</td>
				<td colspan="5">
					<input type="text" ng-model="itemField7"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">下次随访日期</td>
				<td>
					<input type="text" id="itemField8" style="width:100px;"/>
				</td>
				<td align="center">评估结论</td>
				<td colspan="3">
					<input type="text" ng-model="itemField9" id="itemField9"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">所属机构</td>
				<td>
					${patient.fileunit}
				</td>
				<td align="center">创建时间</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${patient.createDate}" />
				</td>
				<td align="center">更新时间</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${patient.updateDate}" />
				</td>
			</tr>
			<tr height="100%">
				<td align="center" colspan="6">
					<button class="btn btn-success" ng-click="save()">
						保存
					</button>
				</td>
			</tr>
		</table>   
		<!-- loading -->
		<div class="loading" style="display:none;">
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
				$("#itemField8").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
			});
			
			//计算总分
			function getScore($scope,v){
				let total = 0;
				let ches = $("input[type='radio']:checked");
				for(let i=0;i<ches.length;i++){
					let score = Number($(ches[i]).attr('score'));
					total = total + score;
					if("field1"==$(ches[i]).attr('name')){
						$("#field1").html(score);
					}else if("field2"==$(ches[i]).attr('name')){
						$("#field2").html(score);
					}else if("field3"==$(ches[i]).attr('name')){
						$("#field3").html(score);
					}else if("field4"==$(ches[i]).attr('name')){
						$("#field4").html(score);
					}else if("field5"==$(ches[i]).attr('name')){
						$("#field5").html(score);
					}
				}
				if(v==2){
					let str = "可自理";
					if(total>3&&total<9){
						str = "轻度依赖";
					}else if(total>8&&total<19){
						str = "中度依赖";
					}else if(total>18){
						str = "不能自理";
					}
					$scope.itemField9 = str;
					$("#itemField9").val(str);
				}
				return total;
			}
			
			//初始化List信息
			function initListData($http,$scope){
				let pid = $("#pid").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>healthCheck/getSelfcare.do",
				    params: {pid:pid}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        let pObj = response.data.datas;
					// 下次随访目标
					$scope.itemField7 = changeNullValue(pObj.field7);
					// 下次随访日期
					$("#itemField8").val(timestampToTime(changeNullValue(pObj.field8)));
					// 评估结论
					let resultStr = changeNullValue(pObj.field9);
					if(resultStr==""){
						getScore($scope,2);
					}else{
						$scope.itemField9 = resultStr;
					}
					// 总分
					let itemField6 =changeNullValue(pObj.field6);
					if(itemField6==''){
						itemField6 = 0;
					}
					$("#itemField6").val(itemField6);
					$(function(){
						//###单选框
						// 进餐：使用餐具将饭菜送人口、咀嚼、吞咽等活动
						initRedio("field1",pObj.field1);
						// 梳洗：梳头、洗脸、刷牙、剃须洗澡等活动
						initRedio("field2",pObj.field2);
						// 穿衣：穿衣裤、袜子、鞋子等活动
						initRedio("field3",pObj.field3);
						// 如厕：小便、大便等活动及自控
						initRedio("field4",pObj.field4);
						// 活动：站立、室内行走、上下楼梯、户外活动
						initRedio("field5",pObj.field5);
						//初始化得分信息
						getScore($scope,1);
						//设置redio变动的时候重新算总分
						$("input[type='radio']").change(function(){
							$("#itemField6").val(getScore($scope,2));
						});
					});
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("healthCheck->getSelfcare.do访问错误出错!");
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
					// 总分
					let field6 = $("#itemField6").val();
					// 下次随访目标
					let field7 = encodeURI($scope.itemField7);
					// 下次随访日期
					let field8 = $("#itemField8").val();
					if(field8!=""&&!regExp.test(field8)){
			        	alert("下次随访日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					// 评估结论
					let field9 = $scope.itemField9;

					//###单选框
					// 进餐：使用餐具将饭菜送人口、咀嚼、吞咽等活动
					let field1 = getRedio("field1");
					// 梳洗：梳头、洗脸、刷牙、剃须洗澡等活动
					let field2 = getRedio("field2");
					// 穿衣：穿衣裤、袜子、鞋子等活动
					let field3 = getRedio("field3");
					// 如厕：小便、大便等活动及自控
					let field4 = getRedio("field4");
					// 活动：站立、室内行走、上下楼梯、户外活动
					let field5 = getRedio("field5");
					
					$scope.loadingShow = true;
					//拼装参数
					let selfCare = {pid:pid,field6:field6,field7:field7,itemField8:field8,
							field9:field9,field1:field1,field2:field2,field3:field3,
							field4:field4,field5:field5
					};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updSelfcare.do",
				        params: selfCare
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("healthCheck->updSelfcare.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
