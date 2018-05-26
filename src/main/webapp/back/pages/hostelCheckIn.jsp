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
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="homepage.jsp">HostelWorld Admin</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages">
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>Read All Messages</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-messages -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                        <!-- /input-group -->
                    </li>
                    <li>
                        <a href="hostelProfile.jsp"><i class="fa fa-dashboard fa-fw"></i> 酒店信息</a>
                    </li>
                    <li>
                        <a href="hostelCheckIn.jsp"><i class="fa fa-table fa-fw"></i> 入住登记</a>
                    </li>
                    <li>
                        <a href="hostelSchedule.jsp"><i class="fa fa-edit fa-fw"></i> 发布计划</a>
                    </li>
                    <li>
                        <a href="hostelRoomCondition.jsp"><i class="fa fa-book fa-fw"></i> 查看房间</a>
                    </li>
                    <li>
                        <a href="hostelRecord.jsp"><i class="fa fa-clock-o fa-fw"></i> 预定/入住记录</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">入住登记</h1>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                入住信息
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-offset-3 col-lg-6">
                                        <form role="form" id="checkInForm">
                                            <div class="form-group">
                                                <label class="col-lg-12">订单编号</label>
                                                <input name="orderId" id="orderId" class="form-control"
                                                       placeholder="订单编号">
                                                <button type="button" class="btn btn-default"
                                                        style="margin-top:20px;margin-left:480px;"
                                                        onclick="loadOrder()">
                                                    查询
                                                </button>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-lg-12">会员ID</label>
                                                <input name="memberId" id="memberId" class="form-control"
                                                       placeholder="会员ID">
                                            </div>

                                            <div class="col-lg-6">
                                                <label>入住时间</label>
                                                <input name="inDate" id="inDate" type="date" style="margin-left:10px;">
                                            </div>
                                            <div class="col-lg-6">
                                                <label>离店时间</label>
                                                <input name="outDate" id="outDate" type="date"
                                                       style="margin-left:10px;">
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-12" style="margin-top:10px;">房间类型</label>
                                                <select name="roomType" id="roomType" class="col-lg-12"
                                                        style="margin-top:5px;height:30px">
                                                    <option>标准单人间</option>
                                                    <option>标准双人间</option>
                                                    <option>套房</option>
                                                    <option>总统套房</option>
                                                </select>
                                                <button type="button" class="btn btn-default"
                                                        style="margin-top:20px;margin-left:480px;"
                                                        onclick="getRoomNum()">
                                                    查询
                                                </button>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-12">房间号</label>
                                                <input name="roomNum" id="roomNum" class="form-control"
                                                       placeholder="房间号">
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-12">房间价格/元</label>
                                                <input name="price" id="roomPrice" class="form-control"
                                                       placeholder="房间价格">
                                            </div>

                                            <br>

                                            <label class="col-lg-12">入住人信息</label>
                                            <div class="form-group" id="visitor">
                                                <input name="name0" class="form-control" placeholder="姓名">
                                                <input name="id0" class="form-control" placeholder="身份证">
                                            </div>

                                            <button type="button" class="btn btn-default" style="margin-left:355px"
                                                    onclick="addVisitor()">
                                                添加入住人
                                            </button>
                                            <button type="button" class="btn btn-default" style="margin-left: 20px"
                                                    onclick="submitCheckInForm()">提交
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <!-- /.row (nested) -->
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<%=basePath%>/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>/dist/js/sb-admin-2.js"></script>

<script>
    var count = 1;
    function addVisitor() {
        var visitor = window.document.getElementById("visitor");
        var name = window.document.createElement("input");
        name.setAttribute("class", "form-control");
        name.setAttribute("placeholder", "姓名");
        name.setAttribute("name", "name" + count);
        var id = window.document.createElement("input");
        id.setAttribute("class", "form-control");
        id.setAttribute("placeholder", "身份证");
        id.setAttribute("name", "id" + count);
        count++;
        visitor.appendChild(name);
        visitor.appendChild(id);
    }

    function loadOrder() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/back/order.do",
            data: {
                orderId: window.document.getElementById("orderId").value
            },
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    window.document.getElementById("memberId").value = jsonData.data.memberId;
                    window.document.getElementById("inDate").value = jsonData.data.inDate;
                    window.document.getElementById("outDate").value = jsonData.data.outDate;
                    window.document.getElementById("roomNum").value = jsonData.data.roomNum;
                    window.document.getElementById("roomPrice").value = jsonData.data.price;
                    window.document.getElementById("roomType").value = jsonData.data.roomType;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function getRoomNum() {
        var memberId = window.document.getElementById("memberId").value;
        if (memberId == "") memberId = -1;

        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/back/hostel/check.do",
            data: {
                memberId: memberId,
                typeStr: window.document.getElementById("roomType").value,
                inDate: window.document.getElementById("inDate").value,
                outDate: window.document.getElementById("outDate").value
            },
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    window.document.getElementById("roomNum").value = jsonData.data.roomNum;
                    window.document.getElementById("roomPrice").value = jsonData.data.price;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function submitCheckInForm() {
        var data = $("#checkInForm").serialize();
        data = decodeURIComponent(data, true);
        data = data.replace(/&/g, "\",\"");
        data = data.replace(/=/g, "\":\"");
        data = "{\"" + data + "\"}";

        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/back/hostel/checkIn.do",
            data: {jsonStr: data},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("入住成功");
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }


</script>

</body>

</html>
