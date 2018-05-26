package com.deng.service.bankcard;

import com.deng.service.exception.ServiceException;

/**
 * Created by deng on 2017/3/12.
 */
public interface BankCardService {
    /**
     * 使用指定银行卡消费
     *
     * @param cardId   银行卡号
     * @param id       持卡人身份证号
     * @param password 银行卡密码，六位数字
     * @param money    消费金额
     * @param name     持卡人姓名
     * @throws ServiceException 身份验证失败、金额不够
     */
    void consume(long cardId, long id, int password, int money, String name) throws ServiceException;
}
