// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.Hashtable;

public class WarWithRollHash {
	// member fields and methods
	private static int PRIME_BASE = 17;
	private static int PRIME_MOD = 102564;

	private String[] s;
	private Hashtable<Integer, String> hTab;
	private int k;
	private ArrayList<String> t;

	public WarWithRollHash(String[] s, int k) {
		// implementation
		this.s = s;
		this.k = k;
		this.t = new ArrayList<String>();
		this.hTab = new Hashtable<Integer, String>();
		for (String ss : s) {
			this.hTab.put(hashStr(ss), ss);
		}
	}

	private void verify(String verify) {
		String sub;
		boolean toAdd = true;
		for (int i = 1; i < (2 * k) - 2; i++) {
			sub = verify.substring(i, k + i);
			int key = hashStr(sub);
			int key2 = 0;
			int power = 1;
			for (int j = 0; j < k; j++) {
				power = (power * PRIME_BASE) % PRIME_MOD;
			}
			for (int a = 0; a < 2 * k; a++) {
				key2 = key2 * PRIME_BASE + verify.charAt(a);
				key2 %= PRIME_MOD;
				if (a >= k) {
					key2 -= power * verify.charAt(a - k) % PRIME_MOD;
					if (key2 < 0) {
						key2 += PRIME_MOD;
					}
				}
			}
			toAdd = false;
			// compute rolling hash
			key = roll(verify, key, i);
			if (hTab.contains(key)) {
				toAdd = true;
			}
			if (!toAdd) {
				return;
			}
		}
		t.add(verify);
	}

	public ArrayList<String> compute2k() {
		// implementation
		for (int i = 0; i < s.length; i++) {
			for (int j = i; j < s.length; j++) {
				if (i == j) {
					verify(s[i] + s[j]);
				} else {
					verify(s[i] + s[j]);
					verify(s[j] + s[i]);
				}
			}
		}
		return t;
	}

	public int hashStr(String str) {
		int ret = 0;
		for (int i = 0; i < str.length(); i++) {
			ret = ret * PRIME_BASE + str.charAt(i);
			ret %= PRIME_MOD;
		}
		return ret;
	}

	private int roll(String str, int key, int i) {

		return 0;
	}
}
