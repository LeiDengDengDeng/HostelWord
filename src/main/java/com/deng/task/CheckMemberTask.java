package com.deng.task;

import com.deng.dao.member.MemberDaoImpl;
import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;
import com.deng.pojo.member.MemberState;
import com.deng.service.exception.ServiceException;
import com.deng.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by deng on 2017/3/21.
 */
@Service("CheckMemberTask")
@Transactional
public class CheckMemberTask {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberDaoImpl memberDao;

    /**
     * 每天凌晨一点执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkMember() {
        for (Member member : memberService.getAllNormalMember()) {
            Date expireDate = member.getExpiredTime();
            Date now = new Date();

            Calendar oneYearLater = Calendar.getInstance();
            oneYearLater.set(Calendar.YEAR, oneYearLater.get(Calendar.YEAR) + 1);

            if (now.after(expireDate)) {
                try {
                    memberService.consumeMemberMoney(member.getMemberId(), 800, ConsumeType.MEMBER_COST);
                    member.setExpiredTime(oneYearLater.getTime());
                } catch (ServiceException e) {
                    member.setState(MemberState.STOPPED);
                } finally {
                    memberDao.update(member);
                }
            }
        }
    }
}
