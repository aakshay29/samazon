package customTools;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazoncategory;

public class DBSamazonCategory {
	public static Samazoncategory getCategory(int categoryID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazoncategory> query = em.createQuery("SELECT c FROM Samazoncategory c where c.categoryid = :categoryID", Samazoncategory.class);
		query.setParameter("categoryID", categoryID);
		Samazoncategory category = null;
		try {
			category = query.getSingleResult();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return category;
	}
}
