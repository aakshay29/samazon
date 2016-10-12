package customTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import UserData.DBUtil;
import model.Samazonreview;

public class DBSamazonReview {
	public static void addReview(Samazonreview review) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(review);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
