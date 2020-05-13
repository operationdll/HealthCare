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
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                   	 修改密码
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form">
                                <div class="form-group">
                                    <label>旧密码</label>
                                    <div class="form-group has-error">
	                                    <label class="control-label" for="inputError" id="error2"></label>
	                                </div>
                                    <input id="userPwd" class="form-control" type="password" placeholder="请输入旧密码">
                                </div>
                                <div class="form-group">
                                    <label>新密码</label>
                                    <div class="form-group has-error">
	                                    <label class="control-label" for="inputError" id="error3"></label>
	                                </div>
                                    <input id="newPwd1" class="form-control" type="password" placeholder="请输入新密码">
                                </div>
                                <div class="form-group">
                                    <label>再次确认密码</label>
                                    <div class="form-group has-error">
	                                    <label class="control-label" for="inputError" id="error4"></label>
	                                </div>
                                    <input id="newPwd2" class="form-control" type="password" placeholder="请再次确认密码">
                                </div>
                                <button id="subBnt" type="button" class="btn btn-default">提交</button>
                                <button id="retBnt" type="button" class="btn btn-default">重置</button>
                            </form>
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>

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
			$("#retBnt").click(function(){
				$("#error2").html("");
				$("#error3").html("");
				$("#error4").html("");
				$("form")[0].reset();
			});
			$("#subBnt").click(function(){
				$("#error2").html("");
				$("#error3").html("");
				$("#error4").html("");
				//密码
				let userPwd = $.trim($("#userPwd").val());
				//新密码1
				let newPwd1 = $.trim($("#newPwd1").val());
				//新密码2
				let newPwd2 = $.trim($("#newPwd2").val());
				if(userPwd==""){
					$("#error2").html("旧密码不能为空");
					$("#userPwd").focus();
					return;
				}
				if(newPwd1==""){
					$("#error3").html("新密码不能为空");
					$("#newPwd1").focus();
					return;
				}
				if(newPwd2==""){
					$("#error4").html("再次确认密码不能为空");
					$("#newPwd2").focus();
					return;
				}
				if(newPwd1!=newPwd2){
					$("#error3").html("新密码与再次确认密码不一致");
					$("#newPwd1").focus();
					return;
				}
				$("#subBnt").hide();
				$("#retBnt").hide();
				$.ajax({
					type: "POST",
					dataType: "json",
					url: "system/doUpdatePWD.do",
					data: {'password':newPwd1,'oldpwd':userPwd},
					success: function(data){
				        if(0==data.code){
							alert("密码修改成功");
						}else if(2==data.code){
							$("#error2").html("旧密码不正确");
							$("#userPwd").focus();
							$("#subBnt").show();
							$("#retBnt").show();
							return;
						}else{
							alert("修改失败");
						}
						$("#subBnt").show();
						$("#retBnt").show();
						$("form")[0].reset();
					}
				});
			});
		});
	</script>
</body>

</html>
