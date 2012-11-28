package base;

import java.util.Date;

public class CloneTest {

	public static void main(String[] args) throws EmptyValueException {

		// Create original obj with childs
		Order order = new Order(1, "Vardenis", "Pavardenis", "Lietuva",
				"Vilnius", "Didlaukio g.", "LT-0000", null);

		OrderProduct orderProduct = new OrderProduct("1", "Product1", 1.99, 1);
		OrderProduct orderProduct2 = new OrderProduct("2", "Product2", 2.99, 2);

		order.getProducts().add(orderProduct);
		order.getProducts().add(orderProduct2);

		System.out.println(order.toString());

		// Clone
		Order orderClone = (Order) order.clone();

		// Change original
		order.setNum(2);
		Date date = new Date();
		date.setTime(10000);
		order.setCreatedAt(date);

		// Change original childs 
		OrderProduct p1 = order.getProducts().get(0);
		p1.setName("CHANGED!!!!");
		p1.setIsbn("CHANGED!!!!");
		p1.setPrice(999.99);
		p1.setQuantity(9999);
		OrderProduct p2 = order.getProducts().get(1);
		p2.setName("CHANGED!!!!");
		p2.setIsbn("CHANGED!!!!");
		p2.setPrice(999.99);
		p2.setQuantity(9999);
		
		// Print clone
		System.out.println("\n\n###Clone###");
		System.out.println(orderClone.toString());
		
		// Chnaged original
		System.out.println("\n\n###Orig with changes###");
		System.out.println(order.toString());

	}

}
