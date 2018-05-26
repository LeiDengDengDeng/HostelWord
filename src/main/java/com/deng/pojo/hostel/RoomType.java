package com.deng.pojo.hostel;

/**
 * Created by dell on 2/19 0019.
 * 酒店房间类型
 */
public enum RoomType {
    SINGLE("标准单人间"), DOUBLE("标准双人间"), SUIT("套房"), PRESIDENT_ROOM("总统套房");

    private String typeStr;

    RoomType(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public static RoomType getRoomTypeByTypeStr(String typeStr) {
        for (int i = 0; i < RoomType.values().length; i++) {
            if (RoomType.values()[i].getTypeStr().equals(typeStr)) return RoomType.values()[i];
        }
        return null;
    }

}
