package sorts.simple;

import javax.xml.crypto.Data;

/**
 * Permutation. Given two integer arrays of size N, design a subquadratic '
 * algorithm to determine whether one is a permutation of the other.
 *  That is, do they contain exactly the same entries but, possibly, in a different order.
 * @author gokulvanan
 *
 */
public class Permuation {

	public boolean isPerm (Integer[] first, Integer[] second){
		Sorts<Integer> sorter = new Sorts<>();
		sorter.shell(first);
		sorter.shell(second);
		int count = 0;
		for(int i=0; i<first.length && (first[i] == second[i]);i++) 
			count++;
		if(count == first.length) return true;
		else return false;
	}
	
}
