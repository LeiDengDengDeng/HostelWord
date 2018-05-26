package com.deng.service.hostel;

import com.deng.aspect.LogType;
import com.deng.dao.hostel.HostelDaoImpl;
import com.deng.pojo.hostel.*;
import com.deng.pojo.log.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by deng on 2017/2/23.
 */
@Service("HostelService")
@Transactional
public class HostelServiceImpl implements HostelService {
    @Autowired
    private HostelDaoImpl hostelDao;

    public long registerAsHostel(Hostel hostel) {
        return (Long) hostelDao.save(hostel);
    }

    public Hostel getHostel(long id) {
        return hostelDao.get(Hostel.class, id);
    }

    public void modifyHostel(Hostel hostel) {
        hostelDao.update(hostel);
    }

    public List<Hostel> getExaminingHostels() {
        return hostelDao.getExaminingHostels();
    }

    public List<Hostel> getRunningHostels() {
        return hostelDao.getRunningHostels();
    }

    @LogType(operatorType = OperatorType.MEMBER, operationName = "审批酒店信息")
    public void examineHostel(long id, boolean passed) {
        Hostel hostel = getHostel(id);
        if (passed) {
            hostel.setState(HostelState.NORMAL);

            // 初始化房间和房间状态
            Set<HostelRoom> hostelRooms = new HashSet<HostelRoom>();
            for (int m = 1; m <= 4; m++) {
                for (int i = 1; i <= 10; i++) {
                    HostelRoom room = new HostelRoom();
                    room.setHostel(hostel);
                    room.setRoomNum(8000 + i + m * 100);
                    room.setRoomType(RoomType.values()[m - 1]);

                    List<HostelRoomState> states = new ArrayList<HostelRoomState>();
                    for (int n = 0; n < 7; n++) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.DATE, c.get(Calendar.DATE) + n);
                        states.add(new HostelRoomState(room, false, false, c.getTime()));
                    }
                    room.setHostelRoomStates(states);
                    hostelRooms.add(room);
                }
            }
            hostel.setHostelRooms(hostelRooms);

            // 初始化酒店基本计划
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);

            Set<BasicSchedule> basicSchedules = new HashSet<BasicSchedule>();
            BasicSchedule singleBs = new BasicSchedule(hostel, RoomType.SINGLE, 200, 188, 166, 150, year);
            BasicSchedule doubleBs = new BasicSchedule(hostel, RoomType.DOUBLE, 400, 388, 366, 350, year);
            BasicSchedule suitBs = new BasicSchedule(hostel, RoomType.SUIT, 600, 588, 566, 550, year);
            BasicSchedule presidentSuitBs = new BasicSchedule(hostel, RoomType.PRESIDENT_ROOM, 1000, 988, 966, 950, year);
            basicSchedules.add(singleBs);
            basicSchedules.add(doubleBs);
            basicSchedules.add(suitBs);
            basicSchedules.add(presidentSuitBs);
            hostel.setBasicSchedules(basicSchedules);

        } else {
            hostel.setState(HostelState.STOPPED);
        }
        hostelDao.update(hostel);
    }

    public void gainMoney(long id, int money) {
        Hostel hostel = hostelDao.get(Hostel.class, id);
        hostel.setEarnMoney(hostel.getEarnMoney() + money);
        hostel.setTotalEarnMoney(hostel.getTotalEarnMoney() + money);
    }

    public void gainSettleMoney(long id, int money) {
        Hostel hostel = hostelDao.get(Hostel.class, id);
        hostel.setEarnMoney(0);
        hostel.setMoney(hostel.getMoney() + money);
    }
}
