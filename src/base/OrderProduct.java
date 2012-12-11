package base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class OrderProduct implements Cloneable, Serializable {
	
	private String isbn;
	private String name;
	private double price;
	private int quantity;
	
	public OrderProduct() {
		
	}
	
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
		StringBuilder s = new StringBuilder();
		
		s.append("-=| Order product data |=-");
		s.append("\n" + "ISBN: " + this.getIsbn());
		s.append("\n" + "Name: " + this.getName());
		s.append("\n" + "Price: " + this.getPrice() + " " + Product.getCurrency());
		s.append("\n" + "Quantity: " + this.getQuantity());
		s.append("\n" + "Total price: " + this.getTotalPrice());
		
		return s.toString();
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
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen
			throw new InternalError(e.toString());
		}
	}
	
}
