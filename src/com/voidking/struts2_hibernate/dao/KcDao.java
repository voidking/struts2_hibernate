package com.voidking.struts2_hibernate.dao;

import java.util.List;

import com.voidking.struts2_hibernate.model.Kcb;

public interface KcDao {
	public Kcb getOneKc(String kch);
	public List getAll();
}
