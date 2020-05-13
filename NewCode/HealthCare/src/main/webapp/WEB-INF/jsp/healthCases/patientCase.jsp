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
		
		.mybnt{
			position:fixed;
			top:3px;
			right:20px;
		}
	</style>
	</head>
	<body ng-app="myApp" ng-controller="myCtrl">
		<input id="uid" type="hidden" value="${uid}"/>
		<div class="mytitle">
			<h3>
            	个人档案
            </h3>
            <button class="btn btn-success mybnt" ng-click="save()">
				保存
			</button>
        </div>
		<table width="100%" height="100%" border="0">
			<tr height="30px">
				<td width="15%" align="center">
					姓名
				</td>
				<td width="18%" align="center">
					<input type="text" ng-model="itemName"/>
				</td>
				<td width="15%" align="center">
					性别
				</td>
				<td width="18%" align="center">
					<select id="itemSex">
						<option value="1">男</option>
						<option value="2">女</option>
						<option value="3">未说明的性别</option>
						<option value="4">未知的性别</option>
					</select>
				</td>
				<td width="15%" align="center">
					档案编号
				</td>
				<td width="19%" align="center">
					<input type="text" ng-model="itemCode" readonly="readonly"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					身份证号
				</td>
				<td align="center">
					<input type="text" ng-model="itemIdcard"/>
				</td>
				<td align="center">
					出生日期
				</td>
				<td align="center">
					<input type="text" ng-model="itemBirthdate" id="itemBirthdate"/>
				</td>
				<td align="center">
					民族
				</td>
				<td align="center">
					<input type="text" ng-model="itemNationality"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					本人电话
				</td>
				<td align="center">
					<input type="text" ng-model="itemPhoneno"/>
				</td>
				<td align="center">
					联系人姓名
				</td>
				<td align="center">
					<input type="text" ng-model="itemCname"/>
				</td>
				<td align="center">
					联系人电话
				</td>
				<td align="center">
					<input type="text" ng-model="itemCphoneno"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					常驻类型
				</td>
				<td align="center">
					<select id="itemRtype">
						<option value="1">户籍</option>
						<option value="2">非户籍</option>
					</select>
				</td>
				<td align="center">
					工作单位
				</td>
				<td align="center" colspan="3">
					<input type="text" ng-model="itemCompany"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					血型
				</td>
				<td align="center">
					<select id="itemBlood">
						<option value="1">A型</option>
						<option value="2">B型</option>
						<option value="3">O型</option>
						<option value="4">AB型</option>
						<option value="5">不详</option>
					</select>
				</td>
				<td align="center">
					职业
				</td>
				<td align="center" colspan="3">
					<select id="itemCareer">
						<option value="1">国家机关、党群组织、企业、事业单位负责人</option>
						<option value="2">专业技术人员</option>
						<option value="3">办事人员和有关人员</option>
						<option value="4">商业、服务业人员</option>
						<option value="5">农、林、牧、渔、水利业生产人员</option>
						<option value="6">生产、运输设备操作人员及有关人员</option>
						<option value="7">军人</option>
						<option value="8">不便分类的其他从业人员</option>
						<option value="9">无职业</option>
					</select>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					RH阴性
				</td>
				<td align="center">
					<select id="itemRH">
						<option value="1">阴性</option>
						<option value="2">阳性</option>
						<option value="3">不详</option>
					</select>
				</td>
				<td align="center">
					文化程度
				</td>
				<td align="center">
					<select id="itemElevel">
						<option value="1">研究生</option>
						<option value="2">大学本科</option>
						<option value="3">大学专科和专科学校</option>
						<option value="4">中等专业学校</option>
						<option value="5">技工学校</option>
						<option value="6">高中</option>
						<option value="7">初中</option>
						<option value="8">小学</option>
						<option value="9">文盲或半文盲</option>
						<option value="10">不详</option>
					</select>
				</td>
				<td align="center">
					婚姻状况
				</td>
				<td align="center">
					<select id="itemMarital">
						<option value="1">未婚</option>
						<option value="2">已婚</option>
						<option value="3">丧偶</option>
						<option value="4">离婚</option>
						<option value="5">未说明的婚姻状况</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">
					医疗费用支付方式<input id="itemPaymentway" type="hidden">
				</td>
				<td align="center" colspan="5">
					<table width="100%" border="0">
						<tr>
							<td style="border:0px;">
								<input id="c1" name="paymentway" type="checkbox" value="1" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								城镇职工基本医疗保险
							</td>
							<td style="border:0px;">
								医保卡号
							</td>
							<td style="border:0px;" colspan="3">
								<input id="cx1" type="text" ng-model="itemField1" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input id="c2" name="paymentway" type="checkbox" value="2" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								城镇居民基本医疗保险
							</td>
							<td style="border:0px;">
								医保卡号
							</td>
							<td style="border:0px;" colspan="3">
								<input id="cx2" type="text" ng-model="itemField2" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input id="c3" name="paymentway" type="checkbox" value="3" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								新型农村合作医疗
							</td>
							<td style="border:0px;">
								卡号
							</td>
							<td style="border:0px;" colspan="3">
								<input id="cx3" type="text" ng-model="itemField3" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input name="paymentway" type="checkbox" value="4" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								贫困救助
							</td>
							<td style="border:0px;">
								<input name="paymentway" type="checkbox" value="5" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								商业医疗保险
							</td>
							<td style="border:0px;">
								<input name="paymentway" type="checkbox" value="6" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								全公费
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input name="paymentway" type="checkbox" value="7" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								全自费
							</td>
							<td style="border:0px;">
								<input name="paymentway" type="checkbox" value="8" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;" colspan="3" algin="left">
								其他&nbsp;<input type="text" ng-model="itemField4" style="width:80%;"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					药物过敏史<input id="itemAllergy" type="hidden">
				</td>
				<td align="center" colspan="2">
					<table width="100%" border="0">
						<tr>
							<td style="border:0px;">
								<input name="allergy" type="checkbox" value="1" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								无
							</td>
							<td style="border:0px;">
								<input name="allergy" type="checkbox" value="2" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								青霉素
							</td>
							<td style="border:0px;">
								<input name="allergy" type="checkbox" value="3" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								磺胺
							</td>
							<td style="border:0px;">
								<input name="allergy" type="checkbox" value="4" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								链霉素
							</td>
							<td style="border:0px;">
								<input name="allergy" type="checkbox" value="5" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								其他
							</td>
						</tr>
					</table>
				</td>
				<td align="center">
					暴露史<input id="itemExposure" type="hidden">
				</td>
				<td align="center" colspan="2">
					<table width="100%" border="0">
						<tr>
							<td style="border:0px;">
								<input name="exposure" type="checkbox" value="1" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								无
							</td>
							<td style="border:0px;">
								<input name="exposure" type="checkbox" value="2" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								化学品
							</td>
							<td style="border:0px;">
								<input name="exposure" type="checkbox" value="3" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								毒物
							</td>
							<td style="border:0px;">
								<input name="exposure" type="checkbox" value="4" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								射线
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					疾病<input id="itemDisease" type="hidden">
				</td>
				<td align="center" colspan="5">
					<table width="100%" border="0">
						<tr>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="1" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								无
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="2" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								高血压
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="3" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								糖尿病
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="4" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								冠心病
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="5" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								慢性阻塞性肺疾病
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="6" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								恶性肿瘤
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="7" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								脑卒中
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="8" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								严重精神障碍
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="9" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								结核病
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="10" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								肝炎
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="11" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								其他法定传染病
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="12" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								职业病
							</td>
							<td style="border:0px;">
								<input name="disease" type="checkbox" value="13" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;" colspan="5">
								其他&nbsp;<input type="text" ng-model="itemField10" style="width:80%;"/>
							</td>
						</tr>
						<tr>
							<td style="border:0px;" colspan="10">
								<table width="100%" height="100%">
									<tr>
										<td>
											确诊时间1
										</td>
										<td>
											<input id="ctime1" ng-model="itemCtime1" style="width:124px;"/>
										</td>
										<td>
											确诊时间2
										</td>
										<td>
											<input id="ctime2" ng-model="itemCtime2" style="width:124px;"/>
										</td>
										<td>
											确诊时间3
										</td>
										<td>
											<input id="ctime3" ng-model="itemCtime3" style="width:124px;"/>
										</td>
										<td>
											确诊时间4
										</td>
										<td>
											<input id="ctime4" ng-model="itemCtime4" style="width:124px;"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" rowspan="2">
					手术
				</td>
				<td align="center" rowspan="2">
					<select id="itemSurgery">
						<option value="1">无</option>
						<option value="2">有</option>
					</select>
				</td>
				<td align="center">
					手术名称
				</td>
				<td align="center">
					<input type="text" ng-model="itemSname1"/>
				</td>
				<td align="center">
					手术时间
				</td>
				<td align="center">
					<input type="text" ng-model="itemStime1" id="stime1"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					手术名称
				</td>
				<td align="center">
					<input type="text" ng-model="itemSname2"/>
				</td>
				<td align="center">
					手术时间
				</td>
				<td align="center">
					<input type="text" ng-model="itemStime2" id="stime2"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" rowspan="2">
					外伤
				</td>
				<td align="center" rowspan="2">
					<select id="itemTrauma">
						<option value="1">无</option>
						<option value="2">有</option>
					</select>
				</td>
				<td align="center">
					外伤名称
				</td>
				<td align="center">
					<input type="text" ng-model="itemTname1"/>
				</td>
				<td align="center">
					外伤时间
				</td>
				<td align="center">
					<input type="text" ng-model="itemTtime1" id="ttime1"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					外伤名称
				</td>
				<td align="center">
					<input type="text" ng-model="itemTname2"/>
				</td>
				<td align="center">
					外伤时间
				</td>
				<td align="center">
					<input type="text" ng-model="itemTtime2" id="ttime2"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" rowspan="2">
					输血
				</td>
				<td align="center" rowspan="2">
					<select id="itemBtran">
						<option value="1">无</option>
						<option value="2">有</option>
					</select>
				</td>
				<td align="center">
					输血原因
				</td>
				<td align="center">
					<input type="text" ng-model="itemBreason1"/>
				</td>
				<td align="center">
					输血时间
				</td>
				<td align="center">
					<input type="text" ng-model="itemBtime1" id="btime1"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					输血原因
				</td>
				<td align="center">
					<input type="text" ng-model="itemBreason2"/>
				</td>
				<td align="center">
					输血时间
				</td>
				<td align="center">
					<input type="text" ng-model="itemBtime2" id="btime2"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center" rowspan="3">
					家族史
				</td>
				<td align="center">
					父亲
				</td>
				<td align="center">
					<input type="text" ng-model="itemFather"/>
				</td>
				<td align="center">
					母亲
				</td>
				<td align="center" colspan="2">
					<input type="text" ng-model="itemMother"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					兄弟姐妹
				</td>
				<td align="center">
					<input type="text" ng-model="itemBsrelative"/>
				</td>
				<td align="center">
					子女
				</td>
				<td align="center" colspan="2">
					<input type="text" ng-model="itemChildren"/>
				</td>
			</tr>
			<tr height="30px">
				<td align="left" colspan="5">
					<lable>1无</lable>
					<lable style="margin-left: 10px;">2高血压</lable>
					<lable style="margin-left: 10px;">3糖尿病</lable>
					<lable style="margin-left: 10px;">4冠心病</lable>
					<lable style="margin-left: 10px;">5慢性阻塞性肺疾病</lable>
					<lable style="margin-left: 10px;">6恶性肿瘤</lable>
					<lable style="margin-left: 10px;">7脑卒中</lable>
					<lable style="margin-left: 10px;">8严重精神障碍</lable>
					<lable style="margin-left: 10px;">9结核病</lable>
					<lable style="margin-left: 10px;">10肝炎</lable>
					</br><lable>11先天畸形</lable>
					<lable style="margin-left: 10px;">12其他</lable>
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					遗传病史
				</td>
				<td align="center">
					<select id="itemGenetic">
						<option value="1">无</option>
						<option value="2">有</option>
					</select>
				</td>
				<td align="center">
					疾病名称
				</td>
				<td align="center" style="border-right:0;">
					<input type="text" ng-model="itemDname"/>
				</td>
				<td align="center" colspan="2" style="border:0;">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">
					残疾情况<input id="itemDisability" type="hidden">
				</td>
				<td align="center" colspan="5">
					<table width="100%" border="0">
						<tr>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="1" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								无残疾
							</td>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="2" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								视力残疾
							</td>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="3" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								听力残疾
							</td>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="4" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								语言残疾
							</td>
						</tr>
						<tr>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="5" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								肢体残疾
							</td>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="6" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								智力残疾
							</td>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="7" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								精神残疾
							</td>
							<td style="border:0px;">
								<input name="disability" type="checkbox" value="8" style="width:15px;height:15px;">
							</td>
							<td style="border:0px;">
								其他残疾
							</td>
						</tr>
					</table>
				</td>
			</tr><!--
			<tr height="30px">
				<td align="center">
					家庭情况
				</td>
				<td align="center" colspan="5">
					<table width="100%" border="0">
						<tr>
							<td>
								户主姓名
							</td>
							<td style="border:0px;">
								<input type="text" ng-model="itemField5"/>
							</td>
							<td>
								户主身份证
							</td>
							<td>
								<input type="text" ng-model="itemField6"/>
							</td>
							<td>
								家庭人口数
							</td>
							<td>
								<input type="text" ng-model="itemField7"/>
							</td>
						</tr>
						<tr>
							<td>
								家庭结构
							</td>
							<td>
								<input type="text" ng-model="itemField8"/>
							</td>
							<td>
								居住情况
							</td>
							<td colspan="3">
								<table width="100%" border="0">
									<tr>
										<td style="border:0px;">
											<input type="radio" value="1" name="field9" checked="checked" style="width:15px;margin-top:3px;">
										</td>
										<td style="border:0px;">
											与成年子女同住
										</td>
										<td style="border:0px;">
											<input type="radio" value="2" name="field9" style="width:15px;margin-top:3px;">
										</td>
										<td style="border:0px;">
											与子孙三代(四代)同住
										</td>
										<td style="border:0px;">
											<input type="radio" value="3" name="field9" style="width:15px;margin-top:3px;">
										</td>
										<td style="border:0px;">
											夫妻二人同住
										</td>
									</tr>
									<tr>
										<td style="border:0px;">
											<input type="radio" value="4" name="field9" style="width:15px;margin-top:3px;">
										</td>
										<td style="border:0px;">
											独居
										</td>
										<td style="border:0px;">
											<input type="radio" value="5" name="field9" style="width:15px;margin-top:3px;">
										</td>
										<td colspan="3" style="border:0px;">
											计划生育特殊家庭
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>-->
			<tr height="30px">
				<td align="center" rowspan="5">
					生活环境
				</td>
				<td align="center">
					厨房排风设施
				</td>
				<td align="center" style="border-right:0;">
					<select id="itemKfacility">
						<option value="1">无</option>
						<option value="2">油烟机</option>
						<option value="3">换气扇</option>
						<option value="4">烟囱</option>
					</select>
				</td>
				<td align="center" colspan="3" style="border:0;">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					燃料类型
				</td>
				<td align="center" style="border-right:0;">
					<select id="itemFtype">
						<option value="1">液化气</option>
						<option value="2">煤</option>
						<option value="3">天然气</option>
						<option value="4">沼气</option>
						<option value="5">柴火</option>
						<option value="6">其他</option>
					</select>
				</td>
				<td align="center" colspan="3" style="border:0;">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					饮水
				</td>
				<td align="center" style="border-right:0;">
					<select id="itemDwater">
						<option value="1">自来水</option>
						<option value="2">经净化过滤的水</option>
						<option value="3">井水</option>
						<option value="4">河湖水</option>
						<option value="5">塘水</option>
						<option value="6">其他</option>
					</select>
				</td>
				<td align="center" colspan="3" style="border:0;">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					厕所
				</td>
				<td align="center" style="border-right:0;">
					<select id="itemToilet">
						<option value="1">卫生厕所</option>
						<option value="2">一格或二格粪池式</option>
						<option value="3">马桶</option>
						<option value="4">露天粪坑</option>
						<option value="5">简易棚厕</option>
					</select>
				</td>
				<td align="center" colspan="3" style="border:0;">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					禽畜栏
				</td>
				<td align="center" style="border-right:0;">
					<select id="itemPoultry">
						<option value="1">无</option>
						<option value="2">单设</option>
						<option value="3">室内</option>
						<option value="4">室外</option>
					</select>
				</td>
				<td align="center" colspan="3" style="border:0;">
					&nbsp;
				</td>
			</tr>
			<tr height="30px">
				<td align="center">
					所属机构
				</td>
				<td align="center">
					<input type="text" ng-model="itemFileunit" readonly="readonly"/>
				</td>
				<td align="center">
					创建日期
				</td>
				<td align="center">
					<input type="text" ng-model="itemCreateDate" readonly="readonly"/>
				</td>
				<td align="center">
					更新日期
				</td>
				<td align="center">
					<input type="text" ng-model="itemUpdateDate" readonly="readonly"/>
				</td>
			</tr>
			<tr height="100%">
				<td align="center" colspan="6">
					&nbsp;
				</td>
			</tr>
		</table>   
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
				$("#itemBirthdate").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#ctime1").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#ctime2").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#ctime3").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#ctime4").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#stime1").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#stime2").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#ttime1").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#ttime2").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#btime1").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
				$("#btime2").datepicker({
				  language: 'zh-CN',
				  format: 'yyyy-mm-dd'
				});
			});
			
			//初始化List信息
			function initListData($http,$scope){
				let id = $("#uid").val();
			    $http({
			        method : "GET",
			        url : "<%=basePath%>patient/getPatient.do",
				    params: {id:id}
			    }).then(function mySucces(response) {
			    	$scope.loadingShow = false;
			        let pObj = response.data.datas;
			        //姓名
			        $scope.itemName = changeNullValue(pObj.name);
			        //本人电话
			        $scope.itemPhoneno = changeNullValue(pObj.phoneno);
			        //档案编号
			        $scope.itemCode = changeNullValue(pObj.code);
			        //出生日期
			        $scope.itemBirthdate = timestampToTime(changeNullValue(pObj.birthdate));
			        //身份证号
			        $scope.itemIdcard = changeNullValue(pObj.idcard);
					//工作单位
					$scope.itemCompany = changeNullValue(pObj.company);
					//联系人姓名
					$scope.itemCname = changeNullValue(pObj.cname);
					//联系人电话
					$scope.itemCphoneno = changeNullValue(pObj.cphoneno);
					//确诊时间1
					$scope.itemCtime1 = timestampToTime(changeNullValue(pObj.ctime1));
					//确诊时间2
					$scope.itemCtime2 = timestampToTime(changeNullValue(pObj.ctime2));
					//确诊时间3
					$scope.itemCtime3 = timestampToTime(changeNullValue(pObj.ctime3));
					//确诊时间4
					$scope.itemCtime4 = timestampToTime(changeNullValue(pObj.ctime4));
					//手术名称1
					$scope.itemSname1 = changeNullValue(pObj.sname1);
					//手术时间1
					$scope.itemStime1 = timestampToTime(changeNullValue(pObj.stime1));
					//手术名称2
					$scope.itemSname2 = changeNullValue(pObj.sname2);
					//手术时间2
					$scope.itemStime2 = timestampToTime(changeNullValue(pObj.stime2));
					//外伤名称1
					$scope.itemTname1 = changeNullValue(pObj.tname1);
					//外伤时间1
					$scope.itemTtime1 = timestampToTime(changeNullValue(pObj.ttime1));
					//外伤名称2
					$scope.itemTname2 = changeNullValue(pObj.tname2);
					//外伤时间2
					$scope.itemTtime2 = timestampToTime(changeNullValue(pObj.ttime2));
					//输血原因1
					$scope.itemBreason1 = changeNullValue(pObj.breason1);
					//输血时间1
					$scope.itemBtime1 = timestampToTime(changeNullValue(pObj.btime1));
					//输血原因2
					$scope.itemBreason2 = changeNullValue(pObj.breason2);
					//输血时间2
					$scope.itemBtime2 = timestampToTime(changeNullValue(pObj.btime2));
					//父亲
					$scope.itemFather = changeNullValue(pObj.father);
					//母亲
					$scope.itemMother = changeNullValue(pObj.mother);
					//兄弟姐妹
					$scope.itemBsrelative = changeNullValue(pObj.bsrelative);
					//子女
					$scope.itemChildren = changeNullValue(pObj.children);
					//疾病名称
					$scope.itemDname = changeNullValue(pObj.dname);
					//所属机构
					$scope.itemFileunit = changeNullValue(pObj.fileunit);
					//创建日期
					$scope.itemCreateDate = timestampToTime(pObj.createDate);
					//更新日期
					$scope.itemUpdateDate = timestampToTime(pObj.updateDate);
					// 医保卡号(城镇保险) 
					$scope.itemField1 = changeNullValue(pObj.field1);
					// 医保卡号(居民基本保险)
					$scope.itemField2 = changeNullValue(pObj.field2);
					// 卡号 
					$scope.itemField3 = changeNullValue(pObj.field3);
					// 医疗费用支付方式其他 
					$scope.itemField4 = changeNullValue(pObj.field4);
					// 户主姓名 
					//$scope.itemField5 = changeNullValue(pObj.field5);
					// 户主身份证 
					//$scope.itemField6 = changeNullValue(pObj.field6);
					// 家庭人口数 
					//$scope.itemField7 = changeNullValue(pObj.field7);
					// 家庭结构 
					//$scope.itemField8 = changeNullValue(pObj.field8);
					//疾病其他
					$scope.itemField10 = changeNullValue(pObj.field10);
					//民族
					$scope.itemNationality = changeNullValue(pObj.nationality);
					if($scope.itemNationality==""){
						$scope.itemNationality = "汉族";
					}
					$(function(){
						//性别
						if(pObj.sex===undefined){
							pObj.sex = 1;
						}
						$("#itemSex").val(pObj.sex);
						//常驻类型
						if(pObj.rtype===undefined){
							pObj.rtype = 1;
						}
						$("#itemRtype").val(pObj.rtype);
						//血型
						if(pObj.blood===undefined){
							pObj.blood = 5;
						}
						$("#itemBlood").val(pObj.blood);
						//RH阴性
						if(pObj.RH===undefined){
							pObj.RH = 3;
						}
						$("#itemRH").val(pObj.RH);
						//文化程度
						if(pObj.elevel===undefined){
							pObj.elevel = 10;
						}
						$("#itemElevel").val(pObj.elevel);
						//职业
						if(pObj.career===undefined){
							pObj.career = 9;
						}
						$("#itemCareer").val(pObj.career);
						//婚姻状况
						if(pObj.marital===undefined){
							pObj.marital = 5;
						}
						$("#itemMarital").val(pObj.marital);
						//手术
						if(pObj.surgery===undefined){
							pObj.surgery = 1;
						}
						$("#itemSurgery").val(pObj.surgery);
						//外伤
						if(pObj.trauma===undefined){
							pObj.trauma = 1;
						}
						$("#itemTrauma").val(pObj.trauma);
						//输血
						if(pObj.btran===undefined){
							pObj.btran = 1;
						}
						$("#itemBtran").val(pObj.btran);
						//遗传病史
						if(pObj.genetic===undefined){
							pObj.genetic = 1;
						}
						$("#itemGenetic").val(pObj.genetic);
						//厨房排风设施
						if(pObj.kfacility===undefined){
							pObj.kfacility = 1;
						}
						$("#itemKfacility").val(pObj.kfacility);
						//燃料类型
						if(pObj.ftype===undefined){
							pObj.ftype = 1;
						}
						$("#itemFtype").val(pObj.ftype);
						//饮水
						if(pObj.dwater===undefined){
							pObj.dwater = 1;
						}
						$("#itemDwater").val(pObj.dwater);
						//厕所
						if(pObj.toilet===undefined){
							pObj.toilet = 1;
						}
						$("#itemToilet").val(pObj.toilet);
						//禽畜栏
						if(pObj.poultry===undefined){
							pObj.poultry = 1;
						}
						$("#itemPoultry").val(pObj.poultry);
						//###复选框
						//医疗费用支付方式[paymentway]
						$scope.itemPaymentway = pObj.paymentway;
						initCheckBox("paymentway",pObj.paymentway);
						//药物过敏史[allergy]
						if(pObj.allergy===undefined){
							pObj.allergy="1";
						}
						$scope.itemAllergy = pObj.allergy;
						initCheckBox("allergy",pObj.allergy);
						//暴露史[exposure]
						$scope.itemExposure = pObj.exposure;
						initCheckBox("exposure",pObj.exposure);
						//疾病[disease]
						if(pObj.disease===undefined){
							pObj.disease="1";
						}
						$scope.itemDisease = pObj.disease;
						initCheckBox("disease",pObj.disease);
						//残疾情况[disability]
						$scope.itemDisability = pObj.disability;
						initCheckBox("disability",pObj.disability);
						
						//###单选框
						// 居住情况[field9]
						//initRedio("field9",pObj.field9);
						
						//自动填充卡号
						$("#c1").change(function(){
							let isCheck = $(this).get(0).checked;
							if(isCheck){
								$scope.itemField1 = $scope.itemIdcard;
								$("#cx1").val($scope.itemIdcard);
							}else{
								$scope.itemField1 = "";
								$("#cx1").val("");
							}
						});
						$("#c2").change(function(){
							let isCheck = $(this).get(0).checked;
							if(isCheck){
								$scope.itemField2 = $scope.itemIdcard;
								$("#cx2").val($scope.itemIdcard);
							}else{
								$scope.itemField2 = "";
								$("#cx2").val("");
							}
						});
						$("#c3").change(function(){
							let isCheck = $(this).get(0).checked;
							if(isCheck){
								$scope.itemField3 = $scope.itemIdcard;
								$("#cx3").val($scope.itemIdcard);
							}else{
								$scope.itemField3 = "";
								$("#cx3").val("");
							}
						});
					});
			    }, function myError(response) {
			    	$scope.loadingShow = false;
			        alert("patient->getPatient.do访问错误出错!");
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
			    	let id = $("#uid").val();
			    	//姓名
			        let name = encodeURI($scope.itemName);
			        //本人电话
			        let phoneno = $scope.itemPhoneno;
			        //出生日期
			        let birthdate = $("#itemBirthdate").val();
			        if(birthdate!=""&&!regExp.test(birthdate)){
			        	alert("出生日期格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
			        //身份证号
			        let idcard = $scope.itemIdcard;
			        if(idcard==""){
						alert("身份证不能为空!");
						return;
					}else if(idcard.length<=13){
						alert("身份证至少13位以上!");
						return;
					}else if(idcard.length>18){
						alert("身份证最多为18位!");
						return;
					}
					//工作单位
					let company = encodeURI($scope.itemCompany);
					//联系人姓名
					let cname = encodeURI($scope.itemCname);
					//联系人电话
					let cphoneno = $scope.itemCphoneno;
					//确诊时间1
					let ctime1 = $("#ctime1").val();
					if(ctime1!=""&&!regExp.test(ctime1)){
			        	alert("确诊时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//确诊时间2
					let ctime2 = $("#ctime2").val();
					if(ctime2!=""&&!regExp.test(ctime2)){
			        	alert("确诊时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//确诊时间3
					let ctime3 = $("#ctime3").val();
					if(ctime3!=""&&!regExp.test(ctime3)){
			        	alert("确诊时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//确诊时间4
					let ctime4 = $("#ctime4").val();
					if(ctime4!=""&&!regExp.test(ctime4)){
			        	alert("确诊时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//手术名称1
					let sname1 = encodeURI($scope.itemSname1);
					//手术时间1
					let stime1 = $("#stime1").val();
					if(stime1!=""&&!regExp.test(stime1)){
			        	alert("手术时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//手术名称2
					let sname2 = encodeURI($scope.itemSname2);
					//手术时间2
					let stime2 = $("#stime2").val();
					if(stime2!=""&&!regExp.test(stime2)){
			        	alert("手术时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//外伤名称1
					let tname1 = encodeURI($scope.itemTname1);
					//外伤时间1
					let ttime1 = $("#ttime1").val();
					if(ttime1!=""&&!regExp.test(ttime1)){
			        	alert("外伤时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//外伤名称2
					let tname2 = encodeURI($scope.itemTname2);
					//外伤时间2
					let ttime2 = $("#ttime2").val();
					if(ttime2!=""&&!regExp.test(ttime2)){
			        	alert("外伤时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//输血原因1
					let breason1 = encodeURI($scope.itemBreason1);
					//输血时间1
					let btime1 = $("#btime1").val();
					if(btime1!=""&&!regExp.test(btime1)){
			        	alert("输血时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//输血原因2
					let breason2 = encodeURI($scope.itemBreason2);
					//输血时间2
					let btime2 = $("#btime2").val();
					if(btime2!=""&&!regExp.test(btime2)){
			        	alert("输血时间格式不正确，正确格式为：2014-01-01");
			        	return;
			        }
					//父亲
					let father = $scope.itemFather;
					//母亲
					let mother = $scope.itemMother;
					//兄弟姐妹
					let bsrelative = $scope.itemBsrelative;
					//子女
					let children = $scope.itemChildren;
					//疾病名称
					let dname = encodeURI($scope.itemDname);
					
					// 医保卡号(城镇保险) 
					let field1 = $scope.itemField1;
					// 医保卡号(居民基本保险)
					let field2 = $scope.itemField2;
					// 卡号 
					let field3 = $scope.itemField3;
					// 医疗费用支付方式其他 
					let field4 = encodeURI($scope.itemField4);
					// 户主姓名 
					let field5 = "";//encodeURI($scope.itemField5);
					// 户主身份证 
					let field6 = "";//$scope.itemField6;
					// 家庭人口数 
					let field7 = "";//$scope.itemField7;
					// 家庭结构 
					let field8 = "";//encodeURI($scope.itemField8);
					//民族
					let nationality = encodeURI($scope.itemNationality);
					//疾病其他
					let field10 = encodeURI($scope.itemField10);
					//###单选框
					//居住情况[field9]
					let field9 = "";//getRedio("field9");
					
			        //性别
					let sex = $("#itemSex").val();
					//常驻类型
					let rtype = $("#itemRtype").val();
					//血型
					let blood = $("#itemBlood").val();
					//RH阴性
					let RH = $("#itemRH").val();
					//文化程度
					let elevel = $("#itemElevel").val();
					//职业
					let career = $("#itemCareer").val();
					//婚姻状况
					let marital = $("#itemMarital").val();
					//手术
					let surgery = $("#itemSurgery").val();
					//外伤
					let trauma = $("#itemTrauma").val();
					//输血
					let btran = $("#itemBtran").val();
					//遗传病史
					let genetic = $("#itemGenetic").val();
					//厨房排风设施
					let kfacility = $("#itemKfacility").val();
					//燃料类型
					let ftype = $("#itemFtype").val();
					//饮水
					let dwater = $("#itemDwater").val();
					//厕所
					let toilet = $("#itemToilet").val();
					//禽畜栏
					let poultry = $("#itemPoultry").val();
			        //###获取复选框数据
					//医疗费用支付方式
					let paymentway = getCheckBox('paymentway');
					$scope.itemPaymentway = paymentway;
					//药物过敏史
					let allergy = getCheckBox('allergy');
					$scope.itemAllergy = allergy;
					//暴露史
					let exposure = getCheckBox('exposure');
					$scope.itemExposure = exposure;
					//疾病
					let disease = getCheckBox('disease');
					$scope.itemDisease = disease;
					//残疾情况
					let disability = getCheckBox('disability');
					$scope.itemDisability = disability;
					//显示加载框
					$scope.loadingShow = true;
					//拼装参数
					let patientDto = {id:id,name:name,phoneno:phoneno,idcard:idcard,company:company,cname:cname,cphoneno:cphoneno,sname1:sname1,
					sname2:sname2,tname1:tname1,tname2:tname2,breason1:breason1,breason2:breason2,father:father,mother:mother,
					bsrelative:bsrelative,children:children,dname:dname,sex:sex,rtype:rtype,nationality:nationality,blood:blood,RH:RH,elevel:elevel,
					career:career,marital:marital,surgery:surgery,trauma:trauma,btran:btran,genetic:genetic,kfacility:kfacility,ftype:ftype,
					dwater:dwater,toilet:toilet,poultry:poultry,paymentway:paymentway,allergy:allergy,exposure:exposure,disease:disease,disability:disability,
					sbirthdate:birthdate,sctime1:ctime1,sctime2:ctime2,sctime3:ctime3,sctime4:ctime4,sstime1:stime1,sstime2:stime2,sbtime2:btime2,sttime1:ttime1,
					sttime2:ttime2,sbtime1:btime1,
					field1:field1,field2:field2,field3:field3,
					field4:field4,field5:field5,field6:field6,
					field7:field7,field8:field8,field9:field9,field10:field10};
				    $http({
				        method : "POST",
				        url : "<%=basePath%>patient/updHealthCase.do",
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
				        alert("updHealthCase.do访问错误出错!");
				        console.log(response.statusText);
				    });
			    };
			});
		</script>
	</body>
</html>
