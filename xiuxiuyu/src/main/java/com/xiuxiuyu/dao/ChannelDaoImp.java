package com.xiuxiuyu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiuxiuyu.beans.Channel;

@Repository
public class ChannelDaoImp implements IChannelDao {
	@Autowired
	private SessionFactory sessionFactory;


public List getChannelList(Integer parent_id) {
	/*sessionFactory .openSession().createSQLQuery("select * from channel where parent_id="+parent_id).addEntity(Channel.class).list();*/
	Session session=sessionFactory.getCurrentSession();
	String cs="from Channel where parentId="+parent_id+" order by orderBy";
	List list=session.createQuery(cs).list();
	return list;
}


public Channel channelSave(Channel channel) {
	Session session=sessionFactory.getCurrentSession();
	if(channel.getOrderBy()==null){
		channel.setOrderBy(10);
	}
    session.saveOrUpdate(channel);
	return channel;
}



public Channel channelUpdata(Channel channel) {
	// TODO Auto-generated method stub
	Session session=sessionFactory.getCurrentSession();
	session.update(channel);
	return channel;
}

public Channel getChannelById(Integer id) {
	return sessionFactory.getCurrentSession().get(Channel.class, id);
}


public void channelDelete(Channel channel) {
	// TODO Auto-generated method stub
	Session session=sessionFactory.getCurrentSession();
	
	 session.delete(channel);
}


public Channel getChannelInfoByUrl(String url) {
	StringBuilder builder = new StringBuilder();
	builder.append("from Channel where url='");
	builder.append(url);
	builder.append("'");
	Channel channel=(Channel) sessionFactory.getCurrentSession().createQuery(builder.toString()).uniqueResult();
	return channel;
}







}
