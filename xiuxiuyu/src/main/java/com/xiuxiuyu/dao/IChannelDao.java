package com.xiuxiuyu.dao;

import java.util.List;

import com.xiuxiuyu.beans.Channel;



public interface IChannelDao {

public List getChannelList(Integer parent_id);
public Channel channelSave(Channel channel);

public Channel channelUpdata(Channel channel);


/**
 * ��ȡ��Ŀ��Ϣ
 * @param id  ��ĿID
 * @return
 */
public Channel getChannelById(Integer id);
public void channelDelete(Channel channel);
/**
 * ������Ŀ·����ȡ��Ŀ��Ϣ
 * @param url
 * @return
 */
public Channel getChannelInfoByUrl(String url);
}
