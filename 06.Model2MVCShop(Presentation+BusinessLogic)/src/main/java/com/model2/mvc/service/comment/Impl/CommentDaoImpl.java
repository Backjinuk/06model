package com.model2.mvc.service.comment.Impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.comment.CommentDao;
import com.model2.mvc.service.domain.Comment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

@Repository("commentDaoImpl")
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addComment(Comment comment) {
		sqlSession.insert("CommentMapper.addComment", comment);
	}

	@Override
	public Map<String, Object> listComment(int prodNo) { 
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("prodNo", prodNo);
		
		List<Comment> list =
				sqlSession.selectList("CommentMapper.listComment", map); 
	
		System.out.println("map의 정보 :" + list);
		int[] commentNo = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setProdNo((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getProdNo().getProdNo()));
			list.get(i).setUserId((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getUserId().getUserId()));
			System.out.println("CommentNo의 정보 : " + list.get(i).getCommentNo() + "\n");
			commentNo[i] = list.get(i).getCommentNo();
		}
		//Arrays.toString(
		map.put("commentNo",  commentNo);
		map.put("list", list);
		
		return map;
	}

	@Override
	public void updateComment(Comment comment) throws Exception {
		sqlSession.update("CommentMapper.updateComment", comment); 
	}

	@Override
	public void deleteComment(Comment comment) {
		sqlSession.delete("CommentMapper.deleteComment", comment);
	}

	@Override
	public Comment getComment(int commentNo) throws Exception {	
		return sqlSession.selectOne("CommentMapper.getComment", commentNo);
				
	}


}
