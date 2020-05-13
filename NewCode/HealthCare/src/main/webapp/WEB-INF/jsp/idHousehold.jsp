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
	</style>
	</head>
	<body>
		<div class="mytitle">
			<h3>
            	户口本录入
            </h3>
        </div>
			<table width="100%" height="100%" border="0">
				<form id="f1">
				<tr height="30px">
					<td align="center">
						姓名
					</td>
					<td align="left">
						<input type="text" id="itemName" style="width:200px;"/>
					</td>
				</tr>
				<tr height="30px">
					<td align="center">
						性别
					</td>
					<td align="left">
						<input type="radio" value="1" name="itemSex" checked style="width:15px;float:left;"><lable style="float:left;margin-top:5px;">男</lable>
						<input type="radio" value="2" name="itemSex" style="width:15px;float:left;margin-left:5px;"><lable style="float:left;margin-top:5px;">女</lable>
					</td>
				</tr>
				<tr height="30px">
					<td align="center">
						民族
					</td>
					<td align="left">
						<input type="text" id="itemNationality" style="width:200px;"/>
					</td>
				</tr>
				<tr height="30px">
					<td align="center">
						出生日期
					</td>
					<td align="left">
						<input type="text" readonly="readonly" id="itemBirthdate" style="width:200px;"/>
					</td>
				</tr>
				<tr height="30px">
					<td align="center">
						身份证号
					</td>
					<td align="left">
						<input type="text" id="itemIdcard" style="width:200px;"/>
					</td>
				</tr>
				<tr height="30px">
					<td align="center">
						现住址
					</td>
					<td align="center">
						<input type="text" id="itemAddress"/>
					</td>
				</tr>
				<tr height="30px">
					<td align="center">
						户籍地址
					</td>
					<td align="center">
						<input type="text" id="itemAddress1"/>
					</td>
				</tr>
				</form>
				<tr height="30px">
					<td align="center" colspan="2">
						<button class="btn btn-success">
							录入
						</button>
					</td>
				</tr>
				<tr height="100%;">
					<td align="center" colspan="2" style="border:0px;">
						&nbsp;
					</td>
				</tr>
			</table>   
			<a style="display:none;" id="toDoBnt"></a>
			<input id="hcid" type="hidden" value="${user.hcid}"/>
		
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<!--datepicker-->
		<script src="<%=basePath%>js/datepicker/js/datepicker.js"></script>
  		<script src="<%=basePath%>js/datepicker/js/datepicker.zh-CN.js"></script>
		<script type="text/javascript">
			$(function(){
				//初始化时间控件
			    $("#itemBirthdate").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				//身份证修改自动填充生日
				$("#itemIdcard").change(function(){
					let idCard = $(this).val();
					if(idCard.length>13){
						$("#itemBirthdate").val(idCard[6]+idCard[7]+idCard[8]+idCard[9]+"-"+idCard[10]+idCard[11]+"-"+idCard[12]+idCard[13]);
					}
				});
				//提交
				$(".btn").click(function(){
					//姓名
					let itemName = encodeURI($("#itemName").val());
					if($.trim(itemName)==""){
						alert("请输入姓名!");
						return;
					}
					//性别
					let itemSex = $("input[name='itemSex']:checked").val();
					//民族
					let itemNationality = encodeURI($("#itemNationality").val());
					//出生日期
					let itemBirthdate = $("#itemBirthdate").val();
					if($.trim(itemBirthdate)==""){
						alert("请选择出生日期!");
						return;
					}
					//身份证号
					let itemIdcard = $.trim($("#itemIdcard").val());
					if(itemIdcard==null||itemIdcard==""){
						alert("身份证不能为空!");
						return;
					}else if(itemIdcard.length<=13){
						alert("身份证至少13位以上!");
						return;
					}else if(itemIdcard.length>18){
						alert("身份证最多为18位!");
						return;
					}
					//现住址
					let itemAddress = encodeURI($("#itemAddress").val());
					//户籍地址
					let itemAddress1 = encodeURI($("#itemAddress1").val());
					//卫生院id
					let hcid = $("#hcid").val();
					$("#toDoBnt").attr("href","manualInputID://"+itemName+";"+itemSex+";"+itemNationality+";"+itemBirthdate+";"+itemIdcard+";"+itemAddress+";"+itemAddress1+";"+hcid);
					$("#toDoBnt")[0].click();
					alert("信息已提交，请查看打印条形码!");
					$("#f1").reset();
				});
			});
		</script>
	</body>
</html>
