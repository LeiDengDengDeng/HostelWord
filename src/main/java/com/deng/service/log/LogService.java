package com.deng.service.log;

import com.deng.pojo.log.ConsumeLog;
import com.deng.pojo.log.IncomeRecordLog;
import com.deng.pojo.log.Log;
import com.deng.pojo.log.PointLog;

import java.util.List;

/**
 * Created by deng on 2017/3/20.
 */
public interface LogService {
    List<ConsumeLog> getConsumeLogsByMemberId(long memberId);

    List<PointLog> getPointLogsByMemberId(long memberId);

    List<Log> getOrderLogsByMemberId(long memberId);

    List<Log> getManagerExamLogs();

    List<IncomeRecordLog> getManagerConsumeLogs();
}
