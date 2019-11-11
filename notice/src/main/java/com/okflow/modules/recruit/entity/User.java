package com.okflow.modules.recruit.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import com.okflow.common.persistence.IdEntity;

/**
 * 用户Entity
 * 
 * @author xiaofanglin
 * @version 2019-09-13
 */
@Entity
@Table(name = "sys_user")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity<User> {

	private static final long serialVersionUID = 1L;
	private String userName; // 用户名
	private String passWord; // 用户密码
	private String phoneNumber; // 电话号码
	private String email; // email地址
	private String contactqq; // 联系qq
	private String isAdmin; // 是否管理员

	@Length(min = 0, max = 11)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Length(min = 0, max = 25)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min = 0, max = 18)
	public String getContactqq() {
		return contactqq;
	}

	public void setContactqq(String contactqq) {
		this.contactqq = contactqq;
	}

	@Length(min = 0, max = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Length(min = 0, max = 20)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Length(min = 0, max = 1)
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}