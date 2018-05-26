package com.deng.controller;

import com.deng.pojo.member.Member;
import com.deng.service.exception.ServiceException;
import com.deng.service.member.MemberService;
import com.deng.service.util.TimeUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by deng on 2017/3/10.
 */
@Controller
@RequestMapping("/front/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpSession session, long memberId, String password) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            memberService.logIn(memberId, password);
        } catch (ServiceException e) {
            // 登录失败
            toReturn.put("success", false);
            toReturn.put("msg", e.getErrorMessage());
            toReturn.put("data", dataJson);
            return toReturn.toString();
        }

        // 登录成功
        session.setAttribute("account", memberId);
        session.setAttribute("type", "member");
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMemberProfile(HttpSession session) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Member member = memberService.getMemberProfile(memberId);
        dataJson.put("memberId", member.getMemberId());
        dataJson.put("memberName", member.getName());
        dataJson.put("id", member.getId());
        dataJson.put("state", member.getState().getTypeStr());
        dataJson.put("consumedMoney", member.getConsumedMoney());
        dataJson.put("money", member.getMoney());
        dataJson.put("point", member.getPoint());
        dataJson.put("level", "VIP" + member.getLevel());
        dataJson.put("expiredTime", TimeUtil.convertDate(member.getExpiredTime()));

        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        return toReturn.toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String registerAsMember(HttpSession session, String memberName, long memberId, String memberPwd, long cardId, String name, long id, int cardPwd) {
        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        // 密码使用原密码和会员身份证号进行md5加盐加密
        Member member = new Member(DigestUtils.md5DigestAsHex((memberPwd + memberId).getBytes()), memberName, memberId);
        try {
            long memberCode = memberService.registerAsMember(member, cardId, id, cardPwd, name);
            dataJson.put("memberId", memberCode);
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

    @RequestMapping(value = "/point/exchange", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String pointToMoney(HttpSession session, int point) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            memberService.pointToMoney(memberId, point);
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

    @RequestMapping(value = "/topUp", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String topUp(HttpSession session, int money, long cardId, String name, long id, int cardPwd) {
        long memberId = (Long) session.getAttribute("account");

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();

        try {
            memberService.topUpMember(memberId, cardId, id, cardPwd, name, money);
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

    @RequestMapping(value = "/stop", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String stopMember(HttpSession session){
        long memberId = (Long) session.getAttribute("account");
        memberService.stopMember(memberId);

        JSONObject toReturn = new JSONObject();
        JSONObject dataJson = new JSONObject();
        toReturn.put("success", true);
        toReturn.put("msg", "success");
        toReturn.put("data", dataJson);

        session.invalidate();
        return toReturn.toString();
    }

}
