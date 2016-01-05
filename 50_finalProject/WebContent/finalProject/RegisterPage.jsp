<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>帳號註冊</title>
    <script type="text/javascript" src="../../js/jquery-2.1.4.min.js"></script>
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
	}
    </style>
    
    <script>
    $(document).ready(function(){
        $("#registerForm").submit(function() {
    		var pass = $( "#password" ).val();
            var passCheck = $( "#passwordCheck" ).val();
            if(pass.localeCompare(passCheck) != 0) {
            	alert("請確認輸入的密碼是否相同!!");
            	return false;
            }
    		});
    }); 
    </script>
</head>

<body>
    <div class="pageHeader">
        <img class="pageLogo" src="../../img/50_MULTI_b.png">
        <button class="pageHeaderButton" type="button" onclick="javascript:location.href='../../index.jsp'">登入系統</button>
    </div>
    <div class="pageTitle">
        <font class="title">註冊帳號</font>
    </div>
    <div class="register">
        <form id="registerForm" action="doRegister" method="POST">
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
                <input class="inputForm" id="realName" type="text" name="name" required pattern="(?=.*[\u4E00-\u9fa5A-Za-z]+$).{3,}" title="僅接受至少三個字的中文或英文姓名" />
                <br>
                <br> 輸入電郵：
                <input class="inputForm" type="email" id="email" name="email" value="" required title="請輸入有效的E-mail信箱">
                <br>
                <br> 銀行帳號：
                <input class="inputForm" type="text" id="text" name="bankAccount" value="" required title="請輸入53銀行之帳號(數字)">
                <br>
                <br>
            </div>
            <div class="loginButtonSection">
				<input class="loginButton" type="submit" name="submit" value="註冊">
			</div>
        </form>
    </div>
</body>

</html>