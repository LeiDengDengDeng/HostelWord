package com.deng.service.manager;

import com.deng.pojo.manager.Manager;
import com.deng.pojo.member.ConsumeType;
import com.deng.service.exception.ServiceException;

/**
 * Created by dell on 2/19 0019.
 */
public interface ManagerService {
    Manager getManager();

    void consumeManagerMoney(int money, ConsumeType consumeType) throws ServiceException;
}
