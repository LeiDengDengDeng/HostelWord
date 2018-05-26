package com.deng.controller;

import com.deng.pojo.hostel.BasicSchedule;
import com.deng.pojo.hostel.RoomType;
import com.deng.pojo.hostel.SpecialSchedule;
import com.deng.service.hostel.HostelService;
import com.deng.service.hostel.ScheduleService;
import com.deng.service.util.TimeUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by deng on 2017/2/24.
 */
@Controller
@RequestMapping("/back/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private HostelService hostelService;

    @RequestMapping(value = "/basicSchedule", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getBasicSchedules(HttpSession session) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<BasicSchedule> basicSchedules = scheduleService.getBasisSchedulesByHostelId(hostelId);

        JSONArray basicScheduleArray = new JSONArray();
        for (BasicSchedule basicSchedule : basicSchedules) {
            JSONObject basicScheduleJO = new JSONObject();
            basicScheduleJO.put("roomType", basicSchedule.getRoomType().getTypeStr());
            basicScheduleJO.put("vip0Price", basicSchedule.getVip0Price());
            basicScheduleJO.put("vip1Price", basicSchedule.getVip1Price());
            basicScheduleJO.put("vip2Price", basicSchedule.getVip2Price());
            basicScheduleJO.put("vip3Price", basicSchedule.getVip3Price());
            basicScheduleJO.put("year", basicSchedule.getYear());
            basicScheduleArray.put(basicScheduleJO);
        }
        dataJson.put("basicSchedules", basicScheduleArray);
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/basicSchedule/modify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyBasicSchedule(HttpSession session, String roomType, int vip0Price, int vip1Price, int vip2Price, int vip3Price) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        BasicSchedule basicSchedule = scheduleService.getBasicScheduleByHostelIdAndRoomType(hostelId, RoomType.getRoomTypeByTypeStr(roomType));
        basicSchedule.setVip0Price(vip0Price);
        basicSchedule.setVip1Price(vip1Price);
        basicSchedule.setVip2Price(vip2Price);
        basicSchedule.setVip3Price(vip3Price);
        scheduleService.modifyBasisSchedule(basicSchedule);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }


    @RequestMapping(value = "/specialSchedule", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSpecialSchedules(HttpSession session) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<SpecialSchedule> specialSchedules = scheduleService.getSpecialScheduleByHostelId(hostelId);

        JSONArray specialScheduleArray = new JSONArray();
        for (SpecialSchedule specialSchedule : specialSchedules) {
            JSONObject specialScheduleJO = new JSONObject();
            specialScheduleJO.put("roomType", specialSchedule.getRoomType().getTypeStr());
            specialScheduleJO.put("vip0Price", specialSchedule.getVip0Price());
            specialScheduleJO.put("vip1Price", specialSchedule.getVip1Price());
            specialScheduleJO.put("vip2Price", specialSchedule.getVip2Price());
            specialScheduleJO.put("vip3Price", specialSchedule.getVip3Price());
            specialScheduleJO.put("date", TimeUtil.convertDate(specialSchedule.getDate()));
            specialScheduleArray.put(specialScheduleJO);
        }
        dataJson.put("specialSchedules", specialScheduleArray);
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/specialSchedule/modify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifySpecialSchedule(HttpSession session, String roomType, int vip0Price, int vip1Price, int vip2Price, int vip3Price, String dateStr) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        SpecialSchedule specialSchedule = scheduleService.getSpecialSchedule(hostelId, RoomType.getRoomTypeByTypeStr(roomType), TimeUtil.convertDateStr(dateStr));
        if (specialSchedule != null) {
            specialSchedule.setVip0Price(vip0Price);
            specialSchedule.setVip1Price(vip1Price);
            specialSchedule.setVip2Price(vip2Price);
            specialSchedule.setVip3Price(vip3Price);
            scheduleService.modifySpecialSchedule(specialSchedule);
        } else {
            specialSchedule = new SpecialSchedule(hostelService.getHostel(hostelId), RoomType.getRoomTypeByTypeStr(roomType), vip0Price, vip1Price, vip2Price, vip3Price, TimeUtil.convertDateStr(dateStr));
            scheduleService.addSpecialSchedule(specialSchedule);
        }

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/specialSchedule/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteSpecialSchedule(HttpSession session, String roomType, String dateStr) {
        long hostelId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        SpecialSchedule specialSchedule = scheduleService.getSpecialSchedule(hostelId, RoomType.getRoomTypeByTypeStr(roomType), TimeUtil.convertDateStr(dateStr));
        if (specialSchedule != null)
            scheduleService.deleteSpecialSchedule(specialSchedule);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }
}
