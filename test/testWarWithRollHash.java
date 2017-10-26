import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class testWarWithRollHash {

	@Test
	public void testZero() {
		String[] strSet = { "AA", "BB" };
		// AAAA
		// AABB x
		// BBAA x
		// BBBB
		WarWithRollHash arr = new WarWithRollHash(strSet, 2);
		ArrayList<String> computed = arr.compute2k();
		assertEquals("Computed list size should be 2", 2, computed.size());
	}

	@Test
	public void testOne() {
		String[] strSet = { "AA", "BB", "AB" };
		// AAAA
		// AABB
		// BBAA x
		// AAAB
		// ABAA x
		// BBBB
		// BBAB x
		// ABBB
		// ABAB x
		// only two possible outcomes
		WarWithRollHash arr = new WarWithRollHash(strSet, 2);
		ArrayList<String> computed = arr.compute2k();
		assertEquals("Computed list size should be 5", 5, computed.size());
	}

	@Test
	public void testTwo() {
		String[] strSet = { "AA", "BB", "AB", "BA" };
		// AAAA
		// AABB
		// BBAA
		// AAAB
		// ABAA
		// AABA
		// BAAA
		// BBBB
		// BBAB
		// ABBB
		// BBBA
		// BABB
		// ABAB
		// ABBA
		// BAAB
		// BABA
		// only two possible outcomes
		WarWithRollHash arr = new WarWithRollHash(strSet, 2);
		ArrayList<String> computed = arr.compute2k();
		assertEquals("Computed list size should be 16", 16, computed.size());
	}

	@Test
	public void testThree() {
		String[] strSet = { "AAA", "ABA", "BAB" };
		// AAAAAA
		// AAAABA x
		// ABAAAA x
		// AAABAB x
		// BABAAA x
		// ABAABA x
		// ABABAB
		// BABABA
		WarWithRollHash arr = new WarWithRollHash(strSet, 3);
		ArrayList<String> computed = arr.compute2k();
		assertEquals("Computer list size should be 3", 3, computed.size());
	}

}
