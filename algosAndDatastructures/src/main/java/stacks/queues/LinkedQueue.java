package stacks.queues;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>{

	private class Node{
		public Item val;
		public Node next;
		
		public Node(Item _v){
			val = _v;
		}
	}
	
	private Node head,tail;
	private int sz;
	
	public void enque(Item val){
		Node elm = new Node(val);
		if(tail == null){ // case when there is not element
			tail = elm;
			head = tail;
		}
		tail.next = elm;
		tail = tail.next;
		sz++;
	}
	
	public Item deque(){
		if(head == null) throw new RuntimeException("Empty Queue Error");
		Item it = head.val;
		head = head.next;
		if(head == null) tail = null; // case when head has crossed tail
		sz--;
		return it;
	}
	
	public void reverse(){
		Node current = head;
		Node next= null;
		Node prev = null;
		while(current != null){
			next = current.next;
			current.next = prev;
			prev = current;
			current=next;
		}
		tail = head;
		head = prev;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new LinkedQueueIterator();
	}
	
	private class LinkedQueueIterator implements Iterator<Item>{

		private Node node = null;
		private int N;
		
		public LinkedQueueIterator(){
			this.node = head;
			this.N=sz;
		}
		@Override
		public boolean hasNext() {
			if(sz != N) throw new ConcurrentModificationException();
			return head == null;
		}

		@Override
		public Item next() {
			if(sz != N) throw new ConcurrentModificationException();
			if(node == null) throw new RuntimeException("Empty queue exception");
			Item val = node.val;
			node = node.next;
			return val;
		}

		@Override
		public void remove() {
			throw new RuntimeException("Emtpy queue exception");
		}
		
	}
	
	@Override
	public String toString(){
		StringBuilder build = new StringBuilder();
		Node node = head;
		while(node != null){
			build.append(node.val.toString());
			build.append(" - ");
			node = node.next;
		}
		return build.toString();
	}
	
	public static void main(String[] arr){
		LinkedStack<Integer>  stack  = new LinkedStack<>();
		for(int i=0; i<10; i++) stack.push((int) (Math.random() * 100));
		System.out.println(stack.toString());
		stack.reverse();
		System.out.println(stack.toString());
	}

	
}
