package com.voidking.struts2_hibernate.action;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.voidking.struts2_hibernate.dao.KcDao;
import com.voidking.struts2_hibernate.dao.imp.KcDaoImp;
public class KcAction extends ActionSupport{
	public String execute()throws Exception{
		KcDao kcDao=new KcDaoImp();
		List list=kcDao.getAll();
		Map request=(Map)ActionContext.getContext().get("request");
		request.put("list", list);
		return SUCCESS;
	}
}
