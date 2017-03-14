package kornelb.msprep.trees;

/**
 * Red-black tree example.
 * Incomplete, node removal is missing TODO 
 * @author kornelb
 *
 * @param <Key>
 */
public class RBTree<Key extends Comparable<Key>>{
	
	private RBTreeNode<Key> root;
	
	public RBTree() {
		root = null;
	}

	/**
	 * Is the tree empty
	 * @return
	 */
	public boolean isEmpty(){
		return root == null;
	}
	
	/**
	 * Inserts a key into the tree. The inserted element becomes the root if the tree is empty. 
	 * @param key
	 */
	public void insert(Key key) {
        if (root == null) {
            root = new RBTreeNode<Key>(null, key, false);
            return;
        }
                
        RBTreeNode<Key> node = root;
        while (true) {
            int comparisonResult = key.compareTo(node.getKey());
            if (comparisonResult == 0) {
            	return;
            } else if (comparisonResult < 0) {
                if (node.getLeftNode() == null) {
                    node.setLeftNode(new RBTreeNode<Key>(node,key,true));
                    fixAfterInsert(node.getLeftNode());
                    return;
                }
                node = node.getLeftNode();
            } else if (comparisonResult > 0) {
                if (node.getRightNode() == null) {
                    node.setRightNode(new RBTreeNode<Key>(node,key,true));
                    fixAfterInsert(node.getRightNode());
                    return;
                }
                node = node.getRightNode();
            }
        }
    }	

	/**
	 * Fixes violations of RBTree rules. 
	 * @param node
	 */
	private void fixAfterInsert(RBTreeNode<Key> node) {
		//the inserted node should be red 
		setColor(node,true);
		
		//Correct double red problems
		if (node != null && node != root && isRed(getParent(node))) {
			
			if (isRed(getSibling(getParent(node)))) {
				//Case: parent and uncle red => recolor parent, uncle and grandparent
				setColor(getParent(node), false);
				setColor(getSibling(getParent(node)), false);
				setColor(getGrandparent(node), true);
				//continue with fixing violations upwards
				fixAfterInsert(getGrandparent(node));
			} else if (node.getParent() == leftOf(getGrandparent(node))){
				// Case: parent red, uncle black");
				if (node == rightOf(getParent(node))) {
					// double rotation is needed, rotate the parent left at first
				    rotateLeft(node = node.getParent());
				}
				//recolor parent and grandparent
				setColor(getParent(node), false);				
				setColor(getGrandparent(node), true);
				// rotate the grandparent right				
				rotateRight(getGrandparent(node));				
			} else if (node.getParent() == rightOf(getGrandparent(node))){
				// Case: parent red, uncle black");
				if (node == leftOf(getParent(node))) {
					// double rotation is needed, rotate the parent right at first
				    rotateRight(node = node.getParent());
				}
				//recolor parent and grandparent
				setColor(getParent(node), false);
				setColor(getGrandparent(node), true);
				// rotate the grandparent left				
				rotateLeft(getGrandparent(node));				
			}
		}
		//root should always remain black
		setColor(root, false);
	}

	/**
	 * Prints out the tree using System.out 
	 */
	public void print() {
		print(this.root);
		System.out.println(this.root);
	}
	
	/**
	 * Prints out the given tree using System.out 
	 */
	public static void print(RBTreeNode<?> node) {
		if (node!=null){
			print(node.getLeftNode());
			System.out.println(node.getKey() + "(" + node.getColorString() + ")");
			print(node.getRightNode());
		}
	}
	
	/**
	 * Rotates the given node left.
	 * @param node
	 */
	protected void rotateLeft(RBTreeNode<Key> node) {
        if (node.getRightNode() == null) {
            return;
        }
        RBTreeNode<Key> oldRight = node.getRightNode();
        node.setRightNode((oldRight.getLeftNode()));
        if (oldRight.getLeftNode()!=null)
        	oldRight.getLeftNode().setParent(node);
        if (node.getParent() == null) {
            root = oldRight;
            root.setParent(null);
        } else if (node.getParent().getLeftNode() == node) {
            node.getParent().setLeftNode(oldRight);
            oldRight.setParent(node.getParent());
        } else {
            node.getParent().setRightNode(oldRight);
            oldRight.setParent(node.getParent());
        }
        oldRight.setLeftNode(node);
        node.setParent(oldRight);
    }

    /**
     * Rotates the given node right.
     */
    protected void rotateRight(RBTreeNode<Key> node) {
        if (node.getLeftNode() == null) {
            return;
        }
        RBTreeNode<Key> oldLeft = node.getLeftNode();
        node.setLeftNode(oldLeft.getRightNode());
        if (oldLeft.getRightNode()!=null) //
        	oldLeft.getRightNode().setParent(node); //
        if (node.getParent() == null) {
            root = oldLeft;
            root.setParent(null);
        } else if (node.getParent().getLeftNode() == node) {
            node.getParent().setLeftNode(oldLeft);
            oldLeft.setParent(node.getParent());
        } else {
            node.getParent().setRightNode(oldLeft);
            oldLeft.setParent(node.getParent());
        }
        oldLeft.setRightNode(node);
        node.setParent(oldLeft);
    }
    
	/**
	 * Searches for key in the tree.
	 * @param key
	 * @return null if not found, instance of RBTreeNode<Key> if  found 
	 */
	public RBTreeNode<Key> search(Key key) {
        if (root == null) {
        	return null;
        }
        RBTreeNode<Key> node = root;
        while (true) {
            int comparisonResult = key.compareTo(node.getKey());
            if (comparisonResult == 0) {
            	return node;
            } else if (comparisonResult < 0) {
                if (node.getLeftNode() == null) 
                	return null; //not found
                node = node.getLeftNode();
            } else if (comparisonResult > 0) {
                if (node.getRightNode() == null)
                	return null; //not found
                node = node.getRightNode();
            }
        }
    }

	//TODO remove node
	
	//
	//Helper methods below, to simplify null checks. 
	//
	
	private  boolean isRed(RBTreeNode<Key> node) {
		return node!=null && node.isRed(); 
	}
	
	public boolean isBlack(RBTreeNode<Key> node) {
		return node==null || !node.isRed(); 
	}
	
	public RBTreeNode<Key> getParent(RBTreeNode<Key> node) {
		return (node == null) ? null : node.getParent();
	}
	public RBTreeNode<Key> getGrandparent(RBTreeNode<Key> node) {
		return (node == null || node.getParent() == null) ? null : node.getParent().getParent();
	}

	public void setColor(RBTreeNode<Key> node, boolean setToRed) {
		if (node!=null)
			node.setRed(setToRed);
	}
	
	public RBTreeNode<Key> getSibling(RBTreeNode<Key> node) {
		if (node == null || node.getParent() == null) return null;
		return (node == node.getParent().getLeftNode()) 
			? node.getParent().getRightNode()
			: node.getParent().getLeftNode();
	}
	
	private RBTreeNode<Key> leftOf(RBTreeNode<Key> node) {
        return node == null ? null : node.getLeftNode();
    }

	private RBTreeNode<?> rightOf(RBTreeNode<?> node) {
        return node == null ? null : node.getRightNode();
    }

	protected RBTreeNode<Key> getRoot() {
		return root;
	}
}
