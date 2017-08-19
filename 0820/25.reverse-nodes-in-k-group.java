/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	class HeadTail {
    		ListNode head;
    		ListNode tail;
    		public HeadTail(ListNode front, ListNode  tail) {
    			this.head = front;
    			this.tail = tail;
    		}
    	}
    public ListNode reverseKGroup(ListNode head, int k) {
    	if(k < 2) {
    		return head;
    	}
    	

        ListNode iter  = head;
        //BUGWARNING: prev is needed. Otherwise the cut won't be complete
        ListNode prev = null;
        ListNode ret = null;

        while(iter != null) {
        	// System.out.println("iter is at "+iter.val);
        	HeadTail front = new HeadTail(null,null);
        	//first step, iterate
        	front = iterate(iter,k);
        	if (front == null) {
        		return (ret != null  ? ret : head);
        	}
        	if (prev != null) {
        		prev.next = null;
        	}
        	//second step, reverse the old array
        	ListNode newFront = reverse(front.head);

        	//third step, stick'em back
        	//now, front.head is the tail
        	// front.tail is tail.next
        	// newFront is the new head.
        	if (ret == null) {
        		ret =  newFront;
        	}

        	front.head.next = front.tail;
        	if(prev != null) prev.next = newFront;
        	prev = front.head;
        	iter = front.tail;
        }
        return (ret != null) ? ret : head;
    }
    private HeadTail iterate (ListNode iter, int k) {
    	int i = 0;
    	ListNode head = iter;
    	for (i = 1; i < k && iter.next != null ; i++) {
    		iter = iter.next;
    	}
    	if (i < k) {
    		return null;
    	}
    	HeadTail ret = new HeadTail(head, iter.next);
    	iter.next = null;
    	return ret;
    }
    private ListNode reverse(ListNode head) {
    	ListNode iter =  head;
    	ListNode prev =  null;
    	while(iter != null) {
    		ListNode next = iter.next;
    		iter.next = prev;
    		prev = iter;
    		iter = next;
    	}
    	return prev;
    }
}