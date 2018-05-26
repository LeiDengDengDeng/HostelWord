package com.deng.pojo.log;

import com.deng.pojo.hostel.Hostel;
import com.deng.pojo.manager.Manager;
import com.deng.pojo.member.ConsumeType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deng on 2017/3/20.
 */
@Entity(name = "incomerecordlog")
@PrimaryKeyJoinColumn(name = "incomerecordlogid")
public class IncomeRecordLog extends Log {
    private Manager manager;
    private int money;
    private ConsumeType consumeType;

    private IncomeRecordLog() {
    }

    public IncomeRecordLog(long operatorId, OperatorType operatorType, String operationName, String content, Date oprationTime, Manager manager, int money, ConsumeType consumeType) {
        super.operatorId = operatorId;
        super.type = operatorType;
        super.operationName = operationName;
        super.content = content;
        super.operationTime = oprationTime;

        this.manager = manager;
        this.money = money;
        this.consumeType = consumeType;
    }

    @ManyToOne
    @JoinColumn(name = "managerid")
    public Manager getManager() {
        return manager;
    }

    @Column(name = "money")
    public int getMoney() {
        return money;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public ConsumeType getConsumeType() {
        return consumeType;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setConsumeType(ConsumeType consumeType) {
        this.consumeType = consumeType;
    }
}
