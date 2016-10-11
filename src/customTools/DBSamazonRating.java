package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazonrating;

public class DBSamazonRating {
	public static int getRating(int productID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonrating> query = em.createQuery("SELECT r FROM Samazonrating r where r.samazonproduct.productid = :productID", Samazonrating.class);
		query.setParameter("productID", productID);
		List<Samazonrating> rating = null;
		int averageRating = 0;
		try {
			int ratingTotal = 0;
			rating = query.getResultList();				
			if(rating.size() > 0){
				for(int i = 0; i < rating.size(); i++){
					ratingTotal += rating.get(0).getRating();
				}			
				averageRating = ratingTotal/rating.size();
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return averageRating;
	}
}
