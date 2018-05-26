package com.deng.service.log;

import com.deng.dao.log.LogDaoImpl;
import com.deng.pojo.log.ConsumeLog;
import com.deng.pojo.log.IncomeRecordLog;
import com.deng.pojo.log.Log;
import com.deng.pojo.log.PointLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deng on 2017/3/20.
 */
@Service("LogService")
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDaoImpl logDao;

    public List<ConsumeLog> getConsumeLogsByMemberId(long memberId) {
        return logDao.getConsumeLogsByMemberId(memberId);
    }

    public List<PointLog> getPointLogsByMemberId(long memberId) {
        return logDao.getPointLogsByMemberId(memberId);
    }

    public List<Log> getOrderLogsByMemberId(long memberId) {
        return logDao.getOrderLogsByMemberId(memberId);
    }

    public List<Log> getManagerExamLogs() {
        return logDao.getManagerExamLogs();
    }

    public List<IncomeRecordLog> getManagerConsumeLogs() {
        return logDao.getManagerConsumeLogs();
    }
}
