package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("serial")
public class EshopV3 extends EshopV2 implements Serializable {

	public EshopV3() {
//		this.loadProductsXml();
//		this.loadOrdersXml();

		// save before exit
//		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//			public void run() {
//				saveProductsXml();
//				saveOrdersXml();
//			}
//		}));

	}

	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/system.data"));
		output.writeObject(this);
		output.close();
	}
	
	public EshopV3 load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/system.data"));
		input.close();
		return (EshopV3) input.readObject();
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
