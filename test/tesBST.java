import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
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
		bst.add("AAA");
		bst.add("ABB");
		bst.add("A");
		assertEquals("Should now have size 6", 6,  bst.size());
		assertEquals("Should now have distinctSize 6", 6, bst.distinctSize());
		assertEquals("Should still have height 3", 3, bst.height());
		bst.remove("A");
		assertEquals("Should now have size 5", 5,  bst.size());
		assertEquals("Should now have distinctSize 5", 5, bst.distinctSize());
		assertEquals("Should still have height 3", 3, bst.height());
		bst.remove("WORD");
		assertEquals("Should now have size 4", 4,  bst.size());
		assertEquals("Should now have distinctSize 4", 4, bst.distinctSize());
		assertEquals("Should still have height 3", 3, bst.height());
		bst.remove("ABB");
		assertEquals("Should now have size 3", 3,  bst.size());
		assertEquals("Should now have distinctSize 3", 3, bst.distinctSize());
		assertEquals("Should still have height 2", 2, bst.height());
		
	}
	
	@Test 
	void testInOrder() {
		String[] stringsToAdd = {"A", "B", "C", "Z", "Y", "F", "A", "A", "Z"};
		
		for(String str : stringsToAdd) {
			bst.add(str);
		}
		
		String[] expectedInOrder = {"A", "A", "A", "B", "C", "F", "Y", "Z", "Z"};
		String[] inOrder = bst.inOrder();

		assertArrayEquals(expectedInOrder, bst.inOrder());
	}
	
	@Test
	void testPreOrder() {
		String[] stringsToAdd = {"C", "Z", "Y", "F", "A", "A", "Z", "A", "B"};
		
		for(String str : stringsToAdd) {
			bst.add(str);
		}
		
		String[] expectedInOrder = {"C", "A", "A", "A", "B", "Z", "Z", "Y", "F"};
		
		assertArrayEquals(expectedInOrder, bst.preOrder());
		
		bst.remove("A");
		String[] newExpectedInOrder = {"C", "A", "A", "B", "Z", "Z", "Y", "F"};
		
		assertArrayEquals(newExpectedInOrder, bst.preOrder());
	}
	
	@Test
	void testFrequency() {
		String[] stringsToAdd = {"C", "Z", "Y", "F", "A", "A", "Z", "A", "B"};
		
		for(String str : stringsToAdd) {
			bst.add(str);
		}
		
		assertEquals("A Should appear three times", 3, bst.frequency("A"));
		assertEquals("B Should appear once", 1, bst.frequency("B"));
	}
	
	@Test
	void testRankOf() {
		String[] stringsToAdd = {"C", "Z", "Y", "F", "A", "A", "Z", "A", "B"};
		
		for(String str : stringsToAdd) {
			bst.add(str);
		}

		assertEquals("Rank of Z shouls be 7", 7, bst.rankOf("Z"));
		assertEquals("Rank of A should be 0", 0, bst.rankOf("A"));
		assertEquals("Rank of B should be 3", 3, bst.rankOf("B"));
		assertEquals("Rank of C should be 4", 4, bst.rankOf("C"));
		assertEquals("Rank of F should be 5", 5, bst.rankOf("F"));
		assertEquals("Rank of Y should be 6", 6, bst.rankOf("Y"));
		assertEquals("Rank of Z should be 7", 7, bst.rankOf("Z"));
		bst.remove("Y");
		assertEquals("Rank of Z should be 6 after removing Y", 6, bst.rankOf("Z"));
	}
}
