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
 * �ϴ��ļ�ʵ����
 * @author ��
 *
 */
@Entity
@Table(name = "upload_file")
public class UploadFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String path;// �ļ�·��������
	private String name;// �ļ�����
	private String realName;// �ļ���ʵ����
	private Date uploadTime;// �ļ��ϴ�ʱ��
    
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
