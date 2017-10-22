import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class testWarWithBST {

	@Test
	public void testCompute2k() {
		String[] strSet = {"AAA", "BBB", "CCC"};
		WarWithBST bst = new WarWithBST(strSet, 3);
		
		ArrayList<String> computed = bst.compute2k();
		
		assertEquals("Computed list size should be strSet.size ^ 2 when there are no duplicates in strSet", strSet.length * strSet.length, computed.size());
		
		String[] newStrSet = {"AAA", "BBB", "CCC", "AAA", "CCC"};

		WarWithBST newBst = new WarWithBST(strSet, 3);
		ArrayList<String> newComputed = newBst.compute2k();
		
		assertTrue("Both computed lists should be identical size the only difference is duplicated", computed.containsAll(newComputed));
		
	}
}
