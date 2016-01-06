<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>刊登廣告</title>
    <script type="text/javascript" src="../../js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../js/uploadAd.js"></script>
    <script type="text/javascript" src="../../js/moment-with-locales.js"></script>
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
        margin: 5px;
    }
    
    .menuSection {
        width: 270px;
        height: 270px;
        float: center;
        text-align: center;
    }
    
    .addNewAd {
        margin: 0px auto;
        width: 500px;
        height: 600px;
    }
    
    .uploadImage {
        margin: 0px auto;
    }
    
    .days {
        font-size: 10px;
        text-align: right;
    }
    
    .createAd {
        margin: 0px auto;
    }
    
    .adDescription {
        resize: none;
        width: 100%;
        height: 100px;
        border-radius: 5px;
    }
    
    .progress {
        margin: 0px auto;
        position: relative;
        width: 500px;
        border: 1px solid #ddd;
        padding: 1px;
        border-radius: 5px;
    }
    
    .bar {
        background-color: #B4F5B4;
        width: 0%;
        height: 20px;
        border-radius: 5px;
    }
    
    .percent {
        margin: 0px auto;
        position: absolute;
        display: inline-block;
        top: 3px;
        left: 48%;
    }
    
    .inputForm {
        border-radius: 5px;
        width: 300px;
    }
    </style>
</head>
<script>
function getExpectDays() {
    var startTimeObj = document.getElementById("startTime");
    var startTime = startTimeObj.value;
    var endTimeObj = document.getElementById("endTime");
    var endTime = endTimeObj.value;
    var days = document.getElementById("days");
    startTime = moment(startTime, "YYYY-MM-DD");
    endTime = moment(endTime, "YYYY-MM-DD");
    var diffDays = endTime.diff(startTime, 'days') + 1;
    if (diffDays <= 0) {
        document.getElementById("alertDays").innerHTML = "<img src=\"../img/alert_36.png\" width=\"15px\" height=\"15px\">";
        days.innerHTML = "結束日期要在開始日期之後唷！！！";
    } else {
        document.getElementById("alertDays").innerHTML = "";
        var estimateFee = diffDays * 10;
        days.innerHTML = "刊登日: " + diffDays.toString() + " 天 預估費用: " + estimateFee.toString() + " 元";
        $('#feeCount').val(estimateFee);
    }
}
</script>

<body>
    <div class="pageHeader">
        <a href="menu"><img class="pageLogo" src="../../img/50_MULTI_b.png"></a>
        <button class="pageHeaderButton" type="button" onclick="javascript:location.href='../../index.jsp'">登出系統</button>
        <div class="welcomeMessage">
            <font>$userName，歡迎來到50廣告公司</font>
        </div>
    </div>
    <p class="title">刊登廣告</p>
    <div class="addNewAd">
        <div class="uploadImage">
            <form id="uploadImage" action="javascript:uploadImage();" enctype="multipart/form-data">
                選擇圖片:
                <input type="file" name="fileToUpload" id="fileToUpload" accept="image/jpg, image/jpeg, image/png" required>
                <input type="submit" value="上傳圖片" name="submit">
            </form>
            <br>
            <div class="progress">
                <div class="bar"></div>
                <div class="percent">0%</div>
            </div>
        </div>
        <br>
        <div class="createAd">
            <form class="createAd" id="createAd" action="doAddAdvertise" method="POST">
                <input id="adOwnerToken" type="hidden" name="adOwnerToken" value="$userToken" readonly required>
                <input id="ImageRef" type="hidden" name="adImageLink" value="" readonly required>
                <input id="feeCount" type="hidden" name="feeCount" value="" readonly required>
                <input id="ImageWidth" type="hidden" name="adImageWidth" value="" readonly required>
                <input id="ImageHeight" type="hidden" name="adImageHeight" value="" readonly required> 圖片連結:
                <font id="ImageRefValue">尚未上傳圖片</font>
                <br>
                <br> 開始日期:
                <input class="inputForm" id="startTime" type="date" name="adStartDate" value="" min="$minDate" required>
                <br>
                <br> 結束日期:
                <input class="inputForm" id="endTime" type="date" name="adEndDate" value="" required> <span id="alertDays"></span>
                <br>
                <font class="days" color="gray"><span id="days">刊登日:</span></font>
                <br>
                <script>
                $(document).ready(
                    function() {
                        getExpectDays();
                        var startTimeObj = document
                            .getElementById("startTime");
                        var endTimeObj = document
                            .getElementById("endTime");
                        startTimeObj.onchange = getExpectDays;
                        endTimeObj.onchange = getExpectDays;
                    });
                </script>
                <br> 廣告標題:
                <input class="inputForm" type="text" name="adTitle" value="" required>
                <br>
                <br> 廣告連結:
                <input class="inputForm" type="text" name="adRef" value="" required>
                <br>
                <br> 廣告文字:
                <textarea class="adDescription" name="adDes" form="createAd"></textarea>
                <br>
                <input type="submit" value="送出申請" name="submit">
            </form>
        </div>
    </div>
</body>

</html>
