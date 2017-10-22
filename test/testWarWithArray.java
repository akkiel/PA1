import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class testWarWithArray {
	
	
	@Test
	public void testCompute2k() {

		String[] strSet = {"AAA", "BBB", "CCC"};
		WarWithArray arr = new WarWithArray(strSet, 3);
		
		ArrayList<String> computed = arr.compute2k();
		
		for (String s : computed) {
			System.out.println(s);
		}
		assertEquals("Computed list size should be strSet.size ^ 2 when there are no duplicates in strSet", strSet.length * strSet.length, computed.size());
	}
	
	
}
