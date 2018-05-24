package com.xiuxiuyu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.dao.IContentDao;
import com.xiuxiuyu.util.PageInfo;
import com.xiuxiuyu.util.StringUtils;

@Service
@Transactional
public class ContentServiceImp implements IContentService {
	@Autowired
    private IContentDao contentDao;
	public Content getContentById(Integer id) {
		return contentDao.getContentById(id);
	}
	public PageInfo<Content> findContentList(PageInfo pageInfo, Channel channel) {
		StringBuffer sql = new StringBuffer();
		List params = new ArrayList();
		sql.append("from Content where state=0");
		if(channel!=null&&channel.getId()!=null){
			sql.append(" and channelId=?");
			params.add(channel.getId());
		}
		Integer total = contentDao.getTotal("select count(*) "+sql.toString(), params);
		sql.append("order by updateTime desc");
		List<Content> list=contentDao.findContentList(sql.toString(), params, pageInfo.getPage(), pageInfo.getPageSize());
		pageInfo.setResultList(list);
		pageInfo.setRows(total);
		return pageInfo;
	}
	public void updateContent(Content content) {
		contentDao.updateContent(content);
	}
	public void deleteContent(Content content) {
        contentDao.deleteContent(content);
	}
	public Content getContentByUrlAndId(String url, Integer id) {
		return contentDao.getContentByUrlAndId(url, id);
	}
	public List<Content> findContentListByChannelId(Integer[] channelId,Integer count, String orderByStr,Integer type) {
		StringBuilder sql = new StringBuilder();
		if(StringUtils.isEmpty(orderByStr)){
			orderByStr = "";
		}
		sql.append(" from Content where state=0");
		if(type!=null){
			sql.append(" and type=").append(type);
		}
		if(channelId!=null&&channelId.length!=0){
			sql.append("  and channelId in("+StringUtils.joinString(channelId)+")  ");
		}
		sql.append(orderByStr);
		return contentDao.findContentList(sql.toString(), null, 1, count);
	}
	public PageInfo<Content> findContentPageInfo(Integer[] channelId, Integer count, String orderByStr, Integer page,Integer type) {
		PageInfo<Content> pageInfo = new PageInfo<Content>();
		StringBuffer sql = new StringBuffer();
		if(StringUtils.isEmpty(orderByStr)){
			orderByStr = "";
		}
		sql.append(" from Content where state=0");
		if(type!=null){
			sql.append(" and type=").append(type);
		}
		if(channelId!=null&&channelId.length!=0){
			sql.append("  and channelId in("+StringUtils.joinString(channelId)+")  ");
		}
		Integer total = contentDao.getTotal("select count(*) "+sql.toString(), null);
		sql.append(orderByStr);
		List<Content> list=contentDao.findContentList(sql.toString(), null, page, count);
		pageInfo.setPage(page);
		pageInfo.setResultList(list);
		pageInfo.setRows(total);
		return pageInfo;
	}
	public Content findContentBySQL(String sql) {
		return contentDao.getContentBySQL(sql);
	}
    
}
