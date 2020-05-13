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
		
		<!-- Morris Charts CSS -->
		<link href="<%=basePath%>backEnd/vendor/morrisjs/morris.css" rel="stylesheet">
		
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
			display: none;
			padding: 50px 20px;
			height: 100%;
		}
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<input id="uid" type="hidden" value="${patient.id}"/>
		<div class="mytitle">
			<table width="100%" height="30px">
                <tr>
                    <td>
                    	<label>身份证： </label>
                    </td>
                    <td align="left">
                    	${patient.idcard}
                    </td>
                    <td>
                    	<label>姓名： </label>
                    </td>
                    <td align="left">
                    	${patient.name}
                    </td>
                    <td>
                    	<label>条码号： </label>
                    </td>
                    <td align="left">
                    	${patient.code}
                    </td>
				</tr>
				<tr>
                    <td>
                    	<label>电话： </label>
                    </td>
                    <td align="left">
                    	${patient.phoneno}
                    </td>
                    <td>
                    	<label>年龄：  </label>
                    </td>
                    <td align="left">
                    	${patient.age}
                    </td>
				</tr>
			</table>
		</div>
		<!-- tab buttons -->
		<button class="tablink" id="conditions">一般情况</button>
		<button class="tablink" id="lifestyle">生活方式</button>
		<button class="tablink" id="organfunction">脏器功能</button>
		<button class="tablink" id="exam">查体</button>
		<button class="tablink" id="assistantExam">辅助检查</button>
		<button class="tablink" id="healthyProblem">现存健康问题</button>
		<button class="tablink" id="hospitalDrug">住院用药</button>
		<button class="tablink" id="healthyComment">健康评价</button>

		<!-- iframe show each individual page -->
		<iframe id="frameDisplay" style="border:0px;width:100%;" scrolling="no"></iframe>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			function changeBg(id){
				$(".tablink").css("background-color","white");
				$(id).css("background-color","#777");
			}
			
			$(function(){
				//初始化一般情况页面
				changeBg("#conditions");
				$("#frameDisplay").attr("src","healthCheck/toGenerally.do?id="+$("#uid").val());
				$("#frameDisplay").css("height","605px");
				$("#conditions").click(function(){
					changeBg("#conditions");
					$("#frameDisplay").css("height","605px");
					$("#frameDisplay").attr("src","healthCheck/toGenerally.do?id="+$("#uid").val());
				});
				$("#lifestyle").click(function(){
					changeBg("#lifestyle");
					$("#frameDisplay").css("height","605px");
					$("#frameDisplay").attr("src","healthCheck/toLifestyle.do?id="+$("#uid").val());
				});
				$("#organfunction").click(function(){
					changeBg("#organfunction");
					$("#frameDisplay").css("height","605px");
					$("#frameDisplay").attr("src","healthCheck/toOrgan.do?id="+$("#uid").val());
				});
				$("#exam").click(function(){
					changeBg("#exam");
					$("#frameDisplay").css("height","905px");
					$("#frameDisplay").attr("src","healthCheck/toExam.do?id="+$("#uid").val());
				});
				$("#assistantExam").click(function(){
					changeBg("#assistantExam");
					$("#frameDisplay").css("height","615px");
					$("#frameDisplay").attr("src","healthCheck/toAssistantExam.do?id="+$("#uid").val());
				});
				$("#healthyProblem").click(function(){
					changeBg("#healthyProblem");
					$("#frameDisplay").css("height","605px");
					$("#frameDisplay").attr("src","healthCheck/toHealthyProblem.do?id="+$("#uid").val());
				});
				$("#hospitalDrug").click(function(){
					changeBg("#hospitalDrug");
					$("#frameDisplay").css("height","455px");
					$("#frameDisplay").attr("src","healthCheck/toHospitalDrug.do?id="+$("#uid").val());
				});
				$("#healthyComment").click(function(){
					changeBg("#healthyComment");
					$("#frameDisplay").css("height","605px");
					$("#frameDisplay").attr("src","healthCheck/toHealthyComment.do?id="+$("#uid").val());
				});
			});
		</script>
	</body>
</html>
