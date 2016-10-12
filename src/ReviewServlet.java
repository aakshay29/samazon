

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBSamazonProduct;
import customTools.DBSamazonReview;
import model.Samazonreview;
import model.Samazonuser;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
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
		String review = request.getParameter("review");
		HttpSession session = request.getSession();
		Samazonreview review2 = new Samazonreview();
		review2.setReview(review);
		review2.setSamazonuser((Samazonuser) session.getAttribute("user"));
		review2.setSamazonproduct(DBSamazonProduct.getProduct(productID));
		DBSamazonReview.addReview(review2);
		response.sendRedirect(request.getContextPath()+"/Single.jsp");
	}

}
