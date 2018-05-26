package com.deng.service.member;

import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;
import com.deng.service.exception.ServiceException;

import java.util.List;

/**
 * Created by deng on 2017/3/9.
 * 会员service
 */
public interface MemberService {
    List<Member> getAllNormalMember();

    Member getMemberProfile(long memberId);

    /**
     * 注册成为酒店会员
     *
     * @param member   会员信息
     * @param cardId   银行卡卡号
     * @param id       持卡人身份证
     * @param password 银行卡密码
     * @param name     持卡人姓名
     * @return 会员id
     * @throws ServiceException 银行卡余额不足、认证银行卡信息失败等
     */
    long registerAsMember(Member member, long cardId, long id, int password, String name) throws ServiceException;

    /**
     * 会员卡充值
     *
     * @param memberId 会员id
     * @param cardId   银行卡卡号
     * @param id       持卡人身份证
     * @param password 银行卡密码
     * @param name     持卡人姓名
     * @param money    充值金额
     * @throws ServiceException 银行卡余额不足、认证银行卡信息失败等
     */
    void topUpMember(long memberId, long cardId, long id, int password, String name, int money) throws ServiceException;

    /**
     * 会员登录
     *
     * @param memberId 会员id
     * @param password 密码
     * @throws ServiceException 找不到对应会员id或密码错误
     */
    void logIn(long memberId, String password) throws ServiceException;

    /**
     * 会员卡余额变动
     *
     * @param memberId 会员id
     * @param money    钱的增减数值，正数代表钱增多，负数代表钱减少
     * @param type     增减原因
     * @throws ServiceException 减少钱时，钱不够
     */
    void consumeMemberMoney(long memberId, int money, ConsumeType type) throws ServiceException;

    /**
     * 会员卡积分变动
     *
     * @param memberId 会员id
     * @param point    积分的增减数值，正数代表积分增多，负数代表积分减少
     * @param type     增减原因
     * @throws ServiceException 减少积分时，积分不够
     */
    void changeMemberPoint(long memberId, int point, ConsumeType type) throws ServiceException;

    /**
     * 会员积分兑换成会员卡余额
     *
     * @param memberId 会员id
     * @param point    兑换积分
     * @throws ServiceException 积分不足
     */
    void pointToMoney(long memberId, int point) throws ServiceException;

    void stopMember(long memberId);
}
