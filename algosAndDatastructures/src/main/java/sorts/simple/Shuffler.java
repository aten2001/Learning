package sorts.simple;

import java.util.Random;

public class Shuffler<Item> {

	private Random random = new Random();
	
	public Shuffler(){
		random.setSeed(System.currentTimeMillis());
	}
	public void basicShuffle(Item[] data){
		Integer[] indx = new Integer[data.length];
		for(int i=0; i<data.length; i++){
			int j = random.nextInt(10 * data.length);
			indx[i] = j;
		}
	}
	
	public void knutShuffle(Item[] data){
		for(int i=1; i<data.length; i++){
			int j = random.nextInt(i+1);
			swap(data, i, j);
		}
	}
	
	private void swap(Item[] data,int i, int j){
		Item buff = data[i];
		data[i] = data[j];
		data[j] = buff;
	}
}
