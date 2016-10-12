

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBSamazonProduct;
import customTools.DBSamazonRating;
import model.Samazonproduct;

/**
 * Servlet implementation class SingleServlet
 */
@WebServlet("/SingleServlet")
public class SingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleServlet() {
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
		int productID = Integer.parseInt(request.getParameter("productID"));	
		Samazonproduct product = DBSamazonProduct.getProduct(productID);
		String productHTML = "";
		productHTML += "<div class=\"single\">"+
		"<div class=\"container\">"+
		"<div class=\"single-info\">"+		
			"<div class=\"col-md-6 single-top-left simpleCart_shelfItem wow fadeInRight animated\" data-wow-delay=\".5s\">"+
				"<h3>"+product.getName()+"</h3>"+
				"<div class=\"single-rating\">";
				
			int rating = DBSamazonRating.getRating(productID);
					if(rating == 0){
						productHTML += "No ratings yet";
					}
					else{
						for(int j = 0; j < rating; j++){
							productHTML += "<span class=\"on\">☆</span>";
						}
						for(int k = 0; k < 5-rating; k++){
							productHTML += "<span>☆</span>";
						}
					}
					
		productHTML += "</div>"+
				"<h6 class=\"item_price\">$"+product.getPrice()+"</h6>"+		
				"<p>Seller: "+product.getSeller()+"</p>"+
				"<a href=\"single.html\"><img src="+product.getImage()+" class=\"img-responsive\" alt=\"\"/></a>"+
				"<div class=\"btn_form\">"+
					"<form action=\"AddRemoveCartServlet\" method=\"post\">"+
						"<input type=\"hidden\" name=\"productID\" value="+productID+">"+
						"<input type=\"hidden\" name=\"action\" value=\"add\">"+
						"<input type=\"submit\" value=\"Add to cart\" id=\"submit\">"+
					"</form>"+
				"</div>"+
				"<div class=\"btn_form\">"+
					"<form action=\"ReviewServlet\" method=\"post\">"+
						"<input type=\"hidden\" name=\"productID\" value="+productID+">"+
						"Add your review: <input type=\"text\" name=\"review\">"+
						"<input type=\"submit\" value=\"Submit\" id=\"submit\">"+
					"</form>"+
				"</div>"+
				"<div class=\"btn_form\">"+
					"<form action=\"RatingServlet\" method=\"post\">"+
						"<input type=\"hidden\" name=\"productID\" value="+productID+">"+
						"Add your rating (1-5): <input type=\"text\" name=\"rating\">"+
						"<input type=\"submit\" value=\"Submit\" id=\"submit\">"+
					"</form>"+
				"</div>"+
			"</div>"+
		   "<div class=\"clearfix\"> </div>"+
		"</div>"+
		"<div class=\"collpse tabs\">"+
			"<div class=\"panel-group collpse\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">"+
				"<div class=\"panel panel-default wow fadeInUp animated\" data-wow-delay=\".5s\">"+
					"<div class=\"panel-heading\" role=\"tab\" id=\"headingOne\">"+
						"<h4 class=\"panel-title\">"+
							"<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseOne\" aria-expanded=\"true\" aria-controls=\"collapseOne\">"+
							 "Description"+
							"</a>"+
						"</h4>"+
					"</div>"+
					"<div id=\"collapseOne\" class=\"panel-collapse collapse in\" role=\"tabpanel\" aria-labelledby=\"headingOne\">"+
						"<div class=\"panel-body\">"+
							""+product.getDescription()+""+
						"</div>"+
					"</div>"+
				"</div>"+
				
				"<div class=\"panel panel-default wow fadeInUp animated\" data-wow-delay=\".7s\">"+
					"<div class=\"panel-heading\" role=\"tab\" id=\"headingThree\">"+
						"<h4 class=\"panel-title\">"+
						"<a class=\"collapsed\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapseThree\" aria-expanded=\"false\" aria-controls=\"collapseThree\">"+
								"reviews"+
							"</a>"+
						"</h4>"+
					"</div>"+
					"<div id=\"collapseThree\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"headingThree\">"+
						"<div class=\"panel-body\">";	
					for(int i = 0; i < product.getSamazonreviews().size(); i++){
						productHTML += ""+product.getSamazonreviews().get(0).getReview()+"<br/><br/>";
					}				
					productHTML += "</div>"+
					"</div>"+
				"</div>"+
				"</div>"+
				"</div>"+
				"</div>"+
				"</div>";
					request.getSession().setAttribute("singleProduct", productHTML);
					response.sendRedirect(request.getContextPath()+"/Single.jsp");
	}

}
