package customTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazonuser;

public class DBSamazonUser {
	public static boolean isValidUser(String username, String password) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select count(s) from Samazonuser s where s.username = :username and s.password = :password";
		TypedQuery<Long> q = em.createQuery(qString, Long.class);
		boolean result = false;
		q.setParameter("username", username);
		q.setParameter("password", password);

		try {
			long userId = q.getSingleResult();
			if (userId > 0) {
				result = true;
			}
		} catch (Exception e) {

			result = false;
		} finally {
			em.close();
		}
		return result;

	}
	public static Samazonuser getUserByUsername(String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select s from Samazonuser s " + "where s.username = :username";
		TypedQuery<Samazonuser> q = em.createQuery(qString, Samazonuser.class);
		q.setParameter("username", username);
		Samazonuser user = null;
		try {
			user = q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return user;

	}
	public static void addUser(Samazonuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void updateUser(Samazonuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
