<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Luxe &mdash; 100% Free Fully Responsive HTML5 Template </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FREEHTML5"/>
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive"/>


    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content=""/>
    <meta name="twitter:image" content=""/>
    <meta name="twitter:url" content=""/>
    <meta name="twitter:card" content=""/>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">
    <!-- <link href='https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700italic,900,700,900italic' rel='stylesheet' type='text/css'> -->

    <!-- Stylesheets -->
    <!-- Dropdown Menu -->
    <link rel="stylesheet" href="css/superfish.css">
    <!-- Owl Slider -->
    <!-- <link rel="stylesheet" href="css/owl.carousel.css"> -->
    <!-- <link rel="stylesheet" href="css/owl.theme.default.min.css"> -->
    <!-- Date Picker -->
    <link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
    <!-- CS Select -->
    <link rel="stylesheet" href="css/cs-select.css">
    <link rel="stylesheet" href="css/cs-skin-border.css">

    <!-- Bootstrap Core CSS -->
    <link href="../back/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../back/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Social Buttons CSS -->
    <link href="../back/vendor/bootstrap-social/bootstrap-social.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../back/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../back/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Themify Icons -->
    <link rel="stylesheet" href="css/themify-icons.css">
    <!-- Flat Icon -->
    <link rel="stylesheet" href="css/flaticon.css">
    <!-- Icomoon -->
    <link rel="stylesheet" href="css/icomoon.css">
    <!-- Flexslider  -->
    <link rel="stylesheet" href="css/flexslider.css">

    <!-- Style -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Modernizr JS -->
    <script src="js/modernizr-2.6.2.min.js"></script>

    <script src="layer/jquery.min.js"></script>
    <script src="layer/layer.js"></script>

    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="js/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
        window.onload = function () {
            loadProfile();
            loadOrders();
            loadOrderLogs();
            loadConsumeLogs();
            loadPointLogs();

            <%
            HttpSession s = request.getSession();
            Long account = (Long)s.getAttribute("account");
            %>
        }
    </script>

