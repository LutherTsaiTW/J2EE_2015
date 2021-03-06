<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>50多媒體廣告</title>
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
        margin-top: 30px;
        font-size: 15px;
    }
    
    .welcomeMessage {
        float: right;
        margin-top: 40px;
        margin-right: 10px;
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
    
    .menuButton {
    	width: 120px;
        height: 120px;
        float: left;
        text-align: center;
        font-size: 20px;
        margin:5px;
    }
    
    .menuSection {
    	width: 270px;
        height: 270px;
        float: center;
        text-align: center;
    }
    
    </style>
</head>
<body>
	<div class="pageHeader">
        <img class="pageLogo" src="../../img/50_MULTI_b.png">
        <button class="pageHeaderButton" type="button" onclick="javascript:location.href='../../index.jsp'">登出系統</button>
        <div class="welcomeMessage">
        	<font>$userName，歡迎來到50廣告公司</font>
        </div>
    </div>
    <br>
    <p class="title">服務目錄</p>
    <br>
    <center>
    <div class="menuSection">
    	<div>
     		<button class="menuButton" type="button" onclick="javascript:location.href='addAdvertise'">刊登廣告</button>
        	<button class="menuButton" type="button" onclick="javascript:location.href='manageAdvertise'">廣告列表</button>
    	</div>
    	<div>
        	<button class="menuButton" type="button" onclick="javascript:location.href='manageAccount'">我的帳戶</button>
        	<button class="menuButton" type="button" onclick="javascript:location.href='managePayment'">收入/支出</button>
        </div>
    </div>
    <div>
		$adImport
    </div>
    </center>
</body>
</html>