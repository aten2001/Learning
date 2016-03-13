package stacks.queues;

import utils.StopWatch;

public class RingQueue<Item> {

	Item[] data;
	int sz, head, tail;
	private static final int defaultSize=10;
	
	public RingQueue(){
		this(defaultSize);
	}
	public RingQueue(int N){
		this.data = (Item []) new Object[N];
		this.head=0;
		this.tail=0;
		this.sz=0;
	}
	
	public void enque(Item it){
		if(sz == data.length - 1) resize(true);
		if(tail == data.length) tail=0;
		this.data[tail++]=it;
		sz++;
	}
	
	public Item deque(){
		if(sz < (data.length / 4) ) resize(false);
		if(head == data.length) head=0;
		Item it = this.data[head];
		this.data[head++] = null;
		sz--;
		return it;
	}
	
	public void resize(boolean incr){
		int N = data.length;
		if(incr){
			Item [] buff  = (Item []) new Object[(N * 2)];
			for(int i=head, k=0; i<sz; i++){
				int indx = (i >= N)? (i+ (i % N)) : i;
				buff[k++] = data[indx];
			}
			this.data=buff;
			this.head=0;
			this.tail=sz;
		}else{
			Item[] buff = (Item[]) new Object[N/2];
			for(int i=head,k=0; i<sz; i++){
				int indx = (i >= N) ? (i + (i % N)) : i;
				buff[k++]=data[indx];
			}
			this.data=buff;
			this.head=0;
			this.tail=sz;
		}
	}
	
	@Override
	public String toString(){
		int N = data.length;
		StringBuilder build = new StringBuilder();
		for(int i=head; i<tail; i++){
			int indx = (i >= N) ? (i + (i % N)) : i;
			if(i != 0) build.append(" - ");
			build.append(data[indx]);
		}
		return build.toString();
	}
	
	public static void main(String[] arr){
		RingQueue<Integer>  stack  = new RingQueue<>();
		StopWatch watch = new StopWatch();
		for(int i=0; i<250000000; i++){ 
			if(i % 3 != 0) stack.enque((int) (Math.random() * 100));
			else if (i != 0 )stack.deque();
		}
		System.out.println(watch.getDuration());
	//	System.out.println(stack.toString());
	}
	
}
