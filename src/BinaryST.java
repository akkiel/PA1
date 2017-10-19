import java.util.ArrayList;

// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

public class BinaryST {
	// member fields and methods
	private Node root;
	private ArrayList<String> INorder = new ArrayList<String>();
	private ArrayList<String> PREorder = new ArrayList<String>();

	public BinaryST() {
		// implementation
		root = null;
	}

	public BinaryST(String[] s) {
		// implementation
		for (int i = 0; i < s.length; i++) {
			add(s[i]);
		}
	}

	public int distinctSize() {
		// implementation
		int count = 0;
		if (root != null) {
			count = distinctSizeHelper(root);
		}
		return count;
	}

	private int distinctSizeHelper(Node n) {
		int count = 1;
		if (n.left != null) {
			count += distinctSizeHelper(n.left);
		}
		if (n.right != null) {
			count += distinctSizeHelper(n.right);
		}
		return count;
	}

	public int size() {
		// implementation
		int count = 0;
		if (root != null) {
			count = sizeHelper(root);
		}
		return count;
	}

	private int sizeHelper(Node n) {
		int count = n.occurances;
		if (n.left != null) {
			count += distinctSizeHelper(n.left);
		}
		if (n.right != null) {
			count += distinctSizeHelper(n.right);
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
					return;
				}
			} else if (s.compareTo(current.data) > 0) {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			} else {
				current.occurances++;
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
		// implementation
		Node parent = root;
		Node current = root;
		while (s.compareTo(current.data) != 0) {
			if (current == null)
				return false;
			if (s.compareTo(current.data) < 0) {
				parent = current;
				current = current.left;
			} else {
				parent = current;
				current = current.right;
			}
		}
		if (current.occurances > 1) {
			current.occurances--;
			return true;
		}
		Node newNode;
		if (current.right != null) {
			newNode = current.right;
		} else if (current.left != null) {
			newNode = current.left;
		} else {
			if (parent.left.data.equals(current.data)) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			return true;
		}
		while (newNode.left != null) {
			newNode = newNode.left;
		}
		newNode.right = current.right;
		newNode.left = current.left;
		if (parent.left.data.equals(current.data)) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		return true;
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
					rank+= current.occurances;
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

	} // end of Node

}