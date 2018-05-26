package com.deng.controller;

import com.deng.pojo.log.ConsumeLog;
import com.deng.pojo.log.IncomeRecordLog;
import com.deng.pojo.log.Log;
import com.deng.pojo.log.PointLog;
import com.deng.service.log.LogService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by deng on 2017/3/20.
 */
@Controller
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/log/consumeLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getConsumeLogs(HttpSession session) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray logs = new JSONArray();
        for (ConsumeLog consumeLog : logService.getConsumeLogsByMemberId(memberId)) {
            JSONObject logObject = new JSONObject();

            logObject.put("time", consumeLog.getOperationTime());
            logObject.put("type", consumeLog.getConsumeType().getTypeStr());
            logObject.put("money", consumeLog.getMoney());

            logs.put(logObject);
        }
        dataJson.put("logs", logs);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/log/pointLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPointLogs(HttpSession session) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray logs = new JSONArray();
        for (PointLog pointLog : logService.getPointLogsByMemberId(memberId)) {
            JSONObject logObject = new JSONObject();

            logObject.put("time", pointLog.getOperationTime());
            logObject.put("type", pointLog.getConsumeType().getTypeStr());
            logObject.put("point", pointLog.getPoint());

            logs.put(logObject);
        }
        dataJson.put("logs", logs);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/log/orderLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getOrderLogs(HttpSession session) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray logs = new JSONArray();
        for (Log log : logService.getOrderLogsByMemberId(memberId)) {
            JSONObject logObject = new JSONObject();

            logObject.put("time", log.getOperationTime());
            logObject.put("content", log.getContent());
            logObject.put("name", log.getOperationName());

            logs.put(logObject);
        }
        dataJson.put("logs", logs);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/log/managerExamLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getManagerExamLogs(HttpSession session) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray logs = new JSONArray();
        for (Log log : logService.getManagerExamLogs()) {
            JSONObject logObject = new JSONObject();

            logObject.put("time", log.getOperationTime());
            logObject.put("content", log.getContent());

            logs.put(logObject);
        }
        dataJson.put("logs", logs);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/log/managerConsumeLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getManagerConsumeLogs(HttpSession session) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        JSONArray logs = new JSONArray();
        for (IncomeRecordLog log : logService.getManagerConsumeLogs()) {
            JSONObject logObject = new JSONObject();

            logObject.put("time", log.getOperationTime());
            logObject.put("money", log.getMoney());
            logObject.put("type", log.getConsumeType().getTypeStr());

            logs.put(logObject);
        }
        dataJson.put("logs", logs);

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }
}
