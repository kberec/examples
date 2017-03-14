package kornelb.msprep.sorting;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for IntSorter
 * @author kornelb
 *
 */
public class TestIntSorter {
	private static final int ARRAYSIZE = 100;
	private static int[] a;
	
	@Before
	public void before(){
		a = new int[ARRAYSIZE];
		Random r = new Random();		
		for (int i = 0; i < ARRAYSIZE; i++) {
			a[i] = r.nextInt(10 * ARRAYSIZE);
		}
	}

	/**
	 * Sorts an array using selection sort and expects the array to be in ascending order 
	 */
	@Test
	public void test_selectionSort_ascending() {
		IntSorter.selectionSort(a, true);
		Assert.assertEquals(ARRAYSIZE, a.length);
		for (int i = 0; i < a.length - 2; i++) {
			Assert.assertTrue(a[i] <= a[i+1]);
		}
	}
	
	/**
	 * Sorts an array using selection sort and expects the array to be in descending order 
	 */
	@Test
	public void test_selectionSort_descending() {
		IntSorter.selectionSort(a, false);
		Assert.assertEquals(ARRAYSIZE, a.length);
		for (int i = 0; i < a.length - 2; i++) {
			Assert.assertTrue(a[i] >= a[i+1]);
		}
	}
	
	/**
	 * Sorts an array using merge sort and expects the array to be in ascending order 
	 */
	@Test
	public void test_mergeSort_ascending() {
		IntSorter.mergeSort(a, true);
		Assert.assertEquals(ARRAYSIZE, a.length);
		for (int i = 0; i < a.length - 2; i++) {
			Assert.assertTrue(a[i] <= a[i+1]);
		}
	}
	
	/**
	 * Sorts an array using merge sort and expects the array to be in descending order 
	 */
	@Test
	public void test_mergeSort_descending() {		
		IntSorter.mergeSort(a, false);
		Assert.assertEquals(ARRAYSIZE, a.length);
		for (int i = 0; i < a.length - 2; i++) {
			Assert.assertTrue(a[i] >= a[i+1]);
		}
	}
	
}