</head>
<body>
<div id="fh5co-wrapper">
    <div id="fh5co-page">
        <div id="fh5co-header">
            <header id="fh5co-header-section">
                <div class="container">
                    <div class="nav-header">
                        <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
                        <h1 id="fh5co-logo"><a href="homepage.jsp">Luxe</a></h1>
                        <nav id="fh5co-menu-wrap" role="navigation">
                            <ul class="sf-menu" id="fh5co-primary-menu">
                                <li><a href="homepage.jsp">主页</a></li>
                                <li>
                                    <a href="hotel.html" class="fh5co-sub-ddown">酒店</a>
                                    <ul class="fh5co-sub-menu">
                                        <li><a href="#">Luxe Hotel</a></li>
                                        <li><a href="#">Deluxe Hotel</a></li>
                                        <li>
                                            <a href="#" class="fh5co-sub-ddown">King Hotel</a>
                                            <ul class="fh5co-sub-menu">
                                                <li><a href="#/preview/?item=build-free-html5-bootstrap-template"
                                                       target="_blank">Build</a></li>
                                                <li><a href="#/preview/?item=work-free-html5-template-bootstrap"
                                                       target="_blank">Work</a></li>
                                                <li><a href="#/preview/?item=light-free-html5-template-bootstrap"
                                                       target="_blank">Light</a></li>
                                                <li><a href="#/preview/?item=relic-free-html5-template-using-bootstrap"
                                                       target="_blank">Relic</a></li>
                                                <li>
                                                    <a href="#/preview/?item=display-free-html5-template-using-bootstrap"
                                                       target="_blank">Display</a></li>
                                                <li><a href="#/preview/?item=sprint-free-html5-template-bootstrap"
                                                       target="_blank">Sprint</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="#">Five Star Hotel</a></li>
                                    </ul>
                                </li>
                                <li><a href="services.html">服务</a></li>
                                <li><a href="blog.html">博客</a></li>
                                <li><a class="active" href="profile.jsp"><%=account%>
                                </a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>

        </div>
        <!-- end:fh5co-header -->
        <div class="fh5co-parallax" style="background-image: url(images/slider1.jpg);"
             data-stellar-background-ratio="0.5">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center fh5co-table">
                        <div class="fh5co-intro fh5co-table-cell">
                            <h1 class="text-center">Welcome</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="fh5co-contact-section">
            <div align="center">
                <p>
                    <button type="button" class="btn btn-outline btn-warning">个人信息</button>
                    <button type="button" class="btn btn-outline btn-warning">预定信息</button>
                    <button type="button" class="btn btn-outline btn-warning">预定记录</button>
                    <button type="button" class="btn btn-outline btn-warning">余额变动</button>
                    <button type="button" class="btn btn-outline btn-warning">积分变动</button>
                </p>
            </div>

            <div class="row" id="main">
                <div class="col-lg-offset-3 col-md-6">
                    <div class="col-md-12">
                        <h3>个人信息</h3>
                    </div>
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>会员ID</label>
                                    <input type="text" id="memberId" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>会员姓名</label>
                                    <input type="text" id="memberName" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>会员身份证号</label>
                                    <input type="text" id="id" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label>会员到期时间</label>
                                    <input type="text" id="expiredTime" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>会员状态</label>
                                    <input type="text" id="state" class="form-control" value="可使用"
                                           disabled="true">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>会员等级</label>
                                    <input type="text" id="level" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>累计已消费金额/元</label>
                                    <input type="text" id="consumedMoney" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>会员卡余额/元</label>
                                    <input type="text" id="money" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>会员卡积分/分</label>
                                    <input type="text" id="point" class="form-control" disabled="true">
                                </div>
                            </div>
                            <div class="col-lg-offset-1 col-md-3">
                                <div class="form-group">
                                    <button class="btn btn-primary" onclick="stopMember()">停用</button>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <button class="btn btn-primary" onclick="openPwdWindow()">修改密码</button>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <button class="btn btn-primary" onclick="openPointWindow()">积分兑换</button>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <button class="btn btn-primary" onclick="openTopUpWindow()">充值</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" id="order">
                <div class="col-lg-offset-1 col-md-10">
                    <div class="col-md-12">
                        <h3>预定信息</h3>
                    </div>
                    <div class="col-md-12">

                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>订单编号</th>
                                    <th>预定时间</th>
                                    <th>预定酒店</th>
                                    <th>预定房间号</th>
                                    <th>房间类型</th>
                                    <th>花费金额/元</th>
                                    <th>入住时间</th>
                                    <th>离店时间</th>
                                    <th>是否取消</th>
                                    <th>是否入住</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody id="ordersBody">
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

            <div class="row" id="orderLog">
                <div class="col-lg-offset-3 col-md-6">
                    <div class="col-md-12">
                        <h3>预定记录</h3>
                    </div>
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>操作时间</th>
                                    <th>操作名称</th>
                                    <th>内容</th>
                                </tr>
                                </thead>
                                <tbody id="orderLogBody">
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

            <div class="row" id="consumeLog">
                <div class="col-lg-offset-3 col-md-6">
                    <div class="col-md-12">
                        <h3>余额变动</h3>
                    </div>
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>变动时间</th>
                                    <th>变动金额/元</th>
                                    <th>变动原因</th>
                                </tr>
                                </thead>
                                <tbody id="consumeLogBody">
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>

            <div class="row" id="pointLog">
                <div class="col-lg-offset-3 col-md-6">
                    <div class="col-md-12">
                        <h3>积分变动</h3>
                    </div>
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>变动时间</th>
                                    <th>变动积分/分</th>
                                    <th>变动原因</th>
                                </tr>
                                </thead>
                                <tbody id="pointLogBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


            <footer id="footer" class="fh5co-bg-color">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="copyright">
                                <p>
                                    <small>&copy; 2016 Free HTML5 Template. <br> All Rights Reserved. <br>
                                        More Templates <a href="http://www.cssmoban.com/" target="_blank"
                                                          title="模板之家">模板之家</a> - Collect from <a
                                                href="http://www.cssmoban.com/" title="网页模板"
                                                target="_blank">网页模板</a> <br>
                                        Demo Images: <a href="#" target="_blank">Unsplash</a></small>
                                </p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-3">
                                    <h3>Company</h3>
                                    <ul class="link">
                                        <li><a href="#">About Us</a></li>
                                        <li><a href="#">Hotels</a></li>
                                        <li><a href="#">Customer Care</a></li>
                                        <li><a href="#">Contact Us</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3">
                                    <h3>Our Facilities</h3>
                                    <ul class="link">
                                        <li><a href="#">Resturant</a></li>
                                        <li><a href="#">Bars</a></li>
                                        <li><a href="#">Pick-up</a></li>
                                        <li><a href="#">Swimming Pool</a></li>
                                        <li><a href="#">Spa</a></li>
                                        <li><a href="#">Gym</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-6">
                                    <h3>Subscribe</h3>
                                    <p>Sed cursus ut nibh in semper. Mauris varius et magna in fermentum. </p>
                                    <form action="#" id="form-subscribe">
                                        <div class="form-field">
                                            <input type="email" placeholder="Email Address" id="email">
                                            <input type="submit" id="submit" value="Send">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <ul class="social-icons">
                                <li>
                                    <a href="#"><i class="icon-twitter-with-circle"></i></a>
                                    <a href="#"><i class="icon-facebook-with-circle"></i></a>
                                    <a href="#"><i class="icon-instagram-with-circle"></i></a>
                                    <a href="#"><i class="icon-linkedin-with-circle"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>

        </div>
        <!-- END fh5co-page -->

    </div>
</div>
<!-- END fh5co-wrapper -->

