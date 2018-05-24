package com.xiuxiuyu.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 上传文件实体类
 * @author 桂都
 *
 */
@Entity
@Table(name = "upload_file")
public class UploadFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String path;// 文件路径，主键
	private String name;// 文件名称
	private String realName;// 文件真实名称
	private Date uploadTime;// 文件上传时间
    
	@Id
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(generator = "generator")
	@Column(name="path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "real_name")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upload_time")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "UploadFile [path=" + path + ", name=" + name + ", realName=" + realName + ", uploadTime=" + uploadTime
				+ "]";
	}
}
