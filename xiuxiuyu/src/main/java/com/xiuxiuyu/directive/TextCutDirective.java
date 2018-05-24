package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.xiuxiuyu.util.DirectiveUtil;
import com.xiuxiuyu.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 截断长标题
 * @author 桂都
 *
 */
@Component("text_cut")
public class TextCutDirective implements TemplateDirectiveModel{
    //s=title, len=20 ,apppend='...'
	public void execute(Environment env, Map map, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String title=DirectiveUtil.getStringValue("s", map);
		String lenStr=DirectiveUtil.getStringValue("len", map);
		Integer len = Integer.valueOf(lenStr);
		String append=DirectiveUtil.getStringValue("append", map);
		if(StringUtils.isEmpty(append)){
			append="";
		}
		String resultStr="";
		if(len>=(title.length()-1)){
			resultStr=title;
		}else{
			resultStr=title.substring(0, len)+append;
		}
//		env.setVariable("text_cut", ObjectWrapper.DEFAULT_WRAPPER.wrap(resultStr));
		env.getOut().write(resultStr);
	}

}
