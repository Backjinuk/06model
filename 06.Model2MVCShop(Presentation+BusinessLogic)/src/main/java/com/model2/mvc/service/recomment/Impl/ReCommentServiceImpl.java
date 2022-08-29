package com.model2.mvc.service.recomment.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.ReComment;
import com.model2.mvc.service.recomment.ReCommentDao;
import com.model2.mvc.service.recomment.ReCommentService;

@Service("reCommentServiceImpl")
public class ReCommentServiceImpl implements ReCommentService {
	
	@Autowired
	@Qualifier("reCommentDaoImpl") 
	private ReCommentDao reCommentDao;
	
	public void setReCommentDao(ReCommentDao reCommentDao) {
		this.reCommentDao = reCommentDao;
	}

	@Override
	public void addReComment(ReComment recomment) throws Exception {
		reCommentDao.addReComment(recomment);
	}

	@Override
	public Map<String, Object> listReComment(int commentNo) throws Exception {
		return reCommentDao.listReComment(commentNo);
	} 

	@Override
	public void updateReComment(ReComment recomment) throws Exception {
		reCommentDao.updateReComment(recomment);
	}

	@Override
	public void deleteReComment(int recommentNo) throws Exception {
		reCommentDao.deleteReComment(recommentNo);
	}
	
}