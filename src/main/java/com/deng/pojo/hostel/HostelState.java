package com.deng.pojo.hostel;

/**
 * Created by dell on 1/26 0026.
 */
public enum HostelState {
	NORMAL("运营中"),EXAMINING(""),STOPPED("已停运");

	private String typeStr;

	HostelState(String typeStr){
		this.typeStr = typeStr;
	}

	public String getTypeStr() {
		return typeStr;
	}
}
