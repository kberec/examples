package kornelb.msprep.main;

import java.util.Arrays;
import java.util.Random;

import kornelb.msprep.queue.Queue;
import kornelb.msprep.sorting.IntSorter;
import kornelb.msprep.trees.RBTree;

public class MainApplication {
	private static final int[] itemsToInsert = {50, 40, 75, 25, 45, 47};
	private static final int[] itemsToInsert2 = {1, 11, 8, 6, 13, 17, 15, 25, 22, 27 };
	private static RBTree<Integer> rbTree2;
	private static RBTree<Integer> rbTree;
	
	private static final int ARRAYSIZE = 10;
	
	public static void main(String[] args) {
		useQueues();
		sortArrays();
		populateRBTrees();
		printRBTrees();
	}

	/**
	 * Populate example RB trees with random data 
	 */
	private static void populateRBTrees() {
		//populate RBTree
		rbTree = new RBTree<Integer>();
		for (int i = 0; i < itemsToInsert.length; i++) {
			rbTree.insert(itemsToInsert[i]);
		}
		
		//populate RBTree
		rbTree2 = new RBTree<Integer>();
		for (int i = 0; i < itemsToInsert2.length; i++) {
			rbTree2.insert(itemsToInsert2[i]);
		}
	}
	
	/**
	 * Print out the example RB Trees
	 */
	private static void printRBTrees() {
		rbTree.print();
		rbTree2.print();
	}
	
	
	/**
	 * Example use of IntSorter
	 */
	private static void sortArrays() {
		sortArraysMergeSort();
		sortArraysSelectionSort();
	}

	/**
	 * Example use of IntSorter.mergeSort
	 */
	private static void sortArraysMergeSort() {
		int[] a = new int[ARRAYSIZE];
		populateArray(a);
		System.out.println("Array to sort using merge sort: " + Arrays.toString(a));
		IntSorter.mergeSort(a, true);
		System.out.println("Sorted ascending: " + Arrays.toString(a));
		IntSorter.selectionSort(a, false);
		System.out.println("Sorted descending: " + Arrays.toString(a));
	}

	/**
	 * Example use of IntSorter.selectionSort
	 */
	private static void sortArraysSelectionSort() {
		int[] a = new int[ARRAYSIZE];
		populateArray(a);
		System.out.println("Array to sort using selection sort: " + Arrays.toString(a));
		IntSorter.selectionSort(a, true);
		System.out.println("Sorted ascending: " + Arrays.toString(a));
		IntSorter.selectionSort(a, false);
		System.out.println("Sorted descending: " + Arrays.toString(a));
	}
	
	/**
	 * Populate an array with random ints
	 * @param a
	 */
	private static void populateArray(int[] a) {
		Random r = new Random();		
		for (int i = 0; i < ARRAYSIZE; i++) {
			a[i] = r.nextInt(10 * ARRAYSIZE);
		}
	}

	/**
	 * Example method to use queues
	 */
	private static void useQueues() {
		//create a queue
		Queue queue = new Queue();
		
		//enqueue few elements
		queue.enqueue("1");
		queue.enqueue("2");
		queue.enqueue("3");
		
		//dequeue few elements
		String str = (String) queue.dequeue();
		System.out.println("dequeued: " + str);
		str = (String) queue.dequeue();
		System.out.println("dequeued: " + str);
		
		//enqueue few more
		queue.enqueue("4");
		queue.enqueue("5");
		queue.enqueue("6");
		queue.enqueue("7");
		queue.enqueue("8");
		queue.enqueue("9");

		//dequeue few more
		str = (String) queue.dequeue();
		str = (String) queue.dequeue();

		queue.enqueue(new Long(1l));
	}
}
