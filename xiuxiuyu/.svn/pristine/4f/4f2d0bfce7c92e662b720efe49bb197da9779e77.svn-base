package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component(value="channel")
public class ChannelDirective implements TemplateDirectiveModel {
@Autowired
	public IChannelService channelService;
	public void execute(Environment env, Map map, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {

		    String idStr = DirectiveUtil.getStringValue("id", map);
		    if(StringUtils.isEmpty(idStr)){
		    	idStr = "0";
		    }
	        Integer id = Integer.valueOf(idStr);
	        Channel channel = channelService.getChannelInfo(id);
			env.setVariable("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(channel));
	        body.render(env.getOut());
		}

}
