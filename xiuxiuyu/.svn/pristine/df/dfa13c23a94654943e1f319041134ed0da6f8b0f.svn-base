package com.xiuxiuyu.util;

import java.util.Map;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

public class DirectiveUtil {
	public static String getStringValue(String name,Map<String,TemplateModel> map){
		TemplateModel templateModel = map.get(name);
		if(templateModel instanceof TemplateScalarModel){
			try {
				return ((TemplateScalarModel)templateModel).getAsString();
			} catch (TemplateModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(templateModel instanceof TemplateNumberModel){
			try {
				return ((TemplateNumberModel)templateModel).getAsNumber().toString();
			} catch (TemplateModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return null;
		}
		return null;
	}
}
