package com.deng.service.member;

import com.deng.aspect.LogType;
import com.deng.dao.member.MemberDaoImpl;
import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;
import com.deng.pojo.member.MemberState;
import com.deng.service.bankcard.BankCardService;
import com.deng.service.exception.ServiceException;
import com.deng.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by deng on 2017/3/9.
 */
@Service("MemberService")
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDaoImpl memberDao;
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ManagerService managerService;

    public List<Member> getAllNormalMember() {
        return memberDao.getAllNormalMember();
    }

    public Member getMemberProfile(long memberId) {
        return memberDao.get(Member.class, memberId);
    }

    public long registerAsMember(Member member, long cardId, long id, int password, String name) throws ServiceException {
        bankCardService.consume(cardId, id, password, 1000, name);
        managerService.consumeManagerMoney(1000, ConsumeType.MEMBER_COST);

        // 将到期时间设置为今天的一年后
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
        member.setExpiredTime(c.getTime());
        member.setMoney(0);
        member.setConsumedMoney(0);
        member.setState(MemberState.NORMAL);

        return (Long) memberDao.save(member);
    }

    public void topUpMember(long memberId, long cardId, long id, int password, String name, int money) throws ServiceException {
        // 先使用银行卡
        bankCardService.consume(cardId, id, password, money, name);
        // 再更改会员资金
        memberService.consumeMemberMoney(memberId, money, ConsumeType.TOP_UP);
        // 修改经理资金
        managerService.consumeManagerMoney(money, ConsumeType.TOP_UP);
    }

    public void logIn(long memberId, String password) throws ServiceException {
        Member member = memberDao.get(Member.class, memberId);
        if (member == null || !DigestUtils.md5DigestAsHex((password + member.getId()).getBytes()).equals(member.getPassword()))
            throw new ServiceException(300001, "用户名或密码错误");
        if (member.getState() == MemberState.STOPPED)
            throw new ServiceException(300005, "该会员卡已停用");
    }

    @LogType(operationName = "会员资金变化")
    public void consumeMemberMoney(long memberId, int money, ConsumeType type) throws ServiceException {
        Member member = getMemberProfile(memberId);

        assert (member != null);

        synchronized (MemberService.class) {
            long balance = member.getMoney() + money;
            if (balance < 0) {
                throw new ServiceException(300002, "会员卡余额不足");
            }
            member.setMoney(balance);
        }

        // 只有涉及预定酒店和取消预定酒店时修改用户的已消费金额
        if (type == ConsumeType.ORDER || type == ConsumeType.CANCEL_ORDER)
            member.setConsumedMoney(member.getConsumedMoney() - money);
        memberDao.update(member);
    }

    @LogType(operationName = "会员积分变化")
    public void changeMemberPoint(long memberId, int point, ConsumeType type) throws ServiceException {
        Member member = getMemberProfile(memberId);

        assert (member != null);

        synchronized (MemberService.class) {
            long balance = member.getPoint() + point;
            if (balance < 0) {
                throw new ServiceException(300003, "会员卡积分不足");
            }

            member.setPoint(balance);
            memberDao.update(member);
        }
    }

    public void pointToMoney(long memberId, int point) throws ServiceException {
        if (point < 100) throw new ServiceException(300004, "兑换积分数量至少为100分");

        int money = point / 100; // 100分换1元

        memberService.changeMemberPoint(memberId, -money * 100, ConsumeType.EXCHANGE_POINT);
        memberService.consumeMemberMoney(memberId, money, ConsumeType.EXCHANGE_POINT);

        managerService.consumeManagerMoney(-money, ConsumeType.EXCHANGE_POINT);
    }

    public void stopMember(long memberId) {
        Member member = memberService.getMemberProfile(memberId);
        member.setState(MemberState.STOPPED);
        memberDao.update(member);
    }
}
