package com.voidking.struts2_hibernate.dao;

import java.util.List;

import com.voidking.struts2_hibernate.model.Zyb;

public interface ZyDao {
	public Zyb getOneZy(Integer zyId);
	public List getAll();
}
