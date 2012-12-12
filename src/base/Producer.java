package base;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	
	private BlockingQueue<Shopping> queue;
	private Shoppings shoppings;
	
	public Producer(BlockingQueue<Shopping> queue, Shoppings shoppings) {
		this.queue = queue;
		this.shoppings = shoppings;
	}

	@Override
	public void run() {
		try {
			Iterator<Shopping> shoppingsIterator = shoppings.iterator();

			while (shoppingsIterator.hasNext()) {
				Shopping shopping = shoppingsIterator.next();
				
				while(this.queue.remainingCapacity() == 0) {
					System.out.println("Producer sleeping (1000) queue full!");
					Thread.sleep(1000);
				}
				
				System.out.println("Producer put shopping to queue.");
				this.queue.put(shopping);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
