package stacks.queues;

import utils.StopWatch;

public class ArrayQueue<Item> {

	private static final int defaultSize = 10;
	private Item[] data;
	private int sz;
	private int head,tail;
	
	public ArrayQueue(){
		this(defaultSize);
	}
	public ArrayQueue(int N){
		this.data = (Item []) new Object[N];
		this.sz=0;
		this.head=0;
		this.tail=0;
	}
	
	public void enque(Item it){
		if(tail == data.length) resize(true);
		this.data[tail++]=it;
		this.sz++;
	}
	
	public Item deque(){
		Item it = this.data[head];
		this.data[head++] = null;
		if( --sz < (data.length / 4) ) resize(false);
		return it;
		
	}
	
	@SuppressWarnings("unchecked")
	public void resize(boolean incr){
		if(incr){
			if(sz > ((3 * data.length) /4) ){
				Item[] obj = (Item []) new Object[2 * data.length ];
				for(int k=0,i=head; i<tail; i++){
					obj[k++] = data[i];
				}
				this.data=obj;
			}else{
				for(int k=0,i=head; i<tail; i++){
					data[k++] = data[i];
					data[i] = null;
				}
			}
		}else{
			Item[] obj = (Item []) new Object[data.length / 2];
			for(int k=0, i=head; i<tail; i++){
				obj[k++] = data[i];
			}
			this.data=obj;
		}
		this.head=0;
		this.tail=sz;

	}
	
	@Override
	public String toString(){
		StringBuilder build = new StringBuilder();
		for(int i=head; i<tail; i++){
			if(i != 0) build.append(" - ");
			build.append(data[i]);
		}
		return build.toString();
	}
	public static void main(String[] arr){
		ArrayQueue<Integer>  stack  = new ArrayQueue<>();
		StopWatch watch = new StopWatch();
		for(int i=0; i<250000000; i++){ 
			if(i % 3 != 0) stack.enque((int) (Math.random() * 100));
			else if (i != 0 )stack.deque();
		}
		System.out.println(watch.getDuration());
	//	System.out.println(stack.toString());
	}
	
	
}
