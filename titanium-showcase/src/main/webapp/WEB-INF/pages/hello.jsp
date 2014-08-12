<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${base }/static/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.getJSON("${base}/hello/hello2.json",{},function(data){
			//alert(JSON.stringify(data));
			
			$.getJSON("${base}/hello/fetchContants.json",{},function(data){
				$("#test").html(data.msg);
			});
		});
		
	});
</script>
</head>
<body>
	hello ! --- ${hello }
	
	<div id="test"></div><br/>
	<c:forEach var="item" items="${result }">
		username:<span>${item.username }</span><br/>
		password:<span>${item.password }</span><br/>
		age:<span>${item.age }</span><br/>
		<c:if test="${item.age >24 }">
			<c:out value="高于24周岁 大龄青年"></c:out>
		</c:if>
		<c:if test="${item.username eq 'tom' }">
			<span>让我想起了Tom猫。。。哈哈</span>
		</c:if>
		<br/>
	</c:forEach>
	
	
</body>
</html>