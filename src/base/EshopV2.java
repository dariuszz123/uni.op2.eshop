package base;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Eshop version 2
 * @author Darius Kriðtapavièius
 *
 */
@SuppressWarnings("serial")
public class EshopV2 extends Eshop {
	
	/**
	 * Customer ordered products map
	 */
	private Map<String, List<Product>> customerOrderedProducts;
	
	/**
	 * Constructor
	 */
	public EshopV2() {
		super();
		this.setCustomerOrderedProducts(new HashMap<String, List<Product>>());
	}
	
	/**
	 * Customer ordered products map getter
	 * @return customer ordered products map
	 */
	public Map<String, List<Product>> getCustomerOrderedProducts() {
		return customerOrderedProducts;
	}
	
	/**
	 * Customer ordered products map setter
	 * @param customerOrderedProducts customer ordered products map
	 */
	public void setCustomerOrderedProducts(
			Map<String, List<Product>> customerOrderedProducts) {
		this.customerOrderedProducts = customerOrderedProducts;
	}
	
	/**
	 * Prints ordered products by each customer
	 */
	public void customerOrderedProductsPrint() {
        Set<Entry<String, List<Product>>> s = customerOrderedProducts.entrySet();
        Iterator<Entry<String, List<Product>>> it = s.iterator();

        while(it.hasNext()) {
            Map.Entry<String, List<Product>> m = it.next();
            String customer = m.getKey();
            List<Product> productsList = m.getValue();
            System.out.println("Customer: " + customer);
            System.out.println("Products:");
            
            Iterator<Product> productsIterator = productsList.iterator();
            while(productsIterator.hasNext()) {
            	Product product = productsIterator.next();
            	System.out.println(product.toString());
            }
            
        }
	}
	
	/**
	 * Add customer ordered products by customer cart
	 * @param customerName
	 * @param customerSurname
	 * @param customerCart
	 */
	private void addCustomerOrderedProducts(String customerName, String customerSurname, Cart customerCart) {

		if (customerCart.getProducts().size() > 0) {

			String customerKey = customerName + " " + customerSurname;

			if (this.getCustomerOrderedProducts().containsKey(customerKey) == false) {
				this.getCustomerOrderedProducts().put(customerKey, new ArrayList<Product>());
			}

			Iterator<CartProduct> cartProductIterator = customerCart.getProducts().iterator();

			while (cartProductIterator.hasNext()) {
				CartProduct cartProduct = cartProductIterator.next();

				Product product = findProduct(cartProduct.getIsbn());

				if (product != null && this.getCustomerOrderedProducts().get(customerKey).contains(product) == false) {
					this.getCustomerOrderedProducts().get(customerKey).add(product);
				}
			}

		}
	}
	
	@Override
	public void createOrder(String name, String surname, String country,
			String city, String address, String postCode)
			throws EmptyCartException, EmptyValueException {

		this.addCustomerOrderedProducts(name, surname, this.getCart());

		super.createOrder(name, surname, country, city, address, postCode);
	}
	
	/**
	 * Check if date1 and date2 is in same date
	 * @param d1 date1
	 * @param d2 date2
	 * @return boolean
	 */
	private boolean isSameDay(Date d1, Date d2) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(d1);
		Calendar c2 = new GregorianCalendar();
		c2.setTime(d2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
			return true;
		}

		return false;
	}
	
	/**
	 * Calculate total orders price by date
	 * @param date
	 * @return price
	 */
	public double getTotalOrdersAmountByDate(Date date) {
		double amount = 0;
		
		Iterator<Order> ordersIterator = this.getOrders().iterator();
		while (ordersIterator.hasNext()) {
			Order order = ordersIterator.next();
			
			if (isSameDay(order.getCreatedAt(), date)) {
				amount = amount + order.getTotalPrice();
			}

		}

		return amount;
	}
	
	/**
	 * Generates quantity chart data
	 * @return data set
	 */
	public PieDataset availableQuantityDataset() {
		DefaultPieDataset result = new DefaultPieDataset();

		Iterator<Product> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			Product product = productsIterator.next();
			result.setValue(product.getName(), product.getQuantity());
		}
		return result;
	}
	
	/**
	 * Generates week sales for chart
	 * @return data set
	 */
	public IntervalXYDataset weekSalesDataset() {
		XYSeries series = new XYSeries("Day sales");

		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -7);

		int i;
		for (i = 1; i <= 7; i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			series.add(cal.get(Calendar.DAY_OF_MONTH),
					getTotalOrdersAmountByDate(cal.getTime()));
		}

		XYSeriesCollection dataset = new XYSeriesCollection(series);
		return dataset;
	}

}
