package base;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@SuppressWarnings("serial")
@XmlRootElement
@XmlSeeAlso({ Shopping.class })
public class Shoppings extends ArrayList<Shopping> {
	
	@XmlElement(name = "shopping")
	public Shoppings getShoppings() {
		return this;
	}
	
}
