import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author darius
 * 
 */
public class Eshop {

	private Vector<Order> orders;
	private Vector<Product> products;
	private Cart cart;

	public Eshop() {
		this.setOrders(new Vector<Order>());
		this.setProducts(new Vector<Product>());
	}

	public Product findProduct(String isbn) {
		Iterator<Product> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			Product product = productsIterator.next();
			if (product.getIsbn() == isbn) {
				return product;
			}
		}

		return null;
	}

	public Vector<Product> findProduct(double price) {

		Vector<Product> foundProducts = new Vector<Product>();
		Iterator<Product> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			Product product = productsIterator.next();
			if (product.getPrice() == price) {
				foundProducts.add(product);
			}
		}

		if (foundProducts.size() > 0) {
			return foundProducts;
		} else {
			return null;
		}

	}

	public void addProduct(Product product) throws Exception {
		if (this.findProduct(product.getIsbn()) == null) {
			this.getProducts().add(product);
		} else {
			throw new Exception("Product already exists!");
		}
	}
		
	public void createOrder(String name, String surname, String country, String city, String address, String postCode) {
		Order order = new Order(name, surname, country, city, address, postCode, this.getCart());
		this.getOrders().add(order);
	}

	public double calcTotalOrdersAmount() {
		double amount = 0;

		Iterator<Order> ordersIterator = this.getOrders().iterator();

		while (ordersIterator.hasNext()) {
			amount += ordersIterator.next().getTotalPrice();
		}

		return amount;
	}

	public int calcTotalOrderedProducts() {
		int totalProducts = 0;
		
		Iterator<Order> ordersIterator = this.getOrders().iterator();
		
		while (ordersIterator.hasNext()) {
			totalProducts += ordersIterator.next().getProducts().size();
		}
		
		return totalProducts;
	}

	public Vector<Order> getOrders() {
		return orders;
	}

	public void setOrders(Vector<Order> orders) {
		this.orders = orders;
	}

	public Vector<Product> getProducts() {
		return products;
	}

	public void setProducts(Vector<Product> products) {
		this.products = products;
	}

	public Cart getCart() {
		return cart;
	}
	
}
