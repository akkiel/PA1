// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.HashSet;

public class WarWithHash {
	// member fields and methods
	private String[] s;
	private int k;
	private HashSet<String> hSet;

	public WarWithHash(String[] s, int k) {
		this.s = s;
		this.k = k;
		hSet = new HashSet<String>();
	}

	public ArrayList<String> compute2k() {
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
		return hSetToArrayList();
	}
	
	private void verify(String s) {
		if (!hSet.contains(s)) {
			hSet.add(s);
		}
	}
	
	private ArrayList<String> hSetToArrayList() {
		ArrayList<String> t = new ArrayList<String>();
		t.addAll(hSet);
		return t;
	}
}
