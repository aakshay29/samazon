

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBSamazonProduct;
import customTools.DBSamazonRating;
import model.Samazonrating;
import model.Samazonuser;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet("/RatingServlet")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
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
		int rating = Integer.parseInt(request.getParameter("rating"));
		HttpSession session = request.getSession();
		Samazonrating rating2 = new Samazonrating();
		rating2.setRating(rating);
		rating2.setSamazonuser((Samazonuser) session.getAttribute("user"));
		rating2.setSamazonproduct(DBSamazonProduct.getProduct(productID));
		DBSamazonRating.addRating(rating2);
		response.sendRedirect(request.getContextPath()+"/Single.jsp");
	}

}
