<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理廣告</title>
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
    <p class="title">管理廣告</p>
    <center>
			<table border="1px" cellspacing="0px" cellpadding="6px"
				valign="middle">
				<tr>
					<th>廣告名稱</th>
					<th>廣告內容</th>
					<th>廣告圖片</th>
					<th>開始日期</th>
					<th>結束日期</th>
				</tr>
				#foreach( $adModel in $AdListModel )
				<tr>
					<td>$adModel.adTitle</td>
					<td>$adModel.adDes</td>
					<td><img src="$adModel.adImageLink" height="100" width="400"></td>
					<td>$adModel.adStartDate</td>
					<td>$adModel.adEndDate</td>
				</tr> 
				#end
			</table>
		</center>
</body>
</html>