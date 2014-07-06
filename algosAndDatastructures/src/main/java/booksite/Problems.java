package booksite;

import java.util.Arrays;

public class Problems {

	/**
	 * A string s is a circular rotation of a string t if it matches when 
	 * the characters are circularly shifted by any number of positions; 
	 * e.g., ACTGACG is a circular shift of TGACGAC, and vice versa. 
	 * Detecting this condition is important in the study of genomic sequences. 
	 * Write a program that checks whether two given strings s and t are circular 
	 * shifts of one another.
	 */
	
	public boolean isCircular(String a, String b){
		return (a.length() == b.length() && a.concat(b).indexOf(b) != -1);
	}
	
	/*
	 * Given an array a[] of N real numbers, design a linear-time algorithm 
	 * to find the maximum value of a[j] - a[i] where j ≥ i.
	 */
	
	public int max(int[] a){
		int min = a[0];
		int max = 0;
		for(int i=1; i<a.length; i++){
			min = Math.min(min, a[i]);
			max = Math.max(max, a[i]-min);
		}
		return max;
	}
	
	/**
	 * Find the duplicate. Given a sorted array of N+2 integers between 0 and N
	 *  with exactly one duplicate, design a logarithmic time algorithm to find 
	 *  the duplicate.
	 *  Hint: Algo compares index to element val - since elements are sorted after duplicate index will be more than the value in it
	 */
	
	public int duplicate(int[] data){
		int lo = 0, hi = data.length-1;
		while(lo < hi){
			int mid = (lo + hi)/2;
			if(data[mid] < mid) hi = mid;
			else if (data[mid] == mid) lo = mid+1;
		}
		return data[lo];
	}
	
	public static void main(String[] args){
		int [] data ={1,3,4,5,73,2,3,0};
		int max = new Problems().max(data);
		System.out.println(max);
		int[] test = {0,1,2,3,3,4,5,6,7};
		System.out.println(new Problems().duplicate(test));

	}
}
