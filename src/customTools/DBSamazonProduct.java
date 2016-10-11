package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazonproduct;

public class DBSamazonProduct {
	public static List<Samazonproduct> getProductList() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonproduct> query = em.createQuery("SELECT p FROM Samazonproduct p", Samazonproduct.class);
		List<Samazonproduct> productList = null;
		try {
			productList = query.getResultList();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return productList;
	}
	public static Samazonproduct getProduct(int productID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonproduct> query = em.createQuery("SELECT p FROM Samazonproduct p where p.productid = :productID", Samazonproduct.class);
		query.setParameter("productID", productID);
		Samazonproduct product = null;
		try {
			product = query.getSingleResult();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return product;
	}
}
