

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBSamazonCart;
import customTools.DBSamazonOrder;
import model.Samazonaddress;
import model.Samazoncart;
import model.Samazonorder;
import model.Samazonuser;

/**
 * Servlet implementation class AddRemoveCartServlet
 */
@WebServlet("/AddRemoveCartServlet")
public class AddRemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemoveCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");	
		String url = null;
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		Samazonaddress address = (Samazonaddress) session.getAttribute("userAddress");
		List<Samazoncart> cartListToOrder = null;
		cartListToOrder = (List<Samazoncart>) session.getAttribute("cartList");
		Samazonuser user = (Samazonuser) session.getAttribute("user");
		
		if(action.equalsIgnoreCase("delete")){
			int cartID2 = Integer.parseInt(request.getParameter("cartID"));
			DBSamazonCart.getCartItemAndDelete(cartID2);
			url = "/Cart.jsp";
		}
		if(action.equalsIgnoreCase("add")){
			int productID = Integer.parseInt(request.getParameter("productID"));
			DBSamazonCart.getProductAndAdd(productID,user);
			url = "/Cart.jsp";
		}
		if(action.equalsIgnoreCase("order")){
			DBSamazonCart.order(cartListToOrder,address);
			url = "/Order.jsp";
		}
		
		String orderHTML = "";
		List<Samazonorder> orderList = null;
		orderList = DBSamazonOrder.getOrderList(userID);
		session.setAttribute("orderList", orderList);		
		String imageSource;
		String productName;
		long productPrice;
		String dateOrdered;
		if(orderList.size() != 0)
			orderHTML = "";
		orderHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">Your orders</h3>";
		for(int i = 0; i < orderList.size(); i++){		
			imageSource = orderList.get(i).getSamazoncart().getSamazonproduct().getImage();
			productName = orderList.get(i).getSamazoncart().getSamazonproduct().getName();
			dateOrdered = orderList.get(i).getDateplaced().toString();
			productPrice = (long) orderList.get(i).getSamazoncart().getSamazonproduct().getPrice();
			orderHTML += "<div class=\"cart-header wow fadeInUp animated\" data-wow-delay=\".5s\">"+
					"<div class=\"cart-sec simpleCart_shelfItem\">"+
						"<div class=\"cart-item cyc\">"+
							"<a href=\"single.html\"><img src="+imageSource+" class=\"img-responsive\" alt=\"\"></a>"+
						"</div>"+
						"<div class=\"cart-item-info\">"+
							"<h4><a href=\"single.html\">"+productName+"</a></h4>"+
							"<ul class=\"qty\">"+
								"<li><p>FREE delivery</p></li>"+
							"</ul>"+
							"<div class=\"delivery\">"+
								"<p>Price: $"+productPrice+"</p><br/>"+
								"<p>Date ordered: $"+dateOrdered+"</p>"+
								"<div class=\"clearfix\"></div>"+
							"</div>"+
						"</div>"+
						"<div class=\"clearfix\"></div>"+
					"</div>";
		}
		request.getSession().setAttribute("orderHTML", orderHTML);
		
		String cartHTML = "";
		System.out.println(userID);
		List<Samazoncart> cartList = null;
		cartList = DBSamazonCart.getCartList(userID);
		System.out.println(cartList);
		session.setAttribute("cartList", cartList);		
		String imageSource2;
		String productName2;
		long productPrice2;
		int cartID;
		int totalPrice2 = 0;
		if(cartList.size() != 0)
			cartHTML = "";
		int totalItems;
		for(totalItems = 0; totalItems < cartList.size(); totalItems++){	
			productPrice2 = (long) cartList.get(totalItems).getSamazonproduct().getPrice();
			totalPrice2 += productPrice2;			
		}
		cartHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">My Shopping Cart("+totalItems+")</h3>";
		cartHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">Total Price = $"+totalPrice2+"</h3>";
		if(totalPrice2 > 50){
			cartHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">Your current order is eligible for free shipping</h3><br/><br/>";
		}
		else{			
			cartHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">Order should be more than $50 for free shipping</h3>";
		}		
		cartHTML += "<form action=\"AddRemoveCartServlet\" method=\"post\">"+
						"<input type=\"hidden\" name=\"action\" value=\"order\">"+
						"<input type=\"submit\" value=\"Place Order\" id=\"submit\">"+
					"</form>";
		for(int i = 0; i < cartList.size(); i++){		
			imageSource2 = cartList.get(i).getSamazonproduct().getImage();
			productName2 = cartList.get(i).getSamazonproduct().getName();
			productPrice2 = (long) cartList.get(i).getSamazonproduct().getPrice();
			cartID = (int) cartList.get(i).getCartid();		
			cartHTML += "<div class=\"cart-header wow fadeInUp animated\" data-wow-delay=\".5s\">"+
					"<div class=\"alert-close\">"+
						"<form action=\"AddRemoveCartServlet\" method=\"post\">"+
							"<input type=\"hidden\" name=\"cartID\" value="+cartID+">"+
							"<input type=\"hidden\" name=\"action\" value=\"delete\">"+
							"<input type=\"submit\" value=\"Delete\" id=\"submit\">"+
						"</form>"+
					"</div>"+
					"<div class=\"cart-sec simpleCart_shelfItem\">"+
						"<div class=\"cart-item cyc\">"+
							"<a href=\"single.html\"><img src="+imageSource2+" class=\"img-responsive\" alt=\"\"></a>"+
						"</div>"+
						"<div class=\"cart-item-info\">"+
							"<h4><a href=\"single.html\">"+productName2+"</a></h4>"+
							"<ul class=\"qty\">"+
								"<li><p>FREE delivery</p></li>"+
							"</ul>"+
							"<div class=\"delivery\">"+
								"<p>Price: $"+productPrice2+"</p>"+
								"<div class=\"clearfix\"></div>"+
							"</div>"+
						"</div>"+
						"<div class=\"clearfix\"></div>"+
					"</div>";
		}
		request.getSession().setAttribute("cartHTML", cartHTML);
		
		String cartWidgetHTML = "";
		List<Samazoncart> cartList2 = null;
		cartList2 = DBSamazonCart.getCartList(userID);
		session.setAttribute("cartList", cartList2);		
		long productPrices;
		int totalPrice3 = 0;
		if(cartList2.size() != 0)
			cartWidgetHTML = "";
		int totalItems2;
		for(totalItems2 = 0; totalItems2 < cartList2.size(); totalItems2++){	
			productPrices = (long) cartList2.get(totalItems2).getSamazonproduct().getPrice();
			totalPrice3 += productPrices;			
		}
		cartWidgetHTML = "<a href=\"checkout.html\"> <span class=\"\">"+
		"$"+totalPrice3+" </span> (<span id=\"\" class=\"\"> "+totalItems2+" </span>)</a>";
		request.getSession().setAttribute("cartWidgetHTML", cartWidgetHTML);
		
		response.sendRedirect(request.getContextPath()+url);
	}

}
