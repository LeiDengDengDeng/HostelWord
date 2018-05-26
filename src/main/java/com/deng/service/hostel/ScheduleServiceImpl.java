package com.deng.service.hostel;

import com.deng.dao.hostel.BasicScheduleDaoImpl;
import com.deng.dao.hostel.SpecialScheduleDaoImpl;
import com.deng.pojo.hostel.BasicSchedule;
import com.deng.pojo.hostel.RoomType;
import com.deng.pojo.hostel.SpecialSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/2/23.
 */
@Service("ScheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private BasicScheduleDaoImpl basicScheduleDao;
    @Autowired
    private SpecialScheduleDaoImpl specialScheduleDao;

    public BasicSchedule getBasicScheduleByHostelIdAndRoomType(long hostelId, RoomType type) {
        return basicScheduleDao.getBasisSchedulesByHostelIdAndRoomType(hostelId, type);
    }

    public List<BasicSchedule> getBasisSchedulesByHostelId(long hostelId) {
        return basicScheduleDao.getBasisSchedulesByHostelId(hostelId);
    }

    public void modifyBasisSchedule(BasicSchedule basicSchedule) {
        basicScheduleDao.update(basicSchedule);
    }

    public List<SpecialSchedule> getSpecialScheduleByHostelId(long hostelId) {
        return specialScheduleDao.getSpecialScheduleByHostelId(hostelId);
    }

    public void modifySpecialSchedule(SpecialSchedule specialSchedule) {
        specialScheduleDao.update(specialSchedule);
    }

    public void addSpecialSchedule(SpecialSchedule specialSchedule) {
        specialScheduleDao.save(specialSchedule);
    }

    public void deleteSpecialSchedule(SpecialSchedule specialSchedule) {
        specialScheduleDao.delete(specialSchedule);
    }

    public SpecialSchedule getSpecialSchedule(long hostelId, RoomType roomType, Date date) {
        return specialScheduleDao.getSpecialSchedulesByHostelIdAndRoomTypeAndDate(hostelId, roomType, date);
    }
}
