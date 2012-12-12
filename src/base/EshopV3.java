package base;

import java.io.File;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("serial")
public class EshopV3 extends EshopV2 {

	public EshopV3() {
		super();
		//loadProductsXml();
	}

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

	public void sortOrdersByNum() {
		Collections.sort(getOrders());
	}

	public void sortOrdersByPrice() {
		Collections.sort(getOrders(), Order.OrderPriceComparator);
	}

}
