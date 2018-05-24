package com.xiuxiuyu.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户工具类
 * @author 桂都
 *
 */
@Entity
@Table(name="role")
public class Role implements Serializable{
private Integer id;
private String name;
private Set<RolePermission> rolePermissions;
@OneToMany(fetch=FetchType.LAZY,mappedBy="role")
public Set<RolePermission> getRolePermissions() {
	return rolePermissions;
}
public void setRolePermissions(Set<RolePermission> rolePermissions) {
	this.rolePermissions = rolePermissions;
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
@Column(name="name")
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
