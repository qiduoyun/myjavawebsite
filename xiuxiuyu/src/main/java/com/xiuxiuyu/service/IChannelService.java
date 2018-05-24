package com.xiuxiuyu.service;

import java.util.List;

import com.xiuxiuyu.beans.Channel;

public interface IChannelService {
public List getChannelList(Integer parent_id);
public Channel channelSave(Channel channel);
/**
 * ������ĿID��ȡ��Ŀ��Ϣ
 * @param id ��ĿID
 * @return
 */
public void channelDelete(Channel channel);
public Channel getChannelInfo(Integer id);
/**
 * ������Ŀ·����ȡ��Ŀ��Ϣ
 * @param url
 * @return
 */
public Channel getChannelInfoByUrl(String url);
}
