<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>註冊成功</title>
<style>
    .pageHeader {
        width: 100%;
        height: 80px;
        float: left;
    }
    
    .pageLogo {
        height: 100%;
        float: left;
    }
    
    .pageHeaderButton {
        width: 80px;
        height: 30px;
        float: center;
        border-radius: 5px;
        margin: 20px;
        font-size: 15px;
    }
    
    .pageTitle {
        width: 100%;
        height: 30px;
        float: left;
        border-radius: 5px;
        text-align: center;
    }
    
    .title {
        text-align: center;
        font-size: 25px
    }
    
    .inputForm {
        border-radius: 5px;
        width: 300px;
    }
    
    .register {
        margin: 0px auto;
        width: 500px;
        height: 600px;
    }
    
    .inputForm {
        border-radius: 5px;
        width: 300px;
    }
    
    .loginButton {
		width: 80px;
		height: 100%;
		border-radius: 5px;
		font-size: 15px;
	}
	
    .loginButtonSection {
		float:center;
		width: 100%;
		height: 30px;
		text-align:center;
		margin-top:20px;
	}
    </style>
</head>
<body>
	<div class="pageHeader">
        <img class="pageLogo" src="../img/50_MULTI_b.png">
    </div>
    <div class="pageTitle">
        <font class="title">恭喜註冊成功</font>
        <br>
        <font class="title">歡迎成為Java 50多媒體的夥伴</font>
        <br>
    </div>
    <div class="loginButtonSection">
    	<br>
		<button class="pageHeaderButton" type="button" onclick="javascript:location.href='../index.jsp'">登入系統</button>
	</div>
</body>
</html>