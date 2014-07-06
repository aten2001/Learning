package stacks.queues;

/**
 * Clone a linked structure with two pointers per node. Suppose that you are given a reference to the first node of a linked structure where each node has two pointers: one pointer to the next node in the sequence (as in a standard singly-linked list) and one pointer to an arbitrary node.
    private class Node {
        private String item;
        private Node next;
        private Node random;
    }
Design a linear-time algorithm to create a copy of the doubly-linked structure. You may modify the original linked structure, but you must end up with two copies of the original.
 * @author gokulvanan
 *
 */
public class CloneALinkedList {

	static class LinkedStruct{
		static class Node {
			public String item;
			public Node next;
			public Node random;
			
			public Node(String it,Node n, Node r){
				this.item=it;
				this.next=n;
				this.random=r;
			}
		}
		public Node head;
		
		public Node clone(){
			return recClone(head);
		}
		
		public Node recClone(Node node){
			if(node == null) return null;
			else return new Node(node.item, recClone(node.next), recClone(node.random));
		}
		
		public void revese(){
			if(head != null) {
				head = recReverse(null,head,head.next);
			}
		}
		
		public Node recReverse(Node prev, Node current,Node next){
			if(current == null) return prev;
			next = current.next;
			current.next=prev;
			prev = current;
			current = next;
			return recReverse(prev, current, next);
		}
		
		//TODO try a iterative approach to this
	}
	
}
