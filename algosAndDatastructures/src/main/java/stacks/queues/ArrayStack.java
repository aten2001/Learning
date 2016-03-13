package stacks.queues;

public class ArrayStack<Item> {
	
	private Item[] data ;
	private int sz,indx;
	private static final int defaultSize=10;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int size){
		this.data = (Item[]) new Object[size];
		this.sz=size;
		this.indx=0;
	}

	public ArrayStack(){
		this(defaultSize);
	}
	
	public void push(Item val){
		if(indx == sz) resize(2 * sz);
		data[indx++] = val;
	}
	
	public Item pop(){
		if(indx == 0) throw new RuntimeException("Empty stack error");
		Item val = data[--indx];
		data[indx] = null;
		if(indx < sz/4) resize(sz/2);
		return val;
	}
	
	public void reverse(){
		for(int i=indx-1; i>=indx/2; i--){
			int j = indx - 1 -i;
			Item temp  = data[j];
			data[j] = data[i];
			data[i] =temp;
		}
	}
	private void resize(int N){
		@SuppressWarnings("unchecked")
		Item[] buff = (Item []) new Object[N];
		for(int i=0; i<sz; i++){
			buff[i]=data[i];
		}
		data = buff;
		sz = N;
	}
	
	@Override
	public String toString(){
		StringBuilder build = new StringBuilder();
		for(int i=0; i<indx; i++){
			if(i != 0) build.append(" - ");
			build.append(data[i]);
		}
		return build.toString();
	}
	public static void main(String[] arr){
		ArrayStack<Integer>  stack  = new ArrayStack<>();
		for(int i=0; i<10; i++) stack.push((int) (Math.random() * 100));
		System.out.println(stack.toString());
		stack.reverse();
		System.out.println(stack.toString());
	}
	
}
