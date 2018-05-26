<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
    <!-- Custom Theme files -->
    <link href="css/login-style.css" rel="stylesheet" type="text/css"
          media="all"/>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <!--Google Fonts-->
    <%--<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400'--%>
    <%--rel='stylesheet' type='text/css'>--%>
    <%--<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet'--%>
    <%--type='text/css'>--%>
    <!--Google Fonts-->
</head>
<body>
<div class="login">
    <div class="login-top">
        <h1><a href="homepage.jsp">Hostel World</a></h1>
        <form role="form" id="registerForm">
            <label for="memberName">会员姓名</label>
            <input type="text" name="memberName" id="memberName">
            <label for="memberId">会员身份证号</label>
            <input type="text" name="memberId" id="memberId">
            <label for="memberPwd">会员卡密码</label>
            <input type="password" name="memberPwd" id="memberPwd">
            <label for="cardId">银行卡号</label>
            <input type="text" name="cardId" id="cardId">
            <label for="name">持卡人姓名</label>
            <input type="text" name="name" id="name">
            <label for="id">持卡人身份证号</label>
            <input type="text" name="id" id="id">
            <label for="cardPwd">银行卡密码</label>
            <input type="password" name="cardPwd" id="cardPwd">
        </form>
        <div class="forgot">
            <input type="submit" value="提交" onclick="submitForm()">
        </div>
    </div>
    <div class="login-bottom">
        <h3>已是会员 &nbsp;<a href="login.jsp">登录</a>&nbsp </h3>
    </div>
</div>

<script type="text/javascript">
    function submitForm() {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/member/register.do",
            data: $("#registerForm").serialize(),
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("注册成功\n你的会员识别码是" + jsonData.data.memberId);
                    window.location.href = "/front/login.jsp";
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