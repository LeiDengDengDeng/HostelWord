package com.deng.pojo.hostel;

import com.deng.pojo.member.Member;
import com.deng.pojo.order.RoomOrder;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by deng on 2017/3/20.
 */
@Entity(name = "checkinrecord")
public class CheckInRecord {
    private long id;
    private HostelRoom room;
    private RoomOrder order;
    private Member member;
    private Date inDate;
    private Date outDate;
    private Set<Visitor> visitors;

    private CheckInRecord() {
    }

    public CheckInRecord(HostelRoom hostelRoom, RoomOrder order, Member member, Date inDate, Date outDate, Set<Visitor> visitors) {
        this.room = hostelRoom;
        this.order = order;
        this.member = member;
        this.inDate = inDate;
        this.outDate = outDate;
        this.visitors = visitors;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hostelid", referencedColumnName = "hostelid"),
            @JoinColumn(name = "roomnum", referencedColumnName = "roomnum")
    })
    public HostelRoom getRoom() {
        return room;
    }

    @OneToOne(optional = true)
    @JoinColumn(name = "orderId")
    public RoomOrder getOrder() {
        return order;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "memberid")
    public Member getMember() {
        return member;
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

    @OneToMany(mappedBy = "checkInRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRoom(HostelRoom room) {
        this.room = room;
    }

    public void setOrder(RoomOrder order) {
        this.order = order;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }
}
