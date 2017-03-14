package kornelb.msprep.trees;

import java.util.Random;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRBTree {
	
	private static final int ARRAYSIZE = 100;
	private static int[] randomItemsInserted = new int[ARRAYSIZE]; //helper array 
	private static RBTree<Integer> rbTree;
	
	
	@BeforeClass
	public static void beforeClass(){
		//insert random items into a Red Black Tree
		rbTree = new RBTree<Integer>();
		Random r = new Random();		
		for (int i = 0; i < ARRAYSIZE; i++) {
			randomItemsInserted[i] = r.nextInt(10 * ARRAYSIZE); 
			rbTree.insert(randomItemsInserted[i]);
		}
		rbTree.print();
	}

	/**
	 * Asserts the root is black
	 */
	@Test
	public void test_rootIsBlack() {
		Assert.assertTrue(rbTree.isBlack(rbTree.getRoot()));
	}
	
	/**
	 * Test whether red nodes are isolated, i.e. both children and parent are black
	 */
	@Test
	public void test_redsIsolated() {
		Assume.assumeTrue(!rbTree.isEmpty()); //Do not test with empty trees
		
		for (int item : randomItemsInserted) {
			RBTreeNode<Integer> node = rbTree.search(item);
			
			//Test if the node is present 
			Assert.assertNotNull(node);
			
			//if red, both children should be black
			if (node.isRed()) {
				Assert.assertFalse(node.getParent().isRed()); //parent should be black
				
				//children should be black
				if (node.getLeftNode()!=null) {
					Assert.assertFalse(node.getLeftNode().isRed());
				}
				if (node.getRightNode()!=null) {
					Assert.assertFalse(node.getRightNode().isRed());
				}
			}
		}
	}
	
	/**
	 * Black-height should be the same for each leaf
	 */
	@Test
	public void test_blackHeightEquals() {
		Assume.assumeTrue(!rbTree.isEmpty()); //Do not test with empty trees
		
		int numOfBlacks = 0;
		int numOfBlacksFirstTime = 0;
		for (int item : randomItemsInserted) {
			RBTreeNode<Integer> node = rbTree.search(item);
			
			//if leaf, get the number of blacks up to the root
			if (node.getLeftNode()==null && node.getRightNode()==null) {
				numOfBlacks=0;
				while (node.getParent()!=null) {
					if (!node.isRed()) {
						numOfBlacks++;
					}						
					node = node.getParent();
				}
				if (numOfBlacksFirstTime==0)
					numOfBlacksFirstTime = numOfBlacks;
				
				Assert.assertEquals(numOfBlacksFirstTime, numOfBlacks);
			}
		}
	}
	
}
