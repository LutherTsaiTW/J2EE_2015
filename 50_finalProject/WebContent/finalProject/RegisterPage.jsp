<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8">
    <title>帳號註冊</title>
    <style>
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
    </style>
</head>

<body>
    <p class="title">帳號註冊</p>
    <div class="register">
        <form action="">
            <div style="text-align: center;">
                輸入帳號：
                <input class="inputForm" type="text" id="account" name="account" value="" pattern="(?=.*^\w+$)(?=.*\d).{8,14}" required title="帳號需至少包含8-14個字元與一個英文字母、一個阿拉伯數字，不包含特出字元">
                <br>
                <br> 輸入密碼：
                <input class="inputForm" type="password" id="password" name="password" value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,14}" required title="密碼需至少包含6-14個字元與一個小寫英文字母、大寫英文字母、一個阿拉伯數字">
                <br>
                <br> 密碼確認：
                <input class="inputForm" type="password" id="passwordCheck" name="passwordCheck" value="" required>
                <br>
                <br> 真實姓名：
                <input class="inputForm" id="realName" type="text" name="Name" required pattern="(?=.*[\u4E00-\u9fa5A-Za-z]+$).{3,}" title="僅接受至少三個字的中文或英文姓名" />
                <br>
                <br> 輸入電郵：
                <input class="inputForm" type="email" id="email" name="email" value="" required title="請輸入有效的E-mail信箱">
                <br>
                <br>
            </div>
            <div style="text-align: center;">
                <input type="submit" id="submit" name="submit" value="註冊">
            </div>
        </form>
    </div>
</body>

</html>
