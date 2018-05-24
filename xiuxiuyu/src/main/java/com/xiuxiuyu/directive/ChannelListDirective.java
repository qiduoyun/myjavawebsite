package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.service.IChannelService;
import com.xiuxiuyu.util.DirectiveUtil;
import com.xiuxiuyu.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component(value="channel_list")
public class ChannelListDirective implements TemplateDirectiveModel {
@Autowired
	public IChannelService iChannelService;

	public void execute(Environment env, Map map, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {

		    String parentIdStr = DirectiveUtil.getStringValue("parentId", map);
		    if(StringUtils.isEmpty(parentIdStr)){
		    	parentIdStr = "0";
		    }
	        Integer parentId = Integer.valueOf(parentIdStr);
			List list =iChannelService.getChannelList(parentId);

			env.setVariable("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
	        body.render(env.getOut());
		}

}
