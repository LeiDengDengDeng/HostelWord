package com.deng.pojo.member;

/**
 * Created by dell on 1/26 0026.
 */
public enum MemberState {
    NORMAL("可使用"), PAUSED("暂停中"), STOPPED("已停用");

    private String typeStr;

    MemberState(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }
}
