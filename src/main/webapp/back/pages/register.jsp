<%@ page language="java" contentType="text/html; charset=GB18030"
         pageEncoding="GB18030" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <%
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/back/";
    %>

    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=basePath%>/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=basePath%>/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=basePath%>/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <%--<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">--%>
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand">HostelWorld Admin</a>
    </div>
    <!-- /.navbar-header -->
    <%--</nav>--%>

</div>
<!-- /#wrapper -->

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-3">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">酒店申请</h3>
                </div>
                <div class="panel-body">
                    <form role="form" method="post" id="registerForm" action="/back/hostel/register.do">
                        <fieldset>
                            <div class="form-group">
                                <label>密码</label>
                                <input class="form-control" name="password" type="password"
                                       value="">
                            </div>
                            <div class="form-group">
                                <label>酒店名</label>
                                <input name="name" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>省份</label>
                                <input name="province" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>市区</label>
                                <input name="city" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>详细地址</label>
                                <input name="address" class="form-control">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <div class="form-actions">
                                <button type="button" class="btn btn-lg btn-success btn-block"
                                        onclick="submitForm()">提交申请
                                </button>
                            </div>
                            <%--<a href="homepage.jsp" class="btn btn-lg btn-success btn-block">Login</a>--%>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<%=basePath%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>/dist/js/sb-admin-2.js"></script>

<script type="text/javascript">
    function submitForm() {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/back/hostel/register.do",
            data: $("#registerForm").serialize(),
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("注册成功\n你的酒店识别码是" + jsonData.data.id);
                    window.location.href = "/back/pages/login.jsp";
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }
</script>

</body>

</html>
