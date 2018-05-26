package com.deng.pojo.order;

import com.deng.pojo.hostel.CheckInRecord;
import com.deng.pojo.hostel.HostelRoom;
import com.deng.pojo.member.Member;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deng on 2017/3/17.
 */
@Entity(name = "roomorder")
public class RoomOrder {
    private long id;
    private Member member;
    private HostelRoom room; // 预定的房间

    private int price; // 该次订单的价格
    private int point; // 该次订单为会员增加的积分

    private Date inDate;
    private Date outDate;
    private Date orderDate; // 预定时间

    private boolean canceled; // 是否被取消
    private boolean checkedIn; // 是否入住

    private CheckInRecord checkInRecord;

    private RoomOrder() {
    }

    public RoomOrder(Member member, HostelRoom room, int price, int point, Date orderDate, Date inDate, Date outDate) {
        this.member = member;
        this.room = room;
        this.price = price;
        this.point = point;
        this.orderDate = orderDate;
        this.inDate = inDate;
        this.outDate = outDate;
        this.canceled = false;
        this.checkedIn = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "memberid")
    public Member getMember() {
        return member;
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hostelid", referencedColumnName = "hostelid"),
            @JoinColumn(name = "roomnum", referencedColumnName = "roomnum")
    })
    public HostelRoom getRoom() {
        return room;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    @Column(name = "point")
    public int getPoint() {
        return point;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "indate")
    public Date getInDate() {
        return inDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "outdate")
    public Date getOutDate() {
        return outDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "orderdate")
    public Date getOrderDate() {
        return orderDate;
    }

    @Column(name = "canceled")
    public boolean isCanceled() {
        return canceled;
    }

    @Column(name = "checkedin")
    public boolean isCheckedIn() {
        return checkedIn;
    }

    @OneToOne(mappedBy = "order")
    public CheckInRecord getCheckInRecord() {
        return checkInRecord;
    }

    @Transient
    public boolean canBeCanceled() {
        return !(isCanceled() || isCheckedIn() || !new Date().before(inDate));
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setRoom(HostelRoom room) {
        this.room = room;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCheckInRecord(CheckInRecord checkInRecord) {
        this.checkInRecord = checkInRecord;
    }
}
