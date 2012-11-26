package base;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class EshopV3 extends EshopV2 {

	public EshopV3() {
		this.loadProductsXml();

		// save before exit
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				saveProductsXml();
			}
		}));

	}

	public void loadProductsXml() {
		try {
			File file = new File("xml/products.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(this.getProducts().getClass());
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
			JAXBContext jaxbContext = JAXBContext.newInstance(this.getProducts().getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this.getProducts(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
