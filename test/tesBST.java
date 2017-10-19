import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class tesBST {

	BinaryST bst = new BinaryST();

	@Before
	void initialize() {
		bst = new BinaryST();
	}

	@Test
	void allMethodsForEmptyBST() {
		assertEquals("Empty tree should have distinct size of 0.", 0, bst.distinctSize());
		assertEquals("Empty tree should have size of 0.", 0, bst.size());
		assertEquals("Empty tree should have height of 0.", 0, bst.height());
		assertEquals("Search in an empty tree should return false.", false, bst.search("A"));
		assertEquals("Frequency of any s in an empty tree should be 0.", 0, bst.frequency("A"));
		assertEquals("Rank of any s in an empty tree should be -1.", -1, bst.rankOf("A"));
	}

	@Test
	void testAdd() {
		bst.add("ABC");
		assertEquals("Single node should have distinct size of 1.", 1, bst.distinctSize());
		assertEquals("Single node should have size of 1.", 1, bst.size());
		bst.add("ABC");
		assertEquals("Single node x2 should have distinct size of 1.", 1, bst.distinctSize());
		assertEquals("Single node x2 should have size of 2.", 2, bst.size());
	}

}
