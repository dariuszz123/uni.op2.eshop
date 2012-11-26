package base;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;


@XmlRootElement
@XmlSeeAlso({ Products.class })
public class Products extends ArrayList<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8895110000500933187L;

	@XmlElement(name = "product")
	public ArrayList<Product> getProducts() {
		return this;
	}

}
