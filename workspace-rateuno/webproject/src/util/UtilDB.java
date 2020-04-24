/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Data;
import datamodel.Reviews;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }
   
   //Data
   public static List<Data> listData() {
      List<Data> resultList = new ArrayList<Data>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> datas = session.createQuery("FROM Data").list();
         for (Iterator<?> iterator = datas.iterator(); iterator.hasNext();) {
            Data data = (Data) iterator.next();
            resultList.add(data);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static List<Data> listData(String keyword) {
      List<Data> resultList = new ArrayList<Data>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> datas = session.createQuery("FROM Data").list();
         for (Iterator<?> iterator = datas.iterator(); iterator.hasNext();) {
            Data data = (Data) iterator.next();
            if (data.getProfessor().contains(keyword)) {
               resultList.add(data);
            }
            else if (data.getCourse().contains(keyword)) {
	           resultList.add(data);
	        }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static Data getCourse(Integer id) {
	      Data course = new Data();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> datas = session.createQuery("FROM Data").list();
	         for (Iterator<?> iterator = datas.iterator(); iterator.hasNext();) {
	            Data data = (Data) iterator.next();
	            if (data.getData_id() == id) {
	               course = data;
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return course;
	   }
   
   //Reviews
   public static List<Reviews> listReviews() {
      List<Reviews> resultList = new ArrayList<Reviews>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> reviews = session.createQuery("FROM reviews").list();
         for (Iterator<?> iterator = reviews.iterator(); iterator.hasNext();) {
            Reviews review = (Reviews) iterator.next();
            resultList.add(review);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static List<Reviews> listReviews(Integer keyword) {
      List<Reviews> resultList = new ArrayList<Reviews>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> reviews = session.createQuery("FROM Reviews").list();
         for (Iterator<?> iterator = reviews.iterator(); iterator.hasNext();) {
            Reviews review = (Reviews) iterator.next();
            if (review.getDataid() == keyword) {
               resultList.add(review);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
   
   public static void createReview(Integer data_id, String student_name, String date, String review, Integer rating) {
	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;
	    try {
	       tx = session.beginTransaction();
	       session.save(new Reviews(data_id, student_name, date, review, rating));
	       tx.commit();
	    } catch (HibernateException e) {
	       if (tx != null)
	          tx.rollback();
	       e.printStackTrace();
	    } finally {
	       session.close();
	    }
	 }

}
