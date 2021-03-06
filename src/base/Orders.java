package base;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@SuppressWarnings("serial")
@XmlRootElement
@XmlSeeAlso({ Orders.class })
public class Orders extends ArrayList<Order> {

	@XmlElement(name = "order")
	public ArrayList<Order> getOrders() {
		return this;
	}
	
}
