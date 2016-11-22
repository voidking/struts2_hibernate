package com.voidking.struts2_hibernate.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.voidking.struts2_hibernate.dao.KcDao;
import com.voidking.struts2_hibernate.model.Kcb;
import com.voidking.struts2_hibernate.util.MySessionFactory;

public class KcDaoImp implements KcDao {

	@Override
	public Kcb getOneKc(String kch) {
		// TODO Auto-generated method stub
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			
			Query query = session.createQuery("from kcb where kch=?");
			query.setParameter(0, kch);
			query.setMaxResults(1);
			Kcb kc = (Kcb)query.uniqueResult();
			
			ts.commit();
			session.close();
			return kc;
			
		} catch (Exception e) {
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
			List list = session.createQuery("from kcb order by kch").list();
			ts.commit();
			session.close();
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
