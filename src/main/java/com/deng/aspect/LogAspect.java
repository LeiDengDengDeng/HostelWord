package com.deng.aspect;

import com.deng.dao.log.LogDaoImpl;
import com.deng.dao.manager.ManagerDaoImpl;
import com.deng.dao.member.MemberDaoImpl;
import com.deng.dao.order.OrderDaoImpl;
import com.deng.pojo.log.ConsumeLog;
import com.deng.pojo.log.IncomeRecordLog;
import com.deng.pojo.log.Log;
import com.deng.pojo.log.PointLog;
import com.deng.pojo.manager.Manager;
import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;
import com.deng.pojo.order.RoomOrder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by deng on 2017/3/17.
 * 日志记录的切面
 */
@Aspect
public class LogAspect {
    @Autowired
    private LogDaoImpl logDao;
    @Autowired
    private MemberDaoImpl memberDao;
    @Autowired
    private OrderDaoImpl orderDao;
    @Autowired
    private ManagerDaoImpl managerDao;

    @Pointcut(value = "execution(@com.deng.aspect.LogType * *(..))&& args(..)")
    public void afterPointcut() {
    }

    @AfterReturning(value = "afterPointcut()")
    public void after(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        LogType type = this.getLogType(joinPoint);

        Log log = null;
        if (type.operationName().equals("会员资金变化")) {
            log = new ConsumeLog((Long) arguments[0], type.operatorType(), type.operationName(), "", new Date(), memberDao.get(Member.class, (Long) arguments[0]), (Integer) arguments[1], (ConsumeType) arguments[2]);
        } else if (type.operationName().equals("会员积分变化")) {
            log = new PointLog((Long) arguments[0], type.operatorType(), type.operationName(), "", new Date(), memberDao.get(Member.class, (Long) arguments[0]), (Integer) arguments[1], (ConsumeType) arguments[2]);
        } else if (type.operationName().equals("预定房间")) {
            String content = "花费" + arguments[3] + "元";
            log = new Log((Long) arguments[0], type.operatorType(), type.operationName(), content, new Date());
        } else if (type.operationName().equals("取消预定")) {
            RoomOrder order = orderDao.get(RoomOrder.class, (Long) arguments[0]);
            String content = "退还" + order.getPrice() + "元";
            log = new Log(order.getMember().getMemberId(), type.operatorType(), type.operationName(), content, new Date());
        } else if (type.operationName().equals("经理资金变化")) {
            long id = 999999;
            log = new IncomeRecordLog(id, type.operatorType(), type.operationName(), "", new Date(), managerDao.get(Manager.class, id), (Integer) arguments[0], (ConsumeType) arguments[1]);
        } else if (type.operationName().equals("审批酒店信息")) {
            long id = 999999;
            String content = "审批编号为" + arguments[0] + "的酒店，审批结果为";

            if ((Boolean) arguments[1])
                content += "通过";
            else
                content += "不通过";

            log = new Log(id, type.operatorType(), type.operationName(), content, new Date());
        }

        if (log != null) {
            System.out.println("记录日志");
            logDao.save(log);
        }
    }


    private LogType getLogType(JoinPoint joinPoint) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs != null && clazzs.length == arguments.length && method.getAnnotation(LogType.class) != null) {
                    return method.getAnnotation(LogType.class);
                }
            }
        }
        return null;
    }
}
