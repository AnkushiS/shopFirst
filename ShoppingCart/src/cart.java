

import java.awt.List;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	double total = 0;
	//cartList list = new cartList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	     cartList cl   = (cartList)session.getAttribute("listVal");
	     
	     String line= "<h3> Order confirmation</h3>";
	        line += "<style>"
					+ ".bs-example{" + "margin-top:20%" + "margin-left:20%"
					+  "margin-bottom:20%" + "}"

					+ "table { " + " table-layout: fixed;"
					+ " word-wrap: break-word;" + "}" + "</style>";
	        
	        
	        line += "<table class=" 
	        		+ "\"table table-striped\"" 
	        		+ "style=width:100%>";
	        
	        line += 
	     			"<tr>" 
	     			+"<th>" + "product list" + "</th><br>"
	     			+"<th>" + "total Price" + "</th><br>"
	     			+ "</tr>"
	     			;
	     
	     
	        String CheckoutTotal = session.getAttribute("total").toString();
	        for(int i=0; i< cl.getCart_list().size(); i++ ){
	      	        
	        line += "<tr>" 
					+ "<td>" +cl.getCart_list().get(i).getProd_name()+ "</td>"
					+ "<td>" + CheckoutTotal + "</td>"
					+"</tr>"
					;
	        }
			line += "</table>";
			request.setAttribute("message", line);
	        
			 getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);	
	        
	        
	        
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		cartItems items = new cartItems();
		int quan = Integer.parseInt(request.getParameter("quan"));
		items.setQuantity(quan);
	
		String line= "<h3> shopping cart Page</h3>";
        line += "<style>"
				+ ".bs-example{" + "margin-top:20%" + "margin-left:20%"
				+  "margin-bottom:20%" + "}"

				+ "table { " + " table-layout: fixed;"
				+ " word-wrap: break-word;" + "}" + "</style>";
        
        
        line += "<table class=" 
        		+ "\"table table-striped\"" 
        		+ "style=width:100%>";
        
        line += 
     			"<tr>" 
     			+"<th>" + "product list" + "</th><br>"
     			+"<th>" + "quantity" + "</th><br>"
     			+"<th>" + "total Price" + "</th><br>"
     			+ "</tr>"
     			;

        HttpSession session = request.getSession();
        cartList cl   = (cartList)session.getAttribute("listVal");
    	
        for(int i=0; i< cl.getCart_list().size(); i++ ){
        	
        	double price = cl.getCart_list().get(i).getPrice();
        	
        	double totalPrice = price * items.getQuantity();
        	items.setTotalPrice(totalPrice);
        
        //	cl.setCart_list(items);
        	
        	total = totalPrice + total;
        	
        	session.setAttribute("total", total);
        line += "<tr>" 
				+ "<td>" +cl.getCart_list().get(i).getProd_name()  + "</td>"
				+ "<td>" + quan + "</td>"
				+ "<td>" + totalPrice + "</td>"
				+"</tr>"
				;
        }
		line += "</table>";
		request.setAttribute("message", line);
		String form_back =  "<form action=productList method=post>"
				+"<div class=input-group input-group-sm style=width:30%>"
				+ "<input class=btn btn-primary btn-lg type=submit value='Add more items'>"
				+ "</div>"
				+"</form>"
				;
		 request.setAttribute("form_back", form_back);
		 
		 String form_sub =  "<form action=cart method=get>"
					+"<div>"
					+ "<input class=btn btn-primary type=submit value='CheckOut' name=sub style=margin-left:20% style=width:30%>"
					+"</div>"
					+"</form>"
					;
			
		request.setAttribute("form_sub", form_sub);
        getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);	
 		
	}


}
