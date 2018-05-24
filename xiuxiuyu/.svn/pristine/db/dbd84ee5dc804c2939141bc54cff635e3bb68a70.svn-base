package com.xiuxiuyu.dao;

import java.util.List;

import com.xiuxiuyu.beans.Content;

public interface IContentDao {
	/**
	 * 根据内容ID获取内容对象
	 * @param id
	 * @return
	 */
   public Content getContentById(Integer id);
   /**
    * 查询内容列表
    * @param sql
    * @return
    */
   public List<Content> findContentList(String sql,List params,Integer page,Integer pageSize);
   /**
    * 查询记录条数
    * @param sql
    * @return
    */
   public Integer getTotal(String sql,List params);
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
    * 获取内容信息
    * @param sql
    * @return
    */
   public Content getContentBySQL(String sql);
}
