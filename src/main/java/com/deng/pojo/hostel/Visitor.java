package com.deng.pojo.hostel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by deng on 2017/3/20.
 */
@Entity(name = "visitor")
public class Visitor{
    private int visitorId;
    private CheckInRecord checkInRecord;
    private String name;
    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitorId")
    public int getVisitorId() {
        return visitorId;
    }

    @ManyToOne
    @JoinColumn(name = "recordId")
    public CheckInRecord getCheckInRecord() {
        return checkInRecord;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public void setCheckInRecord(CheckInRecord checkInRecord) {
        this.checkInRecord = checkInRecord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
