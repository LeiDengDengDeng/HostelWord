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
            loadHostels();
        }
    </script>

</head>
<body id="invest_content">
<div class="ctn-960 mg shadow-5">
    <form action="#" class="js-form-validate" method="post" onSubmit="return false" data-arg-one="#" data-arg-two="100">
        <div id="main" class="confirm-info-list mgt clearfix" style="position: relative; margin:0 auto;">
            <dl class="confirm-info-list-dl confirm-info-list-project">
                <dt class="tc1-title">
                <h2>选择房间</h2>
                </dt>
                <dd class="clearfix">
                    <div style="margin-top: 10px;margin-left: 15px">
                        <label style="font-size:15px;color: #000;">选择酒店</label>
                        <select style="margin-left: 40px;height: 22px;width: 130px;" id="hostelMenu">
                        </select>
                    </div>
                    <div style="margin-top: 15px;margin-left: 15px">
                        <label style="font-size:15px;color: #000;">选择房间类型</label>
                        <select id="roomType" style="margin-left: 11px;height: 22px;width: 130px;">
                            <option>标准单人间</option>
                            <option>标准双人间</option>
                            <option>套房</option>
                            <option>总统套房</option>
                        </select>
                    </div>
                    <div style="margin-top: 15px;margin-left: 15px">
                        <label style="font-size:15px;color: #000">入住时间</label>
                        <input id="inDate" type="date" style="margin-left: 40px">
                        <label style="font-size:15px;color: #000;margin-left: 30px">离店时间</label>
                        <input id="outDate" type="date" style="margin-left: 40px">
                    </div>
                    <div class="invest-btn-line" style="margin-top: 15px;">
                        <input tabindex="3" id="confirm-btn" type="button" value="查询" class="i-p-c-i-btn clearButton"
                               onclick="check()">
                        <span id="confirmERR" class="error"
                              style="vertical-align:top;color:#CB282D;padding-left:20px;line-height:45px"></span>
                    </div>
                </dd>
            </dl>
        </div>
    </form>
</div>

<script>
    var ids;
    var roomNum;
    var price;

    function loadHostels() {
        var hostelMenu = window.document.getElementById("hostelMenu");
        $.ajax({
            type: "GET",
            datatype: "json",
            url: "/front/hostel/running.do",
            data: {},
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    ids = new Array(jsonData.data.hostels.length);
                    for (var i = 0; i < jsonData.data.hostels.length; i++) {
                        var option = window.document.createElement("option");
                        option.setAttribute("value", jsonData.data.hostels[i].name);
                        option.innerHTML = jsonData.data.hostels[i].name;
                        hostelMenu.appendChild(option);

                        ids[i] = jsonData.data.hostels[i].id;
                    }
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function check() {
        <%
            HttpSession s = request.getSession();
            Long account = (Long)s.getAttribute("account");
            %>
        var account = <%=account%>;
        if (account == null) {
            alert("您还没有登录!");
            return;
        }

        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/hostel/check.do",
            data: {
                hostelId: ids[window.document.getElementById("hostelMenu").selectedIndex],
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
                    window.document.getElementById("confirm-btn").onclick = "";

                    var dl = window.document.createElement("dl");
                    dl.setAttribute("class", "confirm-info-list-dl confirm-info-list-project");
                    var dt = window.document.createElement("dt");
                    dt.setAttribute("class", "tc1-title");
                    var h2 = window.document.createElement("h2");
                    h2.innerHTML = "确认预定房间";

                    var dd = window.document.createElement("dd");
                    dd.setAttribute("class", "clearfix")

                    var ul1 = window.document.createElement("ul");
                    ul1.setAttribute("class", "list-style-1 list-style-1-first");

                    var li1 = window.document.createElement("li");
                    li1.innerHTML = "入住时间：" + window.document.getElementById("inDate").value;
                    var li2 = window.document.createElement("li");
                    li2.innerHTML = "离店时间：" + window.document.getElementById("outDate").value;
                    var li3 = window.document.createElement("li");
                    li3.innerHTML = "房间类型：" + window.document.getElementById("roomType").value;

                    var ul2 = window.document.createElement("ul");
                    ul2.setAttribute("class", "list-style-1 list-style-1-two");

                    var li4 = window.document.createElement("li");
                    li4.innerHTML = "房间价格：" + jsonData.data.price + "元";
                    price = jsonData.data.price;
                    var li5 = window.document.createElement("li");
                    li5.innerHTML = "房间号码：" + jsonData.data.roomNum;
                    roomNum = jsonData.data.roomNum;
                    var li6 = window.document.createElement("li");
                    li6.innerHTML = "酒店名称：" + window.document.getElementById("hostelMenu").value;

                    var button = window.document.createElement("input");
                    button.setAttribute("class", "i-p-c-i-btn clearButton");
                    button.setAttribute("type", "button");
                    button.setAttribute("value", "提交");
                    button.setAttribute("style", "margin:auto;");
                    button.onclick = submit;

                    ul1.appendChild(li1);
                    ul1.appendChild(li2);
                    ul1.appendChild(li3);
                    ul2.appendChild(li6);
                    ul2.appendChild(li4);
                    ul2.appendChild(li5);

                    dd.appendChild(ul1);
                    dd.appendChild(ul2);
                    dt.appendChild(h2);
                    dl.appendChild(dt);
                    dl.appendChild(dd);

                    var main = window.document.getElementById("main");
                    main.appendChild(dl);
                    main.appendChild(button);
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

    function submit() {
        $.ajax({
            type: "POST",
            datatype: "json",
            url: "/front/order/order.do",
            data: {
                hostelId: ids[window.document.getElementById("hostelMenu").selectedIndex],
                roomNum: roomNum,
                price: price,
                inDate: window.document.getElementById("inDate").value,
                outDate: window.document.getElementById("outDate").value
            },
            error: function (request) {
                alert("Connection error");
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);  //json数据
                if (jsonData.success) {
                    alert("预定成功");
                } else {
                    alert(jsonData.msg);
                }
            }
        })
    }

</script>

</body>
</html>