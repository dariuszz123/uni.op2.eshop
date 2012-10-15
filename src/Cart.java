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
	
	public boolean addProduct(Product product, int quantity) {
		CartProduct cartProduct;
		cartProduct = findProduct(product.getIsbn());
		if(cartProduct != null) {
			cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
		}else{
			cartProduct = (CartProduct) product;
			cartProduct.setQuantity(quantity);
			this.products.add(cartProduct);
		}
		return true;
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
