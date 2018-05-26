<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
    <!-- Custom Theme files -->
    <link href="css/login-style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <!--Google Fonts-->
    <!--<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400'-->
    <!--rel='stylesheet' type='text/css'>-->
    <!--<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet'-->
    <!--type='text/css'>-->
    <!--Google Fonts-->
</head>
<body>
<div class="login">
    <div class="login-top">
        <h1><a href="homepage.jsp">Hostel World</a></h1>
        <form id="loginForm">
            <input type="text" name="memberId" value="会员识别码" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = '会员识别码';}">
            <input type="password" name="password" value="password" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'password';}">
        </form>
        <div class="forgot">
            <a href="#">忘记密码?</a>
            <input type="submit" value="登录" onclick="submitForm()">
        </div>
    </div>
    <div class="login-bottom">
        <h3>新会员 &nbsp;<a href="register.jsp">注册</a>&nbsp </h3>
    </div>
</div>

<script type="text/javascript">
    function submitForm() {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/member/login.do",
            data: $("#loginForm").serialize(),
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("登录成功");
                    window.location.href = "/front/homepage.jsp";
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