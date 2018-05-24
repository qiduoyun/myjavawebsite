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
 * 内容实体类
 * @author 桂都
 *
 */
@Entity
@Table(name = "content")
public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 内容表自增id
	private Integer channelId;// 栏目ID
	private String contentTemplate;// 内容模板
	private String txt;// 文章内容
	private String imgPath;// 内容标题图片
	private String title;// 内容标题
	private String description;// 内容说明
	private String keyword;// 关键词
	private String author;// 作者
	private String url;// 内容url
	private Date updateTime;// 更新时间
	private Integer type;// 内容类型（1：普通，2：图文，3：视频）
	private String filePath;// 文件路径
	private Integer videoType;// 视频类型（1：mp4,2:flv...）
	private Integer state;// 内容状态：（0：正常,-1：已删除）
	private Integer views;//浏览量
	private Channel channel;// 栏目

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
