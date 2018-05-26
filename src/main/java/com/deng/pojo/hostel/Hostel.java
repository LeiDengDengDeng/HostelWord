package com.deng.pojo.hostel;

import com.deng.pojo.log.Log;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by dell on 1/26 0026.
 */
@Entity(name = "hostel")
public class Hostel {
    private long id;
    private String password;
    private String name;
    private HostelState state; // 旅店状态
    private String province;
    private String city;
    private String address;

    private Set<BasicSchedule> basicSchedules;
    private Set<SpecialSchedule> specialSchedules;
    private Set<HostelRoom> hostelRooms;

    private int money; // 分到的钱
    private int earnMoney; // 截止上次结算后赚到的钱
    private long totalEarnMoney; // 总计赚到的钱

    private Hostel() {
    }

    public Hostel(String password, String name, HostelState state, String province, String city, String address) {
        this.password = password;
        this.name = name;
        this.state = state;
        this.province = province;
        this.city = city;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    public HostelState getState() {
        return state;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<BasicSchedule> getBasicSchedules() {
        return basicSchedules;
    }

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<SpecialSchedule> getSpecialSchedules() {
        return specialSchedules;
    }

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<HostelRoom> getHostelRooms() {
        return hostelRooms;
    }

    @Column(name = "money")
    public int getMoney() {
        return money;
    }

    @Column(name = "earnmoney")
    public int getEarnMoney() {
        return earnMoney;
    }

    @Column(name = "totalearnmoney")
    public long getTotalEarnMoney() {
        return totalEarnMoney;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(HostelState state) {
        this.state = state;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBasicSchedules(Set<BasicSchedule> basicSchedules) {
        this.basicSchedules = basicSchedules;
    }

    public void setSpecialSchedules(Set<SpecialSchedule> specialSchedules) {
        this.specialSchedules = specialSchedules;
    }

    public void setHostelRooms(Set<HostelRoom> hostelRooms) {
        this.hostelRooms = hostelRooms;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setEarnMoney(int earnMoney) {
        this.earnMoney = earnMoney;
    }

    public void setTotalEarnMoney(long totalEarnMoney) {
        this.totalEarnMoney = totalEarnMoney;
    }
}
