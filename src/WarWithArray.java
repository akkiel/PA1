// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
/**
 * 
 * @author Zac Schreck, Adam Kiel
 *
 */
public class WarWithArray {
	// member fields and methods
	private String[] s;
	private int k;
	private ArrayList<String> t;

	public WarWithArray(String[] s, int k) {
		// implementation
		this.s = s;
		this.k = k;
		t = new ArrayList<String>();
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

	private void verify(String verify) {
		String sub;
		boolean toAdd = true;
		for (int i = 1; i < (2*k)-2; i++) {
			sub = verify.substring(i, k + i);
			toAdd = false;
			for (String ss : s) {
				if(ss.equals(sub)) {
					toAdd = true;
					break;
				}
			}
			if (!toAdd) {
				return;
			}
		}
		t.add(verify);
	}
}
