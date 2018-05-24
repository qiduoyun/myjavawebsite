package com.xiuxiuyu.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.beans.Content;
import com.xiuxiuyu.dao.IContentDao;
import com.xiuxiuyu.lucene.LuceneUtil;
import com.xiuxiuyu.service.IChannelService;
import com.xiuxiuyu.service.IContentService;
import com.xiuxiuyu.util.PageInfo;
import com.xiuxiuyu.util.StringUtils;
/**
 * ���ݹ���
 * @author ��
 *
 */
@Controller
public class ContentAct {
	@Autowired
    private IContentService contentService;
	@Autowired
	private IChannelService channelService;
	@RequestMapping("/content/index.do")
    public String index(HttpServletRequest request,ModelMap map){
		map.put("hello", "helloword");
    	return "content/index";
    }
	/**
	 * ��ѯ���������б�
	 * @param request
	 * @param pageInfo ��ҳ����
	 * @param channel ��Ŀ
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/content/list.do")
	@ResponseBody
	public String findContentList(HttpServletRequest request,PageInfo<Content> pageInfo,Channel channel) throws IOException{
		PageInfo<Content> info = contentService.findContentList(pageInfo, channel);
		List list = new ArrayList();
		List<Content> contents = info.getResultList();
		for(Content content:contents){
			Map map=new HashMap();
			map.put("id", content.getId());
			map.put("title", content.getTitle());
			map.put("channelName", content.getChannel().getName());
			map.put("type", content.getType());
			list.add(map);
		}
		JSONObject object = new JSONObject();
		object.put("total", info.getRows());
		object.put("rows", list);
		return object.toString();
	}
	@RequestMapping("/content/toList.do")
	public String toList(Integer channelId,ModelMap modelMap){
		if(channelId!=null){
			Channel channel = channelService.getChannelInfo(channelId);
			modelMap.put("channel", channel);	
		}
		return "content/list";
	}
	/**
	 * ��������
	 * @return
	 */
	@RequestMapping("/content/toAdd.do")
	public String toAdd(){
		return "content/add";
	}
	@RequestMapping("/content/add.do")
	@ResponseBody
	public String add(Content content,HttpServletRequest request){
		int state=0;
		String error="";
		Channel channel = channelService.getChannelInfo(content.getChannelId());
		content.setContentTemplate(channel.getContentTemplate());
		content.setState(0);
		content.setUpdateTime(new Date());
		content.setUrl(channel.getUrl());
		try {
			contentService.updateContent(content);
			state=1;
			LuceneUtil.writeToIndex(LuceneUtil.getPath(request), content);
		} catch (Exception e) {
			state=-1;
			error=e.getMessage();
		}
		JSONObject object = new JSONObject();
		object.put("state", state);
		object.put("error", error);
		return object.toString();
	}

	@RequestMapping("/content/toEdit.do")
	public String toEdit(Integer id,ModelMap map){
		Content content=contentService.getContentById(id);
		map.put("content", content);
	    return "content/edit";	
	}
	/**
	 * �޸�����
	 * @param content
	 * @return
	 */
	@RequestMapping("/content/edit.do")
	@ResponseBody
	public String edit(Content content,HttpServletRequest request){
		int state=0;
		String error="";
		Content editContent=contentService.getContentById(content.getId());
		editContent.setAuthor(content.getAuthor());
		editContent.setChannelId(content.getChannelId());
		editContent.setDescription(content.getDescription());
		editContent.setFilePath(content.getFilePath());
		editContent.setImgPath(content.getImgPath());
		editContent.setKeyword(content.getKeyword());
		editContent.setTitle(content.getTitle());
		editContent.setTxt(content.getTxt());
		editContent.setType(content.getType());
		editContent.setVideoType(content.getVideoType());
		Channel channel = channelService.getChannelInfo(content.getChannelId());
		editContent.setContentTemplate(channel.getContentTemplate());
		editContent.setState(0);
		editContent.setUpdateTime(new Date());
		editContent.setUrl(channel.getUrl());
		try {
			contentService.updateContent(editContent);
			state=1;
			LuceneUtil.writeToIndex(LuceneUtil.getPath(request), content);
		} catch (Exception e) {
			state=-1;
			error=e.getMessage();
		}
		JSONObject object = new JSONObject();
		object.put("state", state);
		object.put("error", error);
		return object.toString();
	}
	/**
	 * ɾ������(��ɾ��״̬���ݳ���ɾ�����������ݸ���״̬Ϊɾ��״̬)
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/delete.do")
	public String delete(Integer id,Integer channelId,HttpServletRequest request,ModelMap modelMap){
		Content content = contentService.getContentById(id);
		try {
			if(content.getState().intValue()==0){
				content.setState(-1);
				content.setUpdateTime(new Date());
				contentService.updateContent(content);
				LuceneUtil.deleteIndex(LuceneUtil.getPath(request), String.valueOf(content.getId()));
			}else{
				contentService.deleteContent(content);	
				LuceneUtil.deleteIndex(LuceneUtil.getPath(request), String.valueOf(content.getId()));
			}
		} catch (Exception e) {
			System.out.println("delete==>"+e.getMessage());
		}
		if(channelId!=null){
			Channel channel = channelService.getChannelInfo(channelId);
			modelMap.put("channel", channel);	
		}
		return "content/list";
	}
	/**
	 * ��ȡ�ؼ���
	 * @param title
	 * @return
	 */
	@RequestMapping("/content/getKeyword.do")
	@ResponseBody
	public String getKeyword(String title){
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("tags", StringUtils.getKeyword(title, true));
	    return jsonObject.toString();
	}
	
	/**
	 * 写入索引
	 * 
	 * @param path
	 * @param content
	 */
	public void writeToIndex(String path, Content content) {
		Directory directory = null;
		IndexWriter writer = null;
		try {
			Path dir = Paths.get(path);
			if(!Files.isReadable(dir)){
				System.out.println("Files.isReadable(path2)"+Files.isReadable(dir));
			}
			directory = new SimpleFSDirectory(dir);
			IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
			writer = new IndexWriter(directory, conf);
			Document document = new Document();
			Field id = new StringField("id", String.valueOf(content.getId()), Field.Store.YES);
			Field title = new TextField("title", content.getTitle(), Field.Store.NO);
			Field txt = new TextField("txt", content.getTxt(), Field.Store.NO);
			document.add(id);
			document.add(title);
			document.add(txt);
			// 删除后添加
			writer.deleteDocuments(new Term("id", String.valueOf(content.getId())));
			writer.addDocument(document);
			writer.flush();
			writer.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
