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

    <title>HostelWorld Admin</title>


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

    <%-- window.onload事件会在页面加载完成后触发 --%>
    <script type="text/javascript">
        window.onload = function () {
            loadOrders();
            loadRecords();
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

    <%-- 以下是非公共部分 --%>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">操作记录</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        订单记录
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>订单编号</th>
                                    <th>预定时间</th>
                                    <th>预定房间号</th>
                                    <th>房间类型</th>
                                    <th>花费金额/元</th>
                                    <th>入住时间</th>
                                    <th>离店时间</th>
                                    <th>是否取消</th>
                                    <th>是否入住</th>
                                </tr>
                                </thead>
                                <tbody id="orderBody">
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        入住记录
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>入住房间号</th>
                                    <th>房间类型</th>
                                    <th>入住时间</th>
                                    <th>离店时间</th>
                                </tr>
                                </thead>
                                <tbody id="checkInBody">
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
        </div>

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

<!-- DataTables JavaScript -->
<script src="<%=basePath%>/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="<%=basePath%>/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>/dist/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function () {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>

<script>
    function loadOrders() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/back/order/all.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var tbody = window.document.getElementById("orderBody");
                    var str = "";
                    for (var i = 0; i < jsonData.data.orders.length; i++) {
                        var order = jsonData.data.orders[i];
                        str += "<tr>" +
                            "<td>" + order.id + "</td>" +
                            "<td>" + order.orderDate + "</td>" +
                            "<td>" + order.roomNum + "</td>" +
                            "<td>" + order.roomType + "</td>" +
                            "<td>" + order.price + "</td>" +
                            "<td>" + order.inDate + "</td>" +
                            "<td>" + order.outDate + "</td>" +
                            "<td>" + order.canceled + "</td>" +
                            "<td>" + order.checkedIn + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function loadRecords() {
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/back/hostel/checkInRecord.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var tbody = window.document.getElementById("checkInBody");
                    var str = "";
                    for (var i = 0; i < jsonData.data.records.length; i++) {
                        var record = jsonData.data.records[i];
                        str += "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + record.roomNum + "</td>" +
                            "<td>" + record.type + "</td>" +
                            "<td>" + record.inDate + "</td>" +
                            "<td>" + record.outDate + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }
</script>

</div>
</body>

</html>
