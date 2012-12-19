package base;

import java.io.File;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * Eshop version 3
 * @author Darius Kriðtapavièius
 *
 */
@SuppressWarnings("serial")
public class EshopV3 extends EshopV2 {
	
	/**
	 * Constructor
	 */
	public EshopV3() {
		super();
		//loadProductsXml();
	}
	
	/**
	 * Load products from xml file
	 */
	public void loadProductsXml() {
		try {
			File file = new File("xml/products.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(this
					.getProducts().getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Products products = (Products) jaxbUnmarshaller.unmarshal(file);
			this.setProducts(products);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save products to xml file
	 */
	public void saveProductsXml() {

		try {
			File file = new File("xml/products.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(this
					.getProducts().getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this.getProducts(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Load orders from xml file
	 */
	public void loadOrdersXml() {
		try {
			File file = new File("xml/orders.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(this.getOrders()
					.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Orders orders = (Orders) jaxbUnmarshaller.unmarshal(file);
			this.setOrders(orders);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save orders to xml file
	 */
	public void saveOrdersXml() {
		try {
			File file = new File("xml/orders.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(this.getOrders()
					.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this.getOrders(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sort all orders by order number ASC
	 */
	public void sortOrdersByNum() {
		Collections.sort(getOrders());
	}
	
	/**
	 * Sort all orders by order price DESC
	 */
	public void sortOrdersByPrice() {
		Collections.sort(getOrders(), Order.OrderPriceComparator);
	}

}
