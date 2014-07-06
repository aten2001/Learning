package stacks.queues;

import java.util.Iterator;

/**
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are reals numbers so that you can compare them.
 * @author gokulvanan
 *
 */

//TODO - A better approach would be to use Heap for max
public class StackWithMax {

	LinkedStack<Integer> stack;
	LinkedStack<Integer> max;
	
	public void push(Integer it){
		this.stack.push(it);
		pushToMax(it);
	}
	
	public Integer pop(){
		Integer it = this.stack.pop();
		popFromMax(it);
		return it;
	}
	
	public Integer max(){
		return max.peek();
	}
	
	private void pushToMax(Integer it){
		LinkedStack<Integer> buff  = new LinkedStack<>();
		Iterator<Integer> iter = max.iterator();
		boolean notpushed=true;
		while(iter.hasNext()){
			Integer val = iter.next();
			if(it > val) buff.push(val);
			else{
				if(notpushed){
					buff.push(it);
					notpushed=false;
				}
				buff.push(val);
			}
		}
		if(notpushed) buff.push(it);
		max = buff;
	}
	
	private void popFromMax(Integer it){
		
	}
	
	
}
