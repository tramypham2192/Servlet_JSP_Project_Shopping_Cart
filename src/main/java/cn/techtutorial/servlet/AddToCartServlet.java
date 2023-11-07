package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.techtutorial.model.Cart;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddToCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = UTF-8");
		try (PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			//de add product vao cart thi phai co id cua product do
			int id = Integer.parseInt(request.getParameter("id")); //get the id from the url
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1); // set it to 1 by default
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if (cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart_list", cartList);
				System.out.println("Session created");
			} else {
				cartList = cart_list;
				//check if the product already exist in cart or not
				boolean exist = false;
				for (Cart c : cartList) {
					if (c.getId() == id) {
						exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
					}
				}
				if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
			}
		}
	}
}
