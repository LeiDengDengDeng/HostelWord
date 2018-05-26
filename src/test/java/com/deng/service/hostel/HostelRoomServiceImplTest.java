package com.deng.service.hostel;

import com.deng.pojo.hostel.RoomType;
import com.deng.service.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by deng on 2017/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-servlet.xml",
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/hib-config.xml"})
public class HostelRoomServiceImplTest {
    @Autowired
    private HostelRoomService hostelRoomService;

    @Test
    public void getRandomFreeRoomByTypeAndDate() throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 2);

        System.out.println(hostelRoomService.getRandomFreeRoomByTypeAndDate(1, RoomType.PRESIDENT_ROOM, TimeUtil.convertDateStr("2017-03-18"), TimeUtil.convertDateStr("2017-03-19")));
    }

    @Test
    public void getPrice() throws Exception {
        System.out.println(hostelRoomService.getPrice(1, RoomType.PRESIDENT_ROOM, TimeUtil.convertDateStr("2017-03-18"), TimeUtil.convertDateStr("2017-03-19"), 1));
    }
}