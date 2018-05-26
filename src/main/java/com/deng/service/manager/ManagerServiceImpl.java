package com.deng.service.manager;

import com.deng.aspect.LogType;
import com.deng.dao.manager.ManagerDaoImpl;
import com.deng.pojo.log.OperatorType;
import com.deng.pojo.manager.Manager;
import com.deng.pojo.member.ConsumeType;
import com.deng.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deng on 2017/3/20.
 */
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {
    private final static long id = 999999;

    @Autowired
    private ManagerDaoImpl managerDao;

    public Manager getManager() {
        return managerDao.get(Manager.class, id);
    }

    @LogType(operationName = "经理资金变化", operatorType = OperatorType.MANAGER)
    public void consumeManagerMoney(int money, ConsumeType consumeType) throws ServiceException {
        Manager manager = managerDao.get(Manager.class, id);

        synchronized (ManagerService.class) {
            long balance = manager.getMoney() + money;
            if (balance < 0) throw new ServiceException(700001, "经理资金不足");
            manager.setMoney(balance);
            managerDao.update(manager);
        }
    }
}
