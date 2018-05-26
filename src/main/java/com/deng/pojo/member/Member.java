package com.deng.pojo.member;

import com.deng.pojo.hostel.CheckInRecord;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by dell on 1/25 0025.
 */
@Entity(name = "member")
public class Member {
    private long memberId; // 7位识别码
    private String password;
    private String name; // 用户姓名
    private long id; // 用户身份证号
    private MemberState state; // 状态
    private Date expiredTime; // 到期时间
    private long consumedMoney; // 已消费金额,根据已消费金额确定等级 【0,10000,200000,1000000,5000000】
    private int[] levelMap = {100000, 500000};
    private long money; // 会员卡余额
    private long point; // 会员积分

    private Set<CheckInRecord> checkInRecords;

    private Member() {
    }

    public Member(String password, String name, long id) {
        this.password = password;
        this.name = name;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    public long getMemberId() {
        return memberId;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    public MemberState getState() {
        return state;
    }

    @Column(name = "expiredtime")
    @Temporal(TemporalType.DATE)
    public Date getExpiredTime() {
        return expiredTime;
    }

    @Column(name = "consumedmoney")
    public long getConsumedMoney() {
        return consumedMoney;
    }

    @Column(name = "money")
    public long getMoney() {
        return money;
    }

    @Column(name = "point")
    public long getPoint() {
        return point;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<CheckInRecord> getCheckInRecords() {
        return checkInRecords;
    }

    @Transient
    public int getLevel() {
        for (int i = 0; i < levelMap.length; i++) {
            if (consumedMoney < levelMap[i]) return (i + 1);
        }
        return levelMap.length + 1;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setState(MemberState state) {
        this.state = state;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void setConsumedMoney(long consumedMoney) {
        this.consumedMoney = consumedMoney;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public void setCheckInRecords(Set<CheckInRecord> checkInRecords) {
        this.checkInRecords = checkInRecords;
    }
}
