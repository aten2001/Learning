package stacks.queues;

import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class LinkedStack<Item> implements Iterable<Item>{

	private class Node {
		public Item val;
		public Node next;

		public Node(Item _val){
			this.val = _val;
		}
	}

	private Node head;
	private int size=0;

	public void push(Item item){
		Node buff = head;
		head = new Node(item);
		head.next = buff;
		size++;
	}


	public Item pop(){
		if(head == null) throw new RuntimeException("Empty stack error");
		Node elm = head;
		head = head.next;
		size--;
		return elm.val;
	}


	public Item peek(){
		if(head == null) throw new RuntimeException("Empty stack error");
		return head.val;
	}

	public boolean isEmpty(){
		return this.head == null;
	}
	
	public void reverse(){
		Node current = head;
		Node prev = null;
		Node next = null;
		while(current != null)
		{
			next = current.next;
			current.next=prev;
			prev = current;
			current = next;
			
		}
		head = prev;
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedStackIterator();
	}

	private class LinkedStackIterator implements Iterator<Item>{

		private Node node;
		private int N;

		public LinkedStackIterator(){
			this.node=head; // pointer reference
			this.N=size; // copy

		}
		@Override
		public boolean hasNext() {
			if(N != size) throw new ConcurrentModificationException();
			return (node != null);
		}

		@Override
		public Item next() {
			if(node == null) throw new RuntimeException("Empty stack error");
			if(N != size) throw new ConcurrentModificationException();
			Item val = node.val;
			node = node.next;
			return val;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new RuntimeException("Not Supported");

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
