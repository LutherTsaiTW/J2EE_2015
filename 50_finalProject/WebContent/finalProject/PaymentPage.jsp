<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收入/支出</title>
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
    <script type="text/javascript">
            function IncomeSuccess() {
            	alert("轉帳成功");
            }
            
            function PaySuccess() {
            	alert("付款成功，感謝您支持50廣告")
             }
    </script>
</head>
<body>
	<script type="text/javascript">
		$callFunction
    </script>
 	<div class="pageHeader">
        <a href="menu"><img class="pageLogo" src="../../img/50_MULTI_b.png"></a>
        <button class="pageHeaderButton" type="button" onclick="javascript:location.href='../../index.jsp'">登出系統</button>
        <div class="welcomeMessage">
            <font>$userName，歡迎來到50廣告公司</font>
        </div>
    </div>
    <p class="title">收取/支付帳款</p>
    <br>
    <center>
    <div class="menuSection">
    	<div>
            <table width='230px' style="table-layout:fixed" border="1px" cellspacing="0px" cellpadding="6px">
                    <td width='115px'>目前收入</td>
                    <td width='115px'>$adIncomeFee</td>
                </tr>
                <tr>
                    <td>目前欠款</td>
                    <td>$adPaymentFee</td>
                </tr>
            </table>
            <br>
        </div>
    	<div>
    		<form id="formIncome" action="doPayIncomeFee" method="POST"></form>
    		<form id="formPayment" action="doPayFee" method="POST"></form>
     		<button class="menuButton" type="submit" form="formIncome" value="Submit">我要收款</button>
        	<button class="menuButton" type="submit" form="formPayment" value="Submit">我要付款</button>
    	</div>
    </div>
    </center>
</body>
</html>