package com.deng.pojo.hostel;

/**
 * Created by deng on 2017/3/20.
 */
public class HostelRoomBO {
    private int roomNum;
    private RoomType type;
    private boolean ordered;
    private boolean checkedIn;

    public HostelRoomBO(int roomNum, RoomType type, boolean ordered, boolean checkedIn) {
        this.roomNum = roomNum;
        this.type = type;
        this.ordered = ordered;
        this.checkedIn = checkedIn;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
