package com.voidking.struts2_hibernate.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.voidking.struts2_hibernate.dao.ZyDao;
import com.voidking.struts2_hibernate.model.Zyb;
import com.voidking.struts2_hibernate.util.MySessionFactory;

public class ZyDaoImp implements ZyDao {

	@Override
	public Zyb getOneZy(Integer zyId) {
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from zyb where id=?");
			query.setParameter(0, zyId);
			query.setMaxResults(1);
			Zyb zy = (Zyb)query.uniqueResult();
			
			ts.commit();
			session.close();
			
			return zy;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			
			List list = session.createQuery("from zyb").list();
			
			ts.commit();
			session.close();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
