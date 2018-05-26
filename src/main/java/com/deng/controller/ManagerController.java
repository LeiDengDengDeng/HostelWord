package com.deng.controller;

import com.deng.pojo.manager.Manager;
import com.deng.pojo.member.ConsumeType;
import com.deng.service.exception.ServiceException;
import com.deng.service.hostel.HostelService;
import com.deng.service.manager.ManagerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by deng on 2017/3/21.
 */
@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private HostelService hostelService;

    @RequestMapping(value = "/back/manager/profile", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getManagerProfile(HttpSession session) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Manager manager = managerService.getManager();
        dataJson.put("id", manager.getId());
        dataJson.put("money", manager.getMoney());

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);
        return toReturn.toString();
    }

    @RequestMapping(value = "/back/manager/settle", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String settle(HttpSession session, long hostelId, int money) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            managerService.consumeManagerMoney(-money, ConsumeType.SETTLE_ACCOUNT);
            hostelService.gainSettleMoney(hostelId, money);
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
