package com.xiuxiuyu.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.dao.IContentDao;
import com.xiuxiuyu.lucene.LuceneUtil;
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
 * 全文搜搜
 * @author桂都
 *
 */
@Component(value="page_lucene_list")
public class PageLuceneListDirective implements TemplateDirectiveModel{
	@Autowired
	private IContentDao contentDao;

	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		//搜索信息
		String searcher=DirectiveUtil.getStringValue("searcher", params);
		//每页的数量
		String countStr=DirectiveUtil.getStringValue("count", params);
		Integer count;
		if(StringUtils.isEmpty(countStr)){
			count = Integer.MAX_VALUE;
		}else{
			count = Integer.valueOf(countStr);
		}
		//当前页
		String pageStr=DirectiveUtil.getStringValue("page", params);
		Integer page;
		if(StringUtils.isEmpty(pageStr)){
			page = 1;
		}else{
			page = Integer.valueOf(pageStr);
		}
		PageInfo<Content> pageInfo = new PageInfo<Content>();;
		Integer totalCount = 0;//总记录数
		if(StringUtils.isEmpty(searcher)){
			pageInfo.setRows(0);
			pageInfo.setResultList(new ArrayList<Content>());
		}else{
			Set<Integer> idList = new HashSet<Integer>();
			Set<String> ids = new HashSet<String>();
			String keyword = StringUtils.getKeyword(searcher, true);
			String[] words = keyword.split(",");
			String path = this.getClass().getClassLoader().getResource("").getPath();
			path = path.replace("classes/", "lucene");
			path = path.substring(1);
			for(String word:words){
				idList = LuceneUtil.searchFromIndex(word, page, count, 0, idList,ids, path);	
			}
			totalCount = ids.size();
			if(!idList.isEmpty()){
				StringBuilder sql = new StringBuilder();
				sql.append("from Content where id in (").append(setToString(idList)).append(")");
				List<Content> contents = contentDao.findContentList(sql.toString(), null, null, null);
				pageInfo.setRows(totalCount);
				pageInfo.setPage(page);
				pageInfo.setPageSize(count);
				pageInfo.setResultList(contents);	
			}else{
				pageInfo.setRows(0);
				pageInfo.setResultList(new ArrayList<Content>());
			}
		}
		env.setVariable("tag_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageInfo.getResultList()));
		env.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(pageInfo.getPage()));
		int totalPage = (totalCount+count-1)/count;
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
     * 拼接in表达的值
     * @param set
     * @return
     */
	private String setToString(Set<Integer> set){
		String ids = "";
		for(Integer id:set){
			ids += id+",";
		}
		if(ids.length()>0){
			ids = ids.substring(0, ids.length()-1);
		}
		return ids;
	}
}
