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
		this.setCart(new Cart());
	}

	public Product findProduct(String isbn) {
		Iterator<Product> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			Product product = productsIterator.next();

			if (product.getIsbn().equals(isbn)) {
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
	
	public void addProduct(String isbn, String name, String description, double price,
			int weight, int quantity) throws Exception {
		this.addProduct(new Product(isbn, name, description, price, weight, quantity));
	}
	
	public void addQuantity(Product product, int quantity) {
		product.setQuantity(product.getQuantity() + quantity);
	}
	
	private int getNextOrderNum() {
		int num = 0;
		
		Iterator<Order> orderIterator = this.getOrders().iterator();
		
		while (orderIterator.hasNext()) {
			Order order = orderIterator.next();
			if(order.getNum() > num) {
				num = order.getNum();
			}
		}
		
		return (num + 1);
	}
	
	public void createOrder(String name, String surname, String country, String city, String address, String postCode) throws Exception {
		
		if(this.getCart().getProducts().size() > 0) {
			Order order = new Order(this.getNextOrderNum(), name, surname, country, city, address, postCode, this.getCart());
			this.getOrders().add(order);
			this.getCart().clear();
		}else{
			throw new Exception("Cart is empty!");
		}
	}
	
	public Order findOrderByNum(int num) {
		Iterator<Order> orderIterator = this.getOrders().iterator();

		while (orderIterator.hasNext()) {
			Order order = orderIterator.next();
			if(order.getNum() == num) {
				return order;
			}
		}
		
		return null;
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
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCart() {
		return cart;
	}
	
}
