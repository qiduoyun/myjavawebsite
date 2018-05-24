package com.xiuxiuyu.dao;

import java.util.List;

import com.xiuxiuyu.beans.Channel;



public interface IChannelDao {

public List getChannelList(Integer parent_id);
public Channel channelSave(Channel channel);

public Channel channelUpdata(Channel channel);


/**
 * 获取栏目信息
 * @param id  栏目ID
 * @return
 */
public Channel getChannelById(Integer id);
public void channelDelete(Channel channel);
/**
 * 根据栏目路径获取栏目信息
 * @param url
 * @return
 */
public Channel getChannelInfoByUrl(String url);
}
