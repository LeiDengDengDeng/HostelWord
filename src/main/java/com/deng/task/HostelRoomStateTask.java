package com.deng.task;

import com.deng.dao.hostel.HostelDaoImpl;
import com.deng.dao.hostel.HostelRoomDaoImpl;
import com.deng.pojo.hostel.Hostel;
import com.deng.pojo.hostel.HostelRoom;
import com.deng.pojo.hostel.HostelRoomState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * Created by deng on 2017/3/16.
 */
@Service("HostelRoomStateTask")
@Transactional
public class HostelRoomStateTask {
    @Autowired
    private HostelDaoImpl hostelDao;
    @Autowired
    private HostelRoomDaoImpl hostelRoomDao;

    /**
     * 每天凌晨一点执行，增加新一天的酒店房间状态
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void addState() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 6);

        for (Hostel hostel : hostelDao.getRunningHostels()) {
            for (HostelRoom room : hostelRoomDao.getHostelRooms(hostel.getId())) {
                room.getHostelRoomStates().add(new HostelRoomState(room, false, false, c.getTime()));
                hostelRoomDao.update(room);
            }
        }
    }
}
