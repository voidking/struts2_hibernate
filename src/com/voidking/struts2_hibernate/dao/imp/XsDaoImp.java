package com.voidking.struts2_hibernate.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.voidking.struts2_hibernate.dao.XsDao;
import com.voidking.struts2_hibernate.model.Xsb;
import com.voidking.struts2_hibernate.util.MySessionFactory;

public class XsDaoImp implements XsDao {

	@Override
	public Xsb getOneXs(String xh) {
		// TODO Auto-generated method stub
		try {

			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from xsb where xh=?");
			query.setParameter(0, xh);
			query.setMaxResults(1);
			Xsb xs = (Xsb)query.uniqueResult();
			ts.commit();
			session.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Xsb xs) {
		// TODO Auto-generated method stub
		try {
			Session session = MySessionFactory.getSessionFactory().openSession();
			Transaction ts = session.beginTransaction();
			
			session.update(xs);
			
			ts.commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
