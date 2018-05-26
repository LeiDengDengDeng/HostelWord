package com.deng.pojo.member;

/**
 * Created by dell on 1/26 0026.
 */
public enum ConsumeType {
    ORDER("预定酒店房间"), CANCEL_ORDER("取消预定"), EXCHANGE_POINT("积分兑换"), MEMBER_COST("支付会费"), TOP_UP("充值"), SETTLE_ACCOUNT("结算");

    private String typeStr;

    ConsumeType(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }
}
