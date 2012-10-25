package base;
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
	
	private int num;
	private String name;
	private String surname;
	private String country;
	private String city;
	private String address;
	private String postCode;
	private Date createdAt;
	private Vector<OrderProduct> products;

	public Order(int num, String name, String surname, String country, String city, String address, String postCode, Cart cart) {
		this.setNum(num);
		this.setName(name);
		this.setSurname(surname);
		this.setCountry(country);
		this.setCity(city);
		this.setAddress(address);
		this.setPostCode(postCode);
		this.setProducts(new Vector<OrderProduct>());
		this.setProductsFromCart(cart);
		this.setCreatedAt(new Date());
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public Vector<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(Vector<OrderProduct> products) {
		this.products = products;
	}
	
	public void setProductsFromCart(Cart cart) {
		Iterator<CartProduct> productsIterator = cart.getProducts().iterator();
		while (productsIterator.hasNext()) {
			CartProduct cartProduct;
			cartProduct = productsIterator.next();
			
			OrderProduct orderProduct;
			orderProduct = new OrderProduct(cartProduct.getIsbn(), cartProduct.getName(), cartProduct.getPrice(), cartProduct.getQuantity());
			
			products.add(orderProduct);
		}
	}

	public double getTotalPrice() {
		double amount = 0;

		Iterator<OrderProduct> productsIterator = this.getProducts().iterator();
		while (productsIterator.hasNext()) {
			amount += productsIterator.next().getTotalPrice();
		}

		return amount;
	}
	
	public int getTotalProductsQuantity() {
		int amount = 0;

		Iterator<OrderProduct> productsIterator = this.getProducts().iterator();
		while (productsIterator.hasNext()) {
			amount += productsIterator.next().getQuantity();
		}

		return amount;
	}
	
	@Override
	public String toString() {
		Format dateFormatter;
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String s = new String();
		s.concat("-=| Order data output |=-");
		s.concat("\n"+"Name: " + this.getName());
		s.concat("\n"+"Surname: " + this.getSurname());
		s.concat("\n"+"Country: " + this.getCountry());
		s.concat("\n"+"City: " + this.getCity());
		s.concat("\n"+"Address: " + this.getAddress());
		s.concat("\n"+"Post code: " + this.getPostCode());
		s.concat("\n"+"Created at: "
				+ dateFormatter.format(this.getCreatedAt()));
		s.concat("\n"+"Order products:");

		Iterator<OrderProduct> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			s.concat("\n" + productsIterator.next().toString());
		}
		
		return s;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Product) {
			if(this.getNum() == ((Order)obj).getNum()) {
				return true;
			}else{
				return false;
			}
		}else{
			return super.equals(obj);
		}
	}

}
