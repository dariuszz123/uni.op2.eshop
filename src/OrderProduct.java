
public class OrderProduct {
	
	private String isbn;
	private String name;
	private double price;
	private int quantity;
	
	public OrderProduct(String isbn, String name, double price, int quantity) {
		this.setIsbn(isbn);
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
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
		System.out.println("-=| Order product data |=-");
		System.out.println("ISBN: " + this.getIsbn());
		System.out.println("Name: " + this.getName());
		System.out.println("Price: " + this.getPrice() + " " + Product.getCurrency());
		System.out.println("Quantity: " + this.getQuantity());
		System.out.println("Total price: " + this.getTotalPrice());
	}
	
}
