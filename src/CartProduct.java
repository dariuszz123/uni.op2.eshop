
public class CartProduct extends Product {
	
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return this.getPrice() * this.getQuantity();
	}
	
	public void println() {
		super.println();
		System.out.println("Total price: " + this.getTotalPrice());
	}
	
}
