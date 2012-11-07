package base;

import java.util.Iterator;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class EshopV2 extends Eshop {
	
	public PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        
        Iterator<Product> productsIterator = this.getProducts().iterator();

		while (productsIterator.hasNext()) {
			Product product = productsIterator.next();
			result.setValue(product.getName(), product.getQuantity());
		}
        return result;
    }
	
}
