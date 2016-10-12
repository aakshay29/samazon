

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBSamazonAddress;
import customTools.DBSamazonUser;
import model.Samazonaddress;
import model.Samazonuser;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		Samazonuser user = new Samazonuser();
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");		
		
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		DBSamazonUser.addUser(user);
		
		Samazonaddress samazonAddress = new Samazonaddress();
		samazonAddress.setAddress(address);
		samazonAddress.setSamazonuser(user);
		DBSamazonAddress.addAddress(samazonAddress);
		
		Samazonuser updateUser = DBSamazonUser.getUserByUsername(username);
		updateUser.setSamazonaddresses(DBSamazonAddress.getAddressList((int) updateUser.getUserid()));
		DBSamazonUser.updateUser(updateUser);
		response.sendRedirect(request.getContextPath()+"/Login.jsp");
	}

}
