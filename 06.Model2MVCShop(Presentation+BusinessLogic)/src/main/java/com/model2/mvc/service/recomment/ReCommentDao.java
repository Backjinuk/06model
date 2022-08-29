package com.model2.mvc.service.recomment;

import java.util.Map;

import com.model2.mvc.service.domain.ReComment;

public interface ReCommentDao {
	

	public void addReComment(ReComment recomment);
	
	public Map<String, Object> listReComment(int commentNo)throws Exception;
	
	public void updateReComment(ReComment recomment)throws Exception;
	
	public void deleteReComment(int recommentNo)throws Exception;
	
}
