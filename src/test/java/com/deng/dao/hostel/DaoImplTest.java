package com.deng.dao.hostel;

import com.deng.dao.member.MemberDaoImpl;
import com.deng.dao.order.OrderDaoImpl;
import com.deng.pojo.hostel.Hostel;
import com.deng.pojo.hostel.HostelRoom;
import com.deng.pojo.hostel.HostelRoomState;
import com.deng.pojo.hostel.RoomType;
import com.deng.pojo.member.Member;
import com.deng.pojo.order.RoomOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by deng on 2017/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-servlet.xml",
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/hib-config.xml"})
public class DaoImplTest {
    @Autowired
    private HostelDaoImpl hostelDao;
    @Autowired
    private OrderDaoImpl orderDao;
    @Autowired
    private HostelRoomDaoImpl hostelRoomDao;
    @Autowired
    private MemberDaoImpl memberDao;

    @Test
    public void add() throws Exception {
        Hostel hostel = hostelDao.get(Hostel.class, Long.parseLong("1000001"));
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
        hostelDao.update(hostel);
    }

    @Test
    public void addOrder() throws Exception {
        HostelRoom room = hostelRoomDao.getHostelRoomByHostelAndRoomNum(1, 8403);
        orderDao.save(new RoomOrder(memberDao.get(Member.class, Long.parseLong("1000003")), room, 800, 8, new Date(), new Date(), new Date()));
    }

    @Test
    public void get() throws Exception {
        assertEquals(orderDao.getOrdersByMemberId(1000003).size(), 1);
    }
}