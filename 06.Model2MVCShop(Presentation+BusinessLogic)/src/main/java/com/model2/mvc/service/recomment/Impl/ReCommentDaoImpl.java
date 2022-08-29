package com.model2.mvc.service.recomment.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.ReComment;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.recomment.ReCommentDao;

@Repository("reCommentDaoImpl")
public class ReCommentDaoImpl implements ReCommentDao {
	
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void addReComment(ReComment recomment) {
		sqlSession.insert("ReCommentMapper.addReComment", recomment);
	}
	@Override
	public Map<String, Object> listReComment(int commentNo) throws Exception {
	
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("commentNo", commentNo);
		
		List<ReComment> list = sqlSession.selectList("ReCommentMapper.listReComment",commentNo);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setUserId((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getUserId().getUserId()));
		}
		map.put("list", list);
		
		return map; 
	}
	@Override
	public void updateReComment(ReComment recomment) throws Exception {
		sqlSession.update("ReCommentMapper.updateReComment", recomment);
	}
	@Override
	public void deleteReComment(int recommentNo) throws Exception {
		sqlSession.delete("ReCommentMapper.deleteReComment",recommentNo);
	}

}
 