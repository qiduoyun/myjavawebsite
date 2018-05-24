package com.xiuxiuyu.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiuxiuyu.beans.Comment;
import com.xiuxiuyu.service.ICommentService;
import com.xiuxiuyu.vo.UserVO;

@Controller
public class FrontCommentAct extends FrontBaseAct{
	@Autowired
	private ICommentService commentService;

	/**
	 * 保存评论
	 * 
	 * @return
	 */
	@RequestMapping("/saveComment.jhtml")
	@ResponseBody
	public String saveComment(Comment comment,HttpServletRequest request) {
		String state = "0";
		try {
			UserVO userVO = getFromSession(request, request.getSession(), "user", "username");
			comment.setCreateTime(new Date());
			comment.setUserId(userVO.getId());
			commentService.updateComment(comment);
			state = "1";
		} catch (Exception e) {
			state = e.getMessage();
		}
		return state;
	}

}
