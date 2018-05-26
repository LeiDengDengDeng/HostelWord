package com.deng.pojo.log;

import com.deng.pojo.member.ConsumeType;
import com.deng.pojo.member.Member;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deng on 2017/3/17.
 */
@Entity(name = "pointlog")
@PrimaryKeyJoinColumn(name = "pointlogid")
public class PointLog extends Log {
    private Member member;
    private int point;
    private ConsumeType consumeType;

    private PointLog() {
    }

    public PointLog(long operatorId, OperatorType operatorType, String operationName, String content, Date operationTime, Member member, int point, ConsumeType type) {
        super.operatorId = operatorId;
        super.type = operatorType;
        super.operationName = operationName;
        super.content = content;
        super.operationTime = operationTime;
        this.member = member;
        this.point = point;
        this.consumeType = type;
    }

    @ManyToOne
    @JoinColumn(name = "memberid")
    public Member getMember() {
        return member;
    }

    @Column(name = "point")
    public int getPoint() {
        return point;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public ConsumeType getConsumeType() {
        return consumeType;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setConsumeType(ConsumeType consumeType) {
        this.consumeType = consumeType;
    }
}
