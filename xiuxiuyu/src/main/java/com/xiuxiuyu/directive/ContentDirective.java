package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

@Component(value="content")
public class ContentDirective implements TemplateDirectiveModel {
@Autowired
	public IContentService contentService;
	public void execute(Environment env, Map map, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		    //内容ID
		    String idStr = DirectiveUtil.getStringValue("id", map);
		    if(StringUtils.isEmpty(idStr)){
		    	idStr = "0";
		    }
	        Integer id = Integer.valueOf(idStr);
	        //栏目ID
		    String channelIdStr = DirectiveUtil.getStringValue("channelId", map);
	        Integer channelId = Integer.valueOf(channelIdStr);
	        //下一篇
		    String nextStr = DirectiveUtil.getStringValue("next", map);
		    if(StringUtils.isEmpty(nextStr)){
		    	nextStr = "-1";
		    }
	        Integer next = Integer.valueOf(nextStr);
	        StringBuilder sql = new StringBuilder();
	        sql.append("from Content where state=0 and id").append(next>0?">":"<").append(id);
	        sql.append(" and channelId=").append(channelId);
	        if(next<0){
	          	sql.append(" order by id desc");
	        }
	        Content content = contentService.findContentBySQL(sql.toString());
			env.setVariable("tag_bean", ObjectWrapper.DEFAULT_WRAPPER.wrap(content));
	        body.render(env.getOut());
		}

}
