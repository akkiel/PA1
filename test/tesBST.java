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
		bst.add("CBA");
		assertEquals("Two different nodes should have distinct size of 2.", 2, bst.distinctSize());
		assertEquals("Single node x2 plus 1 distinct should have size of 3.", 3, bst.size());
	}

	@Test
	void testRemove() {
		bst.add("ABC");
		bst.add("CBA");
		bst.add("ABC");
		bst.add("WORD");
		assertEquals("Should start with size 4", 4,  bst.size());
		assertEquals("Should start with distinctSize 3", 3, bst.distinctSize());
		assertEquals("Should start with height 3", 3, bst.height());
		bst.remove("ABC");
		assertEquals("Should have size 3 after removing one duplicate", 3,  bst.size());
		assertEquals("Should have distinctSize 3 after removing one duplicate", 3, bst.distinctSize());
		assertEquals("Should have height 3 after removing duplicate", 3, bst.height());
	}

}
