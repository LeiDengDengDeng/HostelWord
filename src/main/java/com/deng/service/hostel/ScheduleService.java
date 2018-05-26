package com.deng.service.hostel;

import com.deng.pojo.hostel.BasicSchedule;
import com.deng.pojo.hostel.RoomType;
import com.deng.pojo.hostel.SpecialSchedule;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2/19 0019.
 */
public interface ScheduleService {
    BasicSchedule getBasicScheduleByHostelIdAndRoomType(long hostelId, RoomType type);

    /**
     * 获得最近一年的酒店基本计划
     *
     * @param hostelId
     * @return
     */
    List<BasicSchedule> getBasisSchedulesByHostelId(long hostelId);

    void modifyBasisSchedule(BasicSchedule basicSchedule);

    /**
     * 获得最近一年的酒店特殊计划
     *
     * @param hostelId
     * @return
     */
    List<SpecialSchedule> getSpecialScheduleByHostelId(long hostelId);

    void modifySpecialSchedule(SpecialSchedule specialSchedule);

    void addSpecialSchedule(SpecialSchedule specialSchedule);

    void deleteSpecialSchedule(SpecialSchedule specialSchedule);

    SpecialSchedule getSpecialSchedule(long hostelId, RoomType roomType, Date date);
}