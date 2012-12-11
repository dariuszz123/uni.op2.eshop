package base;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Shopping {

	private String name;
	private String surname;
	private String country;
	private String city;
	private String address;
	private String postCode;
	private ArrayList<String> products;
	
	public Shopping() {
		
	}
	
	public Shopping(String name, String surname, String country, String city,
			String address, String postCode, ArrayList<String> products) {
		super();
		this.name = name;
		this.surname = surname;
		this.country = country;
		this.city = city;
		this.address = address;
		this.postCode = postCode;
		this.products = products;
	}

	public String getName() {
		return name;
	}
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	
	@XmlAttribute
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}
	
	@XmlAttribute
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}
	
	@XmlAttribute
	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}
	
	@XmlAttribute
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}
	
	@XmlAttribute
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public ArrayList<String> getProducts() {
		return products;
	}
	
	@XmlElement(name = "product")
	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}

}
