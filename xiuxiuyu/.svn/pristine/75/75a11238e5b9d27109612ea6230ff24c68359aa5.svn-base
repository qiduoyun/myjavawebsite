package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.service.IChannelService;
import com.xiuxiuyu.service.IContentService;
import com.xiuxiuyu.util.DirectiveUtil;
import com.xiuxiuyu.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 内容列表标签
 * @author 桂都
 *
 */
@Component(value="content_list")
public class ContentListDirective implements TemplateDirectiveModel{
	@Autowired
	private IContentService contentService;
	@Autowired
	private IChannelService channelService;

	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		List<Integer> channelIds = new ArrayList<Integer>();
		String channelIdStr = DirectiveUtil.getStringValue("channelId", params);
		if(!StringUtils.isEmpty(channelIdStr)&&channelIdStr.indexOf(',')!=-1){//支持多个栏目
			String[] ids = channelIdStr.split(",");
			for(String id:ids){
				Channel channel = channelService.getChannelInfo(Integer.valueOf(id));
				sembleChannelIds(channel,channelIds);
			}
		}else{
			if(!StringUtils.isEmpty(channelIdStr)){
				Channel channel = channelService.getChannelInfo(Integer.valueOf(channelIdStr));
				sembleChannelIds(channel,channelIds);
			}
		}
		//排序
		String orderByStr=DirectiveUtil.getStringValue("orderBy", params);
		if(StringUtils.isEmpty(orderByStr)){
			orderByStr="1";
		}
		Integer orderBy = Integer.valueOf(orderByStr);
		//数量
		String countStr=DirectiveUtil.getStringValue("count", params);
		Integer count;
		if(StringUtils.isEmpty(countStr)){
			count = Integer.MAX_VALUE;
		}else{
			count = Integer.valueOf(countStr);
		}
		//内容类型
		String typeStr=DirectiveUtil.getStringValue("type", params);
		Integer type=null;
		if(!StringUtils.isEmpty(typeStr)){
			type=Integer.valueOf(typeStr);
		}		
		Integer[] ids=new Integer[channelIds.size()];
		ids=channelIds.toArray(ids);
		String order="";
		switch (orderBy) {
		case 1:
			order=" order by updateTime desc ";
			break;
		case 2:
			order=" order by updateTime ";
		case 3:
			order=" order views desc ";
		default:
			order=" order by updateTime desc ";
			break;
		}
		List<Content> list = contentService.findContentListByChannelId(ids,count, order,type);
		env.setVariable("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		body.render(env.getOut());
	}
	/**
	 * 获取栏目以及对应子栏目的所有ID
	 * @param channel
	 * @param ids
	 */
	private void sembleChannelIds(Channel channel,List<Integer> ids){
		ids.add(channel.getId());
		Set<Channel> channels = channel.getChildrenChannel();
		if(!channels.isEmpty()){
			for(Channel child:channels){
				sembleChannelIds(child,ids);
			}
		}
	}

}
