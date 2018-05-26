package com.deng.pojo.hostel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by deng on 2017/3/16.
 */
@Entity(name = "hostelroomstate")
public class HostelRoomState implements Serializable {
    private HostelRoom room;
    private boolean isOrdered;
    private boolean isCheckedIn;
    private Date date;

    private HostelRoomState() {
    }

    public HostelRoomState(HostelRoom room, boolean isOrdered, boolean isCheckedIn, Date date) {
        this.room = room;
        this.isOrdered = isOrdered;
        this.isCheckedIn = isCheckedIn;
        this.date = date;
    }

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "hostelid", referencedColumnName = "hostelid", insertable = false, updatable = false),
            @JoinColumn(name = "roomnum", referencedColumnName = "roomnum", insertable = false, updatable = false)
    })
    public HostelRoom getRoom() {
        return room;
    }

    @Column(name = "isorded")
    public boolean isIsOrdered() {
        return isOrdered;
    }

    @Column(name = "ischeckedin")
    public boolean isIsCheckedIn() {
        return isCheckedIn;
    }

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setRoom(HostelRoom room) {
        this.room = room;
    }

    public void setIsOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    public void setIsCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
