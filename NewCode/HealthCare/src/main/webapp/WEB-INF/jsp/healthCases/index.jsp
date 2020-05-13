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

<body>
    <div id="wrapper">
		<input id="uid" type="hidden" value="${uid}"/>
		<input id="selOption" type="hidden" value="${selOption}"/>
        <!-- Navigation -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav nav-second-level">
                	<li>
                        <a href="javascript:void(0)" id="patientHeathBnt">居民健康档案</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="selCase">个人档案</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="healthCheck">健康体检</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="selfCare">自理能力</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="TCM">中医体质</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->

        <div id="page-wrapper" style="padding:0px;">
            <iframe id="contentIframe" style="border:0px;width:100%;height:100%;"></iframe>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

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
			let $url = "patient/phealth.do?id="+$("#uid").val();
			let $sel = $("#selOption").val();
			if($sel!=""){
				if($sel==4){
					$url = "healthCheck/toSelfcare.do?id="+$("#uid").val();
				}else if($sel==5){
					$url = "healthCheck/toTCM.do?id="+$("#uid").val();
				}else{
					$url = "healthCheck/healthCheck.do?id="+$("#uid").val();
				}
			}
			$("#contentIframe").attr("src",$url);
			//居民健康档案
			$("#patientHeathBnt").click(function(){
				$("#contentIframe").attr("src","patient/phealth.do?id="+$("#uid").val());
			});
			//个人档案
			$("#selCase").click(function(){
				$("#contentIframe").attr("src","patient/patientCase.do?id="+$("#uid").val());
			});
			//健康体检
			$("#healthCheck").click(function(){
				$("#contentIframe").attr("src","healthCheck/healthCheck.do?id="+$("#uid").val());
			});
			//自理能力
			$("#selfCare").click(function(){
				$("#contentIframe").attr("src","healthCheck/toSelfcare.do?id="+$("#uid").val());
			});
			//中医体质
			$("#TCM").click(function(){
				$("#contentIframe").attr("src","healthCheck/toTCM.do?id="+$("#uid").val());
			});
		});
	</script>
</body>

</html>
