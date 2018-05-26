package com.deng.pojo.log;

import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deng on 2017/3/17.
 */
@Entity(name = "consumelog")
@PrimaryKeyJoinColumn(name = "consumelogid")
public class ConsumeLog extends Log {
    private Member member;
    private int money;
    private ConsumeType consumeType;

    private ConsumeLog() {
    }

    public ConsumeLog(long operatorId, OperatorType operatorType, String operationName, String content, Date operationTime, Member member, int money, ConsumeType type) {
        super.operatorId = operatorId;
        super.type = operatorType;
        super.operationName = operationName;
        super.content = content;
        super.operationTime = operationTime;
        this.member = member;
        this.money = money;
        this.consumeType = type;
    }

    @ManyToOne
    @JoinColumn(name = "memberid")
    public Member getMember() {
        return member;
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

    public void setMember(Member member) {
        this.member = member;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setConsumeType(ConsumeType type) {
        this.consumeType = type;
    }
}
