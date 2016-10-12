

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBSamazonCategory;
import customTools.DBSamazonProduct;
import model.Samazonproduct;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
		String action = request.getParameter("action");	
		String nextUrl = "";
		HttpSession session = request.getSession();
		if(action.equalsIgnoreCase("edit")){
			int productID = Integer.parseInt(request.getParameter("productID"));	
			Samazonproduct adminProduct = DBSamazonProduct.getProduct(productID);
			session.setAttribute("adminProduct", adminProduct);	
			nextUrl = "/AdminEdit.jsp";
		}
		if(action.equalsIgnoreCase("update")){
			int productID = Integer.parseInt(request.getParameter("productID"));
			Samazonproduct updateProduct = DBSamazonProduct.getProduct(productID);
			String productname = request.getParameter("productname");
			String productdescription = request.getParameter("productdescription");
			int productprice = Integer.parseInt(request.getParameter("productprice"));
			String productimage = request.getParameter("productimage");
			String productseller = request.getParameter("productseller");
			int productcategory = Integer.parseInt(request.getParameter("productcategory"));
			updateProduct.setName(productname);
			updateProduct.setDescription(productdescription);
			updateProduct.setPrice(productprice);
			updateProduct.setImage(productimage);
			updateProduct.setSeller(productseller);
			updateProduct.setSamazoncategory(DBSamazonCategory.getCategory(productcategory));
			DBSamazonProduct.updateProduct(updateProduct);
			
			List<Samazonproduct> productList = null;
			productList = DBSamazonProduct.getProductList();
			session.setAttribute("productList", productList);	

			nextUrl = "/ProductListAdmin.jsp";
		}
		if(action.equalsIgnoreCase("add")){
			Samazonproduct newProduct = new Samazonproduct();
			String productname = request.getParameter("productname");
			String productdescription = request.getParameter("productdescription");
			int productprice = Integer.parseInt(request.getParameter("productprice"));
			String productimage = request.getParameter("productimage");
			String productseller = request.getParameter("productseller");
			int productcategory = Integer.parseInt(request.getParameter("productcategory"));
			newProduct.setName(productname);
			newProduct.setDescription(productdescription);
			newProduct.setPrice(productprice);
			newProduct.setImage(productimage);
			newProduct.setSeller(productseller);
			newProduct.setSamazoncategory(DBSamazonCategory.getCategory(productcategory));
			DBSamazonProduct.addProduct(newProduct);
			
			List<Samazonproduct> productList = null;
			productList = DBSamazonProduct.getProductList();
			session.setAttribute("productList", productList);	

			nextUrl = "/ProductListAdmin.jsp";
		}
		response.sendRedirect(request.getContextPath()+nextUrl);
	}

}
