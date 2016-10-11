package customTools;

import javax.persistence.EntityManager;
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
}
