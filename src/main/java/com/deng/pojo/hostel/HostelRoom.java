package com.deng.pojo.hostel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by dell on 2/19 0019.
 * 具体某个日期下的酒店房间预定和入住情况
 */
@Entity(name = "hostelroom")
public class HostelRoom implements Serializable {
    private Hostel hostel;
    private int roomNum;
    private RoomType roomType;
    private List<HostelRoomState> hostelRoomStates;
    private Set<CheckInRecord> checkInRecords;

    @Id
    @Column(name = "roomnum")
    public int getRoomNum() {
        return roomNum;
    }

    @Column(name = "roomtype")
    @Enumerated(EnumType.STRING)
    public RoomType getRoomType() {
        return roomType;
    }

    @ManyToOne
    @JoinColumn(name = "hostelid", referencedColumnName = "id")
    @Id
    public Hostel getHostel() {
        return hostel;
    }

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("date desc")
    public List<HostelRoomState> getHostelRoomStates() {
        return hostelRoomStates;
    }

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<CheckInRecord> getCheckInRecords() {
        return checkInRecords;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setHostelRoomStates(List<HostelRoomState> hostelRoomStates) {
        this.hostelRoomStates = hostelRoomStates;
    }

    public void setCheckInRecords(Set<CheckInRecord> checkInRecords) {
        this.checkInRecords = checkInRecords;
    }
}
