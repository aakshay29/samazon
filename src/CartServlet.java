

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBSamazonCart;
import model.Samazoncart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
		HttpSession session = request.getSession();
		String cartHTML = "";
		int userID = (int) session.getAttribute("userID");
		System.out.println(userID);
		List<Samazoncart> cartList = null;
		cartList = DBSamazonCart.getCartList(userID);
		System.out.println(cartList);
		session.setAttribute("cartList", cartList);		
		String imageSource;
		String productName;
		long productPrice;
		int cartID;
		int totalPrice = 0;
		if(cartList.size() != 0)
			cartHTML = "";
		int totalItems;
		for(totalItems = 0; totalItems < cartList.size(); totalItems++){	
			productPrice = (long) cartList.get(totalItems).getSamazonproduct().getPrice();
			totalPrice += productPrice;			
		}
		cartHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">My Shopping Cart("+totalItems+")</h3>";
		cartHTML += "<h3 class=\"wow fadeInUp animated\" data-wow-delay=\".5s\">Total Price = $"+totalPrice+"</h3>";
		if(totalPrice > 50){
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
			imageSource = cartList.get(i).getSamazonproduct().getImage();
			productName = cartList.get(i).getSamazonproduct().getName();
			productPrice = (long) cartList.get(i).getSamazonproduct().getPrice();
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
							"<a href=\"single.html\"><img src="+imageSource+" class=\"img-responsive\" alt=\"\"></a>"+
						"</div>"+
						"<div class=\"cart-item-info\">"+
							"<h4><a href=\"single.html\">"+productName+"</a></h4>"+
							"<ul class=\"qty\">"+
								"<li><p>FREE delivery</p></li>"+
							"</ul>"+
							"<div class=\"delivery\">"+
								"<p>Price: $"+productPrice+"</p>"+
								"<div class=\"clearfix\"></div>"+
							"</div>"+
						"</div>"+
						"<div class=\"clearfix\"></div>"+
					"</div>";
		}
		request.getSession().setAttribute("cartHTML", cartHTML);
		response.sendRedirect(request.getContextPath()+"/Cart.jsp");
	}

}
