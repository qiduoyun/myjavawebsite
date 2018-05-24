package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiuxiuyu.beans.Comment;
import com.xiuxiuyu.service.ICommentService;
import com.xiuxiuyu.util.DirectiveUtil;
import com.xiuxiuyu.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 评论列表标签
 * @author 桂都
 *
 */
@Component(value="comment_list")
public class CommentListDirective implements TemplateDirectiveModel{
	@Autowired
    private ICommentService commentService;
	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		//内容ID
		String contentIdStr=DirectiveUtil.getStringValue("contentId", params);
		if(StringUtils.isEmpty(contentIdStr)){
			contentIdStr="1";
		}
		Integer contentId = Integer.valueOf(contentIdStr);
		StringBuilder sql = new StringBuilder();
		List paramList = new ArrayList();
		sql.append("from Comment where contentId=?");
		paramList.add(contentId);
		List<Comment> list = commentService.getCommentList(sql.toString(), paramList);
		env.setVariable("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		body.render(env.getOut());
	}

}
