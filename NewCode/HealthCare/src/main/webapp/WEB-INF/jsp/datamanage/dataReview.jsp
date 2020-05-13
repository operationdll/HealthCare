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
		
		.ico_up{
			float: right;
		    width: 0;
		    height: 0;
		    border: 4px solid transparent ;
		    border-bottom-color:#B0B0B0 ;
		    margin-top: 3px;
		}
		
		.ico_down{
		    float: right;
		    width: 0;
		    height: 0;
		    border: 4px solid transparent ;
		    border-top-color:#B0B0B0 ;
			margin-top: 8px;
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
		.cell {
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
	<body ng-app="myApp" ng-controller="myCtrl">
		<div class="content">
			<div class="module">
				<div class="module-head">
					<h3>
						数据审核
					</h3>
				</div>
				<table border="0" width="100%">
					<tr>
						<td align="center">
							<label>日期</label>
						</td>
						<td align="left" width="150px">
							<input type="text" id="startDate" style="width:150px;" readonly="readonly"/>
						</td>
						<td align="center">
							<label>-----</label>
						</td>
						<td align="left" width="150px">
							<input type="text" id="endDate" style="width:150px;" readonly="readonly"/>
						</td>
						<td align="center">
							<label>年龄范围</label>
						</td>
						<td align="left">
							<select id="selAge"
								style="width: auto;">
								<option value="1">全部</option>
								<option value="2">65岁以上</option>
								<option value="3">65岁以下</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
							<select id="selOption" style="width: auto;">
								<option value="1">健康体检</option>
								<option value="2">体检问诊</option>
								<option value="3">辅助检查</option>
								<option value="4">自理能力</option>
								<option value="5">中医体质</option>
							</select>
						</td>
						<td align="left">
							<a class="btn btn-success" ng-click="search()">查询</a> 
						</td>
					</tr>
					<tr>
						<td align="center">
							<label>身份证</label>
						</td>
						<td align="left">
							<input type="text" id="idCard" style="width:150px;"/>
						</td>
						<td align="center">
							<label>姓名</label>
						</td>
						<td align="left">
							<input type="text" id="sname" style="width:150px;"/>
						</td>
						<td align="center">
							<label>条码号</label>
						</td>
						<td align="left">
							<input type="text" id="scode" style="width:150px;"/>
						</td>
						<td align="left">
							<label style="color:red">{{totalNum}}</label>
						</td>
					</tr>
				</table>
			</div>
			<!--/.module-->
		</div>
		
		<div class="data-grid">
	        <div class="data-view">
	            <div class="grid-head">
		            <div class="grid-head-inner">
		                <table class="data-table" id="dataHead">
		                </table>
		            </div>
	            </div>
	            <div class="grid-body">
	            	<table class="datagrid-btable" id="dataBody">
		            </table>
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
		<div id="tooltip" class="tip"></div>
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
				$("#startDate").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#startDate").datepicker('setDate', new Date());
				$("#endDate").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#endDate").datepicker('setDate', new Date());
				//设置表单高度
    			$(".grid-body").css({"height":$(window).height()-230});
    			$(".data-grid").css({"height":$(window).height()-197});
    			//设置表单宽度
    			$(".data-grid").css({"width":$(window).width()});
    			$(".data-view").css({"width":$(window).width()});
    			$(".grid-head").css({"width":$(window).width()});
    			$(".grid-body").css({"width":$(window).width()});
				//添加横向滚动
				$( ".grid-body" ).scroll(function() {
			        let ml="-"+$(this).scrollLeft()+"px";
			        $(".grid-head-inner").css({"margin-left":ml});
			    });
			});
			
			//计算体质指数(v1身高v2体重)
			function getBMI(v1,v2){
				let bmi = "";
				if(v1!=""&&v2!=""){
					v1 = v1/100;
					let high = (v1*v1).toFixed(2);
					bmi = (Number(v2)/(Number(high))).toFixed(2);
				}
				return bmi;
			}
			
			//打开详情页面
			function openDetail(id){
				let selOption = $("#selOption").val();
				window.open("patient/detail.do?id="+id+"&selOption="+selOption);  
			}
			
			//改变表格数据
			function changeDatas($http,$scope,ishead){
				if(ishead==0){
					ishead = true;
				}else{
					ishead = false;
				}
				let $html = "";
				let $head = "";
	        	let selOption = $("#selOption").val();
	        	if(selOption==1){//健康体检
	        		if(ishead){
	        			$head = `<tr class="data-table-row">
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="id">ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:60px;">
				                        	<div ord="field1">姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="field2">性别</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:50px;">
				                        	<div ord="field3">年龄</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field4">体检日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field5">条码号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field6">健康评价</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field7">自理能力</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field8">中医体质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field9">添加时间</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field10">更新时间</div>
				                        </div>
				                    </td>
			                     </tr>
			        		     `;
	        		}
	        		for(let i=0;i<datas.length;i++){
	        			let field4 = timestampToTime(changeNullValue(datas[i].field4));
	        			//健康评价
			    		let field6 = changeNullValue(datas[i].field6);
						if(field6!=""){
							if("1"==field6){
								field6 = "无异常";
							}else if("2"==field6){
								field6 = "有异常";
							}
						}
			    		//自理能力
			    		let field7 = changeNullValue(datas[i].field7);
						if(field7!=""){
							field7 = "已做";
						}
						//中医体质
			    		let field8 = changeNullValue(datas[i].field8);
						if(field8!=""){
							field8 = "已做";
						}
						// 添加时间
						let field9 = timestampToTime(changeNullValue(datas[i].field9));
						// 更新时间
						let field10 = timestampToTime(changeNullValue(datas[i].field10));
	        			$html = $html + `<tr ondblclick="openDetail(`+datas[i].id+`)">
	        								<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].id+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:60px;"> 
													<span>
														`+datas[i].field1+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].field2+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:50px;"> 
													<span>
														`+datas[i].age+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field4+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+datas[i].field5+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field6+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field7+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field8+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field9+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field10+`
													</span>
												</div>	
											</td>
										</tr>
	        							`; 
	        		}
	        		$("#dataHead").css({"width":"890px"});
	        		$("#dataBody").css({"width":"890px"});
	        	}else if(selOption==2){//体检问诊
	        		if(ishead){
	        			$head = `<tr class="data-table-row">
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="id">ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:60px;">
				                        	<div ord="field1">姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="field2">性别</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:50px;">
				                        	<div ord="field3">年龄</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field4">体检日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field5">条码号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field11">症状</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field12">呼吸频率</div>
				                        </div>
				                    </td>
									<td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field13">腰围</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field14">健康状态评估</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:120px;">
				                        	<div ord="field15">老年人自理能力评估</div>
				                        </div>
				                    </td>
									<td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field16">认知能力</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field17">情感状态</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field18">锻炼频率</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field19">饮食习惯</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field20">吸烟状态</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field21">饮酒频率</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field22">齿列</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field23">左眼视力</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field24">右眼视力</div>
				                        </div>
				                    </td>
									<td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field25">听力</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field26">运功功能</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field27">脑血管疾病</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field28">肾脏疾病</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field29">心血管疾病</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field30">眼部疾病</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field31">神经系统疾病</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field32">其他系统疾病</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field6">健康评价</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field7">自理能力</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field8">中医体质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field10">更新时间</div>
				                        </div>
				                    </td>
								</tr>
			        			`;
	        		}
			        for(let i=0;i<datas.length;i++){
			        	let field4 = timestampToTime(changeNullValue(datas[i].field4));
			        	//症状
			    		let field11 = changeNullValue(datas[i].field11);
						if(field11!=""){
							let arr = field11.split(",");
							field11 = "";
							for(let i=0;i<arr.length;i++){
								if("1"==arr[i]){
									field11 = field11 + "无症状,";
								}else if("2"==arr[i]){
									field11 = field11 + "头痛,";
								}else if("3"==arr[i]){
									field11 = field11 + "头晕,";
								}else if("4"==arr[i]){
									field11 = field11 + "心悸,";
								}else if("5"==arr[i]){
									field11 = field11 + "胸闷,";
								}else if("6"==arr[i]){
									field11 = field11 + "胸痛,";
								}else if("7"==arr[i]){
									field11 = field11 + "慢性咳嗽,";
								}else if("8"==arr[i]){
									field11 = field11 + "咳痰,";
								}else if("9"==arr[i]){
									field11 = field11 + "呼吸困难,";
								}else if("10"==arr[i]){
									field11 = field11 + "多饮,";
								}else if("11"==arr[i]){
									field11 = field11 + "多尿,";
								}else if("12"==arr[i]){
									field11 = field11 + "体重下降,";
								}else if("13"==arr[i]){
									field11 = field11 + "乏力,";
								}else if("14"==arr[i]){
									field11 = field11 + "关节肿痛,";
								}else if("15"==arr[i]){
									field11 = field11 + "视力模糊,";
								}else if("16"==arr[i]){
									field11 = field11 + "手脚麻木,";
								}else if("17"==arr[i]){
									field11 = field11 + "尿急,";
								}else if("18"==arr[i]){
									field11 = field11 + "尿痛,";
								}else if("19"==arr[i]){
									field11 = field11 + "便秘,";
								}else if("20"==arr[i]){
									field11 = field11 + "腹泻,";
								}else if("21"==arr[i]){
									field11 = field11 + "恶心呕吐,";
								}else if("22"==arr[i]){
									field11 = field11 + "眼花,";
								}else if("23"==arr[i]){
									field11 = field11 + "耳鸣,";
								}else if("24"==arr[i]){
									field11 = field11 + "乳房胀痛,";
								}else if("25"==arr[i]){
									field11 = field11 + "其他,";
								}
							}
							if(field11!=""){
								field11 = field11.substring(0,field11.length-1);
							}
						}
			    		//呼吸频率
			    		let field12 = changeNullValue(datas[i].field12);
			    		//腰围
			    		let field13 = changeNullValue(datas[i].field13);
			    		// 健康状态评估
			    		let field14 = changeNullValue(datas[i].field14);
			    		if(field14!=""){
			    			if("1"==field14){
								field14 = "满意";
							}else if("2"==field14){
								field14 = "基本满意";
							}else if("3"==field14){
								field14 = "说不清楚";
							}else if("4"==field14){
								field14 = "不太满意";
							}else if("5"==field14){
								field14 = "不满意";
							}
			    		}
						//老年人自理能力评估
						let field15 = changeNullValue(datas[i].field15);
			    		if(field15!=""){
			    			if("1"==field15){
								field15 = "可自理";
							}else if("2"==field15){
								field15 = "轻度依赖";
							}else if("3"==field15){
								field15 = "中度依赖";
							}else if("4"==field15){
								field15 = "不能自理";
							}
			    		}
			    		//认知能力
			    		let field16 = changeNullValue(datas[i].field16);
			    		if(field16!=""){
			    			if("1"==field16){
								field16 = "粗筛阴性";
							}else if("2"==field16){
								field16 = "粗筛阳性";
							}
			    		}
			    		//情感状态
			    		let field17 = changeNullValue(datas[i].field17);
			    		if(field17!=""){
			    			if("1"==field17){
								field17 = "粗筛阴性";
							}else if("2"==field17){
								field17 = "粗筛阳性";
							}
			    		}
			    		//锻炼频率
			    		let field18 = changeNullValue(datas[i].field18);
			    		if(field18!=""){
			    			if("1"==field18){
								field18 = "每天";
							}else if("2"==field18){
								field18 = "每周一次以上";
							}else if("3"==field18){
								field18 = "偶尔";
							}else if("4"==field18){
								field18 = "不锻炼";
							}
			    		}
						//饮食习惯
			    		let field19 = changeNullValue(datas[i].field19);
			    		if(field19!=""){
			    			if("1"==field19){
								field19 = "荤素均衡";
							}else if("2"==field19){
								field19 = "荤食为主";
							}else if("3"==field19){
								field19 = "素食为主";
							}else if("4"==field19){
								field19 = "嗜盐";
							}else if("5"==field19){
								field19 = "嗜油";
							}else if("6"==field19){
								field19 = "嗜糖";
							}
			    		}
			    		//吸烟状态
			    		let field20 = changeNullValue(datas[i].field20);
			    		if(field20!=""){
			    			if("1"==field20){
								field20 = "从不吸烟";
							}else if("2"==field20){
								field20 = "已戒烟";
							}else if("3"==field20){
								field20 = "吸烟";
							}
			    		}
			    		//饮酒频率
			    		let field21 = changeNullValue(datas[i].field21);
			    		if(field21!=""){
			    			if("1"==field21){
								field21 = "从不";
							}else if("2"==field21){
								field21 = "偶尔";
							}else if("3"==field21){
								field21 = "经常";
							}else if("4"==field21){
								field21 = "每天";
							}
			    		}
			    		//齿列
			    		let field22 = changeNullValue(datas[i].field22);
			    		if(field22!=""){
			    			if("1"==field22){
								field22 = "正常";
							}else if("2"==field22){
								field22 = "缺齿";
							}else if("3"==field22){
								field22 = "龋齿";
							}else if("4"==field22){
								field22 = "义齿(假牙)";
							}
			    		}
			    		//左眼视力
			    		let field23 = changeNullValue(datas[i].field23);
			    		//右眼视力
			    		let field24 = changeNullValue(datas[i].field24);
			    		//听力
			    		let field25 = changeNullValue(datas[i].field25);
			    		if(field25!=""){
			    			if("1"==field25){
								field25 = "听见";
							}else if("2"==field25){
								field25 = "听不清或无法听见";
							}
			    		}
						//运功功能
						let field26 = changeNullValue(datas[i].field26);
			    		if(field26!=""){
			    			if("1"==field26){
								field26 = "可顺利完成";
							}else if("2"==field26){
								field26 = "无法独立完成其中任何一个动作";
							}
			    		}
			    		//脑血管疾病
			    		let field27 = changeNullValue(datas[i].field27);
			    		if(field27!=""){
			    			if("1"==field27){
								field27 = "未发现";
							}else if("2"==field27){
								field27 = "缺血性卒中";
							}else if("3"==field27){
								field27 = "脑出血";
							}else if("4"==field27){
								field27 = "蛛网膜下腔出血";
							}else if("5"==field27){
								field27 = "短暂性脑缺血";
							}else if("6"==field27){
								field27 = "其他";
							}
			    		}
			    		//肾脏疾病
			    		let field28 = changeNullValue(datas[i].field28);
			    		if(field28!=""){
			    			if("1"==field28){
								field28 = "未发现";
							}else if("2"==field28){
								field28 = "糖尿病肾病";
							}else if("3"==field28){
								field28 = "肾功能衰竭";
							}else if("4"==field28){
								field28 = "急性肾炎";
							}else if("5"==field28){
								field28 = "慢性肾炎";
							}else if("6"==field28){
								field28 = "其他";
							}
			    		}
			    		//心血管疾病
			    		let field29 = changeNullValue(datas[i].field29);
			    		if(field29!=""){
			    			if("1"==field29){
								field29 = "未发现";
							}else if("2"==field29){
								field29 = "心肌梗死";
							}else if("3"==field29){
								field29 = "心绞痛";
							}else if("4"==field29){
								field29 = "冠状动脉血运重建";
							}else if("5"==field29){
								field29 = "充血性心力衰竭";
							}else if("6"==field29){
								field29 = "心前区疼痛";
							}else if("7"==field29){
								field29 = "高血压";
							}else if("8"==field29){
								field29 = "夹层动脉瘤";
							}else if("9"==field29){
								field29 = "动脉闭塞性疾病";
							}else if("10"==field29){
								field29 = "其他";
							}
			    		}
						//眼部疾病
			    		let field30 = changeNullValue(datas[i].field30);
			    		if(field30!=""){
			    			if("1"==field30){
								field30 = "未发现";
							}else if("2"==field30){
								field30 = "视网膜出血或渗出";
							}else if("3"==field30){
								field30 = "视乳头水肿";
							}else if("4"==field30){
								field30 = "白内障";
							}else if("5"==field30){
								field30 = "其他";
							}
			    		}
			    		//神经系统疾病
			    		let field31 = changeNullValue(datas[i].field31);
			    		if(field31!=""){
			    			if("1"==field31){
								field31 = "未发现";
							}else if("2"==field31){
								field31 = "阿尔茨海默症(老年痴呆症)";
							}else if("3"==field31){
								field31 = "帕金森病";
							}else if("4"==field31){
								field31 = "其他";
							}
			    		}
			    		//其他系统疾病
			    		let field32 = changeNullValue(datas[i].field32);
			    		if(field32!=""){
			    			if("1"==field32){
								field32 = "未发现";
							}else if("2"==field32){
								field32 = "糖尿病";
							}else if("3"==field32){
								field32 = "慢性支气管炎";
							}else if("4"==field32){
								field32 = "慢性阻塞性肺气肿";
							}else if("5"==field32){
								field32 = "恶性肿瘤";
							}else if("6"==field32){
								field32 = "老年性骨关节病";
							}else if("7"==field32){
								field32 = "其他";
							}
			    		}
			    		//健康评价
			    		let field6 = changeNullValue(datas[i].field6);
						if(field6!=""){
							if("1"==field6){
								field6 = "无异常";
							}else if("2"==field6){
								field6 = "有异常";
							}
						}
						//自理能力
			    		let field7 = changeNullValue(datas[i].field7);
						if(field7!=""){
							field7 = "已做";
						}
						//中医体质
			    		let field8 = changeNullValue(datas[i].field8);
						if(field8!=""){
							field8 = "已做";
						}
						// 更新时间
						let field10 = timestampToTime(changeNullValue(datas[i].field10));
						
						$html = $html + `<tr ondblclick="openDetail(`+datas[i].id+`)">
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].id+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:60px;"> 
													<span>
														`+datas[i].field1+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].field2+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:50px;"> 
													<span>
														`+datas[i].age+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field4+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+datas[i].field5+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field11+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field12 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field13 +`
													</span>
												</div>	
											</td>
					    					<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field14 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:120px;"> 
													<span>
														`+field15 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field16 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field17 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field18 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field19 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field20 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field21 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field22 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field23 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field24 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field25 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field26 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field27 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field28 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field29 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field30 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field31 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field32 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field6 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field7 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field8 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field10 +`
													</span>
												</div>	
											</td>
										</tr>
	        							`;
	        		}
	        		$("#dataHead").css({"width":"3010px"});
	        		$("#dataBody").css({"width":"3010px"});
	        	}else if(selOption==3){//辅助检查
	        		if(ishead){
	        			$head = `<tr class="data-table-row">
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="id">ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:60px;">
				                        	<div ord="field1">姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="field2">性别</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:50px;">
				                        	<div ord="field3">年龄</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field4">体检日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field5">条码号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field33">身高</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field34">体重</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field35">体质指数</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field36">左侧高压</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field37">左侧低压</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field38">右侧高压</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field39">右侧低压</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field40">脉搏</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field41">血红蛋白</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field42">白细胞</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field43">血小板</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field44">血常规其他</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field45">尿酮体</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field46">尿潜血</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field47">尿蛋白</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field48">尿糖</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field49">尿常规其他</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field50">血清谷丙</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field51">血清谷草</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field52">总胆红素</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field53">血清肌酐</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field54">血尿素氮</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field55">总胆固醇</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field56">甘油三酯</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field57">血清低密度</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field58">血清高密度</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field59">空腹血糖</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field60">心电图</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field61">心电图其他</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field62">心率</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field63">B超</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field64">B超异常</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field6">健康评价</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field65">健康指导</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field66">危险因素控制</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field10">更新时间</div>
				                        </div>
				                    </td>
								</tr>
				        		`;
	        		}
	        		for(let i=0;i<datas.length;i++){
	        			let field4 = timestampToTime(changeNullValue(datas[i].field4));
	        			//身高
			    		let field33 = changeNullValue(datas[i].field33);
			    		//体重
			    		let field34 = changeNullValue(datas[i].field34);
			    		//体质指数
			    		let field35 = changeNullValue(datas[i].field35);
			    		if(field35==""){
			    			field35 = getBMI(field33,field34);
			    		}
			    		//左侧高压
			    		let field36 = changeNullValue(datas[i].field36);
			    		//左侧低压
			    		let field37 = changeNullValue(datas[i].field37);
						//右侧高压
						let field38 = changeNullValue(datas[i].field38);
						//右侧低压
			    		let field39 = changeNullValue(datas[i].field39);
			    		//脉搏
			    		let field40 = changeNullValue(datas[i].field40);
			    		//血红蛋白
			    		let field41 = changeNullValue(datas[i].field41);
			    		//白细胞
			    		let field42 = changeNullValue(datas[i].field42);
			    		//血小板
			    		let field43 = changeNullValue(datas[i].field43);
			    		//血常规其他
			    		let field44 = changeNullValue(datas[i].field44);
			    		//尿酮体
			    		let field45 = changeNullValue(datas[i].field45);
			    		//尿潜血
			    		let field46 = changeNullValue(datas[i].field46);
			    		//尿蛋白
			    		let field47 = changeNullValue(datas[i].field47);
			    		//尿糖
			    		let field48 = changeNullValue(datas[i].field48);
			    		//尿常规其他
			    		let field49 = changeNullValue(datas[i].field49);
			    		//血清谷丙
			    		let field50 = changeNullValue(datas[i].field50);
			    		//血清谷草
			    		let field51 = changeNullValue(datas[i].field51);
			    		//总胆红素
			    		let field52 = changeNullValue(datas[i].field52);
			    		//血清肌酐
			    		let field53 = changeNullValue(datas[i].field53);
			    		//血尿素氮
			    		let field54 = changeNullValue(datas[i].field54);
			    		//总胆固醇
			    		let field55 = changeNullValue(datas[i].field55);
			    		//甘油三酯
			    		let field56 = changeNullValue(datas[i].field56);
			    		//血清低密度
			    		let field57 = changeNullValue(datas[i].field57);
			    		//血清高密度
			    		let field58 = changeNullValue(datas[i].field58);
			    		//空腹血糖
			    		let field59 = changeNullValue(datas[i].field59);
			    		//心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
			    		let field60 = changeNullValue(datas[i].field60);
			    		if(field60!=""){
			    			let field60Arr = field60.split(",");
			    			field60 = "";
				    		for(let i=0;i<field60Arr.length;i++){
				    			if("1"==field60Arr[i]){
									field60 = field60 +"正常 ";
								}else if("2"==field60Arr[i]){
									field60 = field60 +"ST段改变 ";
								}else if("3"==field60Arr[i]){
									field60 = field60 +"陈旧性心肌梗塞 ";
								}else if("4"==field60Arr[i]){
									field60 = field60 +"窦性心动过速 ";
								}else if("5"==field60Arr[i]){
									field60 = field60 +"窦性心动过缓 ";
								}else if("6"==field60Arr[i]){
									field60 = field60 +"早搏 ";
								}else if("7"==field60Arr[i]){
									field60 = field60 +"房颤 ";
								}else if("8"==field60Arr[i]){
									field60 = field60 +"房室传导阻滞 ";
								}else if("9"==field60Arr[i]){
									field60 = field60 +"其他 ";
								}
				    		}
						}
			    		//心电图其他
			    		let field61 = changeNullValue(datas[i].field61);
			    		//心率
			    		let field62 = changeNullValue(datas[i].field62);
			    		//B超
			    		let field63 = changeNullValue(datas[i].field63);
			    		if(field60!=""){
							if("1"==field63){
								field63 = "正常";
							}else if("2"==field63){
								field63 = "异常";
							}
						}
			    		//b超异常
			    		let field64 = changeNullValue(datas[i].field64);
			    		//健康评价
			    		let field6 = changeNullValue(datas[i].field6);
						if(field6!=""){
							if("1"==field6){
								field6 = "无异常";
							}else if("2"==field6){
								field6 = "有异常";
							}
						}
						//健康指导
			    		let field65 = changeNullValue(datas[i].field65);
						if(field65!=""){
							if("1"==field65){
								field65 = "定期随访";
							}else if("2"==field65){
								field65 = "纳入慢性病患者健康管理";
							}else if("3"==field65){
								field65 = "建议复查";
							}else if("4"==field65){
								field65 = "建议转诊";
							}
						}
						//危险因素控制
			    		let field66 = changeNullValue(datas[i].field66);
						if(field66!=""){
							let arr = field66.split(",");
							field66="";
							for(let i=0;i<arr.length;i++){
								if("1"==arr[i]){
									field66 = field66 + "戒烟,";
								}else if("2"==arr[i]){
									field66 = field66 + "健康饮酒,";
								}else if("3"==arr[i]){
									field66 = field66 + "饮食,";
								}else if("4"==arr[i]){
									field66 = field66 + "锻炼,";
								}else if("5"==arr[i]){
									field66 = field66 + "减体重,";
								}else if("6"==arr[i]){
									field66 = field66 + "建议接种疫苗,";
								}else if("7"==arr[i]){
									field66 = field66 + "其他,";
								}
							}
							field66 = field66.substring(0,field66.length-1);
						}
						//更新时间
						let field10 = timestampToTime(changeNullValue(datas[i].field10));
						
						$html = $html + `<tr ondblclick="openDetail(`+datas[i].id+`)">
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].id+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:60px;"> 
													<span>
														`+datas[i].field1+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].field2+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:50px;"> 
													<span>
														`+datas[i].age+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field4+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+datas[i].field5+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field33 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field34 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field35 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field36 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field37 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field38 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field39 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field40 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field41 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field42 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field43 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field44 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field45 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field46 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field47 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field48 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field49 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field50 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field51 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field52 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field53 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field54 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field55 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field56 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field57 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field58 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field59 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field60 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field61 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field62 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field63 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field64 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field6 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field65 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field66 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field10 +`
													</span>
												</div>	
											</td>
										</tr>
	        							`;
	        		}
	        		$("#dataHead").css({"width":"3990px"});
	        		$("#dataBody").css({"width":"3990px"});
	        	}else if(selOption==4){//自理能力
	        		if(ishead){
	        			$head = `<tr class="data-table-row">
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="id">ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:60px;">
				                        	<div ord="field1">姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="field2">性别</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:50px;">
				                        	<div ord="field3">年龄</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field4">体检日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field5">条码号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field67">进餐</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field68">梳洗</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field69">穿衣</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field70">如厕</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field71">活动</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field72">评估结论</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field10">更新时间</div>
				                        </div>
				                    </td>
								</tr>
			        			`;
	        		}
	        		for(let i=0;i<datas.length;i++){
	        			let field4 = timestampToTime(changeNullValue(datas[i].field4));
	        			//进餐
			    		let field67 = changeNullValue(datas[i].field67);
						if(field67!=""){
							if("1"==field67){
								field67 = "独立完成";
							}else if("3"==field67){
								field67 = "需要协助，如切碎、搅拌食物等";
							}else if("4"==field67){
								field67 = "完全需要帮助";
							}
						}
						//梳洗
			    		let field68 = changeNullValue(datas[i].field68);
						if(field68!=""){
							if("1"==field68){
								field68 = "独立完成";
							}else if("2"==field68){
								field68 = "能独立地洗头、梳头、洗脸、刷牙、剃须等；洗澡需要协助";
							}else if("3"==field68){
								field68 = "在协助下和适当的时间内，能完成部分梳洗活动";
							}else if("4"==field68){
								field68 = "完全需要帮助";
							}
						}
						//穿衣
			    		let field69 = changeNullValue(datas[i].field69);
						if(field69!=""){
							if("1"==field69){
								field69 = "独立完成";
							}else if("3"==field69){
								field69 = "需要协助,在适当的时间内，完成部分穿衣";
							}else if("4"==field69){
								field69 = "完全需要帮助";
							}
						}
						//如厕
			    		let field70 = changeNullValue(datas[i].field70);
						if(field70!=""){
							if("1"==field70){
								field70 = "不需协助，可自控";
							}else if("2"==field70){
								field70 = "偶尔失禁，但基本上能如厕或使用便具";
							}else if("3"==field70){
								field70 = "经常失禁，在很多提示和协助下尚能如厕或使用便具";
							}else if("4"==field70){
								field70 = "完全失禁，完全需要帮助";
							}
						}
						//活动
			    		let field71 = changeNullValue(datas[i].field71);
						if(field71!=""){
							if("1"==field71){
								field71 = "独立完成所有活动";
							}else if("2"==field71){
								field71 = "借助较小的外力或辅助装置能完成站立、行走、上下楼梯等";
							}else if("3"==field71){
								field71 = "借助较大的外力才能完成站立、行走，不能上下楼梯";
							}else if("4"==field71){
								field71 = "借助较大的外力才能完成站立、行走，不能上下楼梯";
							}
						}
						//评估结论
						let field72 = changeNullValue(datas[i].field72);
						// 更新时间
						let field10 = timestampToTime(changeNullValue(datas[i].field10));
						
						$html = $html + `<tr ondblclick="openDetail(`+datas[i].id+`)">
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].id+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:60px;"> 
													<span>
														`+datas[i].field1+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].field2+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:50px;"> 
													<span>
														`+datas[i].age+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field4+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+datas[i].field5+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field67 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field68 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field69 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field70 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field71 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field72  +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field10   +`
													</span>
												</div>	
											</td>
										</tr>
	        							`; 
	        		}
	        		$("#dataHead").css({"width":"1090px"});
	        		$("#dataBody").css({"width":"1090px"});
	        	}else if(selOption==5){//中医体质
	        		if(ishead){
	        			$head = `<tr class="data-table-row">
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="id">ID</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:60px;">
				                        	<div ord="field1">姓名</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:40px;">
				                        	<div ord="field2">性别</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:50px;">
				                        	<div ord="field3">年龄</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field4">体检日期</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field5">条码号</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field73">平和质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field74">气虚质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field75">阳虚质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field76">阴虚质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field77">痰湿质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field78">湿热质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field79">血瘀质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field80">气郁质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field81">特禀质</div>
				                        </div>
				                    </td>
				                    <td>
				                        <div class="datagrid-cell cell" style="width:100px;">
				                        	<div ord="field82">随方医生</div>
				                        </div>
				                    </td>
								</tr>
			        			`;
	        		}
	        		for(let i=0;i<datas.length;i++){
	        			let field4 = timestampToTime(changeNullValue(datas[i].field4));
	        			//平和质
			    		let field73 = changeNullValue(datas[i].field73);
			    		field73 = field73.split(",")[0];
						if(field73!=""){
							if("1"==field73){
								field73 = "是";
							}else if("2"==field73){
								field73 = "倾向是";
							}else{
								field73 = "否";
							}
						}else{
							field73 = "否";
						}
						//阳虚质
			    		let field74 = changeNullValue(datas[i].field74);
			    		field74 = field74.split(",")[0];
						if(field74!=""){
							if("1"==field74){
								field74 = "是";
							}else if("2"==field74){
								field74 = "倾向是";
							}else{
								field74 = "否";
							}
						}else{
							field74 = "否";
						}
						//气虚质
			    		let field75 = changeNullValue(datas[i].field75);
			    		field75 = field75.split(",")[0];
						if(field75!=""){
							if("1"==field75){
								field75 = "是";
							}else if("2"==field75){
								field75 = "倾向是";
							}else{
								field75 = "否";
							}
						}else{
							field75 = "否";
						}
						//阴虚质
			    		let field76 = changeNullValue(datas[i].field76);
			    		field76 = field76.split(",")[0];
						if(field76!=""){
							if("1"==field76){
								field76 = "是";
							}else if("2"==field76){
								field76 = "倾向是";
							}else{
								field76 = "否";
							}
						}else{
							field76 = "否";
						}
						//痰湿质
			    		let field77 = changeNullValue(datas[i].field77);
			    		field77 = field77.split(",")[0];
						if(field77!=""){
							if("1"==field77){
								field77 = "是";
							}else if("2"==field77){
								field77 = "倾向是";
							}else{
								field77 = "否";
							}
						}else{
							field77 = "否";
						}
						//湿热质
			    		let field78 = changeNullValue(datas[i].field78);
			    		field78 = field78.split(",")[0];
						if(field78!=""){
							if("1"==field78){
								field78 = "是";
							}else if("2"==field78){
								field78 = "倾向是";
							}else{
								field78 = "否";
							}
						}else{
							field78 = "否";
						}
						//血瘀质
			    		let field79 = changeNullValue(datas[i].field79);
			    		field79 = field79.split(",")[0];
						if(field79!=""){
							if("1"==field79){
								field79 = "是";
							}else if("2"==field79){
								field79 = "倾向是";
							}else{
								field79 = "否";
							}
						}else{
							field79 = "否";
						}
						//气郁质
			    		let field80 = changeNullValue(datas[i].field80);
			    		field80 = field80.split(",")[0];
						if(field80!=""){
							if("1"==field80){
								field80 = "是";
							}else if("2"==field80){
								field80 = "倾向是";
							}else{
								field80 = "否";
							}
						}else{
							field80 = "否";
						}
						//特禀质
			    		let field81 = changeNullValue(datas[i].field81);
			    		field81 = field81.split(",")[0];
						if(field81!=""){
							if("1"==field81){
								field81 = "是";
							}else if("2"==field81){
								field81 = "倾向是";
							}else{
								field81 = "否";
							}
						}else{
							field81 = "否";
						}
						//随方医生
			    		let field82 = changeNullValue(datas[i].field82);
	        			
	        			$html = $html + `<tr ondblclick="openDetail(`+datas[i].id+`)">
	        								<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].id+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:60px;"> 
													<span>
														`+datas[i].field1+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:40px;"> 
													<span>
														`+datas[i].field2+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:50px;"> 
													<span>
														`+datas[i].age+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field4+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+datas[i].field5+`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field73 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field74 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field75 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field76 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field77 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field78 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field79 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field80 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field81 +`
													</span>
												</div>	
											</td>
											<td>
												<div class="datagrid-cell cell" style="width:100px;"> 
													<span>
														`+field82 +`
													</span>
												</div>	
											</td>
										</tr>
	        							`; 
	        		}
	        		$("#dataHead").css({"width":"1390px"});
	        		$("#dataBody").css({"width":"1390px"});
	        	}
	        	if(!ishead){
	        		$head = "<tr>" + $("#dataHead tr:eq(0)").html() + "</tr>";
	        	}
	        	$("#dataHead").html($head);
	        	$("#dataBody").html($html);
	        	$("#dataBody tr").click(function(){
	        		$("#dataBody tr").css({"background-color":"white"});
	        		$(this).css({"background-color":"#efefef"});
	        	});
	        	headOrder($http,$scope);
			}
			
			let datas = [];
			
			//单机表头的时候进行排序
			function headOrder($http,$scope){
	        	$("#dataHead .cell div").click(function(){
	        		$(".loading").show();
	        		let $td = $(this).find("i").get(0);
	        		if($td!=undefined){
	        			let cname = $(this).find("i").attr("class");
	        			if(cname=="ico_up"){
	        				//降序
	        				$(this).find("i").removeClass("ico_up");
	        				$(this).find("i").addClass("ico_down");
	        				initListData(2,$(this).attr("ord"),$http,$scope);
	        			}else{
	        				//升序
	        				$(this).find("i").removeClass("ico_down");
	        				$(this).find("i").addClass("ico_up");
	        				initListData(1,$(this).attr("ord"),$http,$scope);
	        			}
	        		}else{
	        			//升序
	        			$("#dataHead i").remove();
	        			$(this).append('<i class="ico_up"></i>');
	        			initListData(1,$(this).attr("ord"),$http,$scope);
	        		}
	        	});
			}
			
			//初始化信息
			function initListData(v,filed,$http,$scope){
				$(function(){
					//身份证
				    let idCard = $("#idCard").val();
				    //姓名
				    let name = encodeURI($("#sname").val());
				    //条码号
				    let code = $("#scode").val();
				    //开始日期
				    let startDate = $("#startDate").val();
				    //结束日期
				    let endDate = $("#endDate").val();
				    //年龄
				    let selAge = $("#selAge").val();
				    //年龄排序是反的时间
				    if("field3"==filed){
				    	if(2==v){
				    		v=1;
				    	}else{
				    		v=2;
				    	}
				    }
				    $http({
				        method : "GET",
				        url : "<%=basePath%>DMG/getDataReview.do",
				        params: {idCard:idCard,name:name,code:code,startDate:startDate,endDate:endDate,selAge:selAge,order:v,filed:filed}
				    }).then(function mySucces(response) {
				    	$(".loading").hide();
				    	if(response.data!=null&&response.data!=""){
				    		datas = response.data.datas;
		        			$scope.totalNum = "当前记录总数:"+datas.length+"条数据";
		        			changeDatas($http,$scope,v);
				    	}
				    }, function myError(response) {
				    	$(".loading").hide();
				        alert("DMGListControl->getDataReview.do访问错误出错!");
				        console.log(response.statusText);
				    });
				});
			}
			
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//总条数
				$scope.totalNum = "当前记录总数:0条数据";
				//隐藏加载框
				$(".loading").show();
				//初始化信息
			    initListData(0,'',$http,$scope);
			    //搜索信息
			    $scope.healthName = "";
				//搜索方法
			    $scope.search = function() {
					$(".loading").show();
					$("#dataHead i").remove();
			    	initListData(0,'',$http,$scope);
			    };
			    //下拉改变时
			    $("#selOption").change(function(){
			    	$(".loading").show();
    				$("#dataHead i").remove();
			    	initListData(0,'',$http,$scope);
    			});
    			//初始化div大小
    			$("#DataTables_Table_0_wrapper").css({"height":$(window).height()-190});
    			$("#DataTables_Table_0_wrapper").css({"overflow":"auto"});
			});
		</script>
	</body>
</html>
