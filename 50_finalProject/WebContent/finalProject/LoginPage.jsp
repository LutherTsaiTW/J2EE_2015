<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Java50多媒體廣告</title>
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
        float: right;
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
        font-size: 24px;
        margin: 0px auto;
    }
    
    .loginSection {
        width: 400px;
        height: 50px;
        margin: 0px auto;
        float: center;
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
        float: center;
        width: 100%;
        height: 30px;
        text-align: center;
    }
    </style>
</head>

<body>
    <div class="pageHeader">
        <img class="pageLogo" src="../../img/50_MULTI_b.png">
        <button class="pageHeaderButton" type="button" onclick="javascript:location.href='register'">註冊帳號</button>
    </div>
    <div class="pageTitle">
        <font class="title">登入系統</font>
    </div>
    <div class="loginSection">
        <form action="">
            <br>
            <br> 帳號：
            <input class="inputForm" type="text" name="account" value="" required>
            <br>
            <br> 密碼：
            <input class="inputForm" type="password" name="password" value="" required>
            <br>
            <br>
            <div class="loginButtonSection">
                <input class="loginButton" type="submit" name="submit" value="登入">
            </div>
        </form>
    </div>
</body>

</html>
