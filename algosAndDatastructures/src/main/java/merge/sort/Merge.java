package merge.sort;

import java.util.Arrays;

public class Merge<Item extends Comparable<Item>> {


	public void mergeSort(Item[] data){
		Item[] aux = (Item []) new Comparable[data.length];
		recMergeSort(data,aux,0,data.length-1);
	}
	
	
	public void bottomUp(Item[] data){
		int N = data.length;
		Item[] aux = (Item[]) new Comparable[N];
		for(int i=0; i<N; i++) aux[i] = data[i];
		for(int i=1; i<N; i+=i){
			for(int j=0; j<N;j+=i+i){
				merge(data, aux, j, j+i-1, Math.min(N-1, j+i+i-1));
			}
		}
		
	}
	
	private void recMergeSort(Item[] data, Item[] aux, int lo, int hi){
		if(lo >= hi) return;
		int mid = (lo + hi) /2;
		recMergeSort(data, aux, lo, mid);
		recMergeSort(data, aux, mid+1, hi);
		merge(data, aux, lo, mid, hi);
	}
	
	private void merge(Item[] data, Item[] aux, int lo, int mid, int hi){
		
		if(mid+1 >= data.length) return;
		if(lesser(data,mid,mid+1)) return;
		for(int i=lo; i<=hi; i++) aux[i] = data[i];
		
		int i=lo, j=mid+1;
		for(int k=lo; k<=hi; k++){
			if(i > mid) data[k] = aux[j++];
			else if(j > hi) data[k] = aux[i++];
			else if(lesser(aux,i,j)) data[k] = aux[i++];
			else data[k] = aux[j++];
		}
	}
	
	private void check(Item[] data){
		for(int i=1; i<data.length; i++){
			if(lesser(data,i-1,i))continue;
			else {
				System.out.println(i-1+"_"+i);
			}
		}
	}
	private void swap(Item[] data,int i, int j){
		Item buff = data[i];
		data[i] = data[j];
		data[j] = buff;
	}
	
	private boolean lesser(Item[] data, int i, int j){
		return data[i].compareTo(data[j]) < 0;
	}
}
