package com.xiuxiuyu.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Short;
import com.xiuxiuyu.beans.Channel;
import com.xiuxiuyu.service.ChannelServiceImp;
import com.xiuxiuyu.service.IChannelService;

@Controller
public class ChannelAct {
	@Autowired
	private IChannelService channelService;
	@RequestMapping("/channel/index.do")
    public String index(ModelMap modelMap,Integer parent_id){
		if(parent_id==null) {
			parent_id=0;
		}
		List list=channelService.getChannelList(parent_id);
		modelMap.put("list",list );
		modelMap.put("parentId",  parent_id);
    	return "channel/index";
    }
	
	@RequestMapping("/channel/xingzeng.do" )
	@ResponseBody
	public String xingzeng(Channel channel){
		channelService.channelSave(channel);
		/*JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", channel.getParentId());
		jsonObject.put("state", 1);
		return jsonObject.toString();*/
		return "1";
	}
	/**
	 * ï¿½ï¿½È¡ï¿½ï¿½Ä¿ï¿½Ð±ï¿½
	 * @param id ï¿½ï¿½ï¿½ï¿½Ä¿id
	 * @return
	 */
	@RequestMapping("/channel/findChannelList.do")
	@ResponseBody
	public String findChannelList(Integer id,Integer selectId) {
		List<Channel> list = channelService.getChannelList(id == null ? 0 : id);
		JSONArray array = new JSONArray();
		for (Channel channel : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", channel.getId());
			jsonObject.put("text", channel.getName());
			if(channel.getId().equals(selectId)){
				jsonObject.put("selected", true);
			}
			sembleChannelTree(channel,jsonObject,selectId);
			array.put(jsonObject);
		}
		return array.toString();
	}

	@RequestMapping("/channel/toxz.do")
	public String toxz(Integer id, ModelMap modelMap){
		modelMap.put("id", id);
	
		return "channel/xingzeng";
	}
	@RequestMapping("/channel/tomodify.do")
	   public String tomodify(Integer id,ModelMap modelMap){
	    	modelMap.put("id", id);
	    	Channel channel =channelService.getChannelInfo(id);
			modelMap.put("c", channel);
	    	return "channel/modify";
	    }
	
	@RequestMapping("/channel/modify.do" )
	@ResponseBody
	public String modify(Channel channel){
		Channel newchannel =channelService.getChannelInfo(channel.getId());
		newchannel.setChannelTemplate(channel.getChannelTemplate());
		newchannel.setUrl(channel.getUrl());
		newchannel.setContentTemplate(channel.getContentTemplate());
		newchannel.setName(channel.getName());
		
		newchannel.setDescription(channel.getDescription());
		
		newchannel.setKeyword(channel.getKeyword());
		
		newchannel.setImgPath(channel.getImgPath());
		
		
		channelService.channelSave(newchannel);
		/*JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", channel.getParentId());
		jsonObject.put("state", 1);
		return jsonObject.toString();*/
		return "1";
	}
	@RequestMapping("/channel/delete.do" )
	public String delete(Channel channel){
		channelService.channelDelete(channel);
		
		return "channel/index";
	}

 @RequestMapping("/mould/index.do")
 @ResponseBody
	public String findURL(HttpServletRequest req,String url){
	if(url==null){
		url=req.getRealPath("/WEB-INF/t");
	}
	 File file=new File(url);
	File[] files=file.listFiles();
	JSONArray jsonArry=new JSONArray();
	
	for(int i=0;i<files.length;i++)
	{
		JSONObject jsonObject=new JSONObject();
		JSONArray Jsonarry=new  JSONArray();
		
		File Filechild=files[i];
		File[] filechild=Filechild.listFiles();
		for(int j=0;j<filechild.length;j++){
			JSONObject jsonObjects=new JSONObject();
			jsonObjects.put("text", filechild[j].getName());
			Jsonarry.put(jsonObjects);
		}
		jsonObject.put("children", Jsonarry);
		jsonObject.put("text", files[i].getName());
		jsonArry.put(jsonObject);
		}
		return jsonArry.toString();
	}
 @RequestMapping("/mould/indexs.do")
 public String findURLs(){
	 
	 return "mould/index";
 }
	/**
	 * ×é×°À¸Ä¿Ê÷ÁÐ±í½á¹¹
	 * @param channel
	 * @param jsonObject
	 */
	private void sembleChannelTree(Channel channel,JSONObject jsonObject,Integer selectId){
		Set<Channel> channels = channel.getChildrenChannel();
		if(!channels.isEmpty()){
			JSONArray array = new JSONArray();
			for(Channel child:channels){
				JSONObject object = new JSONObject();
				object.put("id", child.getId());
				object.put("text", child.getName());
				if(child.getId().equals(selectId)){
					object.put("selected", true);
				}
				sembleChannelTree(child,object,selectId);
				array.put(object);
			}
			jsonObject.put("children", array);
		}
	}

}

 