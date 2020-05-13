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
		 <!--病人BMI值-->
		 <input id="pBMI" type="hidden" value="${healthCheck.field219}"/>
		 <!--病人腰围-->
		 <input id="waistline" type="hidden" value="${healthCheck.field13}"/>
         <div class="tabcontent">   
            <table width="100%">
                <tr height="30px">
                    <td align="center" colspan="5">
                        <table width="100%">
                            <tr>
                                <td align="center" style="border:0px; " >
                                	身份证： ${patient.idcard}
                                </td>
                                <td align="center" style="border:0px; ">
                                	姓名： ${patient.name}
                                </td>
                                <td align="center" style="border:0px; ">
                                	条码号： ${patient.code}
                                </td>
                                <td align="center" style="border:0px;">
                                	随访医生： <input type="text" style="width:100px;"  ng-model="itemField52">
                                </td>
                                <td align="center" style="border:0px;">
                                   	 随访日期：<fmt:formatDate pattern="yyyy-MM-dd" value="${patient.createDate}" />
                                </td>
                            </tr>
                        </table>
                    </td> 
                </tr>
                <tr height="30px">
                    <td>
                    	（1)您精力充沛吗？（指精神头足，乐于做事）（1.2.4.5.13平和质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" checked name="field1" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field1" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field1" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field1" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field1" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qixu">
                    	（2)您容易疲乏吗？（指体力是否稍微活动一下或做点家务劳动就感到累）（气虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field2" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field2" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field2" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field2" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field2" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qixu">
                    	（3)您容易气短，呼吸短促，接不上气吗？（气虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field3" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field3" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field3" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field3" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field3" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qixu">
                    	（4)您说话声音低弱无力吗？（指说话没有力气）（气虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field4" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field4" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field4" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field4" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field4" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qiyu">
                    	（5)您感到闷闷不乐，情绪低沉吗？（指心情不愉快，情绪低落）（气郁质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field5" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field5" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field5" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field5" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field5" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qiyu">
                    	（6)您容易精神紧张，焦虑不安吗？（指遇事是否心情紧张）（气郁质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field6" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field6" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field6" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field6" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field6" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qiyu">
                    	（7)您因为生活状态改变而感到孤独，失落吗？（气郁质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field7" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field7" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field7" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field7" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field7" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qiyu">
                    	（8)您容易感到害怕或受到惊吓吗？（气郁质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field8" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field8" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field8" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field8" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field8" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tanshi">
                    	（9)您感到身体超重不轻松吗？（痰湿质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field9" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">BMI<24</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field9" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">25>BMI≥24</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field9" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">26>BMI≥25</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field9" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">28>BMI≥26</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field9" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">BMI≥28</label>
                                </td>
                                
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yinxu">
                   		（10)您眼睛干涩吗？（阴虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field10" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field10" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field10" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field10" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field10" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yangxu">
                    	（11)您手脚发凉吗？（不包含因周围温度低或穿的少导致的手脚发冷）（阳虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field11" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field11" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field11" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field11" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field11" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yangxu">
                    	（12)您胃脘部，背部，腰部或膝关节等，有一处或多处怕冷（阳虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field12" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field12" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field12" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field12" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field12" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yangxu">
                    	（13)您比一般人耐受不了寒冷吗？（指比别人容易害怕冬天或者是夏天的冷空调，电扇等）（阳虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field13" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field13" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field13" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field13" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field13" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="qixu">
                    	（14)您容易患感冒吗？（指每年感冒的次数）（气虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field14" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field14" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field14" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field14" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field14" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tebing">
                    	（15)您没有感冒也会鼻塞，流鼻涕吗？（特禀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field15" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field15" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field15" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field15" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field15" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tanshi">
                    	（16)您有口粘口腻或睡觉打鼾吗？（痰湿质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field16" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field16" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field16" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field16" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field16" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tebing">
                    	（17)您容易过敏吗？（对药物，食物，气味，花粉或在季节交替，气候变化时）（特禀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field17" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field17" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field17" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field17" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field17" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tebing">
                    	（18)您的皮肤容易起寻麻疹吗？（包括风团，风疹块，风疙瘩）（特禀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field18" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field18" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field18" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field18" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field18" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="xueyu">
                    	（19)您的皮肤在不知不觉中会出现青紫淤斑，皮下出血吗？（指皮肤在没有外伤情况下出现青紫块）（血瘀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field19" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field19" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field19" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field19" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field19" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tebing">
                    	（20)您的皮肤一抓就红，并出现抓痕吗？（指被指甲或钝物划过后皮肤的反应）（特禀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field20" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field20" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field20" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field20" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field20" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yinxu">
                    	（21)您皮肤或口唇干吗？（阴虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field21" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field21" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field21" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field21" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field21" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="xueyu">
                    	（22)您有肢体麻木或固定部位疼痛的感觉吗？（血瘀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field22" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field22" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field22" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field22" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field22" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="shire">
                    	（23)您面部或鼻部有油腻或者油亮发光吗？（指脸上或鼻子）（湿热质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field23" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field23" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field23" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field23" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field23" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="xueyu">
                    	（24)您面色或目眶晦暗，或出现褐色斑块/斑点吗？（血瘀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field24" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field24" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field24" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field24" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field24" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="shire">
                    	（25)您有皮肤湿疹，疮疖吗？（湿热质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field25" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field25" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field25" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field25" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field25" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yinxu">
                    	（26)您感到口干咽燥，总想喝水吗？（阴虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field26" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field26" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field26" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field26" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field26" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="shire">
                    	（27)您感到口苦或嘴里有异味吗？（指口苦或口臭）（湿热质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field27" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field27" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field27" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field27" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field27" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tanshi">
                    	（28)您腹部肥大吗？（指腹部脂肪肥厚）（痰湿质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field28" checked style="float: left;margin-top: -5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left; display: block;"><80cm</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field28" style="float: left;margin-top: -5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left; display: block;">80-85cm</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="3" name="field28" style="float: left;margin-top: -5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left; display: block;">86-90cm</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field28" style="float: left;margin-top: -5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left; display: block;">91-105cm</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field28" style="float: left;margin-top: -5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left; display: block;">>105cm</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yangxu">
                    	（29)您吃（喝）凉的东西会感到不舒服或者怕吃（喝）凉的东西吗？（阳虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field29" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field29" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field29" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field29" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field29" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="shire">
                    	（30)您有大便粘滞不爽，解不尽的感觉吗？（大便容易粘在马桶或便坑壁上）（湿热质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field30" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field30" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field30" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field30" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field30" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="yinxu">
                    	（31)您容易大便干燥吗？（阴虚质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field31" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field31" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field31" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field31" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field31" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="tanshi">
                    	（32)您舌苔厚腻或有舌苔厚厚的感觉吗？（如果自我感觉不清楚可由调查员观察后填写）（痰湿质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field32" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field32" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field32" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field32" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field32" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr height="30px">
                    <td id="xueyu">
                    	（33)您舌下静脉淤紫或增粗吗？（可由调查员辅助观察后填写）（血瘀质）
                    </td>
                    <td style="width:50%">
                        <table width="100%" border="0">
                            <tr>
                                <td style="border:0px;">
                                    <input type="radio" value="1" name="field33" checked style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">没有</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="2" name="field33" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">很少</label>
                                </td>
                               <td style="border:0px;">
                                    <input type="radio" value="3" name="field33" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">有时</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="4" name="field33" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">经常</label>
                                </td>
                                <td style="border:0px;">
                                    <input type="radio" value="5" name="field33" style="float: left;margin-top: -1.5px;display: block;width: 15px;">
                                    <label class="radio-inline" style="float: left;display: block;">总是</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="5">
                        <table width="100%">
                            <tr>
                                <td align="center">气虚质</td>
                                <td align="center">阳虚质</td>
                                <td align="center">阴虚质</td>
                                <td align="center">痰湿质</td>
                                <td align="center">湿热质</td>
                                <td align="center">血瘀质</td>
                                <td align="center">气郁质</td>
                                <td align="center">特禀质</td>
                                <td align="center">平和质</td>   
                            </tr>
                            <tr>
                                <td align="center"><input type="text" readonly="readonly" id="score1"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score2"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score3"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score4"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score5"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score6"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score7"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score8"></td>
                                <td align="center"><input type="text" readonly="readonly" id="score9"></td>
                            </tr>
                            <tr>
                                <td align="center">
                                	<input id="itemField34" type="hidden">
                                    <input name="field34" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field34" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField35" type="hidden">
                                    <input name="field35" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field35" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                    <input id="itemField36" type="hidden">
                                    <input name="field36" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field36" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField37" type="hidden">
                                    <input name="field37" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field37" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField38" type="hidden">
                                    <input name="field38" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field38" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField39" type="hidden">
                                    <input name="field39" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field39" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField40" type="hidden">
                                    <input name="field40" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field40" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField41" type="hidden">
                                    <input name="field41" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field41" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                                <td align="center">
                                	<input id="itemField42" type="hidden">
                                    <input name="field42" type="checkbox" value="1" style="width:15px;height:15px;">是
                                    <input name="field42" type="checkbox" value="2" style=" margin-left:30px;width:15px;height:15px;">倾向是
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <input name="field34" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field34" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field34" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field34" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field34" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField43">
                                </td>
                                <td align="center">
                                    <input name="field35" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field35" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field35" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field35" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field35" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField44">
                                </td>
                                <td align="center">
                                    <input name="field36" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field36" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field36" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field36" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field36" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField45">
                                </td>
                                <td align="center">
                                    <input name="field37" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field37" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field37" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field37" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field37" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField46">
                                </td>
                                <td align="center">
                                    <input name="field38" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field38" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field38" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field38" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field38" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField47">
                                </td>
                                <td align="center">
                                    <input name="field39" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field39" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field39" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field39" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field39" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField48">
                                </td>
                                <td align="center">
                                    <input name="field40" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field40" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field40" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field40" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field40" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField49">
                                </td>
                                <td align="center">
                                    <input name="field41" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field41" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field41" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field41" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field41" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField50">
                                </td>
                                <td align="center">
                                    <input name="field42" type="checkbox" value="3" style="width:15px;height:15px;">情志调摄<br>
                                    <input name="field42" type="checkbox" value="4" style="width:15px;height:15px;">饮食调养<br>
                                    <input name="field42" type="checkbox" value="5" style="width:15px;height:15px;">起居调摄<br>
                                    <input name="field42" type="checkbox" value="6" style="width:15px;height:15px;">运动保健<br>
                                    <input name="field42" type="checkbox" value="7" style="width:15px;height:15px;">穴位保健<br>
                                    <label>其他</label>&nbsp;<input type="text" style="width:40%" ng-model="itemField51">
                                </td>
                            </tr>
                        </table>
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
			//反向算值
			function reTMC(v){
				if(v==1){
					v=5;
				}else if(v==2){
					v=4;
				}else if(v==4){
					v=2;
				}else if(v==5){
					v=1;
				}else{
					v=3;
				}
				return v;
			}
			
			//获取是否该体质
			function getConstitution(score){
				//其它体质
				//各条目得分相加大于等于11分 是
				//各条目得分相加9-10分 倾向是
				//各条目得分相加小于等于8分 否
				let constitution = 0;//0否1是2倾向是
				if(score>=11){
					constitution = 1;
				}else if(score==9||score==10){
					constitution = 2;
				}
				return constitution;
			}
			
			//改变选择框
			function changeTMC(score,field,total,constitution){
				$("#"+score).val(total);
				//1是2倾向是
				if(constitution==1){
					$($("input[name='"+field+"']")[0]).attr("checked",true);
				}else if(constitution==2){
					$($("input[name='"+field+"']")[1]).attr("checked",true);
				}
				//判断是什么体制
				//情志调摄1 2
	            //饮食调养2 3
	            //起居调摄3 4
	            //运动保健4 5
	            //穴位保健5 6
	            if(constitution!=0){
	            	if("score1"==score){//气虚质2 4 5
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[5]).attr("checked",true);
						$($("input[name='"+field+"']")[6]).attr("checked",true);
					}else if("score2"==score){//阳虚质2 4
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[5]).attr("checked",true);
					}else if("score3"==score){//阴虚质2 5
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[6]).attr("checked",true);
					}else if("score4"==score){//痰湿质2 4
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[5]).attr("checked",true);
					}else if("score5"==score){//湿热质2 3
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[4]).attr("checked",true);
					}else if("score6"==score){//血瘀质2 4
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[5]).attr("checked",true);
					}else if("score7"==score){//气郁质1 3 4
						$($("input[name='"+field+"']")[2]).attr("checked",true);
						$($("input[name='"+field+"']")[4]).attr("checked",true);
						$($("input[name='"+field+"']")[5]).attr("checked",true);
					}else if("score8"==score){//特禀质3 5
						$($("input[name='"+field+"']")[4]).attr("checked",true);
						$($("input[name='"+field+"']")[6]).attr("checked",true);
					}else if("score9"==score){//平和质2 3 4 5
						$($("input[name='"+field+"']")[3]).attr("checked",true);
						$($("input[name='"+field+"']")[4]).attr("checked",true);
						$($("input[name='"+field+"']")[5]).attr("checked",true);
						$($("input[name='"+field+"']")[6]).attr("checked",true);
					}
	            }
			}
			
			//计算体质
			function calTMC(){
				$("#score1").val("");
				$("#score2").val("");
				$("#score3").val("");
				$("#score4").val("");
				$("#score5").val("");
				$("#score6").val("");
				$("#score7").val("");
				$("#score8").val("");
				$("#score9").val("");
				$("input[type='checkbox']").removeAttr("checked");
				
				//气虚质（2)（3)（4)（14)
				let field2 = getRedio("field2");
				let field3 = getRedio("field3");
				let field4 = getRedio("field4");
				let field14 = getRedio("field14");
				let total1 = Number(field2) + Number(field3) + Number(field4) + Number(field14);
				
				//阳虚质（11)（12)（13)（29)
				let field11 = getRedio("field11");
				let field12 = getRedio("field12");
				let field13 = getRedio("field13");
				let field29 = getRedio("field29");
				let total2 = Number(field11) + Number(field12) + Number(field13) + Number(field29);
				
				//阴虚质（10)（21)（26)（31)
				let field10 = getRedio("field10");
				let field21 = getRedio("field21");
				let field26 = getRedio("field26");
				let field31 = getRedio("field31");
				let total3 = Number(field10) + Number(field21) + Number(field26) + Number(field31);
				
				//痰湿质（9)（16)（28)（32)
				let field9 = getRedio("field9");
				let field16 = getRedio("field16");
				let field28 = getRedio("field28");
				let field32 = getRedio("field32");
				let total4 = Number(field9) + Number(field16) + Number(field28) + Number(field32);
				
				//湿热质（23)（25)（27)（30)
				let field23 = getRedio("field23");
				let field25 = getRedio("field25");
				let field27 = getRedio("field27");
				let field30 = getRedio("field30");
				let total5 = Number(field23) + Number(field25) + Number(field27) + Number(field30);
				
				//血瘀质（19)（22)（24)（33)
				let field19 = getRedio("field19");
				let field22 = getRedio("field22");
				let field24 = getRedio("field24");
				let field33 = getRedio("field33");
				let total6 = Number(field19) + Number(field22) + Number(field24) + Number(field33);
				
				//气郁质（5)（6)（7)（8)
				let field5 = getRedio("field5");
				let field6 = getRedio("field6");
				let field7 = getRedio("field7");
				let field8 = getRedio("field8");
				let total7 = Number(field5) + Number(field6) + Number(field7) + Number(field8);
				
				//特禀质（15)（17)（18)（20)
				let field15 = getRedio("field15");
				let field17 = getRedio("field17");
				let field18 = getRedio("field18");
				let field20 = getRedio("field20");
				let total8 = Number(field15) + Number(field17) + Number(field18) + Number(field20);
				
				//平和质（1)//反向计分(2)(4)(5)(13)
				let field1 = getRedio("field1");
				let refield2 = reTMC(getRedio("field2"));
				let refield4 = reTMC(getRedio("field4"));
				let refield5 = reTMC(getRedio("field5"));
				let refield13 = reTMC(getRedio("field13"));
				let total9 = Number(field1) + Number(refield2) + Number(refield4) + Number(refield5) + Number(refield13);
				
				//是否平和质
				//各条目得分相加大于等于17分，同时其他8种体质得分都小于等于8分  是
				//各条目得分相加大于等于17分，同时其他8种体质得分都小于等于10分 倾向是
				//不满足上述条件者 否
				let constitution = 0;//0否1是2倾向是
				if(total9>=17){
					if(total1<=8&&total2<=8&&total3<8&&total4<8&&total5<8&&total6<8&&total7<8&&total8<8){
						constitution = 1;
					}else if(total1<10&&total2<10&&total3<10&&total4<10&&total5<10&&total6<10&&total7<10&&total8<10){
						constitution = 2;
					}
				}
				changeTMC('score9','field42',total9,constitution);
				constitution = 0;
				//气虚质
				constitution = getConstitution(total1);
				changeTMC('score1','field34',total1,constitution);
				constitution = 0;
				//阳虚质
				constitution = getConstitution(total2);
				changeTMC('score2','field35',total2,constitution);
				constitution = 0;
				//阴虚质
				constitution = getConstitution(total3);
				changeTMC('score3','field36',total3,constitution);
				constitution = 0;
				//痰湿质
				constitution = getConstitution(total4);
				changeTMC('score4','field37',total4,constitution);
				constitution = 0;
				//湿热质
				constitution = getConstitution(total5);
				changeTMC('score5','field38',total5,constitution);
				constitution = 0;
				//血瘀质
				constitution = getConstitution(total6);
				changeTMC('score6','field39',total6,constitution);
				constitution = 0;
				//气郁质
				constitution = getConstitution(total7);
				changeTMC('score7','field40',total7,constitution);
				constitution = 0;
				//特禀质
				constitution = getConstitution(total8);
				changeTMC('score8','field41',total8,constitution);
			}
			
			//初始化List信息
			function initListData($http,$scope){
				let pid = $("#pid").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>healthCheck/getTCM.do",
				    params: {pid:pid}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        let pObj = response.data.datas;
					// 随访医生 
					$scope.itemField52 = changeNullValue(pObj.field52);
					// 气虚质其他 
					$scope.itemField43 = changeNullValue(pObj.field43);
					// 阳虚质其他 
					$scope.itemField44 = changeNullValue(pObj.field44);
					// 阴虚质其他 
					$scope.itemField45 = changeNullValue(pObj.field45);
					// 痰湿质其他 
					$scope.itemField46 = changeNullValue(pObj.field46);
					// 湿热质其他 
					$scope.itemField47 = changeNullValue(pObj.field47);
					// 血瘀质其他 
					$scope.itemField48 = changeNullValue(pObj.field48);
					// 气郁质其他 
					$scope.itemField49 = changeNullValue(pObj.field49);
					// 特禀质其他 
					$scope.itemField50 = changeNullValue(pObj.field50);
					// 平和质其他 
					$scope.itemField51 = changeNullValue(pObj.field51);
					// 气虚质得分
					$("#score1").val(changeNullValue(pObj.field53));
					// 阳虚质得分
					$("#score2").val(changeNullValue(pObj.field54));
					// 阴虚质得分
					$("#score3").val(changeNullValue(pObj.field55));
					// 痰湿质得分
					$("#score4").val(changeNullValue(pObj.field56));
					// 湿热质得分
					$("#score5").val(changeNullValue(pObj.field57));
					// 血瘀质得分
					$("#score6").val(changeNullValue(pObj.field58));
					// 气郁质得分
					$("#score7").val(changeNullValue(pObj.field59));
					// 特禀质得分
					$("#score8").val(changeNullValue(pObj.field60));
					// 平和质得分
					$("#score9").val(changeNullValue(pObj.field61));
					
					$(function(){
						//###复选框
						// 气虚质 
						$scope.itemField34 = pObj.field34;
						initCheckBox("field34",pObj.field34);
						// 阳虚质 
						$scope.itemField35 = pObj.field35;
						initCheckBox("field35",pObj.field35);
						// 阴虚质 
						$scope.itemField36 = pObj.field36;
						initCheckBox("field36",pObj.field36);
						// 痰湿质 
						$scope.itemField37 = pObj.field37;
						initCheckBox("field37",pObj.field37);
						// 湿热质 
						$scope.itemField38 = pObj.field38;
						initCheckBox("field38",pObj.field38);
						// 血瘀质 
						$scope.itemField39 = pObj.field39;
						initCheckBox("field39",pObj.field39);
						// 气郁质 
						$scope.itemField40 = pObj.field40;
						initCheckBox("field40",pObj.field40);
						// 特禀质 
						$scope.itemField41 = pObj.field41;
						initCheckBox("field41",pObj.field41);
						// 平和质 
						$scope.itemField42 = pObj.field42;
						initCheckBox("field42",pObj.field42);
						
						//###单选框
						// （1）您精力充沛吗？（指精神头足，乐于做事）
						initRedio("field1",pObj.field1);
						// （2）您容易疲乏吗？（指体力如何，是否稍微活动一下或做一点家务劳动就感到累）
						initRedio("field2",pObj.field2);
						// （3）您容易气短，呼吸短促，接不上气吗？
						initRedio("field3",pObj.field3);
						// （4）您说话声音低弱无力吗？（指说话没有力气）
						initRedio("field4",pObj.field4);
						// （5）您感到闷闷不乐、情绪低沉吗？（指心情不愉快，情绪低落）
						initRedio("field5",pObj.field5);
						// （6）您容易精神紧张、焦虑不安吗？（指遇事是否心情紧张）
						initRedio("field6",pObj.field6);
						// （7）您因为生活状态改变而感到孤独、失落吗？
						initRedio("field7",pObj.field7);
						// （8）您容易感到害怕或受到惊吓吗？
						initRedio("field8",pObj.field8);
						
						// （9）您感到身体超重不轻松吗？（感觉身体沉重）
						//病人BMI值
		 				let pBMI = $("#pBMI").val();
		 				if(pBMI!=null&&pBMI!=""){
		 					pBMI = Number(pBMI);
		 					if(pBMI<24){
		 						$($("input[name='field9']")[0]).attr("checked",true);
		 					}else if(pBMI>=24&&pBMI<25){
		 						$($("input[name='field9']")[1]).attr("checked",true);
		 					}else if(pBMI>=25&&pBMI<26){
		 						$($("input[name='field9']")[2]).attr("checked",true);
		 					}else if(pBMI>=26&&pBMI<28){
		 						$($("input[name='field9']")[3]).attr("checked",true);
		 					}else{
		 						$($("input[name='field9']")[4]).attr("checked",true);
		 					}
		 				}
		 				
						// （10）您眼睛干涩吗？
						initRedio("field10",pObj.field10);
						// （11）您手脚发凉吗？（不包含因周围温度低或穿的少导致的手脚发冷）
						initRedio("field11",pObj.field11);
						// （12）您胃脘部、背部或腰膝部怕冷吗？（指上腹部、背部、腰部或膝关节等，有一处或多处怕冷）
						initRedio("field12",pObj.field12);
						// （13）您比一般人耐受不了寒冷吗？（指比别人容易害怕冬天或是夏天的冷空调、电扇等）
						initRedio("field13",pObj.field13);
						// （14）您容易感冒吗？（指每年感冒的次数）
						initRedio("field14",pObj.field14);
						// （15）您没有感冒时也会鼻塞、流鼻涕吗？
						initRedio("field15",pObj.field15);
						// （16）您有口粘口腻，或睡眠打鼾吗？
						initRedio("field16",pObj.field16);
						// （17）您容易过敏（对药物、食物、气味、花粉或在季节交替、气候变化时）吗？
						initRedio("field17",pObj.field17);
						// （18）您的皮肤容易起荨麻疹吗？（包括风团、风疹块、风疙瘩）
						initRedio("field18",pObj.field18);
						// （19）您的皮肤在不知不觉中会出现青紫瘀块、皮下出血吗？（指皮肤在没有外伤的情况下出现青一块紫一块的情况）
						initRedio("field19",pObj.field19);
						// （20）您的皮肤一抓就红，并出现抓痕吗？（指被指甲或钝物划过后皮肤的反应）
						initRedio("field20",pObj.field20);
						// （21）您皮肤或口唇干吗？
						initRedio("field21",pObj.field21);
						// （22）您有肢体麻木或固定部位疼痛的感觉吗？
						initRedio("field22",pObj.field22);
						// （23）您面部或鼻部有油腻感或者油亮发光吗？（指脸上或鼻子）
						initRedio("field23",pObj.field23);
						// （24）您面色或目眶晦暗，或出现褐色斑块/斑点吗？
						initRedio("field24",pObj.field24);
						// （25）您有皮肤湿疹、疮疖吗？
						initRedio("field25",pObj.field25);
						// （26）您感到口干咽燥、总想喝水吗？
						initRedio("field26",pObj.field26);
						// （27）您感到口苦或嘴里有异味吗？(指口苦或口臭)
						initRedio("field27",pObj.field27);
						
						// （28）您腹部肥大吗？（指腹部脂肪肥厚）
						//腰围
		 				let waistline = $("#waistline").val();
		 				if(waistline!=null&&waistline!=""){
		 					waistline = Number(waistline);
		 					if(waistline<80){
		 						$($("input[name='field28']")[0]).attr("checked",true);
		 					}else if(waistline>=80&&waistline<=85){
		 						$($("input[name='field28']")[1]).attr("checked",true);
		 					}else if(waistline>=86&&waistline<=90){
		 						$($("input[name='field28']")[2]).attr("checked",true);
		 					}else if(waistline>=91&&waistline<=105){
		 						$($("input[name='field28']")[3]).attr("checked",true);
		 					}else{
		 						$($("input[name='field28']")[4]).attr("checked",true);
		 					}
		 				}
						
						// （29）您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗？(指不喜欢吃凉的食物，或吃了凉的食物后会不舒服) 
						initRedio("field29",pObj.field29);
						// （30）您有大便粘滞不爽、解不尽的感觉吗？（大便容易粘在马桶或便坑壁上）
						initRedio("field30",pObj.field30);
						// （31）您容易大便干燥吗？
						initRedio("field31",pObj.field31);
						// （32）您舌苔厚腻或有舌苔厚厚的感觉吗？（如果自我感觉不清楚可由调查员观察后填写）
						initRedio("field32",pObj.field32);
						// （33）您舌下静脉瘀紫或增粗吗？(可以调查员辅助观察后填写)
						initRedio("field33",pObj.field33);
					});
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("healthCheck->getTCM.do访问错误出错!");
			        console.log(response.statusText);
			    });
			}
		
			var app = angular.module('myApp', []);
			app.controller('myCtrl', function($scope,$http) {
				//初始化信息
			    initListData($http,$scope);
			    //保存
			    $scope.save = function() {
			    	//计算中医体质
			    	calTMC();
			    	$scope.loadingShow = true;
			    	//病人id信息
			    	let pid = $("#pid").val();
					// 随访医生 
					let field52 = encodeURI($scope.itemField52);
					// 气虚质其他 
					let field43 = encodeURI($scope.itemField43);
					// 阳虚质其他 
					let field44 = encodeURI($scope.itemField44);
					// 阴虚质其他 
					let field45 = encodeURI($scope.itemField45);
					// 痰湿质其他 
					let field46 = encodeURI($scope.itemField46);
					// 湿热质其他 
					let field47 = encodeURI($scope.itemField47);
					// 血瘀质其他 
					let field48 = encodeURI($scope.itemField48);
					// 气郁质其他 
					let field49 = encodeURI($scope.itemField49);
					// 特禀质其他 
					let field50 = encodeURI($scope.itemField50);
					// 平和质其他 
					let field51 = encodeURI($scope.itemField51);
					// 气虚质得分
					let field53 = $("#score1").val();
					// 阳虚质得分
					let field54 = $("#score2").val();
					// 阴虚质得分
					let field55 = $("#score3").val();
					// 痰湿质得分
					let field56 = $("#score4").val();
					// 湿热质得分
					let field57 = $("#score5").val();
					// 血瘀质得分
					let field58 = $("#score6").val();
					// 气郁质得分
					let field59 = $("#score7").val();
					// 特禀质得分
					let field60 = $("#score8").val();
					// 平和质得分
					let field61 = $("#score9").val();

					//###复选框
					// 气虚质 
					let field34 = getCheckBox('field34');
					$scope.itemField34 = field34;
					// 阳虚质 
					let field35 = getCheckBox('field35');
					$scope.itemField35 = field35;
					// 阴虚质 
					let field36 = getCheckBox('field36');
					$scope.itemField36 = field36;
					// 痰湿质 
					let field37 = getCheckBox('field37');
					$scope.itemField37 = field37;
					// 湿热质 
					let field38 = getCheckBox('field38');
					$scope.itemField38 = field38;
					// 血瘀质 
					let field39 = getCheckBox('field39');
					$scope.itemField39 = field39;
					// 气郁质
					let field40 = getCheckBox('field40');
					$scope.itemField40 = field40;
					// 特禀质 
					let field41 = getCheckBox('field41');
					$scope.itemField41 = field41;
					// 平和质 
					let field42 = getCheckBox('field42');
					$scope.itemField42 = field42;

					//###单选框
					// （1）您精力充沛吗？（指精神头足，乐于做事）
					let field1 = getRedio("field1");
					// （2）您容易疲乏吗？（指体力如何，是否稍微活动一下或做一点家务劳动就感到累）
					let field2 = getRedio("field2");
					// （3）您容易气短，呼吸短促，接不上气吗？
					let field3 = getRedio("field3");
					// （4）您说话声音低弱无力吗？（指说话没有力气）
					let field4 = getRedio("field4");
					// （5）您感到闷闷不乐、情绪低沉吗？（指心情不愉快，情绪低落）
					let field5 = getRedio("field5");
					// （6）您容易精神紧张、焦虑不安吗？（指遇事是否心情紧张）
					let field6 = getRedio("field6");
					// （7）您因为生活状态改变而感到孤独、失落吗？
					let field7 = getRedio("field7");
					// （8）您容易感到害怕或受到惊吓吗？
					let field8 = getRedio("field8");
					// （9）您感到身体超重不轻松吗？（感觉身体沉重）
					let field9 = getRedio("field9");
					// （10）您眼睛干涩吗？
					let field10 = getRedio("field10");
					// （11）您手脚发凉吗？（不包含因周围温度低或穿的少导致的手脚发冷）
					let field11 = getRedio("field11");
					// （12）您胃脘部、背部或腰膝部怕冷吗？（指上腹部、背部、腰部或膝关节等，有一处或多处怕冷）
					let field12 = getRedio("field12");
					// （13）您比一般人耐受不了寒冷吗？（指比别人容易害怕冬天或是夏天的冷空调、电扇等）
					let field13 = getRedio("field13");
					// （14）您容易感冒吗？（指每年感冒的次数）
					let field14 = getRedio("field14");
					// （15）您没有感冒时也会鼻塞、流鼻涕吗？
					let field15 = getRedio("field15");
					// （16）您有口粘口腻，或睡眠打鼾吗？
					let field16 = getRedio("field16");
					// （17）您容易过敏（对药物、食物、气味、花粉或在季节交替、气候变化时）吗？
					let field17 = getRedio("field17");
					// （18）您的皮肤容易起荨麻疹吗？（包括风团、风疹块、风疙瘩）
					let field18 = getRedio("field18");
					// （19）您的皮肤在不知不觉中会出现青紫瘀块、皮下出血吗？（指皮肤在没有外伤的情况下出现青一块紫一块的情况）
					let field19 = getRedio("field19");
					// （20）您的皮肤一抓就红，并出现抓痕吗？（指被指甲或钝物划过后皮肤的反应）
					let field20 = getRedio("field20");
					// （21）您皮肤或口唇干吗？
					let field21 = getRedio("field21");
					// （22）您有肢体麻木或固定部位疼痛的感觉吗？
					let field22 = getRedio("field22");
					// （23）您面部或鼻部有油腻感或者油亮发光吗？（指脸上或鼻子）
					let field23 = getRedio("field23");
					// （24）您面色或目眶晦暗，或出现褐色斑块/斑点吗？
					let field24 = getRedio("field24");
					// （25）您有皮肤湿疹、疮疖吗？
					let field25 = getRedio("field25");
					// （26）您感到口干咽燥、总想喝水吗？
					let field26 = getRedio("field26");
					// （27）您感到口苦或嘴里有异味吗？(指口苦或口臭)
					let field27 = getRedio("field27");
					// （28）您腹部肥大吗？（指腹部脂肪肥厚）
					let field28 = getRedio("field28");
					// （29）您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗？(指不喜欢吃凉的食物，或吃了凉的食物后会不舒服) 
					let field29 = getRedio("field29");
					// （30）您有大便粘滞不爽、解不尽的感觉吗？（大便容易粘在马桶或便坑壁上）
					let field30 = getRedio("field30");
					// （31）您容易大便干燥吗？
					let field31 = getRedio("field31");
					// （32）您舌苔厚腻或有舌苔厚厚的感觉吗？（如果自我感觉不清楚可由调查员观察后填写）
					let field32 = getRedio("field32");
					// （33）您舌下静脉瘀紫或增粗吗？(可以调查员辅助观察后填写)
					let field33 = getRedio("field33");
					
					//拼装参数
					let tCMDto = {pid:pid,field52:field52,field43:field43,field44:field44,
							field45:field45,field46:field46,field47:field47,field48:field48,
							field49:field49,field50:field50,field51:field51,field34:field34,
							field35:field35,field36:field36,field37:field37,field38:field38,
							field39:field39,field40:field40,field41:field41,field42:field42,
							field1:field1,field2:field2,field3:field3,field4:field4,
							field5:field5,field6:field6,field7:field7,field8:field8,
							field9:field9,field10:field10,field11:field11,field12:field12,
							field13:field13,field14:field14,field15:field15,field16:field16,
							field17:field17,field18:field18,field19:field19,field20:field20,
							field21:field21,field22:field22,field23:field23,field24:field24,
							field25:field25,field26:field26,field27:field27,field28:field28,
							field29:field29,field30:field30,field31:field31,field32:field32,
							field33:field33,field53:field53,field54:field54,field55:field55,
							field56:field56,field57:field57,field58:field58,field59:field59,
							field60:field60,field61:field61
					};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>healthCheck/updTCM.do",
				        params: tCMDto
				    }).then(function mySucces(response) {
				    	$scope.loadingShow = false;
				    	if(response.data.code==0){
				        	alert('保存成功!');
				        }else{
				        	alert('保存失败!');
				        }
				    }, function myError(response) {
				    	$scope.loadingShow = false;
				        alert("healthCheck->updTCM.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
