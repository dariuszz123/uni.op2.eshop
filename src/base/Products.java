package base;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;


@SuppressWarnings("serial")
@XmlRootElement
@XmlSeeAlso({ Products.class })
public class Products extends ArrayList<Product> {

	@XmlElement(name = "product")
	public ArrayList<Product> getProducts() {
		return this;
	}

}