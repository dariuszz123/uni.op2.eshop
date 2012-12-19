package base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Eshop - e. commerce platform main object
 * @author Darius Kriðtapavièius
 *
 */
@SuppressWarnings("serial")
public class Eshop implements Serializable {
	
	/**
	 * List of all shop orders
	 */
	private Orders orders;
	
	/**
	 * List of all shop products
	 */
	private Products products;
	
	/**
	 * Current user cart
	 */
	private Cart cart;

	/**
	 * Empty constructor
	 */
	public Eshop() {
		this.setOrders(new Orders());
		this.setProducts(new Products());
		this.setCart(new Cart());
	}
	
	/**
	 * Orders list getter
	 * @return order list
	 */
	public List<Order> getOrders() {
		return orders;
	}
	
	/**
	 * Orders list setter
	 * @param orders list
	 */
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	/**
	 * Products list getter
	 * @return products list
	 */
	public List<Product> getProducts() {
		return products;
	}
	
	/**
	 * Products list setter
	 * @param products list
	 */
	public void setProducts(Products products) {
		this.products = products;
	}
	
	/**
	 * Current user cart setter
	 * @param cart
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	/**
	 * Current user cart getter
	 * @return cart
	 */
	public Cart getCart() {
		return cart;
	}
	
	/**
	 * Find one product by isbn number
	 * @param isbn
	 * @return Product
	 */
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
	
	/**
	 * Find products by price
	 * @param price
	 * @return list of products
	 */
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
	
	/**
	 * Add product to shop (products list)
	 * @param product
	 * @throws Exception
	 */
	public void addProduct(Product product) throws Exception {
		if (this.findProduct(product.getIsbn()) == null) {
			this.getProducts().add(product);
		} else {
			throw new Exception("Product already exists!");
		}
	}
	
	/**
	 * Add product to shop (products list)
	 * @param isbn
	 * @param name
	 * @param description
	 * @param price
	 * @param weight
	 * @param quantity
	 * @throws Exception
	 */
	public void addProduct(String isbn, String name, String description,
			double price, int weight, int quantity) throws Exception {
		this.addProduct(new Product(isbn, name, description, price, weight,
				quantity));
	}
	
	/**
	 * Add quantity to product
	 * @param product
	 * @param quantity
	 */
	public void addQuantity(Product product, int quantity) {
		product.setQuantity(product.getQuantity() + quantity);
	}
	
	/**
	 * Generate next number for order
	 * @return number
	 */
	private int getNextOrderNum() {
		int num = 0;

		Iterator<Order> orderIterator = this.getOrders().iterator();

		while (orderIterator.hasNext()) {
			Order order = orderIterator.next();
			if (order.getNum() > num) {
				num = order.getNum();
			}
		}

		return (num + 1);
	}
	
	/**
	 * Create new order from current cart
	 * @param name
	 * @param surname
	 * @param country
	 * @param city
	 * @param address
	 * @param postCode
	 * @throws EmptyCartException
	 * @throws EmptyValueException
	 */
	public void createOrder(String name, String surname, String country,
			String city, String address, String postCode)
			throws EmptyCartException, EmptyValueException {

		if (this.getCart().getProducts().size() > 0) {
			Order order = new Order(this.getNextOrderNum(), name, surname,
					country, city, address, postCode, this.getCart());
			this.getOrders().add(order);
			this.getCart().clear();
		} else {
			throw new EmptyCartException();
		}

	}
	
	/**
	 * Find order by order number
	 * @param num
	 * @return order
	 */
	public Order findOrderByNum(int num) {
		Iterator<Order> orderIterator = this.getOrders().iterator();

		while (orderIterator.hasNext()) {
			Order order = orderIterator.next();
			if (order.getNum() == num) {
				return order;
			}
		}

		return null;
	}
	
	/**
	 * Calculates all orders price
	 * @return price
	 */
	public double calcTotalOrdersAmount() {
		double amount = 0;

		Iterator<Order> ordersIterator = this.getOrders().iterator();

		while (ordersIterator.hasNext()) {
			amount += ordersIterator.next().getTotalPrice();
		}

		return amount;
	}
	
	/**
	 * Calculates all orders products quantities
	 * @return quantity
	 */
	public int calcTotalOrderedProducts() {
		int totalProducts = 0;

		Iterator<Order> ordersIterator = this.getOrders().iterator();

		while (ordersIterator.hasNext()) {
			totalProducts += ordersIterator.next().getProducts().size();
		}

		return totalProducts;
	}

}
