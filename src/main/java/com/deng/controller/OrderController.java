package com.deng.controller;

import com.deng.pojo.order.RoomOrder;
import com.deng.service.exception.ServiceException;
import com.deng.service.order.OrderService;
import com.deng.service.util.TimeUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deng on 2017/3/19.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/back/order", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getOrder(HttpSession session, long orderId) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        RoomOrder o = orderService.getOrder(orderId);
        if (o.isCanceled() || o.isCheckedIn()) {
            toReturn.put("success", false);
            toReturn.put("msg", "该订单已失效");
            toReturn.put("data", dataJson);
        } else {
            dataJson.put("memberId", o.getMember().getMemberId());
            dataJson.put("roomNum", o.getRoom().getRoomNum());
            dataJson.put("roomType", o.getRoom().getRoomType().getTypeStr());
            dataJson.put("inDate", o.getInDate());
            dataJson.put("outDate", o.getOutDate());
            dataJson.put("price", o.getPrice());

            toReturn.put("success", true);
            toReturn.put("msg", "success");
            toReturn.put("data", dataJson);
        }

        return toReturn.toString();
    }

    @RequestMapping(value = "/front/order", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMemberOrders(HttpSession session) {
        Map<Boolean, String> map = new HashMap<Boolean, String>();
        map.put(true, "是");
        map.put(false, "否");

        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray orders = new JSONArray();
        for (RoomOrder o : orderService.getMemberOrders(memberId)) {
            JSONObject orderObject = new JSONObject();

            orderObject.put("id", o.getId());
            orderObject.put("orderDate", o.getOrderDate());
            orderObject.put("hostelName", o.getRoom().getHostel().getName());
            orderObject.put("roomNum", o.getRoom().getRoomNum());
            orderObject.put("roomType", o.getRoom().getRoomType().getTypeStr());
            orderObject.put("price", o.getPrice());
            orderObject.put("inDate", o.getInDate());
            orderObject.put("outDate", o.getOutDate());
            orderObject.put("canceled", map.get(o.isCanceled()));
            orderObject.put("checkedIn", map.get(o.isCheckedIn()));
            orderObject.put("canBeCanceled", o.canBeCanceled());

            orders.put(orderObject);
        }
        dataJson.put("orders", orders);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/back/order/all", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getHostelOrders(HttpSession session) {
        Map<Boolean, String> map = new HashMap<Boolean, String>();
        map.put(true, "是");
        map.put(false, "否");

        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray orders = new JSONArray();
        for (RoomOrder o : orderService.getHostelOrders(hostelId)) {
            JSONObject orderObject = new JSONObject();

            orderObject.put("id", o.getId());
            orderObject.put("orderDate", o.getOrderDate());
            orderObject.put("roomNum", o.getRoom().getRoomNum());
            orderObject.put("roomType", o.getRoom().getRoomType().getTypeStr());
            orderObject.put("price", o.getPrice());
            orderObject.put("inDate", o.getInDate());
            orderObject.put("outDate", o.getOutDate());
            orderObject.put("canceled", map.get(o.isCanceled()));
            orderObject.put("checkedIn", map.get(o.isCheckedIn()));

            orders.put(orderObject);
        }
        dataJson.put("orders", orders);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/front/order/order", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String orderRoom(HttpSession session, long hostelId, int roomNum, int price, String inDate, String outDate) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            orderService.orderRoom(memberId, hostelId, roomNum, price, TimeUtil.convertDateStr(inDate), TimeUtil.convertDateStr(outDate));
        } catch (ServiceException e) {
            toReturn.put("success", false);
            toReturn.put("msg", e.getErrorMessage());
            toReturn.put("data", dataJson);
            return toReturn.toString();
        }

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);
        return toReturn.toString();
    }

    @RequestMapping(value = "/front/order/cancel", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String cancelOrderRoom(HttpSession session, long orderId) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            orderService.cancelOrder(orderId);
        } catch (ServiceException e) {
            toReturn.put("success", false);
            toReturn.put("msg", e.getErrorMessage());
            toReturn.put("data", dataJson);
            return toReturn.toString();
        }

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);
        return toReturn.toString();
    }
}
