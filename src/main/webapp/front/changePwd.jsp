<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
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
        }
    </script>

</head>
<body id="invest_content">
<div class="ctn-960 mg shadow-5">
    <form action="#" class="js-form-validate" method="post" onSubmit="return false" data-arg-one="#" data-arg-two="100">
        <div id="main" class="confirm-info-list mgt clearfix" style="position: relative; margin:0 auto;">
            <div style="margin-top: 10px;margin-left: 90px">
                <label style="font-size:17px;color: rgba(14,7,17,0.67);">旧密码</label>
                <input id="oldPwd" type="number" style="height: 20px;width: 150px;margin-left: 30px">
            </div>
            <div style="margin-top: 15px;margin-left: 90px">
                <label style="font-size:17px;color: rgba(14,7,17,0.67);">新密码</label>
                <input id="newPwd" type="number" style="height: 20px;width: 150px;margin-left: 30px">
            </div>
            <div class="invest-btn-line" style="margin-top: 15px;margin-left: 80px">
                <input id="confirm-btn" type="button" value="确认" class="i-p-c-i-btn clearButton"
                       onclick="submit()">
            </div>
        </div>
    </form>
</div>

<script>
    function submit() {
        alert("修改成功");
    }
</script>

</body>
</html>