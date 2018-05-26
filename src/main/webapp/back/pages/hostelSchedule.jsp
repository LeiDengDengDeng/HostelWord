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
            loadBasicSchedule();
            loadSpecialchedule();
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
                <h1 class="page-header">发布酒店计划</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading" id="year">
                        酒店基本计划
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <table width="100%" class="table table-striped table-bordered table-hover"
                               id="basicSchedule">
                            <thead>
                            <tr>
                                <th contenteditable="false">房间类型</th>
                                <th>房间正常价格/元/天</th>
                                <th>房间VIP1价格/元/天</th>
                                <th>房间VIP2价格/元/天</th>
                                <th>房间VIP3价格/元/天</th>
                            </tr>
                            </thead>
                            <tbody contenteditable="true" id="basicScheduleBody">
                            <%--<tr class="odd gradeX">--%>
                            <%--<td>标准单人间</td>--%>
                            <%--<td>199</td>--%>
                            <%--<td>188</td>--%>
                            <%--</tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <input type="button" value="提交" style="position: absolute;right: 40px;"
                       onclick="submitBasicSchedules()">
            </div>
        </div>

        <br><br>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        酒店特殊计划
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <input type="button" value="添加" onClick="addRow();" style="margin-bottom: 10px">
                        <table width="100%" class="table table-striped table-bordered table-hover"
                               id="specialSchedule">
                            <thead>
                            <tr>
                                <th>房间类型</th>
                                <th>房间正常价格/元/天</th>
                                <th>房间VIP1价格/元/天</th>
                                <th>房间VIP2价格/元/天</th>
                                <th>房间VIP3价格/元/天</th>
                                <th>时间</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody contenteditable="true" id="specialScheduleBody">
                            </tbody>
                        </table>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <input type="button" value="提交" style="position: absolute;right: 40px;"
                       onclick="submitSpecialSchedules()">
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
    function addRow() {
        var oTable = document.getElementById("specialSchedule");
        var tBodies = oTable.tBodies;
        var tBody = tBodies[0];
        var tr = tBody.insertRow(tBody.rows.length);
        var td_0 = tr.insertCell(0);
        td_0.innerHTML = "<select class=\"form-control\"> <option>标准单人间<\/option> <option>标准双人间<\/option> " +
            "<option>套房<\/option> <option>总统套房<\/option> <\/select>";
        tr.insertCell(1);
        tr.insertCell(2);
        tr.insertCell(3);
        tr.insertCell(4);
        var td_5 = tr.insertCell(5);
        td_5.innerHTML = "<td> <input type=\"date\"> </td>"
        var td_6 = tr.insertCell(6);
        td_6.innerHTML = "<input type='submit' value='删除' onClick='delRow(this);'>";
    }

    function delRow(r) {
        var i = r.parentNode.parentNode.rowIndex;
        var tbody = window.document.getElementById("specialScheduleBody");
        var trs = tbody.getElementsByTagName("tr");
        var tds = trs[i - 1].getElementsByTagName("td");

        $.ajax({
            type: "post",
            datatype: "json",
            url: "/back/schedule/specialSchedule/delete.do",
            data: {
                roomType: tds[0].innerHTML,
                dateStr: tds[5].getElementsByTagName("input")[0].value
            },
            error: function () {
                alert("查询失败")
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                }
            }
        })

        document.getElementById('specialSchedule').deleteRow(i);
    }

    function loadBasicSchedule() {
        var tbody = window.document.getElementById("basicScheduleBody");

        $.ajax({
            type: "get",
            datatype: "json",
            url: "/back/schedule/basicSchedule.do",
            data: {},
            error: function () {
                alert("查询失败")
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    window.document.getElementById("year").innerHTML = "酒店基本计划/" + jsonData.data.basicSchedules[0].year + "年";

                    var str = "";
                    for (var i = 0; i < jsonData.data.basicSchedules.length; i++) {
                        var schedule = jsonData.data.basicSchedules[i];
                        str += "<tr class=\"odd gradeX\">" +
                            "<td contenteditable='false'>" + schedule.roomType + "</td>" +
                            "<td>" + schedule.vip0Price + "</td>" +
                            "<td>" + schedule.vip1Price + "</td>" +
                            "<td>" + schedule.vip2Price + "</td>" +
                            "<td>" + schedule.vip3Price + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                }
            }
        })
    }

    function loadSpecialchedule() {
        var tbody = window.document.getElementById("specialScheduleBody");

        $.ajax({
            type: "get",
            datatype: "json",
            url: "/back/schedule/specialSchedule.do",
            data: {},
            error: function () {
                alert("查询失败")
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    var str = "";
                    for (var i = 0; i < jsonData.data.specialSchedules.length; i++) {
                        var schedule = jsonData.data.specialSchedules[i];
                        str += "<tr class=\"odd gradeX\">" +
                            "<td contenteditable='false'>" + schedule.roomType + "</td>" +
                            "<td>" + schedule.vip0Price + "</td>" +
                            "<td>" + schedule.vip1Price + "</td>" +
                            "<td>" + schedule.vip2Price + "</td>" +
                            "<td>" + schedule.vip3Price + "</td>" +
                            "<td> <input type=\"date\" value = " + schedule.date + " readonly=\"true\"> </td>" +
                            "<td> <input type=\"button\" value=\"删除\" onClick=\"delRow(this);\"> </td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function submitBasicSchedules() {
        var tbody = window.document.getElementById("basicScheduleBody");
        var trs = tbody.getElementsByTagName("tr");
        for (var i = 0; i < trs.length; i++) {
            var tds = trs[i].getElementsByTagName("td");

            $.ajax({
                type: "post",
                datatype: "json",
                url: "/back/schedule/basicSchedule/modify.do",
                data: {
                    roomType: tds[0].innerHTML,
                    vip0Price: tds[1].innerHTML,
                    vip1Price: tds[2].innerHTML,
                    vip2Price: tds[3].innerHTML,
                    vip3Price: tds[4].innerHTML
                },
                error: function () {
                    alert("连接错误");
                },
                success: function (data) {
                    var jsonData = $.parseJSON(data);
                    if (jsonData.success) {


                    }
                }
            })
        }
    }

    function submitSpecialSchedules() {
        var tbody = window.document.getElementById("specialScheduleBody");
        var trs = tbody.getElementsByTagName("tr");
        for (var i = 0; i < trs.length; i++) {
            var tds = trs[i].getElementsByTagName("td");

            var roomType;
            if (tds[0].innerHTML.indexOf("select") == -1) {
                roomType = tds[0].innerHTML;
            } else {
                roomType = tds[0].getElementsByClassName("form-control")[0].value;
            }

            $.ajax({
                type: "post",
                datatype: "json",
                url: "/back/schedule/specialSchedule/modify.do",
                data: {
                    roomType: roomType,
                    vip0Price: tds[1].innerHTML,
                    vip1Price: tds[2].innerHTML,
                    vip2Price: tds[3].innerHTML,
                    vip3Price: tds[4].innerHTML,
                    dateStr: tds[5].getElementsByTagName("input")[0].value
                },
                error: function () {
                    alert("连接错误");
                },
                success: function (data) {
                }
            })
        }
    }


</script>

</div>
</body>

</html>
