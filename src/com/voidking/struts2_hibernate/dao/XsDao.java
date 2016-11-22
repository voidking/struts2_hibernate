package com.voidking.struts2_hibernate.dao;

import com.voidking.struts2_hibernate.model.Xsb;

public interface XsDao {
	public Xsb getOneXs(String xh);
	
	public void update(Xsb xs);
}
