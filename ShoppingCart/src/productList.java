
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;


/**
 * Servlet implementation class productList
 */
@WebServlet("/productList")
public class productList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	cartList cart_list = new cartList();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productList() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// list of the the products explicityly -- each with desc and price
		// get values of product using product name
		String line = "";
		cartItems items = new cartItems();
		items.setProd_name(request.getParameter("name"));
				
		HttpSession session = request.getSession();
		List<Product> prod_list = DBtrans.selectProdOne(request.getParameter("name"));
		
		
		   line += "<style>"
					+ ".bs-example{" + "margin-top:20%" + "margin-left:20%"
					+  "margin-bottom:20%" + "}"

					+ "table { " + " table-layout: fixed;"
					+ " word-wrap: break-word;" + "}" 
					+ "</style>";
	        
	        
	        line += "<table class=" 
	        		+ "\"table table-striped\"" 
	        		+ "style=width:100%>";
	        
	        line += 
	     			"<tr>" 
	     			+"<th>" + "Name" + "</th><br>"
	     			+"<th>" + "Description" + "</th><br>"
	     			+"<th>" + "Price" + "</th><br>"
	     			+ "</tr>"
	     			;
	        
		for (int i = 0; i < prod_list.size(); i++){
			line += "<tr>" 
					+ "<td>" + prod_list.get(i).getProdName() + "</td>"
					+ "<td>" + prod_list.get(i).getDescription() + "</td>"
					+ "<td>" + prod_list.get(i).getPrice() + "</td>"
					+"</tr>"
					;
			
			items.setPrice(prod_list.get(i).getPrice().doubleValue());
			cart_list.setCart_list(items);
			}
		
		line += "</table>";
		
		line +=  "<form action=cart method=post>"
				+"<div>"
				+ "<input type=text name=quan placeholder=quantity style=margin-left:20% style=width:30%>"
				+ "<input class=btn btn-primary btn-lg type=submit value='add to Cart' name=sub style=margin-left:20% style=width:30%>"
				+"</div>"
				+"</form>"
				;
		
		request.setAttribute("message", line);

		String form_back =  "<form action=productList method=post>"
				+"<div class=input-group input-group-sm style=width:30%>"
				+ "<input class=btn btn-primary btn-lg type=submit value='GoBack'>"
				+ "</div>"
				+"</form>"
				;
		 request.setAttribute("form_back", form_back);
		 session.setAttribute("listVal", cart_list);
		 getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			// set cookie attributes
			Cookie c = new Cookie("usernamecookie", request.getParameter("user_name"));
			c.setMaxAge(60*60); //set its age to 1 hour
			c.setPath("/"); //allow the entire application to access it
			List<Product> prod_list = DBtrans.selectProdAll();
		    String line= "";
		        line += "<style>"
						+ ".bs-example{" + "margin-top:20%" + "margin-left:20%"
						+  "margin-bottom:20%" + "}"

						+ "table { " + " table-layout: fixed;"
						+ " word-wrap: break-word;" + "}"
						+ "</style>";
		        
		        line += "<table class=" 
		        		+ "\"table table-striped\"" 
		        		+ "style=width:100%>";
		        
		
		        line += 
		     			"<tr>" 
		     			+"<th>" + "product list" + "</th><br>"
		     			+ "</tr>"
		     			;

				for (int i = 0; i < prod_list.size(); i++){
						line += "<tr>"  
								+ "<td><a href= " + "\'" + "productList?name="+ URLEncoder.encode(prod_list.get(i).getProdName())
								+ "\'" + ">"+ prod_list.get(i).getProdName() + "</a>"
								+"</td>"
								+"</tr>"
								;
						
			}
				
				line += "</table>";		
		     
		        request.setAttribute("message", line);
		    	response.addCookie(c);
		        getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);	
		       
		        
			}catch (Exception e){
				e.printStackTrace();
			}	
		
	}

}
