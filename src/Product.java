/**
 * 
 * @author darius
 * 
 */
public class Product {

	private String isbn;
	private String name;
	private String description;
	private double price;
	private int weight;
	private int quantity;

	private final static String currency = "LTL";

	public Product(String isbn, String name, String description, double price,
			int weight) {
		this.setIsbn(isbn);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setWeight(weight);
	}

	public Product() {
		this("000-0-00000-000-0", "Dramblys", "Labai geras dramblys", 10.00,
				10000);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void println() {
		System.out.println("-=| Product data |=-");
		System.out.println("ISBN: " + this.getIsbn());
		System.out.println("Name: " + this.getName());
		System.out.println("Description: " + this.getDescription());
		System.out.println("Price: " + this.getPrice() + " " + Product.getCurrency());
		System.out.println("Weight: " + this.getWeight());
		System.out.println("Quantity: " + this.getQuantity());
	}
	
}
