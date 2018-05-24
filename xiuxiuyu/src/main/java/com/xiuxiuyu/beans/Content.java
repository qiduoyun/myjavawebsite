package com.xiuxiuyu.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ����ʵ����
 * @author ��
 *
 */
@Entity
@Table(name = "content")
public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// ���ݱ�����id
	private Integer channelId;// ��ĿID
	private String contentTemplate;// ����ģ��
	private String txt;// ��������
	private String imgPath;// ���ݱ���ͼƬ
	private String title;// ���ݱ���
	private String description;// ����˵��
	private String keyword;// �ؼ���
	private String author;// ����
	private String url;// ����url
	private Date updateTime;// ����ʱ��
	private Integer type;// �������ͣ�1����ͨ��2��ͼ�ģ�3����Ƶ��
	private String filePath;// �ļ�·��
	private Integer videoType;// ��Ƶ���ͣ�1��mp4,2:flv...��
	private Integer state;// ����״̬����0������,-1����ɾ����
	private Integer views;//�����
	private Channel channel;// ��Ŀ

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="channel_id",insertable=false,updatable=false)
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Id
	@GenericGenerator(name = "generator", strategy="increment")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
    @Column(name="channel_id")
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	@Column(name = "content_template")
	public String getContentTemplate() {
		return contentTemplate;
	}

	public void setContentTemplate(String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}

	@Column(name = "txt")
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Column(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "file_path")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "video_type")
	public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    @Column(name = "views")
	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	private String fullUrl;
	public void setFullUrl(String fullUrl){
		this.fullUrl=fullUrl;
	}
	@Transient
	public String getFullUrl(){
		return "/"+this.getUrl()+"/"+this.id+".jhtml";
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", channelId=" + channelId + ", contentTemplate=" + contentTemplate + ", txt="
				+ txt + ", imgPath=" + imgPath + ", title=" + title + ", description=" + description + ", keyword="
				+ keyword + ", author=" + author + ", url=" + url + ", updateTime=" + updateTime + ", type=" + type
				+ ", filePath=" + filePath + ", videoType=" + videoType + ", state=" + state
				+ "]";
	}

}
