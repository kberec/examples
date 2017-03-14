package kornelb.msprep.queue;

import java.util.Arrays;

/**
 * Non generic queue (FIFO), implemented by using arrays. Items added to the rear of the array, read from the front.
 * @author kornelb
 *
 */
public class Queue {
	protected static final int INITIALSIZE = 5;
	private Object[] items;
	private int size;
	private int frontIndex;
	private int rearIndex;
	
	public Queue() {
		this.items = new Object[INITIALSIZE];
		this.size = 0;
		this.frontIndex = 0;
		this.rearIndex = -1;
	}
	
	/**
	 * @return Is the queue empty
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * Get number of elements
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Place the object into the queue
	 * @param ob Object to be placed into the queue
	 */
	public void enqueue(Object ob){
		//resize if needed
		if (size == items.length)
			increaseArraySize();
		
		//circular queue
		if (rearIndex==items.length - 1)
			rearIndex = 0;
		else
			rearIndex++;
		
		items[rearIndex] = ob;
		size++;
		System.out.println("After enqueue: " + this );
	}
	
	/**
	 * Read the first object from the front of the queue
	 * @return Read object
	 */
	public Object dequeue(){
		if (isEmpty())
			throw new RuntimeException("Empty queue");
		Object ob = items[frontIndex];
		items[frontIndex] = null;
		frontIndex++;
		size--; 
		
		if (size <= items.length / 2) 		
			decreaseArraySize();
		
		System.out.println("After dequeue: " + this );
		return ob;
	}

	private void increaseArraySize() {
		System.out.println("Increasing array size");
		int newSize = items.length * 2;
		Object[] newArray = new Object[newSize];
		System.arraycopy(items, frontIndex, newArray, 0, items.length - frontIndex);
		if (frontIndex!=0)
			System.arraycopy(items, 0, newArray, items.length - frontIndex, frontIndex);
		items = newArray;
		frontIndex = 0;
		rearIndex = size -1;
	}
	
	private void decreaseArraySize() {
		int newSize = items.length / 2;
		if (newSize<INITIALSIZE)
			return;
		System.out.println("Decreasing array size");
		Object[] newArray = new Object[newSize];
		System.arraycopy(items, frontIndex, newArray, 0, rearIndex - frontIndex + 1);
		items = newArray;
		frontIndex = 0;
		rearIndex = size -1;
	}
	
	@Override
	public String toString() {
		return "Queue [items=" + Arrays.toString(items) + ", size=" + size
				+ ", frontIndex=" + frontIndex + ", rearIndex=" + rearIndex
				+ "]";
	}
	
	protected int getFrontIndex() {
		return frontIndex;
	}

	protected int getRearIndex() {
		return rearIndex;
	}

	protected int getArrayLenght(){
		return (items==null) ? 0 : items.length;
	}
}
