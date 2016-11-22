package com.voidking.struts2_hibernate.action;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.voidking.struts2_hibernate.dao.DlDao;
import com.voidking.struts2_hibernate.dao.imp.DlDaoImp;
import com.voidking.struts2_hibernate.model.Dlb;
public class LoginAction extends ActionSupport{
	
	private Dlb dlb;
	
	public Dlb getDlb() {
		return dlb;
	}
	public void setDlb(Dlb dlb) {
		this.dlb=dlb;
	}
	public String execute() throws Exception {
		DlDao dlDao=new DlDaoImp();							
		Dlb user=dlDao.validate(dlb.getXh(), dlb.getKl());  
		if(user!=null){
			
			Map session=(Map)ActionContext.getContext().getSession();
			session.put("user", user);
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
}
