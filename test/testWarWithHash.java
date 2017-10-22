import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class testWarWithHash {
	
	
	@Test
	public void testCompute2k() {

		String[] strSet = {"AAA", "BBB", "CCC"};
		WarWithHash hash = new WarWithHash(strSet, 3);
		
		ArrayList<String> computed = hash.compute2k();
		
		assertEquals("Computed list size should be strSet.size ^ 2 when there are no duplicates in strSet", strSet.length * strSet.length, computed.size());
		
		String[] newStrSet = {"AAA", "BBB", "CCC", "AAA", "CCC"};

		WarWithHash newHash = new WarWithHash(newStrSet, 3);
		ArrayList<String> newComputed = newHash.compute2k();
		
		assertTrue("Both computed lists should be identical size the only difference is duplicated", computed.containsAll(newComputed));
		
	}
	
	
	
	
}
