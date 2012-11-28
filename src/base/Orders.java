package base;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ Orders.class })
public class Orders extends ArrayList<Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396042824856931909L;

	@XmlElement(name = "order")
	public ArrayList<Order> getOrders() {
		return this;
	}
	
}
