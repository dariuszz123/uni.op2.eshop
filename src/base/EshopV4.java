package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import visual.DesignV4;


/**
 * Eshop version 4
 * @author Darius Kriðtapavièius
 *
 */
public class EshopV4 extends EshopV3 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Shoppings queue
	 */
	private transient BlockingQueue<Shopping> shoppingQueue;
	
	/**
	 * Design object
	 */
	private DesignV4 design;
	
	/**
	 * Shopping queue getter
	 * @return queue
	 */
	public BlockingQueue<Shopping> getShoppingQueue() {
		return shoppingQueue;
	}
	
	/**
	 * Shopping queue setter
	 * @param shoppingQueue
	 */
	public void setShoppingQueue(BlockingQueue<Shopping> shoppingQueue) {
		this.shoppingQueue = shoppingQueue;
	}
	
	/**
	 * Design getter
	 * @return design or null
	 */
	public DesignV4 getDesign() {
		return design;
	}
	
	/**
	 * Design setter
	 * @param design
	 */
	public void setDesign(DesignV4 design) {
		this.design = design;
	}
	
	/**
	 * Constructor
	 */
	public EshopV4() {
		super();
	}
	
	/**
	 * Save shop object to file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/system.data"));
		output.writeObject(this);
		output.close();
	}
	
	/**
	 * Loads shop object from file
	 * @return Eshop object
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static EshopV4 load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/system.data"));
		EshopV4 shop = (EshopV4) input.readObject();
		input.close();
		return shop;
	}
	
	/**
	 * Start simulates threads shopping
	 * @throws JAXBException
	 */
	public void startShopping() throws JAXBException {
		this.shoppingQueue = new LinkedBlockingQueue<Shopping>(1);
		System.out.println("Shopping started!");
		
		Shoppings shoppings;
		File file = new File("xml/shopping.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Shoppings.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		shoppings = (Shoppings) jaxbUnmarshaller.unmarshal(file);
		
		new Thread(new Producer(this.shoppingQueue, shoppings)).start();
		new Thread(new Consumer(this, shoppings.size())).start();
	}
	
	/*public void shopingGenerator() {
		try {
			Shoppings shoppings = new Shoppings();
			
			Shopping s1 = new Shopping("Vardenis", "Pavardenis", "Lietuva", "Vilnius", "Naugarduko g. 24", "LT-0000", new ArrayList<String>());
			s1.getProducts().add("111-0-00000-000-1");
			s1.getProducts().add("333-0-00000-000-2");
			s1.getProducts().add("555-0-00000-000-2");
			shoppings.add(s1);
			
			Shopping s2 = new Shopping("Vardenis", "Pavardenis", "Lietuva", "Vilnius", "Naugarduko g. 24", "LT-0000", new ArrayList<String>());
			s2.getProducts().add("555-0-00000-000-2");
			shoppings.add(s2);
			
			Shopping s3 = new Shopping("Vardenis", "Pavardenis", "Lietuva", "Vilnius", "Naugarduko g. 24", "LT-0000", new ArrayList<String>());
			s3.getProducts().add("111-0-00000-000-1");
			shoppings.add(s3);
			
			Shopping s4 = new Shopping("Vardenis", "Pavardenis", "Lietuva", "Vilnius", "Naugarduko g. 24", "LT-0000", new ArrayList<String>());
			s4.getProducts().add("555-0-00000-000-2");
			shoppings.add(s4);
			
			System.out.println(shoppings.size());
			
			File file = new File("xml/shopping.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(shoppings.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(shoppings.getShoppings(), file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}
