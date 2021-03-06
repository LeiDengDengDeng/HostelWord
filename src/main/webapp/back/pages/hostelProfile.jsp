<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HostelWorld Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
        window.onload = function () {
            loadProfile();
        }
    </script>

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
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
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

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">酒店信息</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        基本信息
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form role="form" id="form">
                                    <div class="form-group">
                                        <label>酒店识别码</label>
                                        <input class="form-control" id="hostelId" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>酒店名</label>
                                        <input class="form-control" id="name" name="name">
                                        <%--<p class="help-block">Example block-level help text here.</p>--%>
                                    </div>
                                    <div class="form-group">
                                        <label>省份</label>
                                        <input class="form-control" id="province" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>市区</label>
                                        <input class="form-control" id="city" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>详细地址</label>
                                        <input class="form-control" id="address" name="address">
                                    </div>
                                    <div class="form-group">
                                        <label>酒店状态</label>
                                        <select class="form-control" id="state">
                                            <option>运营中</option>
                                            <option>已停运</option>
                                        </select>
                                    </div>
                                    <button type="button" class="btn btn-default" onclick="formSubmit()">提交</button>
                                </form>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        财务信息
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form role="form" id="financeForm">
                                    <div class="form-group">
                                        <label>累计已分资金/元</label>
                                        <input class="form-control" id="money" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>本阶段赚取资金/元</label>
                                        <input class="form-control" id="earnMoney" name="name" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>累计已赚资金/元</label>
                                        <input class="form-control" id="totalEarnMoney" disabled>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>

        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <script>
        function loadProfile() {
            $.ajax({
                type: "get",
                datatype: "json",
                url: "/back/hostel.do",
                data: {},
                error: function () {
                    alert("查询失败")
                },
                success: function (data) {
                    var jsonData = $.parseJSON(data);
                    if (jsonData.success) {
                        var hostelId = window.document.getElementById("hostelId");
                        hostelId.value = jsonData.data.id;
                        var name = window.document.getElementById("name");
                        name.value = jsonData.data.name;
                        var city = window.document.getElementById("city");
                        city.value = jsonData.data.city;
                        var province = window.document.getElementById("province");
                        province.value = jsonData.data.province;
                        var address = window.document.getElementById("address");
                        address.value = jsonData.data.address;
                        var state = window.document.getElementById("state");
                        address.selected = jsonData.data.state;

                        window.document.getElementById("money").value = jsonData.data.money;
                        window.document.getElementById("earnMoney").value = jsonData.data.earnMoney;
                        window.document.getElementById("totalEarnMoney").value = jsonData.data.totalEarnMoney;
                    }
                }
            })
        }
        function formSubmit() {
            $.ajax({
                type: "POST",
                datatype: "json",
                url: "/back/hostel/modify.do",
                data: $("#form").serialize(),
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    var jsonData = $.parseJSON(data);  //json数据
                    if (jsonData.success) {
                        alert("修改成功!");
                    }
                }
            })
        }

    </script>

</div>
</body>

</html>
