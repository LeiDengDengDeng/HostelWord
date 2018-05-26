<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>预定房间</title>
    <link rel="stylesheet" href="layer/css/min.css">
    <link rel="stylesheet" href="layer/css/min_123.css">
    <script type="text/javascript" src="layer/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.reginpblu-yqm').hide();
            $(".regerror2").toggle(function () {
                $(this).find("span").attr("class", "nle-sicon2")
                $(this).parent().find(".reginpblu-yqm").show();
            }, function () {
                $(this).find("span").attr("class", "nle-sicon")
                $(this).parent().find(".reginpblu-yqm").hide();
            })
        })

        window.onload = function () {
            loadMoney();
        }
    </script>

</head>
<body id="invest_content">
<div class="ctn-960 mg shadow-5">
    <form action="#" class="js-form-validate" method="post" onSubmit="return false" data-arg-one="#" data-arg-two="100">
        <div id="main" class="confirm-info-list mgt clearfix" style="position: relative; margin:0 auto;">
            <div style="margin-top: 10px;margin-left: 60px">
                <label>剩余资金/元</label>
                <label id="remainingMoney" style="margin-left: 30px"></label>
            </div>
            <div style="margin-top: 15px;margin-left: 60px">
                <label>充值金额/元</label>
                <input id="money" type="number" style="height: 18px;width: 160px;margin-left: 30px">
            </div>
            <div style="margin-top: 15px;margin-left: 60px">
                <label for="cardId">银行卡号</label>
                <input type="text" name="cardId" id="cardId" style="height: 18px;width: 160px;margin-left: 48px">
            </div>
            <div style="margin-top: 15px;margin-left: 60px">
                <label for="name">持卡人姓名</label>
                <input type="text" name="name" id="name" style="height: 18px;width: 160px;margin-left: 37px">
            </div>
            <div style="margin-top: 15px;margin-left: 60px">
                <label for="id">持卡人身份证号</label>
                <input type="text" name="id" id="id" style="height: 18px;width: 160px;margin-left: 11px">
            </div>
            <div style="margin-top: 15px;margin-left: 60px">
                <label for="cardPwd">银行卡密码</label>
                <input type="password" name="cardPwd" id="cardPwd"
                       style="height: 18px;width: 160px;margin-left: 38px">
            </div>
            <div class="invest-btn-line" style="margin-top: 15px;margin-left: 80px">
                <input id="confirm-btn" type="button" value="充值" class="i-p-c-i-btn clearButton"
                       onclick="topUp()">
            </div>
        </div>
    </form>
</div>

<script>
    function loadMoney() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/front/member/profile.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    window.document.getElementById("remainingMoney").innerHTML = jsonData.data.money;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function topUp() {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/member/topUp.do",
            data: {
                money: window.document.getElementById("money").value,
                cardId: window.document.getElementById("cardId").value,
                name: window.document.getElementById("name").value,
                id: window.document.getElementById("id").value,
                cardPwd: window.document.getElementById("cardPwd").value
            },
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("充值成功");
                    window.location.reload(true);
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }
</script>

<script src="js/jquery-2.1.4.min.js"></script>

</body>
</html>