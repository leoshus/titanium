<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${base }/static/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.getJSON("${base}/hello/hello2.json",{},function(data){
			alert(JSON.stringify(data));
		});
	});
</script>
</head>
<body>
	hello ! --- ${hello }
</body>
</html>