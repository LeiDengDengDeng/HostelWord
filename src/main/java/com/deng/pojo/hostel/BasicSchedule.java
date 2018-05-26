package com.deng.pojo.hostel;

import javax.persistence.*;

/**
 * Created by dell on 2/19 0019.
 * 酒店基本计划，该计划以年为单位
 */
@Entity(name = "basicschedule")
public class BasicSchedule {
    private int id;

    private Hostel hostel; // 旅店
    private RoomType roomType; // 房间类型
    private int vip0Price; // 非会员价格
    private int vip1Price; // vip1价格
    private int vip2Price; // vip2价格
    private int vip3Price; // vip3价格
    private int year; // 计划归属年份

    private BasicSchedule() {
    }

    public BasicSchedule(Hostel hostel, RoomType roomType, int vip0Price, int vip1Price, int vip2Price, int vip3Price, int year) {
        this.hostel = hostel;
        this.roomType = roomType;
        this.vip0Price = vip0Price;
        this.vip1Price = vip1Price;
        this.vip2Price = vip2Price;
        this.vip3Price = vip3Price;
        this.year = year;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "hostelid")
    public Hostel getHostel() {
        return hostel;
    }

    @Column(name = "roomtype")
    @Enumerated(EnumType.STRING)
    public RoomType getRoomType() {
        return roomType;
    }

    @Column(name = "vip0Price")
    public int getVip0Price() {
        return vip0Price;
    }

    @Column(name = "vip1price")
    public int getVip1Price() {
        return vip1Price;
    }

    @Column(name = "vip2price")
    public int getVip2Price() {
        return vip2Price;
    }

    @Column(name = "vip3price")
    public int getVip3Price() {
        return vip3Price;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setVip0Price(int vip0Price) {
        this.vip0Price = vip0Price;
    }

    public void setVip1Price(int vip1Price) {
        this.vip1Price = vip1Price;
    }

    public void setVip2Price(int vip2Price) {
        this.vip2Price = vip2Price;
    }

    public void setVip3Price(int vip3Price) {
        this.vip3Price = vip3Price;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