<!-- Javascripts -->
<script src="js/jquery-2.1.4.min.js"></script>
<!-- Dropdown Menu -->
<script src="js/hoverIntent.js"></script>
<script src="js/superfish.js"></script>
<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="js/jquery.waypoints.min.js"></script>
<!-- Counters -->
<script src="js/jquery.countTo.js"></script>
<!-- Stellar Parallax -->
<script src="js/jquery.stellar.min.js"></script>
<!-- Owl Slider -->
<!-- // <script src="js/owl.carousel.min.js"></script> -->
<!-- Date Picker -->
<script src="js/bootstrap-datepicker.min.js"></script>
<!-- CS Select -->
<script src="js/classie.js"></script>
<script src="js/selectFx.js"></script>
<!-- Flexslider -->
<script src="js/jquery.flexslider-min.js"></script>

<script src="js/custom.js"></script>

<script>
    function openPwdWindow() {
        layer.open({
            type: 2,
//            skin: 'layui-layer-lan',
            title: '修改密码',
            fix: true,
            shadeClose: true,
            maxmin: true,
            area: ['400px', '220px'],
            content: 'changePwd.jsp'
        })
    }

    function openPointWindow() {
        layer.open({
            type: 2,
//            skin: 'layui-layer-lan',
            title: '积分兑换',
            fix: true,
            shadeClose: true,
            maxmin: true,
            area: ['400px', '220px'],
            content: 'point.jsp'
        })
    }

    function openTopUpWindow() {
        layer.open({
            type: 2,
//            skin: 'layui-layer-lan',
            title: '充值',
            fix: true,
            shadeClose: true,
            maxmin: true,
            area: ['400px', '360px'],
            content: 'topUp.jsp'
        })
    }

    function loadProfile() {
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
                    window.document.getElementById("memberId").value = jsonData.data.memberId;
                    window.document.getElementById("memberName").value = jsonData.data.memberName;
                    window.document.getElementById("id").value = jsonData.data.id;
                    window.document.getElementById("state").value = jsonData.data.state;
                    window.document.getElementById("money").value = jsonData.data.money;
                    window.document.getElementById("point").value = jsonData.data.point;
                    window.document.getElementById("consumedMoney").value = jsonData.data.consumedMoney;
                    window.document.getElementById("level").value = jsonData.data.level;
                    window.document.getElementById("expiredTime").value = jsonData.data.expiredTime;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function loadOrders() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/front/order.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var tbody = window.document.getElementById("ordersBody");
                    var str = "";
                    for (var i = 0; i < jsonData.data.orders.length; i++) {
                        var order = jsonData.data.orders[i];
                        str += "<tr>" +
                            "<td>" + order.id + "</td>" +
                            "<td>" + order.orderDate + "</td>" +
                            "<td>" + order.hostelName + "</td>" +
                            "<td>" + order.roomNum + "</td>" +
                            "<td>" + order.roomType + "</td>" +
                            "<td>" + order.price + "</td>" +
                            "<td>" + order.inDate + "</td>" +
                            "<td>" + order.outDate + "</td>" +
                            "<td>" + order.canceled + "</td>" +
                            "<td>" + order.checkedIn + "</td>";
                        if (order.canBeCanceled) {
                            str += "<td> <input type=\"button\" value=\"取消预定\" onClick=\"cancel(+" + order.id + ")\"> </td>";
                        }
                        else {
                            str += "<td> </td>";
                        }
                        str += "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function cancel(id) {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/order/cancel.do",
            data: {orderId: id},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    alert("取消成功");
                    window.location.href = "/front/profile.jsp";
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function loadOrderLogs() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/log/orderLog.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var tbody = window.document.getElementById("orderLogBody");
                    var str = "";
                    for (var i = 0; i < jsonData.data.logs.length; i++) {
                        var log = jsonData.data.logs[i];
                        str += "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + log.time + "</td>" +
                            "<td>" + log.name + "</td>" +
                            "<td>" + log.content + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function loadConsumeLogs() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/log/consumeLog.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var tbody = window.document.getElementById("consumeLogBody");
                    var str = "";
                    for (var i = 0; i < jsonData.data.logs.length; i++) {
                        var log = jsonData.data.logs[i];
                        str += "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + log.time + "</td>" +
                            "<td>" + log.money + "</td>" +
                            "<td>" + log.type + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function loadPointLogs() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/log/pointLog.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var tbody = window.document.getElementById("pointLogBody");
                    var str = "";
                    for (var i = 0; i < jsonData.data.logs.length; i++) {
                        var log = jsonData.data.logs[i];
                        str += "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + log.time + "</td>" +
                            "<td>" + log.point + "</td>" +
                            "<td>" + log.type + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function stopMember() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/front/member/stop.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    alert("会员卡已停用");
                    window.location.href = "/front/homepage.jsp";
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }
</script>

</body>
</html>