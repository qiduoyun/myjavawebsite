package com.xiuxiuyu.service;


import java.util.List;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.util.PageInfo;

public interface IContentService {
	/**
	 * 根据内容ID获取内容对象
	 * @param id
	 * @return
	 */
   public Content getContentById(Integer id);
   /**
    * 查询内容列表
    * @param pageInfo
    * @param channel
    * @return
    */
   public PageInfo<Content> findContentList(PageInfo pageInfo,Channel channel);
   /**
    * 更新内容
    * @param content
    */
   public void updateContent(Content content);
   /**
    * 彻底删除内容
    * @param content
    */
   public void deleteContent(Content content);
   /**
    * 根据内容URL和ID获取内容对象
    * @param url
    * @param id
    */
   public Content getContentByUrlAndId(String url,Integer id);
   /**
    * 根据栏目ID获取栏目列表信息
    * @param channelId
    * @param orderByStr
    * @return
    */
   public List<Content> findContentListByChannelId(Integer[] channelId,Integer count,String orderByStr,Integer type);
   /**
    * 分页查询内容列表
    * @param channelId
    * @param count
    * @param orderByStr
    * @param page
    * @return
    */
   public PageInfo<Content> findContentPageInfo(Integer[] channelId,Integer count,String orderByStr,Integer page,Integer type);
   /**
    * 查询内容信息
    * @param sql
    * @return
    */
   public Content findContentBySQL(String sql);
}
