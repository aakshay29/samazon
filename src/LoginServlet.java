

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBSamazonAddress;
import customTools.DBSamazonCart;
import customTools.DBSamazonOrder;
import customTools.DBSamazonProduct;
import customTools.DBSamazonRating;
import customTools.DBSamazonUser;
import model.Samazonaddress;
import model.Samazoncart;
import model.Samazonorder;
import model.Samazonproduct;
import model.Samazonuser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getSession().setAttribute("alert", "");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextUrl;
		HttpSession session = request.getSession();
		String productHTML = "";
		String ratingHTML = "";
		List<Samazonproduct> productList = null;
		productList = DBSamazonProduct.getProductList();
		session.setAttribute("productList", productList);		
		String imageSource2;
		String productName2;
		long productPrice2;
		int productID;
		int rating;
		for(int i = 0; i < productList.size(); i++){
			ratingHTML = "";
			imageSource2 = productList.get(i).getImage();
			productName2 = productList.get(i).getName();
			productPrice2 = (long) productList.get(i).getPrice();
			productID = (int) productList.get(i).getProductid();
			rating = DBSamazonRating.getRating(productID);
			if(rating == 0){
				ratingHTML += "No ratings yet";
			}
			else{
				for(int j = 0; j < rating; j++){
					ratingHTML += "<span class=\"on\">☆</span>";
				}
				for(int k = 0; k < 5-rating; k++){
					ratingHTML += "<span>☆</span>";
				}
			}		
			
			productHTML += "<div class=\"product-grids simpleCart_shelfItem wow fadeInUp animated\" data-wow-delay=\".5s\">"+
				"<div class=\"new-top\">"+
					"<a href=\"single.html\"><img src="+imageSource2+" class=\"img-responsive\" alt=\"\"/></a>"+
					"<div class=\"new-text\">"+
						"<ul>"+
							"<li>"+
							"<form action=\"AddRemoveCartServlet\" method=\"post\">"+
								"<input type=\"hidden\" name=\"productID\" value="+productID+">"+
								"<input type=\"hidden\" name=\"action\" value=\"add\">"+
								"<input type=\"submit\" value=\"Add to cart\" id=\"submit\">"+
							"</form><br/>"+
							"<form action=\"SingleServlet\" method=\"post\">"+
								"<input type=\"hidden\" name=\"productID\" value="+productID+">"+
								"<input type=\"submit\" value=\"View Product\" id=\"submit\">"+
							"</form>"+
							"</li>"+
						"</ul>"+
					"</div>"+
				"</div>"+
				"<div class=\"new-bottom\">"+
					"<h5><a class=\"name\" href=\"single.html\">"+productName2+"</a></h5>"+
					"<div class=\"rating\">"+
					ratingHTML+
					"</div>"+
					"<div class=\"ofr\">"+
						"<p><span class=\"item_price\">$"+productPrice2+"</span></p>"+
					"</div>"+
				"</div>"+
			"</div>";
		}
		request.getSession().setAttribute("productList", productHTML);
		
		if(DBSamazonUser.isValidUser(username, password)){
			
			Samazonuser user = null;		
			user = DBSamazonUser.getUserByUsername(username);
			request.getSession().setAttribute("helloUser", "Hello " + user.getName());
			if(user.getRole() == 1){
				List<Samazonproduct> productList2 = null;
				productList2 = DBSamazonProduct.getProductList();
				session.setAttribute("productList", productList2);	
				nextUrl = "/ProductListAdmin.jsp";
			}		
			else{
				session.setAttribute("user", user);
				int userID = (int) user.getUserid();
				session.setAttribute("userID", userID);	
				Samazonaddress userAddress = null;
				userAddress = DBSamazonAddress.getAddress(userID);
				session.setAttribute("userAddress", userAddress);	
				
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

				String cartWidgetHTML = "";
				List<Samazoncart> cartList2 = null;
				cartList2 = DBSamazonCart.getCartList(userID);
				session.setAttribute("cartList", cartList2);		
				long productPrices;
				int totalPrice2 = 0;
				if(cartList2.size() != 0)
					cartWidgetHTML = "";
				int totalItems2;
				for(totalItems2 = 0; totalItems2 < cartList2.size(); totalItems2++){	
					productPrices = (long) cartList2.get(totalItems2).getSamazonproduct().getPrice();
					totalPrice2 += productPrices;			
				}
				cartWidgetHTML = "<a href=\"checkout.html\"> <span class=\"\">"+
				"$"+totalPrice2+" </span> (<span id=\"\" class=\"\"> "+totalItems2+" </span>)</a>";
				request.getSession().setAttribute("cartWidgetHTML", cartWidgetHTML);
				
				nextUrl = "/Products.jsp";
			}
			
		}
		else{	
			request.getSession().setAttribute("helloUser", "<a href=\"Login.jsp\">Click here to login<a>");
			//request.getSession().setAttribute("alert", "Incorrect username or password");
			nextUrl = "/Products.jsp";
		}
		response.sendRedirect(request.getContextPath()+nextUrl);
	}

}
