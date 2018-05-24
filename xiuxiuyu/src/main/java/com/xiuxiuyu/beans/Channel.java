package com.xiuxiuyu.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ��Ŀ
 * 
 * @author ��
 *
 */
@Entity
@Table(name = "channel")
public class Channel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// ��Ŀ������ID
	private String name;// ��Ŀ����
	private String url;// ��Ŀ·��
	private Integer parentId;// ����ĿID
	private String channelTemplate;// ��Ŀģ��
	private String contentTemplate;// ����ģ��
	private String imgPath;// ��ĿͼƬ·��
	private String description;// ��Ŀ˵��
	private String keyword;// �ؼ���
	private Integer orderBy;//����
	private Channel parentChannel;// ����Ŀ
	private Set<Channel> childrenChannel;// ����Ŀ
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id",insertable=false,updatable=false)
	public Channel getParentChannel() {
		return parentChannel;
	}

	public void setParentChannel(Channel parentChannel) {
		this.parentChannel = parentChannel;
	}

	@OneToMany(fetch=FetchType.LAZY,targetEntity=Channel.class,mappedBy="parentChannel")
	public Set<Channel> getChildrenChannel() {
		return childrenChannel;
	}

	public void setChildrenChannel(Set<Channel> childrenChannel) {
		this.childrenChannel = childrenChannel;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
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

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "channel_template")
	public String getChannelTemplate() {
		return channelTemplate;
	}

	public void setChannelTemplate(String channelTemplate) {
		this.channelTemplate = channelTemplate;
	}

	@Column(name = "content_template")
	public String getContentTemplate() {
		return contentTemplate;
	}

	public void setContentTemplate(String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}

	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
   
	@Column(name = "order_by")
   public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	private String fullUrl;	//��Ŀ����·��
@Transient
	public String getFullUrl(){
		return "/"+url+"/index.jhtml";
		
		}
	public void setFullUrl(String fullUrl){
		this.fullUrl=fullUrl;
	}
	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", url=" + url + ", parentId=" + parentId + ", channelTemplate="
				+ channelTemplate + ", contentTemplate=" + contentTemplate + ", imgPath=" + imgPath + ", description="
				+ description + ", keyword=" + keyword + "]";
	}

}
