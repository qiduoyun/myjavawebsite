package com.xiuxiuyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.dao.IChannelDao;
@Service
@Transactional
public class ChannelServiceImp implements IChannelService {
	@Autowired
	private IChannelDao channelDao;
    
	public List getChannelList(Integer parent_id) {
		return channelDao.getChannelList(parent_id);
	}
	public Channel channelSave(Channel channel) {
		return channelDao.channelSave(channel);
	}
	public Channel getChannelInfo(Integer id) {
		return channelDao.getChannelById(id);
	}
	public void channelDelete(Channel channel) {
		// TODO Auto-generated method stub
		channelDao.channelDelete(channel);
	}
	public Channel getChannelInfoByUrl(String url) {
		// TODO Auto-generated method stub
		return channelDao.getChannelInfoByUrl(url);
	}

}
