package com.deng.pojo.log;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deng on 2017/3/17.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "log")
public class Log {
    protected long id;
    protected long operatorId; // 操作者id
    protected OperatorType type; // 操作者
    protected String operationName; // 操作名称
    protected String content;
    protected Date operationTime;

    public Log() {
    }

    public Log(long operatorId, OperatorType type, String operationName, String content, Date operationTime) {
        this.operatorId = operatorId;
        this.type = type;
        this.operationName = operationName;
        this.content = content;
        this.operationTime = operationTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Column(name = "operatorid")
    public long getOperatorId() {
        return operatorId;
    }

    @Column(name = "operatortype")
    @Enumerated(EnumType.STRING)
    public OperatorType getType() {
        return type;
    }

    @Column(name = "operatename")
    public String getOperationName() {
        return operationName;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operatetime")
    public Date getOperationTime() {
        return operationTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public void setType(OperatorType type) {
        this.type = type;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}
