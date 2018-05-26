package com.deng.pojo.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dell on 1/26 0026.
 */
@Entity(name = "bankcard")
public class BankCard {
	private long cardId; // 银行卡号
	private long id; // 身份证号
	private String name; // 持卡人姓名
	private int password; // 密码
	private long money; // 银行卡余额

	@Id
	@Column(name = "cardid")
	public long getCardId() {
		return cardId;
	}

	@Column(name = "id")
	public long getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "password")
	public int getPassword() {
		return password;
	}

	@Column(name = "money")
	public long getMoney() {
		return money;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public void setMoney(long money) {
		this.money = money;
	}
}
