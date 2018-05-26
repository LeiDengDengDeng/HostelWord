package com.deng.service.bankcard;

import com.deng.dao.bankcard.BankCardDaoImpl;
import com.deng.pojo.member.BankCard;
import com.deng.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deng on 2017/3/13.
 */
@Service("BankCardService")
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankCardDaoImpl bankCardDao;

    public void consume(long cardId, long id, int password, int money, String name) throws ServiceException {
        if (money <= 0) throw new ServiceException(200001, "消费金额必须是正整数");

        BankCard bankCard = bankCardDao.get(BankCard.class, cardId);

        if (bankCard == null || !bankCard.getName().equals(name) || bankCard.getId() != id || bankCard.getPassword() != (password)) {
            throw new ServiceException(200002, "银行卡信息错误");
        } else {
            long balance = bankCard.getMoney() - money;
            if (balance < 0) {
                throw new ServiceException(200003, "银行卡余额不足");
            } else {
                // 完成消费
                bankCard.setMoney(balance);
                bankCardDao.update(bankCard);
            }
        }
    }
}
