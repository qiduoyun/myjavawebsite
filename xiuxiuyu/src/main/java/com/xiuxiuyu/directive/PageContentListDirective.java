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
import com.xiuxiuyu.util.PageInfo;
import com.xiuxiuyu.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * ��ҳ�����б��ǩ
 * @author ��
 *
 */
@Component(value="page_content_list")
public class PageContentListDirective implements TemplateDirectiveModel{
	@Autowired
	private IContentService contentService;
	@Autowired
	private IChannelService channelService;

	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		List<Integer> channelIds = new ArrayList<Integer>();
		String channelIdStr = DirectiveUtil.getStringValue("channelId", params);
		if(channelIdStr.indexOf(',')!=-1){//֧�ֶ����Ŀ
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
		//����
		String orderByStr=DirectiveUtil.getStringValue("orderBy", params);
		if(StringUtils.isEmpty(orderByStr)){
			orderByStr="1";
		}
		//��������
		String typeStr=DirectiveUtil.getStringValue("type", params);
		Integer type=null;
		if(!StringUtils.isEmpty(typeStr)){
			type=Integer.valueOf(typeStr);
		}
		Integer orderBy = Integer.valueOf(orderByStr);
		//����
		String countStr=DirectiveUtil.getStringValue("count", params);
		Integer count;
		if(StringUtils.isEmpty(countStr)){
			count = Integer.MAX_VALUE;
		}else{
			count = Integer.valueOf(countStr);
		}
		//��ǰҳ
		String pageStr=DirectiveUtil.getStringValue("page", params);
		Integer page;
		if(StringUtils.isEmpty(pageStr)){
			page = 1;
		}else{
			page = Integer.valueOf(pageStr);
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
		PageInfo<Content> pageInfo = contentService.findContentPageInfo(ids,count, order,page,type);
		env.setVariable("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageInfo.getResultList()));
		env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageInfo.getPage()));
		int totalPage = (pageInfo.getRows()+count-1)/count;
		env.setVariable("total", ObjectWrapper.DEFAULT_WRAPPER.wrap(totalPage));
		env.setVariable("rows", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageInfo.getRows()));
		String selectStr="";
		for(int index=1;index<=totalPage;index++){
			if(index == page){
				selectStr+="<option value='"+index+"' selected='selected'>"+index+"</option>";
			}else{
				selectStr+="<option value='"+index+"'>"+index+"</option>";
			}
		}
		env.setVariable("selectStr", ObjectWrapper.DEFAULT_WRAPPER.wrap(selectStr));
		body.render(env.getOut());
	}
	/**
	 * ��ȡ��Ŀ�Լ���Ӧ����Ŀ������ID
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
