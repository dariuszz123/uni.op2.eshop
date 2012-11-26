package base;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
	
	public double getPrice() {
		return price;
	}
	
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return this.getPrice() * this.getQuantity();
	}
	
	@Override
	public String toString() {
		String s = new String();
		
		s.concat("-=| Order product data |=-");
		s.concat("\n" + "ISBN: " + this.getIsbn());
		s.concat("\n" + "Name: " + this.getName());
		s.concat("\n" + "Price: " + this.getPrice() + " " + Product.getCurrency());
		s.concat("\n" + "Quantity: " + this.getQuantity());
		s.concat("\n" + "Total price: " + this.getTotalPrice());
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof OrderProduct) {
			if(this.getIsbn().equals( ((OrderProduct)obj).getIsbn())) {
				return true;
			}else{
				return false;
			}
		}else{
			return super.equals(obj);
		}
	}
}
