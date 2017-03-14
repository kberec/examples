package kornelb.msprep.sorting;


/**
 * Sorts array of ints using selection sort and merge sort algorithms
 * @author kornelb
 *
 */
public class IntSorter {
	
	/**
	 * Sorts array of ints using selection sort
	 * Worst case time: O (n2)
	 * @param a to be sorted
	 * @param ascending true - ascending, false - descending
	 */
	public static void selectionSort(int[] a, boolean ascending) {
		int minmax;
		int minmaxIndex;
		for (int i = 0; i < a.length - 1; i++) {
			minmax = a[i];
			minmaxIndex = i;
			for (int j = i; j < a.length; j++) {
				if (ascending && a[j]<minmax ||
					!ascending && a[j]>minmax 	
						) {
					minmax = a[j];
					minmaxIndex = j;
				}
			}
			a[minmaxIndex] = a[i];
			a[i] = minmax;
		}
	}
	
	/**
	 * Sorts array of ints using merge
	 * Worst case time: O ( n log n)
	 * @param a to be sorted
	 * @param ascending true - ascending, false - descending
	 */
	public static void mergeSort(int[] a, boolean ascending) {
		mergeSortArray(a, 0, a.length - 1, ascending);
	}
	
	/**
	 * Internal method to be called recursively for merge sort
	 * @param a
	 * @param start
	 * @param end
	 * @param ascending
	 */
	private static void mergeSortArray(int[] a, int start, int end, boolean ascending) {
		if (start==end) {
			return;
		}
		
		//split into two
		int middle = (end + start) / 2;
		
		//call recursively for each half
		mergeSortArray(a, start, middle, ascending);
		mergeSortArray(a, middle+1, end, ascending);
		
		//temp array to hold sorted elements
		int[] temp = new int[end - start + 1];
		
		//sort elements
		int newPosition = 0;
		int left = start;
		int right = middle+1;
		while (left <= middle && right <= end) {
			if ( (ascending  && a[left] < a[right]) || 
				 (!ascending && a[left] > a[right])) {
				temp[newPosition] = a[left];
				left++;
			} else {
				temp[newPosition] = a[right];
				right++;
			}
			newPosition++;
		}
		
		//just move elements if any remains 
		while (left <= middle) {
			temp[newPosition] = a[left];
			left++;
			newPosition++;
		}
		while (right <= end) {
			temp[newPosition] = a[right];
			right++;
			newPosition++;
		}
		
		System.arraycopy(temp, 0, a, start, temp.length); 
	}
}
