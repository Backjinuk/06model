package com.model2.mvc.service.coment;

import com.model2.mvc.service.domain.Coment;

public interface ComentDao {
	
	public void insertComent(Coment coment);
	
	public void updateComent(Coment coment);
	
	public int deleteComent(int comentNo);

}
