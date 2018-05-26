package com.deng.service.member;

import com.deng.pojo.member.ConsumeType;
import com.deng.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by deng on 2017/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-servlet.xml",
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/hib-config.xml"})
public class MemberServiceImplTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void consumeMemberMoney() throws Exception {
        memberService.consumeMemberMoney(1000003, -10000, ConsumeType.ORDER);
    }

    @Test
    public void pointToMoney() {
        try {
            memberService.pointToMoney(1000003, 100);
        } catch (ServiceException e) {
            System.out.println(e.getErrorMessage());
        }
    }

}