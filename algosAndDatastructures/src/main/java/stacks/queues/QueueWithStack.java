package stacks.queues;

import java.util.NoSuchElementException;

/**
 * Queue with two stacks. Implement a queue with two stacks so that each queue operations 
 * takes a constant amortized number of stack operations.
 * @author gokulvanan
 *
 *Hint: If you push elements onto a stack and then pop them all, they appear in reverse order. 
 *If you repeat this process, they're now back in order.
 */
public class QueueWithStack<Item> {
	
	LinkedStack<Item> enque  = null;
	LinkedStack<Item> deque = null;
	
	public QueueWithStack(){
		this.enque = new LinkedStack<>();
		this.deque = new LinkedStack<>();
	}
	
	public void enque(Item it){
		this.enque.push(it);
	}

	public void deque(){
		if(this.deque.isEmpty()){
			if(this.enque.isEmpty()) throw new NoSuchElementException();
			else{
				while(!enque.isEmpty()){
					deque.push(enque.pop());
				}
			}
		}else{
			deque.pop();
		}
	}
}
