package com.deng.controller;

import com.deng.pojo.hostel.Hostel;
import com.deng.pojo.hostel.HostelState;
import com.deng.pojo.manager.Manager;
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
 * Created by dell on 2/13 0013.
 */
@Controller
@RequestMapping("/back")
public class LoginController {
    @Autowired
    private HostelService hostelService;
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpSession session, long account, String password) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        boolean success = false;
        Hostel hostel = hostelService.getHostel(account);
        if (hostel != null && hostel.getPassword().equals(password) && hostel.getState() == HostelState.NORMAL) {
            dataJson.put("type", "hostel");
            session.setAttribute("type", "hostel");
            success = true;
        }
        Manager manager = managerService.getManager();
        if (manager.getId() == account && manager.getPassword().equals(password)) {
            dataJson.put("type", "manager");
            session.setAttribute("type", "manager");
            success = true;
        }

        if (success) {
            session.setAttribute("account", account);
            toReturn.put("success", true);
            toReturn.put("msg", "success");
        } else {
            toReturn.put("success", false);
            toReturn.put("msg", "请检查ID和密码");
        }
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        // 清除Session
        session.invalidate();

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }
}
