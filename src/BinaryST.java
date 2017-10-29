import java.util.ArrayList;

// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

/**
 * 
 * @author Zac Schreck, Adam Kiel
 *
 */
public class BinaryST {
	// member fields and methods
	private Node root;
	private int size;
	private int dsize;
	private ArrayList<String> INorder = new ArrayList<String>();
	private ArrayList<String> PREorder = new ArrayList<String>();

	public BinaryST() {
		// implementation
		root = null;
		size = 0;
		dsize = 0;
	}

	public BinaryST(String[] s) {
		// implementation
		size = 0;
		dsize = 0;
		for (int i = 0; i < s.length; i++) {
			add(s[i]);
		}
	}

	public int distinctSize() {
		// implementation
		return dsize;
	}

	public int size() {
		// implementation
		return size;
	}

	public int sizeHelper(Node n) {
		int count = n.occurances;
		if (n.left != null) {
			count += sizeHelper(n.left);
		}
		if (n.right != null) {
			count += sizeHelper(n.right);
		}
		return count;
	}

	public int height() {
		// implementation
		return heightHelper(root);
	}
	
	private int heightHelper(Node n) {
		if (n == null) {
			return 0;
		}
		return 1 + Math.max(heightHelper(n.left), heightHelper(n.right));
	}

	public void add(String s) {
		// implementation
		Node newNode = new Node(s);
		if (root == null) {
			root = newNode;
			dsize += 1;
			size += newNode.occurances;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (s.compareTo(current.data) < 0) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					dsize += 1;
					size += newNode.occurances;
					return;
				}
			} else if (s.compareTo(current.data) > 0) {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					dsize += 1;
					size += newNode.occurances;
					return;
				}
			} else {
				current.occurances++;
				size += 1;
				return;
			}
		}
	}

	public boolean search(String s) {
		// implementation
		Node current = root;
		while (current != null) {
			if (s.equals(current.data)) {
				return true;
			} else if (s.compareTo(current.data) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	public int frequency(String s) {
		// implementation
		Node current = root;
		while (current != null) {
			if (s.equals(current.data)) {
				return current.occurances;
			} else if (s.compareTo(current.data) < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return 0;
	}

	public boolean remove(String s) {
		if (root == null)
			return false;
		else {
			if (root.data.equals(s)) {
				Node ar = new Node();
				ar.left = root;
				boolean result = root.remove(s, ar);
				root = ar.left;
				return result;
			} else {
				return root.remove(s, null);
			}

		}
	}

	public String[] inOrder() {
		// implementation
		INorder = new ArrayList<String>();
		if (root == null)
			return null;
		inOrderHelper(root);
		return INorder.toArray(new String[INorder.size()]);
	}

	private void inOrderHelper(Node n) {
		if (n == null)
			return;
		inOrderHelper(n.left);
		for (int occur = 0; occur < n.occurances; occur++) {
			INorder.add(n.data);
		}
		inOrderHelper(n.right);
	}

	public String[] preOrder() {
		// implementation
		PREorder = new ArrayList<String>();
		if (root == null)
			return null;
		preOrderHelper(root);
		return PREorder.toArray(new String[PREorder.size()]);
	}

	private void preOrderHelper(Node n) {
		if (n == null)
			return;
		for (int occur = 0; occur < n.occurances; occur++) {
			PREorder.add(n.data);
		}
		preOrderHelper(n.left);
		preOrderHelper(n.right);
	}

	public int rankOf(String s) {
		// implementation
		int rank = 0;
		Node current = root;
		while (current != null) {
			if (s.compareTo(current.data) < 0) // move to left subtree
				current = current.left;
			else if (s.compareTo(current.data) > 0) {
				if (current.left != null) {
					rank += current.occurances + sizeHelper(current.left);
				} else {
					rank += current.occurances;
				}
				current = current.right;
			} else if (current.left != null) {
				return rank + sizeHelper(current.left);
			} else {
				return rank;
			}
		}
		return -1;
	}

	/*****************************************************
	 *
	 * the Node class
	 *
	 ******************************************************/

	private class Node {
		private String data;
		private int occurances;
		private Node left, right;

		public Node(String data, Node l, Node r) {
			left = l;
			right = r;
			this.data = data;
			occurances = 1;
		}

		public Node(String data) {
			this(data, null, null);
		}

		public Node() {
		}

		public boolean remove(String s, Node parent) {
			if (s.compareTo(this.data) < 0) {
				if (left != null)
					return left.remove(s, this);
				else
					return false;
			} else if (s.compareTo(this.data) > 0) {
				if (right != null)
					return right.remove(s, this);
				else
					return false;
			} else {
				if (occurances > 1) {
					occurances--;
					size -= 1;
					return true;
				}
				if (left != null && right != null) {
					this.data = right.minValue();
					right.remove(this.data, this);
				} else if (parent.left == this) {
					parent.left = (left != null) ? left : right;
					dsize--;
					size--;
				} else if (parent.right == this) {
					parent.right = (left != null) ? left : right;
					dsize--;
					size--;
				}
				return true;
			}
		}

		private String minValue() {
			if (left == null)
				return data;
			else
				return left.minValue();
		}

	} // end of Node

}