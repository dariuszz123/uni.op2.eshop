import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author darius
 * 
 */
public class Order {

	private String name;
	private String surname;
	private String country;
	private String city;
	private String address;
	private String postCode;
	private Date createdAt;
	private Vector<Product> products;

	public Order(String name, String surname, String country, String city, String address, String postCode) {
		this.setName(name);
		this.setSurname(surname);
		this.setCountry(country);
		this.setCity(city);
		this.setAddress(address);
		this.setPostCode(postCode);
		this.setProducts(new Vector<Product>());
		this.setCreatedAt(new Date());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createAt) {
		this.createdAt = createAt;
	}

	public Vector<Product> getProducts() {
		return products;
	}

	public void setProducts(Vector<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public double getTotalPrice() {
		double amount = 0;

		Iterator<Product> productsIterator = this.getProducts().iterator();
		while (productsIterator.hasNext()) {
			amount += productsIterator.next().getPrice();
		}

		return amount;
	}

	public void println() {
		Format dateFormatter;
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println("-=| Order data output |=-");
		System.out.println("Name: " + this.getName());
		System.out.println("Surname: " + this.getSurname());
		System.out.println("Country: " + this.getCountry());
		System.out.println("City: " + this.getCity());
		System.out.println("Address: " + this.getAddress());
		System.out.println("Post code: " + this.getPostCode());
		System.out.println("Created at: "
				+ dateFormatter.format(this.getCreatedAt()));
		System.out.println("Order products:");

		Iterator<Product> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			productsIterator.next().println();
		}

	}

}
