// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.Hashtable;
/**
 * 
 * @author Zac Schreck, Adam Kiel
 *
 */
public class WarWithRollHash {
	// member fields and methods
	private static int PRIME_BASE = 17;
	private static int PRIME_MOD = 102564;

	private String[] s;
	private Hashtable<Integer, String> hTab;
	private int k;
	private ArrayList<String> t;
	private int power;

	public WarWithRollHash(String[] s, int k) {
		// implementation
		this.s = s;
		this.k = k;
		this.t = new ArrayList<String>();
		this.hTab = new Hashtable<Integer, String>();
		for (String ss : s) {
			this.hTab.put(hashStr(ss), ss);
		}
		power = 1;
		for (int i = 0; i < k; i++)
			power = (power * PRIME_BASE) % PRIME_MOD;
	}

	private void verify(String verify) {
		boolean toAdd = true;
		int key = hashStr(verify.substring(0, k));
		for (int i = 1; i < (2 * k) - 2; i++) {
			toAdd = false;
			key = key * PRIME_BASE + verify.charAt(i + k - 1);
			key %= PRIME_MOD;
			key -= power * verify.charAt(i - 1) % PRIME_MOD;
			if (key < 0)
				key += PRIME_MOD;

			if (hTab.containsKey(key)) {
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
}
