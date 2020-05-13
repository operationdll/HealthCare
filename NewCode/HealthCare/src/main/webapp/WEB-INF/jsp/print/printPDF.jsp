<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <meta name="Generator" content="FastReport http://www.fast-report.com" /> 
  <title>智慧健康云平台</title> 
  <style type="text/css">
  </style> 
 </head> 
 <body> 
      <a class="media" href="<%=basePath%>${fileName}"></a>
	  <script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
	  <script type="text/javascript" src="<%=basePath%>js/jquery.media.js"></script>
		<script type="text/javascript">
			$(function() {  
				let w=$(window).width();
				let h=$(window).height();
			    $('a.media').media({width:w, height:h});  
			});
		</script>
 </body>
</html>
