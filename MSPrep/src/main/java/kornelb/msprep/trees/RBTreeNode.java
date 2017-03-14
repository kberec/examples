package kornelb.msprep.trees;

/**
 * Red Black Tree Node class. Contains links to left and right children and to the parent
 * as well as a boolean flag for red/black attribute.
 * 
 * @author kornelb
 *
 * @param <Key>
 */
public class RBTreeNode<Key extends Comparable<Key>> {	
	private Key key;
	//TODO extend with data
	private RBTreeNode<Key> parent;
	private RBTreeNode<Key> leftNode;
	private RBTreeNode<Key> rightNode;
	private boolean red;
	
	public RBTreeNode(RBTreeNode<Key> parent, Key key, boolean color) {
		super();
		this.parent = parent;
		this.key = key;
		this.red = color;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public RBTreeNode<Key> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(RBTreeNode<Key> leftNode) {
		this.leftNode = leftNode;
	}

	public RBTreeNode<Key> getRightNode() {
		return rightNode;
	}

	public void setRightNode(RBTreeNode<Key> rightNode) {
		this.rightNode = rightNode;
	}

	public boolean isRed() {
		return red;
	}
	
	public String getColorString() {
		return (this.isRed() ? "R" : "B");
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public RBTreeNode<Key> getParent() {
		return parent;
	}

	public void setParent(RBTreeNode<Key> parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "RBTreeNode [key=" + key + ", parent=" + ((parent==null) ? "null " : parent.getKey()) + ", leftNode="
				+ leftNode + ", rightNode=" + rightNode
				+ ", color=" + getColorString() + "]";
	}
	
}
