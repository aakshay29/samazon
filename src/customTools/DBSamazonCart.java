package customTools;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import UserData.DBUtil;
import model.Samazonaddress;
import model.Samazoncart;
import model.Samazonorder;
import model.Samazonproduct;
import model.Samazonuser;

public class DBSamazonCart {
	public static List<Samazoncart> getCartList(int userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazoncart> query = em.createQuery("SELECT c FROM Samazoncart c where c.samazonuser.userid = :userID and c.productordered = 0", Samazoncart.class);
		query.setParameter("userID", userID);
		List<Samazoncart> cartList = null;
		try {
			cartList = query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return cartList;
	}
	public static void delete(Samazoncart cartItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(cartItem));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void getCartItemAndDelete(int cartID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazoncart> query = em.createQuery("SELECT c FROM Samazoncart c where c.cartid = :cartID", Samazoncart.class);
		query.setParameter("cartID", cartID);
		Samazoncart cartItem = null;
		try {
			cartItem = query.getSingleResult();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		delete(cartItem);
	}
	public static Samazoncart getCartItem(int cartID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazoncart> query = em.createQuery("SELECT c FROM Samazoncart c where c.cartid = :cartID", Samazoncart.class);
		query.setParameter("cartID", cartID);
		Samazoncart cartItem = null;
		try {
			cartItem = query.getSingleResult();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return cartItem;
	}
	//@SuppressWarnings("null")
	public static void getProductAndAdd(int productID, Samazonuser user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();	
		TypedQuery<Samazonproduct> query = em.createQuery("SELECT p FROM Samazonproduct p where p.productid = :productID", Samazonproduct.class);
		query.setParameter("productID", productID);
		Samazonproduct product;
		Samazoncart cartItem = new Samazoncart();
		try {
			product = query.getSingleResult();	
			cartItem.setSamazonuser(user);
			cartItem.setSamazonproduct(product);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		add(cartItem);
	}
	public static void add(Samazoncart cartItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(cartItem);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void update(Samazoncart cart) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		cart.setProductordered(1);
		try {
			trans.begin();
			em.merge(cart);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static void order(List<Samazoncart> cartListToOrder, Samazonaddress address) {
		Date date = new Date();	
		for(int i = 0; i < cartListToOrder.size(); i++){
			Samazonorder order = new Samazonorder();
			order.setDateplaced(date);
			order.setSamazoncart(getCartItem((int) cartListToOrder.get(i).getCartid()));
			order.setSamazonaddress(address);
			DBSamazonOrder.add(order);
			update(getCartItem((int) cartListToOrder.get(i).getCartid()));
			//getCartItemAndDelete((int) cartListToOrder.get(i).getCartid());
		}
	}
}
