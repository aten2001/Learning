package stacks.queues;

import javax.management.RuntimeErrorException;

/**
 * Detect cycle in a linked list. A singly-linked data structure is a data structure made up of nodes where each node has a pointer to the next node (or a pointer to null). Suppose that you have a pointer to the first node of a singly-linked list data structure:
Determine whether a singly-linked data structure contains a cycle. You may use only two pointers into the list (and no other variables). The running time of your algorithm should be linear in the number of nodes in the data structure.
If a singly-linked data structure contains a cycle, determine the first node that participates in the cycle. you may use only a constant number of pointers into the list (and no other variables). The running time of your algorithm should be linear in the number of nodes in the data structure.
You may not modify the structure of the linked list.
 * @author gokulvanan
 *
 */
public class CycleInALinkedList {

	/*
	 * Solving this involves using the hare and tortise algorithm
	 */
	
	static class Node{
		public String data;
		public Node next;
	}
	
	private Node head;
	
	public boolean isThereACycle(){
		if (head == null) throw new RuntimeException("NOt applicable");
		Node hare = incrHare(head);
		Node tortoise = head;
		while(hare != tortoise){
			if(hare == null) return false;
			else if(tortoise == null) return false;
			hare = incrHare(hare);
			tortoise = tortoise.next;
		}
		
		return true;
		
	}
	
	private Node incrHare(Node h){
		if(h == null) return null;
		Node hare = h.next; 
		return (hare == null) ? null : hare.next;
	}
}
