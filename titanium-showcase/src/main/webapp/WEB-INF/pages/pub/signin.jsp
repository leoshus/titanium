<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Titanium-SpringMVC</title>
<script src="${base }/static/js/jquery/jquery-1.9.1.min.js" type="text/javascript" ></script>
<link rel="stylesheet" type="text/css" href="${base }/static/css/login.css"/>
</head>
<body>
<div class='signup_container'>
    <h1 class='signup_title'>Titanium-SpringMVC</h1>
    <img src='${base}/static/images/login/people.png' id='admin'/>
    <div id="signup_forms" class="signup_forms clearfix">
            <form class="signup_form_form" id="signup_form" method="post" action="${base }/j_spring_security_check">
                    <div class="form_row first_row">
                        <label for="signup_email">请输入用户名</label><div class='tip ok'></div>
                        <input type="text" name="j_username" placeholder="请输入用户名" id="signup_name" data-required="required">
                    </div>
                    <div class="form_row">
                        <label for="signup_password">请输入密码</label><div class='tip error'></div>
                        <input type="password" name="j_password" placeholder="请输入密码" id="signup_password" data-required="required">
                    </div>
                    <!-- <div class="form_row">
                            <input type="text" name="user[password]" placeholder="" id="signup_select" value='' data-required="required">
                    </div> -->
           </form>
    </div>

    <div class="login-btn-set">
    	<div class='rem'>记住我</div> <a href='javascript:void(0)' id="loginSubmit" class='login-btn'></a>
    </div>
    <div id="fortest"><a href="javascript:void(0)">admin测试入口</a></div>
    <p class='copyright'>版权所有 sdw2330976</p>
</div>

</body>

<script type="text/javascript">

$(function(){
	//TODO ------------for developer start----------- 
	$("#fortest").click(function(){
		$("#signup_name").val("admin");
		$("#signup_password").val("123");
		$("#signup_form").submit();
	});
	//-------------------end----------------------
	$("#loginSubmit").click(function(){
		$("#signup_name").val("admin");
		$("#signup_password").val("123");
		$("#signup_form").submit();
	});

    $('.rem').click(function(){
        $(this).toggleClass('selected');
    });

    $('#signup_select').click(function(){
        $('.form_row ul').show();
        event.cancelBubble = true;
    });

    $('#d').click(function(){
        $('.form_row ul').toggle();
        event.cancelBubble = true;
    });

    $('body').click(function(){
        $('.form_row ul').hide();
    });

    $('.form_row li').click(function(){
        var v  = $(this).text();
        $('#signup_select').val(v);
        $('.form_row ul').hide();
    });


});


</script>

</html>