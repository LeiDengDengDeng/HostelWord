package com.deng.service.hostel;

import com.deng.pojo.hostel.Hostel;
import com.deng.pojo.member.ConsumeType;
import com.deng.service.exception.ServiceException;

import java.util.List;

/**
 * Created by dell on 2/19 0019.
 */
public interface HostelService {
    /**
     * 注册成为酒店
     *
     * @param hostel 酒店信息
     * @return id
     */
    long registerAsHostel(Hostel hostel);

    /**
     * 获得酒店信息
     *
     * @param id 酒店id
     * @return 酒店信息
     */
    Hostel getHostel(long id);

    /**
     * 修改酒店信息
     *
     * @param hostel 修改后的酒店信息
     */
    void modifyHostel(Hostel hostel);

    /**
     * 获得待审批的酒店信息
     *
     * @return 待审批的酒店信息
     */
    List<Hostel> getExaminingHostels();

    /**
     * 获得运营中的酒店信息
     *
     * @return 运营中的酒店信息
     */
    List<Hostel> getRunningHostels();

    /**
     * 审批酒店
     *
     * @param id     酒店id
     * @param passed 是否通过
     */
    void examineHostel(long id, boolean passed);

    void gainMoney(long id, int money);

    void gainSettleMoney(long id, int money);
}
