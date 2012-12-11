package base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author darius
 * 
 */

@SuppressWarnings("serial")
@XmlRootElement
public class Product implements Serializable{

	private String isbn;
	private String name;
	private String description;
	private double price;
	private int weight;
	private int quantity;

	private final static String currency = "LTL";

	public Product(String isbn, String name, String description, double price,
			int weight, int quantity) {
		this.setIsbn(isbn);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setWeight(weight);
		this.setQuantity(quantity);
	}

	public Product() {
		this("000-0-00000-000-0", "Dramblys", "Labai geras dramblys", 10.00,
				10000, 10);
	}

	public String getIsbn() {
		return isbn;
	}
	
	@XmlAttribute
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	@XmlElement
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
	@XmlElement
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
	@XmlElement
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getQuantity() {
		return quantity;
	}
	
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static String getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		String s = new String();

		s.concat("-=| Cart Product data |=-");
		s.concat("\n" + "ISBN: " + this.getIsbn());
		s.concat("\n" + "Name: " + this.getName());
		s.concat("\n" + "Description: " + this.getDescription());
		s.concat("\n" + "Price: " + this.getPrice() + " "
				+ Product.getCurrency());
		s.concat("\n" + "Weight: " + this.getWeight());
		s.concat("\n" + "Available quantity: " + this.getQuantity());
		s.concat("\n" + "Price: " + this.getPrice());

		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			if (this.getIsbn().equals(((Product) obj).getIsbn())) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}

}
