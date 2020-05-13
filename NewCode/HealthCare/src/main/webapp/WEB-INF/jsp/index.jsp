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

</head>

<body style="overflow: hidden;">
	<input type="hidden" id="isadmin" value="${user.userName}"/>
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;background:white;">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img src="<%=basePath%>images/logo.jpg" style="width:51px;height:51px;margin-left:18px;">
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="javascript:void(0)" id="updatePwd"><i class="fa fa-gear fa-fw"></i> 修改密码</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="javascript:void(0)" id="logoutBnt"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                    	<li id="systemMGE" style="display:none;">
                            <a href="javascript:void(0)"><i class="fa fa-wrench fa-fw"></i> 系统管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            	<li>
                                    <a href="javascript:void(0)" id="areaBnt">区域管理</a>
                                </li>
                            	<li>
                                    <a href="javascript:void(0)" id="hcBnt">卫生所管理</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="userBnt">用户管理</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="showMap">全国体检数据</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="showCData">各个卫生院体检数据</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="synDataBnt">省数据同步</a>
                                </li>
                               	<li>
                                    <a href="javascript:void(0)" id="synHislog">省错误数据上传</a>
                                </li>
                            </ul>
                        </li>
                        <li class="notAdmin">
                            <a href="javascript:void(0)"><i class="fa fa-dashboard fa-fw"></i> 身份证扫描<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0)" id="idScanBnt">身份证扫描</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="idHousehold">户口本录入</a>
                                </li>
                            </ul>
                        </li>
                        <li class="notAdmin">
                            <a href="javascript:void(0)"><i class="fa fa-dashboard fa-fw"></i> 健康档案<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0)" id="patientBnt">体检人员信息</a>
                                </li>
                            </ul>
                        </li>
                        <li class="notAdmin">
                            <a href="javascript:void(0)"><i class="fa fa-bar-chart-o fa-fw"></i> 数据管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0)" id="dataBnt">数据审核</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="abnormalityBnt">体检异常</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="synDataUserBnt">省数据同步</a>
                                </li>
                            </ul>
                        </li>
                        <li id="dataManage" style="display:none;">
                            <a href="javascript:void(0)"><i class="fa fa-bar-chart-o fa-fw"></i> 数据管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0)" id="dataMBnt1">数据审核</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" id="dataMBnt2">体检异常</a>
                                </li>
                            </ul>
                        </li>
                        <li class="notAdmin">
                            <a href="javascript:void(0)"><i class="fa fa-edit fa-fw"></i> 打印管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0)" id="printBnt">报告打印</a>
																</li>
																<li>
																	<a href="javascript:void(0)" id="doctorBnt">家医签约</a>
															</li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper" style="padding:0px;">
			<iframe id="contentIframe" style="border:0px;width:100%;height:92%;"></iframe>
			<div style="display: block;width: 100%;height: 100%;text-align: center;">
				Copyright &copy; Ji Shi Jian Kang Ltd. All rights reserved. ICP证:<a href="javascript:window.open('http://www.beian.miit.gov.cn')" style="color: red;">京ICP备19025060号-1</a>
			</div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    <input type="hidden" id="hcid" value="${user.hcid}"/>

    <!-- jQuery -->
    <script src="<%=basePath%>backEnd/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>backEnd/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=basePath%>backEnd/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=basePath%>backEnd/dist/js/sb-admin-2.js"></script>

	<script type="text/javascript">
		$(function(){
			//判断是否是管理员
			if($("#isadmin").val()=="admin"){
				$("#systemMGE").show();
				$(".notAdmin").hide();
				$("#contentIframe").attr("src","system/toStatistics.do");
			}else{//判断是否为非卫生院用户
				let hcid = $("#hcid").val();
				if(hcid==""){
					$(".notAdmin").hide();
					$("#dataManage").show();
				}
			}
			//全国体检数据
			$("#showMap").click(function(){
				$("#contentIframe").attr("src","system/toStatistics.do");
			});
			//各个卫生院体检数据
			$("#showCData").click(function(){
				$("#contentIframe").attr("src","system/toCData.do");
			});
			//修改密码
			$("#updatePwd").click(function(){
				$("#contentIframe").attr("src","system/updatePWD.do");
			});
			//身份证扫描
			$("#idScanBnt").click(function(){
				$("#contentIframe").attr("src","system/toIdScan.do");
			});
			//区域管理
			$("#areaBnt").click(function(){
				$("#contentIframe").attr("src","area/list.do");
			});
			//卫生所管理
			$("#hcBnt").click(function(){
				$("#contentIframe").attr("src","system/healthcenterList.do");
			});
			//用户管理
			$("#userBnt").click(function(){
				$("#contentIframe").attr("src","system/userList.do");
			});
			//省数据同步
			$("#synDataBnt").click(function(){
				$("#contentIframe").attr("src","system/synDataView.do");
			});
			//省错误数据上传
			$("#synHislog").click(function(){
				$("#contentIframe").attr("src","system/toHislog.do");
			});
			//用户省数据同步
			$("#synDataUserBnt").click(function(){
				$("#contentIframe").attr("src","system/synDataView.do");
			});
			//体检人员信息
			$("#patientBnt").click(function(){
				$("#contentIframe").attr("src","patient/list.do?status=1");
			});
			//数据审核
			$("#dataBnt").click(function(){
				$("#contentIframe").attr("src","DMG/toDataReview.do");
			});
			//体检异常
			$("#abnormalityBnt").click(function(){
				$("#contentIframe").attr("src","DMG/abnormalityData.do");
			});
			//报告打印
			$("#printBnt").click(function(){
				$("#contentIframe").attr("src","print/printList.do");
			});
			//家医管理
			$("#doctorBnt").click(function(){
				$("#contentIframe").attr("src","doctor/doctor.do");
			});
			//户口本录入
			$("#idHousehold").click(function(){
				$("#contentIframe").attr("src","system/idHousehold.do");
			});
			
			//数据审核
			$("#dataMBnt1").click(function(){
				$("#contentIframe").attr("src","DMG/healthCenter.do?status=0");
			});
			
			//体检异常
			$("#dataMBnt2").click(function(){
				$("#contentIframe").attr("src","DMG/healthCenter.do?status=1");
			});
			
			//登出
			$("#logoutBnt").click(function(){
			    $.ajax({
					type: "POST",
					dataType: "json",
					url: "system/logout.do",
					success: function(data){
						window.location = "index.html";
					}
				});
			});
		});
	</script>
</body>

</html>
