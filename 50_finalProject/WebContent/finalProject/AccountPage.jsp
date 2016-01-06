<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的帳號</title>
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
    
    table,
    th,
    td {
        border: 1px solid black;
        cellpadding:6px;
        cellspacing:0px;
    }
    </style>
</head>

<body>
    <div class="pageHeader">
        <a href="menu"><img class="pageLogo" src="../../img/50_MULTI_b.png"></a>
        <button class="pageHeaderButton" type="button" onclick="javascript:location.href='../../index.jsp'">登出系統</button>
        <div class="welcomeMessage">
            <font>$userName，歡迎來到50廣告公司</font>
        </div>
    </div>
    <p class="title">我的帳號</p>
    <center>
        <div>
            <table width='400px' style="table-layout:fixed">
                <tr>
                    <td width='100px'>客戶</td>
                    <td width='300px'>$name</td>
                </tr>
                <tr>
                    <td>Token</td>
                    <td>$applicationToken</td>
                </tr>
                <tr>
                    <td>目前收入</td>
                    <td>$adIncomeFee</td>
                </tr>
                <tr>
                    <td>目前欠款</td>
                    <td>$adPaymentFee</td>
                </tr>
            </table>
        </div>
    </center>
</body>

</html>
