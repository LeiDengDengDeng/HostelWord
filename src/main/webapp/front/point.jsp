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
            loadPoint();
        }
    </script>

</head>
<body id="invest_content">
<div class="ctn-960 mg shadow-5">
    <form action="#" class="js-form-validate" method="post" onSubmit="return false" data-arg-one="#" data-arg-two="100">
        <div id="main" class="confirm-info-list mgt clearfix" style="position: relative; margin:0 auto;">
            <div style="margin-top: 10px;margin-left: 90px">
                <label style="font-size:17px;color: rgba(14,7,17,0.67);">剩余积分/分</label>
                <label id="remainingPoint" style="font-size:15px;color: rgba(14,7,17,0.67);margin-left: 30px"></label>
            </div>
            <div style="margin-top: 15px;margin-left: 90px">
                <label style="font-size:17px;color: rgba(14,7,17,0.67);">兑换数量/分</label>
                <input id="point" type="number" style="height: 20px;width: 70px;margin-left: 30px">
            </div>
            <div class="invest-btn-line" style="margin-top: 15px;margin-left: 80px">
                <input id="confirm-btn" type="button" value="兑换" class="i-p-c-i-btn clearButton"
                       onclick="exchange()">
            </div>
        </div>
    </form>
</div>

<script>
    function loadPoint() {
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
                    window.document.getElementById("remainingPoint").innerHTML = jsonData.data.point;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function exchange() {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/member/point/exchange.do",
            data: {point: window.document.getElementById("point").value},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("兑换成功");
                    window.location.reload(true);
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }
</script>

</body>
</html>