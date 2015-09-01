import java.util.ArrayList;


public class cartList {

	ArrayList<cartItems> cart_list = new ArrayList<cartItems>();

	public ArrayList<cartItems> getCart_list() {
		return cart_list;
	}


	public void setCart_list(cartItems items) {
		cart_list.add(items);
	}
	
}
