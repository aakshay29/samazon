package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazonaddress;

public class DBSamazonAddress {
	public static Samazonaddress getAddress(int userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonaddress> query = em.createQuery("SELECT a FROM Samazonaddress a where a.samazonuser.userid = :userID", Samazonaddress.class);
		query.setParameter("userID", userID);
		Samazonaddress address = null;
		try {
			address = query.getSingleResult();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return address;
	}
	public static List<Samazonaddress> getAddressList(int userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonaddress> query = em.createQuery("SELECT a FROM Samazonaddress a where a.samazonuser.userid = :userID", Samazonaddress.class);
		query.setParameter("userID", userID);
		List <Samazonaddress> address = null;
		try {
			address = query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return address;
	}
	public static void addAddress(Samazonaddress address) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(address);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
