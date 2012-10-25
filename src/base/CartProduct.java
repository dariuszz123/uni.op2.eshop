package base;

public class CartProduct {
	
	private String isbn;
	private String name;
	private double price;
	private int weight;
	private int quantity;

	private final static String currency = "LTL";

	public CartProduct(String isbn, String name, double price,
			int weight, int quantity) {
		this.setIsbn(isbn);
		this.setName(name);
		this.setPrice(price);
		this.setWeight(weight);
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

	/**
	 * Price in currency like 1.99
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Price in currency like 1.99
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Weight in grams
	 * 
	 * @return
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Weight in grams
	 * 
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public static String getCurrency() {
		return currency;
	}

	public double getTotalPrice() {
		return this.getPrice() * this.getQuantity();
	}
	
	@Override
	public String toString() {
		String s = new String();
		
		s.concat("-=| Cart Product data |=-");
		s.concat("\n" + "ISBN: " + this.getIsbn());
		s.concat("\n" + "Name: " + this.getName());
		s.concat("\n" + "Price: " + this.getPrice() + " " + Product.getCurrency());
		s.concat("\n" + "Weight: " + this.getWeight());
		s.concat("\n" + "Quantity: " + this.getQuantity());
		s.concat("\n" + "Total price: " + this.getTotalPrice());
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CartProduct) {
			if(this.getIsbn().equals( ((CartProduct)obj).getIsbn())) {
				return true;
			}else{
				return false;
			}
		}else{
			return super.equals(obj);
		}
	}
	
}
