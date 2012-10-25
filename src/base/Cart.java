package base;
import java.util.Iterator;
import java.util.Vector;

public class Cart {

	private Vector<CartProduct> products;
	
	public Cart() {
		this.setProducts(new Vector<CartProduct>());
	}
	
	public Vector<CartProduct> getProducts() {
		return products;
	}

	public void setProducts(Vector<CartProduct> products) {
		this.products = products;
	}
	
	public CartProduct findProduct(String isbn) {
		Iterator<CartProduct> productsIterator = this.getProducts().iterator();
		while (productsIterator.hasNext()) {
			CartProduct cartProduct = productsIterator.next();
			if(cartProduct.getIsbn() == isbn) {
				return cartProduct;
			}
		}
		return null;
	}
	
	public void addProduct(Product product, int quantity) throws java.lang.Exception {
		CartProduct cartProduct;
		cartProduct = findProduct(product.getIsbn());
		
		if(product.getQuantity() < quantity) {
			throw new java.lang.Exception("This quanity not available at the moment!");
		}else{
			if(cartProduct != null) {
				cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
			}else{
				cartProduct = new CartProduct(product.getIsbn(), product.getName(), product.getPrice(), product.getWeight(), quantity);
				this.products.add(cartProduct);
			}
			product.setQuantity(product.getQuantity()-quantity);
		}
	}
	
	public void removeProduct(String isbn, Product returnQuantityToProduct) {
		CartProduct cartProduct = this.findProduct(isbn);
		if(cartProduct != null) {
			if(returnQuantityToProduct != null) {
				returnQuantityToProduct.setQuantity(returnQuantityToProduct.getQuantity() + cartProduct.getQuantity());
			}
			this.getProducts().remove(cartProduct);
		}
	}
	
	public double getTotalPrice() {
		double amount = 0;

		Iterator<CartProduct> productsIterator = this.getProducts().iterator();
		while (productsIterator.hasNext()) {
			amount += productsIterator.next().getTotalPrice();
		}

		return amount;
	}
	
	public void clear() {
		this.getProducts().removeAllElements();
	}
	
	public int productsCount() {
		return this.getProducts().size();
	}
	
}
