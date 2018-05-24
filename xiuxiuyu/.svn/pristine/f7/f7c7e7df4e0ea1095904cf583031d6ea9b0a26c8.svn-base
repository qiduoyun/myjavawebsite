package com.xiuxiuyu.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户工具类
 * @author 桂都
 *
 */
@Entity
@Table(name="role_permission")
public class RolePermission implements Serializable{
private Integer roleId;
private Integer permissionId;
private Role role;
private Permission permission;
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="role_id",insertable=false,updatable=false)
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="permission_id",insertable=false,updatable=false)
public Permission getPermission() {
	return permission;
}
public void setPermission(Permission permission) {
	this.permission = permission;
}
@Id
@GenericGenerator(name="generator",strategy="assigned")
@GeneratedValue(generator="generator")
@Column(name="role_id")
public Integer getRoleId() {
	return roleId;
}
public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}
@Id
@GenericGenerator(name="generator",strategy="assigned")
@GeneratedValue(generator="generator")
@Column(name="permission_id")
public Integer getPermissionId() {
	return permissionId;
}
public void setPermissionId(Integer permissionId) {
	this.permissionId = permissionId;
}
}
