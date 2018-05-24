package com.xiuxiuyu.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
/**
 * �û�������
 * @author ��
 *
 */
@Entity
@Table(name="user")
public class User implements Serializable{
private Integer id;
private String username;
private String password;
private String name;
private String email;
private String telphone;
private Date registerTime;
private Date loginTime;
private Date updateTime;
private Integer roleId;
private Integer days;
private Integer state;
private Role role;
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="role_id",insertable=false,updatable=false)
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
@Id
@Column(name="id")
@GenericGenerator(name="generator",strategy="increment")
@GeneratedValue(generator="generator")
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Column(name="username")
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
@Column(name="password")
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Column(name="name")
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Column(name="email")
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Column(name="telphone")
public String getTelphone() {
	return telphone;
}
public void setTelphone(String telphone) {
	this.telphone = telphone;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="regist_time")
public Date getRegisterTime() {
	return registerTime;
}
public void setRegisterTime(Date registerTime) {
	this.registerTime = registerTime;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="login_time")
public Date getLoginTime() {
	return loginTime;
}
public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="update_time")
public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
@Column(name="state")
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}

@Column(name="role_id")
public Integer getRoleId() {
	return roleId;
}
public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}
@Column(name="days")
public Integer getDays() {
	return days;
}
public void setDays(Integer days) {
	this.days = days;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
			+ email + ", telphone=" + telphone + ", registerTime=" + registerTime + ", loginTime=" + loginTime
			+ ", updateTime=" + updateTime + ", state=" + state + "]";
}
}
