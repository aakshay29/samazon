package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazonorder;

public class DBSamazonOrder {
	public static void add(Samazonorder order) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(order);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static List<Samazonorder> getOrderList(int userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonorder> query = em.createQuery("SELECT o FROM Samazonorder o where o.samazoncart.samazonuser.userid = :userID", Samazonorder.class);
		query.setParameter("userID", userID);
		List<Samazonorder> orderList = null;
		try {
			orderList = query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return orderList;
	}
}
