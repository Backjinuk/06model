package com.model2.mvc.service.comment.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.comment.CommentDao;
import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Comment;

@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService {
	 
	@Autowired
	@Qualifier("commentDaoImpl") 
	private CommentDao commentDao;
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	 
	public CommentServiceImpl() {
		System.out.println("::" + getClass() + "¿‘¥œ¥Ÿ");
	}

	
	@Override
	public void addComment(Comment comment) throws Exception {
		commentDao.addComment(comment);

	}

	@Override
	public Map<String, Object> listComment(int prodNo) throws Exception {
		return commentDao.listComment(prodNo);
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		commentDao.updateComment(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws Exception {
		commentDao.deleteComment(comment); 
	}

	@Override
	public Comment getComment(int  commentNo) throws Exception { 
		return commentDao.getComment(commentNo);
	}


 
}
