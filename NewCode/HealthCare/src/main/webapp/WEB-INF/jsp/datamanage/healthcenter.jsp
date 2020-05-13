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
		<link rel="stylesheet"
			href="<%=basePath%>bootstrap/css/bootstrap.min.css">
		<link type="text/css"
			href="<%=basePath%>bootstrap/css/bootstrap-responsive.min.css"
			rel="stylesheet">
		<link type="text/css" href="<%=basePath%>index/css/theme.css"
			rel="stylesheet">
		<link type="text/css"
			href="<%=basePath%>index/images/icons/css/font-awesome.css"
			rel="stylesheet">
		<link type="text/css"
			href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600'
			rel='stylesheet'>

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
		
		/* 表单样式 */
		.data-grid {
			position:relative;
			overflow:hidden;
			font-size:14px;
			color:#444;
			box-sizing:border-box;
			border-color:#95B8E7;
			border-width:1px;
			border-style:solid;
		}
		.data-view {
			position:absolute;
			overflow:hidden;
			top:0px;
			right:0px;
		}
		.grid-head {
			border-color:#DDD;
			overflow:hidden;
			cursor:default;
			border-width:0px 0px 1px;
			border-style:solid;
			background-color:#efefef;
		}
		.grid-head-inner {
			display:block;
			float:left;
		}
		.data-table {
			border-collapse:separate;
		}
		.data-table td {
			border-color:#CCC;
			border-width:0px 1px 1px 0px;
			border-style:dotted;
			margin:0px;
			padding:0px;
		}
		.datagrid-cell {
			margin:0px;
			padding:0px 4px;
			font-size:12px;
		}
		.cell-c1 {
			width:40px;
			height:auto;
		    text-align:center;
		}
		.cell-c2 {
			width:160px;
			height:auto;
		    text-align:center;
		}
		.cell-c3 {
			width:60px;
			height:auto;
		    text-align:center;
		}
		.cell-c4 {
			width:100px;
			height:auto;
		    text-align:center;
		}
		.cell-c5 {
			width:50px;
			height:auto;
		    text-align:center;
		}
		.cell-c6 {
			width:100px;
			height:auto;
		    text-align:center;
		}
		.cell-c7 {
			width:100px;
			height:auto;
		    text-align:center;
		}
		.cell-c8 {
			width:150px;
			height:auto;
		    text-align:center;
		}
		.cell-c9 {
			width:150px;
			height:auto;
		    text-align:center;
		}
		.grid-body {
			overflow:auto;
			margin:0px;
			padding:0px;
		}
		.datagrid-btable {
			border-collapse:separate;
		}
		.datagrid-btable tr {
			height:25px;
		}
		.datagrid-btable tr td {
			border-color:#CCC;
			border-width:0px 1px 1px 0px;
			border-style:dotted;
			margin:0px;
			padding:0px;
		}
		
	</style>
	</head>
	<body>
		<div class="content">
			<div class="module">
				<div class="module-head">
					<h3>
						数据管理
					</h3>
				</div>
				<div class="module-body">
					<form class="form-horizontal row-fluid" id="f1" action="<%=basePath%>DMG/toDataReview1.do" method="post">
						<div class="control-group">
							<label class="control-label" for="basicinput">
								卫生院
							</label>
							<div class="controls">
								<select id="hcid" name="hcid" style="width: auto;">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<a class="btn btn-success" id="sbnt" style="margin-left:10px;margin-top:-8px;">提交</a> 
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<!-- loading -->
		<div class="loading">
			<div class="loading_anim">
				<div class="loader" style="flex-grow: 1;"></div>
				<div
					style="flex-grow: 1; text-align: center; line-height: 52px; height: 52px;">
					数据提交中请稍后
				</div>
			</div>
		</div>
		
		<input type="hidden" id="status" value="${status}"/>
		<input type="hidden" id="code" value="${code}"/>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//状态0数据审核1体检异常
				let status = $("#status").val();
				if(1==status){
					$("#f1").attr("action","<%=basePath%>DMG/abnormalityData1.do")
				}
				let code = $("#code").val();
				$.ajax({
					type: "GET",
					dataType: "json",
					url: "<%=basePath%>system/getHealthByDistrict.do",
					async: true,
					data: {district:code},
					success: function(data){
						$(".loading").hide();
						var data = data.datas;
						let hHtml = '<option value="">--请选择--</option>';
				    	for(let i=0;i<data.length;i++){
				    		hHtml = hHtml + '<option value="'+data[i].id+'">'+data[i].name+'</option>';;
				    	}
				    	$("#hcid").html(hHtml);
					}
				});
				
				//表单提交
				$("#sbnt").click(function(){
					var hcid =  $("#hcid").val();
					if(hcid==""){
						alert("请选择卫生院");
						return;
					}
					$(".loading").show();
					$("#f1").submit();
				});
			});
		</script>
	</body>
</html>
