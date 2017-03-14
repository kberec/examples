package kornelb.msprep.queue;

import org.junit.Assert;
import org.junit.Test;

public class TestQueue {
	
	/**
	 * Constructs a Queue and expects no items present
	 */
	@Test
	public void test_constructor() {
		Queue queue = new Queue();
		Assert.assertEquals(queue.getSize(), 0);
		queue.enqueue("1");
		Assert.assertEquals(queue.getSize(), 1);
	}
	
	/**
	 * Enqueues objects into a newly created queue and expects size to be the number of elements added  
	 */
	@Test
	public void test_enqueues() {
		Queue queue = new Queue();
		Object[] toEnqueue = {"a","b","c"};
		for (Object object : toEnqueue) {
			queue.enqueue(object);
		}
		Assert.assertEquals(toEnqueue.length, queue.getSize());
		Assert.assertEquals(toEnqueue.length - 1, queue.getRearIndex());
		Assert.assertEquals(0, queue.getFrontIndex());
	}
	
	/**
	 * Enqueues objects into a newly created queue and expects elements to dequeuing in the same order they were enqueued  
	 */
	@Test
	public void test_dequeues() {
		Queue queue = new Queue();
		Object[] toEnqueue = {"a","b","c"};
		for (Object object : toEnqueue) {
			queue.enqueue(object);
		}
		for (int i = 0; i < toEnqueue.length; i++) {
			System.out.println(i + "" + toEnqueue[i]);
			Assert.assertEquals(toEnqueue[i],queue.dequeue());
		}
		Assert.assertEquals(queue.getSize(), 0);
	}

	/**
	 * Enqueues more objects than the initial size into a queue and expects array length to be increased  
	 */
	@Test
	public void test_arrayIncreased() {
		Queue queue = new Queue();
		Assert.assertEquals(Queue.INITIALSIZE, queue.getArrayLenght());
		
		for (int i = 0; i < Queue.INITIALSIZE; i++) {
			queue.enqueue(String.valueOf(i));
		}
		queue.enqueue("one more");		
		Assert.assertEquals(Queue.INITIALSIZE * 2, queue.getArrayLenght());
	}
	
}
