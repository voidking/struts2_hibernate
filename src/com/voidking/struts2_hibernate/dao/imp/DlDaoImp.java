package com.voidking.struts2_hibernate.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.voidking.struts2_hibernate.dao.DlDao;
import com.voidking.struts2_hibernate.model.Dlb;
import com.voidking.struts2_hibernate.util.MySessionFactory;

public class DlDaoImp implements DlDao {

	@Override
	public Dlb validate(String xh, String kl) {
		// TODO Auto-generated method stub
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from Dlb where xh=? and kl=?");
			query.setParameter(0, xh);
			query.setParameter(1, kl);
			query.setMaxResults(1);
			Dlb dlb =(Dlb)query.uniqueResult();
			
			ts.commit();
			session.close();
			
			if(dlb!=null){
				return dlb;
			}else{
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
