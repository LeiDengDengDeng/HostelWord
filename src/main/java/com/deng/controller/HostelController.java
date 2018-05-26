package com.deng.controller;

import com.deng.pojo.hostel.*;
import com.deng.service.exception.ServiceException;
import com.deng.service.hostel.HostelRoomService;
import com.deng.service.hostel.HostelService;
import com.deng.service.member.MemberService;
import com.deng.service.util.TimeUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by deng on 2017/3/3.
 */
@Controller
public class HostelController {
    @Autowired
    private HostelService hostelService;
    @Autowired
    private HostelRoomService hostelRoomService;
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/back/hostel/register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String registerAsHostel(String password, String name, String province, String city, String address) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Hostel hostel = new Hostel(password, name, HostelState.EXAMINING, province, city, address);
        long id = hostelService.registerAsHostel(hostel);
        dataJson.put("id", id);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);
        return toReturn.toString();
    }

    @RequestMapping(value = "/back/hostel/examining", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getExaminingHostels() {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray hostelArray = new JSONArray();
        for (Hostel hostel : hostelService.getExaminingHostels()) {
            JSONObject hostelObj = new JSONObject();
            hostelObj.put("id", hostel.getId());
            hostelObj.put("name", hostel.getName());
            hostelObj.put("province", hostel.getProvince());
            hostelObj.put("city", hostel.getCity());
            hostelObj.put("address", hostel.getAddress());

            hostelArray.put(hostelObj);
        }

        dataJson.put("hostels", hostelArray);
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/back/hostel/exam", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String examHostel(long hostelId, boolean passed) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        hostelService.examineHostel(hostelId, passed);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/back/hostel", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getHostelProfile(HttpSession session) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Hostel hostel = hostelService.getHostel(hostelId);
        dataJson.put("id", hostel.getId());
        dataJson.put("province", hostel.getProvince());
        dataJson.put("city", hostel.getCity());
        dataJson.put("address", hostel.getAddress());
        dataJson.put("name", hostel.getName());
        dataJson.put("state", hostel.getState().getTypeStr());
        dataJson.put("money", hostel.getMoney());
        dataJson.put("earnMoney", hostel.getEarnMoney());
        dataJson.put("totalEarnMoney", hostel.getTotalEarnMoney());


        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/back/hostel/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modifyHostelProfile(HttpSession session, String name, String address) {
        long hostelId = (Long) session.getAttribute("account");

        Hostel hostel = hostelService.getHostel(hostelId);
        hostel.setName(name);
        hostel.setAddress(address);
        hostelService.modifyHostel(hostel);

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }


    @RequestMapping(value = "/back/hostel/room", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getHostelRoomsByDate(HttpSession session, String start, String end) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray roomArray = new JSONArray();
        for (HostelRoomBO room : hostelRoomService.getHostelRooms(hostelId, TimeUtil.convertDateStr(start), TimeUtil.convertDateStr(end))) {
            JSONObject roomObject = new JSONObject();

            roomObject.put("type", room.getType().getTypeStr());
            roomObject.put("roomNum", room.getRoomNum());
            roomObject.put("isOrdered", room.isOrdered());
            roomObject.put("isCheckedIn", room.isCheckedIn());

            roomArray.put(roomObject);
        }
        dataJson.put("rooms", roomArray);
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/front/hostel/running", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getRunningHostels() {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();
        JSONArray hostelArray = new JSONArray();
        for (Hostel hostel : hostelService.getRunningHostels()) {
            JSONObject hostelObject = new JSONObject();
            hostelObject.put("id", hostel.getId());
            hostelObject.put("name", hostel.getName());
            hostelObject.put("province", hostel.getProvince());
            hostelObject.put("city", hostel.getCity());
            hostelObject.put("address", hostel.getAddress());
            hostelObject.put("money", hostel.getMoney());
            hostelObject.put("earnMoney", hostel.getEarnMoney());
            hostelObject.put("totalEarnMoney", hostel.getTotalEarnMoney());

            hostelArray.put(hostelObject);
        }
        dataJson.put("hostels", hostelArray);
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/front/hostel/check", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkAvailabilityForMember(HttpSession session, long hostelId, String typeStr, String inDate, String outDate) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            int level = memberService.getMemberProfile(memberId).getLevel();
            int roomNum = hostelRoomService.getRandomFreeRoomByTypeAndDate(hostelId, RoomType.getRoomTypeByTypeStr(typeStr), TimeUtil.convertDateStr(inDate), TimeUtil.convertDateStr(outDate));
            dataJson.put("roomNum", roomNum);
            dataJson.put("price", hostelRoomService.getPrice(hostelId, RoomType.getRoomTypeByTypeStr(typeStr), TimeUtil.convertDateStr(inDate), TimeUtil.convertDateStr(outDate), level));
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

    @RequestMapping(value = "/back/hostel/check", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkAvailabilityForHostel(HttpSession session, long memberId, String typeStr, String inDate, String outDate) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            int level = 0;
            if (memberService.getMemberProfile(memberId) != null) {
                level = memberService.getMemberProfile(memberId).getLevel();
            }

            int roomNum = hostelRoomService.getRandomFreeRoomByTypeAndDate(hostelId, RoomType.getRoomTypeByTypeStr(typeStr), TimeUtil.convertDateStr(inDate), TimeUtil.convertDateStr(outDate));
            dataJson.put("roomNum", roomNum);
            dataJson.put("price", hostelRoomService.getPrice(hostelId, RoomType.getRoomTypeByTypeStr(typeStr), TimeUtil.convertDateStr(inDate), TimeUtil.convertDateStr(outDate), level));
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

    @RequestMapping(value = "/back/hostel/checkIn", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkIn(HttpSession session, String jsonStr) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject checkInForm = new JSONObject(jsonStr);
        long orderId = -1;
        if (!checkInForm.getString("orderId").equals("")) orderId = checkInForm.getLong("orderId");
        long memberId = -1;
        if (!checkInForm.getString("memberId").equals("")) memberId = checkInForm.getLong("memberId");
        int price = checkInForm.getInt("price");
        int roomNum = checkInForm.getInt("roomNum");
        Date inDate = TimeUtil.convertDateStr(checkInForm.getString("inDate"));
        Date outDate = TimeUtil.convertDateStr(checkInForm.getString("outDate"));

        Set<Visitor> visitors = new HashSet<Visitor>();
        for (int i = 0; ; ) {
            try {
                String name = checkInForm.getString("name" + i);
                Long id = checkInForm.getLong("id" + i);

                i++;
                Visitor visitor = new Visitor();
                visitor.setName(name);
                visitor.setId(id);

                visitors.add(visitor);
            } catch (org.json.JSONException e) {
                break;
            }
        }
        try {
            hostelRoomService.checkInHostelRoom(memberId, orderId, price, visitors, hostelId, roomNum, inDate, outDate);
        } catch (ServiceException e) {
            JSONObject toReturn = new JSONObject();
            JSONObject dataJson = new JSONObject();
            toReturn.put("success", false);
            toReturn.put("msg", e.getErrorMessage());
            toReturn.put("data", dataJson);
            return toReturn.toString();
        }

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);
        return toReturn.toString();
    }


    @RequestMapping(value = "/back/hostel/checkInRecord", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCheckInRecords(HttpSession session) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();
        JSONArray recordArray = new JSONArray();

        for (CheckInRecord r : hostelRoomService.getCheckInRecordsByHostelId(hostelId)) {
            JSONObject recordObj = new JSONObject();
            recordObj.put("roomNum", r.getRoom().getRoomNum());
            recordObj.put("type", r.getRoom().getRoomType().getTypeStr());
            recordObj.put("inDate", r.getInDate());
            recordObj.put("outDate", r.getOutDate());

            recordArray.put(recordObj);
        }
        dataJson.put("records", recordArray);
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);
        return toReturn.toString();
    }
}
